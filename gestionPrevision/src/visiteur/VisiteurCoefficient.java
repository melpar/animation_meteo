package visiteur;

import previsionVents.DonneeVent;
import previsionVents.ListePrevision;
import previsionVents.Prevision;
import previsionVents.ZonePrevision;

public class VisiteurCoefficient implements Visiteur {

	ZonePrevision zoneModifier;
	ZonePrevision zonePrevision;
	double coefficient;
	
	public VisiteurCoefficient(ZonePrevision zone,double coefficient) {
		this.zoneModifier=zone;
		if(coefficient<-1 || coefficient > 1) {
			this.coefficient = 0;
		} else {
			this.coefficient=coefficient;
		}
	}
	
	void calculCoefficient(DonneeVent element) {
		element.setVitesseVent(
				element.getVitesseVent() * (1 + coefficient));
	}
	
	@Override
	public void agitSur(ListePrevision element) {
		zonePrevision = element.getZonePrevision();
		for(Prevision prevision : element.getListePrevision() ) {
			prevision.applique(this);
		}
		
	}
	
	@Override
	public void agitSur(Prevision element) {
		
		element.getDonneVent(0,0).applique(this);
	}
	
	@Override
	public void agitSur(DonneeVent element) {
		calculCoefficient(element);
	}
	
	@Override
	public void agitSur(ElementVisitable element) {
		// TODO Auto-generated method stub
		
	}

	

	
}
