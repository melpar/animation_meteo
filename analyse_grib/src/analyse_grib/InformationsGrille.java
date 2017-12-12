package analyse_grib;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InformationsGrille {
  private double lattidude;
  private double longitude;
  private double pasX;
  private double pasY;
  private int nombreX;
  private int nombreY;
  private List<Vent> listeVents;
  private Date datePrevision;

  public InformationsGrille() {
    this.listeVents = new ArrayList<Vent>();
  }

  public void addVent(Vent v) {
    this.listeVents.add(v);
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
    this.pasX = pasX;
  }

  public double getPasY() {
    return pasY;
  }

  public void setPasY(double pasY) {
    this.pasY = pasY;
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

  public List<Vent> getVents() {
    return this.listeVents;
  }

  public Date getDatePrevision() {
    return datePrevision;
  }

  public void setDatePrevision(Date datePrevision) {
    this.datePrevision = datePrevision;
  }
}
