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
      while(tick()) {
        synchronized(this.controller) {
          while(!this.controller.running()) {
            this.controller.wait();
            this.lastTime = System.currentTimeMillis();
          }
        }
      }

      this.action();
    } catch(InterruptedException e) {
      e.printStackTrace();
    } catch(ControllerException e) {
      e.printStackTrace();
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

  public String serialize() {
    return "Event=" + this.getClass().getName() + ",delay=" + this.delayTime;
  }

  public abstract void action() throws ControllerException;
}