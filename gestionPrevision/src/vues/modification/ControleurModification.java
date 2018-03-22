package vues.modification;

import java.util.Calendar;

import carteFX.facade.FacadeFx;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import previsionVents.ZonePrevision;

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
  Button closeButton;

  @FXML
  DatePicker date;

  // initialise les champs text
  @FXML
  public void initialize() {
    this.choix.setPromptText(" ");
    this.choix.getItems().add("Contraste lineaire");
    this.choix.getItems().add("Contraste progressif");
    this.choix.getItems().add("Coefficient");
  }

  public void functionTest() {
    if (verif()) {

      Calendar date = Calendar.getInstance();
      date.set(this.date.getValue().getYear(), this.date.getValue().getMonthValue() - 1,
          this.date.getValue().getDayOfMonth());
      System.out.println(date.getTime());

    }
  }

  // foncion lors du clique sur le bouton ajouter
  // manque verif pour la date
  @FXML
  public void actionButton() {
    // si valider lancer la fonction modifierCoefficientVent de la facade
    ZonePrevision zone = FacadeFx.getInstance().getZone();
    if (zone != null) {
      if (verif()) {
        System.out.println(this.date.getValue().getYear() + " "
            + this.date.getValue().getMonthValue() + " " + this.date.getValue().getDayOfMonth());
        Calendar date = Calendar.getInstance();
        date.set(this.date.getValue().getYear(), this.date.getValue().getMonthValue(),
            this.date.getValue().getDayOfMonth());

        Float coefficient = Float.parseFloat(this.champCoeff.getText());
        FacadeFx.getInstance().getModifier().modifierCoefficientVent(zone, date, coefficient);
        closeButtonAction();
      }
    }
  }

  public void init() {
    this.champCoeff.clear();
    this.champSeuil.clear();
    this.champErreur.setText("");
  }

  // fonction qui verifie que les champs seuil et coefficient sont correct
  public boolean verif() {
    boolean valide = true;
    champErreur.setText("");
    // verif choix Contraste lineaire
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
        try {
          if (date.getValue().equals("")) {

          }
        } catch (Exception e) {
          String erreur = champErreur.getText();
          champErreur.setText(erreur + "Le champs date doit etre complete");
          valide = false;
        }

        // si valider lancer la fonction modifierCoefficientVent de la facade
        if (valide)
          champErreur.setText("Saisie valider");
      }

    }
    // verif choix Contraste progressif
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
        try {
          if (date.getValue().equals("")) {

          }
        } catch (Exception e) {
          String erreur = champErreur.getText();
          champErreur.setText(erreur + "Le champs date doit etre complete");
          valide = false;
        }
        if (valide)
          champErreur.setText("Saisie valider");
      }
    }
    // verif choix coeff
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
        try {
          if (date.getValue().equals("")) {

          }
        } catch (Exception e) {
          String erreur = champErreur.getText();
          champErreur.setText(erreur + "Le champs date doit etre complete");
          valide = false;
        }
      }
      // si tout est correct
      if (valide)
        champErreur.setText("Saisie valider");
    }
    return valide;

  }

  // fonction qui affiche les champs de saisie.
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
  private void closeButtonAction() {
    // get a handle to the stage
    Stage stage = (Stage) closeButton.getScene().getWindow();
    // do what you have to do
    stage.close();
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
