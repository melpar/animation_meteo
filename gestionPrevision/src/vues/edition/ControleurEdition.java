package vues.edition;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ControleurEdition {

  @FXML
  TextField puissanceVal;

  @FXML
  TextField directionVal;

  @FXML
  TextField dureeVal;

  @FXML
  ComboBox<String> heuresVal;

  @FXML
  ComboBox<String> minutesVal;

  @FXML
  TableView<NouvelleAjout> listePrevision;

  @FXML
  TableColumn<NouvelleAjout, String> datePrevision;

  @FXML
  TableColumn<NouvelleAjout, Float> puissancePrevision;

  @FXML
  TableColumn<NouvelleAjout, String> heurePrevision;

  @FXML
  TableColumn<NouvelleAjout, Float> directionPrevision;

  @FXML
  TableColumn<NouvelleAjout, Integer> dureePrevision;

  @FXML
  DatePicker dateVal;

  @FXML
  ScrollPane consoleZone;

  @FXML
  TextFlow console;

  int pas;

  ObservableList<NouvelleAjout> listeObservable;

  @FXML
  public void initialize() {
    this.pas = 0;
    this.listeObservable = FXCollections.observableArrayList();

    this.datePrevision = new TableColumn<NouvelleAjout, String>("Date");
    this.heurePrevision = new TableColumn<NouvelleAjout, String>("Heure");
    this.puissancePrevision = new TableColumn<NouvelleAjout, Float>("Puissance (Km\\h)");
    this.directionPrevision = new TableColumn<NouvelleAjout, Float>("Direction (° rad)");
    this.dureePrevision = new TableColumn<NouvelleAjout, Integer>("Duree (heures)");

    this.datePrevision.setCellValueFactory(new PropertyValueFactory<NouvelleAjout, String>("date"));
    this.heurePrevision
        .setCellValueFactory(new PropertyValueFactory<NouvelleAjout, String>("heure"));
    this.dureePrevision
        .setCellValueFactory(new PropertyValueFactory<NouvelleAjout, Integer>("duree"));
    this.puissancePrevision
        .setCellValueFactory(new PropertyValueFactory<NouvelleAjout, Float>("puissance"));
    this.directionPrevision
        .setCellValueFactory(new PropertyValueFactory<NouvelleAjout, Float>("direction"));

    this.listePrevision.getColumns().add(datePrevision);
    this.listePrevision.getColumns().add(heurePrevision);
    this.listePrevision.getColumns().add(dureePrevision);
    this.listePrevision.getColumns().add(puissancePrevision);
    this.listePrevision.getColumns().add(directionPrevision);

    for (int i = 0; i < 24; i++) {
      heuresVal.getItems().add(i + "");
    }
    for (int i = 0; i < 60; i++) {
      minutesVal.getItems().add(i + "");
    }
    heuresVal.getSelectionModel().selectFirst();
    minutesVal.getSelectionModel().selectFirst();

    directionVal.setPromptText("° rad");
    puissanceVal.setPromptText("km/h");
    dureeVal.setPromptText("heures");

  }

  @FXML
  public void actionBoutonAjouter() {

    boolean validation = true;
    float p = 0;
    float d = 0;
    int du = 0;
    String date = "";
    String heure = "";

    String chaine;
    try {
      chaine = puissanceVal.getText();
      if (!chaine.equals("")) {
        puissanceVal.setStyle("-fx-background-color: rgb(153, 255, 153);");
        p = Float.parseFloat(chaine);
      } else {
        throw new NumberFormatException();
      }

    } catch (NumberFormatException e) {
      validation = false;
      puissanceVal.setStyle("-fx-background-color: rgb(255, 80, 80);");

      Text t = new Text("Erreur: Champ Puissance (format incorrect: nécessite un nombre)" + "\n");
      t.setFill(Color.rgb(204, 0, 0));
      this.console.getChildren().add(t);
      this.scrollbas();
    }

    try {
      chaine = directionVal.getText();
      if (!chaine.equals("")) {
        directionVal.setStyle("-fx-background-color: rgb(153, 255, 153);");
        d = (Float.parseFloat(chaine) % 360);
        directionVal.setText("" + d);
      } else {
        throw new NumberFormatException();
      }
    } catch (NumberFormatException e) {
      validation = false;
      directionVal.setStyle("-fx-background-color: rgb(255, 80, 80);");

      Text t = new Text("Erreur: Champ Direction (format incorrect : nécessite un nombre)" + "\n");
      t.setFill(Color.rgb(204, 0, 0));
      this.console.getChildren().add(t);
      this.scrollbas();
    }

    try {
      chaine = dureeVal.getText();
      if (!chaine.equals("")) {
        dureeVal.setStyle("-fx-background-color: rgb(153, 255, 153);");
        du = (Integer.parseInt(chaine));
      } else {
        throw new NumberFormatException();
      }
    } catch (NumberFormatException e) {
      validation = false;
      dureeVal.setStyle("-fx-background-color: rgb(255, 80, 80);");

      Text t = new Text(
          "Erreur: Champ Duree (format incorrect : nécessite un nombre entier)" + "\n");
      t.setFill(Color.rgb(204, 0, 0));
      this.console.getChildren().add(t);
      this.scrollbas();
    }

    try {
      chaine = dateVal.getValue().toString();
      if (!chaine.equals("")) {
        dateVal.setStyle("-fx-background-color: rgb(153, 255, 153);");
        date = chaine;
      } else {
        throw new Exception();
      }
    } catch (Exception e) {
      validation = false;
      dateVal.setStyle("-fx-background-color: rgb(255, 80, 80);");

      Text t = new Text("Erreur : Champ Date " + "\n");
      t.setFill(Color.rgb(204, 0, 0));
      this.console.getChildren().add(t);
      this.scrollbas();
    }

    if (!(heuresVal.getValue().equals("")) && !(minutesVal.getValue().equals(""))) {
      heure = heuresVal.getValue() + " : " + minutesVal.getValue();
    } else {
      validation = false;

      Text t = new Text("Erreur : Champs Heures/Minutes " + "\n");
      t.setFill(Color.rgb(204, 0, 0));
      this.console.getChildren().add(t);
      this.scrollbas();
    }

    if (validation) {
      NouvelleAjout n = new NouvelleAjout(date, d, p, heure, du);
      listeObservable.addAll(FXCollections.observableArrayList(n));
      this.actualiserListePrevision();
      Text t = new Text("Ajout d'une nouvelle prevision : " + date + "\n");
      t.setFill(Color.rgb(0, 153, 51));
      this.console.getChildren().add(t);
      this.scrollbas();
    }

  }

  private void actualiserListePrevision() {
    this.listePrevision.setItems(this.listeObservable);

    this.puissanceVal.setText("");
    this.dureeVal.setText("");
    this.directionVal.setText("");
    heuresVal.getSelectionModel().selectFirst();
    minutesVal.getSelectionModel().selectFirst();

  }

  private void scrollbas() {
    consoleZone.vvalueProperty().bind(console.heightProperty());
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

  public class NouvelleAjout {

    public String date;
    public float direction;
    public float puissance;
    public String heure;
    public int duree;

    public NouvelleAjout(String date, float direction, float puissance, String heure, int duree) {
      this.date = date;
      this.direction = direction;
      this.puissance = puissance;
      this.heure = heure;
      this.duree = duree;
    }

    public String getDate() {
      return date;
    }

    public void setDate(String date) {
      this.date = date;
    }

    public float getDirection() {
      return direction;
    }

    public void setDirection(float direction) {
      this.direction = direction;
    }

    public float getPuissance() {
      return puissance;
    }

    public void setPuissance(float puissance) {
      this.puissance = puissance;
    }

    public String getHeure() {
      return heure;
    }

    public void setHeure(String heure) {
      this.heure = heure;
    }

    public int getDuree() {
      return duree;
    }

    public void setDuree(int duree) {
      this.duree = duree;
    }

  }

}
