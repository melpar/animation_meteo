package previsionVents;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import java.util.Calendar;

import analysefichiergrib.InformationsGrille;
import analysefichiergrib.ParserGrib;
import analysefichiergrib.Vent;

public class RecuperationDonneesGrib {
  /**
   * Permet de lancer un récupération de données Grib.
   * @param args pas utilisés
   */
  public static void main(String[] args) {
    RecuperationDonneesGrib recuperationTest = new RecuperationDonneesGrib();
    ListePrevision listePrevisionTest = recuperationTest.getListePrevision("gascogne.grb");
    System.out.println("Nombre de pr�visions : "+listePrevisionTest.getNombrePrevision());
    DonneeVent[][] donnee = listePrevisionTest.getListePrevision().get(0).getListeDonneVent();
    System.out.println(donnee.length);
    System.out.println(donnee[0].length);
    ZonePrevision zone = listePrevisionTest.getZonePrevision();
    System.out.println("longitude : " + zone.getLongitudeHautGauche());
    System.out.println("latitude : " + zone.getLatitudeHautGauche());
    for (int i = 0; i < donnee.length; i++) {
      for (int y = 0; y < donnee[0].length; y++) {
        
        System.out.println("coordonee : (" + i + " " + y + ")(lat: " + zone.getLatitudePosition(i)
            + "\tlong: " + zone.getLongitudePosition(y) + " )");
        System.out.print("Orientation :" + donnee[i][y].getOrientationVent());
        System.out.println("\tVitesse :" + donnee[i][y].getVitesseVent());
      }
    }
  }

  /**
   * Permet de parser le fichier grib dont le nom est en parametre.
   * @param nom nom du fichier (chemin complet)
   * @return liste de prévision créée
   */
  public ListePrevision getListePrevision(String nom) {
    ParserGrib parser = new ParserGrib();
    System.out.println("parse");
    InformationsGrille informations = parser.getInformationsGrille(nom);
    System.out.println("pars�");
    Map<Date, List<Vent>> liste = informations.getVents();

    ListePrevision listePrevision = new ListePrevision(informations.getLattidude(),
        informations.getLongitude(), informations.getPasX(), informations.getPasY(),
        informations.getNombreX(), informations.getNombreY());

    Set<Date> cles = liste.keySet();
    Iterator<Date> it = cles.iterator();
    while (it.hasNext()) {
      Date date = it.next();
      List<Vent> vents = liste.get(date);
      for(Vent vent : vents){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        listePrevision.ajouterDonneeVent(c, vent.getVecteurU(),
            vent.getVecteurV(), vent.getLatitude(), vent.getLongitude());
      }
    }
    return listePrevision;
  }
}
