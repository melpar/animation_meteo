package edition.mains;

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
    Date date = new Date();

    /*
     * ajoue d'une prevision a l'edition
     */
    edition.ajouterPrevision(date, 3, 1.0, 2.0);

    /*
     * modification de la date
     */
    date = new Date(date.getTime() + 3600 * 1000 * 3);

    /*
     * ajoue d'une prevision a l'edition
     */
    edition.ajouterPrevision(date, 1, 3.0, 4.0);

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
