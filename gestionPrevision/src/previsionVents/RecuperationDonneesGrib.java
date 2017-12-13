package previsionVents;

import java.util.List;

import analyse_grib.InformationsGrille;
import analyse_grib.ParserGrib;
import analyse_grib.Vent;

public class RecuperationDonneesGrib {
  public static void main(String[] args) {
    RecuperationDonneesGrib recuperationTest = new RecuperationDonneesGrib();
    ListePrevision listePrevisionTest = recuperationTest.getListePrevision();
    System.out.println(listePrevisionTest.getNombrePrevision());
    DonneeVent[][] donnee = listePrevisionTest.getListePrevision().get(0).getListeDonneVent();
    System.out.println(donnee.length);
    System.out.println(donnee[0].length);
    ZonePrevision zone=listePrevisionTest.getZonePrevision();
    System.out.println("longitude : "+zone.getLongitudeHautGauche());
    System.out.println("latitude : "+zone.getLatitudeHautGauche());
    
   /* for (int i = 0; i < donnee.length; i++) {
      for (int y = 0; y < donnee[0].length; y++) {
        System.out.println("coordonee : (" + i + " " + y + ")(lat: "+zone.getLatitudePosition(i)+"\tlong: "+zone.getLongitudePosition(y));
        System.out.print("Orientation :" + donnee[i][y].getOrientationVent());
        System.out.println("\tVitesse :" + donnee[i][y].getVitesseVent());
      }
    }*/
  }

  public ListePrevision getListePrevision() {
    ParserGrib parser = new ParserGrib();
    InformationsGrille informations = parser.getInformationsGrille("gascogne.grb");
    List<Vent> liste = informations.getVents();

    ListePrevision listePrevision = new ListePrevision(informations.getLattidude(),informations.getLongitude(), informations.getPasX(), informations.getPasY(),
        informations.getNombreX(), informations.getNombreY());

    for (Vent vent : informations.getVents()) {
      listePrevision.ajouterDonneeVent(informations.getDatePrevision(), vent.getVecteurU(),
          vent.getVecteurV(), vent.getLatitude(), vent.getLongitude());

    }
    return listePrevision;
  }
}
