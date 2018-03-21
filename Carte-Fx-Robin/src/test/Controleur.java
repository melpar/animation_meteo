package test;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class Controleur implements Initializable {
	
	MapCanvas canvas;

	@FXML
	BorderPane borderPane;
	
	@FXML
	public void actionBouttonDeplacer() {
		System.out.println("Action Boutton Deplacer");
		this.canvas.deplacer = !this.canvas.deplacer;
	}
	
	@FXML
	public void actionBouttonZoom() {
		System.out.println("Action Boutton Zoom");
		this.canvas.zoom(-100);
	}
	
	@FXML
	public void actionBouttonDeZoom() {
		System.out.println("Action Boutton DeZoom");
		this.canvas.zoom(100);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.canvas = new MapCanvas(1024, 768);
		Pane pane = new Pane(canvas.getCanvas());
		borderPane.setCenter(pane);
	}

}
