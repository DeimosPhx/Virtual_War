package Terrain;

import unite.Char;
import unite.Mine;
import unite.Piegeur;
import unite.Robot;
import unite.Tireur;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import joueur.Joueur;
import joueur.Vue;
/*import javax.swing.*;
import java.awt.*;*/
/*
 * Ebauche de plateau graphique int�gr� au code.
 */
public class Plateau extends JPanel{
	//attributs
	private Parcelle[][] grille;
	private int tailleX,tailleY;
	private static Map<String,ImageIcon> images= new HashMap<>();
	private static final int tailleParcelle=50;
	private Random r = new Random();
	//constructors

	public Plateau(int taillex,int tailley){
		this.grille = new Parcelle[taillex][tailley];
		this.tailleX = taillex;
		this.tailleY = tailley;
		images.put("herbe"		,new ImageIcon("images/Herbe.png"	));
		images.put("1obstacle"	,new ImageIcon("images/Montagne.png"));
		images.put("2obstacle"	,new ImageIcon("images/Foret.png"	));
		images.put("1base"		,new ImageIcon("images/Base1.png"	));
		images.put("2base"		,new ImageIcon("images/Base2.png"	));
		images.put("1char"		,new ImageIcon("images/Char1.png"	));
		images.put("2char"		,new ImageIcon("images/Char2.png"	));
		images.put("1tireur"	,new ImageIcon("images/Tireur1.png"	));
		images.put("2tireur"	,new ImageIcon("images/Tireur2.png"	));
		images.put("1piegeur"	,new ImageIcon("images/piegeur1.png"));
		images.put("2piegeur"	,new ImageIcon("images/piegeur2.png"));
		images.put("1mine"		,new ImageIcon("images/Mine1.png"	));
		images.put("2mine"		,new ImageIcon("images/Mine2.png"	));

		for(int i=0;i<taillex;i++){
			for(int j=0;j<tailley;j++){
				this.grille[i][j] = new Parcelle(new Coordonnees(i,j));
			}
		}
		Frame f = new Frame("Terrain");
		f.setBounds(500,200, tailleParcelle*tailleX+3, tailleParcelle*tailleY+33);
		f.add(this);
		// Fermeture de la fenêtre
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)  {System.exit(0);}
		});
		// Affichage de la fenêtre
		f.setVisible(true);

	}
	public void paint(Graphics g) {
		for (int x=0; x<tailleX; x++){
			for (int y=0; y<tailleY; y++){ 
				if 	   (this.grille[x][y] instanceof Char) {
					if(this.grille[x][y].getEquipe()==1){
						g.drawImage(images.get("1char").getImage(),x*tailleParcelle,y*tailleParcelle,null);
					}
					else{
						g.drawImage(images.get("2char").getImage(),x*tailleParcelle,y*tailleParcelle,null);
					}
				}
				else if(this.grille[x][y] instanceof Obstacle) {
						g.drawImage(images.get("1obstacle").getImage(),x*tailleParcelle,y*tailleParcelle,null);
				}
				else if(this.grille[x][y] instanceof Base) {
					if(this.grille[x][y].getEquipe()==1){
						g.drawImage(images.get("1base").getImage(),x*tailleParcelle,y*tailleParcelle,null);
					}
					else{
						g.drawImage(images.get("2base").getImage(),x*tailleParcelle,y*tailleParcelle,null);
					}
				}
				else if(this.grille[x][y] instanceof Tireur) {
					if(this.grille[x][y].getEquipe()==1){
						g.drawImage(images.get("1tireur").getImage(),x*tailleParcelle,y*tailleParcelle,null);
					}
					else{
						g.drawImage(images.get("2tireur").getImage(),x*tailleParcelle,y*tailleParcelle,null);
					}
				}
				else if(this.grille[x][y] instanceof Mine) {
					if(this.grille[x][y].getEquipe()==1){
						g.drawImage(images.get("1mine").getImage(),x*tailleParcelle,y*tailleParcelle,null);
					}
					else{
						g.drawImage(images.get("2mine").getImage(),x*tailleParcelle,y*tailleParcelle,null);
					}
				}
				else if(this.grille[x][y] instanceof Piegeur) {
					if(this.grille[x][y].getEquipe()==1){
						g.drawImage(images.get("1piegeur").getImage(),x*tailleParcelle,y*tailleParcelle,null);
					}
					else{
						g.drawImage(images.get("2piegeur").getImage(),x*tailleParcelle,y*tailleParcelle,null);
					}
				}
				else {
					g.drawImage(images.get("herbe").getImage(),x*tailleParcelle,y*tailleParcelle,null);
				}
			}
		}
	}

	public Parcelle[][] getGrille(){
		return this.grille;
	}

	public int getX(){
		return tailleX;
	}
	public int getY(){
		return tailleY;
	}
	public Plateau(int taillex,int tailley,char remplir){
		this(taillex,tailley);
		//this.remplir(remplir);
	}

	//methodes
	/*public void remplir(char c){
		for(int x=0;x<this.tailleX;x++){
			for(int y=0;y<this.tailleY;y++){
				this.grille[x][y] = c;
			}
		}
	}*/
	public boolean estVide(Coordonnees cord){
		return grille[cord.getAbscisse()][cord.getOrdonnee()].estVide();
	}
	public void setObstacle(Coordonnees cord){
		grille[cord.getAbscisse()][cord.getOrdonnee()] = new Obstacle(cord);
	}
	public void setBase(Base base){
		grille[base.getCord().getAbscisse()][base.getCord().getOrdonnee()] = base;
	}
	public void setRobot(Robot robot){
		grille[robot.getAbscisse()][robot.getOrdonnee()] = robot;
	}
	public Parcelle getContenu(Coordonnees cord){
		return grille[cord.getAbscisse()][cord.getOrdonnee()];
	}

	public void setMine(Coordonnees cord, Mine mine){
		grille[cord.getAbscisse()][cord.getOrdonnee()] = mine;
	}
	public boolean deplacerTest(Joueur j,Robot rob,Direction direc){
		/*
		 * test de deplacement simple
		 */
		/*
		 * cible: this.grille[cord_unit.cibler(direc.getCoordonnees()).getAbscisse()][cord_unit.cibler(direc.getCoordonnees()).getOrdonnee()]
		 * coordonnees unit�: this.grille[cord_unit.getAbscisse()][cord_unit.getOrdonnee()]
		 * coordonnees de la destination: this.grille[direc.getCoordonnees().getAbscisse()][direc.getCoordonnees().getOrdonnee()]
		 */
		Coordonnees cord_unit = rob.getCord();
		if(cord_unit.cibler(direc.getCoordonnees()).getAbscisse() < 0 || cord_unit.cibler(direc.getCoordonnees()).getOrdonnee() < 0 || cord_unit.cibler(direc.getCoordonnees()).getAbscisse() > this.grille.length || cord_unit.cibler(direc.getCoordonnees()).getOrdonnee() > this.grille[0].length ){
			/*
			 * on ne fait rien car on sort du tableau
			 */
			return false;
		}
		else if(this.grille[cord_unit.cibler(direc.getCoordonnees()).getAbscisse()][cord_unit.cibler(direc.getCoordonnees()).getOrdonnee()] instanceof Obstacle || this.grille[cord_unit.cibler(direc.getCoordonnees()).getAbscisse()][cord_unit.cibler(direc.getCoordonnees()).getOrdonnee()] instanceof Robot){
			/*
			 * on ne fait rien car on rencontre un obstacle ou un robot
			 */
			return false;

		}
		else{
			this.grille[cord_unit.cibler(direc.getCoordonnees()).getAbscisse()][cord_unit.cibler(direc.getCoordonnees()).getOrdonnee()] = rob;
			rob.deployer(new Coordonnees(cord_unit.cibler(direc.getCoordonnees()).getAbscisse(),cord_unit.cibler(direc.getCoordonnees()).getOrdonnee()));
			if(j.getBase().estDans(rob)){
				j.getBase().removeRobot(rob);
			}

			if(this.grille[cord_unit.getAbscisse()][cord_unit.getOrdonnee()] instanceof Base){

			}
			else{

				this.grille[cord_unit.getAbscisse()][cord_unit.getOrdonnee()] = new Parcelle(new Coordonnees(cord_unit.getAbscisse(),cord_unit.getOrdonnee()));
			}
			return true;
		}


	}
	public boolean deplacer(Joueur j,Robot rob,Direction direc){
		Coordonnees cord_unit = rob.getCord();
		if(cord_unit.cibler(direc.getCoordonnees()).getAbscisse() < 0 || cord_unit.cibler(direc.getCoordonnees()).getOrdonnee() < 0 || cord_unit.cibler(direc.getCoordonnees()).getAbscisse() > this.grille.length || cord_unit.cibler(direc.getCoordonnees()).getOrdonnee() > this.grille[0].length){
			/*
			 * destination: en dehors du plateau
			 * effet: cancel deplacement
			 * return: false
			 */
			return false;
		}
		else if(this.grille[cord_unit.cibler(direc.getCoordonnees()).getAbscisse()][cord_unit.cibler(direc.getCoordonnees()).getOrdonnee()] instanceof Base){
			/*
			 * destination: base
			 * effet: ajout de l'unit� dans la liste de la Base
			 * return: true
			 */
			Base b = j.getBase();
			b.addRobot(rob);
			this.grille[cord_unit.getAbscisse()][cord_unit.getOrdonnee()].vider();
			return true;
		}
		else if(this.grille[cord_unit.cibler(direc.getCoordonnees()).getAbscisse()][cord_unit.cibler(direc.getCoordonnees()).getOrdonnee()] instanceof Obstacle || this.grille[cord_unit.cibler(direc.getCoordonnees()).getAbscisse()][cord_unit.cibler(direc.getCoordonnees()).getOrdonnee()] instanceof Robot){
			/*
			 * destination: Obstacle || Robot
			 * effet: cancel deplacement 
			 * return: false
			 */
			return false;
		}
		else if(this.grille[cord_unit.cibler(direc.getCoordonnees()).getAbscisse()][cord_unit.cibler(direc.getCoordonnees()).getOrdonnee()] instanceof Mine){
			/*
			 * destination: Mine
			 * effet: deplacement + explosion
			 * return: true
			 */
			//faire explosion de la mine (CF antoine)
			this.grille[cord_unit.cibler(direc.getCoordonnees()).getAbscisse()][cord_unit.cibler(direc.getCoordonnees()).getOrdonnee()] = this.grille[cord_unit.getAbscisse()][cord_unit.getOrdonnee()];
			this.grille[cord_unit.getAbscisse()][cord_unit.getOrdonnee()].vider();
			return true;
		}
		else{
			/*
			 * destination: Parcelle vide
			 *effet: deplacement
			 *return: true
			 */
			//gestion de l'eventuallit� d'etre dans la base
			if(this.grille[cord_unit.getAbscisse()][cord_unit.getOrdonnee()] instanceof Base){
				Base b = j.getBase();
				b.removeRobot(rob);
				rob.deployer(new Coordonnees(cord_unit.cibler(direc.getCoordonnees()).getAbscisse(),cord_unit.cibler(direc.getCoordonnees()).getOrdonnee()));
			}
			else{
				this.grille[cord_unit.cibler(direc.getCoordonnees()).getAbscisse()][cord_unit.cibler(direc.getCoordonnees()).getOrdonnee()] = this.grille[cord_unit.getAbscisse()][cord_unit.getOrdonnee()];
				this.grille[cord_unit.getAbscisse()][cord_unit.getOrdonnee()].vider();
			}
			return true;
		}
		/*
		 * cible: this.grille[cord_unit.cibler(direc.getCoordonnees()).getAbscisse()][cord_unit.cibler(direc.getCoordonnees()).getOrdonnee()]
		 * coordonnees unit�: this.grille[cord_unit.getAbscisse()][cord_unit.getOrdonnee()]
		 * coordonnees de la destination: this.grille[direc.getCoordonnees().getAbscisse()][direc.getCoordonnees().getOrdonnee()]
		 */

	}
	public boolean estDans(Coordonnees cord){
		return this.tailleX > cord.getAbscisse() && this.tailleY > cord.getOrdonnee()
				&& cord.getAbscisse() >=0 && cord.getOrdonnee() >=0;
	}
	//ebauche graphique
	/*	public void afficher(String title){
		if(this.graphique){
			this.setTitle(title);
			this.setSize(800,600);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//this.setSize(this.tailleX*10,this.tailleY*10);
			this.setLayout(new GridLayout(this.tailleX,this.tailleY));
			String c = new String("");
			for(int x=0;x<this.tailleX;x++){
				for(int y=0;y<this.tailleY;y++){
					/*c = "";
					c += this.grille[x][y];*/
	/*					//set graphical grid
				}
			}
			this.setVisible(true);
		}
		else{
			JOptionPane.showMessageDialog(null, "ERROR INITIALISATION NOT GRAPHICAL !");
		}
	}*/
	public void placer(int x,int y,char c){
		//this.grille[x][y] = c;
	}
	public String toString(){
		String out = new String("");
		for(int x=0;x<this.tailleX;x++){
			for(int y=0;y<this.tailleY;y++){
				out += "|" + "_" + this.grille[x][y].toString() + "_" + "|";
			}
			out += "\n";
		}
		return out;
	}

}
