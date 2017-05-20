package joueur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


import terrain.Base;
import terrain.Coordonnees;
import terrain.Direction;
import terrain.Obstacle;
import terrain.Parcelle;
import terrain.Plateau;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import unite.*;

public class Jeu extends Application{

	private int tauxObstacle, tailleX = 10, tailleY=10;
	private ArrayList<Robot> compojoueur1 = new ArrayList<Robot>();
	private ArrayList<Robot> compoJ2 = new ArrayList<Robot>();
	boolean joueur1EstHumain, joueur2EstHumain;
	private Base B1,B2;
	private Vue vuejoueur1, vueJ2;
	private Joueur joueur1,joueur2;
	private Plateau plateau;
	private static HashMap<String,Image> images= new HashMap<String,Image>();
	private static final int tailleParcelle=50;
	private boolean tourjoueur1 = true;
	private Robot selectedRobot = null;
	private boolean modeMine = false;
	private boolean j1gagne = false;
	private boolean j2gagne = false;
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
		images.put("combat", 	new Image("combat.png"));

	}

	private void setUniteDansbase(){
		ArrayList<Robot> lstjoueur1 = new ArrayList<Robot>();
		for(int i=0;i<compojoueur1.size();i++){
			lstjoueur1.add(compojoueur1.get(new Integer(i)));
		}
		ArrayList<Robot> lstJ2 = new ArrayList<Robot>();
		for(int i=0;i<compoJ2.size();i++){
			lstJ2.add(compoJ2.get(new Integer(i)));
		}
		B1.setList(lstjoueur1);
		B2.setList(lstJ2);
	}

	public void setReglage(int tauxObstacle,boolean joueur1EstHumain,boolean joueur2EstHumain,int nbCharjoueur1,int nbCharJ2,int nbTireurjoueur1,int nbTireurJ2,int nbPiegeurjoueur1,int nbPiegeurJ2){
		this.tauxObstacle = tauxObstacle;
		this.joueur1EstHumain = joueur1EstHumain;
		this.joueur2EstHumain = joueur2EstHumain;
		this.B1 = new Base(new Coordonnees(0,0),1);
		this.B2 = new Base(new Coordonnees(tailleX-1,tailleY-1),2);
		plateau = new Plateau(tailleX, tailleY);
		plateau.setBase(B1);
		plateau.setBase(B2);
		vuejoueur1 = new Vue(1,plateau);
		vueJ2 = new Vue(2,plateau);

		//A CHANGER SI IA
		joueur1 = new Joueur(1,B1,vuejoueur1);
		joueur2 = new Joueur(2,B2,vueJ2);

		setCompo(nbCharjoueur1, nbCharJ2, nbTireurjoueur1, nbTireurJ2, nbPiegeurjoueur1, nbPiegeurJ2, plateau);
		joueur1.setList(compojoueur1);
		joueur2.setList(compoJ2);
		setObstacle(plateau, joueur1, joueur2, tauxObstacle);
		setUniteDansbase();

		initialisation();
	}

	public void setCompo(int nbCharjoueur1,int nbCharJ2,int nbTireurjoueur1,int nbTireurJ2,int nbPiegeurjoueur1,int nbPiegeurJ2, Plateau plateau){
		for(int i = 0; i<nbCharjoueur1; i++){
			compojoueur1.add(new Char(1,new Coordonnees(0, 0), plateau));
		}
		for(int i = 0; i<nbCharJ2; i++){
			compoJ2.add(new Char(2,new Coordonnees(tailleX-1, tailleY-1), plateau));
		}
		for(int i = 0; i<nbTireurjoueur1; i++){
			compojoueur1.add(new Tireur(1,new Coordonnees(0, 0), plateau));
		}
		for(int i = 0; i<nbTireurJ2; i++){
			compoJ2.add(new Tireur(2,new Coordonnees(tailleX-1, tailleY-1), plateau));
		}
		for(int i = 0; i<nbPiegeurjoueur1; i++){
			compojoueur1.add(new Piegeur(1,new Coordonnees(0, 0), plateau));
		}
		for(int i = 0; i<nbPiegeurJ2; i++){
			compoJ2.add(new Piegeur(2,new Coordonnees(tailleX-1, tailleY-1), plateau));
		}

	}

	public static void setObstacle(Plateau ter,Joueur joueur1,Joueur j2,int tauxObstacle){
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
				/*if((x!=joueur1.getBase().getCord().getAbscisse() && y!=joueur1.getBase().getCord().getOrdonnee()) 
	        			&& (x!=j2.getBase().getCord().getAbscisse() && y!=j2.getBase().getCord().getOrdonnee())
	        			&& (x!=joueur1.getBase().getCord().getAbscisse()+1 && y!=joueur1.getBase().getCord().getOrdonnee()+1)
	        			&& (x!=j2.getBase().getCord().getAbscisse()+1 && y!=j2.getBase().getCord().getOrdonnee()+1))*/
				if(plat[x][y].autoriserPlacementObstacle(ter,new Coordonnees(x,y))){
					plat[x][y] = new Obstacle(new Coordonnees(x,y));
					plat[x][y].setPasVide();
					isOut = true;
				}
			}while(!isOut);
		}
	}
	public void afficherBarDeVie(GraphicsContext gc,Robot r){
		double pourcentageDeVie = (r.getEnergie()/60)*100;
		gc.setFill(Color.rgb((int)((100-pourcentageDeVie)*2.55), 0, (int)(pourcentageDeVie*2.55)));
		gc.fillRect(r.getAbscisse()*tailleParcelle+5, r.getOrdonnee()*tailleParcelle+120, pourcentageDeVie*40, 4);
		//gc.drawImage(images.get("1char"),x*tailleParcelle,y*tailleParcelle+80);
	}
	public void draw(GraphicsContext gc){
		double pourcentageDeVie;
		Robot r= null;
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
			r = B1.getRobot(i);
			pourcentageDeVie = ((double)r.getEnergie()/60)*100;
			gc.setFill(Color.rgb((int)((100-pourcentageDeVie)*2.55), (int)(pourcentageDeVie*2.55),0));
			gc.fillRect(i*50, 40, pourcentageDeVie*0.4, 6);
		}
		for(int i=0;i<B2.getTailleListe();i++){
			if		(B2.getRobot(i) instanceof Char){
				gc.drawImage(images.get("2charBase"), 450-i*50, 90+tailleY*50);
			}else if(B2.getRobot(i) instanceof Tireur){
				gc.drawImage(images.get("2tireurBase"), 450-i*50, 90+tailleY*50);
			}else if(B2.getRobot(i) instanceof Piegeur){
				gc.drawImage(images.get("2piegeurBase"), 450-i*50, 90+tailleY*50);
			}
			r = B2.getRobot(i);
			pourcentageDeVie = ((double)r.getEnergie()/60)*100;
			gc.setFill(Color.rgb((int)((100-pourcentageDeVie)*2.55), (int)(pourcentageDeVie*2.55),0));
			gc.fillRect(450-i*50, 40+90+tailleY*50, pourcentageDeVie*0.4, 6);
		}

		//AFFICHER LE PLATEAUDE JEU
		for (int x=0; x<tailleX; x++){
			for (int y=0; y<tailleY; y++){
				if 	  (Plateau.grille[x][y] instanceof Char) {
					if(Plateau.grille[x][y].getEquipe()==1){
						gc.drawImage(images.get("1char"),x*tailleParcelle,y*tailleParcelle+80);
					}
					else{
						gc.drawImage(images.get("2char"),x*tailleParcelle,y*tailleParcelle+80);
					}
					r = (Robot)Plateau.grille[x][y];
					pourcentageDeVie = ((double)r.getEnergie()/60)*100;
					gc.setFill(Color.rgb((int)((100-pourcentageDeVie)*2.55), (int)(pourcentageDeVie*2.55),0));
					gc.fillRect(r.getAbscisse()*tailleParcelle+5, r.getOrdonnee()*tailleParcelle+120, pourcentageDeVie*0.4, 6);
				}
				else if(Plateau.grille[x][y] instanceof Obstacle) {
					gc.drawImage(images.get("1obstacle"),x*tailleParcelle,y*tailleParcelle+80);
				}
				else if(Plateau.grille[x][y] instanceof Base) {
					if(Plateau.grille[x][y].getEquipe()==1){
						gc.drawImage(images.get("1base"),x*tailleParcelle,y*tailleParcelle+80);
					}
					else{
						gc.drawImage(images.get("2base"),x*tailleParcelle,y*tailleParcelle+80);
					}
				}
				else if(Plateau.grille[x][y] instanceof Tireur) {
					if(Plateau.grille[x][y].getEquipe()==1){
						gc.drawImage(images.get("1tireur"),x*tailleParcelle,y*tailleParcelle+80);
					}
					else{
						gc.drawImage(images.get("2tireur"),x*tailleParcelle,y*tailleParcelle+80);
					}
					r = (Robot)Plateau.grille[x][y];
					pourcentageDeVie = ((double)r.getEnergie()/60)*100;
					gc.setFill(Color.rgb((int)((100-pourcentageDeVie)*2.55), (int)(pourcentageDeVie*2.55),0));
					gc.fillRect(r.getAbscisse()*tailleParcelle+5, r.getOrdonnee()*tailleParcelle+120,pourcentageDeVie*0.4, 6);
				}
				else if(Plateau.grille[x][y] instanceof Mine) {
					if(Plateau.grille[x][y].getEquipe()==1 && tourjoueur1){
						gc.drawImage(images.get("1mine"),x*tailleParcelle,y*tailleParcelle+80);
					}
					else if(Plateau.grille[x][y].getEquipe()==2 && !tourjoueur1){
						gc.drawImage(images.get("2mine"),x*tailleParcelle,y*tailleParcelle+80);
					}
					else{
						gc.drawImage(images.get("herbe"),x*tailleParcelle,y*tailleParcelle+80);
					}
				}
				else if(Plateau.grille[x][y] instanceof Piegeur) {
					if(Plateau.grille[x][y].getEquipe()==1){
						gc.drawImage(images.get("1piegeur"),x*tailleParcelle,y*tailleParcelle+80);
					}
					else{
						gc.drawImage(images.get("2piegeur"),x*tailleParcelle,y*tailleParcelle+80);
					}
					r = (Robot)Plateau.grille[x][y];
					pourcentageDeVie = ((double)r.getEnergie()/60)*100;
					gc.setFill(Color.rgb((int)((100-pourcentageDeVie)*2.55), (int)(pourcentageDeVie*2.55),0));
					gc.fillRect(r.getAbscisse()*tailleParcelle+5, r.getOrdonnee()*tailleParcelle+120, pourcentageDeVie*0.4, 6);
				}
				else {gc.drawImage(images.get("herbe"),x*tailleParcelle,y*tailleParcelle+80);}}}

		//ON VAS AFFICHER LE ROBOT ACTUELLEMENT SELECTIONNER :
		if(selectedRobot != null){
			if(selectedRobot.getEquipe()==1){}
			else{}
			gc.strokeRect(selectedRobot.getAbscisse()*tailleParcelle, selectedRobot.getOrdonnee()*tailleParcelle+80, tailleParcelle, tailleParcelle);
		}
		//Affichons l'interface dynamique :

		//deplacement
		for (int x=0; x<tailleX; x++){
			for (int y=0; y<tailleY; y++){
				if(selectedRobot!=null){
					for(Direction d : Direction.values()){
						if(plateau.peuxDeplacer(joueur1, selectedRobot, d) 
								&& x==selectedRobot.getAbscisse()+d.getCoordonnees().getAbscisse()
								&& y==selectedRobot.getOrdonnee()+d.getCoordonnees().getOrdonnee()){
							if(selectedRobot.getEquipe()==1){gc.drawImage(images.get("1herbe"),x*tailleParcelle,y*tailleParcelle+80);}
							else{gc.drawImage(images.get("2herbe"),x*tailleParcelle,y*tailleParcelle+80);}
						}
					}
				}
			}
		}
		//combat
		if(selectedRobot!=null && !(selectedRobot instanceof Piegeur)){
			for (int x=0; x<tailleX; x++){
				for (int y=0; y<tailleY; y++){
					for(Direction d : Direction.values()){
						if(selectedRobot.peutTirer(d) && 
								x ==(selectedRobot.getAbscisse()+d.getCoordonnees().getAbscisse()) && 
								y ==(selectedRobot.getOrdonnee()+d.getCoordonnees().getOrdonnee())){
							gc.drawImage(images.get("combat"),selectedRobot.getRobotFromPlateau(d).getAbscisse()*tailleParcelle,selectedRobot.getRobotFromPlateau(d).getOrdonnee()*tailleParcelle+80);
						}
					}
				}
			}
		}
		if(j1gagne){
			gc.clearRect(0, 0,tailleX*tailleParcelle,tailleY+300*tailleParcelle+300);
			gc.fillText("Bravo joueur 1 ! Promis le prochaine ecran de fin sera mieux !", 10,50,1000); 
		}
		else if(j2gagne){
			gc.fillText("Bravo joueur 2 ! Promis le prochaine ecran de fin sera mieux !", 10,50,1000); 
		}
	}


	private ArrayList<Hitboxe> getHitboxes(ArrayList<Robot> unites){
		ArrayList<Hitboxe> hitboxes = new ArrayList<Hitboxe>();
		if(unites.get(0).getEquipe()==1){
			for(int i =0; i<B1.getTailleListe();i++){
				if(!B1.getRobot(i).getEstMort()){
					hitboxes.add(new Hitboxe(new Rectangle(i*50,0,tailleParcelle,tailleParcelle),B1.getRobot(i)));
				}
			}
			for(Robot r : unites){
				if(!(B1.estDans(r))){
					hitboxes.add(new Hitboxe(new Rectangle(r.getAbscisse()*tailleParcelle ,
							r.getOrdonnee()*tailleParcelle+80,
							tailleParcelle,tailleParcelle),r));
				}
			}
		}
		else{
			for(int i =0; i<B2.getTailleListe();i++){
				if(!B2.getRobot(i).getEstMort()){
					hitboxes.add(new Hitboxe(new Rectangle(450-i*50, 90+tailleY*50,tailleParcelle,tailleParcelle),B2.getRobot(i)));
				}
			}
			for(Robot r : unites){
				if(!(B2.estDans(r))){
					hitboxes.add(new Hitboxe(new Rectangle(r.getAbscisse()*tailleParcelle ,
							r.getOrdonnee()*tailleParcelle+80,
							tailleParcelle,tailleParcelle),r));
				}
			}
		}
		return hitboxes;
	}
	private ArrayList<Hitboxe> getHitboxes(Robot robot){
		ArrayList<Hitboxe> out = new ArrayList<Hitboxe>();
		if(!robot.getEstMort()){
			for (int x=0; x<tailleX; x++){
				for (int y=0; y<tailleY; y++){
					for(Direction d : Direction.values()){
						if(plateau.peuxDeplacer(joueur1, robot, d) 
								&& x==selectedRobot.getAbscisse()+d.getCoordonnees().getAbscisse()
								&& y==selectedRobot.getOrdonnee()+d.getCoordonnees().getOrdonnee()){
							out.add(new Hitboxe(new Rectangle(x*tailleParcelle,y*tailleParcelle+80, tailleParcelle,tailleParcelle),robot,d));
						}
					}
				}
			}
		}
		return out;
	}

	private ArrayList<Hitboxe> getHitboxesPourTaper(Robot robot){
		ArrayList<Hitboxe> out = new ArrayList<Hitboxe>();
		for(Direction d : Direction.values()){
			if(robot.peutTirer(d)){
				out.add(new Hitboxe(new Rectangle((robot.getRobotFromPlateau(d).getAbscisse())*tailleParcelle,robot.getRobotFromPlateau(d).getOrdonnee()*tailleParcelle+80,tailleParcelle,tailleParcelle), robot, d));
			}
		}
		return out;
	}
	private void tuerLesRobotsQuiSontDCD(Joueur j){
		for(Robot r : j.getListeRobot()){
			if(r.getEnergie()<=0){
				r.setEstmort();
				Plateau.grille[r.getAbscisse()][r.getOrdonnee()] = new Parcelle(new Coordonnees(r.getAbscisse(), r.getOrdonnee()));
			}
		}
	}
	private void soignerLesRobotsEnBase(Joueur j){
		for(int i =0;i<j.getBase().getTailleListe();i++){
			j.getBase().getRobot(i).recuperationEnergie();
		}
	}
	private void finDuTour(){
		tourjoueur1 = !tourjoueur1;
		soignerLesRobotsEnBase(joueur1);
		soignerLesRobotsEnBase(joueur2);
		tuerLesRobotsQuiSontDCD(joueur1);
		tuerLesRobotsQuiSontDCD(joueur2);
		if(joueur1.Aperdu()){j2gagne = true;System.out.println("flag");}
		if(joueur2.Aperdu()){j1gagne = true;System.out.println("flag");}
	}

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
			if(tourjoueur1){
				for(Hitboxe h : getHitboxes(joueur1.getListeRobot())){
					if(h.getHitboxe().contains(new Point2D(e.getSceneX(),e.getSceneY()))){
						selectedRobot=h.getRobot();
						hasFindSomething = true;
					}
				}
			}else{
				for(Hitboxe h : getHitboxes(joueur2.getListeRobot())){
					if(h.getHitboxe().contains(new Point2D(e.getSceneX(),e.getSceneY()))){
						selectedRobot=h.getRobot();
						hasFindSomething = true;
					}
				}
			}
			if(e.isShiftDown() && selectedRobot instanceof Piegeur){
				for(Hitboxe h : getHitboxes(selectedRobot)){
					if(h.getHitboxe().contains(new Point2D(e.getSceneX(),e.getSceneY()))){
						Piegeur p =  (Piegeur)selectedRobot;
						if(p.getNbrMine()>=0){
							System.out.println(p.getNbrMine());
							selectedRobot.tirer(h.getDirection());
							selectedRobot.subitDegatsEtMeurtPotentiellement(selectedRobot.getCout());
							finDuTour();
						}
					}
				}
			}

			if(selectedRobot != null && !(selectedRobot instanceof Piegeur && e.isShiftDown())){
				for(Hitboxe h : getHitboxes(selectedRobot)){
					if(h.getHitboxe().contains(new Point2D(e.getSceneX(),e.getSceneY()))){
						if(selectedRobot.getEquipe()==1){
							plateau.deplacerTest(joueur1, selectedRobot, h.getDirection());
							selectedRobot.subitDegatsEtMeurtPotentiellement(selectedRobot.getCoutAvancer());
							finDuTour();
							if(selectedRobot instanceof Char){plateau.deplacerTest(joueur1, selectedRobot, h.getDirection());}
						}
						else{
							plateau.deplacerTest(joueur2, selectedRobot, h.getDirection());
							selectedRobot.subitDegatsEtMeurtPotentiellement(selectedRobot.getCoutAvancer());
							finDuTour();
							if(selectedRobot instanceof Char){plateau.deplacerTest(joueur2, selectedRobot, h.getDirection());}}
					}
				}
				if(!(selectedRobot instanceof Piegeur)){
					for(Hitboxe h : getHitboxesPourTaper(selectedRobot)){
						if(h.getHitboxe().contains(new Point2D(e.getSceneX(),e.getSceneY()))){
							selectedRobot.tirer(h.getDirection());
							selectedRobot.subitDegatsEtMeurtPotentiellement(selectedRobot.getCout());
							finDuTour();
						}
					}
				}
			}
			if(!hasFindSomething){selectedRobot = null;}
			draw(gcJeu);
		});


	}

}
