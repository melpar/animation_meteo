package visiteur;

import previsionVents.DonneeVent;
import previsionVents.ListePrevision;
import previsionVents.Prevision;
import previsionVents.ZonePrevision;

public abstract class VisiteurModifier implements Visiteur {

	
	ZonePrevision zoneModifier;
	ZonePrevision zonePrevision;
	
	public VisiteurModifier(ZonePrevision zone) {
		this.zoneModifier=zone;
	}
	
	/**
	 * modification des vent se trouvant dans la zone.
	 * @param vent
	 */
	
	abstract void modification(DonneeVent vent);
	
	/**
	 * appel des vent se trouvant dans la prevision.
	 * @param prevision
	 */
	
	void zoneModifier(Prevision prevision) {
		
		double limiteX = zoneModifier.getNombreX()*zoneModifier.getPasX();
		double limiteY = zoneModifier.getNombreY()*zoneModifier.getPasY();
		for(double positionX = zoneModifier.getLatitudeHautGauche();positionX<limiteX;positionX+=zonePrevision.getPasX()) {
			for(double positionY = zoneModifier.getLongitudeHautGauche();positionY<limiteY;positionY+=zonePrevision.getPasY()) {
				int matriceX = (int)((positionX-zonePrevision.getLatitudeHautGauche())/zonePrevision.getPasX());
				int matriceY = (int)((positionY-zonePrevision.getLongitudeHautGauche())/zonePrevision.getPasY());
				DonneeVent vent = prevision.getDonneeVent(matriceX,matriceY);
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
	public void agitSur(ZonePrevision element) {

	}
	
	@Override
	public void agitSur(Prevision element) {
		zoneModifier(element);
	}
	
	@Override
	public void agitSur(DonneeVent element) {
		modification(element);
	}
	
	@Override
	public void agitSur(ElementVisitable element) {

	}
	
}
