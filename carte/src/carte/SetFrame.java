package carte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.geotools.map.MapContext;
import org.geotools.swing.JMapFrame;

public class SetFrame {

  JMapFrame frame;
  MapContext map;
  private JLabel labelDate;
  // Dessiner dessiner;

  /**
   * initialiser les boutton
   * 
   * @param frame
   * @param map
   */
  SetFrame(JMapFrame frame, MapContext map) {
    this.frame = frame;
    this.map = map;
    addbouttonReculer();
    addLabelDate();
    addbouttonAvancer();

    addbouttonModifier();
    addbouttonEditer();
    // dessiner = new Dessiner(map);

  }

  /**
   * ajouter un boutton add en listener
   */
  void addbouttonEditer() {
    JButton boutonEditer = new JButton("Editer");
    boutonEditer.setEnabled(false);
    boutonEditer.addActionListener(new ActionEditer());
    frame.getToolBar().add(boutonEditer);
  }

  /**
   * ajouter un boutton add en listener
   */
  void addbouttonReculer() {
    JButton boutonEditer = new JButton("<");
    boutonEditer.addActionListener(new ActionReculer());
    frame.getToolBar().add(boutonEditer);
  }

  /**
   * definir l'action du boutton editer
   * 
   * @author thomas
   *
   */
  class ActionReculer implements ActionListener {
    int nbTest;

    ActionReculer() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
      System.out.println("reculer");

    }
  }

  /**
   * ajouter un boutton add en listener
   */
  void addbouttonAvancer() {
    JButton boutonEditer = new JButton(">");
    boutonEditer.addActionListener(new ActionAvancer());
    frame.getToolBar().add(boutonEditer);
  }

  /**
   * ajouter un boutton delete en listener
   */
  void addbouttonModifier() {
    JButton boutonSupprimer = new JButton("Modifier");
    boutonSupprimer.setEnabled(false);
    boutonSupprimer.addActionListener(new ActionModifier());
    frame.getToolBar().add(boutonSupprimer);
  }

  /**
   * definir l'action du boutton modifier
   * 
   * @author thomas
   *
   */
  class ActionModifier implements ActionListener {
    ActionModifier() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      System.out.println("modifier");
    }
  }

  /**
   * definir l'action du boutton delete
   * 
   * @author thomas
   *
   */
  class ActionDelete implements ActionListener {
    ActionDelete() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub

      System.out.println("remove ");
      if (map.getLayerCount() > 1)
        map.removeLayer(1);
    }
  }

  /**
   * ajouter un boutton delete en listener
   */
  void addLabelDate() {
    this.labelDate = new JLabel();
    frame.getToolBar().add(this.labelDate);
  }

  public void setLabelDateValue(String labelDateValue) {
    this.labelDate.setText(labelDateValue);
  }

}
