package carte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.geotools.map.MapContext;

/**
 * definir l'action du boutton add
 * 
 * @author thomas
 *
 */
class ActionAjout implements ActionListener {
  int nb;
  MapContext map;

  ActionAjout(MapContext map) {
    this.map = map;
    nb = 0;
    System.out.println("init " + nb);

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    nb += 10;
    System.out.println("dessin " + nb);
    Dessiner dessiner1 = new Dessiner(map, nb);
  }
}