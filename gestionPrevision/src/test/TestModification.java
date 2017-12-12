package test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

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
    this.listePrevisionTest=new ListePrevision(0.0,10.0,15.50,20.50,10,10);
    this.dateTest=new Date();
  }

  @Test
  public void testInitialisation() {
    assertTrue(this.listePrevisionTest!=null);
    assertTrue(this.listePrevisionTest.getZonePrevision()!=null);
    assertTrue(this.listePrevisionTest.getListePrevision()!=null);
  }
  
  @Test
  public void testAjouterPrevision() {
    assertTrue(this.listePrevisionTest.getNombrePrevision()==0);
    this.listePrevisionTest.ajouterPrevision(new Date());
    this.listePrevisionTest.ajouterPrevision(new Date());
    this.listePrevisionTest.ajouterPrevision(new Date());
    assertTrue(this.listePrevisionTest.getListePrevision().get(0).getDatePrevision()!=null);
    assertTrue(this.listePrevisionTest.getNombrePrevision()==3);   
  }
  
  @Test
  public void testAjouterVent() {    
    this.listePrevisionTest.ajouterPrevision(this.dateTest);
    System.out.println(this.listePrevisionTest.getListePrevision().get(0).getListeDonneVent());
    this.listePrevisionTest.ajouterDonneeVent(this.dateTest, 10, 50,(int) 3,(int) 4);
    DonneeVent[][] donnee = listePrevisionTest.getListePrevision().get(0).getListeDonneVent();
    DonneeVent vent= donnee[3][4];
    System.out.println(vent.getOrientationVent());
    assertTrue(vent.getOrientationVent()-50<1);

  }
  
  @Test
  public void testModifierVent() {    
    this.listePrevisionTest.ajouterPrevision(this.dateTest);
    System.out.println(this.listePrevisionTest.getListePrevision().get(0).getListeDonneVent());
    this.listePrevisionTest.ajouterDonneeVent(this.dateTest, 10, 50,(int) 3,(int) 4);
    this.listePrevisionTest.ajouterDonneeVent(this.dateTest, 10, 50,(int) 0,(int) 0);
    this.listePrevisionTest.ajouterDonneeVent(this.dateTest, 10, 50,(int) 9,(int) 9);
    DonneeVent[][] donnee = listePrevisionTest.getListePrevision().get(0).getListeDonneVent();
    DonneeVent vent= donnee[3][4];
    //System.out.println(vent.getVitesseVent());
    double ancienneValeur = vent.getVitesseVent();
    
    //ZonePrevision zonePrevisionTest=new ZonePrevision(1.0,11.0,7.75,10.25,20,20);
    ZonePrevision zonePrevisionTest=new ZonePrevision(0.0,10.0,15.50,20.50,10,10);
    VisiteurCoefficient modifier = new VisiteurCoefficient(zonePrevisionTest, -0.5);
    listePrevisionTest.applique(modifier);
    assertTrue(vent.getVitesseVent()==ancienneValeur/2);
    //System.out.println(vent.getVitesseVent());
  }
  

}
