package events;

import tme4.*;

public class ThermostatDay extends Event {
  public ThermostatDay(long delayTime, Controller controller) {
    super(delayTime, controller);
  }

  public void action() throws ControllerException {
    this.controller.setVariable("Thermostat", "Day");
    this.controller.log("The thermostat is now on daytime settings");
  }
}