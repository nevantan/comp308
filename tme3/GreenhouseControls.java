/*
 * File: GreenhouseControls.java
 * Modified By: Nevan Tan <nevan@tanclan.ca>
 * StudentID: 3099925
 * Date Modified: 2018-03-23
 * 
 * Compile:
 * 	javac GreenhouseControls.java
 * 
 * Run:
 * 	java GreenhouseControls -f [settings_file]
 * 
 * Original docblock:
 */

//: innerclasses/GreenhouseControls.java
// This produces a specific application of the
// control system, all in a single class. Inner
// classes allow you to encapsulate different
// functionality for each type of event.
// From 'Thinking in Java, 4th ed.' (c) Bruce Eckel 2005
// www.BruceEckel.com. See copyright notice in CopyRight.txt.

/***********************************************************************
 * Adapated for COMP308 Java for Programmer, 
 *		SCIS, Athabasca University
 *
 * Assignment: TME3
 * @author: Steve Leung
 * @date  : Oct 21, 2005
 *
 */

import java.io.*;
import java.nio.file.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import tme3.*;

public class GreenhouseControls extends Controller {
    private boolean light = false;
    private boolean water = false;
    private boolean fans = false;
    private String thermostat = "Day";
    private String eventsFile = "examples1.txt";
    
    private boolean windowok = true;
    private boolean poweron = true;
    private int errorcode = 0;


    public class LightOn extends Event {
        public LightOn(long delayTime) {
            super(delayTime);
        }
        public void action() {
            // Put hardware control code here to
            // physically turn on the light.
            light = true;
        }
        public String toString() {
            return "Light is on";
        }
    }

    public class LightOff extends Event {
        public LightOff(long delayTime) {
            super(delayTime);
        }
        public void action() {
            // Put hardware control code here to
            // physically turn off the light.
            light = false;
        }
        public String toString() {
            return "Light is off";
        }
    }

    public class WaterOn extends Event {
        public WaterOn(long delayTime) {
            super(delayTime);
        }
        public void action() {
            // Put hardware control code here.
            water = true;
        }
        public String toString() {
            return "Greenhouse water is on";
        }
    }

    public class WaterOff extends Event {
        public WaterOff(long delayTime) {
            super(delayTime);
        }
        public void action() {
            // Put hardware control code here.
            water = false;
        }
        public String toString() {
            return "Greenhouse water is off";
        }
    }

    public class FansOn extends Event {
        public FansOn(long delayTime) {
            super(delayTime);
        }
        public void action() {
            fans = true;
        }
        public String toString() {
            return "Greenhouse fans are on";
        }
    }

    public class FansOff extends Event {
        public FansOff(long delayTime) {
            super(delayTime);
        }
        public void action() {
            fans = true;
        }
        public String toString() {
            return "Greenhouse fans are off";
        }
    }

    public class ThermostatNight extends Event {
        public ThermostatNight(long delayTime) {
            super(delayTime);
        }
        public void action() {
            // Put hardware control code here.
            thermostat = "Night";
        }
        public String toString() {
            return "Thermostat on night setting";
        }
    }

    public class ThermostatDay extends Event {
        public ThermostatDay(long delayTime) {
            super(delayTime);
        }
        public void action() {
            // Put hardware control code here.
            thermostat = "Day";
        }
        public String toString() {
            return "Thermostat on day setting";
        }
    }

    // An example of an action() that inserts a
    // new one of itself into the event list:
    public class Bell extends Event {
    	private int rings;
    	private int rung;
    	
        public Bell(long delayTime, int rings, int rung) {
            super(delayTime);
            this.rings = rings;
            this.rung = rung;
        }
        public void action() {
			this.rung++;
			if(this.rung < this.rings) {
				addEvent(new Bell(this.delayTime, this.rings, this.rung));
			}
        }
        public String toString() {
            return "Bing!";
        }
        public String toString(Boolean serialize) {
        	return "Event=Bell,time=" + this.delayTime + ",rings=" + this.rings + ",rung=" + this.rung;
        }
    }
    
    public class WindowMalfunction extends Event {
    	public WindowMalfunction(long delayTime) {
    		super(delayTime);
    	}
    	public void action() throws ControllerException {
    		windowok = false;
    		errorcode = 1;
    		eventList.remove(this);
    		throw new ControllerException(this.toString());
    	}
    	public String toString() {
    		return "There is a problem with the greenhouse windows!";
    	}
    }
    
    public class FixWindow implements Fixable {
    	public void fix() {
    		windowok = true;
    	}
    	public void log() {
    		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
		
    		String fixLine = "[" + dateFormat.format(date) + "] ";
    		fixLine += "The greenhouse windows have been fixed";
    		
    		try(FileWriter fw = new FileWriter("fix.log", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)
			) {
				out.println(fixLine);
				System.out.println("Logged:\n" + fixLine);
			} catch(IOException e) {
				e.printStackTrace();
			}
    	}
    }
    
    public class PowerOut extends Event {
    	public PowerOut(long delayTime) {
    		super(delayTime);
    	}
    	public void action() throws ControllerException {
    		poweron = false;
    		errorcode = 2;
    		eventList.remove(this);
    		throw new ControllerException(this.toString());
    	}
    	public String toString() {
    		return "The power has gone out!";
    	}
    }
    
    public class PowerOn implements Fixable {
    	public void fix() {
    		poweron = true;
    	}
    	public void log() {
    		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
    		
    		String fixLine = "[" + dateFormat.format(date) + "] ";
    		fixLine += "The greenhouse power has been restored";
    		
    		try(FileWriter fw = new FileWriter("fix.log", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)
			) {
				out.println(fixLine);
				System.out.println("Logged:\n" + fixLine);
			} catch(IOException e) {
				e.printStackTrace();
			}
    	}
    }

    public class Restart extends Event {
    	private Boolean temp;
    	
        public Restart(long delayTime, String filename, Boolean temp) {
            super(delayTime);
            eventsFile = filename;
            this.temp = temp;
        }

        public void action() {
        	// Open provided events file
			File file = new File(eventsFile);
			
			try {
				Scanner sc = new Scanner(file);
				
				// For each line...
				while(sc.hasNextLine()) {
					String line = sc.nextLine();
					
					// Look for the pattern [key]=[value]
					Pattern p = Pattern.compile("(\\w*)=(\\w*),?");
					Matcher m = p.matcher(line);
					
					Map<String, String> vars = new HashMap<>();
					
					// For each key/value pair in the line...
					while(m.find()) {
						String key = m.group(1);
						String value = m.group(2);
						
						// Store pair in map
						vars.put(key, value);
					}

					// Deal with each event type. Not really necessary for this exercise,
					// but allows for expansion of there are other events that have specific
					// setup like Bell.
					long delayTime = Long.parseLong(vars.get("time"));					
					switch(vars.get("Event")) {
						case "LightOn":
							addEvent(new LightOn(delayTime));
							break;
						case "LightOff":
							addEvent(new LightOff(delayTime));
							break;
						case "WaterOn":
							addEvent(new WaterOn(delayTime));
							break;
						case "WaterOff":
							addEvent(new WaterOff(delayTime));
							break;
						case "FansOn":
							addEvent(new FansOn(delayTime));
							break;
						case "FansOff":
							addEvent(new FansOff(delayTime));
							break;
						case "ThermostatNight":
							addEvent(new ThermostatNight(delayTime));
							break;
						case "ThermostatDay":
							addEvent(new ThermostatDay(delayTime));
							break;
						case "Bell":
							int rings = 0;
							if(vars.containsKey("rings")) rings = Integer.parseInt(vars.get("rings"));
							addEvent(new Bell(delayTime, rings, 0));
							break;
						case "WindowMalfunction":
							addEvent(new WindowMalfunction(delayTime));
							break;
						case "PowerOut":
							addEvent(new PowerOut(delayTime));
							break;
						case "Terminate":
							addEvent(new Terminate(delayTime));
							break;
					}
				}
				
				sc.close();
				
				// If the settings file was marked as temp, delete it
				if(this.temp) {
					file.delete();
				}
			} catch(FileNotFoundException e) {
				System.out.println("Settings file not found!");
			}
        }

        public String toString() {
            return "Restarting system";
        }
    }

    public class Terminate extends Event {
        public Terminate(long delayTime) {
            super(delayTime);
        }
        public void action() {
            System.exit(0);
        }
        public String toString() {
            return "Terminating";
        }
    }
    
    public class Restore {
    	// Store events list to write to file
    	private String events = "";
    	
    	public Restore(String dumpFile) {
    		// Open dump file
			File file = new File(dumpFile);
			String section = "";
			
			try {
				Scanner sc = new Scanner(file);

				// For each line...				
				while(sc.hasNextLine()) {
					String line = sc.nextLine();
					
					if(line.length() <= 0) continue; // Ignore blank lines
					else if(line.charAt(0) == '[') section = line; // If section line, remember it
					else parseLine(section, line); // Parse line based on section
				}
				
				// Once all lines have been parsed, restart the GreenhouseControls
				apply();
				
				sc.close();
			} catch(FileNotFoundException e) {
				System.out.println("Dump file not found!");
			}
    	}
    	
    	private void parseLine(String section, String line) {
    		// Grab the key/value pair from the dump line
    		Pattern p = Pattern.compile("(.*)\\:(.*)");
			Matcher m = p.matcher(line);
    		
    		String key = "", value = "";
    		
    		if(m.find()) {
				key = m.group(1);
				value = m.group(2);
			}

    		// Parse the line based on the section
    		switch(section) {
    			case "[STATES]":
    				parseStateLine(key, value);
    				break;
    			case "[ERROR CONDITIONS]":
    				parseErrorLine(key, value);
    				break;
    			case "[EVENTS]":
    				parseEventLine(line);
    				break;
    		}
    	}
    	
    	private void parseStateLine(String key, String value) {
    		// Set states
			switch(key) {
				case "Lights":
					light = Boolean.valueOf(value);
					break;
				case "Water":
					water = Boolean.valueOf(value);
					break;
				case "Fans":
					fans = Boolean.valueOf(value);
					break;
				case "Thermostat":
					thermostat = value;
					break;
				case "Settings File":
					eventsFile = value;
					break;
			}
    	}
    	
    	private void parseErrorLine(String key, String value) {
    		// Set error states
			switch(key) {
				case "Window":
					windowok = Boolean.valueOf(value);
					break;
				case "Power":
					poweron = Boolean.valueOf(value);
					break;
				case "Errorcode":
					errorcode = Integer.parseInt(value);
					break;
			}  		
    	}
    	
    	private void parseEventLine(String line) {
    		// Add event to String to be written to file
    		this.events += line + '\n';
    	}
    	
    	private void apply() {
    		// Write a temp settings file and restart the system
    		try {
				Files.write(Paths.get("restart.temp"), this.events.getBytes(), StandardOpenOption.CREATE);
			} catch(IOException e) {
				e.printStackTrace();
			}
			addEvent(new Restart(0, "restart.temp", true));
    	}
    }
    
    public int getError() {
    	return errorcode;
    }
    
    public Fixable getFixable(int error) {
    	errorcode = 0;
    	
    	switch(error) {
    		case 1:
    			return new FixWindow();
    		case 2:
    			return new PowerOn();
    	}
    	
    	return null;
    }

	public void shutdown(String message) {
		// Get the current date
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		
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

		// Serialize GreenhouseControls		
		String dump = toString(eventList);
		
		// Dump state to file
		try {
			Files.write(Paths.get("dump.out"), dump.getBytes(), StandardOpenOption.CREATE);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		// Clear the events list and stop the system
		eventList.clear();
		addEvent(new Terminate(0));
	}

	public String toString(List<Event> eventList) {
		// Serialize state
		String data = "[STATES]\n";
		data += "Lights:" + light + "\n";
		data += "Water:" + water + "\n";
		data += "Fans:" + fans + "\n";
		data += "Thermostat:" + thermostat + "\n";
		data += "Settings File:" + eventsFile + "\n\n";
		data += "[ERROR CONDITIONS]\n";
		data += "Window:" + windowok + "\n";
		data += "Power:" + poweron + "\n";
		data += "Errorcode:" + errorcode + "\n\n";
		data += "[EVENTS]\n";
		
		// Serialize event list
		for(Event e : eventList) {
			data += e.toString(true);
		}
		
		return data;
	}

    public static void printUsage() {
        System.out.println("Correct format: ");
        System.out.println("  java GreenhouseControls -f <filename>, or");
        System.out.println("  java GreenhouseControls -d dump.out");
    }

    //---------------------------------------------------------

    public static void main(String[] args) {
        try {
            String option = args[0];
            String filename = args[1];

            if (!(option.equals("-f")) && !(option.equals("-d"))) {
                System.out.println("Invalid option");
                printUsage();
            }

            GreenhouseControls gc = new GreenhouseControls();

            if (option.equals("-f")) {
                gc.addEvent(gc.new Restart(0, filename, false));
            } else if(option.equals("-d")) {
            	// Start from restore point
            	GreenhouseControls.Restore restore = gc.new Restore(filename);
            }

            gc.run();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid number of parameters");
            printUsage();
        }
    }
} ///:~