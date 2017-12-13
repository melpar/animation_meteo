package visiteur;

import previsionVents.DonneeVent;
import previsionVents.ListePrevision;
import previsionVents.Prevision;
import previsionVents.ZonePrevision;

public class VisiteurContrasteProgressif extends VisiteurModifier {

	double coefficient;
	double seuil;
	
	public VisiteurContrasteProgressif(ZonePrevision zone,double coefficient,double seuil) {
		super(zone);
		
		// Le coefficient ne peux pas etre superieur a 1 ou inferieur a -1
		if(Math.abs(coefficient) > 1) {
			this.coefficient = 0;
		} else {
			this.coefficient=coefficient;
		}
		
		if(seuil <=0 || seuil >= 400) {
			this.seuil = 100;
		} else {
			this.seuil=seuil;
		}
	}
	
	/**
	 * modification des vent se trouvant dans la zone.
	 * @param vent
	 */

	@Override
	void modification(DonneeVent vent) {
		if(vent.getVitesseVent() < seuil) {
			vent.setVitesseVent(vent.getVitesseVent() * (1 - coefficient));
		} else {
			vent.setVitesseVent(vent.getVitesseVent() * (1 + coefficient));
		}
	}
	



	

	
}
