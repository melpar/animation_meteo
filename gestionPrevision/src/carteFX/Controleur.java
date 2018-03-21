package carteFX;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;

import carte.AfficherFleches;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import previsionVents.ListePrevision;
import previsionVents.RecuperationDonneesGrib;

public class Controleur implements Initializable {

  MapCanvas canvas;

  @FXML
  BorderPane borderPane;

  @FXML
  SplitPane splitPane;

  @FXML
  public void actionBouttonDeplacer() {
    System.out.println("Action Boutton Deplacer");
    this.canvas.deplacer = !this.canvas.deplacer;
  }

  @FXML
  public void actionBouttonZoom() {
    System.out.println("Action Boutton Zoom");
    this.canvas.zoom(-100);
  }

  @FXML
  public void actionBouttonDeZoom() {
    System.out.println("Action Boutton DeZoom");
    this.canvas.zoom(100);
  }

  @FXML
  public void ouvrirFichierGrib() {
    AfficherFleches afficherFleches = AfficherFleches.getInstance(this.canvas.getMap());
    JFileChooser choix = new JFileChooser();
    int retour = choix.showOpenDialog(null);
    if (retour == JFileChooser.APPROVE_OPTION) {
      // un fichier a été choisi (sortie par OK)
      // nom du fichier choisi
      System.out.println(choix.getSelectedFile().getName());
      // chemin absolu du fichier choisi
      choix.getSelectedFile().getAbsolutePath();
      RecuperationDonneesGrib recupGrib = new RecuperationDonneesGrib();
      ListePrevision prevision = recupGrib
          .getListePrevision(choix.getSelectedFile().getAbsolutePath());
      afficherFleches.setPas(5, 5);
      try {
        afficherFleches.action(null);
      } catch (Throwable e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } else {
      System.out.println("pas de fichier");
    }
    ;// pas de fichier choisi
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.canvas = new MapCanvas(1024, 768);
    Pane pane = new Pane(canvas.getCanvas());
    // borderPane.setCenter(pane);
    // splitPane.getItems().set(1, pane);
    splitPane.getItems().add(pane);
  }

}
