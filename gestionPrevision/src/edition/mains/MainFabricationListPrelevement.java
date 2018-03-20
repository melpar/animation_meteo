package edition.mains;

import edition.usines.FabricationListPrevision;
import edition.visiteur.VisiteurAffichage;
import previsionVents.ListePrevision;

public class MainFabricationListPrelevement {

  public static void main(String[] args) {

    ListePrevision previsions = FabricationListPrevision.creerListPrevisionVide(10.0, 10.0, 10.0,
        10.0, 10, 10);
    previsions.applique(new VisiteurAffichage());
    System.out.println();
    previsions.applique(new VisiteurAffichage());

  }

}
