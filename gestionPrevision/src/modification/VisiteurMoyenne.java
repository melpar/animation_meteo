package modification;

import previsionVents.DonneeVent;
import previsionVents.ListePrevision;
import previsionVents.Prevision;
import previsionVents.ZonePrevision;

public class VisiteurMoyenne extends VisiteurModifier {

	final int SEUIL_DEFAUT = 100;
	
	int nbDonneeVitesse;
	int nbDonneeDirection;
	double totalDonneesVitesse;
	double totalDonneesDirection;
	
	public VisiteurMoyenne(ZonePrevision zone) {
		super(zone);
		nbDonneeVitesse=0;
		nbDonneeDirection=0;
		totalDonneesVitesse=0;
		totalDonneesDirection=0;
	}
	
	/**
	 * modification des vent se trouvant dans la zone.
	 * @param vent
	 */

	@Override
	void modification(DonneeVent vent) {
		totalDonneesVitesse +=vent.getVitesseVent();
		totalDonneesDirection +=vent.getVitesseVent();
		nbDonneeVitesse++;
		nbDonneeDirection++;
	}

	public double getMoyenneVitesse() {
		return totalDonneesVitesse/nbDonneeVitesse;
	}
	
	public double getMoyenneDirection() {
		return totalDonneesDirection/nbDonneeDirection;
	}
	



	

	
}
