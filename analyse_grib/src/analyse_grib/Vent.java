package analyse_grib;

public class Vent {
  private int latitude;
  private int longitude;
  private double vecteurU;
  private double vecteurV;

  public Vent(int la, int lo, double u, double v) {
    this.latitude = la;
    this.longitude = lo;
    this.vecteurU = u;
    this.vecteurV = v;
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

  public double getVecteurV() {
    return vecteurV;
  }

  public void setVecteurV(double vecteurV) {
    this.vecteurV = vecteurV;
  }

  public double getVecteurU() {
    return vecteurU;
  }

  public void setVecteurU(double vecteurU) {
    this.vecteurU = vecteurU;
  }

}
