package carteFX.ressources.modification;

import java.util.Calendar;

import carteFX.facade.FacadeFx;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import previsionVents.FacadePrevisionVents;
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

  // initialise les champs text
  @FXML
  public void initialize() {
    this.choix.setPromptText(" ");
    this.choix.getItems().add("Contraste lineaire");
    this.choix.getItems().add("Contraste progressif");
    this.choix.getItems().add("Coefficient");
    this.choix.setValue("Contraste lineaire");
    coeff.setVisible(true);
    seuil.setVisible(true);
  }

  // fonction lors du clique sur le bouton ajouter
  @FXML
  public void actionButton() {
    // recupere les donnees de la zone selectione
    // ZonePrevision zone = FacadeFx.getInstance().getZone();
    ZonePrevision zone = FacadePrevisionVents.getFacadePrevisionVents().getPrevisions()
        .getZonePrevision();
    if (zone != null) {
      if (verif()) {
        if (this.choix.getValue().equals("Contraste lineaire")) {
          // recupere la date courante de l'interface
          Calendar date = FacadeFx.getInstance().getDate();
          Float coefficient = Float.parseFloat(this.champCoeff.getText());
          Float seuil = Float.parseFloat(this.champSeuil.getText());
          FacadeFx.getInstance().getModifier().modifierContrasteLineaireVent(zone, date,
              coefficient, seuil);
          closeButtonAction();
        }
        if (this.choix.getValue().equals("Contraste progressif")) {
          // recupere la date courante de l'interface
          Calendar date = FacadeFx.getInstance().getDate();
          Float coefficient = Float.parseFloat(this.champCoeff.getText());
          Float seuil = Float.parseFloat(this.champSeuil.getText());
          FacadeFx.getInstance().getModifier().modifierContrasteProgressifVent(zone, date,
              coefficient, seuil);
          closeButtonAction();
        }
        if (this.choix.getValue().equals("Coefficient")) {
          // recupere la date courante de l'interface
          Calendar date = FacadeFx.getInstance().getDate();
          Float coefficient = Float.parseFloat(this.champCoeff.getText());
          FacadeFx.getInstance().getModifier().modifierCoefficientVent(zone, date, coefficient);
          closeButtonAction();
        }

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
        try {
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
        } catch (NumberFormatException e) {
          champErreur.setText("Saisissez des nombres valide\n");
          valide = false;
        }
      }
      // si valider lancement de la fonction modifierCoefficientVent de la
      // facade
      if (valide)
        champErreur.setText("Saisie valider");
    }

    // verif choix Contraste progressif
    if (this.choix.getValue().equals("Contraste progressif"))

    {
      if (champCoeff.getText().isEmpty() || champSeuil.getText().isEmpty()) {
        champErreur.setText("Un ou plusieurs champ sont vide\n");
        valide = false;
      } else {
        try {
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
        } catch (NumberFormatException e) {
          champErreur.setText("Saisissez des nombres valide\n");
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
        try {
          float resCoeff = Float.parseFloat(champCoeff.getText());
          if (resCoeff > 1 || resCoeff < -1) {
            champErreur.setText("Le Coefficient doit etre compris entre -1 et 1\n");
            valide = false;
          }
        } catch (NumberFormatException e) {
          champErreur.setText("Saisissez un nombre valide\n");
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
