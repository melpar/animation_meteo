package previsionVents;

public class ZonePrevision {
  
  private double longitudeHautGauche ;
  private double latitudeHautGauche ;
  private double pasX ;
  private double pasY ;
  private int nombreX ;
  private int nombreY ; 
  
  
  public ZonePrevision(double longitude, double latitude, double pasX, double pasY, int nombreX,int nombreY) {
    this.longitudeHautGauche = longitude;
    this.latitudeHautGauche = latitude;
    this.pasX = pasX;
    this.pasY = pasY;
    this.nombreX = nombreX;
    this.nombreY = nombreY;
  }

  public int getPositionX(Double latitude) {    
    return (int) ((latitude-this.latitudeHautGauche)/nombreX);
  }
  
  public int getPositionY(Double longitude) {
    return (int) ((longitude-this.longitudeHautGauche)/nombreY);
  }
  
  public double getLatitudePosition(int postiontX) {
    return  this.latitudeHautGauche+(this.pasX*postiontX);
  }
  
  public double getLongitudePosition(int postionY) {
    return  this.longitudeHautGauche+(this.pasY*postionY);
  }

  
}
