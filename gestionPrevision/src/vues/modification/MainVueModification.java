package vues.modification;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainVueModification extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("../../vues/modification/VueModification.fxml"));
    Scene scene = new Scene(root);
    primaryStage.setTitle("Popu Modificationsdf");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

}