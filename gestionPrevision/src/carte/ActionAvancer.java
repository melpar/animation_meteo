package carte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * definir l'action du boutton editer
 * 
 * @author thomas
 *
 */
class ActionAvancer implements ActionListener {
  int nbTest;

  ActionAvancer() {

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println("avancer");

  }
}