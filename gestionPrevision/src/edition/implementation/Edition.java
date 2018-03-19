package edition.implementation;

import java.util.ArrayList;
import java.util.Calendar;

import previsionVents.DonneeVent;
import previsionVents.ListePrevision;
import previsionVents.Prevision;
import previsionVents.ZonePrevision;

public class Edition {

  /**
   * Precision de la grille
   */
  final int NOMBRE_POINT = 3;

  /**
   * zone de l'edition
   */
  ZonePrevision zone;

  /**
   * Liste des prevision relatif a une zonz
   */
  ArrayList<Prevision> previsions;

  /**
   * Creation d'une edition pour une zone
   * 
   * @param latitude1
   *          Cordonnée de la zonne
   * @param longitude1
   *          Cordonnée de la zonne
   * @param latitude2
   *          Cordonnée de la zonne
   * @param longitude2
   *          Cordonnée de la zonne
   */
  public Edition(double latitude1, double longitude1, double latitude2, double longitude2) {
    super();

    this.zone = new ZonePrevision();
    this.zone.setLatitude(latitude1);
    this.zone.setLongitude(longitude1);
    this.zone.setNombreX(this.NOMBRE_POINT);
    this.zone.setNombreY(this.NOMBRE_POINT);
    this.zone.setPasX((latitude2 - latitude1) / 2);
    this.zone.setPasY((longitude2 - longitude1) / 2);

    this.previsions = new ArrayList<>();
  }

  /**
   * Ajoute une prevision pour une zone
   * 
   * @param date
   *          date debut de la prevision
   * @param duree
   *          nombre d'heur que dure une prevision
   * @param vitesse
   *          vitesse du vent
   * @param direction
   *          direction du vent
   */
  public void ajouterPrevision(Calendar date, int duree, double vitesse, double direction) {
    for (int i = 0; i < duree; i++) {
      Prevision prevision = new Prevision();
      DonneeVent[][] donnees = new DonneeVent[this.NOMBRE_POINT][this.NOMBRE_POINT];
      for (int j = 0; j < this.NOMBRE_POINT; j++) {
        for (int k = 0; k < this.NOMBRE_POINT; k++) {
          donnees[j][k] = new DonneeVent();
          donnees[j][k].setOrientationVent(direction);
          donnees[j][k].setVitesseVent(vitesse);
        }
      }
      prevision.setMatrice(donnees);

      //date = new Date(date.getTime() + 3600 * 1000);
      date = Calendar.getInstance();

      prevision.setDate(date);

      this.previsions.add(prevision);
    }
  }

  /**
   * convertie l'edition en listePrevision
   * 
   * @return retourne un listePrevision
   */
  public ListePrevision creerListPrevision() {
    ListePrevision listePrevision = new ListePrevision();
    listePrevision.setZonePrevisions(this.zone);
    listePrevision.setListePrevision(this.previsions);
    return listePrevision;
  }
}
