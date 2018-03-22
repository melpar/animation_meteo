package previsionVents;

import java.util.Calendar;

import visiteur.ElementVisitable;
import visiteur.Visiteur;

public class Prevision implements ElementVisitable, Comparable<Prevision> {

  private Calendar datePrevision;
  private DonneeVent[][] listeDonneVent;
  private FabriqueDonnee fabriqueDonnee;

  public Prevision() {
    super();
  }

  public DonneeVent getDonneeVent(int positionX, int positionY) {
    if ((positionX > this.listeDonneVent.length - 1)
        || (positionY > this.listeDonneVent[0].length - 1)) {
      return null;
    }
    if ((positionX < 0) || (positionY < 0)) {
      return null;
    }

    return this.listeDonneVent[positionX][positionY];

  }

  public Prevision(Calendar datePrevision, int nombreX, int nombreY) {
    this.datePrevision = datePrevision;
    System.out.println("X : " + nombreX);
    System.out.println("X : " + nombreY);
    this.listeDonneVent = new DonneeVent[nombreX][nombreY];
    for (int posX = 0; posX < this.listeDonneVent.length; posX++) {
      for (int posY = 0; posY < this.listeDonneVent[0].length; posY++) {
        this.listeDonneVent[posX][posY] = new DonneeVent();
      }
    }
    this.fabriqueDonnee = new FabriqueDonnee();
  }

  public void ajouterDonneeVent(double u, double v, int positionX, int positionY) {
    this.listeDonneVent[positionX][positionY] = this.fabriqueDonnee.creeDonneeVent(u, v);
  }

  public Calendar getDatePrevision() {
    return datePrevision;
  }

  public DonneeVent[][] getListeDonneVent() {
    return listeDonneVent;
  }

  @Override
  public void applique(Visiteur visiteur) {
    visiteur.agitSur(this);

  }

  public void setDate(Calendar date) {
    this.datePrevision = date;
  }

  public void setMatrice(DonneeVent[][] donnees) {
    this.listeDonneVent = donnees;
  }

  @Override
  public int compareTo(Prevision arg0) {
    System.out.println("compareTo");
    if (this.datePrevision.before(arg0.datePrevision)) {
      return -1;
    } else {
      return 1;
    }
  }

}
