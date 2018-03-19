package modification;

import previsionVents.DonneeVent;
import previsionVents.ListePrevision;
import previsionVents.Prevision;
import previsionVents.ZonePrevision;
import visiteur.ElementVisitable;
import visiteur.Visiteur;

public class VisisteurMemoire implements Visiteur {

  private ListePrevision sauvegarde;
  
  @Override
  public void agitSur(ElementVisitable element) {
    // TODO Auto-generated method stub

  }

  @Override
  public void agitSur(DonneeVent element) {
    // TODO Auto-generated method stub

  }

  @Override
  public void agitSur(ListePrevision element) {
    sauvegarde = element;
  }

  @Override
  public void agitSur(Prevision element) {
    // TODO Auto-generated method stub

  }

  @Override
  public void agitSur(ZonePrevision element) {
    // TODO Auto-generated method stub

  }

}
