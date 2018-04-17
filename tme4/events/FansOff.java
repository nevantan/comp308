package events;

import tme4.*;

public class FansOff extends Event {
  public FansOff(long delayTime, Controller controller) {
    super(delayTime, controller);
  }

  public void action() throws ControllerException {
    this.controller.setVariable("Fans", false);
    this.controller.log("Fans are now off");
  }
}