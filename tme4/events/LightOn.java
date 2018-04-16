package events;

import tme4.*;

public class LightOn extends Event {
  public LightOn(long delayTime, Controller controller) {
    super(delayTime, controller);
  }

  public void action() throws ControllerException {
    this.controller.setVariable("Lights", true);
    this.controller.log("Lights are now on");
    System.out.println("Lights are now on");
  }
}