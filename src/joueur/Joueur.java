package joueur;
import java.util.ArrayList;
import Terrain.Base;
import unite.Char;
import unite.Piegeur;
import unite.Robot;
import unite.Tireur;

public class Joueur {

	int equipe;
	private ArrayList<Robot> listeRobot = new ArrayList<Robot>();
	private ArrayList<Piegeur> listePiegeur = new ArrayList<Piegeur>();
	private ArrayList<Tireur> listeTireur = new ArrayList<Tireur>();
	private ArrayList<Char> listeChar = new ArrayList<Char>();
	
	private Base base;	
	private Vue vue;
	public ArrayList<Robot> getListeRobot(){
		return listeRobot;
	}
	public ArrayList<Piegeur> getListePiegeur() {
		return listePiegeur;
	}
	public ArrayList<Tireur> getListeTireur() {
		return listeTireur;
	}
	public ArrayList<Char> getListeChar() {
		return listeChar;
	}
	public Base getBase() {
		return base;
	}
	public Vue getVue() {
		return vue;
	}
	public Joueur(int equipe, Base base, Vue vue){
		this.base 	= 	base;
		this.equipe =	equipe;
		this.vue 	= 	vue;
	}
	public void supprimerRobot(Robot r){
		if (r instanceof Piegeur){
			listePiegeur.remove(r);
		}
		else if(r instanceof Tireur){
			listeTireur.remove(r);
		}
		else if(r instanceof Char){
			listeChar.remove(r);
		}
		listeRobot.remove(r);
	}
	public void updateArrays(){
		listePiegeur.clear();
		listeChar.clear();
		listeTireur.clear();
		for(Robot r : listeRobot){
			if (r instanceof Piegeur){
				listePiegeur.add((Piegeur) r);
			}
			else if(r instanceof Tireur){
				listeTireur.add((Tireur) r);
			}
			else if(r instanceof Char){
				listeChar.add((Char) r);
			}
		}
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
	public int getEquipe() {
		return equipe;
	}
	public Robot PeutEtreYaDesMorts(){
		for(Piegeur p : listePiegeur){
			if(p.getEnergie()<=0){
				return p;
			}
		}
		for(Tireur t : listeTireur){
			if(t.getEnergie()<=0){
				supprimerRobot(t);
				return t;
			}
		}
		for(Char c : listeChar){
			if(c.getEnergie()<=0){
				supprimerRobot(c);
				return c;
			}
		}
		return null;
	}

	public String toString(){
		String piegeur = "\nPiegeur :";
		String tireur = "\nTireur :";
		String tank = "\nTank :";
		for(Piegeur p : listePiegeur){
			piegeur += "\n[ n°" + listePiegeur.indexOf(p) + " | Coordonnees : " + p.getCord() + " | Energie : " + p.getEnergie() +" ]";
		}
		for(Tireur p : listeTireur){
			tireur += "\n[ n°" + listeTireur.indexOf(p) + " | Coordonnees : " + p.getCord() + " | Energie : " + p.getEnergie() +" ]";
		}
		for(Char p : listeChar){
			tank += "\n[ n°" + listeChar.indexOf(p) + " | Coordonnees : " + p.getCord() + " | Energie : " + p.getEnergie() +" ]";
		}
		return "Joueur " + equipe + " :" + piegeur + tireur + tank;
	}
}

