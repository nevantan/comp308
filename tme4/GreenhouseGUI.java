import tme4.*;
import events.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GreenhouseGUI extends Application {
  GreenhouseControls controls;

  public static void main(String[] args) {
    launch(args);
  }

  public void start(Stage primaryStage) {
    this.controls = new GreenhouseControls();
    this.controls.addEvent(new Restart(0, this.controls, "settings.txt", false));
    
    primaryStage.setTitle("Greenhouse Controls");
    Button btn = new Button();
    btn.setText("Hello World!");

    StackPane root = new StackPane();
    root.getChildren().add(btn);
    primaryStage.setScene(new Scene(root, 300, 250));
    primaryStage.show();
  }
}