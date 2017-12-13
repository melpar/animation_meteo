package carte;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AffichageEdition extends JFrame {
  
  JPanel general = new JPanel();
  JPanel bandeau = new JPanel();
  JPanel liste = new JPanel();
  JPanel bandeauliste = new JPanel();
  

  JLabel vitesse = new JLabel("Vitesse");
  JLabel direction = new JLabel("Direction");
  JLabel  duree= new JLabel("duree");
  JLabel date = new JLabel("Date");
  
  JTextField vitessechamp = new JTextField();
  JTextField directionchamp = new JTextField();
  JTextField datechamp = new JTextField();

  JTable tableau = new JTable();
  
  
  
  JLabel dateListe = new JLabel("Date");
  JLabel vitesseListe = new JLabel("Vitesse");
  JLabel directionListe = new JLabel("Direction");
  
  JButton ajouter= new JButton("Ajouter"); 
  JButton creer= new JButton("Créer"); 
  
  Integer[] tab = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};
  JComboBox<Integer> listeheure = new JComboBox<Integer>(tab);
  
  Integer[] tab1 = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23};
  JComboBox<Integer> listeheure1 = new JComboBox<Integer>(tab1);
  
  public AffichageEdition() {
    this.setTitle("Edition");
    this.setSize(600, 400);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setVisible(true);
    
    general.setLayout( new GridLayout(4,1));
    bandeau.setLayout(new GridBagLayout());
    liste.setLayout(new GridBagLayout());
    bandeauliste.setLayout(new GridBagLayout());
    
    
    
    bandeau.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
    bandeauliste.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
    
    
    GridBagConstraints parametre = new GridBagConstraints();
    
    parametre.anchor = GridBagConstraints.NORTHWEST;
    parametre.insets = new Insets(0, 30, 5, 5);
    parametre.weightx = 1;
    parametre.weighty = 5;
    parametre.fill = GridBagConstraints.HORIZONTAL;
    
    bandeau.add(vitesse,parametre);
    parametre.gridx = 1;
    bandeau.add(direction,parametre);
    parametre.gridx = 2;
    bandeau.add(duree, parametre);
    parametre.gridx = 3;
    bandeau.add(date, parametre);
    parametre.gridx = 4;
    bandeau.add(ajouter, parametre);
    parametre.gridx=0;
    parametre.gridy=1;
    bandeau.add(vitessechamp, parametre);
    parametre.gridx=1;
    bandeau.add(directionchamp, parametre);
    parametre.gridx =2;
    bandeau.add(listeheure, parametre);
    parametre.gridx = 3;
    bandeau.add(datechamp, parametre);
    parametre.gridx = 4;
    bandeau.add(creer, parametre);
    parametre.gridy=2;
    parametre.gridx =3;
    bandeau.add(listeheure1, parametre);
    
    parametre.gridx=0;
    parametre.gridy=0;
    //parametre.fill = GridBagConstraints.NONE;
    

    
    
    bandeauliste.add(dateListe,parametre);
    parametre.gridx=1;
    bandeauliste.add(vitesseListe,parametre);
    parametre.gridx=2;
    bandeauliste.add(directionListe,parametre);


    
    
    
    general.add(bandeau);
    general.add(bandeauliste);
    //general.add(liste);
    this.setContentPane(general);
  }
}
