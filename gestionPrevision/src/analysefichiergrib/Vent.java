package analysefichiergrib;

/**
 * Representation d'un vent extrait d'un fichier grib.
 * 
 * @author StudioLab
 *
 */
public class Vent {
  private int latitude;
  private int longitude;
  private double vecteurU;
  private double vecteurV;

  /**
   * Constructeur de vent.
   * 
   * @param lat
   *          Latitude du vent
   * @param lon
   *          Longitude du vent
   * @param vecteurU
   *          Vecteur su l'axe X
   * @param vecteurV
   *          Vecteur su l'axe Y
   */
  public Vent(int lat, int lon, double vecteurU, double vecteurV) {
    this.latitude = lat;
    this.longitude = lon;
    this.vecteurU = vecteurU;
    this.vecteurV = vecteurV;
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
