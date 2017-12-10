package carte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import org.geotools.map.MapContext;
import org.geotools.swing.JMapFrame;

public class SetFrame {

	JMapFrame frame;
	MapContext map;
	//Dessiner dessiner;
	
	
	/**
	 * initialiser les boutton
	 * @param frame
	 * @param map
	 */
	SetFrame(JMapFrame frame,MapContext map){
		this.frame=frame;
		this.map=map;
		bouttonAdd();
		bouttonDelete();
		//dessiner = new Dessiner(map);
		
	}
	
	
	/**
	 * ajouter un boutton add en listener
	 */
	void bouttonAdd(){
		JButton ajouterCalque = new JButton("ajouter");
		ajouterCalque.addActionListener(new ActionAdd());
		frame.getToolBar().add(ajouterCalque);
	}
	
	/**
	 * definir l'action du boutton add
	 * @author thomas
	 *
	 */
	class ActionAdd implements ActionListener{
		int nbTest;
		ActionAdd(){
			
			nbTest=0;
			System.out.println("init "+nbTest);
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			nbTest+=10;
			System.out.println("dessin "+nbTest);
			Dessiner dessiner1 = new Dessiner(map,nbTest);
		}
	}
	/**
	 * ajouter un boutton delete en listener
	 */
	void bouttonDelete(){
		JButton supprimerCalque = new JButton("supprimer");
		supprimerCalque.addActionListener(new ActionDelete());
		frame.getToolBar().add(supprimerCalque);
	}
	
	/**
	 * definir l'action du boutton delete
	 * @author thomas
	 *
	 */
	class ActionDelete implements ActionListener{
		ActionDelete(){
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			System.out.println("remove ");
			if(map.getLayerCount()>1)map.removeLayer(1);
		}
	}
	
	/**
	 * boutton non defini
	 */
	void boutton3(){
		JButton button3 = new JButton("Button 3");
		frame.getToolBar().add(button3);
	}


	

	
}
