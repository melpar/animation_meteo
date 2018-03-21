package carteFX;

import javax.swing.JFileChooser;

import org.geotools.geometry.jts.ReferencedEnvelope;

import carte.AfficherFleches;
import carteFX.densite.Zoom;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import previsionVents.ListePrevision;
import previsionVents.RecuperationDonneesGrib;
import previsionVents.ZoneSelectionne;

public class GestionAffichagePrincipal {

  ZoneSelectionne zone;
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

  public ZoneSelectionne getZone() {
    return zone;
  }
}
