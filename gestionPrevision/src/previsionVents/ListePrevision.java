package previsionVents;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import visiteur.ElementVisitable;
import visiteur.Visiteur;

public class ListePrevision implements ElementVisitable {

  private List<Prevision> listePrevision;
  private ZonePrevision zonePrevision;

  public ListePrevision() {
    super();
  }

  public ListePrevision(double latitude, double longitude, double pasX, double pasY, int nombreX,
      int nombreY) {
    this.listePrevision = new ArrayList<Prevision>();
    this.zonePrevision = new ZonePrevision(latitude, longitude, pasX, pasY, nombreX, nombreY);
    FacadePrevisionVents facade = FacadePrevisionVents.getFacadePrevisionVents();
    facade.setPrevisions(this);
  }

  public void ajouterPrevision(Date datePrevision) {
    this.listePrevision.add(new Prevision(datePrevision, this.zonePrevision.getNombreX(),
        this.zonePrevision.getNombreY()));
  }

  public Boolean ajouterDonneeVent(Date datePrevision, double u, double v, double latitude,
      double longitude) {

    int positionX = this.zonePrevision.getPositionX(latitude);
    int positionY = this.zonePrevision.getPositionY(longitude);
    this.ajouterDonneeVent(datePrevision, u, v, latitude, longitude);
    return true;
  }

  public Boolean ajouterDonneeVent(Date datePrevision, double u, double v, int positionX,
      int positionY) {
    int indicePrevision = this.getPrevision(datePrevision);
    if (indicePrevision == -1) {
      return false;
    }
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
    return this.listePrevision.size() - 1;
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

  public void setZonePrevisions(ZonePrevision zone) {
    this.zonePrevision = zone;
  }

  public void setListePrevision(ArrayList<Prevision> arrayList) {
    this.listePrevision = arrayList;
  }

  public Prevision getUnePrevision(Date date) {
    return this.listePrevision.get(this.getPrevision(date));
  }

}
