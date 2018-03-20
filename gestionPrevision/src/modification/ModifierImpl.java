package modification;

import java.util.ArrayList;
import java.util.List;

import previsionVents.FacadePrevisionVents;
import previsionVents.ZonePrevision;
import visiteur.Visiteur;

public class ModifierImpl {

  private static final int RETOUR_MAX = 5;
  private int retourN;
  private int nbRetourArriere;
  private int nbRetourAvant;

  private List<VisisteurMemoire> listRetour;

  public ModifierImpl() {
    retourN = 0;
    nbRetourArriere = 0;
    nbRetourAvant = 0;
    listRetour = new ArrayList<VisisteurMemoire>();
    memorise();
  }

  public void modifierCoefficientVent(ZonePrevision zonePrevision, float coefficient) {
    VisiteurCoefficient modifier = new VisiteurCoefficient(zonePrevision, coefficient);
    FacadePrevisionVents.getFacadePrevisionVents().getPrevisions().applique(modifier);
    memorise();
  }

  public void modifierContrasteProgressifVent(ZonePrevision zonePrevision, float coefficient,
      float seuil) {
    VisiteurContrasteProgressif modifier = new VisiteurContrasteProgressif(zonePrevision,
        coefficient, seuil);
    FacadePrevisionVents.getFacadePrevisionVents().getPrevisions().applique(modifier);
    memorise();
  }

  public void modifierContrasteLineaireVent(ZonePrevision zonePrevision, float coefficient,
      float seuil) {
    VisiteurContrasteLineaire modifier = new VisiteurContrasteLineaire(zonePrevision, coefficient,
        seuil);
    FacadePrevisionVents.getFacadePrevisionVents().getPrevisions().applique(modifier);
    memorise();
  }
  
  public double getMoyenneVitesseVent(ZonePrevision zonePrevision) {
    VisiteurMoyenne moyenne = new VisiteurMoyenne(zonePrevision);
    FacadePrevisionVents.getFacadePrevisionVents().getPrevisions().applique(moyenne);
    return moyenne.getMoyenneVitesse();
  }
  
  public double getMoyenneDirectionVent(ZonePrevision zonePrevision) {
    VisiteurMoyenne moyenne = new VisiteurMoyenne(zonePrevision);
    FacadePrevisionVents.getFacadePrevisionVents().getPrevisions().applique(moyenne);
    return moyenne.getMoyenneDirection();
  }

  private void memorise() {
    ZonePrevision zonePrevision = FacadePrevisionVents.getFacadePrevisionVents().getPrevisions().getZonePrevision();
    VisisteurMemoire memoire = new VisisteurMemoire(zonePrevision);
    if (listRetour.size() < RETOUR_MAX) {
      listRetour.add(memoire);
    } else {
      listRetour.set(retourN, memoire);
    }
    retourN = (retourN + 1) % RETOUR_MAX;
    nbRetourArriere = addAction(nbRetourArriere);
    nbRetourAvant=0;
  }
  
  private int addAction(int action) {
    if (action < RETOUR_MAX) {
      return action + 1;
    } else {
      return action;
    }
  }
  
  private int delAction(int action) {
    if (action > 0) {
      return action - 1;
    } else {
      return action;
    }
  }

  public boolean restaureArriere() {
    int index = (retourN - nbRetourArriere + nbRetourAvant) % RETOUR_MAX;
    if (nbRetourArriere > 0 | index < listRetour.size()) {
      VisiteurRestauration restauration = new VisiteurRestauration(listRetour.get(index));
      FacadePrevisionVents.getFacadePrevisionVents().getPrevisions().applique(restauration);
      System.out.println(index);
      nbRetourArriere=delAction(nbRetourArriere);
      nbRetourAvant=addAction(nbRetourAvant);

      return true;
    } else {
      return false;
    }
  }

  public boolean restaureAvant() {
    int index = (retourN - nbRetourArriere + nbRetourAvant) % RETOUR_MAX;
    if (nbRetourAvant > 0 | index < listRetour.size()) {
      VisiteurRestauration restauration = new VisiteurRestauration(listRetour.get(index));
      FacadePrevisionVents.getFacadePrevisionVents().getPrevisions().applique(restauration);
      System.out.println(index);
      nbRetourAvant=delAction(nbRetourAvant);
      return true;
    } else {
      return false;
    }
  }

}
