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
import org.geotools.swing.tool.ZoomInTool;

public class Zoom extends ZoomInTool {
  /** Tool name. */
  public static final String TOOL_NAME = LocaleUtils.getValue("CursorTool", "ZoomIn");

  /** Tool tip text. */
  public static final String TOOL_TIP = LocaleUtils.getValue("CursorTool", "ZoomInTooltip");

  /** Cursor. */
  public static final String CURSOR_IMAGE = "/org/geotools/swing/icons/mActionZoomIn.png";

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
   * Zoom in by the currently set increment, with the map centred at the location
   * (in world coords) of the mouse click.
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

    DirectPosition2D corner = new DirectPosition2D(
        mapPos.getX() - 0.5d * paneArea.getWidth() / newScale,
        mapPos.getY() + 0.5d * paneArea.getHeight() / newScale);

    Envelope2D newMapArea = new Envelope2D();
    newMapArea.setFrameFromCenter(mapPos, corner);
    getMapPane().setDisplayArea(newMapArea);
  }

  /**
   * Records the map position of the mouse event in case this button press is the
   * beginning of a mouse drag.
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
      dragged = false;
      getMapPane().setDisplayArea(env);

    }
    // List<InformationsVents> vents = new ArrayList<>();
    //
    // for (int i = 0; i < 10000000; i += 100000) {
    // InformationsVents v = new InformationsVents();
    // v.setPositionX(i);
    // v.setPositionY(i);
    // v.setDirection(7 * Math.PI / 4);
    // vents.add(v);
    // }
    //
    // dessiner.ajouterCalque(vents);
    System.out.println("released");
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
