package carteFX.densite;

import org.geotools.geometry.jts.ReferencedEnvelope;

import carteFX.MapCanvas;

public class Zoom {
  private MapCanvas canvas;

  public Zoom(MapCanvas can) {
    this.canvas = can;
  }

  public void zoom(double delta) {
    ReferencedEnvelope envelope = canvas.getMap().getViewport().getBounds();
    double percent = delta / canvas.canvas.getWidth();
    double width = envelope.getWidth();
    double height = envelope.getHeight();
    double deltaW = width * percent;
    double deltaH = height * percent;
    envelope.expandBy(deltaW, deltaH);
    System.out
        .println("Nouvelles coordonnees : (min) " + envelope.getMinX() + " " + envelope.getMinX());
    System.out
        .println("Nouvelles coordonnees : (max) " + envelope.getMaxX() + " " + envelope.getMaxX());

    canvas.doSetDisplayArea(envelope);
  }
}
