package Terrain;

public class Parcelle {
	private Coordonnees cord;
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
		return " ";
	}
}