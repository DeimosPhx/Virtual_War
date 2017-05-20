package terrain;



import java.util.ArrayList;

import joueur.Joueur;
import joueur.Menu;
import joueur.Vue;
import unite.Piegeur;
import unite.Robot;
import unite.Tireur;
import ordinateur.Dijkstra;

public class EssaiTerrain {
	public static void main(String[] args) {
		new Menu();
		Plateau terrain = new Plateau(10,10);
		ArrayList<Robot> testList = new ArrayList<Robot>();
		Joueur j2= new Joueur(2);
		j2.setList(testList);
		terrain.setObstacle(new Coordonnees(0,1));
		terrain.setObstacle(new Coordonnees(5,5));
		terrain.setObstacle(new Coordonnees(6,6));
		terrain.setObstacle(new Coordonnees(7,7));
		terrain.setObstacle(new Coordonnees(7,6));
		terrain.setObstacle(new Coordonnees(5,9));
		terrain.setObstacle(new Coordonnees(5,4));
		terrain.setObstacle(new Coordonnees(6,4));
		Piegeur piegeur1 = new Piegeur(2, new Coordonnees(4, 3), terrain);
		terrain.setRobot(piegeur1);
		
		//On veut que le piegeur se deplace jusqu'en (6,7)
		boolean resultat1 = terrain.deplacer(j2, piegeur1,Dijkstra.deroulement(terrain, piegeur1, new Coordonnees(6,7)));
		if(resultat1){
			System.out.println("ok1");
		}
		boolean resultat2 = terrain.deplacer(j2, piegeur1,Dijkstra.deroulement(terrain, piegeur1, new Coordonnees(6,7)));
		if(resultat2){
			System.out.println("ok2");
		}
		boolean resultat3 = terrain.deplacer(j2, piegeur1,Dijkstra.deroulement(terrain, piegeur1, new Coordonnees(6,7)));
		if(resultat3){
			System.out.println("ok3");
		}
		boolean resultat4 = terrain.deplacer(j2, piegeur1,Dijkstra.deroulement(terrain, piegeur1, new Coordonnees(6,7)));
		if(resultat4){
			System.out.println("ok4");
		}
	}
	
	
}