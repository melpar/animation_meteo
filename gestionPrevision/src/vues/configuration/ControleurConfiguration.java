package vues.configuration;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class ControleurConfiguration {

  @FXML
  Label labelPath;

  @FXML
  public void modifierDossierStockage() {
    DirectoryChooser chooser = new DirectoryChooser();
    File selectedDirectory = chooser.showDialog(new Stage());
    if (selectedDirectory != null) {
      String path = selectedDirectory.getAbsolutePath();
      this.labelPath.setText(path);
    }
  }

  @FXML
  public void keyTyped(KeyEvent k) {
    char car = k.getCharacter().charAt(0);
    if (car < '0' || car > '9') {
      k.consume();
    }
  }

}
