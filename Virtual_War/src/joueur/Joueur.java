package joueur;
import java.util.ArrayList;
import java.util.HashMap;

import Terrain.Base;
import unite.Robot;

public class Joueur {

	int equipe;
	private HashMap<Integer, Robot> listeRobot = new HashMap<Integer,Robot>();
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
	public void setList(HashMap<Integer, Robot> compoJ1){
		this.listeRobot = compoJ1;
	}
	public Robot getRobot(int i){
		return listeRobot.get(i);
	}
	public HashMap<Integer,Robot>getListeRobot(){
		return listeRobot;
	}
	public void supprimerRobot(Robot robot){
		listeRobot.remove(robot);
	}
	public boolean Aperdu(){
		return listeRobot.isEmpty();
	}
	public Joueur(int equipe){
		this.equipe = equipe;
	}
	public void addRobot(Robot robot){
		listeRobot.put(listeRobot.size()+1,robot);
	}
}
