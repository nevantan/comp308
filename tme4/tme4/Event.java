package tme4;

import java.lang.Runnable;
import java.lang.InterruptedException;

public abstract class Event implements Runnable {
  protected final long delayTime;
  protected long curTime;
  protected long lastTime;
  protected Controller controller;

  public Event(long delayTime, Controller controller) {
    this.delayTime = delayTime;
    this.curTime = delayTime;

    this.controller = controller;
  }

  // Thread start method
  public void run() {
    this.lastTime = System.currentTimeMillis();
    
    try {
      synchronized(this.controller) {
        this.controller.incrementThreads();
      }

      while(tick()) {
        synchronized(this.controller) {
          while(!this.controller.running() || this.controller.kill()) {
            if(this.controller.kill()) return;
            this.controller.wait();
            this.lastTime = System.currentTimeMillis();
          }
        }
      }

      this.action();
    } catch(InterruptedException e) {
      e.printStackTrace();
    } catch(ControllerException e) {
      synchronized(this.controller) {
        this.controller.shutdown(e.getMessage());
      }
    } finally {
      synchronized(this.controller) {
        this.controller.decrementThreads();
        this.controller.callback(this);
      }
    }
  }

  // Countdown
  private Boolean tick() {
    long now = System.currentTimeMillis();
    long delta = now - this.lastTime;

    this.curTime -= delta;
    this.lastTime = now;

    return this.curTime > 0;
  }

  public long getTime() {
    return this.curTime;
  }

  public String serialize() {
    return "event=" + this.getClass().getSimpleName() + ",delay=" + this.curTime;
  }

  public abstract void action() throws ControllerException;
}