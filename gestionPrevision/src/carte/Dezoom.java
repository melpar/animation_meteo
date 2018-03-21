package carte;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Point2D;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import org.geotools.geometry.DirectPosition2D;
import org.geotools.geometry.Envelope2D;
import org.geotools.swing.event.MapMouseEvent;
import org.geotools.swing.locale.LocaleUtils;
import org.geotools.swing.tool.ZoomOutTool;

import previsionVents.FacadePrevisionVents;

public class Dezoom extends ZoomOutTool {

  /** Cursor hotspot coordinates. */
  public static final Point CURSOR_HOTSPOT = new Point(14, 9);

  /** Cursor. */
  public static final String CURSOR_IMAGE = "/org/geotools/swing/icons/mActionZoomOut.png";

  /** Icon for the control. */
  public static final String ICON_IMAGE = "/org/geotools/swing/icons/mActionZoomOut.png";

  /** Tool name. */
  public static final String TOOL_NAME = LocaleUtils.getValue("CursorTool", "ZoomOut");

  /** Tool tip text. */
  public static final String TOOL_TIP = LocaleUtils.getValue("CursorTool", "ZoomOutTooltip");

  private Cursor cursor;

  private final Point startPosDevice;
  private final Point2D startPosWorld;
  private boolean dragged;
  private Dessiner dessiner;

  public Dezoom(Dessiner dessiner) {
    Toolkit tk = Toolkit.getDefaultToolkit();
    ImageIcon imgIcon = new ImageIcon(getClass().getResource(CURSOR_IMAGE));
    cursor = tk.createCustomCursor(imgIcon.getImage(), CURSOR_HOTSPOT, TOOL_NAME);
    this.dessiner = dessiner;
    startPosDevice = new Point();
    startPosWorld = new DirectPosition2D();
    dragged = false;
  }

  /**
   * Zoom out by the currently set increment, with the map centred at the
   * location (in world coords) of the mouse click.
   * 
   * @param e
   *          map mapPane mouse event
   */
  @Override
  public void onMouseClicked(MapMouseEvent e) {
    Rectangle paneArea = ((JComponent) getMapPane()).getVisibleRect();
    DirectPosition2D mapPos = e.getWorldPos();

    double scale = getMapPane().getWorldToScreenTransform().getScaleX();
    double newScale = scale / zoom;

    double newX = mapPos.getX() - 0.5d * paneArea.getWidth() / newScale;
    double newY = mapPos.getY() + 0.5d * paneArea.getHeight() / newScale;
    DirectPosition2D corner = new DirectPosition2D(newX, newY);

    Envelope2D newMapArea = new Envelope2D();
    newMapArea.setFrameFromCenter(mapPos, corner);
    getMapPane().setDisplayArea(newMapArea);
    FacadePrevisionVents facade = FacadePrevisionVents.getFacadePrevisionVents();
    AfficherFleches afficherFleches = AfficherFleches.getInstance();
    System.out.println("prev = " + facade.getPrevisions());
    if (facade.getPrevisions() != null) {
      System.out.println("on met à jour les fleches");
      double pasX = facade.getPrevisions().getZonePrevision().getPasX();
      double pasXDouble = (startPosDevice.getX() - newX) / pasX;
      if (pasXDouble < 0) {
        pasXDouble = pasXDouble * -1;
      }
      while (pasXDouble > 100) {
        pasXDouble /= 10;
      }
      double pasY = facade.getPrevisions().getZonePrevision().getPasY();
      double pasYDouble = (startPosDevice.getY() - newY) / pasY;
      if (pasYDouble < 0) {
        pasYDouble = pasYDouble * -1;
      }
      while (pasYDouble > 100) {
        pasYDouble /= 10;
      }
      afficherFleches.setPas((int) pasXDouble, (int) pasYDouble);
      double taille = facade.getPrevisions().getZonePrevision().getPasX() * (pasXDouble - 5);
      afficherFleches.setTaille(taille);

      try {
        afficherFleches.action(null);
      } catch (Throwable error) {
        // TODO Auto-generated catch block
        error.printStackTrace();
      }
    }
  }
}
