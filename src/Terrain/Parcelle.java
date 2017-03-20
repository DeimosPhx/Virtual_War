package Terrain;

public class Parcelle {
	private Coordonnees cord;
	
	public Parcelle(Coordonnees cord){
		this.cord = cord;
	}
	public Coordonnees getCord(){
		return this.cord;
	}
}
