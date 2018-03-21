package previsionVents;

import com.vividsolutions.jts.geom.Coordinate;

import carte.CalculPosition;
import visiteur.ElementVisitable;
import visiteur.Visiteur;

public class ZonePrevision implements ElementVisitable {

  private double longitudeHautGauche;
  private double latitudeHautGauche;
  private double pasX;
  private double pasY;
  private int nombreX;
  private int nombreY;

  public ZonePrevision() {
    super();
  }

  public ZonePrevision(double latitude, double longitude, double pasX, double pasY, int nombreX,
      int nombreY) {
    this.longitudeHautGauche = longitude;
    this.latitudeHautGauche = latitude;
    this.pasX = pasX;
    this.pasY = pasY;
    this.nombreX = nombreX;
    this.nombreY = nombreY;
  }

  public int getPositionX(Double latitude) {
    return (int) ((latitude - this.latitudeHautGauche) / nombreX);
  }

  public int getPositionY(Double longitude) {
    return (int) ((longitude - this.longitudeHautGauche) / nombreY);
  }

  public double getLatitudePosition(int postionY) {
    return this.latitudeHautGauche - (this.pasY * postionY);
  }

  public double getLongitudePosition(int postionX) {
    return this.longitudeHautGauche + (this.pasX * postionX);
  }

  public double getLongitudeHautGauche() {
    return longitudeHautGauche;
  }

  public double getLatitudeHautGauche() {
    return latitudeHautGauche;
  }

  public double getPasX() {
    return pasX;
  }

  public double getPasY() {
    return pasY;
  }

  public int getNombreX() {
    return nombreX;
  }

  public int getNombreY() {
    return nombreY;
  }

  @Override
  public void applique(Visiteur visiteur) {
    visiteur.agitSur(this);
  }

  public void setLatitude(double latitude) {
    this.latitudeHautGauche = latitude;
  }

  public void setLongitude(double longitude) {
    this.longitudeHautGauche = longitude;
  }

  public void setPasX(double pasX) {
    this.pasX = pasX;
  }

  public void setPasY(double pasY) {
    this.pasY = pasY;
  }

  public void setNombreX(int nombreX) {
    this.nombreX = nombreX;
  }

  public void setNombreY(int nombreY) {
    this.nombreY = nombreY;
  }

  public Coordinate getMinimum() {
    Coordinate coord = new Coordinate(this.latitudeHautGauche + 1, this.longitudeHautGauche + 1);
    return CalculPosition.convertEpsg4326to3857(coord);
  }

  public Coordinate getMaximum() {
    Coordinate coord = new Coordinate(this.latitudeHautGauche - this.nombreX * pasX - 1,
        this.longitudeHautGauche + this.nombreY * pasY + 1);
    return CalculPosition.convertEpsg4326to3857(coord);
  }
}
