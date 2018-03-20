package edition.mains;

import edition.implementation.Json;
import edition.visiteur.VisiteurAffichage;
import java.io.IOException;
import java.util.Calendar;
import previsionVents.DonneeVent;
import previsionVents.ListePrevision;
import previsionVents.Prevision;

public class MainEcritureJson {

  public static void main(String[] args) throws IOException {

    // creation d'une ListPrevision
    ListePrevision previsions = new ListePrevision(1.0, 2.0, 3.0, 4.0, 3, 3);
    
    Calendar calendar = Calendar.getInstance();
    calendar.set(2018,3,19,11,43);   

    Prevision prevision = new Prevision(calendar, 3, 3);
    DonneeVent[][] matrice = prevision.getListeDonneVent();

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        matrice[i][j].setOrientationVent(5.0);
        matrice[i][j].setVitesseVent(6.0);
      }
    }

    previsions.getListePrevision().add(prevision);
    previsions.getListePrevision().add(prevision);
    previsions.getListePrevision().add(prevision);

    previsions.applique(new VisiteurAffichage());

    // creation json
    Json json = new Json();
    json.JsonWrite(previsions, "test3.json");

  }
}
