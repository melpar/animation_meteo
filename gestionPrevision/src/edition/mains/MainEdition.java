package edition.mains;

import edition.implementation.Edition;
import edition.visiteur.VisiteurAffichage;
import java.util.Date;

import previsionVents.ListePrevision;

public class MainEdition {

  /**
   * Permet de simuler le comportement de la popup.
   * @param args pas utilisés
   */
  @SuppressWarnings("deprecation")
  public static void main(String[] args) {

    Date date = new Date();
    Edition edition = new Edition(0.0, 0.0, 10.0, 10.0);
    edition.ajouterPrevision(date, 3, 1.0, 2.0);

    date.setHours(date.getDate() + 3);
    edition.ajouterPrevision(date, 5, 3.0, 4.0);

    ListePrevision listePrevision = edition.creerListPrevision();

    listePrevision.applique(new VisiteurAffichage());
  }

}
