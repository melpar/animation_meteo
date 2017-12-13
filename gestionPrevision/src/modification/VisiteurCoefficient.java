package modification;

import previsionVents.DonneeVent;
import previsionVents.ListePrevision;
import previsionVents.Prevision;
import previsionVents.ZonePrevision;

public class VisiteurCoefficient extends VisiteurModifier {

	double coefficient;
	
	public VisiteurCoefficient(ZonePrevision zone,double coefficient) {
		super(zone);
		
		// Le coefficient ne peux pas etre superieur a 1 ou inferieur a -1
		if(Math.abs(coefficient) > 1) {
			this.coefficient = 0;
		} else {
			this.coefficient=coefficient;
		}
	}
	
	/**
	 * modification des vent se trouvant dans la zone.
	 * @param vent
	 */

	@Override
	void modification(DonneeVent vent) {
		vent.setVitesseVent(vent.getVitesseVent() * (1 + coefficient));
	}
		
}
