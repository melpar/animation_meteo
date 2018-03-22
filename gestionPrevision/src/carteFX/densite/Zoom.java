package carteFX.densite;

import org.geotools.geometry.jts.ReferencedEnvelope;

import carteFX.MapCanvas;

public class Zoom {
  private MapCanvas canvas;
  private static double deltaZoom;

  public Zoom(MapCanvas can) {
    this.canvas = can;
  }

  public void zoom(double delta) {
    if ((!(delta >= 0 && delta > 0)) || (!(delta <= -2000 && delta < 0))) {
      ReferencedEnvelope envelope = canvas.getMap().getViewport().getBounds();
      double percent = delta / canvas.canvas.getWidth();
      double width = envelope.getWidth();
      double height = envelope.getHeight();
      double deltaW = width * percent;
      double deltaH = height * percent;
      canvas.delzone();
      envelope.expandBy(deltaW, deltaH);
      AffichageFleches.miseAJour(envelope);
      canvas.doSetDisplayArea(envelope);
      deltaZoom += delta;
      System.out.println("Delta : " + delta);
    }
  }
}
