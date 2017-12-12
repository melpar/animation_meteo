package test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

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
  }

  @Test
  public void testInitialisation() {
    assertTrue(this.listePrevisionTest!=null);
    assertTrue(this.previsionTest.size()==0);
  }
  
  public void testAjouter() {
    
  }

}
