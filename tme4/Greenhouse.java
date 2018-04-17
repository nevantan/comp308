import tme4.*;
import events.*;

import java.io.File;
import java.lang.reflect.Method;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.stage.Stage;
import javafx.stage.FileChooser;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

public class Greenhouse extends Application {
  GreenhouseControls controls;

  private Stage stage;

  private static String[] arguments;

  @FXML
  private TextArea logArea;
  private ContextMenu contextMenu;

  public static void main(String[] args) {
    arguments = args;
    launch(args);
  }

  public void start(Stage stage) throws Exception {
    this.stage = stage;

    FXMLLoader loader = new FXMLLoader(getClass().getResource("layout.fxml"));
    loader.setController(this);
    Parent root = loader.load();

    // Setup control system
    try {
        Method handleEventComplete = Greenhouse.class.getMethod("handleEventComplete", new Class[] { Event.class });
        this.controls = new GreenhouseControls(logArea, this, handleEventComplete);
    } catch(Exception e) {
        e.printStackTrace();
    }

    Scene scene = new Scene(root, 640, 313);

    stage.setTitle("COMP308 Assignment 4");
    stage.setScene(scene);
    stage.show();
  }

  // GUI Handling Code

  // Menu Bar
  @FXML
  private MenuItem menuNewWindow;

  @FXML
  private MenuItem menuCloseWindow;

  @FXML
  private MenuItem menuOpenEventsFile;

  @FXML
  private MenuItem menuOpenDumpFile;

  @FXML
  private MenuItem menuExit;

  @FXML
  private TextField eventsFilePathTextField;

  @FXML
  void handleNewWindowAction(ActionEvent event) {
    try {
      Process p = Runtime.getRuntime().exec("java Greenhouse");
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  void handleCloseWindowAction(ActionEvent event) {
    stage.close();
  }

  @FXML
  void handleOpenEventsFileAction(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select Events File");
    File file = fileChooser.showOpenDialog(stage);
    
    if(file != null) {
        eventsFilePathTextField.setText(file.getAbsolutePath());
        startButton.setDisable(false);
    }
  }

  @FXML
  void handleOpenDumpFileAction(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select Events File");
    File file = fileChooser.showOpenDialog(stage);
    
    if(file != null) {
        this.controls.restore(file);
        //startButton.setDisable(false);
    }
  }

  @FXML
  void handleExitAction(ActionEvent event) {
    Platform.exit();
    System.exit(0);
  }

  @FXML
  private Button startButton;
  @FXML
  private Button restartButton;
  @FXML
  void handleStartButtonAction(ActionEvent event) {
    this.controls.addEvent(new Restart(0, this.controls, eventsFilePathTextField.getText(), false));

    startButton.setDisable(true);
    restartButton.setDisable(true);
    suspendButton.setDisable(false);
    terminateButton.setDisable(false);
  }

  @FXML
  private Button terminateButton;
  @FXML
  void handleTerminateButtonAction(ActionEvent event) {
    this.controls.addEvent(new Terminate(0, this.controls));

    startButton.setDisable(false);
    restartButton.setDisable(false);
    suspendButton.setDisable(false);
    resumeButton.setDisable(true);
    terminateButton.setDisable(true);
  }

  @FXML
  private Button suspendButton;
  @FXML
  void handleSuspendButtonAction(ActionEvent event) {
    this.controls.running(false);
    resumeButton.setDisable(false);
    suspendButton.setDisable(true);
  }

  @FXML
  private Button resumeButton;
  @FXML
  void handleResumeButtonAction(ActionEvent event) {
    this.controls.running(true);
    resumeButton.setDisable(true);
    suspendButton.setDisable(false);
  }

  public void handleEventComplete(Event e) {
    this.controls.removeEvent(e);
    //System.out.println("Callback: " + e.serialize());
  }

  @Override
  public void stop() {
    Platform.exit();
    System.exit(0);
  }
}