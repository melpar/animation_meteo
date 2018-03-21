package modification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import previsionVents.FacadePrevisionVents;
import previsionVents.ListePrevision;
import previsionVents.ZonePrevision;

public class ModifierImpl {

  private static final int RETOUR_MAX = 5;
  private int retourN;
  // private int nbRetourArriere;
  // private int nbRetourAvant;
  // private int memoMax;
  // private int memoEtat;

  private List<ListePrevision> listRetour;

  public ModifierImpl() {
    retourN = 0;
    // nbRetourArriere = 0;
    // nbRetourAvant = 0;
    // memoMax = 0;
    // memoEtat = 0;
    listRetour = new ArrayList<ListePrevision>();
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

  // private void memorise() {
  // VisisteurMemoire memoire = new VisisteurMemoire();
  // FacadePrevisionVents.getFacadePrevisionVents().getPrevisions().applique(memoire);
  // if (listRetour.size() < RETOUR_MAX) {
  // listRetour.add(memoire.getSauvegarde());
  // } else {
  // listRetour.set(retourN, memoire.getSauvegarde());
  // }
  // retourN = (retourN + 1) % RETOUR_MAX;
  // memoMax = addAction(memoMax);
  // // nbRetourArriere = addAction(nbRetourArriere);
  // // nbRetourAvant = 0;
  // }

  private void memorise() {
    VisisteurMemoire memoire = new VisisteurMemoire();
    FacadePrevisionVents.getFacadePrevisionVents().getPrevisions().applique(memoire);
    if (retourN != 0) {
      replaceMemorise(retourN);
      retourN = 0;
    }
    addMemorise(memoire.getSauvegarde());
  }

  private void addMemorise(ListePrevision prevision) {
    if (listRetour.size() >= RETOUR_MAX) {
      listRetour.remove(0);
    }
    listRetour.add(prevision);
  }

  private void replaceMemorise(int index) {
    index = listRetour.size() - 1 - index;
    while (index > listRetour.size() - 1) {
      listRetour.remove(listRetour.size() - 1);
    }
  }

  private ListePrevision getSave() throws IOException {
    int index = listRetour.size() - 1 - retourN;
    if (listRetour.size() == 0 | index < 0 | index > listRetour.size()) {
      throw new IOException();
    }
    return listRetour.get(index);
  }

  private int addAction(int action) throws IOException {
    if (action < listRetour.size() - 1) {
      return action + 1;
    } else {
      throw new IOException();
    }
  }

  private int delAction(int action) throws IOException {
    if (action > 0) {
      return action - 1;
    } else {
      throw new IOException();
    }
  }

  // private int inverse(int action) {
  // return listRetour.size() - action;
  // }

  // public boolean restaureArriere() {
  // int index = (retourN - memoEtat - 1) % RETOUR_MAX;
  // // if (nbRetourArriere > 0 & nbRetourArriere < RETOUR_MAX) {
  // if (memoMax > 0 & memoEtat < memoMax) {
  // FacadePrevisionVents.getFacadePrevisionVents().setPrevisions(listRetour.get(index));
  // memoMax = delAction(memoMax);
  // memoEtat = addAction(memoEtat);
  // // nbRetourArriere = addAction(nbRetourArriere);
  // // nbRetourAvant = nbRetourArriere;
  // return true;
  // } else {
  // return false;
  // }
  // }

  public boolean restaureArriere() {

    try {
      retourN = addAction(retourN);
      FacadePrevisionVents.getFacadePrevisionVents().setPrevisions(getSave());
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  public boolean restaureAvant() {

    try {
      retourN = delAction(retourN);
      FacadePrevisionVents.getFacadePrevisionVents().setPrevisions(getSave());
      return true;
    } catch (IOException e) {
      return false;
    }
  }

}
