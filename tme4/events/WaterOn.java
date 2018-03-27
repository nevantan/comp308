package events;

import tme4.*;

public class WaterOn extends Event {
  public WaterOn(long delayTime, Controller controller) {
    super(delayTime, controller);
  }

  public void action() throws ControllerException {
    this.controller.setVariable("Water", true);
    System.out.println("Water is now on");
  }
}