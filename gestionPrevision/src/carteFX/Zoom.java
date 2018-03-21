package carteFX;

import org.geotools.geometry.jts.ReferencedEnvelope;

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
    canvas.doSetDisplayArea(envelope);
  }
}
