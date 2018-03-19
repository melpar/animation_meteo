package modification;

import previsionVents.DonneeVent;
import previsionVents.ZonePrevision;

public class VisiteurContrasteLineaire extends VisiteurModifier {

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

  public VisiteurContrasteLineaire(ZonePrevision zone, double coefficient, double seuil) {
    super(zone);

    // Le coefficient ne peux pas etre superieur a 1 ou inferieur a -1
    if (Math.abs(coefficient) > 10) {
      this.coefficient = 0;
    } else {
      this.coefficient = coefficient;
    }

    if (seuil < 0 || seuil >= VITESSE_MAX) {
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
    // expression : vitesse
    double valeur = vent.getVitesseVent() * 2.0f
        / (1.0 + Math.exp(-coefficient * (vent.getVitesseVent() - seuil)));
    if (valeur > VITESSE_MAX) {
      valeur = VITESSE_MAX;
    }
    vent.setVitesseVent(valeur);
  }

}
