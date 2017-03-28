package Essai;


import javax.swing.JOptionPane;
import taupes.terrain.*;
import taupes.actions.Creuser;
import taupes.actions.Deplacer;
import taupes.actions.PoserTas;
import taupes.parcelles.*;
import java.util.Random;

public class EssaiTerrain {

	private final static String DEPLACER = "deplacer";
	private final static String CREUSER = "creuser";
	private final static String TAS = "tas";
	private final static String HAUT = "haut";
	private final static String BAS = "bas";
	private final static String GAUCHE = "gauche";
	private final static String DROITE = "droite";

	private static int askAction(){
		String reponseJoueur = "";
		reponseJoueur = JOptionPane.showInputDialog("Rentre ton action : deplacer, creuser, tas");
		if		(reponseJoueur.equals(DEPLACER)){return 1;}
		else if	(reponseJoueur.equals(CREUSER))	{return 2;}
		else if	(reponseJoueur.equals(TAS))		{return 3;}

		else{JOptionPane.showInputDialog("Ta reponse ne correspond à rien :(");}
		return 0;
	}
	private static int AskDirection(){
		String reponseJoueur = "";
		reponseJoueur = JOptionPane.showInputDialog("Rentre ta direction : haut, bas, gauche, droite");
		if		(reponseJoueur.equals(HAUT))	{return 1;}
		else if	(reponseJoueur.equals(BAS))		{return 2;}
		else if	(reponseJoueur.equals(GAUCHE))	{return 3;}
		else if	(reponseJoueur.equals(DROITE))	{return 4;}

		else{JOptionPane.showInputDialog("Ton action ne correspond à rien :(");}
		return 0;
	}
	private static boolean actionPossible(Taupe taupe, Direction direction){
		Coordonnees caseCible = taupe.getCoordonnees().cibler(direction.getCoordonnees());
		if(!taupe.getVue().estTerrain(caseCible) || taupe.getVue().estTas(caseCible) ){return false;}
		return true;
	}
	private static boolean estTombe(Taupe taupe, Direction direction){
		Coordonnees caseCible = taupe.getCoordonnees().cibler(direction.getCoordonnees());
		if(taupe.getVue().estTrou(caseCible) ){return true;}
		return false;
	}
	private static boolean annulation(Taupe taupe, Direction direction, String action){
		Coordonnees caseCible = taupe.getCoordonnees().cibler(direction.getCoordonnees());
		if(action.equals(CREUSER) && taupe.getVue().estTas(caseCible)){			return true;}
		else if(action.equals(TAS) && taupe.getVue().estTrou(caseCible)){				return true;}
		return false;
	}
	public static void main(String args[]){


		boolean gagner = false, tourJoueur = true, actionCorrect = false;
		int reponseAction = 0, reponseDirection = 0;
		Terrain terrain = new Terrain(8);
		Vue vueJ1 = new Vue(terrain);
		Vue vueJ2 = new Vue(terrain);
		Taupe joueur = new Taupe(1, vueJ1);
		joueur.setCoordonnees(new Coordonnees(1, 1));
		Taupe cpu = new Taupe(2, vueJ2);
		cpu.setCoordonnees(new Coordonnees(6, 6));
		joueur.getVue().poserTaupe(joueur, new Coordonnees(1,1));
		cpu.getVue().poserTaupe(cpu, new Coordonnees(6,6));
		
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
			else{
				System.out.println("Tour J2");
				while(!actionCorrect){
					reponseAction = askAction();
					if(reponseAction == 1){
						reponseDirection = AskDirection();
						if(reponseDirection == 1){
							if(actionPossible(cpu, Direction.HAUT)){
								new Deplacer(cpu, Direction.HAUT).agit();
								actionCorrect = true;}
						}
						if(reponseDirection == 2){
							if(actionPossible(cpu, Direction.BAS)){
								new Deplacer(cpu, Direction.BAS).agit();
								actionCorrect = true;}
						}
						if(reponseDirection == 3){
							if(actionPossible(cpu, Direction.GAUCHE)){
								new Deplacer(cpu, Direction.GAUCHE).agit();
								actionCorrect = true;}
						}
						if(reponseDirection == 4){
							if(actionPossible(cpu, Direction.DROITE)){
								new Deplacer(cpu, Direction.DROITE).agit();
								actionCorrect = true;}
						}
					}


					if(reponseAction == 2){
						reponseDirection = AskDirection();
						if(actionPossible(cpu, Direction.HAUT)){

							if(reponseDirection == 1){
								if(!annulation(cpu,Direction.HAUT, CREUSER)){
									new Creuser(cpu, Direction.HAUT).agit();
									actionCorrect = true;}
							}	
						}
						if(actionPossible(cpu, Direction.BAS)){
							if(reponseDirection == 2){
								if(!annulation(cpu, Direction.BAS, CREUSER)){
									new Creuser(cpu, Direction.BAS).agit();
									actionCorrect = true;}
							}
						}
						if(actionPossible(cpu, Direction.GAUCHE)){
							if(reponseDirection == 3){
								if(!annulation(cpu, Direction.GAUCHE, CREUSER)){
									new Creuser(cpu, Direction.GAUCHE).agit();
									actionCorrect = true;}
							}
						}
						if(actionPossible(cpu, Direction.DROITE)){
							if(reponseDirection == 4){
								if(!annulation(cpu, Direction.DROITE, CREUSER)){
									new Creuser(cpu, Direction.DROITE).agit();
									actionCorrect = true;}
							}
						}
					}

					if(reponseAction == 3){
						reponseDirection = AskDirection();
						if(actionPossible(cpu, Direction.HAUT)){
							if(reponseDirection == 1){
								if(!annulation(cpu,Direction.HAUT,TAS)){
									new PoserTas(cpu, Direction.HAUT).agit();
									actionCorrect = true;}
							}
						}
						if(actionPossible(cpu, Direction.BAS)){
							if(reponseDirection == 2){
								if(!annulation(cpu,Direction.BAS,TAS)){
									new PoserTas(cpu, Direction.BAS).agit();
									actionCorrect = true;}
							}
						}if(actionPossible(cpu, Direction.GAUCHE)){
							if(reponseDirection == 3){
								if(!annulation(cpu,Direction.GAUCHE,TAS)){
									new PoserTas(cpu, Direction.GAUCHE).agit();
									actionCorrect = true;}	
							}
						}
						if(actionPossible(cpu, Direction.DROITE)){
							if(reponseDirection == 4){
								if(!annulation(cpu,Direction.DROITE,TAS)){
									new PoserTas(cpu, Direction.DROITE).agit();
									actionCorrect = true;}
							}
						}
					}
				}
				actionCorrect = false;
				tourJoueur = true;
				terrain.updateUI();
			}
		}
		System.out.println("J2 a gagné !");
	}
}
