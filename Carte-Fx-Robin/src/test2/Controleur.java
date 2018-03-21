package test2;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import main.java.tutorial2.MapCanvas;

public class Controleur implements Initializable {

	@FXML
	BorderPane borderPane;
	
	@FXML
	Canvas canvas;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		MapCanvas canvas = new MapCanvas(1024, 768);
		this.canvas = canvas.canvas;
	}

}
