package joueur;
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
		//String taux = JOptionPane.showInputDialog(null,"Taux d'obstacles : ");
		//int tx = Integer.valueOf(taux);
		Plateau plat = new Plateau(10,10);
		/*
		 * on genere les obstacles selon le taux
		 */
		return plat;
	}
	public boolean obstacleLigCol(Plateau plat){
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
	
	public boolean obstacleDiagonale(Plateau plat){
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
	
	public boolean obstaclebase(Plateau plat, Coordonnees coord){
		return (coord.getAbscisse()>4 && coord.getOrdonnee()>4 && (coord.getAbscisse()<(plat.getX()-3))&& (coord.getOrdonnee()<(plat.getY()-3)));  
	}
	public static void compo(Joueur j,Plateau plat){
		int nbRobot = 0;
        while (true) {
            String nbRobot1 = JOptionPane.showInputDialog(null,"J"+j.getEquipe() + "\n entrez le nombre de robot souhaité, entre 1 et 9 : ");
            if ( nbRobot1.charAt(0) > '0' && nbRobot1.charAt(0) <= '9') {
                nbRobot = Integer.valueOf(nbRobot1);
                break;
            }
        }
		for(int i=0; i<nbRobot; i++){
			boolean isOut;
			do{
				isOut = true;
				String selec = JOptionPane.showInputDialog(null,"Quel robot souhaitez vous utiliser ?\n char \n piegeur \n tireur");
			
			switch(selec){
			case "char": 
				Char c1 = new Char(j.getEquipe(),new Coordonnees((j.getEquipe()-1)*9, (j.getEquipe()-1)*9), plat);
				j.addRobot(c1);
				isOut = false;
				/*
				 * ecreer une unitée et l'ajouter à l'arraylist de la base du joueur (selon son equipe)
				 */
				break;
			case "piegeur":
				Piegeur p1 = new Piegeur(j.getEquipe(),new Coordonnees((j.getEquipe()-1)*9, (j.getEquipe()-1)*9), plat);
				j.addRobot(p1);
				isOut = false;
				break;
			case "tireur":
				Tireur t1 = new Tireur(j.getEquipe(),new Coordonnees((j.getEquipe()-1)*9, (j.getEquipe()-1)*9), plat);
				j.addRobot(t1);
				isOut = false;
				break;
				default:
					JOptionPane.showMessageDialog(null, "ERREUR ENTREE INVALIDE");
			}
			}while(isOut);
		}
		j.updateArrays();
	}
}
