package analysefichiergrib;

import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Representation des parametres du fichier grib.
 * 
 * @author StudioLab
 *
 */
public class InformationsGrille {
  private double lattidude;
  private double longitude;
  private double pasX;
  private double pasY;
  private int nombreX;
  private int nombreY;
  private Map<Date, List<Vent>> listeVents;

  public InformationsGrille() {
    this.listeVents = new HashMap<Date, List<Vent>>();

  }

  public void addVent(Date d, Vent v) {
    if (this.listeVents.get(d) == null) {
      this.listeVents.put(d, new ArrayList<>());
    }
    this.listeVents.get(d).add(v);
  }

  public double getLattidude() {
    return lattidude;
  }

  public void setLattidude(double lattidude) {
    this.lattidude = lattidude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public double getPasX() {
    return pasX;
  }

  public void setPasX(double pasX) {
    this.pasX = Math.abs(pasX);
  }

  public double getPasY() {
    return pasY;
  }

  public void setPasY(double pasY) {
    this.pasY = Math.abs(pasY);
  }

  public int getNombreX() {
    return nombreX;
  }

  public void setNombreX(int nombreX) {
    this.nombreX = nombreX;
  }

  public int getNombreY() {
    return nombreY;
  }

  public void setNombreY(int nombreY) {
    this.nombreY = nombreY;
  }

  public Map<Date, List<Vent>> getVents() {
    return this.listeVents;
  }

}
