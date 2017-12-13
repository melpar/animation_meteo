package carte;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import org.geotools.map.MapContext;
import org.geotools.swing.action.SafeAction;

public class AfficherFleches extends SafeAction {
  MapContext map;

  public AfficherFleches(MapContext m) {
    super("Afficher");
    this.map = m;
  }

  public void action(ActionEvent e) throws Throwable {
    System.out.println("afficher");
    Dessiner dessiner = new Dessiner(map);
    List<InformationsVents> vents = new ArrayList<>();

    for (int i = 0; i < 10000000; i += 100000) {
      InformationsVents v = new InformationsVents();
      v.setPositionX(i);
      v.setPositionY(i);
      v.setDirection(7 * Math.PI / 4);
      vents.add(v);
    }

    dessiner.ajouterCalque(vents);
  }
}
