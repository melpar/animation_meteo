package test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import previsionVents.DonneeVent;
import previsionVents.ListePrevision;
import previsionVents.Prevision;

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
  public void testAjouterVent() {    
    this.listePrevisionTest.ajouterPrevision(this.dateTest);
    System.out.println(this.listePrevisionTest.getListePrevision().get(0).getListeDonneVent());
    this.listePrevisionTest.ajouterDonneeVent(this.dateTest, 10, 50, 3, 4);
    DonneeVent vent=this.listePrevisionTest.getListePrevision().get(0).getDonneVent(3, 4);
    System.out.println(vent.getOrientationVent());
    assertTrue(vent.getOrientationVent()==50.9901951359);

  }
  

}
