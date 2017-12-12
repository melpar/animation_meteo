package previsionVents;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListePrevision {
  private List<Prevision> listePrevision;
  private ZonePrevision zonePrevision;
  
  public ListePrevision(double lattidude, double longitude,double pasX,double pasY,int nombreX,int nombreY) {
    this.listePrevision=new ArrayList<Prevision>();
    this.zonePrevision=new ZonePrevision(lattidude,longitude, pasX,pasY,nombreX,nombreY);
  }
  
  public void ajouterPrevision(Date datePrevision) {
    this.listePrevision.add(new Prevision(datePrevision, this.zonePrevision.getNombreX(),this.zonePrevision.getNombreY()));
  }
  
  public Boolean ajouterDonneeVent(Date datePrevision,double u, double v, double latitude ,double longitude) {
    int indicePrevision = this.getPrevision(datePrevision);
    if(indicePrevision!=-1) {
      return false;
    }
    int positionX= this.zonePrevision.getPositionX(latitude);
    int positionY= this.zonePrevision.getPositionY(longitude);
    this.listePrevision.get(indicePrevision).ajouterDonneeVent(u, v, positionX, positionY);    
    return true;    
  }
  
  public int getPrevision(Date datePrevision) {
    for(int indice=0;indice<this.listePrevision.size();indice++) {
      if(this.listePrevision.get(indice).getDatePrevision().equals(datePrevision)) {
        return indice;
      }
    }
    return -1;
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

}
