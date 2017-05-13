package joueur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;

import Terrain.Base;
import Terrain.Coordonnees;
import Terrain.Obstacle;
import Terrain.Parcelle;
import Terrain.Plateau;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import unite.Char;
import unite.Mine;
import unite.Piegeur;
import unite.Robot;
import unite.Tireur;

public class Jeu extends Application{
	int tauxObstacle, tailleX = 10, tailleY=10;
	HashMap<Integer,Robot> compoJ1 = new HashMap<Integer,Robot>();
	HashMap<Integer,Robot> compoJ2 = new HashMap<Integer,Robot>();
	boolean joueur1EstHumain, joueur2EstHumain;
	Base B1,B2;
	Vue vueJ1, vueJ2;
	Joueur joueur1,joueur2;
	Plateau plateau;
	private static HashMap<String,Image> images= new HashMap<String,Image>();
	private static final int tailleParcelle=50;
	boolean tourJ1;
	
	private static void initialisation(){
		images.put("herbe"		,new Image("Herbe.png"	));
		images.put("1obstacle"	,new Image("Montagne.png"));
		images.put("2obstacle"	,new Image("Foret.png"	));
		images.put("1base"		,new Image("Base1.png"	));
		images.put("2base"		,new Image("Base2.png"	));
		images.put("1char"		,new Image("Char1.png"	));
		images.put("2char"		,new Image("Char2.png"	));
		images.put("1tireur"	,new Image("Tireur1.png"));
		images.put("2tireur"	,new Image("Tireur2.png"));
		images.put("1piegeur"	,new Image("piegeur1.png"));
		images.put("2piegeur"	,new Image("piegeur2.png"));
		images.put("1mine"		,new Image("Mine1.png"	));
		images.put("2mine"		,new Image("Mine2.png"	));

	}
	
	public void setReglage(int tauxObstacle,boolean joueur1EstHumain,boolean joueur2EstHumain,int nbCharJ1,int nbCharJ2,int nbTireurJ1,int nbTireurJ2,int nbPiegeurJ1,int nbPiegeurJ2){
	this.tauxObstacle = tauxObstacle;
	setCompo( nbCharJ1, nbCharJ2, nbTireurJ1, nbTireurJ2, nbPiegeurJ1, nbPiegeurJ2, plateau);
	this.joueur1EstHumain = joueur1EstHumain;
	this.joueur2EstHumain = joueur2EstHumain;
	Base B1 = new Base(new Coordonnees(0,0),1);
	Base B2 = new Base(new Coordonnees(tailleX-1,tailleY-1),2);
	plateau = new Plateau(tailleX, tailleY);
	plateau.setBase(B1);
	plateau.setBase(B2);
	vueJ1 = new Vue(1,plateau);
	vueJ2 = new Vue(2,plateau);
	
	//A CHANGER SI IA
	joueur1 = new Joueur(1);
	joueur2 = new Joueur(2);
	
	setCompo(nbCharJ1, nbCharJ2, nbTireurJ1, nbTireurJ2, nbPiegeurJ1, nbPiegeurJ2, plateau);
	joueur1.setList(compoJ1);
	joueur2.setList(compoJ2);
	setObstacle(plateau, joueur1, joueur2, tauxObstacle);
	initialisation();
	}

	public void setCompo(int nbCharJ1,int nbCharJ2,int nbTireurJ1,int nbTireurJ2,int nbPiegeurJ1,int nbPiegeurJ2, Plateau plateau){
		int cpt = 0;
		for(int i = 0; i<nbCharJ1; i++){
			cpt++;
			compoJ1.put(cpt,new Char(1,new Coordonnees(0, 0), plateau));
		}
		for(int i = 0; i<nbCharJ2; i++){
			cpt++;
			compoJ2.put(cpt,new Char(2,new Coordonnees(tailleX-1, tailleY-1), plateau));
		}
		for(int i = 0; i<nbTireurJ1; i++){
			cpt++;
			compoJ1.put(cpt,new Tireur(1,new Coordonnees(0, 0), plateau));
		}
		for(int i = 0; i<nbTireurJ2; i++){
			cpt++;
			compoJ2.put(cpt,new Char(2,new Coordonnees(tailleX-1, tailleY-1), plateau));
		}
		for(int i = 0; i<nbPiegeurJ1; i++){
			cpt++;
			compoJ1.put(cpt,new Char(1,new Coordonnees(0, 0), plateau));
		}
		for(int i = 0; i<nbPiegeurJ1; i++){
			cpt++;
			compoJ1.put(cpt,new Char(2,new Coordonnees(tailleX-1, tailleY-1), plateau));
		}

	}

	public static void setObstacle(Plateau ter,Joueur j1,Joueur j2,int tauxObstacle){
		/*
		 * on genere les obstacles selon le taux
		 * en s'assurant qu'il existe un chemin d'une base a l'autre
		 */
		Parcelle[][] plat = ter.getGrille();
		int tx= tauxObstacle;
        //plateau de 100case (10*10)
        //EZ taux
        Random rnd = new Random();
        boolean isOut = false;
        for(int i=0;i<tx;i++){
        	do{
        		isOut = false;
        		int x = rnd.nextInt(9);
            	int y = rnd.nextInt(9);
	        	/*if((x!=j1.getBase().getCord().getAbscisse() && y!=j1.getBase().getCord().getOrdonnee()) 
	        			&& (x!=j2.getBase().getCord().getAbscisse() && y!=j2.getBase().getCord().getOrdonnee())
	        			&& (x!=j1.getBase().getCord().getAbscisse()+1 && y!=j1.getBase().getCord().getOrdonnee()+1)
	        			&& (x!=j2.getBase().getCord().getAbscisse()+1 && y!=j2.getBase().getCord().getOrdonnee()+1))*/
            	if(plat[x][y].autoriserPlacementObstacle(ter,new Coordonnees(x,y))){
	        		plat[x][y] = new Obstacle(new Coordonnees(x,y));
	        		plat[x][y].setPasVide();
	        		isOut = true;
	        	}
        	     }while(!isOut);
		}
	}

	public void draw(GraphicsContext gc){
		for (int x=0; x<tailleX; x++){
			for (int y=0; y<tailleY; y++){
				if 	  (Plateau.grille[y][x] instanceof Char) {
					if(Plateau.grille[y][x].getEquipe()==1){
						gc.drawImage(images.get("1char"),x*tailleParcelle,y*tailleParcelle+80);
					}
					else{
						gc.drawImage(images.get("2char"),x*tailleParcelle,y*tailleParcelle+80);
					}
				}
				else if(Plateau.grille[y][x] instanceof Obstacle) {
					gc.drawImage(images.get("1obstacle"),x*tailleParcelle,y*tailleParcelle+80);
				}
				else if(Plateau.grille[y][x] instanceof Base) {
					if(Plateau.grille[y][x].getEquipe()==1){
						gc.drawImage(images.get("1base"),x*tailleParcelle,y*tailleParcelle+80);
					}
					else{
						gc.drawImage(images.get("2base"),x*tailleParcelle,y*tailleParcelle+80);
					}
				}
				else if(Plateau.grille[y][x] instanceof Tireur) {
					if(Plateau.grille[y][x].getEquipe()==1){
						gc.drawImage(images.get("1tireur"),x*tailleParcelle,y*tailleParcelle+80);
					}
					else{
						gc.drawImage(images.get("2tireur"),x*tailleParcelle,y*tailleParcelle+80);
					}
				}
				else if(Plateau.grille[y][x] instanceof Mine) {
					if(Plateau.grille[y][x].getEquipe()==1 && tourJ1){
						gc.drawImage(images.get("1mine"),x*tailleParcelle,y*tailleParcelle+80);
					}
					else if(Plateau.grille[y][x].getEquipe()==2 && !tourJ1){
						gc.drawImage(images.get("2mine"),x*tailleParcelle,y*tailleParcelle+80);
					}
					else{
						gc.drawImage(images.get("herbe"),x*tailleParcelle,y*tailleParcelle+80);
					}
				}
				else if(Plateau.grille[y][x] instanceof Piegeur) {
					if(Plateau.grille[y][x].getEquipe()==1){
						gc.drawImage(images.get("1piegeur"),x*tailleParcelle,y*tailleParcelle+80);
					}
					else{
						gc.drawImage(images.get("2piegeur"),x*tailleParcelle,y*tailleParcelle+80);
					}
				}
				else {
					gc.drawImage(images.get("herbe"),x*tailleParcelle,y*tailleParcelle+80);
				}
			}
		}

	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.close();
		Canvas canvas = new Canvas(tailleX*tailleParcelle,tailleY*tailleParcelle+160);
		VBox root = new VBox();
		GraphicsContext gcJeu = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		Scene sceneJeu = new Scene(root);
		stage.setScene(sceneJeu);
		stage.show();
		draw(gcJeu);
	}

}
