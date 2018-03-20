package edition.visiteur;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import previsionVents.DonneeVent;
import previsionVents.ListePrevision;
import previsionVents.Prevision;
import previsionVents.ZonePrevision;
import visiteur.ElementVisitable;
import visiteur.Visiteur;

public class VisiteurAffichage implements Visiteur {

  @Override
  public void agitSur(ElementVisitable element) {
    System.out.println("Element pas encore implementer");
  }

  @Override
  public void agitSur(DonneeVent element) {
    System.out.print(" " + element.getOrientationVent());
    System.out.print(" " + element.getVitesseVent());
  }

  @Override
  public void agitSur(ListePrevision element) {
    element.getZonePrevision().applique(this);
    for (Prevision prevision : element.getListePrevision()) {
      prevision.applique(this);
    }
  }

  @Override
  public void agitSur(Prevision element) {
    System.out.println("\t" + element.getDatePrevision().getTime());
    for (DonneeVent[] dataY : element.getListeDonneVent()) {
      for (DonneeVent dataX : dataY) {
        dataX.applique(this);
      }
      System.out.println();
    }
  }

  @Override
  public void agitSur(ZonePrevision element) {
    System.out.println(element.getLatitudeHautGauche());
    System.out.println(element.getLongitudeHautGauche());
    System.out.println(element.getPasX());
    System.out.println(element.getPasY());
    System.out.println(element.getNombreX());
    System.out.println(element.getNombreY());
  }

}
