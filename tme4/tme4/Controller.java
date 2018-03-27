package tme4;

import java.lang.Thread;

public abstract class Controller {
  protected Object monitor;
  protected static Boolean _running;

  public Controller() {
    this.monitor = new Object();
    this._running = false;
  }

  public void addEvent(Event e) {
    Thread t = new Thread(e);
    t.start();
  }

  public static Boolean running() {
    return _running;
  }
}