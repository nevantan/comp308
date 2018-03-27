package events;

import tme4.*;

public class PrintState extends Event {
  public PrintState(long delayTime, Controller controller) {
    super(delayTime, controller);
  }

  public void action() throws ControllerException {
    System.out.println("\n=================\n");
    System.out.println(this.controller);
    System.out.println("=================\n");
  }
}