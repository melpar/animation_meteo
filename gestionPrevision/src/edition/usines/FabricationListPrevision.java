package edition.usines;

import java.util.ArrayList;

import previsionVents.ListePrevision;
import previsionVents.Prevision;
import previsionVents.ZonePrevision;

public class FabricationListPrevision {

	/**
	 * Pour la visualisation
	 * 
	 * @param latitude
	 * @param longitude
	 * @param pasX
	 * @param pasY
	 * @param nombreX
	 * @param nombreY
	 * @return
	 */
	public static ListePrevision creerListPrevisionVide(double latitude, double longitude, double pasX, double pasY,
			int nombreX, int nombreY) {

		ListePrevision listPrevisions = new ListePrevision();

		// la zone
		ZonePrevision zone = new ZonePrevision();
		zone.setLatitude(latitude);			///////////////////////// nicolas
		zone.setLongitude(longitude);		///////////////////////// nicolas
		zone.setPasX(pasX);				///////////////////////// nicolas
		zone.setPasY(pasY);			///////////////////////// nicolas
		zone.setNombreX(nombreX);		///////////////////////// nicolas
		zone.setNombreY(nombreY);		///////////////////////// nicolas

		listPrevisions.setZonePrevisions(zone); ///////////////////////// nicolas

		// liste preivision
		listPrevisions.setListePrevision(new ArrayList<Prevision>()); ///////////////////////// nicolas

		return listPrevisions;
	}
}
