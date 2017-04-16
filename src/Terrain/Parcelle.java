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
}