package ordinateur;

public class Arete {
	
	private Sommet entrant;
	private Sommet sortant;
	private int POIDS = 1;
	
	public Arete(){}
	public Arete(Sommet entrant,Sommet sortant){
		this.entrant = entrant;
		this.sortant = sortant;
	}
	public Arete(Sommet entrant,Sommet sortant,int poids){
		this.entrant = entrant;
		this.sortant = sortant;
		this.POIDS   = poids;
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
	public void setPoids(int poids) {
		this.POIDS = poids;
		
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