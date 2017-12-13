package carte;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class AffichageConfiguration extends JFrame {
  JPanel general = new JPanel();
  JPanel choixUnite = new JPanel();
  JPanel choixRepresentation = new JPanel();
  JPanel choixDossier = new JPanel();
  JPanel choixDensite = new JPanel();
  JPanel choixTelechargement = new JPanel();

  JTextField valeurseuil = new JTextField();

  // création des boutons
  JRadioButton kmh = new JRadioButton("km/h");
  JRadioButton noeud = new JRadioButton("noeud");
  JRadioButton mph = new JRadioButton("mph");
  JRadioButton fleches = new JRadioButton("Fleches");
  JRadioButton couleurs = new JRadioButton("Couleurs");
  JRadioButton barbules = new JRadioButton("Barbules");
  JRadioButton supprimer = new JRadioButton("Supprimer les fichiers précédents");
  JRadioButton conserver = new JRadioButton("Conserver les fichiers précédents");
  // création d'un groupe de bouton pour gerer liées les boutons radio
  ButtonGroup groupUnite = new ButtonGroup();
  ButtonGroup groupRepresentation = new ButtonGroup();
  ButtonGroup groupTelechargement = new ButtonGroup();

  JLabel labelFichier = new JLabel("h:/");
  JButton boutonFichier = new JButton("Choisir");
  JButton boutonValider = new JButton("Valider");
  JButton boutonAnnuler = new JButton("Annuler");

  public AffichageConfiguration() {

    // definissions du titre
    this.setTitle("Configuration");

    // définion de la taille
    this.setSize(600, 500);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    general.setLayout(new GridLayout(5, 1));
    initChoixUnite();

    initChoixRepresentation();

    initChoixStockage();

    initChoixTelechargement();
    
    initChoixDensite();
    ValiderConfiguration valider = new ValiderConfiguration();
    valider.setConfig(this);
    boutonValider.addMouseListener(valider);

    boutonAnnuler.addMouseListener(new MouseListener() {
      @Override
      public void mouseReleased(MouseEvent e) {
      }

      @Override
      public void mousePressed(MouseEvent e) {
      }

      @Override
      public void mouseExited(MouseEvent e) {
      }

      @Override
      public void mouseEntered(MouseEvent e) {
      }

      @Override
      public void mouseClicked(MouseEvent e) {
        AffichageConfiguration.this.dispose();
      }
    });




    general.add(choixUnite);
    general.add(choixRepresentation);
    general.add(choixDossier);
    general.add(choixDensite);
    general.add(choixTelechargement);

    this.setContentPane(general);
    this.setVisible(true);
  }

  private void initChoixTelechargement() {
    choixTelechargement.setLayout(new GridBagLayout());
    
    groupTelechargement.add(conserver);
    groupTelechargement.add(supprimer);

    GridBagConstraints parametre = new GridBagConstraints();
    parametre.anchor = GridBagConstraints.LINE_START;
    parametre.weightx = 1;
    parametre.weighty = 3;
    
    choixTelechargement.add(new JLabel("Choix sauvegarde : "),parametre);
    parametre.gridy=1;
    choixTelechargement.add(conserver,parametre);
    parametre.gridy=2;
    choixTelechargement.add(supprimer,parametre);
    parametre.insets = new Insets(5, 30, 5, 5);
    parametre.gridy=3;
    parametre.gridx=3;
    choixTelechargement.add(boutonAnnuler, parametre);
    parametre.gridx=4;
    choixTelechargement.add(boutonValider, parametre);

    choixTelechargement.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
  }

  private void initChoixStockage() {
    choixDossier.setLayout(new GridLayout(2, 1));
    JPanel panelChoix = new JPanel();
    panelChoix.setLayout(new BorderLayout());
    panelChoix.add(labelFichier, BorderLayout.CENTER);
    panelChoix.add(boutonFichier, BorderLayout.EAST);
    choixDossier.add(new JLabel("Choisir le dossier de stockage : "));
    choixDossier.add(panelChoix);

    choixDossier.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
  }

  private void initChoixRepresentation() {
    choixRepresentation.setLayout(new GridLayout(4, 1));
    groupRepresentation.add(fleches);
    groupRepresentation.add(couleurs);
    groupRepresentation.add(barbules);

    choixRepresentation.add(new JLabel("Séléctionner la représentation du vent : "));
    choixRepresentation.add(fleches);
    choixRepresentation.add(couleurs);
    choixRepresentation.add(barbules);

    choixRepresentation.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
  }

  private void initChoixUnite() {
    choixUnite.setLayout(new GridLayout(4, 1));

    groupUnite.add(kmh);
    groupUnite.add(noeud);
    groupUnite.add(mph);

    choixUnite.add(new JLabel("Séléctionner l'unité: "));
    choixUnite.add(kmh);
    choixUnite.add(noeud);
    choixUnite.add(mph);

    choixUnite.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

  }
  
  private void initChoixDensite() {
    choixDensite.setLayout(new GridBagLayout());
    choixDensite.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
    GridBagConstraints modification = new GridBagConstraints();
    modification.anchor = GridBagConstraints.LINE_START;
    modification.weightx = 1;
    modification.weighty = 3;
    
    choixDensite.add(new JLabel("Choissisez le nombre de point desiré :"),modification);
    modification.gridx =1;
    choixDensite.add(new JSpinner(new SpinnerNumberModel(10,0,100,1)),modification);
  }

  public static void main(String[] args) {
    AffichageConfiguration aff = new AffichageConfiguration();
  }

}
