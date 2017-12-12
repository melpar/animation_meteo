package test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import previsionVents.DonneeVent;
import previsionVents.ListePrevision;
import previsionVents.Prevision;
import previsionVents.ZonePrevision;

public class TestPrevision {
  
  private ListePrevision listePrevisionTest;
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
    assertTrue(vent.getOrientationVent()-50<1);
  }
  
  
  

}
