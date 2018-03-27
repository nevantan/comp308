package events;

import tme4.*;

public class LightOn extends Event {
  public LightOn(long delayTime, Controller controller) {
    super(delayTime, controller);
  }

  public void action() throws ControllerException {
    this.controller.setVariable("Lights", true);
    System.out.println("Lights are now on");
  }
}