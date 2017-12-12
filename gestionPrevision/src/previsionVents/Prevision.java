package previsionVents;

import java.util.Date;

import visiteur.ElementVisitable;
import visiteur.Visiteur;


public class Prevision implements ElementVisitable{
  
  private Date datePrevision;
  private DonneeVent[][] listeDonneVent;
  private FabriqueDonnee fabriqueDonnee;
  
  public Prevision(Date datePrevision,int nombreX, int nombreY) {
    this.datePrevision = datePrevision;
    this.listeDonneVent = new DonneeVent[nombreX][nombreY];    
    for(int posX=0;posX<this.listeDonneVent.length;posX++) {
      for(int posY=0;posY<this.listeDonneVent[0].length;posX++) {
        this.listeDonneVent[posX][posY] = new DonneeVent();
      }
    }
    this.fabriqueDonnee= new FabriqueDonnee();
  }
  
  public void ajouterDonneeVent(double u, double v, int positionX, int positionY) {
    this.listeDonneVent[positionX][positionY]=this.fabriqueDonnee.creeDonneeVent(u, v);
  }

  public Date getDatePrevision() {
    return datePrevision;
  }
  
  public DonneeVent getDonneVent(int positionX, int positionY) {
    if((positionX>this.listeDonneVent.length-1)|| (positionY>this.listeDonneVent[0].length-1)){
      return null;
    }
    if((positionX<0)|| (positionY<0)){
      return null;
    }
    
    return this.listeDonneVent[positionX][positionX];
    
  }

@Override
public void applique(Visiteur visiteur) {
	visiteur.agitSur(this);
	
}

   
}
