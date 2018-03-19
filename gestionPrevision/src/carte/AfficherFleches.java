package carte;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import org.geotools.map.MapContext;
import org.geotools.swing.action.SafeAction;

import modification.VisiteurMoyenne;
import previsionVents.ListePrevision;
import previsionVents.Prevision;
import previsionVents.ZonePrevision;

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
      for (int i = 0; i < prev.getListeDonneVent().length; i += 20) {
        for (int j = 0; j < prev.getListeDonneVent()[i].length; j += 20) {
          ZonePrevision zone = new ZonePrevision(
              previsions.getZonePrevision().getLatitudePosition(i),
              previsions.getZonePrevision().getLongitudePosition(j),
              previsions.getZonePrevision().getPasX(),
              previsions.getZonePrevision().getPasY(), 1, 1);
          VisiteurMoyenne visiteur = new VisiteurMoyenne(zone);
          previsions.applique(visiteur);
          InformationsVents v = new InformationsVents();
          v.setPositionY(this.previsions.getZonePrevision().getLatitudePosition(i));
          v.setPositionX(this.previsions.getZonePrevision().getLongitudePosition(j));
          v.setDirection(visiteur.getMoyenneDirection());
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
