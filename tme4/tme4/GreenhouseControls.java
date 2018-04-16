package tme4;

import events.*;
import javafx.scene.control.TextArea;
import java.util.ArrayList;
import java.lang.reflect.Method;

import java.io.*;
import java.nio.file.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GreenhouseControls extends Controller {
  protected ArrayList<Tuple> states;

  public GreenhouseControls(TextArea logOutput, Object o, Method cb) {
    super(logOutput, o, cb);

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

  public void setVariable(String key, Object value, String message) throws ControllerException {
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

  public void setVariable(String key, Object value) throws ControllerException {
    setVariable(key, value, "Setting " + key + "...");
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

  public void setErrorCode(int code) { try { this.setVariable("Error Code", code); } catch(ControllerException e) {} }
  public int getErrorCode() {
    Object code = this.getVariable("Error Code");
    if(code != null) return (int)code;
    return -1;
  }

  public void shutdown(String message) {
    // Get the current date
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();
    
    // Log error
    this.log(message);

    // Line to print to console and error log
    String errLine = "[" + dateFormat.format(date) + "] ";
    errLine += message;

    // Append to error log
    try(FileWriter fw = new FileWriter("error.log", true);
      BufferedWriter bw = new BufferedWriter(fw);
      PrintWriter out = new PrintWriter(bw)
    ) {
      out.println(errLine);
      System.out.println("Logged:\n" + errLine);
    } catch(IOException e) {
      e.printStackTrace();
    }

    try {
      String dump = "";
      for(Tuple t : this.states) {
        dump += t.key + "=" + t.value + "\n";
      }
      Files.write(Paths.get("dump.out"), dump.getBytes(), StandardOpenOption.CREATE);
    } catch(IOException e) {
      e.printStackTrace();
    }

    this.states.clear();
    this.addEvent(new Terminate(0, this));
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