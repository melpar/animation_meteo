package carte;


import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.layout.Pane;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.map.FeatureLayer;
import org.geotools.map.MapContent;
import org.geotools.renderer.lite.StreamingRenderer;
import org.geotools.styling.SLD;

import org.geotools.styling.Style;
import org.jfree.fx.FXGraphics2D;


public class MapCanvas {

  private final Canvas canvas;
  private MapContent map;
  private GraphicsContext gc;
   
  public MapCanvas(double width, double height) { 
    canvas = new Canvas(width,height);
    gc = canvas.getGraphicsContext2D();
    initMap(); 
    drawMap(gc); 
  }
   
  public Node getCanvas() {
    return canvas;
  }
  
  private void initMap() {
    try {
      FileDataStore store = FileDataStoreFinder.getDataStore(
          this.getClass().getResource
          ("shape/simplified_land_polygons.shp"));
      SimpleFeatureSource featureSource = store.getFeatureSource();
      map = new MapContent();
      map.setTitle("Animation météo");
      Style style = SLD.createSimpleStyle(featureSource.getSchema());
      FeatureLayer layer = new FeatureLayer(featureSource, style);
      map.addLayer(layer);
      map.getViewport().setScreenArea(new Rectangle((int) canvas.getWidth(), (int) canvas.getHeight()));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  private void drawMap(GraphicsContext gc) {
    StreamingRenderer draw = new StreamingRenderer();
    draw.setMapContent(map);
    FXGraphics2D graphics = new FXGraphics2D(gc);
    graphics.setBackground(java.awt.Color.WHITE);
    Rectangle rectangle = new Rectangle((int) canvas.getWidth(), (int) canvas.getHeight());
    draw.paint(graphics, rectangle, map.getViewport().getBounds());
  }
  

}