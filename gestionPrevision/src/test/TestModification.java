package test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import edition.implementation.Json;
import previsionVents.DonneeVent;
import previsionVents.ListePrevision;
import previsionVents.Prevision;
import previsionVents.ZonePrevision;
import visiteur.VisiteurCoefficient;

public class TestModification {
  
  private ListePrevision listePrevisionTest;
  private ListePrevision listeModificationTest;
  private Prevision previsionTest;
  private Date dateTest;
  
  
  @Before
  public void initialisation() {
    this.dateTest= new Date();
    this.listePrevisionTest=new ListePrevision(1,1,5,5,10,10);
    
    this.listePrevisionTest.ajouterPrevision(this.dateTest); 
    this.listePrevisionTest.ajouterDonneeVent(this.dateTest, 10, 50,(int) 3,(int) 4);
    this.listePrevisionTest.ajouterDonneeVent(this.dateTest, 10, 50,(int) 0,(int) 0);
    this.listePrevisionTest.ajouterDonneeVent(this.dateTest, 10, 50,(int) 9,(int) 9);
  }
  
  @Test
  public void testModifierCoefficientVent() {    

    DonneeVent[][] donnee = listePrevisionTest.getListePrevision().get(0).getListeDonneVent();
    DonneeVent vent= donnee[3][4];
    double ancienneValeur = vent.getVitesseVent();
    
    ZonePrevision zonePrevisionTest=new ZonePrevision(1,1,5,5,10,10);
    VisiteurCoefficient modifier = new VisiteurCoefficient(zonePrevisionTest, -0.5);
    listePrevisionTest.applique(modifier);
    assertTrue(vent.getVitesseVent()==ancienneValeur/2);
  }
  
  @Test
  public void testModifierZoneVent() {    
    DonneeVent[][] donnee = listePrevisionTest.getListePrevision().get(0).getListeDonneVent();
    DonneeVent ventMilieu= donnee[3][4];
    DonneeVent ventDebut= donnee[0][0];
    DonneeVent ventFin= donnee[9][9];
    double ancienneValeurDebut = ventDebut.getVitesseVent();
    double ancienneValeurMilieux = ventMilieu.getVitesseVent();
    double ancienneValeurFin = ventFin.getVitesseVent();
    
    ZonePrevision zonePrevisionTest=new ZonePrevision(6,3,5,5,7,7);
    VisiteurCoefficient modifier = new VisiteurCoefficient(zonePrevisionTest, -0.5);
    listePrevisionTest.applique(modifier);
    assertTrue(ventDebut.getVitesseVent()==ancienneValeurDebut);
    assertTrue(ventMilieu.getVitesseVent()==ancienneValeurMilieux/2);
    assertTrue(ventFin.getVitesseVent()==ancienneValeurFin);
  }
  
  @Test
  public void testModifierContrasteVent() {    
    DonneeVent[][] donnee = listePrevisionTest.getListePrevision().get(0).getListeDonneVent();
    DonneeVent ventMilieu= donnee[3][4];
    DonneeVent ventDebut= donnee[0][0];
    DonneeVent ventFin= donnee[9][9];
    double ancienneValeurDebut = ventDebut.getVitesseVent();
    double ancienneValeurMilieux = ventMilieu.getVitesseVent();
    double ancienneValeurFin = ventFin.getVitesseVent();
    
    ZonePrevision zonePrevisionTest=new ZonePrevision(6,3,5,5,7,7);
    VisiteurCoefficient modifier = new VisiteurCoefficient(zonePrevisionTest, -0.5);
    listePrevisionTest.applique(modifier);
    assertTrue(ventDebut.getVitesseVent()==ancienneValeurDebut);
    assertTrue(ventMilieu.getVitesseVent()==ancienneValeurMilieux/2);
    assertTrue(ventFin.getVitesseVent()==ancienneValeurFin);
  }
  @Test
  public void testSauvegardeVent() {    
	  Json json = new Json();
		json.JsonWrite(listePrevisionTest, "test2.json");
  }

}
