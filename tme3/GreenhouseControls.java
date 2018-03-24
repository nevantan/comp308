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
import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Map;
import java.util.HashMap;
import tme3.*;

public class GreenhouseControls extends Controller {
    private boolean light = false;
    private boolean water = false;
    private boolean fans = false;
    private boolean windowok = true;
    private boolean poweron = true;
    private String thermostat = "Day";
    private String eventsFile = "examples1.txt";


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
    }
    
    public class WindowMalfunction extends Event {
    	public WindowMalfunction(long delayTime) {
    		super(delayTime);
    	}
    	public void action() {
    		windowok = false;
    	}
    	public String toString() {
    		return "There is a problem with the greenhouse windows!";
    	}
    }
    
    public class PowerOut extends Event {
    	public PowerOut(long delayTime) {
    		super(delayTime);
    	}
    	public void action() {
    		poweron = false;
    	}
    	public String toString() {
    		return "The power has gone out!";
    	}
    }

    public class Restart extends Event {
    	String eventsFile;
    	
        public Restart(long delayTime, String filename) {
            super(delayTime);
            this.eventsFile = filename;
        }

        public void action() {
			File file = new File(this.eventsFile);
			
			try {
				Scanner sc = new Scanner(file);
				
				while(sc.hasNextLine()) {
					String line = sc.next();
					Pattern p = Pattern.compile("(\\w*)=(\\w*),?");
					Matcher m = p.matcher(line);
					
					Map<String, String> vars = new HashMap<>();
					
					while(m.find()) {
						String key = m.group(1);
						String value = m.group(2);
						
						vars.put(key, value);
					}

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
						case "Terminate":
							addEvent(new Terminate(delayTime));
							break;
					}
				}
				
				sc.close();
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
                gc.addEvent(gc.new Restart(0, filename));
            }

            gc.run();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid number of parameters");
            printUsage();
        }
    }
} ///:~