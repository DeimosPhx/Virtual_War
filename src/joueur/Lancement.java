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
	
	private static int askUnite() {
		String reponseJoueur = "";
		reponseJoueur = JOptionPane.showInputDialog("Avec quelle unite veux-tu faire ton action ? Le CHAR, le TIREUR ou le PIEGEUR ?");
		if		(reponseJoueur.equals(CHAR))	{return 1;}
		else if	(reponseJoueur.equals(TIREUR))	{return 2;}
		else if	(reponseJoueur.equals(PIEGEUR))	{return 3;}
		
		else{ JOptionPane.showInputDialog("Ca ne veut rien dire.");
		}
		return 0;
	}
	
	private static int askActionTireurChar() {
		String reponseJoueur = "";
		reponseJoueur = JOptionPane.showInputDialog("Rentre ton action : se DEPLACER ou TIRER");
		if		(reponseJoueur.equals(DEPLACER)){return 1;}
		else if	(reponseJoueur.equals(TIRER))	{return 2;}
		
		else{ JOptionPane.showInputDialog("Ca ne veut rien dire.");
		}
		return 0;
	}
	
	private static int askActionPiegeur() {
		String reponseJoueur = "";
		reponseJoueur = JOptionPane.showInputDialog("Rentre ton action : se DEPLACER ou POSER une mine");
		if		(reponseJoueur.equals(DEPLACER)){return 1;}
		else if	(reponseJoueur.equals(POSER))	{return 2;}
		
		else{ JOptionPane.showInputDialog("Ca ne veut rien dire.");
		}
		return 0;
	}
	
	private static Direction askDirection4() {
		String reponseJoueur = "";
		reponseJoueur = JOptionPane.showInputDialog("Rentre ta direction : HAUT, BAS, GAUCHE, DROITE");
		if		(reponseJoueur.equals(HAUT))	{return Direction.HAUT;}
		else if	(reponseJoueur.equals(BAS))		{return Direction.BAS;}
		else if	(reponseJoueur.equals(GAUCHE))	{return Direction.GAUCHE;}
		else 									{return Direction.DROITE;}
	}
	
	private static Direction askDirection8(){
		String reponseJoueur = "";
		reponseJoueur = JOptionPane.showInputDialog("Rentre ta direction : HAUT, BAS, GAUCHE, DROITE, HAUTGAUCHE, HAUTDROITE, BASGAUCHE, BASDROITE");
		if		(reponseJoueur.equals(HAUT))		{return Direction.HAUT;}
		else if	(reponseJoueur.equals(BAS))			{return Direction.BAS;}
		else if	(reponseJoueur.equals(GAUCHE))		{return Direction.GAUCHE;}
		else if	(reponseJoueur.equals(DROITE))		{return Direction.DROITE;}
		else if	(reponseJoueur.equals(HAUTGAUCHE))	{return Direction.HAUT_GAUCHE;}
		else if	(reponseJoueur.equals(HAUTDROITE))	{return Direction.HAUT_DROITE;}
		else if	(reponseJoueur.equals(BASGAUCHE))	{return Direction.BAS_GAUCHE;}
		else										{return Direction.BAS_DROITE;}
	}
	
	public static void main(String args[]) {
		
		boolean aperdu = false, tourJoueur1 = true;
		int reponseUnite = 0; 
		int reponseAction = 0; 
		Direction reponseDirection;
		Plateau plateau = new Plateau(6,6);
		Vue vueJ1 = new Vue(1,plateau);
		Vue vueJ2 = new Vue(2,plateau);
		Coordonnees Base1 = new Coordonnees(0,0);
		Coordonnees Base2 = new Coordonnees(6,6);
		Base B1 = new Base(Base1);
		Base B2 = new Base(Base2);
		Joueur J1 = new Joueur(1,B1,vueJ1);
		Joueur J2 = new Joueur(2,B2,vueJ2);		
		Coordonnees cchar1 = new Coordonnees(1,0);
		Coordonnees cchar2 = new Coordonnees(6,5);
		Coordonnees ctireur1 = new Coordonnees(1,1);
		Coordonnees ctireur2 = new Coordonnees(5,5);
		Coordonnees cpiegeur1 = new Coordonnees(0,1);
		Coordonnees cpiegeur2 = new Coordonnees(5,6);
		Char char1 = new Char(1,cchar1,plateau);
		Char char2 = new Char(2,cchar2,plateau);
		Tireur tireur1 = new Tireur(1,ctireur1,plateau);
		Tireur tireur2 = new Tireur(2,ctireur2,plateau);
		Piegeur piegeur1 = new Piegeur(1,cpiegeur1,plateau);
		Piegeur piegeur2 = new Piegeur(2,cpiegeur2,plateau);
		J1.addRobot(char1, tireur1, piegeur1);
		J2.addRobot(char2, tireur2, piegeur2);
		plateau.setBase(B1);
		plateau.setBase(B2);
		plateau.setRobot(char1);
		plateau.setRobot(char2);
		plateau.setRobot(tireur1);
		plateau.setRobot(tireur2);
		plateau.setRobot(piegeur1);
		plateau.setRobot(piegeur2);

		while ( !J1.Aperdu() && !J2.Aperdu() ) {
			
			if ( tourJoueur1 ) {
				System.out.println("Tour du J1");
					reponseUnite = askUnite();
					if ( reponseUnite == 1 ) {
						reponseAction = askActionTireurChar();
						if ( reponseAction == 1 ) {
							reponseDirection = askDirection4();
							if ( char1.peutTirer(reponseDirection) ) {
								/* DEPLACEMENT */
								tourJoueur1 = false;
							}
						}
						if ( reponseAction == 2 ) {
							reponseDirection = askDirection4();
							if ( char1.peutTirer(reponseDirection) ) {
								char1.tirer(reponseDirection);
								tourJoueur1 = false;
							}
						}
					}
					if ( reponseUnite == 2 ) {
						reponseAction = askActionTireurChar();
						if ( reponseAction == 1 ) {
							reponseDirection = askDirection8();
							if ( tireur1.peutTirer(reponseDirection) ) {
								/* DEPLACEMENT */
								tourJoueur1 = false;
							}
						}
						if ( reponseAction == 2 ) {
							reponseDirection = askDirection4();
							if ( tireur1.peutTirer(reponseDirection) ) {
								tireur1.tirer(reponseDirection);
								tourJoueur1 = false;
							}
						}
					}
					if ( reponseUnite == 3 ) {
						reponseAction = askActionPiegeur();
						if ( reponseAction == 1 ) {
							reponseDirection = askDirection8();
							if ( tireur1.peutTirer(reponseDirection) ) {
								/* DEPLACEMENT */
								tourJoueur1 = false;
							}
						}
						if ( reponseAction == 2 ) {
							reponseDirection = askDirection8();
							if ( tireur1.peutTirer(reponseDirection) ) {
								tireur1.tirer(reponseDirection);
								tourJoueur1 = false;
							}
						}
					}
					J1.Aperdu();
					J2.Aperdu();
			}
			else {
				System.out.println("Tour du J2");
					reponseUnite = askUnite();
					if ( reponseUnite == 1 ) {
						reponseAction = askActionTireurChar();
						if ( reponseAction == 1 ) {
							reponseDirection = askDirection4();
							if ( char1.peutTirer(reponseDirection) ) {
								/* DEPLACEMENT */
								tourJoueur1 = true;
							}
						}
						if ( reponseAction == 2 ) {
							reponseDirection = askDirection4();
							if ( char1.peutTirer(reponseDirection) ) {
								char1.tirer(reponseDirection);
								tourJoueur1 = true;
							}
						}
					}
					if ( reponseUnite == 2 ) {
						reponseAction = askActionTireurChar();
						if ( reponseAction == 1 ) {
							reponseDirection = askDirection8();
							if ( tireur1.peutTirer(reponseDirection) ) {
								/* DEPLACEMENT */
								tourJoueur1 = true;
							}
						}
						if ( reponseAction == 2 ) {
							reponseDirection = askDirection4();
							if ( tireur1.peutTirer(reponseDirection) ) {
								tireur1.tirer(reponseDirection);
								tourJoueur1 = true;
							}
						}
					}
					if ( reponseUnite == 3 ) {
						reponseAction = askActionPiegeur();
						if ( reponseAction == 1 ) {
							reponseDirection = askDirection8();
							if ( tireur1.peutTirer(reponseDirection) ) {
								/* DEPLACEMENT */
								tourJoueur1 = true;
							}
						}
						if ( reponseAction == 2 ) {
							reponseDirection = askDirection8();
							if ( tireur1.peutTirer(reponseDirection) ) {
								tireur1.tirer(reponseDirection);
								tourJoueur1 = true;
							}
						}
					}
					J1.Aperdu();
					J2.Aperdu();
				}
			
			}
		
		/*
		while(!gagner){
			if(tourJoueur){
				System.out.println("tour J1");
				while(!actionCorrect){
					reponseAction = askAction();
					if(reponseAction == 1){
						reponseDirection = AskDirection();
						if(reponseDirection == 1){
							if(actionPossible(joueur, Direction.HAUT)){
								if(estTombe(joueur, Direction.HAUT)){
									gagner = true;
								}
								new Deplacer(joueur, Direction.HAUT).agit();
								actionCorrect = true;}
						}
						if(reponseDirection == 2){
							if(actionPossible(joueur, Direction.BAS)){
								if(estTombe(joueur, Direction.BAS)){
									gagner = true;
								}
								new Deplacer(joueur, Direction.BAS).agit();
								actionCorrect = true;}
						}
						if(reponseDirection == 3){
							if(actionPossible(joueur, Direction.GAUCHE)){
								if(estTombe(joueur, Direction.GAUCHE)){
									gagner = true;
								}
							new Deplacer(joueur, Direction.GAUCHE).agit();
							actionCorrect = true;}
						}
						if(reponseDirection == 4){
							if(actionPossible(joueur, Direction.DROITE)){
								if(estTombe(joueur, Direction.DROITE)){
									gagner = true;
								}
							new Deplacer(joueur, Direction.DROITE).agit();
							actionCorrect = true;}
						}
					}
					if(reponseAction == 2){
						reponseDirection = AskDirection();
						if(actionPossible(joueur, Direction.HAUT)){
							if(reponseDirection == 1){
								if(!annulation(joueur,Direction.HAUT, CREUSER)){
									new Creuser(joueur, Direction.HAUT).agit();
									actionCorrect = true;}
							}	
						}
						if(actionPossible(joueur, Direction.BAS)){
							if(reponseDirection == 2){
								if(!annulation(joueur, Direction.BAS, CREUSER)){
									new Creuser(joueur, Direction.BAS).agit();
									actionCorrect = true;}
							}
						}
						if(actionPossible(joueur, Direction.GAUCHE)){
							if(reponseDirection == 3){
								if(!annulation(joueur, Direction.GAUCHE, CREUSER)){
									new Creuser(joueur, Direction.GAUCHE).agit();
									actionCorrect = true;}
							}
						}
						if(actionPossible(joueur, Direction.DROITE)){
							if(reponseDirection == 4){
								if(!annulation(joueur, Direction.DROITE, CREUSER)){
									new Creuser(joueur, Direction.DROITE).agit();
									actionCorrect = true;}
							}
						}
					}
					if(reponseAction == 3){
						reponseDirection = AskDirection();
						if(actionPossible(joueur, Direction.HAUT)){
							if(reponseDirection == 1){
								if(!annulation(joueur,Direction.HAUT,TAS)){
									new PoserTas(joueur, Direction.HAUT).agit();
									actionCorrect = true;}
							}
						}
						if(actionPossible(joueur, Direction.BAS)){
							if(reponseDirection == 2){
								if(!annulation(joueur,Direction.BAS,TAS)){
									new PoserTas(joueur, Direction.BAS).agit();
									actionCorrect = true;}
							}
						}
						if(actionPossible(joueur, Direction.GAUCHE)){
							if(reponseDirection == 3){
								if(!annulation(joueur,Direction.GAUCHE,TAS)){
									new PoserTas(joueur, Direction.GAUCHE).agit();
									actionCorrect = true;}	
							}
						}
						if(actionPossible(joueur, Direction.DROITE)){
							if(reponseDirection == 4){
								if(!annulation(joueur,Direction.DROITE,TAS)){
									new PoserTas(joueur, Direction.DROITE).agit();
									actionCorrect = true;}
							}
						}
					}
				}
				actionCorrect = false;
				tourJoueur = false;
				terrain.updateUI();
			}
			*/
		
	}

}