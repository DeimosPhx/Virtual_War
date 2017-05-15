package ordinateur;

import java.util.ArrayList;
import unite.*;

import terrain.*;

public class Dijkstra {
	
	public static Direction deroulement(Plateau plateau,Robot robot,Coordonnees destination){
		int typeDuRobot = -1;
		if(robot instanceof Char){
		 typeDuRobot = 1;
		}
		else if (robot instanceof Piegeur){
			typeDuRobot = 2;
		}
		else if (robot instanceof Tireur){
			typeDuRobot = 3;
		}
		Coordonnees emplacementRobot = robot.getCord();
		ArrayList<Sommet> listeSommetGraphe = creationGraphe(plateau,typeDuRobot);
		if(typeDuRobot == 1){
			for(Sommet s : listeSommetGraphe){
				s.remplirVoisinsChar(plateau, s.getCoordonnees(), listeSommetGraphe);
			}
		}
		else{
			for(Sommet s : listeSommetGraphe){
				s.remplirVoisinsTireurPiegeur(plateau, s.getCoordonnees(), listeSommetGraphe);
			}
		}
		
		ArrayList<Sommet> listeSommetAtteint = new ArrayList<Sommet>();
		ArrayList<Arete> listeAreteAExplorer = new ArrayList<Arete>();
		Sommet arrive = new Sommet(new ArrayList<Arete>());
		
		arrive.setCoordonnees(new Coordonnees(destination.getAbscisse(),destination.getOrdonnee()));
		Sommet depart = listeSommetGraphe.get(emplacementRobot.getAbscisse()*plateau.getX()+emplacementRobot.getOrdonnee());
		listeSommetAtteint.add(depart);
		Sommet origine = depart;
		//Arete areteDepart = depart.choisirProchaineAreteAExplorer(depart.getListeVoisins());
		
		//Sommet sommetAExplorer = depart.choisirProchainSommetAExplorer(areteDepart);
		for(Arete arete : depart.getListeVoisins()){
			listeAreteAExplorer.add(arete);
		}
		boolean cheminTermine=false;
		depart.explorer(depart.choisirProchaineAreteAExplorer(listeAreteAExplorer), listeAreteAExplorer);
		Sommet sommetCherche = new Sommet(new ArrayList<Arete>());
		int i=0;
		while(!cheminTermine){
			Arete areteSuivante = depart.choisirProchaineAreteAExplorer(listeAreteAExplorer);
			depart = areteSuivante.getSortant();
			depart.explorer(areteSuivante, listeAreteAExplorer);
			listeSommetAtteint.add(depart);
			depart.comparerRecord(areteSuivante,origine);
			
			if(listeSommetAtteint.get(i+1).getIndex(plateau) == arrive.getIndex(plateau)){
				cheminTermine=true;
				sommetCherche = listeSommetAtteint.get(i).remonteChemin(origine);
			}
			i++;
			
		}
		//fin du parcours
		Coordonnees coordDuSommetCherche = sommetCherche.getCoordonnees();
		return coordDuSommetCherche.coordonneeToDirection();
	}
	
	
	public static ArrayList<Sommet> creationGraphe(Plateau plateau,int typeDuRobot){
		ArrayList<Sommet> listeSommetGraphe = new ArrayList<Sommet>();
		//creation de tous les sommets avant tout
		for(int i=0;i<plateau.getY();i++){
			for(int j=0;j<plateau.getX();j++){
				listeSommetGraphe.add(new Sommet(new Coordonnees(i,j),new ArrayList<Arete>()));
			}
		}
		return listeSommetGraphe;
	}









}