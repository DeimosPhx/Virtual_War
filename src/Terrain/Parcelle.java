package Terrain;
import unite.*;

public class Parcelle {
	protected Coordonnees cord;
	private boolean estVide;
	protected int equipe;
	public Parcelle(Coordonnees cord){
		this.cord = cord;
		estVide = true;
	}
	public void vider(){
		this.estVide = true;
	}
	public boolean estVide(){
		return this.estVide;
	}
	public void setPasVide(){
		this.estVide = false;
	}
	public Coordonnees getCord(){
		return this.cord;
	}
	public int getEquipe(){
		return equipe;
	}
	public String toString(){
		String st = " ";
		if(this instanceof Base){
			st = "b";
		}
		else if(this instanceof Char){
			st = "c";
		}
		else if(this instanceof Mine){
			st = "m";
		}
		else if(this instanceof Piegeur){
			st = "p";
		}
		else if(this instanceof Tireur){
			st = "t";
		}
		else if(this instanceof Obstacle){
			st = "O";
		}
		if(equipe == 1 && !st.equals("O")){
			st = st.toUpperCase();
		}
		return st;
	}
	
	public boolean autoriserPlacementObstacle(Plateau plat,Coordonnees c){
		if(c.getAbscisse()!=0 && c.getOrdonnee()!=0){
			if(!plat.getGrille()[c.getAbscisse()+Direction.HAUT_GAUCHE.getAbscisse()][c.getOrdonnee()+Direction.HAUT_GAUCHE.getOrdonnee()].estVide()){
				return false;
			}
		}
		if(c.getAbscisse()!=0 && c.getOrdonnee()!=9){
			if(!plat.getGrille()[c.getAbscisse()+Direction.HAUT_DROITE.getAbscisse()][c.getOrdonnee()+Direction.HAUT_DROITE.getOrdonnee()].estVide()){
				return false;
			}
		}
		if(c.getAbscisse()!=9 && c.getOrdonnee()!=0){
			if(!plat.getGrille()[c.getAbscisse()+Direction.BAS_GAUCHE.getAbscisse()][c.getOrdonnee()+Direction.BAS_GAUCHE.getOrdonnee()].estVide()){
				return false;
			}
		}
		if(c.getAbscisse()!=9 && c.getOrdonnee()!=9){
			if(!plat.getGrille()[c.getAbscisse()+Direction.BAS_DROITE.getAbscisse()][c.getOrdonnee()+Direction.BAS_DROITE.getOrdonnee()].estVide()){
				return false;
			}
		}
		
		return true;
	}
}
