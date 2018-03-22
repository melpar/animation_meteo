package previsionVents;

import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import analysefichiergrib.InformationsGrille;
import analysefichiergrib.ParserGrib;
import analysefichiergrib.Vent;

public class RecuperationDonneesGrib {
  /**
   * Permet de lancer un récupération de données Grib.
   * 
   * @param args
   *          pas utilisés
   */
  public static void main(String[] args) {
    RecuperationDonneesGrib recuperationTest = new RecuperationDonneesGrib();
    ListePrevision listePrevisionTest = recuperationTest.getListePrevision("gascogne.grb");
    System.out.println("Nombre de pr�visions : " + listePrevisionTest.getNombrePrevision());
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
   * 
   * @param nom
   *          nom du fichier (chemin complet)
   * @return liste de prévision créée
   */
  public ListePrevision getListePrevision(String nom) {
    ParserGrib parser = new ParserGrib();
    System.out.println("parse");
    InformationsGrille informations = parser.getInformationsGrille(nom);
    System.out.println("pars�");
    Map<Calendar, List<Vent>> liste = informations.getVents();

    ListePrevision listePrevision = new ListePrevision(informations.getLattidude(),
        informations.getLongitude(), informations.getPasX(), informations.getPasY(),
        informations.getNombreX(), informations.getNombreY());

    Set<Calendar> cles = liste.keySet();
    Iterator<Calendar> it = cles.iterator();
    while (it.hasNext()) {
      Calendar calendar = it.next();
      List<Vent> vents = liste.get(calendar);
      for (Vent vent : vents) {
        listePrevision.ajouterDonneeVent(calendar, vent.getVecteurU(), vent.getVecteurV(),
            vent.getLatitude(), vent.getLongitude());
      }
    }
    Collections.sort(listePrevision.getListePrevision());
    return listePrevision;
  }
}
