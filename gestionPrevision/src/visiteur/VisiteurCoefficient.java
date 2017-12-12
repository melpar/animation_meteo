package visiteur;

import previsionVents.ZonePrevision;

public class VisiteurCoefficient implements Visiteur {

	ZonePrevision zone;
	
	@Override
	public void agitSur(ElementVisitable element) {
		
	}

	public VisiteurCoefficient(ZonePrevision zone) {
		this.zone=zone;
	}
	
	void calculCoefficient() {
		
	}
}
