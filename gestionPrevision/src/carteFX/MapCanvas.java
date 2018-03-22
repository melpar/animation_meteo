package carteFX;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.geometry.DirectPosition2D;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.DefaultMapContext;
import org.geotools.map.FeatureLayer;
import org.geotools.map.MapContext;
import org.geotools.renderer.lite.StreamingRenderer;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;

import com.vividsolutions.jts.geom.Coordinate;

import carte.CalculPosition;
import carteFX.classFX.FXGraphics2D;
import carteFX.densite.Zoom;
import carteFX.facade.FacadeFx;
import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import previsionVents.ZonePrevision;
import previsionVents.ZoneSelectionne;

public class MapCanvas {
  public Canvas canvas;
  private MapContext map;
  private GraphicsContext gc;
  public boolean deplacer = false;
  private ZoneSelectionne zone;

  public MapCanvas(int width, int height) {
    canvas = new Canvas(width, height);
    gc = canvas.getGraphicsContext2D();
    initMap();
    drawMap(gc);
    initEvent();
    initPaintThread();
  }

  public Node getCanvas() {
    return canvas;
  }

  @SuppressWarnings("deprecation")
  private void initMap() {
    try {
      File file;
      file = new File("shape/simplified_land_polygons.shp");

      FileDataStore store = FileDataStoreFinder.getDataStore(file);
      SimpleFeatureSource featureSource = store.getFeatureSource();
      map = new DefaultMapContext();
      map.setTitle("Quickstart");
      Style style = SLD.createSimpleStyle(featureSource.getSchema());
      FeatureLayer layer = new FeatureLayer(featureSource, style);
      map.addLayer(layer);

      map.getViewport()
          .setScreenArea(new Rectangle((int) canvas.getWidth(), (int) canvas.getHeight()));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private boolean repaint = true;

  private void drawMap(GraphicsContext gc) {
    if (!repaint) {
      return;
    }
    repaint = false;
    StreamingRenderer draw = new StreamingRenderer();
    draw.setMapContent(map);
    FXGraphics2D graphics = new FXGraphics2D(gc);
    graphics.setBackground(java.awt.Color.WHITE);
    graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
    Rectangle rectangle = new Rectangle((int) canvas.getWidth(), (int) canvas.getHeight());
    drawZone(gc);
    draw.paint(graphics, rectangle, map.getViewport().getBounds());

  }

  private double baseDrageX;
  private double baseDrageY;

  /*
   * initial for mouse event
   */
  private void initEvent() {
    /*
     * setting the original coordinate
     */
    canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent e) {
        baseDrageX = e.getSceneX();
        baseDrageY = e.getSceneY();
        e.consume();
        if (!deplacer) {
          zone = new ZoneSelectionne(baseDrageX, baseDrageY, baseDrageX, baseDrageY);
        }
      }
    });
    /*
     * translate according to the mouse drag
     */
    canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        if (deplacer) {
          double difX = e.getSceneX() - baseDrageX;
          double difY = e.getSceneY() - baseDrageY;
          baseDrageX = e.getSceneX();
          baseDrageY = e.getSceneY();
          DirectPosition2D newPos = new DirectPosition2D(difX, difY);
          DirectPosition2D result = new DirectPosition2D();
          map.getViewport().getScreenToWorld().transform(newPos, result);
          ReferencedEnvelope env = new ReferencedEnvelope(map.getViewport().getBounds());
          env.translate(env.getMinimum(0) - result.x, env.getMaximum(1) - result.y);
          delzone();
          doSetDisplayArea(env);
          e.consume();
        } else {
          double baseX = baseDrageX;
          double baseY = baseDrageY - 100;

          double difX = e.getSceneX() - baseDrageX;
          double difY = e.getSceneY() - baseDrageY;
          if (difX < 0) {
            zone.setDebutX(baseX + difX);
            zone.setFinX(baseX);
          } else {
            zone.setDebutX(baseX);
            zone.setFinX(baseX + difX);
          }
          if (difY < 0) {
            zone.setDebutY(baseY + difY);
            zone.setFinY(baseY);
          } else {
            zone.setDebutY(baseY);
            zone.setFinY(baseY + difY);
          }
          repaint = true;
        }
      }
    });

    /*
     * enregistrer la zone
     */
    canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent e) {
        e.consume();
        if (!deplacer) {
          FacadeFx.getInstance().setZone(getZone(e));
        }
      }
    });

    /*
     * double clicks to restore to original map
     */
    canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        if (t.getClickCount() > 1) {
          doSetDisplayArea(map.getMaxBounds());
        }

        t.consume();
      }
    });
    /*
     * scroll for zoom in and out
     */
    canvas.addEventHandler(ScrollEvent.SCROLL, new EventHandler<ScrollEvent>() {

      @Override
      public void handle(ScrollEvent e) {
        Zoom z = new Zoom(MapCanvas.this);
        z.zoom(e.getDeltaY() * -1);
        e.consume();
      }
    });
  }

  private static final double PAINT_HZ = 50.0;

  private void initPaintThread() {
    ScheduledService<Boolean> svc = new ScheduledService<Boolean>() {
      protected Task<Boolean> createTask() {
        return new Task<Boolean>() {
          protected Boolean call() {
            Platform.runLater(new Runnable() {
              @Override
              public void run() {
                drawMap(gc);
              }
            });
            return true;
          }
        };
      }
    };
    svc.setPeriod(Duration.millis(1000.0 / PAINT_HZ));
    svc.start();
  }

  public void doSetDisplayArea(ReferencedEnvelope envelope) {
    map.getViewport().setBounds(envelope);
    repaint = true;
  }

  public void rafraichir() {
    ReferencedEnvelope env = new ReferencedEnvelope(map.getViewport().getBounds());
    doSetDisplayArea(env);
  }

  public MapContext getMap() {
    return map;
  }

  public ZoneSelectionne getzone() {
    return zone;
  }

  private ZonePrevision getZone(MouseEvent e) {
    // DirectPosition2D first = new DirectPosition2D(zone.getDebutX(),
    // zone.getDebutY());
    // DirectPosition2D second = new DirectPosition2D(zone.getFinX(),
    // zone.getFinY());
    // DirectPosition2D firstW = new DirectPosition2D(zone.getDebutX(),
    // zone.getDebutY());
    // DirectPosition2D secondW = new DirectPosition2D(zone.getFinX(),
    // zone.getFinY());
    // map.getViewport().getScreenToWorld().transform(first, firstW);
    // map.getViewport().getScreenToWorld().transform(second, secondW);
    //
    // Coordinate coord1 = new Coordinate(firstW.getX(), firstW.getY());
    // Coordinate coord2 = new Coordinate(secondW.getX(), secondW.getY());
    // System.out.println("Point 1 : " + coord1.x + " " + coord1.y);
    // System.out.println("Point 2 : " + coord2.x + " " + coord2.y);
    //
    // System.out.println("Bounds : " + map.getViewport().getBounds().getMinX()
    // + " "
    // + map.getViewport().getBounds().getMinY());
    // System.out.println("Bounds : " + map.getViewport().getBounds().getMaxX()
    // + " "
    // + map.getViewport().getBounds().getMaxY());
    //
    // double pas = FacadeFx.getInstance().getConfiguration().getPat();
    // int nombreX = coord1.x > coord2.x ? (int) ((coord1.x - coord2.x) / pas)
    // : (int) ((coord2.x - coord1.x) / pas);
    // System.out.println("Nombre x = " + nombreX);
    // System.out.println("coord1.x = " + coord1.x + " coord2.x = " + coord2.x);
    // int nombreY = coord1.y > coord2.y ? (int) ((coord1.y - coord2.y) / pas)
    // : (int) ((coord2.y - coord1.y) / pas);
    // ZonePrevision zonePrevision = new ZonePrevision(coord1.x, coord1.y, pas,
    // pas, nombreX, nombreY);
    // return zonePrevision;

    double difX = e.getSceneX() - baseDrageX;
    double difY = e.getSceneY() - baseDrageY;
    baseDrageX = e.getSceneX();
    baseDrageY = e.getSceneY();
    DirectPosition2D newPos = new DirectPosition2D(difX, difY);
    DirectPosition2D result = new DirectPosition2D();
    map.getViewport().getScreenToWorld().transform(newPos, result);
    ReferencedEnvelope env = new ReferencedEnvelope(map.getViewport().getBounds());
    env.translate(env.getMinimum(0) - result.x, env.getMaximum(1) - result.y);
    System.out.println("Min x : " + e.getSceneX() + " Min y : " + e.getSceneY());
    System.out.println("Min x : " + result.getX() + " Min y : " + result.getY());
    Coordinate coord = CalculPosition
        .convertEpsg3857to4326(new Coordinate(env.getMinX(), env.getMinY()));
    Coordinate coord2 = CalculPosition
        .convertEpsg3857to4326(new Coordinate(env.getMaxX(), env.getMaxY()));
    System.out.println("Min x : " + env.getMinX() / 100 + " Min y : " + env.getMaxY() / -10);
    System.out.println("Coord : " + coord.x + " " + coord.y);
    ZonePrevision zonePrevision = new ZonePrevision(coord.x, coord2.y, 1, 1, 100, 100);
    return zonePrevision;
  }

  public void delzone() {
    zone = null;
    ;
  }

  private void drawZone(GraphicsContext gc) {
    if (zone != null) {
      gc.setStroke(Color.BLUE);
      gc.setLineWidth(2);
      gc.strokeRect(zone.getDebutX(), zone.getDebutY(), zone.getFinX() - zone.getDebutX(),
          zone.getFinY() - zone.getDebutY());
      gc.setStroke(Color.BLACK);
    }
  }

}