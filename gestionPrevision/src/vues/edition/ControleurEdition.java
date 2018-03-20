package vues.edition;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ControleurEdition {
  
  @FXML
  TextField puissance;
  
  @FXML
  TextField direction;
  
  @FXML
  TextField durree;
  
  @FXML
  TextField marquePneuAvant;
  
  
  @FXML
  public void actionBoutonAjouter() {
    System.out.println("actionBoutonAjouter");
  }
  
  @FXML
  public void actionBoutonCreer() {
    System.out.println("actionBoutonCreer");
  }
  
  @FXML
  public void keyTyped(KeyEvent k) {
    char car = k.getCharacter().charAt(0);
    if (car < '0' || car > '9') {
      k.consume();
    }
  }

}
