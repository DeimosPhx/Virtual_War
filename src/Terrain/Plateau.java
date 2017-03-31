package Terrain;

import unite.Mine;
import unite.Robot;
import unite.Tireur;
import joueur.Vue;
/*import javax.swing.*;
import java.awt.*;*/
/*
 * Ebauche de plateau graphique intï¿½grï¿½ au code.
 */
public class Plateau{
	//attributs
	private Parcelle[][] grille;
	private int tailleX,tailleY;
	private boolean graphique;
	//constructors
	public Plateau(int taillex,int tailley){
		this.grille = new Parcelle[taillex][tailley];
		this.tailleX = taillex;
		this.tailleY = tailley;
		this.graphique = false;
		for(int i=0;i<taillex;i++){
			for(int j=0;j<tailley;j++){
				this.grille[i][j] = new Parcelle(new Coordonnees(i,j));
			}
		}
	}
	
	public Plateau(int taillex,int tailley,boolean graphique){
		this(taillex,tailley);
		this.graphique = true;
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
	public boolean deplacer(Robot rob,Direction direc){
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
			 * effet: ajout de l'unité dans la liste de la Base
			 * return: true
			 */
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
			this.grille[cord_unit.cibler(direc.getCoordonnees()).getAbscisse()][cord_unit.cibler(direc.getCoordonnees()).getOrdonnee()] = this.grille[cord_unit.getAbscisse()][cord_unit.getOrdonnee()];
			this.grille[cord_unit.getAbscisse()][cord_unit.getOrdonnee()].vider();
			return true;
		}
		/*
		 * cible: this.grille[cord_unit.cibler(direc.getCoordonnees()).getAbscisse()][cord_unit.cibler(direc.getCoordonnees()).getOrdonnee()]
		 * coordonnees unité: this.grille[cord_unit.getAbscisse()][cord_unit.getOrdonnee()]
		 * coordonnees de la destination: this.grille[direc.getCoordonnees().getAbscisse()][direc.getCoordonnees().getOrdonnee()]
		 */
		
	}
	public boolean estDans(Coordonnees cord){
		return this.tailleX < cord.getAbscisse() && this.tailleY < cord.getOrdonnee();
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
					out += "|" + "_" + this.grille[x][y] + "_" + "|";
				}
				out += "\n";
			}
			return out;
	}
		
}
