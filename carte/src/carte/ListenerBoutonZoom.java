package carte;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.geotools.swing.MapPane;
import org.geotools.swing.tool.AbstractZoomTool;
import org.geotools.swing.tool.ZoomInTool;

public class ListenerBoutonZoom implements MouseListener {
  private MapPane monPan;
  private Dessiner dessiner;

  ListenerBoutonZoom(MapPane pan, Dessiner dessin) {
    this.monPan = pan;
    this.dessiner = dessin;
  }

  @Override
  public void mouseClicked(MouseEvent arg0) {
    ZoomInTool abs = new Zoom(dessiner);
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
