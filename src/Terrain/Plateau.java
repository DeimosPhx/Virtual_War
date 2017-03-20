package Terrain;
/*import javax.swing.*;
import java.awt.*;*/
/*
 * Ebauche de plateau graphique int�gr� au code.
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
	}
	public Plateau(int taillex,int tailley,boolean graphique){
		this(taillex,tailley);
		this.graphique = true;
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
	public void deplacer(String direction,int x,int y){
		/*char tmp = ' ';
		switch(direction){
		case "bas":
			tmp = this.grille[x][y];
			this.grille[x][y] = this.grille[x+1][y];
			this.grille[x+1][y] = tmp;
			break;
		case "haut":
			tmp = this.grille[x][y];
			this.grille[x][y] = this.grille[x-1][y];
			this.grille[x-1][y] = tmp;
			
			break;
		case "droite":
			tmp = this.grille[x][y];
			this.grille[x][y] = this.grille[x][y+1];
			this.grille[x][y+1] = tmp;
			
			break;
		case "gauche":
			tmp = this.grille[x][y];
			this.grille[x][y] = this.grille[x][y-1];
			this.grille[x][y-1] = tmp;
			
			break;
		default:
			
		}*/
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
