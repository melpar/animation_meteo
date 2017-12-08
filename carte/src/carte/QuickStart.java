package carte;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.map.DefaultMapContext;
import org.geotools.map.MapContext;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.action.SafeAction;
import org.geotools.swing.data.JFileDataStoreChooser;

/**
 * Prompts the user for a shapefile and displays the contents on the screen in a
 * map frame.
 * <p>
 * This is the GeoTools Quickstart application used in documentationa and
 * tutorials. *
 */
public class QuickStart {

  /**
   * GeoTools Quickstart demo application. Prompts the user for a shapefile and
   * displays its contents on the screen in a map frame
   */
  public static void main(String[] args) throws Exception {
    // display a data store file chooser dialog for shapefiles
    File file = JFileDataStoreChooser.showOpenFile("shp", null);
    if (file == null) {
      return;
    }

    FileDataStore store = FileDataStoreFinder.getDataStore(file);
    SimpleFeatureSource featureSource = store.getFeatureSource();

    // Create a map context and add our shapefile to it
    MapContext map = new DefaultMapContext();
    map.setTitle("Quickstart");
    map.addLayer(featureSource, null);

    // // Now display the map
    // JMapFrame.showMap(map);
    // Frame frame = JMapFrame.getFrames()[0];
    // MenuBar menu = new MenuBar();
    // Menu me = new Menu("test");
    // me.add(new MenuItem("test"));
    // menu.add(me);
    // menu.setName("test");
    // frame.setMenuBar(menu);

    // Create a JMapFrame with a menu to choose the display style for the
    JMapFrame frame = new JMapFrame(map);
    frame.setSize(800, 600);
    frame.enableStatusBar(true);

    // frame.enableTool(JMapFrame.Tool.ZOOM, JMapFrame.Tool.PAN,
    // JMapFrame.Tool.RESET);
    frame.enableToolBar(true);

    System.out.println(frame.getMapPane());
    System.out.println(frame.getMapPane().getComponentCount());
    JMenuBar menuBar = new JMenuBar();
    frame.setJMenuBar(menuBar);
    JMenu menu = new JMenu("Raster");
    menuBar.add(menu);

    menu.add(new SafeAction("Grayscale display") {
      public void action(ActionEvent e) throws Throwable {
        System.out.println();
      }
    });

    menu.add(new SafeAction("RGB display") {
      public void action(ActionEvent e) throws Throwable {

      }
    });
    // Finally display the map frame.
    // When it is closed the app will exit.
    frame.setVisible(true);
  }

}
