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
import org.geotools.swing.MapPane;
import org.geotools.swing.event.MapMouseEvent;
import org.geotools.swing.locale.LocaleUtils;
import org.geotools.swing.tool.ZoomInTool;

import previsionVents.FacadePrevisionVents;

public class Zoom extends ZoomInTool {
  /** Tool name. */
  public static final String TOOL_NAME = LocaleUtils.getValue("CursorTool", "ZoomIn");

  /** Tool tip text. */
  public static final String TOOL_TIP = LocaleUtils.getValue("CursorTool", "ZoomInTooltip");

  /** Cursor. */
  public static final String CURSOR_IMAGE = "/org/geotools/swing/icons/mActionZoomOut.png";

  /** Cursor hotspot coordinates. */
  public static final Point CURSOR_HOTSPOT = new Point(14, 9);

  /** Icon for the control. */
  public static final String ICON_IMAGE = "/org/geotools/swing/icons/mActionZoomIn.png";

  private Cursor cursor;

  private final Point startPosDevice;
  private final Point2D startPosWorld;
  private boolean dragged;
  private Dessiner dessiner;

  /**
   * Constructor.
   */
  public Zoom(Dessiner dessiner) {
    Toolkit tk = Toolkit.getDefaultToolkit();
    ImageIcon imgIcon = new ImageIcon(getClass().getResource(CURSOR_IMAGE));
    cursor = tk.createCustomCursor(imgIcon.getImage(), CURSOR_HOTSPOT, TOOL_NAME);
    this.dessiner = dessiner;
    startPosDevice = new Point();
    startPosWorld = new DirectPosition2D();
    dragged = false;
  }

  /**
   * Zoom in by the currently set increment, with the map centred at the
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
    double newScale = scale * zoom;

    double newX = mapPos.getX() - 0.5d * paneArea.getWidth() / newScale;
    double newY = mapPos.getY() + 0.5d * paneArea.getHeight() / newScale;
    DirectPosition2D corner = new DirectPosition2D(newX, newY);

    Envelope2D newMapArea = new Envelope2D();
    newMapArea.setFrameFromCenter(mapPos, corner);
    getMapPane().setDisplayArea(newMapArea);
  }

  /**
   * Records the map position of the mouse event in case this button press is
   * the beginning of a mouse drag.
   *
   * @param ev
   *          the mouse event
   */
  @Override
  public void onMousePressed(MapMouseEvent ev) {
    startPosDevice.setLocation(ev.getPoint());
    startPosWorld.setLocation(ev.getWorldPos());
  }

  /**
   * Records that the mouse is being dragged.
   *
   * @param ev
   *          the mouse event
   */
  @Override
  public void onMouseDragged(MapMouseEvent ev) {
    dragged = true;
  }

  /**
   * If the mouse was dragged, determines the bounds of the box that the user
   * defined and passes this to the mapPane's {@code setDisplayArea} method.
   *
   * @param ev
   *          the mouse event
   */
  @Override
  public void onMouseReleased(MapMouseEvent ev) {
    if (dragged && !ev.getPoint().equals(startPosDevice)) {
      Envelope2D env = new Envelope2D();
      env.setFrameFromDiagonal(startPosWorld, ev.getWorldPos());

      System.out.println("x = " + startPosDevice.getX() + " | y = " + startPosDevice.getY());
      System.out.println("x = " + ev.getWorldPos().getX() + " | y = " + ev.getWorldPos().getY());
      dragged = false;
      getMapPane().setDisplayArea(env);

      FacadePrevisionVents facade = FacadePrevisionVents.getFacadePrevisionVents();
      AfficherFleches afficherFleches = AfficherFleches.getInstance();
      System.out.println("prev = " + facade.getPrevisions());
      if (facade.getPrevisions() != null) {
        System.out.println("on met à jour les fleches");
        double pasX = facade.getPrevisions().getZonePrevision().getPasX();
        double pasXDouble = (startPosDevice.getX() - ev.getWorldPos().getX()) / pasX;
        if (pasXDouble < 0) {
          pasXDouble = pasXDouble * -1;
        }
        while (pasXDouble > 100) {
          pasXDouble /= 10;
        }
        double pasY = facade.getPrevisions().getZonePrevision().getPasY();
        double pasYDouble = (startPosDevice.getY() - ev.getWorldPos().getY()) / pasY;
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
        } catch (Throwable e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
    System.out.println("released");
  }

  public static String realiserZoom(MapPane pan, double x1, double x2, double y1, double y2,
      double pasX, double pasY) {
    Envelope2D env = new Envelope2D();
    Point2D startPosWorld = new Point2D.Double(x1, y1);
    Point2D stopPosWorld = new Point2D.Double(x2, y2);
    env.setFrameFromDiagonal(startPosWorld, stopPosWorld);
    pan.setDisplayArea(env);
    System.out.println("Zoom : " + x1 + " " + x2 + " " + (x1 - x2));
    System.out.println("Zoom : " + y1 + " " + y2 + " " + (y2 - y1));
    double nouveauPasX = (x1 - x2) / pasX;
    while (nouveauPasX > 100) {
      nouveauPasX /= 100;
    }
    System.out.println("nouveau : " + nouveauPasX);
    double nouveauPasY = (y2 - y1) / pasY;
    while (nouveauPasY > 100) {
      nouveauPasY /= 100;
    }
    System.out.println("nouveau : " + nouveauPasY);
    return nouveauPasX + "/" + nouveauPasY;
  }

  /**
   * Get the mouse cursor for this tool.
   */
  @Override
  public Cursor getCursor() {
    return cursor;
  }

  /**
   * Returns true to indicate that this tool draws a box on the map display when
   * the mouse is being dragged to show the zoom-in area.
   */
  @Override
  public boolean drawDragBox() {
    return true;
  }
}
