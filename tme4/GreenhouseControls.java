import tme4.*;
import events.*;

import java.util.ArrayList;

public class GreenhouseControls extends Controller {
  protected ArrayList<Tuple> states;

  public GreenhouseControls() {
    super();

    this.states = new ArrayList<>();

    try {
      setVariable("Lights", false);
      setVariable("Lights", true);
    } catch(ControllerException e) {
      e.printStackTrace();
    }
  }

  public void setVariable(String key, Object value) throws ControllerException {
    synchronized(this.states) {
      for(int i = 0; i < this.states.size(); i++) {
        Tuple state = this.states.get(i);

        if(state.key == key) {
          if(!(state.value.getClass().equals(value.getClass()))) throw new ControllerException("Type of value does not match state key provided.");

          this.states.set(i, new Tuple(key, value));
          return;
        }
      }

      states.add(new Tuple(key, value));
    }
  }

  public Object getVariable(String key) {
    synchronized(this.states) {
      for(Tuple state : this.states) {
        if(state.key == key) return state.value;
      }
    }

    return null;
  }

  public void setLights(Boolean state) { try { this.setVariable("Lights", state); } catch(ControllerException e) {} }
  public Boolean getLights() { return (Boolean)this.getVariable("Lights"); }

  public void setWater(Boolean state) { try { this.setVariable("Water", state); } catch(ControllerException e) {} }
  public Boolean getWater() { return (Boolean)this.getVariable("Water"); }

  public static void main(String[] args) {
    GreenhouseControls gc = new GreenhouseControls();
    gc.addEvent(new Restart(0, gc, "settings.txt", false));
  }
}