package carteFX;

import java.net.URL;
import java.util.ResourceBundle;

import carteFX.densite.Zoom;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;

public class Controleur implements Initializable {

  GestionAffichagePrincipal gestion;

  @FXML
  BorderPane borderPane;

  @FXML
  SplitPane splitPane;

  @FXML
  public void actionBouttonDeplacer() {
    System.out.println("Action Boutton Deplacer");
    // gestion.getCanvas().deplacer = !gestion.getCanvas().deplacer;
  }

  @FXML
  public void actionBouttonZoom() {
    System.out.println("Action Boutton Zoom");
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
  public void ouvrirFichierJson() {
    gestion.ouvrirJson();
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
