package joueur;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import unite.*;
import Terrain.*;
public class Menu {
	/*
	 * Objectifs:
	 * 	acceuillir le joueur
	 *  on fait composer les équipes des 2 joueurs
	 *  ??
	 */
	
	/*	showInputDialog(null,String message);
	 * BESOINS:
	 * 	compo #arraylist
	 *  créer base avec compo #return base
	 *  generateur terrain #return terrain
	 */
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
			//Pour savoir si la case en diagonale à droite sera encore dans le tableau
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
	
	public boolean autoriserObstacle(Plateau plateau,Coordonnees c){
		int cpt=0;
		//Vérifier que la case possèdent ou non 3 cases obstacles voisines
		if((c.getOrdonnee()-1)<0||!(plateau.getGrille()[c.getOrdonnee()-1][c.getAbscisse()].estVide())){
			//voisin du dessus
			cpt++;
		}
		if((c.getAbscisse()+1)>plateau.getY()||!(plateau.getGrille()[c.getOrdonnee()][c.getAbscisse()+1].estVide())){
			//voisin de droite
			cpt++;
		}
		if((c.getOrdonnee()+1)>plateau.getX()||!(plateau.getGrille()[c.getOrdonnee()+1][c.getAbscisse()].estVide())){
			//voisin du dessous
			cpt++;
		}
		if((c.getAbscisse()-1)<0||!(plateau.getGrille()[c.getOrdonnee()][c.getAbscisse()-1].estVide())){
			//voisin de gauche
			cpt++;
		}
		
		if(cpt==3){return false;}
		else{return true;}
		
	}
	public static ArrayList<Robot> compo(Joueur j,Plateau plat){
		//int nbRobot = 0;
		//String nbRobot1 = JOptionPane.showInputDialog(null,"J"+j.getEquipe() + "\n entrez le nombre de robot souhaité : ");
		//nbRobot = Integer.valueOf(nbRobot1);
		JOptionPane.showMessageDialog(null, "J"+j.getEquipe()+": formez votre armée !");
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
				 * creer une unitée et l'ajouter à l'arraylist de la base du joueur (selon son equipe)
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
}
