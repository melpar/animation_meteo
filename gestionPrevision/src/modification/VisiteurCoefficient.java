package modification;

import java.util.Calendar;

import previsionVents.DonneeVent;
import previsionVents.ZonePrevision;

public class VisiteurCoefficient extends VisiteurModifier {

  double coefficient;

  /**
   * 
   * @param zone
   *          selectionee
   * @param coefficient.
   */

  public VisiteurCoefficient(ZonePrevision zone, Calendar date, double coefficient) {
    super(zone, date);

    // Le coefficient ne peux pas etre superieur a 1 ou inferieur a -1
    if (Math.abs(coefficient) > 1) {
      this.coefficient = 0;
    } else {
      this.coefficient = coefficient;
    }
  }

  /**
   * modification des vent se trouvant dans la zone.
   * 
   * @param vent.
   */

  @Override
  void modification(DonneeVent vent) {
    vent.setVitesseVent(vent.getVitesseVent() * (1 + coefficient));
  }
}
