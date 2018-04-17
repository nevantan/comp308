package events;

import tme4.*;

public class WaterOff extends Event {
  public WaterOff(long delayTime, Controller controller) {
    super(delayTime, controller);
  }

  public void action() throws ControllerException {
    this.controller.setVariable("Water", false);
    this.controller.log("Water is now off");
  }
}