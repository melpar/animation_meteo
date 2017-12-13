package edition.mains;

import edition.implementation.Json;
import edition.visiteur.VisiteurAffichage;
import previsionVents.ListePrevision;

public class MainLectureJson {

	public static void main(String[] args) {
		ListePrevision listePrevision = new Json().JsonRead("test2.json");
		listePrevision.applique(new VisiteurAffichage());
	}

}
