package edition.mains;

import edition.adapteurs.AdapteurAjouterPrevision;
import edition.usines.FabricationListPrevision;
import edition.visiteur.VisiteurAffichage;
import previsionVents.ListePrevision;

public class MainFabricationListPrelevement {

	public static void main(String[] args) {

		ListePrevision previsions = FabricationListPrevision.creerListPrevisionVide(10.0, 10.0, 10.0, 10.0, 10, 10);
		previsions.applique(new VisiteurAffichage());
		System.out.println();
		
		
		AdapteurAjouterPrevision.AjouterPrevision(previsions.getListePrevision(), 12, 12, 1994, 10, 20.0, 20.0, 10, 10);
		AdapteurAjouterPrevision.AjouterPrevision(previsions.getListePrevision(), 12, 12, 1994, 10, 20.0, 20.0, 10, 10);
		previsions.applique(new VisiteurAffichage());

	}

}
