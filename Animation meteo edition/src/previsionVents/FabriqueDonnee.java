package previsionVents;

public class FabriqueDonnee { 
  
  
  public DonneeVent creeDonneeVent(double u,double v) {  
    double orientation =this.calculOrientation(u,v);
    double vitesse =this.calculvitesse(u, v);    
    return new DonneeVent(vitesse, orientation);   
  }
  
  private double calculOrientation(double u,double v) {
    return Math.sqrt(Math.pow(u, 2)+Math.pow(v, 2));
  }
  
  private double calculvitesse(double u,double v) {
    return Math.atan2(u,v);
  }
}
