package ordinateur;

import java.util.ArrayList;

public class Arete {
	
	private Sommet entrant;
	private Sommet sortant;
	private final int POIDS = 1;
	
	public Arete(Sommet entrant,Sommet sortant){
		this.entrant = entrant;
		this.sortant = sortant;
	}
	
	public Sommet getEntrant() {
		return entrant;
	}
	public void setEntrant(Sommet entrant) {
		this.entrant = entrant;
	}
	public Sommet getSortant() {
		return sortant;
	}
	public void setSortant(Sommet sortant) {
		this.sortant = sortant;
	}
	public int getPoids() {
		return POIDS;
	}
	
	public int getChemin(Sommet debut){
		int cpt=1;
		Sommet prochainSommet = this.getEntrant();
		Arete prochaineArete = prochainSommet.getRecord();
		while(!prochainSommet.equals(debut)){
			prochainSommet = prochaineArete.getEntrant();
			prochaineArete = prochainSommet.getRecord();
			cpt++;
		}
		return cpt;
	}

}