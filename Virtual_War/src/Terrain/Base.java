package Terrain;
import unite.*;
import java.util.ArrayList;

public class Base extends Parcelle{
	private ArrayList<Robot> lst = new ArrayList<Robot>();
	
	public Base(Coordonnees cord){
		super(cord);
		super.equipe = 0;
	}
	public boolean estDans(Robot rob){
		boolean in = false;
		for(Robot r : lst){
			in = r.equals(rob);
		}
		return in;
	}
	public void setList(ArrayList<Robot> list){
		this.lst = list;
	}
	public void addRobot(Robot rob){
		lst.add(rob);
		if(estDans(rob)){
			System.out.println("flag");
		}
	}
	public void removeRobot(Robot rob){
		lst.remove(rob);
	}
	public Base(Coordonnees cord,int equipe){
		this(cord);
		super.equipe = equipe;
	}
	public Robot getRobot(int index){
		return lst.get(index);
	}
	public int getTailleListe(){
		return lst.size();
	}
	/*public String toString(){
		if(this.equipe == 1){
			return "B";
		}
		else if(this.equipe == 2){
			return "b";
		}
		else{
			return "ERROR";
		}
	}*/
}
