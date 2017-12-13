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

  List<DonneeVent> getDonneesZone(int minX, int minY, int maxX, int maxY, Date datePrevision) {
    List<DonneeVent> donnees = new ArrayList<DonneeVent>();
    Prevision prevision = this.previsions.getUnePrevision(datePrevision);
    for (int indiceX = minX; indiceX < maxX; indiceX++) {
      for (int indiceY = minY; indiceY < maxY; indiceY++) {
        donnees.add(prevision.getDonneeVent(indiceX, indiceY));
      }
    }
    return donnees;
  }

  public ListePrevision getPrevisions() {
    return previsions;
  }

  public void setPrevisions(ListePrevision previsions) {
    this.previsions = previsions;
  }
}
