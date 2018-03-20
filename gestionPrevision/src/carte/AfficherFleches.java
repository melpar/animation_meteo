package carte;

import java.awt.event.ActionEvent;

import org.geotools.map.MapContext;
import org.geotools.swing.action.SafeAction;

import previsionVents.FacadePrevisionVents;
import previsionVents.ListePrevision;
import previsionVents.Prevision;

public class AfficherFleches extends SafeAction {
  MapContext map;
  private int pas;

  public AfficherFleches(MapContext m) {
    super("Afficher");
    this.map = m;
    pas = 1;
  }

  public void action(ActionEvent e) throws Throwable {
    ListePrevision previsions = FacadePrevisionVents.getFacadePrevisionVents().getPrevisions();
    if (previsions != null) {
      Dessiner dessiner = new Dessiner(map);
      Prevision prev = previsions.getListePrevision().get(0);
      Double taille = 0.5;

      dessiner.ajouterCalque(prev, taille, pas);
    }

  }

  public int getPas() {
    return pas;
  }

  public void setPas(int pas) {
    this.pas = pas;
  }
}
