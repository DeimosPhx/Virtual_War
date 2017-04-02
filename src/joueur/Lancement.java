package joueur;
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

	private static int askUniteType() {
		String reponseJoueur = "";
		while(true){
			reponseJoueur = JOptionPane.showInputDialog("Avec quelle unite veux-tu faire ton action ? Entre le type d'unité parmis : \n char \n tireur \n piegeur");
			if		(reponseJoueur.equals(CHAR))	{return 1;}
			else if	(reponseJoueur.equals(TIREUR))	{return 3;}
			else if	(reponseJoueur.equals(PIEGEUR))	{return 2;}

			else{ JOptionPane.showInputDialog("Ca ne veut rien dire.");
			}
		}		
	}
	private static int askUniteNumero() {
		String reponseJoueur = "";
		while(true){
			reponseJoueur = JOptionPane.showInputDialog("Avec quelle unite veux-tu faire ton action ? Entre le n° de l'unité");
			if ( reponseJoueur.charAt(0) >= '0' && reponseJoueur.charAt(0) < '9') {
				return Integer.valueOf(reponseJoueur);
				//else{ JOptionPane.showInputDialog("Ca ne veut rien dire.");
			}
		}
	}

	private static int askActionTireurChar() {
		String reponseJoueur = "";
		while(true){
			reponseJoueur = JOptionPane.showInputDialog("Rentre ton action : se DEPLACER ou TIRER");
			if		(reponseJoueur.equals(DEPLACER)){return 1;}
			else if	(reponseJoueur.equals(TIRER))	{return 2;}
			else{ JOptionPane.showInputDialog("Ca ne veut rien dire.");
			}
		}
	}


	private static int askActionPiegeur() {
		String reponseJoueur = "";
		while(true){
			reponseJoueur = JOptionPane.showInputDialog("Rentre ton action : se DEPLACER ou POSER une mine");
			if		(reponseJoueur.equals(DEPLACER)){return 1;}
			else if	(reponseJoueur.equals(POSER))	{return 2;}
			else									{ JOptionPane.showInputDialog("Ca ne veut rien dire.");
			}
		}
	}

	private static Direction askDirection4() {
		String reponseJoueur = "";
		while(true){
			reponseJoueur = JOptionPane.showInputDialog("Rentre ta direction : haut, bas, gauche, droite");
			if		(reponseJoueur.equals(HAUT))	{return Direction.HAUT;}
			else if	(reponseJoueur.equals(BAS))		{return Direction.BAS;}
			else if	(reponseJoueur.equals(GAUCHE))	{return Direction.GAUCHE;}
			else if (reponseJoueur.equals(DROITE))  {return Direction.DROITE;}
		}
	}

	private static Direction askDirection8(){
		String reponseJoueur = "";
		while(true){
			reponseJoueur = JOptionPane.showInputDialog("Rentre ta direction : haut, bas, gauche, droite, hautgauche, hautdroite, basgauche, basdroite");
			if		(reponseJoueur.equals(HAUT))		{return Direction.HAUT;}
			else if	(reponseJoueur.equals(BAS))			{return Direction.BAS;}
			else if	(reponseJoueur.equals(GAUCHE))		{return Direction.GAUCHE;}
			else if	(reponseJoueur.equals(DROITE))		{return Direction.DROITE;}
			else if	(reponseJoueur.equals(HAUTGAUCHE))	{return Direction.HAUT_GAUCHE;}
			else if	(reponseJoueur.equals(HAUTDROITE))	{return Direction.HAUT_DROITE;}
			else if	(reponseJoueur.equals(BASGAUCHE))	{return Direction.BAS_GAUCHE;}
			else if (reponseJoueur.equals(BASDROITE))	{return Direction.BAS_DROITE;}
		}
	}
	private static void détruire(Joueur j, Plateau plateau, Robot charCourant){
		j.supprimerRobot(charCourant);
		plateau.getGrille()[charCourant.getAbscisse()][charCourant.getOrdonnee()]
				= new Parcelle(charCourant.getCord());
	}
	private static void onVasDetruireTousLesRobotsSansVie(Joueur j,Plateau plateau){
		while(j.PeutEtreYaDesMorts() != null){
			détruire(j, plateau, j.PeutEtreYaDesMorts());
		}
	}
	public static void main(String args[]) {

		boolean tourJoueur1 = true, tourValide = false;
		int reponseUniteType = -1, reponseUniteNumero = -1, reponseAction = 0,
				resultatDuDeplacer;
		Direction reponseDirection;

		Char charCourant = null;
		Tireur tireurCourant = null;
		Piegeur piegeurCourant = null;
		Plateau plateau = Menu.acceuil();
		Vue vueJ1 = new Vue(1,plateau);
		Vue vueJ2 = new Vue(2,plateau);
		Base b1 = new Base(new Coordonnees(0,0),1);
		Base b2 = new Base(new Coordonnees(9,9),2);
		plateau.setBase(b1);
		plateau.setBase(b2);
		Joueur j1 = new Joueur(1,b1,vueJ1);
		Joueur j2 = new Joueur(2,b2,vueJ2);		
		Menu.compo(j1, plateau);
		b1.initaliserLesListes(j1.getListeRobot());
		Menu.compo(j2, plateau);
		b2.initaliserLesListes(j2.getListeRobot());

		while ( !j1.Aperdu() && !j2.Aperdu() ) {
			tourValide = false;
			while(!tourValide){
				tourValide = true;
				if ( tourJoueur1 ) 	{System.out.println("\n \nTour du J1");
				System.out.println(vueJ1);
				System.out.println(j1);
				}
				else				{System.out.println("\n \nTour du J2");
				System.out.println(vueJ2);
				System.out.println(j2);
				}
				reponseUniteType = askUniteType();
				if ( reponseUniteType == 1 ) {//char
					if(tourJoueur1){charCourant = j1.getListeChar().get(reponseUniteNumero);}
					else{			charCourant = j2.getListeChar().get(reponseUniteNumero);} 
					reponseUniteNumero = askUniteNumero();					
					reponseAction = askActionTireurChar();
					if(reponseAction == 1){  	//deplacer
					reponseDirection = askDirection4();
					
					
					
					
					
					}
					else if(reponseAction == 2){//tirer
						reponseDirection= askDirection4();
						if(!charCourant.peutTirer(reponseDirection)){
							tourValide = false;
						}
						else{
							charCourant.tirer(reponseDirection);
							charCourant.subitDegatsEtMeurtPotentiellement(charCourant.getCout());
						}
					}
				}


				if ( reponseUniteType == 2 ) {	//piegeur
					reponseUniteNumero = askUniteNumero();
					if(tourJoueur1){piegeurCourant = j1.getListePiegeur().get(reponseUniteNumero);}
					else{			piegeurCourant = j2.getListePiegeur().get(reponseUniteNumero);} 
					reponseAction = askActionPiegeur();
					if(reponseAction == 1){  	//deplacer
						reponseDirection = askDirection8();
						
						
						
						
					}
					else if(reponseAction == 2){//tirer
						reponseDirection= askDirection8();
						if(piegeurCourant.peutTirer(reponseDirection)){
							tourValide = false;
						}
						else{
							piegeurCourant.tirer(reponseDirection);
							piegeurCourant.subitDegatsEtMeurtPotentiellement(piegeurCourant.getCout());
						}
					}
				}


				if ( reponseUniteType == 3 ) {	//tireur
					reponseUniteNumero = askUniteNumero();
					if(tourJoueur1){tireurCourant = j1.getListeTireur().get(reponseUniteNumero);}
					else{			tireurCourant = j2.getListeTireur().get(reponseUniteNumero);}
					reponseAction = askActionTireurChar();
					if(reponseAction == 1){  	//deplacer
						reponseDirection = askDirection8();

						
						
						
						
					}
					else if(reponseAction == 2){//tirer
						reponseDirection= askDirection4();
						if(!tireurCourant.peutTirer(reponseDirection)){
							tourValide = false;
						}
						else{
							tireurCourant.tirer(reponseDirection);
							tireurCourant.subitDegatsEtMeurtPotentiellement(tireurCourant.getCout());
						}
					}
				}
				if(!tourValide){
					JOptionPane.showInputDialog("Ton action n'est pas possible");
				}
			}
			onVasDetruireTousLesRobotsSansVie(j1, plateau);
			onVasDetruireTousLesRobotsSansVie(j2, plateau);
			b1.soigneTousLesRobots();
			b2.soigneTousLesRobots();
			b1.supprimerLesRobotsDontLesCoordonneesCorrespondentPasAlaBase();
			b2.supprimerLesRobotsDontLesCoordonneesCorrespondentPasAlaBase();
			tourJoueur1 = !tourJoueur1;
			vueJ2 = new Vue(2,plateau);
		}
		System.out.println("bien joué");
	}
}
