package fixables;

import tme4.Fixable;
import tme4.GreenhouseControls;
import tme4.ControllerException;

import java.io.*;
import java.nio.file.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class PowerOn implements Fixable {
  public void fix(GreenhouseControls controls) {
    controls.setPowerOn(true);
  }

  public void log() {
    // Get the current date
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();

    // Line to print to console and error log
    String errLine = "[" + dateFormat.format(date) + "] ";
    errLine += "Power has been restored.";

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
  }
}