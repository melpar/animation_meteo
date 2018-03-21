package modification;

import java.util.Calendar;

import previsionVents.DonneeVent;
import previsionVents.ListePrevision;
import previsionVents.Prevision;
import previsionVents.ZonePrevision;
import visiteur.ElementVisitable;
import visiteur.Visiteur;

public abstract class VisiteurModifier implements Visiteur {

  final int VITESSE_MAX = 400;

  ZonePrevision zoneModifier;
  ZonePrevision zonePrevision;
  Calendar date;

  public VisiteurModifier(ZonePrevision zone, Calendar date) {
    this.zoneModifier = zone;
    this.date = date;
  }

  /**
   * modification des vent se trouvant dans la zone.
   * 
   * @param vent
   */

  abstract void modification(DonneeVent vent);

  /**
   * appel des vent se trouvant dans la prevision.
   * 
   * @param prevision
   */

  void zoneModifier(Prevision prevision) {
    double limiteX = zoneModifier.getLatitudeHautGauche()
        + zoneModifier.getNombreX() * zoneModifier.getPasX();
    double limiteY = zoneModifier.getLongitudeHautGauche()
        + zoneModifier.getNombreY() * zoneModifier.getPasY();
    for (double positionX = zoneModifier
        .getLatitudeHautGauche(); positionX < limiteX; positionX += zonePrevision.getPasX()) {
      for (double positionY = zoneModifier
          .getLongitudeHautGauche(); positionY < limiteY; positionY += zonePrevision.getPasY()) {
        int matriceX = (int) ((positionX - zonePrevision.getLatitudeHautGauche())
            / zonePrevision.getPasX());
        int matriceY = (int) ((positionY - zonePrevision.getLongitudeHautGauche())
            / zonePrevision.getPasY());
        DonneeVent vent = prevision.getDonneeVent(matriceX, matriceY);
        if (vent != null) {
          vent.applique(this);
        }
      }
    }
  }

  @Override
  public void agitSur(ListePrevision element) {
    zonePrevision = element.getZonePrevision();
    for (Prevision prevision : element.getListePrevision()) {
      prevision.applique(this);
    }
  }

  @Override
  public void agitSur(ZonePrevision element) {

  }

  @Override
  public void agitSur(Prevision element) {
    if (element.getDatePrevision().equals(date)) {
      zoneModifier(element);
    }
  }

  @Override
  public void agitSur(DonneeVent element) {
    modification(element);
  }

  @Override
  public void agitSur(ElementVisitable element) {

  }

}
