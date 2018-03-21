package vues.modification;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ControleurModification {

  @FXML
  ComboBox<String> choix;

  @FXML
  HBox coeff;

  @FXML
  HBox seuil;

  @FXML
  TextField champCoeff;

  @FXML
  TextField champSeuil;

  @FXML
  Text champErreur;

  @FXML
  public void initialize() {
    this.choix.setPromptText(" ");
    this.choix.getItems().add("Contraste lineaire");
    this.choix.getItems().add("Contraste progressif");
    this.choix.getItems().add("Coefficient");
    // champErreur.setText(" ");
  }

  @FXML
  public void actionButton() {
    if (verif())
      ; // si valider lancer la fonction modifierCoefficientVent de la facade
  }

  public void init() {
    this.champCoeff.clear();
    this.champSeuil.clear();
    this.champErreur.setText("");
  }

  public boolean verif() {
    boolean valide = true;
    champErreur.setText("");
    if (this.choix.getValue().equals("Contraste lineaire")) {
      if (champCoeff.getText().isEmpty() || champSeuil.getText().isEmpty()) {
        champErreur.setText("Un ou plusieurs champ sont vide\n");
        valide = false;
      } else {
        float resCoeff = Float.parseFloat(champCoeff.getText());
        float resSeuil = Float.parseFloat(champSeuil.getText());
        if (resCoeff > 1 || resCoeff < -1) {
          champErreur.setText("Le Coefficient doit etre compris entre -1 et 1\n");
          valide = false;
        }
        if (resSeuil > 400 || resSeuil < 0) {
          String erreur = champErreur.getText();
          champErreur.setText(erreur + "Le seuil doit etre compris entre 0 et 400\n");
          valide = false;
        }
        // si valider lancer la fonction modifierCoefficientVent de la facade
        if (valide)
          champErreur.setText("Saisie valider");
      }

    }

    if (this.choix.getValue().equals("Contraste progressif")) {
      if (champCoeff.getText().isEmpty() || champSeuil.getText().isEmpty()) {
        champErreur.setText("Un ou plusieurs champ sont vide\n");
        valide = false;
      } else {
        float resCoeff = Float.parseFloat(champCoeff.getText());
        float resSeuil = Float.parseFloat(champSeuil.getText());
        if (resCoeff > 1 || resCoeff < -1) {
          champErreur.setText("Le Coefficient doit etre compris entre -1 et 1\n");
          valide = false;
        }
        if (resSeuil > 400 || resSeuil < 0) {
          String erreur = champErreur.getText();
          champErreur.setText(erreur + "Le seuil doit etre compris entre 0 et 400\n");
          valide = false;
        }
        if (valide)
          champErreur.setText("Saisie valider");
      }
    }
    if (this.choix.getValue().equals("Coefficient")) {
      if (champCoeff.getText().isEmpty()) {
        champErreur.setText("Le champ coeff est vide\n");
        valide = false;
      } else {
        float resCoeff = Float.parseFloat(champCoeff.getText());
        if (resCoeff > 1 || resCoeff < -1) {
          champErreur.setText("Le Coefficient doit etre compris entre -1 et 1\n");
          valide = false;
        }
        // si valider lancer la fonction modifierCoefficientVent de la facade
        if (valide)
          champErreur.setText("Saisie valider");
      }
    }
    return valide;
  }

  @FXML
  public void choix() {
    init();
    if (this.choix.getValue().equals("Contraste lineaire")) {
      coeff.setVisible(true);
      seuil.setVisible(true);
    }
    if (this.choix.getValue().equals("Contraste progressif")) {
      coeff.setVisible(true);
      seuil.setVisible(true);
    }
    if (this.choix.getValue().equals("Coefficient")) {
      coeff.setVisible(true);
      seuil.setVisible(false);
    }
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

}
