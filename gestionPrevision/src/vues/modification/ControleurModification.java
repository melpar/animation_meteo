package vues.modification;

import javafx.scene.input.KeyEvent;

public class ControleurModification {

  public void keyTyped(KeyEvent k) {
    char car = k.getCharacter().charAt(0);
    if (car < '0' || car > '9') {
      k.consume();
    }
  }

}
