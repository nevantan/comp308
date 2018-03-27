package events;

import tme4.*;

public class WindowMalfunction extends Event {
  public WindowMalfunction(long delayTime, Controller controller) {
    super(delayTime, controller);
  }

  public void action() throws ControllerException {
    this.controller.setVariable("Window Ok", false);
    this.controller.setVariable("Error Code", 1);
    throw new ControllerException(this.toString());
  }

  public String toString() {
    return "There is a problem with the greenhouse windows!";
  }
}