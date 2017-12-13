
package carte;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class AffichageModification extends JFrame {

  JPanel general = new JPanel();
  
  JPanel choixModification = new JPanel();
  JPanel parametreContraste = new JPanel();
  JPanel niveau = new JPanel();
  JPanel duree = new JPanel();

  JTextField valeurseuil = new JTextField();
  
  Double max = 100.00;
  SpinnerNumberModel model = new SpinnerNumberModel(0,0,100,1);
  JSpinner coeffisientspin = new JSpinner(model);

  // création des boutons
  JRadioButton lineaire = new JRadioButton("Lineaire ");
  JRadioButton progressif = new JRadioButton("Progressif");
  JRadioButton coefficient = new JRadioButton("Coefficient");

  // création d'un groupe de bouton pour gerer liées les boutons radio
  ButtonGroup group = new ButtonGroup();

  JLabel titrecontraste = new JLabel("Paramétre du contraste :");
  JLabel soustitreseuil = new JLabel("Seuil");
  JLabel soustitrevaleurseuil = new JLabel("Valeur du seuil");
  JLabel uniteseuil = new JLabel("Entrée une vitesse");

  JLabel niveautext = new JLabel("Niveau");
  JLabel coefficienttext = new JLabel("% de coefficient");
  JLabel maxcoef = new JLabel("Maximum : 100 %");
  
  JLabel plagehoraire = new JLabel("Plage horaire");
  JLabel textduree = new JLabel("Durée de la modification");
  JLabel maxduree= new JLabel("Maximum : 24 h");
  
  Integer[] tab = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};
  JComboBox<Integer> dureeliste = new JComboBox<Integer>(tab);
  
  JButton annuler = new JButton("Annuler");
  JButton Appliquer = new JButton("Appliquer");

  public AffichageModification() {

    // definissions du titre
    this.setTitle("Modification");

    // définion de la taille
    this.setSize(600, 400);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setVisible(true);

    general.setLayout(new GridLayout(4, 1));
    choixModification.setLayout(new GridBagLayout());
    parametreContraste.setLayout(new GridBagLayout());
    niveau.setLayout(new GridBagLayout());
    duree.setLayout(new GridBagLayout());

    GridBagConstraints modification = new GridBagConstraints();
    modification.fill = GridBagConstraints.HORIZONTAL;
    modification.insets = new Insets(5, 30, 5, 5);
    modification.weightx = 1;
    modification.weighty = 3;
    modification.anchor = GridBagConstraints.LINE_START;

    parametreContraste.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
    niveau.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
    duree.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));

    group.add(lineaire);
    group.add(progressif);
    group.add(coefficient);

    choixModification.add(lineaire, modification);
    modification.gridy = 1;
    choixModification.add(progressif, modification);
    modification.gridy = 2;
    choixModification.add(coefficient, modification);
    modification.gridy = 0;
    
    parametreContraste.add(titrecontraste, modification);
    modification.gridy = 1;
    modification.insets = new Insets(5, 10, 5, 5);
    parametreContraste.add(soustitreseuil, modification);
    modification.insets = new Insets(5, 30, 5, 0);
    modification.gridy = 2;
    parametreContraste.add(soustitrevaleurseuil, modification);
    modification.gridx = 1;
    modification.insets = new Insets(5, 30, 5, 5);
    parametreContraste.add(valeurseuil, modification);
    modification.gridx=2;
    parametreContraste.add(uniteseuil,modification);
    modification.insets = new Insets(5, 10, 5, 0);
    modification.gridx = 0;
    modification.gridy = 0;
    
    niveau.add(niveautext,modification);    
    modification.gridy = 1;
    modification.insets = new Insets(5, 30, 5, 0);
    niveau.add(coefficienttext,modification);
    modification.gridx = 1;
    niveau.add(coeffisientspin,modification);
    modification.gridx =2;
   
    niveau.add(maxcoef,modification);
    
    modification.gridx=0;
    modification.gridy = 0;
    modification.insets = new Insets(5, 10, 5, 0);
    duree.add(plagehoraire,modification);
    modification.gridy=1;
    modification.insets = new Insets(5, 30, 5, 0);
    duree.add(textduree,modification);
    modification.gridx = 2;
    duree.add(dureeliste,modification);
    modification.gridx = 3;
    duree.add(maxduree,modification);
    
    modification.gridy=2;
    modification.gridx = 2;
    duree.add(annuler,modification);
    modification.gridx = 3;
    duree.add(Appliquer,modification);

    general.add(choixModification);
    general.add(parametreContraste);
    general.add(niveau);
    general.add(duree);

    this.setContentPane(general);

  }

}
