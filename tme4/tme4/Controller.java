package tme4;

import java.lang.reflect.Method;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.lang.reflect.*;
import java.lang.Thread;
import java.util.ArrayList;

import javafx.scene.control.TextArea;

public abstract class Controller {
  protected Boolean _running;
  protected Boolean _kill;
  protected TextArea logOutput;

  protected Object o;
  protected Method cb;

  protected int threadCount;

  public Controller(TextArea logOutput, Object o, Method cb) {
    this._running = true;
    this._kill = false;

    this.logOutput = logOutput;

    this.o = o;
    this.cb = cb;
  }

  public void addEvent(Event e) {
    this._kill = false;

    Controller self = this;
    Thread.UncaughtExceptionHandler h = new Thread.UncaughtExceptionHandler() {
      public void uncaughtException(Thread th, Throwable ex) {
        shutdown(ex.getMessage());
      }
    };
    Thread t = new Thread(e);
    t.setUncaughtExceptionHandler(h);
    t.start();
  }

  public void addEvent(String name, long delayTime) {
    try {
      Class cl = Class.forName("events." + name);
      Constructor con = cl.getConstructor(long.class, Controller.class);
      Object o = con.newInstance(delayTime, this);
      Event e = (Event)o;

      this.addEvent(e);
    } catch(ClassNotFoundException e) { // TODO
      e.printStackTrace();
    } catch(NoSuchMethodException e) { // TODO
      e.printStackTrace();
    } catch(InstantiationException e) { // TODO
      e.printStackTrace();
    } catch(IllegalAccessException e) { // TODO
      e.printStackTrace();
    } catch(InvocationTargetException e) { // TODO
      e.printStackTrace();
    }
  }

  public Boolean running() {
    synchronized(this) {
      this.notifyAll();
    }
    return this._running;
  }

  public void running(Boolean running) {
    synchronized(this) {
      this._running = running;
      this.notifyAll();
    }
  }

  public Boolean kill() {
    synchronized(this) {
      this.notifyAll();
    }
    return this._kill;
  }

  public void killAll() {
    synchronized(this) {
      this._kill = true;
      this.notifyAll();
    }
  }

  public void incrementThreads() {
    this.threadCount++;
  }

  public void decrementThreads() {
    this.threadCount--;
  }

  public void callback(String action) {
    try {
      this.cb.invoke(this.o, new Object[] { action });
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  public int threads() {
    return this.threadCount;
  }

  public void log(String message) {
    String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    logOutput.setText(logOutput.getText() + "[" + timestamp + "] " + message + "\n");
  }

  public abstract void shutdown(String message);
  public abstract void setVariable(String key, Object value) throws ControllerException;
}