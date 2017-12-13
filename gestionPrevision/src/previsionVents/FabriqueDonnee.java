package previsionVents;

public class FabriqueDonnee { 
  private static final  double COEFFICIENT_KILOMETRE_HEURE = 3.6;  
  
  public DonneeVent creeDonneeVent(double u,double v) {  
    double vitesse =this.calculvitesse(u,v)*COEFFICIENT_KILOMETRE_HEURE;
    double orientation=this.calculOrientation(u, v);    
    return new DonneeVent(vitesse, orientation);   
  }
  
  private double calculOrientation(double u,double v) {
    return  Math.atan2(u,v);
  }
  
  private double calculvitesse(double u,double v) {   
    return Math.sqrt(Math.pow(u, 2)+Math.pow(v, 2));
  }
}
