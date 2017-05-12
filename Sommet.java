package ordinateur;

import java.util.ArrayList;

import terrain.Coordonnees;
import terrain.Direction;
import terrain.Plateau;

public class Sommet {

	private int numero;
	private ArrayList<Arete> listeVoisins;
	private Arete areteRecord;
	private static int cpt=0;
	
	public Sommet(ArrayList<Arete> listeVoisins){
		this.numero = cpt;
		this.listeVoisins = listeVoisins;
		cpt++;
	}
	public Sommet(ArrayList<Arete> listeVoisins,Arete areteRecord){
		this(listeVoisins);
		this.areteRecord = areteRecord;
	}

	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
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
	public void addAreteListeVoisins(Arete arete) {
		listeVoisins.add(arete);
	}
	public ArrayList<Arete> remplirVoisinsTireurPiegeur(Plateau plateau,Coordonnees coordonnees,ArrayList<Sommet> listeSommet){
		

		if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.HAUT.getAbscisse(),coordonnees.getOrdonnee()+Direction.HAUT.getOrdonnee()))){
			listeVoisins.add(new Arete(this,listeSommet.get(this.getNumero()-plateau.getX())));
		}
		if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.BAS.getAbscisse(),coordonnees.getOrdonnee()+Direction.BAS.getOrdonnee()))){
			listeVoisins.add(new Arete(this,listeSommet.get(this.getNumero()+plateau.getX())));
		}
		if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.GAUCHE.getAbscisse(),coordonnees.getOrdonnee()+Direction.GAUCHE.getOrdonnee()))){
			listeVoisins.add(new Arete(this,listeSommet.get(this.getNumero()-1)));
		}
		if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.DROITE.getAbscisse(),coordonnees.getOrdonnee()+Direction.DROITE.getOrdonnee()))){
			listeVoisins.add(new Arete(this,listeSommet.get(this.getNumero()+1)));
		}
		if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.HAUT_DROITE.getAbscisse(),coordonnees.getOrdonnee()+Direction.HAUT_DROITE.getOrdonnee()))){
			listeVoisins.add(new Arete(this,listeSommet.get(this.getNumero()-plateau.getX()+1)));
		}
		if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.HAUT_GAUCHE.getAbscisse(),coordonnees.getOrdonnee()+Direction.HAUT_GAUCHE.getOrdonnee()))){
			listeVoisins.add(new Arete(this,listeSommet.get(this.getNumero()-plateau.getX()-1)));
		}
		if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.BAS_DROITE.getAbscisse(),coordonnees.getOrdonnee()+Direction.BAS_DROITE.getOrdonnee()))){
			listeVoisins.add(new Arete(this,listeSommet.get(this.getNumero()+plateau.getX()+1)));
		}
		if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.BAS_GAUCHE.getAbscisse(),coordonnees.getOrdonnee()+Direction.BAS_GAUCHE.getOrdonnee()))){
			listeVoisins.add(new Arete(this,listeSommet.get(this.getNumero()+plateau.getX()-1)));
		}
		return listeVoisins;
	}
	
	public ArrayList<Arete> remplirVoisinsChar(Plateau plateau,Coordonnees coordonnees,ArrayList<Sommet> listeSommet){
		
		if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.HAUT.getAbscisse(),coordonnees.getOrdonnee()+Direction.HAUT.getOrdonnee()))){
			if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.HAUT.getAbscisse()*2,coordonnees.getOrdonnee()+Direction.HAUT.getOrdonnee()*2))){
				listeVoisins.add(new Arete(this,listeSommet.get(this.getNumero()-plateau.getX()*2)));
			}else{
				listeVoisins.add(new Arete(this,listeSommet.get(this.getNumero()-plateau.getX())));
			}
		}
		if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.BAS.getAbscisse(),coordonnees.getOrdonnee()+Direction.BAS.getOrdonnee()))){
			if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.BAS.getAbscisse()*2,coordonnees.getOrdonnee()+Direction.BAS.getOrdonnee()*2))){
				listeVoisins.add(new Arete(this,listeSommet.get(this.getNumero()+plateau.getX()*2)));
			}else{
				listeVoisins.add(new Arete(this,listeSommet.get(this.getNumero()+plateau.getX())));
			}
		}
		if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.GAUCHE.getAbscisse(),coordonnees.getOrdonnee()+Direction.GAUCHE.getOrdonnee()))){
			if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.GAUCHE.getAbscisse()*2,coordonnees.getOrdonnee()+Direction.GAUCHE.getOrdonnee()*2))){
				listeVoisins.add(new Arete(this,listeSommet.get(this.getNumero()-2)));
			}else{
				listeVoisins.add(new Arete(this,listeSommet.get(this.getNumero()-1)));
			}
		}
		if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.DROITE.getAbscisse(),coordonnees.getOrdonnee()+Direction.DROITE.getOrdonnee()))){
			if(plateau.autoriser(new Coordonnees(coordonnees.getAbscisse()+Direction.DROITE.getAbscisse()*2,coordonnees.getOrdonnee()+Direction.DROITE.getOrdonnee()*2))){
				listeVoisins.add(new Arete(this,listeSommet.get(this.getNumero()+2)));
			}else{
				listeVoisins.add(new Arete(this,listeSommet.get(this.getNumero()+1)));
			}
		}
		return listeVoisins;
	}

	public void explorer(Arete explore,ArrayList<Arete>listeAreteAExplorer){
		//exploration de l'arete choisie
		for(Arete arete : this.listeVoisins){
			listeAreteAExplorer.add(getNumero(), arete);
		}
		listeAreteAExplorer.remove(explore);
		
	}
	public Sommet choisirProchainSommetAExplorer(Arete arete){
		return arete.getSortant();
	}
	public Arete choisirProchaineAreteAExplorer(ArrayList<Arete> listeAreteAExplorer){
		Arete tmp = listeAreteAExplorer.get(0);
		listeAreteAExplorer.remove(listeAreteAExplorer.get(0));
		return tmp;
	}
	
	public int getRecordChemin(Sommet depart){
		boolean cheminTermine = false;
		int cpt=0;
		while(!cheminTermine){
			cpt++;
			Sommet suivant = this.getRecord().getEntrant();
			Arete suivante = suivant.getRecord();
			
			if(suivante.getEntrant().equals(depart)){cheminTermine=true;}
		}
		return cpt;
	}
	
	public int getRecordChemin(Sommet depart,Arete aTester){
		boolean cheminTermine = false;
		int cpt=0;
		while(!cheminTermine){
			cpt++;
			Sommet suivant = aTester.getEntrant();
			Arete suivante = suivant.getRecord();
			
			if(suivante.getEntrant().equals(depart)){cheminTermine=true;}
		}
		return cpt;
	}

	public void comparerRecord(Arete arete,Sommet depart){
		if(this.getRecordChemin(depart,arete)>this.getRecordChemin(depart)){
			this.setRecord(arete);
		}else if(this.getRecord().equals(null)){
			this.setRecord(arete);
		}
	}

	public Sommet remonteChemin(Sommet depart){
		boolean cheminTermine = false;
		Sommet justeAvant = new Sommet(new ArrayList<Arete>());
		while(!cheminTermine){
			justeAvant = this.getRecord().getEntrant();
			Sommet suivant = justeAvant.getRecord().getEntrant();
			Arete suivante = suivant.getRecord();
			
			if(suivante.getEntrant().equals(depart)){cheminTermine=true;}
		}
		return justeAvant;
	}
	
	public Coordonnees sommetToCoordonnees(){
		return new Coordonnees((this.getNumero()-this.getNumero()%10)/10,this.getNumero()%10);
	}


	



}