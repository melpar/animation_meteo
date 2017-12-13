package carte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * definir l'action du boutton editer
 * 
 * @author thomas
 *
 */
class ActionEditer implements ActionListener {
  int nbTest;

  ActionEditer() {

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println("editer");

  }
}
