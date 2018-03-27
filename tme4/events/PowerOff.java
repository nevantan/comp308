package events;

import tme4.*;

public class PowerOff extends Event {
  public PowerOff(long delayTime, Controller controller) {
    super(delayTime, controller);
  }

  public void action() throws ControllerException {
    this.controller.setVariable("Power On", false);
    this.controller.setVariable("Error Code", 2);
    throw new ControllerException(this.toString());
  }

  public String toString() {
    return "The power has gone out!";
  }
}