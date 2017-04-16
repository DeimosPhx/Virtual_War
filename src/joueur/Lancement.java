package joueur;
import javax.*;
import javax.swing.JOptionPane;
import Terrain.*;
import unite.*;

public class Lancement {

	private final static String DEPLACER = "deplacer";
	private final static String TIRER = "tirer";
	private final static String POSER = "poser";
	private final static String CHAR = "char";
	private final static String TIREUR = "tireur";
	private final static String PIEGEUR = "piegeur";
	private final static String HAUT = "haut";
	private final static String BAS = "bas";
	private final static String GAUCHE = "gauche";
	private final static String DROITE = "droite";
	private final static String HAUTGAUCHE = "haut gauche";
	private final static String HAUTDROITE = "haut droite";
	private final static String BASGAUCHE = "bas gauche";
	private final static String BASDROITE = "bas droite";
	private final static String UNITE1 = "unite1";
	private final static String UNITE2 = "unite2";
	private final static String UNITE3 = "unite3";

	private static int askUnite(Joueur J) {
		String reponseJoueur = "";
			reponseJoueur = JOptionPane.showInputDialog("J"+J.getEquipe()+"Avec quelle unite veux-tu faire ton action ?\n unite1\n unite2\n unite3");

			if		(reponseJoueur.equals(UNITE1))	{return 1;}
			else if	(reponseJoueur.equals(UNITE2))	{return 2;}
			else if	(reponseJoueur.equals(UNITE3))	{return 3;}

			else{ JOptionPane.showInputDialog("Ca ne veut rien dire.");
			return 0;
			}
		}

	private static int askActionTireurChar() {
		String reponseJoueur = "";
			reponseJoueur = JOptionPane.showInputDialog("Rentre ton action : se DEPLACER ou TIRER");
			if		(reponseJoueur.equals(DEPLACER)){return 1;}
			else if	(reponseJoueur.equals(TIRER))	{return 2;}

			else{ JOptionPane.showInputDialog("Ca ne veut rien dire.");
			return 0;
			}
	}

	private static int askActionPiegeur() {
		String reponseJoueur = "";
		while(true){
			reponseJoueur = JOptionPane.showInputDialog("Rentre ton action : se DEPLACER ou POSER une mine");
			if		(reponseJoueur.equals(DEPLACER)){return 1;}
			else if	(reponseJoueur.equals(POSER))	{return 2;}

			else{ JOptionPane.showInputDialog("Ca ne veut rien dire.");}
		}
	}

	private static Direction askDirection4() {
		String reponseJoueur = "";
		while(true){
			reponseJoueur = JOptionPane.showInputDialog("Rentre ta direction : HAUT, BAS, GAUCHE, DROITE");
			if		(reponseJoueur.equals(HAUT))	{return Direction.HAUT;}
			else if	(reponseJoueur.equals(BAS))		{return Direction.BAS;}
			else if	(reponseJoueur.equals(GAUCHE))	{return Direction.GAUCHE;}
			else if	(reponseJoueur.equals(DROITE))	{return Direction.DROITE;}
			else{return null;}
		}
	}

	private static Direction askDirection8(){
		String reponseJoueur = "";
		while(true){
			reponseJoueur = JOptionPane.showInputDialog("Rentre ta direction : HAUT, BAS, GAUCHE, DROITE, HAUTGAUCHE, HAUTDROITE, BASGAUCHE, BASDROITE");
			if		(reponseJoueur.equals(HAUT))		{return Direction.HAUT;}
			else if	(reponseJoueur.equals(BAS))			{return Direction.BAS;}
			else if	(reponseJoueur.equals(GAUCHE))		{return Direction.GAUCHE;}
			else if	(reponseJoueur.equals(DROITE))		{return Direction.DROITE;}
			else if	(reponseJoueur.equals(HAUTGAUCHE))	{return Direction.HAUT_GAUCHE;}
			else if	(reponseJoueur.equals(HAUTDROITE))	{return Direction.HAUT_DROITE;}
			else if	(reponseJoueur.equals(BASGAUCHE))	{return Direction.BAS_GAUCHE;}
			else if (reponseJoueur.equals(BASDROITE))	{return Direction.BAS_DROITE;}
			else{return null;}
		}
	}

	public static void main(String args[]) {

		boolean j1aperdu = false, tourJoueur1 = true,j2aperdu = false;
		int reponseUnite = 0; 
		int reponseAction = 0; 
		Direction reponseDirection;
		Plateau plat = Menu.acceuil();
		Vue vueJ1 = new Vue(1,plat);
		Vue vueJ2 = new Vue(2,plat);
		Base B1 = new Base(new Coordonnees(0,0),1);
		Base B2 = new Base(new Coordonnees(9,9),2);
		Joueur J1 = new Joueur(1,B1,vueJ1);
		Joueur J2 = new Joueur(2,B2,vueJ2);		
		plat.setBase(B1);
		plat.setBase(B2);
		Menu.setObstacle(plat,J1,J2);
		J1.setList(Menu.compo(J1, plat));
		J2.setList(Menu.compo(J2, plat));
		B1.setList(J1.getListeRobot());
		B2.setList(J2.getListeRobot());
		int selecUnit,selecAction;
		Direction selecDirec;
		while(!j1aperdu && !j2aperdu){
			/*
			 * TOUR DE J1
			 */
			System.out.println(plat);
			boolean isOut;
			do{
				isOut = false;
				do{
					selecUnit = askUnite(J1);
					if(selecUnit == 0){System.out.println("ERROR: impossible de trouver l'unité demandé!");}
				}while(selecUnit == 0);
				selecAction = askActionTireurChar();
				do{
					selecDirec = askDirection4();
					if(selecDirec.equals(null)){System.out.println("ERROR: impossible de determiner la direction!");}
				}while(selecDirec.equals(null));
				switch(selecAction){
				case 0:
					//error
					System.out.println("Action impossible !");
					isOut = false;
					break;
				case 1:
					//deplacement
					isOut = plat.deplacerTest(J1,J1.getRobot(selecUnit-1), selecDirec);
					break;
				case 2:
					//tire
					if (J1.getRobot(selecUnit-1).peutTirer(selecDirec) ) {
						isOut = true;
						J1.getRobot(selecUnit-1).tirer(selecDirec);
					}
					break;
				}
			}while(!isOut);

			/*
			 * TOUR DE J2
			 */
			System.out.println(plat);
			do{
				isOut = false;
				do{
					selecUnit = askUnite(J2);
					if(selecUnit == 0){System.out.println("ERROR: impossible de trouver l'unité demandé");}
				}while(selecUnit == 0);
				selecAction = askActionTireurChar();
				do{
					selecDirec = askDirection4();
					if(selecDirec.equals(null)){System.out.println("ERROR: impossible de determiner la direction!");}
				}while(selecDirec.equals(null));
				switch(selecAction){
				case 0:
					//error
					System.out.println("Action impossible !");
					isOut = false;
					break;
				case 1:
					//deplacement
					isOut = plat.deplacerTest(J2,J2.getRobot(selecUnit-1), selecDirec);
					break;
				case 2:
					//tire
					if (J2.getRobot(selecUnit-1).peutTirer(selecDirec) ) {
						isOut = true;
						J2.getRobot(selecUnit-1).tirer(selecDirec);
					}
					break;
				}
			}while(!isOut);
		}
		

	}

}