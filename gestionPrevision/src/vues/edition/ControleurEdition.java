package vues.edition;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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

}
