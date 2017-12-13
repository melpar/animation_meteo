package carte;

import java.awt.event.ActionEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.map.DefaultMapContext;
import org.geotools.map.MapContext;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.action.SafeAction;

/**
 * Prompts the user for a shapefile and displays the contents on the screen in a
 * map frame.
 * <p>
 * This is the GeoTools Quickstart application used in documentationa and
 * tutorials. *
 */
public class AffichagePrincipal {

  private SetFrame modifFrame;
  private static String format = "   dd/MM/yyyy hh   ";
  private AfficherFleches afficherFleches;

  /**
   * GeoTools Quickstart demo application. Prompts the user for a shapefile and
   * displays its contents on the screen in a map frame
   */
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
    frame.setSize(800, 600);
    frame.enableStatusBar(true);

    // frame.enableTool(JMapFrame.Tool.PAN, JMapFrame.Tool.RESET);
    frame.enableToolBar(true);
    frame.getToolBar().remove(1);
    Dessiner dessiner = new Dessiner(map);
    this.afficherFleches = new AfficherFleches(map);
    JButton boutonZoom = new JButton("+");
    boutonZoom.addMouseListener(new ListenerBoutonZoom(frame.getMapPane(), dessiner));
    frame.getToolBar().add(boutonZoom);
    // boutonZoom.addActionListener(new ActionListener() {
    // public void actionPerformed(ActionEvent e) {
    // frame.getMapPane().setState(JMapPane.);
    // }
    // });

    // ZoomInAction action = (ZoomInAction) boutonZoom.getAction();
    // frame.getMapPane().addMouseWheelListener(new MouseWheelListener() {
    // public void mouseWheelMoved(MouseWheelEvent ev) {
    // System.out.println("molette");
    // try {
    // afficherFleches.action(null);
    // } catch (Throwable e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // int clicks = ev.getWheelRotation();
    // // -ve means wheel moved up, +ve means down
    // int sign = (clicks < 0 ? -1 : 1);
    //
    // ReferencedEnvelope env = frame.getMapPane().getDisplayArea();
    // double width = env.getWidth();
    // double delta = 1.5 * sign;
    //
    // env.expandBy(delta);
    // frame.getMapPane().setDisplayArea((org.opengis.geometry.Envelope) env);
    // frame.getMapPane().repaint();
    // }
    // });

    // ZoomInAction zoomAction = new Zoom(frame.getMapPane());
    // boutonZoom.setAction(zoomAction);
    System.out.println(frame.getMapPane());
    System.out.println(frame.getMapPane().getComponentCount());
    JMenuBar menuBar = new JMenuBar();

    // ajout des fonctionnalitées supplementaire
    modifFrame = new SetFrame(frame, map);

    frame.setJMenuBar(menuBar);
    JMenu menuFichier = new JMenu("Fichier");
    menuBar.add(menuFichier);
    menuFichier.add(new SafeAction("Ouvrir fichier") {
      public void action(ActionEvent e) throws Throwable {
        System.out.println("fichier");
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

  public static void main(String[] args) throws Exception {
    AffichagePrincipal aff = new AffichagePrincipal();
    aff.afficherFenetrePrincipale();
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    String dateValue = sdf.format(d);
    aff.setLabelDate(dateValue);
  }

}
