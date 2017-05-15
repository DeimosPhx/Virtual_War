package joueur;
import java.util.ArrayList;
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
	public void setList(ArrayList<Robot> lst){
		this.listeRobot = lst;
	}
	public Robot getRobot(int i){
		return listeRobot.get(i);
	}
	public ArrayList<Robot> getListeRobot(){
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
		listeRobot.add(robot);
	}
	public void addRobot(Robot robot1, Robot robot2){
		listeRobot.add(robot1);
		listeRobot.add(robot2);
	}
	public void addRobot(Robot robot1, Robot robot2, Robot robot3){
		addRobot(robot1, robot2);
		listeRobot.add(robot3);
	}
	public void addRobot(Robot robot1, Robot robot2, Robot robot3, Robot robot4){
		addRobot(robot1,robot2,robot3);
		listeRobot.add(robot4);
	}
	public void addRobot(Robot robot1, Robot robot2, Robot robot3, Robot robot4, Robot robot5){
		addRobot(robot1,robot2,robot3,robot4);
		listeRobot.add(robot5);
	}
	public void addRobot(Robot robot1, Robot robot2, Robot robot3, Robot robot4, Robot robot5, Robot robot6){
		addRobot(robot1,robot2,robot3,robot4,robot5);
		listeRobot.add(robot6);
	}
}