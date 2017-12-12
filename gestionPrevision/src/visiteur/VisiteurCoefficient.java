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
	
	void calculCoefficient(DonneeVent vent) {
		vent.setVitesseVent(
				vent.getVitesseVent() * (1 + coefficient));
	}
	
	/**
	 * appel des vent se trouvant dans la prevision.
	 * @param prevision
	 */
	
	void zoneModifier(Prevision prevision) {
		for(double positionX = zoneModifier.getLatitudeHautGauche();positionX<zoneModifier.getNombreX()*zoneModifier.getPasX();positionX+=zoneModifier.getPasX()) {
			for(double positionY = zoneModifier.getLongitudeHautGauche();positionX<zoneModifier.getNombreY()*zoneModifier.getPasY();positionX+=zoneModifier.getPasY()) {
				DonneeVent vent = prevision.getDonneVent((int) ((positionX-zonePrevision.getLatitudeHautGauche())/zonePrevision.getPasX()),
						(int)((positionY-zonePrevision.getLatitudeHautGauche())/zonePrevision.getPasY()));
				if(vent!=null) {
					vent.applique(this);
				}
			}
		}
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
		zoneModifier(element);
		
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
