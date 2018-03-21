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
    AffichageFleches.miseAJour(envelope);
    canvas.doSetDisplayArea(envelope);
  }
}
