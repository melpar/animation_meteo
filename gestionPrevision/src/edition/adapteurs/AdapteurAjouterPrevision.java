package edition.adapteurs;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import previsionVents.DonneeVent;
import previsionVents.Prevision;

public class AdapteurAjouterPrevision {

	/**
	 * Prevision, Date, Donne Vent, NbX, NbY
	 * 
	 * @param previsions
	 * @param jour
	 * @param mois
	 * @param annee
	 * @param heure
	 * @param vitesse
	 * @param direction
	 */
	@SuppressWarnings("deprecation")
	public static void AjouterPrevision(List<Prevision> previsions, int jour, int mois, int annee, int heure,
			double vitesse, double direction, int nombreX, int nombreY) {
		// La prevision
		Prevision prevision = new Prevision();
		// creation de la date
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(annee, mois, jour);
		
		prevision.setDate(calendar);
		DonneeVent [][] donnees = new DonneeVent[nombreX][nombreY];
		for (int i = 0; i < nombreX; i++) {
			for (int j = 0; j < nombreY; j++) {
				
				DonneeVent donnee = new DonneeVent();
				
				donnee.setOrientationVent(direction);
				donnee.setVitesseVent(vitesse);
				
				donnees[i][j] = donnee;
			}
		}
		prevision.setMatrice(donnees);
		previsions.add(prevision);
	}
	
	public static void AjouterPrevision(List<Prevision> previsions, int jour, int mois, int annee, int heure,
			DonneeVent [][] donnees) {
		// La prevision
		Prevision prevision = new Prevision();
		// creation de la date
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
//		date.setJour(jour);
//		date.setMois(mois);
//		date.setAnnee(annee);
//		date.setHeure(heure);
		////////////////////////////////////////			Modifier date utiliser notre classe
		prevision.setDate(calendar);
		prevision.setMatrice(donnees);
		previsions.add(prevision);
	}
	
}
