package modification;

import previsionVents.DonneeVent;
import previsionVents.ListePrevision;
import previsionVents.Prevision;
import previsionVents.ZonePrevision;

public class VisiteurContrasteLineaire extends VisiteurModifier {

	
	
	double coefficient;
	double seuil;
	
	public VisiteurContrasteLineaire(ZonePrevision zone,double coefficient,double seuil) {
		super(zone);
		
		// Le coefficient ne peux pas etre superieur a 1 ou inferieur a -1 
		if(Math.abs(coefficient) > 10) {
			this.coefficient = 0;
		} else {
			this.coefficient=coefficient;
		}
		
		if(seuil <0 || seuil >= VITESSE_MAX) {
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
		// expression : vitesse
		double valeur =  vent.getVitesseVent()*2 / ( 1.0 + Math.exp( -coefficient * ( vent.getVitesseVent() - seuil ) ) ); 
		if(valeur > VITESSE_MAX) {
			valeur = VITESSE_MAX;
		}
		vent.setVitesseVent(valeur);		
	}

	
	



	

	
}
