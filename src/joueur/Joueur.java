package joueur;
import java.util.ArrayList;
import java.util.HashMap;

import terrain.Base;
import unite.Robot;

public class Joueur {

	int equipe;
	private ArrayList<Robot> listeRobot = new ArrayList<Robot>();
	private Base base;	
	private Vue vue;
	
	public Joueur(int equipe, Base base, Vue vue){
		this.base 	= 	base;
		this.equipe =	equipe;
		this.vue 	= 	vue;
	}
	public int getEquipe(){
		return equipe;
	}
	public Base getBase(){
		return this.base;
	}
	public void setList(ArrayList<Robot> compoJ1){
		this.listeRobot = compoJ1;
	}
	public Robot getRobot(int i){
		return listeRobot.get(i);
	}
	public ArrayList<Robot>getListeRobot(){
		return listeRobot;
	}
	public void supprimerRobot(Robot robot){
		
	}
	public boolean Aperdu(){
		//A ECRIRE
		return false;
	}
	public Joueur(int equipe){
		this.equipe = equipe;
	}
	public void addRobot(Robot robot){
		listeRobot.add(robot);
	}
}
