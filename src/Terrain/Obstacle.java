package Terrain;

public class Obstacle extends Parcelle{
	public Obstacle(Coordonnees cord){
		super(cord);
	}
	public String toString(){
		return "O";
	}
	public boolean autoriserPlacementObstacle(Plateau plat,Coordonnees c){
		if(!plat.getGrille()[c.getAbscisse()+Direction.HAUT_GAUCHE.getAbscisse()][c.getOrdonnee()+Direction.HAUT_GAUCHE.getOrdonnee()].estVide()){
			return false;
		}
		if(!plat.getGrille()[c.getAbscisse()+Direction.HAUT_DROITE.getAbscisse()][c.getOrdonnee()+Direction.HAUT_DROITE.getOrdonnee()].estVide()){
			return false;
		}
		if(!plat.getGrille()[c.getAbscisse()+Direction.BAS_GAUCHE.getAbscisse()][c.getOrdonnee()+Direction.BAS_GAUCHE.getOrdonnee()].estVide()){
			return false;
		}
		if(!plat.getGrille()[c.getAbscisse()+Direction.BAS_DROITE.getAbscisse()][c.getOrdonnee()+Direction.BAS_DROITE.getOrdonnee()].estVide()){
			return false;
		}
		return true;
	}
}
