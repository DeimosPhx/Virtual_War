package Terrain;

public class Parcelle {
	private Coordonnees cord;
	boolean estVide;
	
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
	public String toString(){
		return " ";
	}
}
