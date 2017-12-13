package edition.implementation;

import java.util.ArrayList;
import java.util.Date;

import previsionVents.DonneeVent;
import previsionVents.ListePrevision;
import previsionVents.Prevision;
import previsionVents.ZonePrevision;

public class Edition {

	final int NOMBRE_POINT = 3;

	ZonePrevision zone;
	ArrayList<Prevision> previsions;

	public Edition(double latitude1, double longitude1, double latitude2, double longitude2) {
		super();

		this.zone = new ZonePrevision();
		this.zone.setLatitude(latitude1);
		this.zone.setLongitude(longitude1);
		this.zone.setNombreX(this.NOMBRE_POINT);
		this.zone.setNombreY(this.NOMBRE_POINT);
		this.zone.setPasX((latitude2 - latitude1) / 2);
		this.zone.setPasY((longitude2 - longitude1) / 2);

		this.previsions = new ArrayList<>();
	}

	@SuppressWarnings("deprecation")
	public void ajouterPrevision(Date date, int duree, double vitesse, double direction) {
		for (int i = 0; i < duree; i++) {
			Prevision prevision = new Prevision();
			DonneeVent[][] donnees = new DonneeVent[this.NOMBRE_POINT][this.NOMBRE_POINT];
			for (int j = 0; j < this.NOMBRE_POINT; j++) {
				for (int k = 0; k < this.NOMBRE_POINT; k++) {
					donnees[j][k] = new DonneeVent();
					donnees[j][k].setOrientationVent(direction);
					donnees[j][k].setVitesseVent(vitesse);
				}
			}
			prevision.setMatrice(donnees);

			Date date2 = (Date) date.clone();
			date2.setHours(date.getHours() + i);
			prevision.setDate(date2);

			this.previsions.add(prevision);
		}
	}

	public ListePrevision creerListPrevision() {
		ListePrevision listePrevision = new ListePrevision();
		listePrevision.setZonePrevisions(this.zone);
		listePrevision.setListePrevision(this.previsions);
		return listePrevision;
	}
}
