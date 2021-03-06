package events;

import tme4.*;

public class LightOff extends Event {
  public LightOff(long delayTime, Controller controller) {
    super(delayTime, controller);
  }

  public void action() throws ControllerException {
    this.controller.setVariable("Lights", false);
    this.controller.log("Lights are now off");
  }
}