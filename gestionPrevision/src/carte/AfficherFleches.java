package carte;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import org.geotools.map.MapContext;
import org.geotools.swing.action.SafeAction;

import previsionVents.ListePrevision;
import previsionVents.Prevision;

public class AfficherFleches extends SafeAction {
  MapContext map;
  ListePrevision previsions;

  public AfficherFleches(MapContext m) {
    super("Afficher");
    this.map = m;
  }

  public void action(ActionEvent e) throws Throwable {
    if (this.previsions != null) {
      Dessiner dessiner = new Dessiner(map);
      List<InformationsVents> vents = new ArrayList<>();
      Prevision prev = this.previsions.getListePrevision().get(0);
      for (int i = 0; i < prev.getListeDonneVent().length; i++) {
        for (int j = 0; j < prev.getListeDonneVent()[i].length; j++) {
          System.out.println("action");
          InformationsVents v = new InformationsVents();
          v.setPositionY(this.previsions.getZonePrevision().getLatitudePosition(i));
          v.setPositionX(this.previsions.getZonePrevision().getLongitudePosition(j));
          v.setDirection(prev.getDonneeVent(i, j).getOrientationVent());
          vents.add(v);
        }

      }

      dessiner.ajouterCalque(vents);
    }

  }

  public ListePrevision getPrevisions() {
    return previsions;
  }

  public void setPrevisions(ListePrevision previsions) {
    this.previsions = previsions;
  }
}
