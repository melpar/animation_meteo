package vues.configuration;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainVueConfiguration extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader
        .load(getClass().getResource("../../vues/configuration/VueConfiguration.fxml"));
    Scene scene = new Scene(root);
    primaryStage.setTitle("Popu Configuration");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

}