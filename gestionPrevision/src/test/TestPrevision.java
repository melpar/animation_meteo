package test;

import static org.junit.Assert.*;

import java.util.Date;

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
  private Date dateTest;
  private DonneeVent donneeTest;
  private FabriqueDonnee fabriquetest;

  
  
  @Before
  public void initialisation() {    
    this.dateTest= new Date();
    this.fabriquetest = new FabriqueDonnee();
    this.donneeTest =  this.fabriquetest.creeDonneeVent(10, 50);
    this.previsionTest= new Prevision(this.dateTest, 10, 10);   
    this.listePrevisionTest=new ListePrevision(0.0,10.0,15.50,20.50,10,10);

  }
  
  @Test
  public void testInitialisationDonnee() {
    assertTrue(this.donneeTest!=null);
    assertTrue(this.donneeTest.getOrientationVent()-50<1);
    assertTrue(this.donneeTest.getVitesseVent()>0);    
  }
  
  @Test
  public void testInitialisationPrevision() {
    assertTrue(this.previsionTest!=null);
    assertTrue(this.previsionTest.getDatePrevision()!=null);
    DonneeVent[][] donnee=this.previsionTest.getListeDonneVent();
    for(int i=0;i<donnee.length;i++) {
      for(int j=0;j<donnee[i].length;j++) {
        assertTrue(donnee[i][j].getVitesseVent()==0);
        assertTrue(donnee[i][j].getOrientationVent()==0);
      }
    }
  }
  
  @Test
  public void testajouterPrevision() {
    assertTrue(this.previsionTest.getDonneeVent(3, 4).getOrientationVent()==0);
    assertTrue(this.previsionTest.getDonneeVent(3, 4).getVitesseVent()==0);
    this.previsionTest.ajouterDonneeVent(10, 50, 3, 4);
    assertTrue(this.previsionTest.getDonneeVent(3, 4).getOrientationVent()-50<1);
    assertTrue(this.previsionTest.getDonneeVent(3, 4).getVitesseVent()>0);
  }

  @Test
  public void testInitialisationListePrevision() {
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
  public void testZonePrevision() {
     ZonePrevision zone=this.listePrevisionTest.getZonePrevision();
     assertTrue(zone.getLongitudeHautGauche()==0.0);
     assertTrue(zone.getLatitudeHautGauche()==10.0);
  }
  
  @Test
  public void testAjouterVent() {    
    this.listePrevisionTest.ajouterPrevision(this.dateTest);
    this.listePrevisionTest.ajouterDonneeVent(this.dateTest, 10, 50, (int)3,(int) 4);
    DonneeVent[][] donnee = listePrevisionTest.getListePrevision().get(0).getListeDonneVent();
    DonneeVent vent= donnee[3][4];
    System.out.println(vent.getVitesseVent());
    System.out.println(vent.getOrientationVent());
    assertTrue(vent.getOrientationVent()<1);
    assertTrue(vent.getVitesseVent()-183<1);
    
  }
  
  
  

}
