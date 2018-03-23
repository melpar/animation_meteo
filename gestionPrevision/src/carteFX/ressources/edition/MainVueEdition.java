package carteFX.ressources.edition;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainVueEdition extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("../../vues/edition/VueEdition.fxml"));
    Scene scene = new Scene(root);
    primaryStage.setTitle("Edition prévision");
    primaryStage.setScene(scene);
    primaryStage.resizableProperty().setValue(false);
    primaryStage.show();
  }

}