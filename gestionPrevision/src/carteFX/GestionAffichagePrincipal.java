package carteFX;

import javax.swing.JFileChooser;

import carte.AfficherFleches;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import previsionVents.ListePrevision;
import previsionVents.RecuperationDonneesGrib;

public class GestionAffichagePrincipal {

  MapCanvas canvas;

  public GestionAffichagePrincipal(SplitPane splitPane) {
    // TODO Auto-generated constructor stub
    // this.canvas = new MapCanvas(1024, 768);
    this.canvas = new MapCanvas(1500, 1000);
    Pane pane = new Pane(canvas.getCanvas());
    splitPane.getItems().add(pane);
  }

  public void ouvrirGrib() {
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

      // String lesPas = Zoom.realiserZoom(frame.getMapPane(),
      // prevision.getZonePrevision().getMaximum().x,
      // prevision.getZonePrevision().getMinimum().x,
      // prevision.getZonePrevision().getMaximum().y,
      // prevision.getZonePrevision().getMinimum().y,
      // prevision.getZonePrevision().getPasX(),
      // prevision.getZonePrevision().getPasY());
      AfficherFleches afficherFleches = AfficherFleches.getInstance(canvas.getMap());
      double pasXDouble = 20;
      double pasYDouble = 20;
      double taille = prevision.getZonePrevision().getPasX() * (pasXDouble - 5);
      afficherFleches.setPas((int) pasXDouble, (int) pasYDouble);
      afficherFleches.setTaille(taille);

      try {
        afficherFleches.action(null);
        canvas.rafraichir();
      } catch (Throwable e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } else {
      System.out.println("pas de fichier");
    }
    ;// pas de fichier choisi
  }

  public void ouvrirJson() {
    // TODO a faire
  }

  public void editer() {
    // TODO a faire
  }

  public void modifier() {
    // TODO a faire
  }

  public void parametre() {
    // TODO a faire
  }

  public MapCanvas getCanvas() {
    return canvas;
  }
}
