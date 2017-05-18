package joueur;

import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;

import com.sun.prism.paint.Color;

import Terrain.Base;
import Terrain.Coordonnees;
import Terrain.Direction;
import Terrain.Obstacle;
import Terrain.Parcelle;
import Terrain.Plateau;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import unite.Char;
import unite.Mine;
import unite.Piegeur;
import unite.Robot;
import unite.Tireur;

public class Jeu extends Application{
	private int tauxObstacle, tailleX = 10, tailleY=10;

	private HashMap<Integer,Robot> compoJ1 = new HashMap<Integer,Robot>();
	private HashMap<Integer,Robot> compoJ2 = new HashMap<Integer,Robot>();
	boolean joueur1EstHumain, joueur2EstHumain;
	private Base B1,B2;
	private Vue vueJ1, vueJ2;
	private Joueur joueur1,joueur2;
	private Plateau plateau;
	private static HashMap<String,Image> images= new HashMap<String,Image>();
	private static final int tailleParcelle=50;
	private boolean tourJ1;
	private Robot selectedRobot = null;

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
		images.put("1charBase"	,new Image("charDansBase.png"));
		images.put("1tireurBase",new Image("infanterieDansbase.png"));
		images.put("1piegeurBase",new Image("piegeurDansBase.png"));
		images.put("2charBase"	,new Image("charDansBase2.png"));
		images.put("2tireurBase",new Image("infanterieDansBaseJ2.png"));
		images.put("2piegeurBase",new Image("piegeurDansBaseJ2.png"));
		images.put("1herbe",	new Image("HerbeJ1.png"));
		images.put("2herbe",	new Image("HerbeJ2.png"));

	}

	private void setUniteDansbase(){
		ArrayList<Robot> lstJ1 = new ArrayList<Robot>();
		for(int i=0;i<compoJ1.size();i++){
			lstJ1.add(compoJ1.get(new Integer(i)));
		}
		ArrayList<Robot> lstJ2 = new ArrayList<Robot>();
		for(int i=0;i<compoJ2.size();i++){
			lstJ2.add(compoJ2.get(new Integer(i)));
		}
		B1.setList(lstJ1);
		B2.setList(lstJ2);
	}

	public void setReglage(int tauxObstacle,boolean joueur1EstHumain,boolean joueur2EstHumain,int nbCharJ1,int nbCharJ2,int nbTireurJ1,int nbTireurJ2,int nbPiegeurJ1,int nbPiegeurJ2){
		this.tauxObstacle = tauxObstacle;
		setCompo( nbCharJ1, nbCharJ2, nbTireurJ1, nbTireurJ2, nbPiegeurJ1, nbPiegeurJ2, plateau);
		this.joueur1EstHumain = joueur1EstHumain;
		this.joueur2EstHumain = joueur2EstHumain;
		this.B1 = new Base(new Coordonnees(0,0),1);
		this.B2 = new Base(new Coordonnees(tailleX-1,tailleY-1),2);
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
		setUniteDansbase();

		initialisation();
	}

	public void setCompo(int nbCharJ1,int nbCharJ2,int nbTireurJ1,int nbTireurJ2,int nbPiegeurJ1,int nbPiegeurJ2, Plateau plateau){
		int cptJ1 = 0;
		int cptJ2 = 0;

		for(int i = 0; i<nbCharJ1; i++){
			compoJ1.put(cptJ1,new Char(1,new Coordonnees(0, 0), plateau));
			cptJ1++;
		}
		for(int i = 0; i<nbCharJ2; i++){
			compoJ2.put(cptJ2,new Char(2,new Coordonnees(tailleX-1, tailleY-1), plateau));
			cptJ2++;
		}
		for(int i = 0; i<nbTireurJ1; i++){
			compoJ1.put(cptJ1,new Tireur(1,new Coordonnees(0, 0), plateau));
			cptJ1++;
		}
		for(int i = 0; i<nbTireurJ2; i++){
			compoJ2.put(cptJ2,new Tireur(2,new Coordonnees(tailleX-1, tailleY-1), plateau));
			cptJ2++;
		}
		for(int i = 0; i<nbPiegeurJ1; i++){
			compoJ1.put(cptJ1,new Piegeur(1,new Coordonnees(0, 0), plateau));
			cptJ1++;
		}
		for(int i = 0; i<nbPiegeurJ2; i++){
			compoJ2.put(cptJ2,new Piegeur(2,new Coordonnees(tailleX-1, tailleY-1), plateau));
			cptJ2++;
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
		gc.clearRect(0, 0,tailleX*tailleParcelle,tailleY*tailleParcelle+160);
		//AFFICHER LES UNITES DANS LA BASE :
		for(int i=0;i<B1.getTailleListe();i++){
			if		(B1.getRobot(i) instanceof Char){
				gc.drawImage(images.get("1charBase"), i*50, 0);
			}else if(B1.getRobot(i) instanceof Tireur){
				gc.drawImage(images.get("1tireurBase"), i*50, 0);
			}else if(B1.getRobot(i) instanceof Piegeur){
				gc.drawImage(images.get("1piegeurBase"), i*50, 0);
			}
		}
		for(int i=0;i<B2.getTailleListe();i++){
			if		(B2.getRobot(i) instanceof Char){
				gc.drawImage(images.get("2charBase"), 450-i*50, 90+tailleY*50);
			}else if(B2.getRobot(i) instanceof Tireur){
				gc.drawImage(images.get("2tireurBase"), 450-i*50, 90+tailleY*50);
			}else if(B2.getRobot(i) instanceof Piegeur){
				gc.drawImage(images.get("2piegeurBase"), 450-i*50, 90+tailleY*50);
			}
		}

		//AFFICHER LE PLATEAUDE JEU
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
				else {gc.drawImage(images.get("herbe"),x*tailleParcelle,y*tailleParcelle+80);}}}

		//ON VAS AFFICHER LE ROBOT ACTUELLEMENT SELECTIONNER :
		if(selectedRobot != null){
			if(selectedRobot.getEquipe()==1){}
			else{}
			gc.strokeRect(selectedRobot.getAbscisse()*tailleParcelle, selectedRobot.getOrdonnee()*tailleParcelle+80, tailleParcelle, tailleParcelle);
		}
		//Affichons l'interface dynamique :
		for (int x=0; x<tailleX; x++){
			for (int y=0; y<tailleY; y++){
				if(selectedRobot!=null){
					for(Direction d : Direction.values()){
						if(x==1&y==0){System.out.println(x +"/"+ selectedRobot.getAbscisse()+d.getCoordonnees().getOrdonnee()+" "+y+"/"+selectedRobot.getAbscisse()+d.getCoordonnees().getAbscisse());}
						if(plateau.peuxDeplacer(joueur1, selectedRobot, d) 
								&& x==selectedRobot.getAbscisse()+d.getCoordonnees().getOrdonnee()
								&& y==selectedRobot.getOrdonnee()+d.getCoordonnees().getAbscisse()){
							if(selectedRobot.getEquipe()==1){gc.drawImage(images.get("1herbe"),x*tailleParcelle,y*tailleParcelle+80);}
							else{gc.drawImage(images.get("2herbe"),x*tailleParcelle,y*tailleParcelle+80);}
						}
					}
				}
			}
		}
	}


	private ArrayList<Hitboxe> getHitboxes(HashMap<Integer,Robot> unites){
		ArrayList<Hitboxe> hitboxes = new ArrayList<Hitboxe>();
		if(unites.get(0).getEquipe()==1){
			for(int i =0; i<B1.getTailleListe();i++){
				hitboxes.add(new Hitboxe(new Rectangle(i*50,0,tailleParcelle,tailleParcelle),B1.getRobot(i)));
			}
			for(int i =0; i<unites.size();i++){

				if(!(unites.get(i).getCord().equals(new Coordonnees(0, 0)))){
					hitboxes.add(new Hitboxe(new Rectangle(unites.get(i).getAbscisse()*tailleParcelle ,
							unites.get(i).getOrdonnee()*tailleParcelle+80,
							tailleParcelle,tailleParcelle),unites.get(i)));
				}
			}
		}
		else{
			for(int i =0; i<B2.getTailleListe();i++){
				hitboxes.add(new Hitboxe(new Rectangle(450-i*50, 90+tailleY*50,tailleParcelle,tailleParcelle),B2.getRobot(i)));
			}
			for(int i =0; i<unites.size();i++){

				if(!(unites.get(i).getCord().equals(new Coordonnees(9, 9)))){
					hitboxes.add(new Hitboxe(new Rectangle(unites.get(i).getAbscisse()*tailleParcelle ,
							unites.get(i).getOrdonnee()*tailleParcelle+80,
							tailleParcelle,tailleParcelle),unites.get(i)));
				}
			}

		}
		return hitboxes;

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
		canvas.setOnMouseClicked(e -> {
			boolean hasFindSomething = false;
			for(Hitboxe h : getHitboxes(joueur1.getListeRobot())){
				if(h.getHitboxe().contains(new Point2D(e.getSceneX(),e.getSceneY()))){
					selectedRobot=h.getRobot();
					hasFindSomething = true;
				}
			}
			for(Hitboxe h : getHitboxes(joueur2.getListeRobot())){
				if(h.getHitboxe().contains(new Point2D(e.getSceneX(),e.getSceneY()))){
					selectedRobot=h.getRobot();
					hasFindSomething = true;
				}
			}
			if(hasFindSomething){
				System.out.println(selectedRobot);
			}else{selectedRobot = null;}




			draw(gcJeu);
		});


	}

}
