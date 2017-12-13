package edition.mains;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainVueEdition extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../edition/vue/vueEdition.fxml"));
	    Scene scene = new Scene(root);
	    primaryStage.setTitle("Parc Automobile");
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
