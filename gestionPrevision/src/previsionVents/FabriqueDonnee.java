package previsionVents;

public class FabriqueDonnee { 
  
  
  public DonneeVent creeDonneeVent(double vX,double vY) {  
    double orientation =this.calculOrientation(vX,vY);
    double vitesse =this.calculvitesse(vX, vY);    
    return new DonneeVent(vitesse, orientation);   
  }
  
  private double calculOrientation(double u,double v) {
    return Math.sqrt(Math.pow(u, 2)+Math.pow(v, 2));
  }
  
  private double calculvitesse(double u,double v) {
    return Math.atan2(v,u);
  }
}
