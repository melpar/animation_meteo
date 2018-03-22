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

    // Si la zone affichée est plus petite, on adapte
    AfficherFleches afficherFleches = AfficherFleches.getInstance();
    if (facade.getPrevisions() != null) {
      System.out.println("on met à jour les fleches");

      double pasXDouble = (newMaxX - newMinX) / 10;
      while (pasXDouble > 100) {
        pasXDouble /= 10;
      }
      double pasY = facade.getPrevisions().getZonePrevision().getPasY();
      double pasYDouble = (newMaxY - newMinY) / 10;
      if (pasYDouble < 0) {
        pasYDouble = pasYDouble * -1;
      }
      while (pasYDouble > 100) {
        pasYDouble /= 10;
      }
      afficherFleches.setPas((int) pasXDouble, (int) pasYDouble);
      double tailleX = facade.getPrevisions().getZonePrevision().getPasX() * (pasXDouble - 5);
      double tailleY = facade.getPrevisions().getZonePrevision().getPasY() * (pasYDouble - 5);
      double taille = tailleX < tailleY ? tailleX : tailleY;
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
