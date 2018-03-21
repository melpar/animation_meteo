package test;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import edition.implementation.Json;
import modification.VisisteurMemoire;
import modification.VisiteurCoefficient;
import modification.VisiteurContrasteLineaire;
import modification.VisiteurContrasteProgressif;
import modification.VisiteurMoyenne;
import modification.VisiteurRestauration;
import previsionVents.DonneeVent;
import previsionVents.ListePrevision;
import previsionVents.ZonePrevision;
import visiteur.Visiteur;

public class TestModification {

  private ListePrevision listePrevisionTest;
  private Calendar dateTest;

  @Before
  public void initialisation() {
    this.dateTest = Calendar.getInstance();
    this.listePrevisionTest = new ListePrevision(1, 1, 5, 5, 10, 10);

    this.listePrevisionTest.ajouterPrevision(this.dateTest);
    // creation d'un vent inferieur a 190kmH
    this.listePrevisionTest.ajouterDonneeVent(this.dateTest, 10, 50, (int) 3, (int) 4);
    // creation d'un vent superieur a 190kmH
    this.listePrevisionTest.ajouterDonneeVent(this.dateTest, 20, 50, (int) 0, (int) 0);
    // creation d'un vent superieur a 190kmH
    this.listePrevisionTest.ajouterDonneeVent(this.dateTest, 30, 50, (int) 9, (int) 9);
  }

  @Test
  public void testModifierCoefficientVent() {

    DonneeVent[][] donnee = listePrevisionTest.getListePrevision().get(0).getListeDonneVent();
    DonneeVent vent = donnee[3][4];
    double ancienneValeur = vent.getVitesseVent();

    ZonePrevision zonePrevisionTest = new ZonePrevision(1, 1, 5, 5, 10, 10);
    VisiteurCoefficient modifier = new VisiteurCoefficient(zonePrevisionTest, -0.5);
    listePrevisionTest.applique(modifier);

    assertTrue(vent.getVitesseVent() == ancienneValeur / 2);
  }

  @Test
  public void testModifierZoneVent() {
    DonneeVent[][] donnee = listePrevisionTest.getListePrevision().get(0).getListeDonneVent();
    DonneeVent ventMilieu = donnee[3][4];
    DonneeVent ventDebut = donnee[0][0];
    DonneeVent ventFin = donnee[9][9];
    double ancienneValeurDebut = ventDebut.getVitesseVent();
    double ancienneValeurMilieux = ventMilieu.getVitesseVent();
    double ancienneValeurFin = ventFin.getVitesseVent();

    ZonePrevision zonePrevisionTest = new ZonePrevision(6, 3, 5, 5, 7, 7);
    VisiteurCoefficient modifier = new VisiteurCoefficient(zonePrevisionTest, -0.5);
    listePrevisionTest.applique(modifier);

    assertTrue(ventDebut.getVitesseVent() == ancienneValeurDebut);
    assertTrue(ventMilieu.getVitesseVent() == ancienneValeurMilieux / 2);
    assertTrue(ventFin.getVitesseVent() == ancienneValeurFin);
  }

  @Test
  public void testModifierContrasteProgressifVent() {
    DonneeVent[][] donnee = listePrevisionTest.getListePrevision().get(0).getListeDonneVent();
    DonneeVent ventMilieu = donnee[3][4];
    DonneeVent ventFort = donnee[0][0];
    DonneeVent ventFin = donnee[9][9];
    double ancienneValeurFort = ventFort.getVitesseVent();
    double ancienneValeurMilieux = ventMilieu.getVitesseVent();
    double ancienneValeurFin = ventFin.getVitesseVent();

    ZonePrevision zonePrevisionTest = new ZonePrevision(1, 1, 5, 5, 10, 10);
    Visiteur modifier = new VisiteurContrasteProgressif(zonePrevisionTest, 0.5, 190);// seuil �
                                                                                     // 190 kmH
    listePrevisionTest.applique(modifier);

    assertTrue(ventFort.getVitesseVent() - ancienneValeurFort * 2 < 0.1);
    assertTrue(ventMilieu.getVitesseVent() - ancienneValeurMilieux / 2 < 0.1);
    assertTrue(ventFin.getVitesseVent() - ancienneValeurFin * 2 < 0.1);
  }

  @Test
  public void testModifierContrasteLineaireVent() {
    DonneeVent[][] donnee = listePrevisionTest.getListePrevision().get(0).getListeDonneVent();
    DonneeVent ventMilieu = donnee[3][4];
    DonneeVent ventFort = donnee[0][0];
    DonneeVent ventFin = donnee[9][9];
    double ancienneValeurFort = ventFort.getVitesseVent();
    double ancienneValeurMilieux = ventMilieu.getVitesseVent();
    double ancienneValeurFin = ventFin.getVitesseVent();

    ZonePrevision zonePrevisionTest = new ZonePrevision(1, 1, 5, 5, 10, 10);
    Visiteur modifier = new VisiteurContrasteLineaire(zonePrevisionTest, 0.5, 190);// seuil � 190
                                                                                   // kmH
    listePrevisionTest.applique(modifier);

    double valeurTester = ancienneValeurFort * 2
        / (1.0 + Math.exp(-0.5 * (ancienneValeurFort - 190)));
    assertTrue(ventFort.getVitesseVent() == valeurTester);
    assertTrue(ventMilieu.getVitesseVent() < ancienneValeurMilieux);
    assertTrue(ventFin.getVitesseVent() > ancienneValeurFin);
  }

  @Test
  public void testMoyenne() {

    ZonePrevision zonePrevisionTest = new ZonePrevision(1, 1, 5, 5, 10, 10);

    VisiteurMoyenne moyenne = new VisiteurMoyenne(zonePrevisionTest);
    listePrevisionTest.applique(moyenne);
    double valeurMoyenneAncienne = moyenne.getMoyenneVitesse();

    Visiteur modifier = new VisiteurContrasteLineaire(zonePrevisionTest, 1, 100);// seuil � 190
                                                                                 // kmH
    listePrevisionTest.applique(modifier);

    moyenne.reset();
    listePrevisionTest.applique(moyenne);

    assertTrue(valeurMoyenneAncienne != moyenne.getMoyenneVitesse());
  }

  @Test
  public void testSauvegardeVent() {
    Json json = new Json();
    json.jsonWrite(listePrevisionTest, "test2.json");
    ListePrevision listePrevisionLecture = json.JsonRead("test2.json");

    DonneeVent[][] donnee = listePrevisionTest.getListePrevision().get(0).getListeDonneVent();
    DonneeVent[][] donneeEnregistre = listePrevisionLecture.getListePrevision().get(0)
        .getListeDonneVent();
    DonneeVent vent = donnee[3][4];
    DonneeVent ventEnregistrer = donneeEnregistre[3][4];

    assertTrue(vent.getVitesseVent() == ventEnregistrer.getVitesseVent());
  }

  @Test
  public void testMemoire() {
    Json json = new Json();
    json.jsonWrite(listePrevisionTest, "test2.json");
    ListePrevision listePrevisionLecture = json.JsonRead("test2.json");

    DonneeVent[][] donnee = listePrevisionTest.getListePrevision().get(0).getListeDonneVent();
    DonneeVent[][] donneeEnregistre = listePrevisionLecture.getListePrevision().get(0)
        .getListeDonneVent();
    DonneeVent vent = donnee[3][4];
    DonneeVent ventEnregistrer = donneeEnregistre[3][4];

    assertTrue(vent.getVitesseVent() == ventEnregistrer.getVitesseVent());
  }

  @Test
  public void testRestauration() {
    DonneeVent[][] donnee = listePrevisionTest.getListePrevision().get(0).getListeDonneVent();
    DonneeVent vent = donnee[3][4];
    double ancienneValeur = vent.getVitesseVent();

    ZonePrevision zonePrevisionTest = new ZonePrevision(1, 1, 5, 5, 10, 10);
    VisisteurMemoire memoire = new VisisteurMemoire();
    listePrevisionTest.applique(memoire);
    VisiteurCoefficient modifier = new VisiteurCoefficient(zonePrevisionTest, -0.5);
    listePrevisionTest.applique(modifier);
    assertTrue(vent.getVitesseVent() == ancienneValeur / 2);
    VisiteurRestauration restauration = new VisiteurRestauration(memoire);
    listePrevisionTest.applique(restauration);
    DonneeVent[][] donnee2 = listePrevisionTest.getListePrevision().get(0).getListeDonneVent();
    DonneeVent vent2 = donnee2[3][4];
    assertTrue(vent2.getVitesseVent() == ancienneValeur);
  }

}
