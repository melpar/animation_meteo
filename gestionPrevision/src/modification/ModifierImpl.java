package modification;

import java.util.ArrayList;
import java.util.List;

import previsionVents.FacadePrevisionVents;
import previsionVents.ZonePrevision;

public class ModifierImpl {

  private static final int RETOUR_MAX = 5;
  private int retourN;
  private int nbRetour;
  private List<VisisteurMemoire> listRetour;

  public ModifierImpl() {
    retourN = 0;
    nbRetour = 0;
    listRetour = new ArrayList<VisisteurMemoire>();
  }

  public void modifierCoefficientVent(ZonePrevision zonePrevision, float coefficient) {
    VisiteurCoefficient modifier = new VisiteurCoefficient(zonePrevision, coefficient);
    FacadePrevisionVents.getFacadePrevisionVents().getPrevisions().applique(modifier);
  }

  public void modifierContrasteProgressifVent(ZonePrevision zonePrevision, float coefficient,
      float seuil) {
    VisiteurContrasteProgressif modifier = new VisiteurContrasteProgressif(zonePrevision,
        coefficient, seuil);
    FacadePrevisionVents.getFacadePrevisionVents().getPrevisions().applique(modifier);
  }

  public void modifierContrasteLineaireVent(ZonePrevision zonePrevision, float coefficient,
      float seuil) {
    VisiteurContrasteLineaire modifier = new VisiteurContrasteLineaire(zonePrevision, coefficient,
        seuil);
    FacadePrevisionVents.getFacadePrevisionVents().getPrevisions().applique(modifier);
  }

  private int memorise() {
    if (nbRetour < RETOUR_MAX) {
      nbRetour = nbRetour + 1;
    }

    retourN = (retourN + 1) % RETOUR_MAX;
    return retourN;
  }

  public boolean restaureArriere() {
    return false;
  }

  public boolean restaureAvant() {
    return false;
  }

}
