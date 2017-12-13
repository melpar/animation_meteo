package modification;

import previsionVents.DonneeVent;
import previsionVents.ListePrevision;
import previsionVents.Prevision;
import previsionVents.ZonePrevision;

public class VisiteurMoyenne extends VisiteurModifier {
	
	int nbDonneeVitesse;
	int nbDonneeDirection;
	double totalDonneesVitesse;
	double totalDonneesDirection;
	
	public VisiteurMoyenne(ZonePrevision zone) {
		super(zone);
		reset();
	}
	
	/**
	 * modification des vent se trouvant dans la zone.
	 * @param vent
	 */

	@Override
	void modification(DonneeVent vent) {
		totalDonneesVitesse +=vent.getVitesseVent();
		totalDonneesDirection +=vent.getOrientationVent();
		nbDonneeVitesse++;
		nbDonneeDirection++;
	}

	public double getMoyenneVitesse() {
		if(nbDonneeVitesse==0) {
			return 0;
		}
		return totalDonneesVitesse/nbDonneeVitesse;
	}
	
	public double getMoyenneDirection() {
		if(nbDonneeDirection==0) {
			return 0;
		}
		return totalDonneesDirection/nbDonneeDirection;
	}
	
	public void reset() {
		nbDonneeVitesse=0;
		nbDonneeDirection=0;
		totalDonneesVitesse=0;
		totalDonneesDirection=0;
	}
	



	

	
}
