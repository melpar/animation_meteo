package edition.date;

public class Date {

	int jour;
	int mois;
	int annee;
	int heure;

	public Date() {
		super();
	}

	public int getJour() {
		return jour;
	}

	public void setJour(int jour) {
		this.jour = jour;
	}

	public int getMois() {
		return mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public int getHeure() {
		return heure;
	}

	public void setHeure(int heure) {
		this.heure = heure;
	}

	@Override
	public String toString() {
		return "Date [jour=" + jour + ", mois=" + mois + ", annee=" + annee + ", heure=" + heure + "]";
	}

}
