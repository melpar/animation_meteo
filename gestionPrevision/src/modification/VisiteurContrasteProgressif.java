package modification;

import previsionVents.DonneeVent;
import previsionVents.ListePrevision;
import previsionVents.Prevision;
import previsionVents.ZonePrevision;

public class VisiteurContrasteProgressif extends VisiteurModifier {

	final double SEUIL_DEFAUT = 100;
	
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
		
		if(seuil <=0 || seuil >= VITESSE_MAX) {
			this.seuil = SEUIL_DEFAUT;
		} else {
			this.seuil=seuil;
		}
		System.out.println("mon seuil : "+this.seuil);
	}
	
	/**
	 * modification des vent se trouvant dans la zone.
	 * @param vent
	 */

	@Override
	void modification(DonneeVent vent) {
		double valeur;
		if(vent.getVitesseVent() < seuil) {
			valeur = vent.getVitesseVent() * (1 - coefficient);
			
		} else {
			valeur = vent.getVitesseVent() * (1 + coefficient);
		}
		if(valeur > VITESSE_MAX) {
			valeur = VITESSE_MAX;
		}
		vent.setVitesseVent(valeur);
	}

	
	



	

	
}
