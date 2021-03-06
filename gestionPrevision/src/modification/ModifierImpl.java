package modification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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
    listRetour = new ArrayList<ListePrevision>();
    memorise();
  }

  public void modifierCoefficientVent(ZonePrevision zonePrevision, Calendar date,
      float coefficient) {
    VisiteurCoefficient modifier = new VisiteurCoefficient(zonePrevision, date, coefficient);
    FacadePrevisionVents.getFacadePrevisionVents().getPrevisions().applique(modifier);
    memorise();
  }

  public void modifierContrasteProgressifVent(ZonePrevision zonePrevision, Calendar date,
      float coefficient, float seuil) {
    VisiteurContrasteProgressif modifier = new VisiteurContrasteProgressif(zonePrevision, date,
        coefficient, seuil);
    FacadePrevisionVents.getFacadePrevisionVents().getPrevisions().applique(modifier);
    memorise();
  }

  public void modifierContrasteLineaireVent(ZonePrevision zonePrevision, Calendar date,
      float coefficient, float seuil) {
    VisiteurContrasteLineaire modifier = new VisiteurContrasteLineaire(zonePrevision, date,
        coefficient, seuil);
    FacadePrevisionVents.getFacadePrevisionVents().getPrevisions().applique(modifier);
    memorise();
  }

  public double getMoyenneVitesseVent(ZonePrevision zonePrevision, Calendar date) {
    VisiteurMoyenne moyenne = new VisiteurMoyenne(zonePrevision, date);
    FacadePrevisionVents.getFacadePrevisionVents().getPrevisions().applique(moyenne);
    return moyenne.getMoyenneVitesse();
  }

  public double getMoyenneDirectionVent(ZonePrevision zonePrevision, Calendar date) {
    VisiteurMoyenne moyenne = new VisiteurMoyenne(zonePrevision, date);
    FacadePrevisionVents.getFacadePrevisionVents().getPrevisions().applique(moyenne);
    return moyenne.getMoyenneDirection();
  }

  private void memorise() {
    if (FacadePrevisionVents.getFacadePrevisionVents().getPrevisions() != null) {
      VisisteurMemoire memoire = new VisisteurMemoire();
      FacadePrevisionVents.getFacadePrevisionVents().getPrevisions().applique(memoire);
      if (retourN != 0) {
        replaceMemorise(retourN);
      }
      addMemorise(memoire.getSauvegarde());
    }
  }

  private void addMemorise(ListePrevision prevision) {
    if (listRetour.size() >= RETOUR_MAX) {
      listRetour.remove(0);
    }
    listRetour.add(prevision);
  }

  private void replaceMemorise(int index) {
    index = listRetour.size() - 1 - index;
    retourN = 0;
    while (index < listRetour.size() - 1) {
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

  public boolean isArriere() {
    // return retourN < listRetour.size();
    return false;
  }

  public boolean isAvant() {
    // return retourN > 0;
    return false;
  }
}
