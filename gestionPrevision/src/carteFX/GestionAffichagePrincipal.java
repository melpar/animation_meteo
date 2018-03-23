package carteFX;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.geotools.geometry.jts.ReferencedEnvelope;

import com.vividsolutions.jts.geom.Coordinate;

import carte.AfficherFleches;
import carte.CalculPosition;
import carteFX.densite.Zoom;
import carteFX.facade.FacadeFx;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import previsionVents.FacadePrevisionVents;
import previsionVents.ListePrevision;
import previsionVents.RecuperationDonneesGrib;
import previsionVents.ZonePrevision;
import previsionVents.ZoneSelectionne;

public class GestionAffichagePrincipal {

  private ZoneSelectionne zone;
  private int indexDatePrevision;
  private MapCanvas canvas;

  public GestionAffichagePrincipal(SplitPane splitPane) {
    // this.canvas = new MapCanvas(1024, 768);
    indexDatePrevision = 0;
    this.canvas = new MapCanvas(1500, 1000);
    Pane pane = new Pane(canvas.getCanvas());
    splitPane.getItems().add(pane);
    AfficherFleches afficherFleches = AfficherFleches.getInstance(canvas.getMap());

  }

  public void ouvrirGrib() {
    FileChooser fileChooser = new FileChooser();
    File selecetFile = fileChooser.showOpenDialog(new Stage());
    if (selecetFile != null) {
      System.out.println(selecetFile.getAbsolutePath());
      // chemin absolu du fichier choisi
      // choix.getSelectedFile().getAbsolutePath();
      RecuperationDonneesGrib recupGrib = new RecuperationDonneesGrib();
      ListePrevision prevision = recupGrib.getListePrevision(selecetFile.getAbsolutePath());

      AfficherFleches afficherFleches = AfficherFleches.getInstance(canvas.getMap());
      double pasXDouble = 20;
      double pasYDouble = 20;
      double taille = prevision.getZonePrevision().getPasX() * (pasXDouble - 5);
      afficherFleches.setPas((int) pasXDouble, (int) pasYDouble);
      afficherFleches.setTaille(taille);

      try {
        afficherFleches.action(null);
        Zoom z = new Zoom(canvas);
        z.zoom(-500);
        double maxX = prevision.getZonePrevision().getMaximum().x;
        double minX = prevision.getZonePrevision().getMinimum().x;
        double maxY = prevision.getZonePrevision().getMaximum().y;
        double minY = prevision.getZonePrevision().getMinimum().y;
        ReferencedEnvelope env = new ReferencedEnvelope(canvas.getMap().getViewport().getBounds());
        env.translate(minX + (minX + maxX) / 2, minY - (minY + maxY) / 2);
        canvas.doSetDisplayArea(env);
        Calendar valeurDate = FacadePrevisionVents.getFacadePrevisionVents().getPrevisions()
            .getListePrevision().get(indexDatePrevision).getDatePrevision();
        FacadeFx.getInstance().setDate(valeurDate);
        canvas.rafraichir();
      } catch (Throwable e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    //
    // JFileChooser choix = new JFileChooser();
    // int retour = choix.showOpenDialog(null);
    // if (retour == JFileChooser.APPROVE_OPTION) {
    // // un fichier a été choisi (sortie par OK)
    // // nom du fichier choisi
    // System.out.println(choix.getSelectedFile().getName());
    // // chemin absolu du fichier choisi
    // choix.getSelectedFile().getAbsolutePath();
    // RecuperationDonneesGrib recupGrib = new RecuperationDonneesGrib();
    // ListePrevision prevision = recupGrib
    // .getListePrevision(choix.getSelectedFile().getAbsolutePath());
    //
    // AfficherFleches afficherFleches =
    // AfficherFleches.getInstance(canvas.getMap());
    // double pasXDouble = 20;
    // double pasYDouble = 20;
    // double taille = prevision.getZonePrevision().getPasX() * (pasXDouble -
    // 5);
    // afficherFleches.setPas((int) pasXDouble, (int) pasYDouble);
    // afficherFleches.setTaille(taille);
    //
    // try {
    // afficherFleches.action(null);
    // Zoom z = new Zoom(canvas);
    // z.zoom(-500);
    // double maxX = prevision.getZonePrevision().getMaximum().x;
    // double minX = prevision.getZonePrevision().getMinimum().x;
    // double maxY = prevision.getZonePrevision().getMaximum().y;
    // double minY = prevision.getZonePrevision().getMinimum().y;
    // ReferencedEnvelope env = new
    // ReferencedEnvelope(canvas.getMap().getViewport().getBounds());
    // env.translate(minX + (minX + maxX) / 2, minY - (minY + maxY) / 2);
    // canvas.doSetDisplayArea(env);
    // Calendar valeurDate =
    // FacadePrevisionVents.getFacadePrevisionVents().getPrevisions()
    // .getListePrevision().get(indexDatePrevision).getDatePrevision();
    // FacadeFx.getInstance().setDate(valeurDate);
    // canvas.rafraichir();
    // } catch (Throwable e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // } else {
    // System.out.println("pas de fichier");
    // }
    // ;// pas de fichier choisi
  }

  public void ouvrirJson() {
    // TODO a faire
  }

  public void sauvegarder() {
    // TODO Auto-generated method stub

  }

  public void editer() {
    Stage primaryStage = new Stage();
    Parent root;
    try {
      root = FXMLLoader.load(getClass().getResource("ressources/edition/VueEdition.fxml"));
      Scene scene = new Scene(root);
      primaryStage.setTitle("Edition");
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void modifier() {
    Stage primaryStage = new Stage();
    Parent root;
    try {
      File currentDirFile = new File(".");
      String helper = currentDirFile.getAbsolutePath();
      String currentDir = helper.substring(0,
          helper.length() - currentDirFile.getCanonicalPath().length());
      System.out.println(currentDir);
      root = FXMLLoader
          .load(getClass().getResource("ressources/modification/VueModification.fxml"));
      Scene scene = new Scene(root);
      primaryStage.setTitle("Modifications");
      primaryStage.setScene(scene);
      primaryStage.show();
      primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
        public void handle(WindowEvent we) {
          canvas.rafraichir();
        }
      });
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public void actionPrecedente() {
    FacadeFx.getInstance().getModifier().restaureArriere();
    canvas.rafraichir();
  }

  public void actionSuivante() {
    FacadeFx.getInstance().getModifier().restaureAvant();
    canvas.rafraichir();
  }

  public void updateAction(Button precedent, Button suivant) {
    precedent.setDisable(FacadeFx.getInstance().getModifier().isArriere());
    suivant.setDisable(FacadeFx.getInstance().getModifier().isAvant());
  }

  public void parametre() {
    Stage primaryStage = new Stage();
    Parent root;
    try {
      root = FXMLLoader
          .load(getClass().getResource("ressources/configuration/VueConfiguration.fxml"));
      Scene scene = new Scene(root);
      primaryStage.setTitle("Configuration");
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void dateSuivante() {
    // TODO a faire
    if (indexDatePrevision + 1 < FacadePrevisionVents.getFacadePrevisionVents().getPrevisions()
        .getListePrevision().size()) {
      indexDatePrevision++;
      Calendar valeurDate = FacadePrevisionVents.getFacadePrevisionVents().getPrevisions()
          .getListePrevision().get(indexDatePrevision).getDatePrevision();
      FacadeFx.getInstance().setDate(valeurDate);
      AfficherFleches afficherFleches = AfficherFleches.getInstance(canvas.getMap());
      afficherFleches.setPrevision(indexDatePrevision);
      try {
        afficherFleches.action(null);
        canvas.rafraichir();
      } catch (Throwable e) {

      }
    }
  }

  public void datePrecedente() {
    if (indexDatePrevision - 1 >= 0) {
      indexDatePrevision--;
      Calendar valeurDate = FacadePrevisionVents.getFacadePrevisionVents().getPrevisions()
          .getListePrevision().get(indexDatePrevision).getDatePrevision();
      FacadeFx.getInstance().setDate(valeurDate);
      AfficherFleches afficherFleches = AfficherFleches.getInstance(canvas.getMap());
      afficherFleches.setPrevision(indexDatePrevision);
      try {
        afficherFleches.action(null);
        canvas.rafraichir();
      } catch (Throwable e) {

      }
    }
  }

  public void updateDate(Button precedent, Label date, Button suivant) {
    if (FacadePrevisionVents.getFacadePrevisionVents().getPrevisions() != null) {
      if (FacadePrevisionVents.getFacadePrevisionVents().getPrevisions().getListePrevision()
          .size() > 0) {
        Calendar valeurDate = FacadePrevisionVents.getFacadePrevisionVents().getPrevisions()
            .getListePrevision().get(indexDatePrevision).getDatePrevision();
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-YYYY : kk");
        String formatted = format1.format(valeurDate.getTime());
        date.setText(formatted);
      }
      if (indexDatePrevision > 0) {
        precedent.setDisable(false);
      } else {
        precedent.setDisable(true);
      }
      if (indexDatePrevision < FacadePrevisionVents.getFacadePrevisionVents().getPrevisions()
          .getListePrevision().size() - 1) {
        suivant.setDisable(false);
      } else {
        suivant.setDisable(true);
      }
    }
  }

  public MapCanvas getCanvas() {
    return canvas;
  }

  private ZonePrevision getZone() {

    Coordinate coorDeb = CalculPosition.convertEpsg4326to3857(
        new Coordinate(canvas.getzone().getDebutX(), canvas.getzone().getDebutY()));
    Coordinate coorFin = CalculPosition.convertEpsg4326to3857(
        new Coordinate(canvas.getzone().getFinX(), canvas.getzone().getFinY()));
    double pas = FacadeFx.getInstance().getConfiguration().getPat();
    int nombreX = (int) ((coorFin.x - coorDeb.x) / pas);
    int nombreY = (int) ((coorFin.y - coorDeb.y) / pas);
    ZonePrevision zonePrevision = new ZonePrevision(coorDeb.x, coorDeb.y, pas, pas, nombreX,
        nombreY);
    return zonePrevision;
  }

}
