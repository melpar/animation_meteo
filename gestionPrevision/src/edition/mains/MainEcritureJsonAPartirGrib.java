package edition.mains;

import edition.implementation.Json;
import previsionVents.ListePrevision;
import previsionVents.RecuperationDonneesGrib;

public class MainEcritureJsonAPartirGrib {
  public static void main(String[] args) {
    // Lecture Grib
    RecuperationDonneesGrib recupGrib = new RecuperationDonneesGrib();
    ListePrevision previsions = recupGrib.getListePrevision("gascogne.grb");
    // creation json
    Json json = new Json();
    json.JsonWrite(previsions, "test2.json");
  }
}
