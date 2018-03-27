package events;

import tme4.*;

public class FansOn extends Event {
  public FansOn(long delayTime, Controller controller) {
    super(delayTime, controller);
  }

  public void action() throws ControllerException {
    System.out.println("Fans are now on");
  }
}