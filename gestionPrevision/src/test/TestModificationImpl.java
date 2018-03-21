package test;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import edition.implementation.Json;
import modification.ModifierImpl;
import modification.VisisteurMemoire;
import modification.VisiteurCoefficient;
import modification.VisiteurRestauration;
import previsionVents.DonneeVent;
import previsionVents.FacadePrevisionVents;
import previsionVents.ListePrevision;
import previsionVents.ZonePrevision;

public class TestModificationImpl {

  private ListePrevision listePrevisionTest;
  private Calendar dateTest;
  private ModifierImpl modifier;

  @Before
  public void initialisation() {

    dateTest = Calendar.getInstance();
    this.listePrevisionTest = new ListePrevision(1, 1, 5, 5, 10, 10);

    this.listePrevisionTest.ajouterPrevision(this.dateTest);
    // creation d'un vent inferieur a 190kmH
    this.listePrevisionTest.ajouterDonneeVent(this.dateTest, 10, 50, (int) 3, (int) 4);
    // creation d'un vent superieur a 190kmH
    this.listePrevisionTest.ajouterDonneeVent(this.dateTest, 20, 50, (int) 0, (int) 0);
    // creation d'un vent superieur a 190kmH
    this.listePrevisionTest.ajouterDonneeVent(this.dateTest, 30, 50, (int) 9, (int) 9);

    modifier = new ModifierImpl();
  }

  @Test
  public void testModifierCoefficientVent() {

    DonneeVent[][] donnee = FacadePrevisionVents.getFacadePrevisionVents().getPrevisions()
        .getListePrevision().get(0).getListeDonneVent();
    DonneeVent vent = donnee[3][4];
    double ancienneValeur = vent.getVitesseVent();

    ZonePrevision zonePrevisionTest = new ZonePrevision(1, 1, 5, 5, 10, 10);
    this.modifier.modifierCoefficientVent(zonePrevisionTest, dateTest, (float) -0.5);

    double vitesseTest = FacadePrevisionVents.getFacadePrevisionVents().getPrevisions()
        .getListePrevision().get(0).getListeDonneVent()[3][4].getVitesseVent();
    assertTrue(vitesseTest == ancienneValeur / 2);
  }

  @Test
  public void testModifierZoneVent() {
    DonneeVent[][] donnee = FacadePrevisionVents.getFacadePrevisionVents().getPrevisions()
        .getListePrevision().get(0).getListeDonneVent();
    DonneeVent ventMilieu = donnee[3][4];
    DonneeVent ventDebut = donnee[0][0];
    DonneeVent ventFin = donnee[9][9];
    double ancienneValeurDebut = ventDebut.getVitesseVent();
    double ancienneValeurMilieux = ventMilieu.getVitesseVent();
    double ancienneValeurFin = ventFin.getVitesseVent();

    ZonePrevision zonePrevisionTest = new ZonePrevision(6, 3, 5, 5, 7, 7);
    this.modifier.modifierCoefficientVent(zonePrevisionTest, dateTest, (float) -0.5);

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
    this.modifier.modifierContrasteProgressifVent(zonePrevisionTest, dateTest, (float) 0.5, 190);
    ;

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
    this.modifier.modifierContrasteLineaireVent(zonePrevisionTest, dateTest, (float) 0.5, 190);

    double valeurTester = ancienneValeurFort * 2
        / (1.0 + Math.exp(-0.5 * (ancienneValeurFort - 190)));
    assertTrue(ventFort.getVitesseVent() == valeurTester);
    assertTrue(ventMilieu.getVitesseVent() < ancienneValeurMilieux);
    assertTrue(ventFin.getVitesseVent() > ancienneValeurFin);
  }

  @Test
  public void testMoyenne() {
    ZonePrevision zonePrevisionTest = new ZonePrevision(1, 1, 5, 5, 10, 10);
    double valeurMoyenneAncienne = this.modifier.getMoyenneVitesseVent(zonePrevisionTest, dateTest);
    this.modifier.modifierContrasteLineaireVent(zonePrevisionTest, dateTest, 1, 100);
    assertTrue(
        valeurMoyenneAncienne != this.modifier.getMoyenneVitesseVent(zonePrevisionTest, dateTest));
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
    json.jsonWrite(listePrevisionTest, "test3.json");
    ListePrevision listePrevisionLecture = json.JsonRead("test3.json");

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
    VisiteurCoefficient modifier = new VisiteurCoefficient(zonePrevisionTest, dateTest, -0.5);
    listePrevisionTest.applique(modifier);
    assertTrue(vent.getVitesseVent() == ancienneValeur / 2);
    VisiteurRestauration restauration = new VisiteurRestauration(memoire);
    listePrevisionTest.applique(restauration);
    DonneeVent[][] donnee2 = listePrevisionTest.getListePrevision().get(0).getListeDonneVent();
    DonneeVent vent2 = donnee2[3][4];
    assertTrue(vent2.getVitesseVent() == ancienneValeur);
  }

  @Test
  public void testPileRestauration1() {

    DonneeVent[][] donnee = FacadePrevisionVents.getFacadePrevisionVents().getPrevisions()
        .getListePrevision().get(0).getListeDonneVent();
    DonneeVent vent = donnee[3][4];
    double ancienneValeur = vent.getVitesseVent();

    ZonePrevision zonePrevisionTest = new ZonePrevision(1, 1, 5, 5, 10, 10);

    this.modifier.modifierCoefficientVent(zonePrevisionTest, dateTest, (float) -0.5);

    double vitesseTest = FacadePrevisionVents.getFacadePrevisionVents().getPrevisions()
        .getListePrevision().get(0).getListeDonneVent()[3][4].getVitesseVent();
    assertTrue(vitesseTest == ancienneValeur / 2);

    this.modifier.restaureArriere();

    double vitesseTest2 = FacadePrevisionVents.getFacadePrevisionVents().getPrevisions()
        .getListePrevision().get(0).getListeDonneVent()[3][4].getVitesseVent();
    assertTrue(vitesseTest2 == ancienneValeur);
  }

  @Test
  public void testPileRestauration2() {

    DonneeVent[][] donnee = FacadePrevisionVents.getFacadePrevisionVents().getPrevisions()
        .getListePrevision().get(0).getListeDonneVent();
    DonneeVent vent = donnee[3][4];
    double ancienneValeur = vent.getVitesseVent();

    ZonePrevision zonePrevisionTest = new ZonePrevision(1, 1, 5, 5, 10, 10);

    this.modifier.modifierCoefficientVent(zonePrevisionTest, dateTest, (float) -0.5);

    this.modifier.restaureArriere();
    this.modifier.restaureAvant();

    double vitesseTest2 = FacadePrevisionVents.getFacadePrevisionVents().getPrevisions()
        .getListePrevision().get(0).getListeDonneVent()[3][4].getVitesseVent();
    assertTrue(vitesseTest2 == ancienneValeur / 2);
  }

  @Test
  public void testPileRestauration3() {
    assertTrue(!this.modifier.restaureArriere());
    assertTrue(!this.modifier.restaureAvant());
  }

  @Test
  public void testPileRestauration4() {
    ZonePrevision zonePrevisionTest = new ZonePrevision(1, 1, 5, 5, 10, 10);
    this.modifier.modifierCoefficientVent(zonePrevisionTest, dateTest, (float) -0.5);
    assertTrue(this.modifier.restaureArriere());
    assertTrue(this.modifier.restaureAvant());
    assertTrue(!this.modifier.restaureAvant());
  }

  @Test
  public void testPileRestauration5() {
    ZonePrevision zonePrevisionTest = new ZonePrevision(1, 1, 5, 5, 10, 10);
    this.modifier.modifierCoefficientVent(zonePrevisionTest, dateTest, (float) -0.5);
    assertTrue(this.modifier.restaureArriere());
    assertTrue(!this.modifier.restaureArriere());
  }
}
