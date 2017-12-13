package previsionVents;

public class FabriqueDonnee { 
  private static final  double COEFFICIENT_KILOMETRE_HEURE = 3.6;  
  
  public DonneeVent creeDonneeVent(double u,double v) {  
    double orientation =this.calculOrientation(u,v);
    double vitesse =this.calculvitesse(u, v);    
    return new DonneeVent(vitesse*COEFFICIENT_KILOMETRE_HEURE, orientation);   
  }
  
  private double calculOrientation(double u,double v) {
    return Math.sqrt(Math.pow(u, 2)+Math.pow(v, 2));
  }
  
  private double calculvitesse(double u,double v) {
    return Math.atan2(u,v);
  }
}
