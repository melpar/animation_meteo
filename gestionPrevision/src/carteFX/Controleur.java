package carteFX;

import java.net.URL;
import java.util.ResourceBundle;

import carteFX.densite.Zoom;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;

public class Controleur implements Initializable {

  GestionAffichagePrincipal gestion;

  @FXML
  BorderPane borderPane;

  @FXML
  SplitPane splitPane;

  @FXML
  Button deplacer;

  @FXML
  Button selectionner;

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
  }

  @FXML
  public void sauvegarder() {
    gestion.sauvegarder();
  }

  @FXML
  public void editer() {
    gestion.editer();
  }

  @FXML
  public void modifier() {
    gestion.modifier();
  }

  @FXML
  public void parametre() {
    gestion.parametre();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    gestion = new GestionAffichagePrincipal(splitPane);
    gestion.getCanvas().deplacer = true;
  }

}
