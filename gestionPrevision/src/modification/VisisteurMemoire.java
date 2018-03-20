package modification;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import previsionVents.DonneeVent;
import previsionVents.ListePrevision;
import previsionVents.Prevision;
import previsionVents.ZonePrevision;
import visiteur.ElementVisitable;
import visiteur.Visiteur;

public class VisisteurMemoire implements Visiteur {

  private ListePrevision sauvegarde;

  public VisisteurMemoire() {
    sauvegarde = new ListePrevision();
    sauvegarde.setListePrevision(new ArrayList<Prevision>());
  }

  public ListePrevision getSauvegarde() {
    return sauvegarde;
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
    sauvegarde.setZonePrevisions(element.getZonePrevision());
    for (Prevision prevision : element.getListePrevision()) {
      prevision.applique(this);
    }
  }

  @Override
  public void agitSur(Prevision element) {
    Calendar date = element.getDatePrevision();
    DonneeVent[][] donneesX = element.getListeDonneVent();
    int nombreX = donneesX.length;
    int nombreY = donneesX[0].length;
    // sauvegarde.ajouterPrevision(date);
    Prevision previsionSave = new Prevision(date, nombreX, nombreY);
    DonneeVent[][] donneesSaveX = new DonneeVent[donneesX.length][];
    for (int x = 0; x < donneesX.length; x++) {
      DonneeVent[] donneesY = donneesX[x];
      DonneeVent[] donneesSaveY = new DonneeVent[donneesY.length];
      for (int y = 0; y < donneesY.length; y++) {
        double vitesseVent = donneesY[y].getVitesseVent();
        double orientationVent = donneesY[y].getOrientationVent();
        DonneeVent donneesSaveVent = new DonneeVent(vitesseVent, orientationVent);
        donneesSaveY[y] = donneesSaveVent;
      }
      donneesSaveX[x] = donneesSaveY;
    }
    previsionSave.setMatrice(donneesSaveX);
    List<Prevision> list = sauvegarde.getListePrevision();
    ArrayList<Prevision> listPrevision = new ArrayList<Prevision>();
    listPrevision.addAll(list);
    listPrevision.add(previsionSave);
    sauvegarde.setListePrevision(listPrevision);
  }

  @Override
  public void agitSur(ZonePrevision element) {
    // TODO Auto-generated method stub

  }

}
