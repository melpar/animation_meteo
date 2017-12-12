package visiteur;

import previsionVents.DonneeVent;
import previsionVents.ListePrevision;
import previsionVents.Prevision;

public interface Visiteur {
	void agitSur(ElementVisitable element);

	void agitSur(DonneeVent element);

	void agitSur(ListePrevision element);

	void agitSur(Prevision element);

}
