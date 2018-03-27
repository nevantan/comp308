package tme4;

import java.lang.reflect.*;
import java.lang.Thread;

public abstract class Controller {
  protected Boolean _running;

  public Controller() {
    this._running = true;
  }

  public void addEvent(Event e) {
    Thread t = new Thread(e);
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

  public abstract void setVariable(String key, Object value);
}