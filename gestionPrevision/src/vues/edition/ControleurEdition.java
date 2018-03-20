package vues.edition;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ControleurEdition {

  @FXML
  TextField puissanceVal;

  @FXML
  TextField directionVal;

  @FXML
  TextField durree;

  @FXML
  DatePicker datePrevision;

  @FXML
  ComboBox<String> heuresVal;

  @FXML
  ComboBox<String> minutesVal;

  @FXML
  public void initialize() {
    for (int i = 0; i < 24; i++) {
      heuresVal.getItems().add(i + "");
    }
    for (int i = 0; i < 60; i++) {
      minutesVal.getItems().add(i + "");
    }

  }

  @FXML
  public void actionBoutonAjouter() {

    try {
      String chaine = directionVal.getText();
      if (!chaine.equals("")) {
        puissanceVal.setStyle("-fx-background-color: rgb(153, 255, 153);");
        Float.parseFloat(puissanceVal.getText());
      }

    } catch (NumberFormatException e) {
      puissanceVal.setStyle("-fx-background-color: rgb(255, 80, 80);");
    }

    try {
      String chaine = directionVal.getText();
      if (!chaine.equals("")) {
        directionVal.setStyle("-fx-background-color: rgb(153, 255, 153);");
        float p = (Float.parseFloat(directionVal.getText()) % 360);
        directionVal.setText("" + p);
      }
    } catch (NumberFormatException e) {
      directionVal.setStyle("-fx-background-color: rgb(255, 80, 80);");
    }

    System.out.println("ok");
  }

  @FXML
  public void actionBoutonFinaliser() {

  }

  @FXML
  public void keyTyped(KeyEvent k) {
    System.out.println("test a");
    if (k.getCharacter().equals("")) {
      return;
    }

    char car = k.getCharacter().charAt(0);
    if ((car >= 'a' && car <= 'z') || (car >= 'A' && car <= 'Z')) {
      k.consume();
    }
  }

}
