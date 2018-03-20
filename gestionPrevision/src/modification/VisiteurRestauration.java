package modification;

import java.util.Calendar;

import previsionVents.DonneeVent;
import previsionVents.ListePrevision;
import previsionVents.Prevision;
import previsionVents.ZonePrevision;
import visiteur.ElementVisitable;
import visiteur.Visiteur;

public class VisiteurRestauration implements Visiteur {

  ListePrevision memoire;

  public VisiteurRestauration(VisisteurMemoire memoire) {
    this.memoire = memoire.getSauvegarde();
    // TODO Auto-generated constructor stub
  }

  @Override
  public void agitSur(ElementVisitable element) {
    // TODO Auto-generated method stub

  }

  @Override
  public void agitSur(DonneeVent element) {
    // TODO Auto-generated method stub

  }

  @Override
  public void agitSur(ListePrevision element) {
    for (Prevision prevision : element.getListePrevision()) {
      prevision.applique(this);
    }
  }

  @Override
  public void agitSur(Prevision element) {
    Calendar date = element.getDatePrevision();
    Prevision previsionSave = memoire.getUnePrevision(date);

    DonneeVent[][] donneesSave = previsionSave.getListeDonneVent();
    DonneeVent[][] donnees = element.getListeDonneVent();
    for (int i = 0; i < donnees.length; i++) {
      donnees[i] = donneesSave[i];
    }
    element.setMatrice(donneesSave);
  }

  @Override
  public void agitSur(ZonePrevision element) {
    // TODO Auto-generated method stub

  }

}
