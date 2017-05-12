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
				s.remplirVoisinsChar(plateau, s.sommetToCoordonnees(), listeSommetGraphe);
			}
		}
		else{
			for(Sommet s : listeSommetGraphe){
				s.remplirVoisinsTireurPiegeur(plateau, s.sommetToCoordonnees(), listeSommetGraphe);
			}
		}
		
		ArrayList<Sommet> listeSommetAtteint = new ArrayList<Sommet>();
		ArrayList<Arete> listeAreteAExplorer = new ArrayList<Arete>();
		Sommet arrive = new Sommet(new ArrayList<Arete>());
		
		arrive.setNumero(destination.getAbscisse()*plateau.getX()+destination.getOrdonnee());
		Sommet depart = listeSommetGraphe.get(emplacementRobot.getAbscisse()*plateau.getX()+emplacementRobot.getOrdonnee());
		listeSommetAtteint.add(depart);
		Sommet origine = depart;
		//Arete areteDepart = depart.choisirProchaineAreteAExplorer(depart.getListeVoisins());
		
		//Sommet sommetAExplorer = depart.choisirProchainSommetAExplorer(areteDepart);
		
		boolean cheminTermine=false;
		depart.explorer(depart.choisirProchaineAreteAExplorer(listeAreteAExplorer), listeAreteAExplorer);
		Sommet sommetCherche = new Sommet(new ArrayList<Arete>());
		while(!cheminTermine){
			Arete areteSuivante = depart.choisirProchaineAreteAExplorer(listeAreteAExplorer);
			depart = areteSuivante.getSortant();
			depart.explorer(areteSuivante, listeAreteAExplorer);
			depart.comparerRecord(areteSuivante,origine);

			for(int i=0;i<listeSommetAtteint.size();i++){
				if(listeSommetAtteint.get(i).getNumero() == arrive.getNumero()){
					cheminTermine=true;
					sommetCherche = listeSommetAtteint.get(i).remonteChemin(origine);
				}
			}
		}
		//fin du parcours
		Coordonnees coordDuSommetCherche = sommetCherche.sommetToCoordonnees();
		return coordDuSommetCherche.obtenirDirectionAPartirDeCoordonnees();
	}
	
	
	public static ArrayList<Sommet> creationGraphe(Plateau plateau,int typeDuRobot){
		ArrayList<Sommet> listeSommetGraphe = new ArrayList<Sommet>();
		//création de tous les sommets avant tout
		for(int i=0;i<plateau.getX();i++){
			for(int j=0;j<plateau.getY();j++){
				listeSommetGraphe.add(new Sommet(new ArrayList<Arete>()));
			}
		}
		return listeSommetGraphe;
	}









}