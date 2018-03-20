package vues.modification;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ControleurModification implements Initializable{
  
  @FXML 
  TextField fieldContraste;
  
  @FXML 
  TextField fieldCoeffissiant;
  
  @FXML
  RadioButton buttonLineaire;
  
  @FXML
  RadioButton buttonProgressif;
  
  @FXML
  RadioButton buttonCoeffissiant;

  
  @FXML
  public void actionFieldContraste() {
    this.fieldCoeffissiant.setDisable(true);
    this.fieldContraste.setDisable(false);
  }
  
  @FXML
  public void actionFieldCoeffissiant() {
    this.fieldCoeffissiant.setDisable(false);
    this.fieldContraste.setDisable(true);
    
  }
  
  @FXML
  public void keyTyped(KeyEvent k) {
    if (k.getCharacter().equals("")) {
      return;
    }

    char car = k.getCharacter().charAt(0);
    if ((car >= 'a' && car <= 'z') || (car >= 'A' && car <= 'Z')) {
      k.consume();
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.buttonLineaire.setSelected(true);
    this.actionFieldContraste();
  }

}
