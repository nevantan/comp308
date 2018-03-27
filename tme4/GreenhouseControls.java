import tme4.*;
import events.*;

public class GreenhouseControls extends Controller {
  public GreenhouseControls() {
    super();
    this.monitor = new Object();
  }

  public static void main(String[] args) {
    GreenhouseControls gc = new GreenhouseControls();
    //gc.addEvent(new FansOn(2000, gc.monitor));
    gc.addEvent("FansOn", 2000);
  }
}