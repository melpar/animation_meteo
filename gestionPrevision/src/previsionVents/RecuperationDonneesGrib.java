package previsionVents;

import java.util.List;

import analyse_grib.InformationsGrille;
import analyse_grib.ParserGrib;
import analyse_grib.Vent;

public class RecuperationDonneesGrib {
  public static void main(String[] args) {

  }

  public ListePrevision getListePrevision() {
    ParserGrib parser = new ParserGrib();
    InformationsGrille informations = parser.getInformationsGrille("gascogne.grb");
    List<Vent> liste = informations.getVents();

    ListePrevision listePrevision = new ListePrevision(informations.getLattidude(),
        informations.getLongitude(), informations.getPasX(), informations.getPasY(),
        informations.getNombreX(), informations.getNombreY());

    for (Vent vent : informations.getVents()) {
      listePrevision.ajouterDonneeVent(informations.getDatePrevision(), vent.getVecteurU(),
          vent.getVecteurV(), vent.getLatitude(), vent.getLongitude());

    }
    return listePrevision;
  }
}
