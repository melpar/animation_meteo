package carte;

import java.awt.event.ActionEvent;

import org.geotools.map.MapContext;
import org.geotools.swing.action.SafeAction;

import previsionVents.FacadePrevisionVents;
import previsionVents.ListePrevision;
import previsionVents.Prevision;

public class AfficherFleches extends SafeAction {
  private static AfficherFleches instance;
  MapContext map;
  private int pasX;
  private int pasY;
  private double taille;
  private int indexPrevision;

  private AfficherFleches(MapContext m) {
    super("Afficher");
    this.map = m;
    pasX = 1;
    pasY = 1;
    indexPrevision = 0;
  }

  public static AfficherFleches getInstance(MapContext m) {
    if (instance == null) {
      instance = new AfficherFleches(m);
    }
    return instance;
  }

  public static AfficherFleches getInstance() {
    return instance;
  }

  public void action(ActionEvent e) throws Throwable {
    ListePrevision previsions = FacadePrevisionVents.getFacadePrevisionVents().getPrevisions();
    if (previsions != null) {
      Dessiner dessiner = new Dessiner(map);
      Prevision prev = previsions.getListePrevision().get(indexPrevision);// TODO Valeur a modifier

      dessiner.ajouterCalque(prev, taille, pasX, pasY);
    }

  }

  public void setPas(int pasX, int pasY) {
    this.pasX = pasX;
    this.pasY = pasY;
  }

  public void setTaille(double t) {
    this.taille = t;
  }

  public void setPrevision(int index) {
    if (index >= 0 & index < FacadePrevisionVents.getFacadePrevisionVents().getPrevisions()
        .getListePrevision().size()) {
      indexPrevision = index;
    }
  }
}
