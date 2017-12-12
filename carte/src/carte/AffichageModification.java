package carte;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class AffichageModification extends JFrame {
  

  
  
  JPanel general = new JPanel();
  JPanel choixModification = new JPanel();
  JPanel parametreContraste = new JPanel();
  JPanel parametreContrastel3 = new JPanel();
  JPanel niveau = new JPanel();
  JPanel duree = new JPanel();
  
  JTextField valeurseuil = new JTextField();
  
//création des boutons
  JRadioButton linéaire = new JRadioButton("Linéaire ");
  JRadioButton progressif = new JRadioButton("Progressif");
  JRadioButton coefficient = new JRadioButton("Coefficient");
  
  //création d'un groupe de bouton pour gerer liées les boutons radio
  ButtonGroup group = new ButtonGroup();
  
  JLabel titrecontraste = new JLabel("Paramétre du contraste :");
  JLabel soustitreseuil = new JLabel("Seuil");
  JLabel soustitrevaleurseuil = new JLabel("Valeur du seuil");
  
  JLabel niveautext = new JLabel("Niveau");
  JLabel coefficienttext = new JLabel("% de coefficient");
  JLabel maxcoef = new JLabel("Maximum : 100 %");

  public AffichageModification() {
    
    //definissions du titre
    this.setTitle("Modification");
    
    //définion de la taille
    this.setSize(600, 400);
    
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    this.setVisible(true);
    
    
    
    general.setLayout(new GridLayout(4,1));
    choixModification.setLayout(new GridBagLayout());
    parametreContraste.setLayout(new GridBagLayout());
    parametreContrastel3.setLayout(new GridBagLayout());
    
    GridBagConstraints modification = new GridBagConstraints();
    modification.fill = GridBagConstraints.BOTH;
    modification.ipady = GridBagConstraints.WEST;
    modification.insets = new Insets(5, 30, 5, 5);
    modification.weightx  = 1;
    modification.weighty = 3;
    
    parametreContraste.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
    niveau.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
    duree.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));

    
    
    group.add(linéaire);
    group.add(progressif);
    group.add(coefficient);

    choixModification.add(linéaire,modification);
    modification.gridy=1;
    choixModification.add(progressif,modification);
    modification.gridy=2;
    choixModification.add(coefficient,modification);
    modification.gridy=0;
    parametreContraste.add(titrecontraste,modification);
    modification.gridy=1;
    modification.insets = new Insets(5, 10, 5, 5);
    parametreContraste.add(soustitreseuil,modification);
    modification.insets = new Insets(5, 30, 5, 0);
    modification.gridy=2;
    parametreContraste.add(soustitrevaleurseuil,modification);
    modification.gridx=1;
    modification.insets = new Insets(5, 5, 5, 40);
    parametreContraste.add(valeurseuil,modification);
    
    general.add(choixModification);
    general.add(parametreContraste);
    general.add(niveau);
    general.add(duree);
    
    this.setContentPane(general);

  }

  

}
