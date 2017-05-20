package ordinateur;

import java.util.ArrayList;

import terrain.Coordonnees;
import terrain.Direction;
import terrain.Plateau;

public class Sommet {

	private Coordonnees coordonnees;
	private ArrayList<Arete> listeVoisins;
	private Arete areteRecord;
	private boolean possedeDejaUnRecord = false;
	private boolean estUneAreteExceptionnelle;
	private int poidsDuChemin;
	
	public Sommet(){}
	public Sommet(ArrayList<Arete> listeVoisins){
		this.listeVoisins = listeVoisins;
	}
	public Sommet(Coordonnees coordonnees,ArrayList<Arete> listeVoisins){
		this.coordonnees = coordonnees;
		this.listeVoisins = listeVoisins;
	}
	public Sommet(Coordonnees coordonnees,ArrayList<Arete> listeVoisins,Arete areteRecord){
		this(coordonnees,listeVoisins);
		this.areteRecord = areteRecord;
	}
	public Arete getRecord(){
		return this.areteRecord;
	}
	public void setRecord(Arete areteRecord){
		this.areteRecord = areteRecord;
	}
	public ArrayList<Arete> getListeVoisins() {
		return listeVoisins;
	}	
	public Coordonnees getCoordonnees() {
		return coordonnees;
	}
	public void setCoordonnees(Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
	}
	public void setPossedeDejaUnRecord(boolean record){
		this.possedeDejaUnRecord = record;
	}
	public void setAreteExceptionnelle(boolean bool){
		this.estUneAreteExceptionnelle = bool;
	}
	
	public int getPoidsDuChemin() {
		return poidsDuChemin;
	}
	public void setPoidsDuChemin(int poidsDuChemin) {
		this.poidsDuChemin = poidsDuChemin;
	}
	public ArrayList<Arete> remplirVoisinsTireurPiegeur(Plateau plateau,Coordonnees coordonnees,ArrayList<Sommet> listeSommet){
		

		if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.HAUT.getAbscisse(),coordonnees.getOrdonnee()+Direction.HAUT.getOrdonnee()))){
			this.listeVoisins.add(new Arete(this,listeSommet.get(this.getIndex(plateau)-plateau.getY())));
		}
		if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.BAS.getAbscisse(),coordonnees.getOrdonnee()+Direction.BAS.getOrdonnee()))){
			this.listeVoisins.add(new Arete(this,listeSommet.get(this.getIndex(plateau)+plateau.getY())));
		}
		if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.GAUCHE.getAbscisse(),coordonnees.getOrdonnee()+Direction.GAUCHE.getOrdonnee()))){
			listeVoisins.add(new Arete(this,listeSommet.get(this.getIndex(plateau)-1)));
		}
		if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.DROITE.getAbscisse(),coordonnees.getOrdonnee()+Direction.DROITE.getOrdonnee()))){
			listeVoisins.add(new Arete(this,listeSommet.get(this.getIndex(plateau)+1)));
		}
		if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.HAUT_DROITE.getAbscisse(),coordonnees.getOrdonnee()+Direction.HAUT_DROITE.getOrdonnee()))){
			listeVoisins.add(new Arete(this,listeSommet.get(this.getIndex(plateau)-plateau.getY()+1)));
		}
		if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.HAUT_GAUCHE.getAbscisse(),coordonnees.getOrdonnee()+Direction.HAUT_GAUCHE.getOrdonnee()))){
			listeVoisins.add(new Arete(this,listeSommet.get(this.getIndex(plateau)-plateau.getY()-1)));
		}
		if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.BAS_DROITE.getAbscisse(),coordonnees.getOrdonnee()+Direction.BAS_DROITE.getOrdonnee()))){
			listeVoisins.add(new Arete(this,listeSommet.get(this.getIndex(plateau)+plateau.getY()+1)));
		}
		if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.BAS_GAUCHE.getAbscisse(),coordonnees.getOrdonnee()+Direction.BAS_GAUCHE.getOrdonnee()))){
			listeVoisins.add(new Arete(this,listeSommet.get(this.getIndex(plateau)+plateau.getY()-1)));
		}
		return listeVoisins;
	}
	
	public ArrayList<Arete> remplirVoisinsChar(Plateau plateau,Coordonnees coordonnees,ArrayList<Sommet> listeSommet){
		
		if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.HAUT.getAbscisse(),coordonnees.getOrdonnee()+Direction.HAUT.getOrdonnee()))){
			if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.HAUT.getAbscisse()*2,coordonnees.getOrdonnee()+Direction.HAUT.getOrdonnee()*2))){
				listeVoisins.add(new Arete(this,listeSommet.get(this.getIndex(plateau)-plateau.getY()*2)));
			}else{
				listeVoisins.add(new Arete(this,listeSommet.get(this.getIndex(plateau)-plateau.getY())));
			}
		}
		if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.BAS.getAbscisse(),coordonnees.getOrdonnee()+Direction.BAS.getOrdonnee()))){
			if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.BAS.getAbscisse()*2,coordonnees.getOrdonnee()+Direction.BAS.getOrdonnee()*2))){
				listeVoisins.add(new Arete(this,listeSommet.get(this.getIndex(plateau)+plateau.getY()*2)));
			}else{
				listeVoisins.add(new Arete(this,listeSommet.get(this.getIndex(plateau)+plateau.getY())));
			}
		}
		if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.GAUCHE.getAbscisse(),coordonnees.getOrdonnee()+Direction.GAUCHE.getOrdonnee()))){
			if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.GAUCHE.getAbscisse()*2,coordonnees.getOrdonnee()+Direction.GAUCHE.getOrdonnee()*2))){
				listeVoisins.add(new Arete(this,listeSommet.get(this.getIndex(plateau)-2)));
			}else{
				listeVoisins.add(new Arete(this,listeSommet.get(this.getIndex(plateau)-1)));
			}
		}
		if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.DROITE.getAbscisse(),coordonnees.getOrdonnee()+Direction.DROITE.getOrdonnee()))){
			if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.DROITE.getAbscisse()*2,coordonnees.getOrdonnee()+Direction.DROITE.getOrdonnee()*2))){
				listeVoisins.add(new Arete(this,listeSommet.get(this.getIndex(plateau)+2)));
			}else{
				listeVoisins.add(new Arete(this,listeSommet.get(this.getIndex(plateau)+1)));
			}
		}
		return listeVoisins;
	}

	public static void explorer(Arete explore,ArrayList<Arete>listeAreteAExplorer,ArrayList<Sommet> listeSommetAtteint){
		//exploration de l'arete choisie
		Sommet aExplorer = explore.getSortant();
		for(Arete arete : aExplorer.listeVoisins){
			listeAreteAExplorer.add(arete);
		}
		listeAreteAExplorer.remove(explore);
		if(!listeSommetAtteint.contains(aExplorer)){
			listeSommetAtteint.add(aExplorer);
		}
		
	}

	public static Arete choisirProchaineAreteAExplorer(ArrayList<Arete> listeAreteAExplorer){
		return listeAreteAExplorer.get(0);
	}

	
	public int getRecordChemin(Sommet origine,Arete aTester){
		if(aTester.getEntrant().equals(origine)){return 1;}
		boolean cheminTermine = false;
		Sommet s = aTester.getEntrant();
		int cpt=1;
		while(!cheminTermine){
			if(s.getAntecedent().equals(origine)){return cpt;}
			s = s.getAntecedent();
			if(s.getAntecedent().equals(origine)){return cpt;}
			cpt += s.getRecord().getPoids();
			if(s.getRecord().getPoids()==0){return cpt;}
			if(s.getRecord().getEntrant().equals(aTester.getEntrant())){return this.getPoidsDuChemin()+1;}
			if(s.getRecord().getSortant().getRecord().equals(s.getRecord().getEntrant())){return this.getPoidsDuChemin()+1;}
			
		}
		return cpt;
	}

	public Sommet remonter(Arete aTester) {
		return aTester.getEntrant();
	}
	public int comparerRecord(Arete arete,Sommet origine){
		
		if(this.estUneAreteExceptionnelle){
			this.setRecord(new Arete(this,this,0));
			this.setPossedeDejaUnRecord(true);
			return 42;
		}
		
		if(!this.possedeDejaUnRecord && !this.estUneAreteExceptionnelle){
			this.setRecord(arete);
			this.setPossedeDejaUnRecord(true);
			this.setPoidsDuChemin(this.getRecordChemin(origine, arete));
			return 0;
			
		}else if(this.getRecordChemin(origine,arete)>this.getRecordChemin(origine,this.getRecord()) && !this.estUneAreteExceptionnelle){
			this.setRecord(arete);
			this.setPoidsDuChemin(this.getRecordChemin(origine, arete));
			return 1;
		}
		
		return -1;

	}


	public Sommet remonteChemin(Sommet origine){

		Sommet sommet = this;
		Arete arete = new Arete();
		boolean finDuChemin = false;
		
		while(!finDuChemin){
			arete = sommet.getRecord();
			sommet = arete.getEntrant();
			
			if(arete.getEntrant().equals(origine)){
				finDuChemin = true;
				return arete.getSortant();
			}
		}
		return null;
	
	}
	
	public int getIndex(Plateau plateau){
		return this.getCoordonnees().getOrdonnee()+this.getCoordonnees().getAbscisse()*plateau.getX();
	}
	public void setListeVoisins(ArrayList<Arete> listeVoisins) {
		this.listeVoisins = listeVoisins;
	}
	public Sommet getAntecedent(){
		return this.getRecord().getEntrant();
	}


	



}