package modification;

import java.util.Calendar;

import previsionVents.DonneeVent;
import previsionVents.ZonePrevision;

public class VisiteurContrasteProgressif extends VisiteurModifier {

  final double SEUIL_DEFAUT = 100;

  double coefficient;
  double seuil;

  /**
   * 
   * @param zone
   *          selectionee
   * @param niveau
   *          de contraste
   * @param seuil
   *          du contraste
   */

  public VisiteurContrasteProgressif(ZonePrevision zone, Calendar date, double coefficient,
      double seuil) {
    super(zone, date);

    // Le coefficient ne peux pas etre superieur a 1 ou inferieur a -1
    if (Math.abs(coefficient) > 1) {
      this.coefficient = 0;
    } else {
      this.coefficient = coefficient;
    }

    if (seuil <= 0 || seuil >= VITESSE_MAX) {
      this.seuil = SEUIL_DEFAUT;
    } else {
      this.seuil = seuil;
    }
  }

  /**
   * modification des vent se trouvant dans la zone.
   * 
   * @param vent
   */

  @Override
  void modification(DonneeVent vent) {
    double valeur;
    if (vent.getVitesseVent() < seuil) {
      valeur = vent.getVitesseVent() * (1 - coefficient);

    } else {
      valeur = vent.getVitesseVent() * (1 + coefficient);
    }
    if (valeur > VITESSE_MAX) {
      valeur = VITESSE_MAX;
    }
    vent.setVitesseVent(valeur);
  }

}
