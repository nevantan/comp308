package events;

import tme4.*;
import java.io.*;
import java.nio.file.*;
import java.util.Scanner;
import java.util.regex.*;
import java.util.HashMap;

public class Restart extends Event {
  private Boolean temp;
  private String eventsFile;

  public Restart(long delayTime, Controller controller, String filename, Boolean temp) {
    super(delayTime, controller);
    this.eventsFile = filename;
    this.temp = temp;
  }

  public void action() {
    synchronized(this.controller) {
      this.controller.running(false);

      File file = new File(this.eventsFile);

      try {
        Scanner sc = new Scanner(file);

        while(sc.hasNextLine()) {
          String line = sc.nextLine();

          Pattern p = Pattern.compile("(\\w*)=(\\w*),?");
          Matcher m = p.matcher(line);

          HashMap<String, String> vars = new HashMap<>();

          while(m.find()) {
            String key = m.group(1);
            String value = m.group(2);

            vars.put(key, value);
          }

          long delayTime = Long.parseLong(vars.get("delay"));
          controller.addEvent(vars.get("event"), delayTime);
        }

        sc.close();

        if(this.temp) {
          file.delete();
        }
      } catch(FileNotFoundException e) {
        System.out.println("Settings file not found!");
      } finally {
        this.controller.running(true);
      }
    }
  }

  public String toString() {
    return "Restarting system...";
  }
}