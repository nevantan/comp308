package tme4;

import events.*;

import java.util.ArrayList;

public class GreenhouseControls extends Controller {
  protected ArrayList<Tuple> states;

  public GreenhouseControls() {
    super();

    this.states = new ArrayList<>();

    // Set initial state
    this.setLights(false);
    this.setWater(false);
    this.setFans(false);
    this.setThermostat("Day");
    this.setEventsFile("");
    this.setWindowOk(true);
    this.setPowerOn(true);
    this.setErrorCode(0);
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

  // Convenience getters and setters
  public void setLights(Boolean state) { try { this.setVariable("Lights", state); } catch(ControllerException e) {} }
  public Boolean getLights() { return (Boolean)this.getVariable("Lights"); }

  public void setWater(Boolean state) { try { this.setVariable("Water", state); } catch(ControllerException e) {} }
  public Boolean getWater() { return (Boolean)this.getVariable("Water"); }

  public void setFans(Boolean state) { try { this.setVariable("Fans", state); } catch(ControllerException e) {} }
  public Boolean getFans() { return (Boolean)this.getVariable("Fans"); }

  public void setThermostat(String state) { try { this.setVariable("Thermostat", state); } catch(ControllerException e) {} }
  public String getThermostat() { return (String)this.getVariable("Thermostat"); }

  public void setEventsFile(String filename) { try { this.setVariable("EventsFile", filename); } catch(ControllerException e) {} }
  public String getEventsFile() { return (String)this.getVariable("EventsFile"); }

  public void setWindowOk(Boolean state) { try { this.setVariable("Window Ok", state); } catch(ControllerException e) {} }
  public Boolean getWindowOk() { return (Boolean)this.getVariable("Window Ok"); }

  public void setPowerOn(Boolean state) { try { this.setVariable("Power On", state); } catch(ControllerException e) {} }
  public Boolean getPowerOn() { return (Boolean)this.getVariable("Power On"); }

  public void setErrorCode(int code) { try { this.setVariable("ErrorCode", code); } catch(ControllerException e) {} }
  public int getErrorCode() {
    Object code = this.getVariable("ErrorCode");
    if(code != null) return (int)code;
    return -1;
  }

  public String toString() {
    String data = "";
    data += "Lights: " + getLights() + "\n";
    data += "Water: " + getWater() + "\n";
    data += "Fans: " + getFans() + "\n";
    data += "Thermostat: " + getThermostat() + "\n";
    data += "Events File: " + getEventsFile() + "\n";
    data += "Window Ok: " + getWindowOk() + "\n";
    data += "Power On: " + getPowerOn() + "\n";
    data += "Error Code: " + getErrorCode() + "\n";
    return data;
  }
}