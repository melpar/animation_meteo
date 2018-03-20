package carte;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception { 
      try { 
          MapCanvas canvas = new MapCanvas(1024, 768); 
          Pane pane = new Pane(canvas.getCanvas());
          Scene scene = new Scene(pane);
          primaryStage.setScene(scene);
          primaryStage.show(); 
      } catch (Exception e) { 
              e.printStackTrace(); 
      } 
  }

  public static void main(String[] args) {
      launch(args);
  }

}
