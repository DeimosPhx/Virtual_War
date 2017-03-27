package Terrain;

public class EssaiTerrain {
	public static void main(String[] args) {
		Plateau terrain = new Plateau(10,10);
		terrain.setBase(new Coordonnees(0,0),1);
		terrain.setBase(new Coordonnees(9,9), 2);
		terrain.setObstacle(new Coordonnees(5,5));
		System.out.println(terrain);
	}
	
	
}
