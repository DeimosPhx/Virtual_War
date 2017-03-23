package Terrain;

public class Base extends Parcelle{
	private int equipe;
	public Base(Coordonnees cord){
		super(cord);
		equipe = 0;
	}
	public Base(Coordonnees cord,int equipe){
		this(cord);
		this.equipe = equipe;
	}
	public String toString(){
		if(equipe == 1){
			return "B";
		}
		else if(equipe == 2){
			return "b";
		}
		else{
			return "ERROR";
		}
	}
}
