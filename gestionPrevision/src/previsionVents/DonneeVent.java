package previsionVents;

public class DonneeVent {
  
  private double vitesseVent;
  private double orientationVent;
  
  public DonneeVent() {
    this.vitesseVent=0;
    this.orientationVent=0;
  }
  
  public DonneeVent(double vitesseVent, double orientationVent) {
    this.vitesseVent = vitesseVent;
    this.orientationVent = orientationVent;
  } 

  public double getVitesseVent() {
    return vitesseVent;
  }

  public double getOrientationVent() {
    return orientationVent;
  }

  public void setVitesseVent(double vitesseVent) {
    this.vitesseVent = vitesseVent;
  }

  public void setOrientationVent(double orientationVent) {
    this.orientationVent = orientationVent;
  }  
  

}
