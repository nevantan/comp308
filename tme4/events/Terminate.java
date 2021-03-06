package events;

import tme4.*;

public class Terminate extends Event {
  public Terminate(long delayTime, Controller controller) {
    super(delayTime, controller);
  }

  public void action() throws ControllerException {
    this.controller.log("Terminating execution...");
    this.controller.killAll();
  }
}