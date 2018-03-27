package tme4;

import java.lang.Runnable;
import java.lang.InterruptedException;

public abstract class Event implements Runnable {
  protected final long delayTime;
  protected long curTime;
  protected long lastTime;
  protected Object monitor;

  public Event(long delayTime, Object monitor) {
    this.delayTime = delayTime;
    this.curTime = delayTime;

    this.monitor = monitor;
  }

  // Thread start method
  public void run() {
    this.lastTime = System.currentTimeMillis();
    
    try {
      synchronized(this.monitor) {
        while(tick()) {
          while(!Controller.running()) {
            this.monitor.wait();
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

  public void setMonitor(Object monitor) {
    this.monitor = monitor;
  }

  public abstract void action() throws ControllerException;
}