package edition.mains;

import java.util.Calendar;
import java.util.Date;

import edition.implementation.Edition;
import edition.visiteur.VisiteurAffichage;
import previsionVents.ListePrevision;

public class MainEdition {

  /**
   * Permet de simuler le comportement de la popup d'édition.
   * 
   * @param
   */
  public static void main(String[] args) {

    /*
     * Creation d'un edition
     */
    Edition edition = new Edition(0.0, 0.0, 10.0, 10.0);

    /*
     * creation de la date de la prevision
     */
    Calendar calendar = Calendar.getInstance();
    

    /*
     * ajoue d'une prevision a l'edition
     */
    edition.ajouterPrevision(calendar, 3, 1.0, 2.0);

    /*
     * modification de la date
     */
    calendar.set(2018,3,19,11,43);   

    /*
     * ajoue d'une prevision a l'edition
     */
    edition.ajouterPrevision(calendar, 1, 3.0, 4.0);

    /*
     * conversion de l'objet edition en objet listPrevision
     */
    ListePrevision listePrevision = edition.creerListPrevision();

    /*
     * affichage d'une prevision
     */
    listePrevision.applique(new VisiteurAffichage());

  }

}
