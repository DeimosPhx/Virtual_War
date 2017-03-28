package Terrain;
import unite.*;
import java.util.*;
public class Base extends Parcelle{
	private int equipe;
	private ArrayList<Robot> list = new ArrayList<Robot>();
	
	public Base(Coordonnees cord){
		super(cord);
		equipe = 0;
	}
	public void add(Robot rob){
		list.add(rob);
	}
	public Robot get(int idx){
		return list.get(idx);
	}
	public void supr(Robot rob){
		list.remove(rob);
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
