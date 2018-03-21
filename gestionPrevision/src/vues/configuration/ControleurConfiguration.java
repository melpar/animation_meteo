package vues.configuration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import carteFX.facade.FacadeFx;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class ControleurConfiguration implements Initializable {

  private FacadeFx facade;

  @FXML
  Label labelPath;

  @FXML
  ComboBox<String> comboBoxUnite;

  @FXML
  ComboBox<String> comboBoxRepresentation;

  @FXML
  ComboBox<String> comboBoxConserver;

  @FXML
  Button buttonAppliquer;

  @FXML
  Button buttonAnnuler;

  @FXML
  public void modifierDossierStockage() {
    DirectoryChooser chooser = new DirectoryChooser();
    File selectedDirectory = chooser.showDialog(new Stage());
    if (selectedDirectory != null) {
      String path = selectedDirectory.getAbsolutePath();
      this.labelPath.setText(path);
    }
  }

  @FXML
  public void keyTyped(KeyEvent k) {
    char car = k.getCharacter().charAt(0);
    if (car < '0' || car > '9') {
      k.consume();
    }
  }

  @FXML
  public void actionAppliquer() {
    this.facade.getConfiguration().setDossierSauvegarde(this.labelPath.getText());
    this.facade.getConfiguration().setUnite(this.comboBoxUnite.getValue());
    this.facade.getConfiguration().setRepresentation(this.comboBoxRepresentation.getValue());
    this.facade.getConfiguration().setUnite(this.comboBoxUnite.getValue());

    Stage stage = (Stage) this.buttonAppliquer.getScene().getWindow();
    stage.close();
  }

  @FXML
  public void actionAnnuler() {
    Stage stage = (Stage) this.buttonAnnuler.getScene().getWindow();
    stage.close();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    /*
     * initialiser les combobox
     */
    this.comboBoxUnite.getItems().add("km/h");
    this.comboBoxUnite.getItems().add("noeud");
    this.comboBoxUnite.getItems().add("mph");

    this.comboBoxRepresentation.getItems().add("Flèches");
    this.comboBoxRepresentation.getItems().add("Couleur");
    this.comboBoxRepresentation.getItems().add("Barbule");

    this.comboBoxConserver.getItems().add("Conserver");
    this.comboBoxConserver.getItems().add("Supprimer");

    this.facade = FacadeFx.getInstance();

    /*
     * Initialisation de l'uniter
     */
    this.comboBoxUnite.setValue(this.facade.getConfiguration().getUnite());

    /*
     * Initialisation du type de fleche
     */
    this.comboBoxRepresentation.setValue(this.facade.getConfiguration().getRepresentation());

    /*
     * Initialisation du dossier de sauvegarde
     */
    this.labelPath.setText(this.facade.getConfiguration().getDossierSauvegarde());

    /*
     * Initialisation Conserver
     */
    if (this.facade.getConfiguration().isConserver()) {
      this.comboBoxConserver.setValue("Conserver");

    } else {
      this.comboBoxConserver.setValue("Supprimer");
    }
  }

}
