package previsionVents;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FacadePrevisionVents {
  private static FacadePrevisionVents instance;
  private ListePrevision previsions;

  /**
   * Permet de retourner l'instance de facade (singleton).
   * 
   * @return instance courante
   */
  public static FacadePrevisionVents getFacadePrevisionVents() {
    if (instance == null) {
      instance = new FacadePrevisionVents();
    }
    return instance;
  }

  private FacadePrevisionVents() {
  }

//  List<DonneeVent> getDonneesZone(double minX, double minY, double maxX, double maxY,
//      Date datePrevision) {
//    List<DonneeVent> donnees = new ArrayList<DonneeVent>();
//    Prevision prevision = this.previsions.getUnePrevision(datePrevision);
//    for (double latitude = minX; latitude < maxX; latitude++) {
//      for (double longitude = minY; longitude < maxY; longitude++) {
//        int positionX = this.previsions.getZonePrevision().getPositionX(latitude);
//        int positionY = this.previsions.getZonePrevision().getPositionY(longitude);
//        if (positionX > 0 && positionX < this.previsions.getZonePrevision().getNombreX()) {
//          if (positionY > 0 && positionY < this.previsions.getZonePrevision().getNombreY()) {
//            donnees.add(prevision.getDonneeVent(positionX, positionY));
//            System.out.println("getDonnees");
//          }
//        }
//
//      }
//    }
//    return donnees;
//  }

  public ListePrevision getPrevisions() {
    return previsions;
  }

  public void setPrevisions(ListePrevision previsions) {
    this.previsions = previsions;
  }
}
