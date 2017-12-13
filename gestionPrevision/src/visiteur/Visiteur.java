package visiteur;

import previsionVents.DonneeVent;
import previsionVents.ListePrevision;
import previsionVents.Prevision;
import previsionVents.ZonePrevision;

public interface Visiteur {
	void agitSur(ElementVisitable element);

	void agitSur(DonneeVent element);

	void agitSur(ListePrevision element);

	void agitSur(Prevision element);

	public void agitSur(ZonePrevision element);
}
