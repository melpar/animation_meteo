package vues.edition;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
  TableColumn<NouvelleAjout, Button> supprimerPrevision;

  @FXML
  DatePicker dateVal;

  @FXML
  ScrollPane consoleZone;

  @FXML
  TextFlow console;

  int idPrevision;

  ObservableList<NouvelleAjout> listeObservable;

  @FXML
  public void initialize() {
    this.idPrevision = 0;
    this.listeObservable = FXCollections.observableArrayList();

    this.datePrevision = new TableColumn<NouvelleAjout, String>("Date");
    this.heurePrevision = new TableColumn<NouvelleAjout, String>("Heure");
    this.puissancePrevision = new TableColumn<NouvelleAjout, Float>("Puissance (Km\\h)");
    this.directionPrevision = new TableColumn<NouvelleAjout, Float>("Direction (° rad)");
    this.dureePrevision = new TableColumn<NouvelleAjout, Integer>("Duree (heures)");
    this.supprimerPrevision = new TableColumn<NouvelleAjout, Button>("");

    this.datePrevision.setCellValueFactory(new PropertyValueFactory<NouvelleAjout, String>("date"));
    this.heurePrevision
        .setCellValueFactory(new PropertyValueFactory<NouvelleAjout, String>("heure"));
    this.dureePrevision
        .setCellValueFactory(new PropertyValueFactory<NouvelleAjout, Integer>("duree"));
    this.puissancePrevision
        .setCellValueFactory(new PropertyValueFactory<NouvelleAjout, Float>("puissance"));
    this.directionPrevision
        .setCellValueFactory(new PropertyValueFactory<NouvelleAjout, Float>("direction"));
    this.supprimerPrevision
        .setCellValueFactory(new PropertyValueFactory<NouvelleAjout, Button>("bouton"));

    double taille = 105;
    this.datePrevision.setPrefWidth(taille);
    this.heurePrevision.setPrefWidth(taille);
    this.puissancePrevision.setPrefWidth(taille + 50);
    this.directionPrevision.setPrefWidth(taille + 50);
    this.dureePrevision.setPrefWidth(taille);
    this.supprimerPrevision.setPrefWidth(taille - 10);

    this.listePrevision.getColumns().add(datePrevision);
    this.listePrevision.getColumns().add(heurePrevision);
    this.listePrevision.getColumns().add(dureePrevision);
    this.listePrevision.getColumns().add(puissancePrevision);
    this.listePrevision.getColumns().add(directionPrevision);
    this.listePrevision.getColumns().add(supprimerPrevision);

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
        if (p < 0 || p > 410) {
          throw new NumberFormatException();
        }
      } else {
        throw new NumberFormatException();
      }

    } catch (NumberFormatException e) {
      validation = false;
      puissanceVal.setStyle("-fx-background-color: rgb(255, 80, 80);");

      Text t = new Text(
          "Erreur: Champ Puissance (format incorrect: nécessite un nombre -1<puissance<410)"
              + "\n");
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
        if (du > 48) {

        }
      } else {
        throw new NumberFormatException();
      }
    } catch (NumberFormatException e) {
      validation = false;
      dureeVal.setStyle("-fx-background-color: rgb(255, 80, 80);");

      Text t = new Text(
          "Erreur: Champ Duree (format incorrect : nécessite un nombre entier 0<duree<48)" + "\n");
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
      NouvelleAjout n = new NouvelleAjout(date, d, p, heure, du, listeObservable.size());
      listeObservable.addAll(FXCollections.observableArrayList(n));
      this.actualiserListePrevision();
      Text t = new Text("Ajout d'une nouvelle prevision : " + date + "\n");
      t.setFill(Color.rgb(0, 153, 51));
      this.console.getChildren().add(t);
      this.scrollbas();
    }

  }

  private boolean supprimerPrevision(int id) {
    for (int i = 0; i < this.listeObservable.size(); i++) {
      if (this.listeObservable.get(i).getId() == id) {
        Text t = new Text(
            "Suppression de la prevision du " + this.listeObservable.get(i).getDate() + "\n");
        this.listeObservable.remove(i);
        t.setFill(Color.rgb(204, 0, 0));
        this.console.getChildren().add(t);
        this.scrollbas();
        return true;
      }
    }
    return false;

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
  public void actionBoutonValider() {

  }

  @FXML
  public void actionBoutonFermer() {
    Platform.exit();
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
    public Button bouton;
    public int id;

    public NouvelleAjout(String date, float direction, float puissance, String heure, int duree,
        int id) {
      this.date = date;
      this.direction = direction;
      this.puissance = puissance;
      this.heure = heure;
      this.duree = duree;
      this.id = id;
      this.bouton = new Button("Supprimer");
      this.bouton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent e) {
          ControleurEdition.this.supprimerPrevision(id);
        }
      });
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

    public Button getBouton() {
      return bouton;
    }

    public void setBouton(Button bouton) {
      this.bouton = bouton;
    }

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

  }

}
