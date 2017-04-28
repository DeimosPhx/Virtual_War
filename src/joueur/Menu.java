package joueur;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import unite.*;
import Terrain.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.text.Text;
public class Menu extends Application{
	
	// IMPORTANT
	// NE PAS OUBLIER DE PLACER LES IMAGES DANS LE REPERTOIRES SRC ! (VOIR GIT)
	//
	/*
	 * Objectifs:
	 * 	acceuillir le joueur
	 *  on fait composer les �quipes des 2 joueurs
	 *  ??
	 */

	private ArrayList<Robot> compo = new ArrayList<Robot>();
	private static Map<String,Image> images = new HashMap<>();
	private boolean initialisation = true;

	/*	showInputDialog(null,String message);
	 * BESOINS:
	 * 	compo #arraylist
	 *  cr�er base avec compo #return base
	 *  generateur terrain #return terrain
	 */
	public Menu(){
	}
	public ArrayList<Robot> getComp(){
		return compo;
	}
	public static Plateau acceuil(){
		/* JOptionPane pour dire bonjour */
		JOptionPane.showMessageDialog(null, "Bienvenue dans Virtual War !");
		//int tx = Integer.valueOf(taux);
		Plateau plat = new Plateau(10,10);		
		return plat;
	}
	public static void setObstacle(Plateau ter,Joueur j1,Joueur j2){
		/*
		 * on genere les obstacles selon le taux
		 * en s'assurant qu'il existe un chemin d'une base a l'autre
		 */
		Parcelle[][] plat = ter.getGrille();
		String taux = JOptionPane.showInputDialog(null,"Taux d'obstacles : ");
		int tx = Integer.valueOf(taux);
		//plateau de 100case (10*10)
		//EZ taux
		Random rnd = new Random();
		boolean isOut = false;
		for(int i=0;i<tx;i++){
			do{
				isOut = false;
				int x = rnd.nextInt(9);
				int y = rnd.nextInt(9);
				if((x!=j1.getBase().getCord().getAbscisse() && y!=j1.getBase().getCord().getOrdonnee()) 
						&& (x!=j2.getBase().getCord().getAbscisse() && y!=j2.getBase().getCord().getOrdonnee())
						&& (x!=j1.getBase().getCord().getAbscisse()+1 && y!=j1.getBase().getCord().getOrdonnee()+1)
						&& (x!=j2.getBase().getCord().getAbscisse()+1 && y!=j2.getBase().getCord().getOrdonnee()+1)){
					plat[x][y] = new Obstacle(new Coordonnees(x,y));
					isOut = true;
				}
			}while(!isOut);
		}
	}

	public static boolean obstacleLigCol(Plateau plat){
		//Colonne
		for(int i=0; i<10; i++){
			int cpt = 0;
			if(plat.getGrille()[i][0] instanceof Obstacle){
				cpt++;
				if(cpt ==3){
					return false;
				}
			}
		}

		for(int j=0; j<10; j++){
			int cpt = 0;
			if(plat.getGrille()[0][j] instanceof Obstacle){
				cpt++;
				if(cpt ==3){
					return false;
				}
			}
		}
		return true;
	}

	public static boolean obstacleDiagonale(Plateau plat){
		Coordonnees actuel = new Coordonnees(0,0);
		//Pour savoir si la coordonnees actuel ne descend pas en dessous du tableau
		while(plat.estDans(new Coordonnees(actuel.getAbscisse()+1,actuel.getOrdonnee()))){
			//Pour savoir si la case en diagonale � droite sera encore dans le tableau
			while(plat.estDans(new Coordonnees((actuel.getAbscisse()-1),actuel.getOrdonnee()+1))){
				actuel = new Coordonnees(actuel.getAbscisse()-1,actuel.getOrdonnee()+1);
				int cpt=0;
				if(plat.getGrille()[actuel.getAbscisse()][actuel.getOrdonnee()] instanceof Obstacle){
					cpt++;
				}else{cpt=0;}
				if(cpt == 3){
					return false;
				}

			}
			actuel= new Coordonnees(actuel.getAbscisse()+1,actuel.getOrdonnee());
		}
		return true;
	}

	public static boolean obstaclebase(Plateau plat, Coordonnees coord){
		return (coord.getAbscisse()>4 && coord.getOrdonnee()>4 && (coord.getAbscisse()<(plat.getX()-3))&& (coord.getOrdonnee()<(plat.getY()-3)));  
	}
	public static ArrayList<Robot> compo(Joueur j,Plateau plat){
		//int nbRobot = 0;
		//String nbRobot1 = JOptionPane.showInputDialog(null,"J"+j.getEquipe() + "\n entrez le nombre de robot souhait� : ");
		//nbRobot = Integer.valueOf(nbRobot1);
		JOptionPane.showMessageDialog(null, "J"+j.getEquipe()+": formez votre arm�e !");
		ArrayList<Robot> armee = new ArrayList<Robot>();
		for(int i=0; i<3; i++){
			boolean isOut;
			do{
				isOut = true;
				String selec = JOptionPane.showInputDialog(null,"Quel robot souhaitez vous utiliser ?\n CHAR \n PIEGEUR \n TIREUR");

				switch(selec){
				case "CHAR": 
					Char c1 = new Char(j.getEquipe(),new Coordonnees((j.getEquipe()-1)*9, (j.getEquipe()-1)*9), plat);
					armee.add(c1);
					/*
					 * creer une unit�e et l'ajouter � l'arraylist de la base du joueur (selon son equipe)
					 */
					break;
				case "PIEGEUR":
					Piegeur p1 = new Piegeur(j.getEquipe(),new Coordonnees((j.getEquipe()-1)*9, (j.getEquipe()-1)*9), plat);
					armee.add(p1);
					break;
				case "TIREUR":
					Tireur t1 = new Tireur(j.getEquipe(),new Coordonnees((j.getEquipe()-1)*9, (j.getEquipe()-1)*9), plat);
					armee.add(t1);
					break;
				default:
					JOptionPane.showMessageDialog(null, "ERREUR ENTREE INVALIDE");
					isOut = false;
				}
			}while(!isOut);
		}
		return armee;
	}	
	private void draw(GraphicsContext gc){
		//CE IF PERMET DE DETERMINER CE QUE LE MENU DOIT AFFICHER
		//EN L OCCURENCE LA CREATION DE COMPO
		if(initialisation){
			gc.clearRect(0, 0, 300, 300);
			gc.drawImage(images.get("char"), 50, 50);
			gc.drawImage(images.get("piegeur"), 200, 50);
			gc.drawImage(images.get("tireur"), 350, 50);
			gc.setStroke(Color.BLACK);
			//DESSIN DES CARRES
			gc.strokeRect(50,150, 30,30);
			gc.strokeRect(80,150, 30,30);
			gc.strokeRect(110,150, 30,30);
			//DESSIN DU NOMBRE
			gc.fillText("0", 90, 170);
			//PLUS
			gc.fillRect(63,150, 3,30);
			gc.fillRect(50,164, 30,3);
			//MOINS
			gc.fillRect(110,164, 30,3);



		}
	}

	public void start(Stage stage) {
		VBox root = new VBox();
		Canvas canvas = new Canvas (500, 200);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		images.put("tireur"		,new Image("tireur.png"	));
		images.put("piegeur"	,new Image("piegeur.png"));
		images.put("char"		,new Image("char.png"	));
		draw(gc);
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		stage.setTitle("Hello Paint");
		stage.setScene(scene);
		stage.show();
	}
	//DEMMARE LE MENU INTERACTIF, A UTILISER DANS LANCEMENT:
	public void lancement(String[] args) {
		launch(args);
	}
}
