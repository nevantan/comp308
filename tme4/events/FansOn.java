package events;

import tme4.Event;
import tme4.ControllerException;

public class FansOn extends Event {
  public FansOn(long delayTime, Object monitor) {
    super(delayTime, monitor);
  }

  public void action() throws ControllerException {
    System.out.println("Fans are now on");
  }
}