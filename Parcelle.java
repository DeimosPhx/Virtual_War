package Terrain;

public class Parcelle {
	private Coordonnees cord;
	
	public Parcelle(Coordonnees cord){
		this.cord = cord;
	}
	public boolean estVide(){
		return true;
	}
	public Coordonnees getCord(){
		return this.cord;
	}
	public String toString(){
		return " ";
	}
}
