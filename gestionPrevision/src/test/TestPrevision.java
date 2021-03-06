package test;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import previsionVents.DonneeVent;
import previsionVents.FabriqueDonnee;
import previsionVents.ListePrevision;
import previsionVents.Prevision;
import previsionVents.ZonePrevision;

public class TestPrevision {

  private ListePrevision listePrevisionTest;
  private Prevision previsionTest;
  private DonneeVent donneeTest;
  private FabriqueDonnee fabriquetest;

  /**
   * Initialise les données nécessaires aux tests.
   */
  @Before
  public void initialisation() {
    this.fabriquetest = new FabriqueDonnee();
    this.donneeTest = this.fabriquetest.creeDonneeVent(10, 50);
    this.previsionTest = new Prevision(Calendar.getInstance(), 10, 10);
    this.listePrevisionTest = new ListePrevision(0.0, 10.0, 15.50, 20.50, 10, 10);

  }

  @Test
  public void testInitialisationDonnee() {
    assertTrue(this.donneeTest != null);
    assertTrue(this.donneeTest.getOrientationVent() < 1);
    assertTrue(this.donneeTest.getVitesseVent() - 183 < 1);
  }

  @Test
  public void testInitialisationPrevision() {
    assertTrue(this.previsionTest != null);
    assertTrue(this.previsionTest.getDatePrevision() != null);
    DonneeVent[][] donnee = this.previsionTest.getListeDonneVent();
    for (int i = 0; i < donnee.length; i++) {
      for (int j = 0; j < donnee[i].length; j++) {
        assertTrue(donnee[i][j].getVitesseVent() == 0);
        assertTrue(donnee[i][j].getOrientationVent() == 0);
      }
    }
  }

  @Test
  public void testAjouterPrevision() {
    assertTrue(this.previsionTest.getDonneeVent(3, 4).getOrientationVent() == 0);
    assertTrue(this.previsionTest.getDonneeVent(3, 4).getVitesseVent() == 0);
    this.previsionTest.ajouterDonneeVent(10, 50, 3, 4);
    assertTrue(this.previsionTest.getDonneeVent(3, 4).getOrientationVent() < 1);
    assertTrue(this.previsionTest.getDonneeVent(3, 4).getVitesseVent() - 183 < 1);
  }

  @Test
  public void testInitialisationListePrevision() {
    assertTrue(this.listePrevisionTest != null);
    assertTrue(this.listePrevisionTest.getZonePrevision() != null);
    assertTrue(this.listePrevisionTest.getListePrevision() != null);
  }

  @Test
  public void testAjouterPrevisionListePrevision() {
    assertTrue(this.listePrevisionTest.getNombrePrevision() == 0);
    this.listePrevisionTest.ajouterPrevision(Calendar.getInstance());
    this.listePrevisionTest.ajouterPrevision(Calendar.getInstance());
    this.listePrevisionTest.ajouterPrevision(Calendar.getInstance());
    assertTrue(this.listePrevisionTest.getListePrevision().get(0).getDatePrevision() != null);
    assertTrue(this.listePrevisionTest.getNombrePrevision() == 3);
  }

  @Test
  public void testZonePrevision() {
    ZonePrevision zone = this.listePrevisionTest.getZonePrevision();
    assertTrue(zone.getLongitudeHautGauche() == 10.0);
    assertTrue(zone.getLatitudeHautGauche() == 0.0);
    assertTrue(zone.getNombreX() == 10);
    assertTrue(zone.getNombreY() == 10);
    assertTrue(zone.getPasX() == 15.50);
    assertTrue(zone.getPasY() == 20.50);
  }

  @Test
  public void testAjouterVent() {
    this.listePrevisionTest.ajouterPrevision(Calendar.getInstance());
    this.listePrevisionTest.ajouterDonneeVent(Calendar.getInstance(), 10, 50, (int) 3, (int) 4);
    DonneeVent[][] donnee = listePrevisionTest.getListePrevision().get(0).getListeDonneVent();
    DonneeVent vent = donnee[3][4];
    System.out.println(vent.getVitesseVent());
    System.out.println(vent.getOrientationVent());
    assertTrue(vent.getOrientationVent() < 1);
    assertTrue(vent.getVitesseVent() - 183 < 1);

  }

}
