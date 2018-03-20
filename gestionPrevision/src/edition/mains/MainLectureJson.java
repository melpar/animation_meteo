package edition.mains;

import edition.implementation.Json;
import edition.visiteur.VisiteurAffichage;
import previsionVents.ListePrevision;

public class MainLectureJson {

  /**
   * Execute une lecture de fichier JSON.
   * 
   * @param args
   *          pas utilisé
   */
  public static void main(String[] args) {
    ListePrevision listePrevision = new Json().JsonRead("test3.json");
    listePrevision.applique(new VisiteurAffichage());
  }

}
