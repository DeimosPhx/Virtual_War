package Terrain;

import java.util.ArrayList;
import unite.Char;
import unite.Piegeur;
import unite.Robot;
import unite.Tireur;

public class Base extends Parcelle{
	private int equipe;
	private ArrayList<Robot> listeRobot = new ArrayList<Robot>();
	private ArrayList<Piegeur> listePiegeur = new ArrayList<Piegeur>();
	private ArrayList<Tireur> listeTireur = new ArrayList<Tireur>();
	private ArrayList<Char> listeChar = new ArrayList<Char>();

	public Base(Coordonnees cord){
		super(cord);
		equipe = 0;
	}
	public Base(Coordonnees cord,int equipe){
		this(cord);
		this.equipe = equipe;
	}
	public void ajouter(Robot r){
		listeRobot.add(r);
		if(r instanceof Piegeur){
			listePiegeur.add((Piegeur)r);
		}
		if(r instanceof Tireur){
			listeTireur.add((Tireur)r);
		}
		if(r instanceof Char){
			listeChar.add((Char)r);
		}
	}
	public void initaliserLesListes(ArrayList<Robot> liste){
		for(Robot r : liste){
			ajouter(r);
		}
	}
	public void supprimerRobot(Robot r){
		if(r != null){
			listeRobot.remove(r);
			if(r instanceof Piegeur){
				listePiegeur.remove((Piegeur)r);
			}
			if(r instanceof Tireur){
				listeTireur.remove((Tireur)r);
			}
			if(r instanceof Char){
				listeChar.remove((Char)r);
			}
		}
	}
	public void supprimerLesRobotsDontLesCoordonneesCorrespondentPasAlaBase(){
		Robot RobotASupprimer = null; 
		for(Robot r : listeRobot){
			if(!(r.getAbscisse() == this.getCord().getAbscisse()
					&& r.getOrdonnee() == this.getCord().getOrdonnee()) ){
				RobotASupprimer = r;
			}
		}
		supprimerRobot(RobotASupprimer);
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
	public void soigneTousLesRobots(){
		for(Piegeur p : listePiegeur){
			p.recuperationEnergie();
		}

		for(Tireur t : listeTireur){
			t.recuperationEnergie();
		}
		for(Char c : listeChar){
			c.recuperationEnergie();
		}
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
