package events;

import tme4.*;

public class FansOn extends Event {
  public FansOn(long delayTime, Controller controller) {
    super(delayTime, controller);
  }

  public void action() throws ControllerException {
    this.controller.setVariable("Fans", true);
    this.controller.log("Fans are now on");
  }
}