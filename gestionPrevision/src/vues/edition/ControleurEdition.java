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
  TextField dureeVal;

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

    directionVal.setPromptText("° rad");
    puissanceVal.setPromptText("km/h");
    dureeVal.setPromptText("heures");
  }

  @FXML
  public void actionBoutonAjouter() {
    String chaine;
    try {
      chaine = puissanceVal.getText();
      System.out.println("ok :" + chaine);
      if (!chaine.equals("")) {
        puissanceVal.setStyle("-fx-background-color: rgb(153, 255, 153);");
        float p = Float.parseFloat(chaine);
        puissanceVal.setText("" + p);
      } else {
        puissanceVal.setStyle("-fx-background-color: rgb(255, 80, 80);");
      }

    } catch (NumberFormatException e) {
      puissanceVal.setStyle("-fx-background-color: rgb(255, 80, 80);");
    }

    try {
      chaine = directionVal.getText();
      if (!chaine.equals("")) {
        directionVal.setStyle("-fx-background-color: rgb(153, 255, 153);");
        float p = (Float.parseFloat(chaine) % 360);
        directionVal.setText("" + p);
      } else {
        directionVal.setStyle("-fx-background-color: rgb(255, 80, 80);");
      }
    } catch (NumberFormatException e) {
      directionVal.setStyle("-fx-background-color: rgb(255, 80, 80);");
    }

    try {
      chaine = dureeVal.getText();
      System.out.println("duree:" + chaine);
      if (!chaine.equals("")) {
        dureeVal.setStyle("-fx-background-color: rgb(153, 255, 153);");
        float p = (Integer.parseInt(chaine));
        dureeVal.setText("" + p);
      }
    } catch (NumberFormatException e) {
      dureeVal.setStyle("-fx-background-color: rgb(255, 80, 80);");
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
