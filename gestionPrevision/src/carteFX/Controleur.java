package carteFX;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.mail.MessagingException;

import carteFX.densite.Zoom;
import carteFX.facade.FacadeFx;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import mail.Mail;

public class Controleur implements Initializable {

  GestionAffichagePrincipal gestion;

  @FXML
  BorderPane borderPane;

  @FXML
  SplitPane splitPane;

  @FXML
  Button actionPrecedente;

  @FXML
  Button actionSuivante;

  @FXML
  Button deplacer;

  @FXML
  Button selectionner;

  @FXML
  Button dateSuivante;

  @FXML
  Button datePrecedente;

  @FXML
  Label labelDate;

  @FXML
  Button buttonTelecgarger;

  @FXML
  Button editer;

  public Button getEditer() {
    return editer;
  }

  public void setEditer(Button editer) {
    this.editer = editer;
  }

  @FXML
  public void actionBouttonDeplacer() {
    gestion.getCanvas().deplacer = true;
    deplacer.setDisable(true);
    selectionner.setDisable(false);
  }

  @FXML
  public void actionBouttonSelectionner() {
    gestion.getCanvas().deplacer = false;
    deplacer.setDisable(false);
    selectionner.setDisable(true);
  }

  @FXML
  public void actionBouttonZoom() {
    Zoom z = new Zoom(gestion.getCanvas());
    z.zoom(-100);
  }

  @FXML
  public void actionBouttonDeZoom() {
    System.out.println("Action Boutton DeZoom");
    Zoom z = new Zoom(gestion.getCanvas());
    z.zoom(100);
  }

  @FXML
  public void ouvrirFichierGrib() {
    gestion.ouvrirGrib();
    gestion.updateDate(datePrecedente, labelDate, dateSuivante);
  }

  @FXML
  public void sauvegarder() {
    gestion.sauvegarder();
  }

  @FXML
  public void editer() {
    gestion.editer();
    // gestion.updateDate(datePrecedente, labelDate, dateSuivante);
  }

  @FXML
  public void modifier() {
    gestion.modifier();
    gestion.updateAction(actionPrecedente, actionSuivante);
  }

  @FXML
  public void actionPrecedente() {
    gestion.actionPrecedente();
    gestion.updateAction(actionPrecedente, actionSuivante);
  }

  @FXML
  public void actionSuivante() {
    gestion.actionSuivante();
    gestion.updateAction(actionPrecedente, actionSuivante);
  }

  @FXML
  public void parametre() {
    gestion.parametre();
  }

  @FXML
  public void dateSuivante() {
    gestion.dateSuivante();
    gestion.updateDate(datePrecedente, labelDate, dateSuivante);
  }

  @FXML
  public void datePrecedente() {
    gestion.datePrecedente();
    gestion.updateDate(datePrecedente, labelDate, dateSuivante);
  }

  @FXML
  public void actionButtonTelecgarger() {
    this.buttonTelecgarger.setDisable(true);
    System.out.println("actionButtonTelecgarger");
    // ==========================En attente d'une Zone selectionner et renommer
    // actionButtonTelecgarger =========================================
    try {
      Mail mail = new Mail();

      String file = FacadeFx.getInstance().getConfiguration().getDossierSauvegardeGrib();
      String res = mail.getGribFile(file, 0.0, 0.0, 10.0, 3353.0);

      System.out.println(res);
    } catch (MessagingException | IOException | InterruptedException e) {
      e.printStackTrace();
    }

    this.buttonTelecgarger.setDisable(false);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    gestion = new GestionAffichagePrincipal(splitPane);
    gestion.getCanvas().deplacer = true;
    editer.setDisable(true);
  }

}
