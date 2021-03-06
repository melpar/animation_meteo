package carteFX.ressources.edition;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import carte.AfficherFleches;
import carteFX.Controleur;
import carteFX.facade.FacadeFx;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import previsionVents.FacadePrevisionVents;
import previsionVents.ListePrevision;

public class ControleurEdition {

  @FXML
  TextField vitesseVal;

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
  TableColumn<NouvelleAjout, Float> vitessePrevision;

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

  @FXML
  Button bouttonFermer;

  private double la;
  private double li;
  private double px;
  private double py;
  private int nx;
  private int ny;

  int idPrevision;

  ObservableList<NouvelleAjout> listeObservable;

  @FXML
  public void initialize() {

    FacadeFx facade = FacadeFx.getInstance();

    this.la = facade.getZone().getLatitudeHautGauche();
    this.li = facade.getZone().getLongitudeHautGauche();
    this.px = facade.getZone().getPasX();
    this.py = facade.getZone().getPasY();
    this.nx = facade.getZone().getNombreX();
    this.ny = facade.getZone().getNombreY();

    this.idPrevision = 0;
    this.listeObservable = FXCollections.observableArrayList();

    this.datePrevision = new TableColumn<NouvelleAjout, String>("Date");
    this.heurePrevision = new TableColumn<NouvelleAjout, String>("Heure");
    this.vitessePrevision = new TableColumn<NouvelleAjout, Float>("Vitesse (Km\\h)");
    this.directionPrevision = new TableColumn<NouvelleAjout, Float>("Direction (°)");
    this.dureePrevision = new TableColumn<NouvelleAjout, Integer>("Duree (heures)");
    this.supprimerPrevision = new TableColumn<NouvelleAjout, Button>("");

    this.datePrevision.setCellValueFactory(new PropertyValueFactory<NouvelleAjout, String>("date"));
    this.heurePrevision
        .setCellValueFactory(new PropertyValueFactory<NouvelleAjout, String>("heure"));
    this.dureePrevision
        .setCellValueFactory(new PropertyValueFactory<NouvelleAjout, Integer>("duree"));
    this.vitessePrevision
        .setCellValueFactory(new PropertyValueFactory<NouvelleAjout, Float>("vitesse"));
    this.directionPrevision
        .setCellValueFactory(new PropertyValueFactory<NouvelleAjout, Float>("direction"));
    this.supprimerPrevision
        .setCellValueFactory(new PropertyValueFactory<NouvelleAjout, Button>("bouton"));

    double taille = 105;
    this.datePrevision.setPrefWidth(taille);
    this.heurePrevision.setPrefWidth(taille);
    this.vitessePrevision.setPrefWidth(taille + 50);
    this.directionPrevision.setPrefWidth(taille + 50);
    this.dureePrevision.setPrefWidth(taille);
    this.supprimerPrevision.setPrefWidth(taille - 15);

    this.listePrevision.getColumns().add(datePrevision);
    this.listePrevision.getColumns().add(heurePrevision);
    this.listePrevision.getColumns().add(dureePrevision);
    this.listePrevision.getColumns().add(vitessePrevision);
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

    directionVal.setPromptText("°");
    vitesseVal.setPromptText("km/h");
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
      chaine = vitesseVal.getText();
      if (!chaine.equals("")) {
        vitesseVal.setStyle("-fx-background-color: rgb(153, 255, 153);");
        p = Float.parseFloat(chaine);
        if (p < 0 || p > 410) {
          throw new NumberFormatException();
        }
      } else {
        throw new NumberFormatException();
      }

    } catch (NumberFormatException e) {
      validation = false;
      vitesseVal.setStyle("-fx-background-color: rgb(255, 80, 80);");

      Text t = new Text(
          "Erreur: Champ Vitesse (format incorrect: nécessite un nombre -1<vitesse<410)" + "\n");
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
      chaine = dateVal.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
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
      heure = heuresVal.getValue() + ":" + minutesVal.getValue();
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
    this.vitesseVal.setText("");
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

    ListePrevision lp = new ListePrevision(this.la, this.li, this.px, this.py, this.nx, this.ny);
    System.out.println("Bouton valider");
    System.out.println("Liste observable : " + listeObservable.size());
    for (int i = 0; i < this.listeObservable.size(); i++) {
      System.out.println("Liste observable 2 : " + listeObservable.get(i).getDuree());
      System.out.println("nx : " + nx);
      System.out.println("ny : " + ny);
      for (int h = 0; h < this.listeObservable.get(i).getDuree(); h++) {
        for (int x = 0; x < nx; x++) {
          for (int y = 0; y < ny; y++) {
            System.out.println("Nouvel ajout i " + i + " h " + h);
            System.out.println("Nouvel ajout x " + x + " y " + y);
            System.out.println("Nouvel ajout nx " + nx + " ny " + ny);
            NouvelleAjout n = this.listeObservable.get(i);
            Calendar c = this.conversionCalendar(n.getDate(), n.getHeure());
            c.setTimeInMillis(c.getTimeInMillis() + (3600000 * (h)));
            double u = this.getU(n.getVitesse(), n.getDirection());
            double v = this.getV(n.getVitesse(), n.getDirection());
            lp.ajouterDonneeVent(c, u, v, x, y);

          }
        }
      }
      AfficherFleches afficherFleches = AfficherFleches
          .getInstance(Controleur.getInstance().getGestion().getCanvas().getMap());
      double pasXDouble = 20;
      double pasYDouble = 20;
      double taille = lp.getZonePrevision().getPasX() * (pasXDouble - 5);
      afficherFleches.setPas((int) pasXDouble, (int) pasYDouble);
      afficherFleches.setTaille(taille);

      try {
        afficherFleches.action(null);
        Calendar valeurDate = FacadePrevisionVents.getFacadePrevisionVents().getPrevisions()
            .getListePrevision().get(0).getDatePrevision();
        FacadeFx.getInstance().setDate(valeurDate);
        Controleur.getInstance().getGestion().getCanvas().rafraichir();
      } catch (Throwable e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      Controleur.getInstance().getInstance().updateDate();
    }
    if (this.listeObservable.size() == 0) {
      Text t = new Text("Erreur: Aucune previsions saisie \n");
      t.setFill(Color.rgb(204, 0, 0));
      this.console.getChildren().add(t);
      this.scrollbas();
    } else {
      this.actionBoutonFermer();
    }
  }

  private Calendar conversionCalendar(String date, String heure) {
    Calendar c = Calendar.getInstance();

    DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    Date d = new Date();
    try {
      c.setTime(df.parse(date + " " + heure));
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return c;
  }

  private double getV(double v, double a) {
    return (Math.cos(a * Math.PI / 180) * v) / 3.6;
  }

  private double getU(double v, double a) {
    return (Math.sin(a * Math.PI / 180) * v) / 3.6;
  }

  @FXML
  public void actionBoutonFermer() {
    Stage stage = (Stage) this.console.getScene().getWindow();
    stage.close();
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
    public float vitesse;
    public String heure;
    public int duree;
    public Button bouton;
    public int id;

    public NouvelleAjout(String date, float direction, float vitesse, String heure, int duree,
        int id) {
      this.date = date;
      this.direction = direction;
      this.vitesse = vitesse;
      this.heure = heure;
      this.duree = duree;
      this.id = id;
      this.bouton = new Button();
      ImageView img = new ImageView(new Image(getClass().getResourceAsStream("croix.png")));
      img.setFitWidth(20);
      img.setFitHeight(20);
      this.bouton.setGraphic(img);
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

    public float getVitesse() {
      return vitesse;
    }

    public void setVitesse(float vitesse) {
      this.vitesse = vitesse;
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
