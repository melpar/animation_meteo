package previsionVents;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import visiteur.ElementVisitable;
import visiteur.Visiteur;

public class ListePrevision implements ElementVisitable {

  private List<Prevision> listePrevision;
  private ZonePrevision zonePrevision;

  public ListePrevision(double latitude, double longitude, double pasX, double pasY, int nombreX,
      int nombreY) {
    this.listePrevision = new ArrayList<Prevision>();
    this.zonePrevision = new ZonePrevision(latitude, longitude, pasX, pasY, nombreX, nombreY);
  }

  public void ajouterPrevision(Date datePrevision) {
    this.listePrevision.add(new Prevision(datePrevision, this.zonePrevision.getNombreX(),
        this.zonePrevision.getNombreY()));
  }

  public Boolean ajouterDonneeVent(Date datePrevision, double u, double v, double latitude,
      double longitude) {
    int indicePrevision = this.getPrevision(datePrevision);
    if (indicePrevision == -1) {
      return false;
    }
    int positionX = this.zonePrevision.getPositionX(latitude);
    int positionY = this.zonePrevision.getPositionY(longitude);
    this.listePrevision.get(indicePrevision).ajouterDonneeVent(u, v, positionX, positionY);
    return true;
  }

  public int getPrevision(Date datePrevision) {
    for (int indice = 0; indice < this.listePrevision.size(); indice++) {
      if (this.listePrevision.get(indice).getDatePrevision().equals(datePrevision)) {
        return indice;
      }
    }
    this.ajouterPrevision(datePrevision);
    return this.listePrevision.size()-1;
  }

  public List<Prevision> getListePrevision() {
    return listePrevision;
  }

  public ZonePrevision getZonePrevision() {
    return zonePrevision;
  }

  public int getNombrePrevision() {
    return this.listePrevision.size();
  }

  @Override
  public void applique(Visiteur visiteur) {
    visiteur.agitSur(this);
  }

}
