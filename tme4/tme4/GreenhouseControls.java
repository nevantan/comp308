package tme4;

import events.*;
import fixables.*;
import javafx.scene.control.TextArea;
import java.util.ArrayList;
import java.lang.reflect.Method;

import java.io.*;
import java.nio.file.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

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

  public void setVariable(String key, Object ov, String message) throws ControllerException {
    String value;
    switch(ov.getClass().getSimpleName()) {
      case "Integer":
        value = "" + (int)ov;
        break;
      default:
        value = ov.toString();
    }

    for(int i = 0; i < this.states.size(); i++) {
      Tuple state = this.states.get(i);

      if(state.key.equals(key)) {
        if(!(state.value.getClass().equals(value.getClass()))) throw new ControllerException("Type of value does not match state key provided.");

        this.states.set(i, new Tuple(key, value));
        return;
      }
    }

    states.add(new Tuple(key, value));
  }

  public void setVariable(String key, Object value) throws ControllerException {
    setVariable(key, value, "Setting " + key + "...");
  }

  public Object getVariable(String key) {
    for(Tuple state : this.states) {
      if(state.key.equals(key)) {
        return state.value;
      }
    }

    return null;
  }

  // Convenience getters and setters
  public void setLights(Boolean state) { try { this.setVariable("Lights", state.toString()); } catch(ControllerException e) { e.printStackTrace(); } }
  public Boolean getLights() { return Boolean.parseBoolean((String)this.getVariable("Lights")); }

  public void setWater(Boolean state) { try { this.setVariable("Water", state.toString()); } catch(ControllerException e) { e.printStackTrace(); } }
  public Boolean getWater() { return Boolean.parseBoolean((String)this.getVariable("Water")); }

  public void setFans(Boolean state) { try { this.setVariable("Fans", state.toString()); } catch(ControllerException e) { e.printStackTrace(); } }
  public Boolean getFans() { return Boolean.parseBoolean((String)this.getVariable("Fans")); }

  public void setThermostat(String state) { try { this.setVariable("Thermostat", state); } catch(ControllerException e) { e.printStackTrace(); } }
  public String getThermostat() { return (String)this.getVariable("Thermostat"); }

  public void setEventsFile(String filename) { try { this.setVariable("Events File", filename); } catch(ControllerException e) { e.printStackTrace(); } }
  public String getEventsFile() { return (String)this.getVariable("Events File"); }

  public void setWindowOk(Boolean state) { try { this.setVariable("Window Ok", state.toString()); } catch(ControllerException e) { e.printStackTrace(); } }
  public Boolean getWindowOk() { return Boolean.parseBoolean((String)this.getVariable("Window Ok")); }

  public void setPowerOn(Boolean state) { try { this.setVariable("Power On", state.toString()); } catch(ControllerException e) { e.printStackTrace(); } }
  public Boolean getPowerOn() { return Boolean.parseBoolean((String)this.getVariable("Power On")); }

  public void setErrorCode(int code) { try { this.setVariable("Error Code", "" + code); } catch(ControllerException e) { e.printStackTrace(); } }
  public int getErrorCode() {
    int code = Integer.parseInt((String)this.getVariable("Error Code"));
    return code;
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

      String events = "";
      for(Event e : this.events) {
        if(e.getTime() > 0) {
          events += e.serialize() + "\n";
        }
      }
      Files.write(Paths.get("dump.settings"), events.getBytes(), StandardOpenOption.CREATE);
    } catch(IOException e) {
      e.printStackTrace();
    }

    this.states.clear();
    this.addEvent(new Terminate(0, this));
  }

  public void restore(File dumpfile) {
    try {
      Scanner sc = new Scanner(dumpfile);
      while(sc.hasNextLine()) {
        String line = sc.nextLine();
        if(line.length() <= 0) continue;
        
        Pattern p = Pattern.compile("(.*)=(.*)");
        Matcher m = p.matcher(line);
        String key = "", value = "";
        if(m.find()) {
          key = m.group(1);
          value= m.group(2);
        }

        this.setVariable(key, value, "Restoring " + key + " to " + value);
      }

      Fixable fixable = this.getFixable(this.getErrorCode());
      if(fixable != null)
        fixable.fix(this);

      this.addEvent(new Restart(0, this, "dump.settings", true));
    } catch(FileNotFoundException e) {
      System.out.println("Dump file not found");
    } catch(ControllerException e) {}
  }

  public Fixable getFixable(int error) {
    System.out.println(error);
    this.setErrorCode(0);
    switch(error) {
      case 1:
        return new FixWindow();
      case 2:
        return new PowerOn();
    }

    this.setErrorCode(error);
    return null;
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