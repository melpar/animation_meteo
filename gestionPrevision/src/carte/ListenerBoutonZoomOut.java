package carte;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.geotools.swing.MapPane;
import org.geotools.swing.tool.AbstractZoomTool;
import org.geotools.swing.tool.ZoomOutTool;

public class ListenerBoutonZoomOut implements MouseListener {
  private MapPane monPan;
  private Dessiner dessiner;

  ListenerBoutonZoomOut(MapPane pan, Dessiner dessin) {
    this.monPan = pan;
    this.dessiner = dessin;
  }

  @Override
  public void mouseClicked(MouseEvent arg0) {
    ZoomOutTool abs = new Dezoom(dessiner);
    monPan.setCursorTool(abs);
    abs.setZoom(AbstractZoomTool.DEFAULT_ZOOM_FACTOR);

  }

  @Override
  public void mouseEntered(MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseExited(MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mousePressed(MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseReleased(MouseEvent arg0) {
    // TODO Auto-generated method stub

  }
}
