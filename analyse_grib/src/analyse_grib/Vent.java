package analyse_grib;

public class Vent {
  private int latitude;
  private int longitude;
  private double vitesse;
  private double direction;

  public Vent(int la, int lo, double vi, double di) {
    this.latitude = la;
    this.longitude = lo;
    this.vitesse = vi;
    this.direction = di;
  }

  public int getLatitude() {
    return latitude;
  }

  public void setLatitude(int latitude) {
    this.latitude = latitude;
  }

  public int getLongitude() {
    return longitude;
  }

  public void setLongitude(int longitude) {
    this.longitude = longitude;
  }

  public double getVitesse() {
    return vitesse;
  }

  public void setVitesse(double vitesse) {
    this.vitesse = vitesse;
  }

  public double getDirection() {
    return direction;
  }

  public void setDirection(double direction) {
    this.direction = direction;
  }
}
