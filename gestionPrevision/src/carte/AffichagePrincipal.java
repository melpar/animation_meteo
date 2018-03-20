package carte;

import java.awt.event.ActionEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.map.DefaultMapContext;
import org.geotools.map.MapContext;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.action.SafeAction;

import edition.implementation.Json;
import previsionVents.ListePrevision;
import previsionVents.RecuperationDonneesGrib;

/**
 * Prompts the user for a shapefile and displays the contents on the screen in a
 * map frame. This is the GeoTools Quickstart application used in documentationa
 * and tutorials. *
 */
@SuppressWarnings("deprecation")
public class AffichagePrincipal {

  private SetFrame modifFrame;
  private static String format = "   dd/MM/yyyy hh   ";
  private AfficherFleches afficherFleches;

  /**
   * GeoTools Quickstart demo application. Prompts the user for a shapefile and
   * displays its contents on the screen in a map frame
   */
  @SuppressWarnings({ "serial" })
  public void afficherFenetrePrincipale() throws Exception {
    // display a data store file chooser dialog for shapefiles
    File file;
    file = new File("shape/simplified_land_polygons.shp");

    FileDataStore store = FileDataStoreFinder.getDataStore(file);
    SimpleFeatureSource featureSource = store.getFeatureSource();

    // Create a map context and add our shapefile to it
    MapContext map = new DefaultMapContext();
    map.setTitle("Animation météo");
    map.addLayer(featureSource, null);

    // Create a JMapFrame with a menu to choose the display style for the
    JMapFrame frame = new JMapFrame(map);
    frame.setSize(1500, 1000);
    frame.enableStatusBar(true);
    // frame.enableTool(JMapFrame.Tool.PAN, JMapFrame.Tool.RESET);
    frame.enableToolBar(true);
    frame.getToolBar().remove(1);
    Dessiner dessiner = new Dessiner(map);
    this.afficherFleches = new AfficherFleches(map);
    JButton boutonZoom = new JButton("+");
    boutonZoom.addMouseListener(new ListenerBoutonZoom(frame.getMapPane(), dessiner));
    frame.getToolBar().add(boutonZoom);

    System.out.println(frame.getMapPane());
    System.out.println(frame.getMapPane().getComponentCount());
    JMenuBar menuBar = new JMenuBar();

    // ajout des fonctionnalitées supplementaire
    modifFrame = new SetFrame(frame, map);

    frame.setJMenuBar(menuBar);
    JMenu menuFichier = new JMenu("Fichier");
    menuBar.add(menuFichier);
    menuFichier.add(new SafeAction("Ouvrir un fichier Grib") {
      public void action(ActionEvent e) throws Throwable {

        JFileChooser choix = new JFileChooser();
        int retour = choix.showOpenDialog(null);
        if (retour == JFileChooser.APPROVE_OPTION) {
          // un fichier a été choisi (sortie par OK)
          // nom du fichier choisi
          System.out.println(choix.getSelectedFile().getName());
          // chemin absolu du fichier choisi
          choix.getSelectedFile().getAbsolutePath();
          System.out.println("1");
          RecuperationDonneesGrib recupGrib = new RecuperationDonneesGrib();
          System.out.println("1");
          ListePrevision prevision = recupGrib
              .getListePrevision(choix.getSelectedFile().getAbsolutePath());
          System.out.println("1");
          afficherFleches.setPas(5);
          afficherFleches.action(null);
        } else {
          System.out.println("pas de fichier");
        }
        ;// pas de fichier choisi
      }
    });

    menuFichier.add(new SafeAction("Ouvrir un fichier JSON") {
      public void action(ActionEvent e) throws Throwable {

        JFileChooser choix = new JFileChooser();
        int retour = choix.showOpenDialog(null);
        if (retour == JFileChooser.APPROVE_OPTION) {
          // un fichier a été choisi (sortie par OK)
          // nom du fichier choisi
          choix.getSelectedFile().getName();
          // chemin absolu du fichier choisi
          choix.getSelectedFile().getAbsolutePath();
          ListePrevision listePrevision = new Json().JsonRead("test2.json");
          afficherFleches.setPas(5);
          afficherFleches.action(null);
        } else {
          System.out.println("pas de fichier");
        }
        ;// pas de fichier choisi
      }
    });

    menuFichier.add(afficherFleches);
    JMenu menuModification = new JMenu("Edition");
    menuBar.add(menuModification);
    JMenu menuConfiguration = new JMenu("Configuration");
    menuBar.add(menuConfiguration);

    // Finally display the map frame.
    // When it is closed the app will exit.
    frame.setVisible(true);
  }

  public void setLabelDate(String value) {
    this.modifFrame.setLabelDateValue(value);
  }

  /**
   * Permet de lancer l'affichage de la fenetre.
   * 
   * @param args
   *          pas utilisé
   * @throws Exception
   *           si erreur lors de l'affichage
   */
  public static void main(String[] args) throws Exception {
    AffichagePrincipal aff = new AffichagePrincipal();
    aff.afficherFenetrePrincipale();
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    String dateValue = sdf.format(d);
    aff.setLabelDate(dateValue);
  }

}
