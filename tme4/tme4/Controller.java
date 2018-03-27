package tme4;

import java.lang.reflect.*;
import java.lang.Thread;

public abstract class Controller {
  protected Object monitor;
  protected static Boolean _running;

  public Controller() {
    this.monitor = new Object();
    this._running = true;
  }

  public void addEvent(Event e) {
    Thread t = new Thread(e);
    t.start();
  }

  public void addEvent(String name, long delayTime) {
    try {
      Class cl = Class.forName("events." + name);
      Constructor con = cl.getConstructor(long.class, Object.class);
      Object o = con.newInstance(delayTime, this.monitor);
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

  public static Boolean running() {
    return _running;
  }
}