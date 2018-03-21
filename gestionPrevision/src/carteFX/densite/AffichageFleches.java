package carteFX.densite;

import org.geotools.geometry.jts.ReferencedEnvelope;

import carte.AfficherFleches;
import previsionVents.FacadePrevisionVents;
import previsionVents.ListePrevision;

public class AffichageFleches {
  public static void miseAJour(ReferencedEnvelope envelope) {

    double newMinX = envelope.getMinX();
    double newMaxX = envelope.getMaxX();
    double newMinY = envelope.getMinY();
    double newMaxY = envelope.getMaxY();

    System.out.println("Nouvelles coordonnees : (min) " + newMinX + " " + newMaxX);
    System.out.println("Nouvelles coordonnees : (max) " + newMinY + " " + newMaxY);

    FacadePrevisionVents facade = FacadePrevisionVents.getFacadePrevisionVents();
    ListePrevision prev = facade.getPrevisions();

    // Si la zone affich�e est plus petite, on adapte
    AfficherFleches afficherFleches = AfficherFleches.getInstance();
    System.out.println("prev = " + facade.getPrevisions());
    if (facade.getPrevisions() != null) {
      System.out.println("on met � jour les fleches");
      double pasX = facade.getPrevisions().getZonePrevision().getPasX();
      double pasXDouble = (newMaxX - newMinX) / pasX;
      while (pasXDouble > 100) {
        pasXDouble /= 10;
      }
      double pasY = facade.getPrevisions().getZonePrevision().getPasY();
      double pasYDouble = (newMaxY - newMinY) / pasY;
      if (pasYDouble < 0) {
        pasYDouble = pasYDouble * -1;
      }
      while (pasYDouble > 100) {
        pasYDouble /= 10;
      }
      afficherFleches.setPas((int) pasXDouble, (int) pasYDouble);
      double taille = facade.getPrevisions().getZonePrevision().getPasX() * (pasXDouble - 5);
      afficherFleches.setTaille(taille);
      try {
        afficherFleches.action(null);
      } catch (Throwable e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
}
