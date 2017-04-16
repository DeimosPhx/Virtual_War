package Terrain;



import joueur.Menu;
import joueur.Vue;
import unite.Piegeur;
import unite.Tireur;

public class EssaiTerrain {
	public static void main(String[] args) {
		new Menu();
		Plateau terrain = new Plateau(10,10);
		Vue vue = new Vue(1, terrain);
		Vue vue2 = new Vue(2, terrain);
		terrain.setBase(new Base(new Coordonnees(0,0),1));
		terrain.setBase(new Base(new Coordonnees(9,9),1));
		terrain.setObstacle(new Coordonnees(5,5));
		//terrain.setRobot(new Tireur(1, new Coordonnees(5,7), terrain));
		Piegeur piegeur1 = new Piegeur(1, new Coordonnees(1, 1), terrain);
		terrain.setRobot(piegeur1);
		piegeur1.tirer(Direction.DROITE);
		Piegeur piegeur2 = new Piegeur(2, new Coordonnees(2, 2), terrain);
		terrain.setRobot(piegeur1);
		piegeur2.tirer(Direction.BAS_GAUCHE);
		Tireur tireur1 = new Tireur(1, new Coordonnees(5, 6), terrain);
		Tireur tireur2 = new Tireur(2, new Coordonnees(5, 8), terrain);
		terrain.setRobot(tireur1);
		terrain.setRobot(tireur2);
		if(tireur1.peutTirer(Direction.DROITE)){
			System.out.println("Bon");
			tireur1.tirer(Direction.DROITE);
		}
		if(tireur1.peutTirer(Direction.GAUCHE)){
			System.out.println("PAS BON !");
		}
		System.out.println(terrain);
		System.out.println(vue);
		System.out.println(vue2);
		System.out.println(tireur2.getEnergie());
	}
	
	
}
