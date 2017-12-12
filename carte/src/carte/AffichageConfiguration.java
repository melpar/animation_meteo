package carte;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class AffichageConfiguration extends JFrame {
	JPanel general = new JPanel();
	JPanel choixUnite = new JPanel();
	JPanel choixRepresentation = new JPanel();
	JPanel choixDossier = new JPanel();
	JPanel choixDensite = new JPanel();
	JPanel choixTelechargement = new JPanel();

	JTextField valeurseuil = new JTextField();

	// crÃ©ation des boutons
	JRadioButton kmh = new JRadioButton("km/h");
	JRadioButton noeud = new JRadioButton("noeud");
	JRadioButton mph = new JRadioButton("mph");
	JRadioButton fleches = new JRadioButton("km/h");
	JRadioButton couleurs = new JRadioButton("noeud");
	JRadioButton barbules = new JRadioButton("mph");
	JRadioButton supprimer = new JRadioButton("Supprimer les fichiers précédents");
	JRadioButton conserver = new JRadioButton("Conserver les fichiers précédents");
	// crÃ©ation d'un groupe de bouton pour gerer liÃ©es les boutons radio
	ButtonGroup groupUnite = new ButtonGroup();
	ButtonGroup groupRepresentation = new ButtonGroup();
	ButtonGroup groupTelechargement = new ButtonGroup();

	JLabel labelFichier = new JLabel("h:/");
	JButton boutonFichier = new JButton("Choisir");
	JButton boutonValider = new JButton("Valider");
	JButton boutonAnnuler = new JButton("Annuler");

	public AffichageConfiguration() {

		// definissions du titre
		this.setTitle("Modification");

		// dÃ©finion de la taille
		this.setSize(600, 400);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		general.setLayout(new GridLayout(6, 1));
		initChoixUnite();

		initChoixRepresentation();

		initChoixStockage();

		initChoixTelechargement();
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
		JPanel panelValide = new JPanel();
		panelValide.setLayout(new BorderLayout());
		panelValide.add(boutonAnnuler, BorderLayout.WEST);
		panelValide.add(boutonValider, BorderLayout.EAST);
		general.add(choixUnite);
		general.add(choixRepresentation);
		general.add(choixDossier);
		general.add(choixDensite);
		general.add(choixTelechargement);
		general.add(panelValide);
		this.setContentPane(general);
		this.setVisible(true);
	}

	private void initChoixTelechargement() {
		choixTelechargement.setLayout(new GridLayout(4, 1));
		groupTelechargement.add(conserver);
		groupTelechargement.add(supprimer);

		choixTelechargement.add(new JLabel("Séléctionner la représentation du vent : "));
		choixTelechargement.add(conserver);
		choixTelechargement.add(supprimer);

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
		groupRepresentation.add(kmh);
		groupRepresentation.add(noeud);
		groupRepresentation.add(mph);

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

		choixUnite.add(new JLabel("Sélectionner l'unité: "));
		choixUnite.add(kmh);
		choixUnite.add(noeud);
		choixUnite.add(mph);

		choixUnite.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

	}

	public static void main(String[] args) {
		AffichageConfiguration aff = new AffichageConfiguration();
	}

}
