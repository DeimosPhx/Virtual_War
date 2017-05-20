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
		
//Initialisation du graphe		
		
		ArrayList<Sommet> listeSommetGraphe   = creationGraphe(plateau,typeDuRobot);
		ArrayList<Sommet> listeSommetAtteint  = new ArrayList<Sommet>();
		ArrayList<Arete>  listeAreteAExplorer = new ArrayList<Arete>();
		
		Sommet depart = listeSommetGraphe.get(robot.getCord().getOrdonnee()+robot.getCord().getAbscisse()*plateau.getX());
		depart.setAreteExceptionnelle(true);
		Sommet arrive = listeSommetGraphe.get(destination.getOrdonnee()+destination.getAbscisse()*plateau.getX());

		for(Arete a : depart.getListeVoisins()){
			listeAreteAExplorer.add(a);
		}
		Arete aExplorer = Sommet.choisirProchaineAreteAExplorer(listeAreteAExplorer);
		listeSommetAtteint.add(depart);
		depart.comparerRecord(new Arete(), depart);
		Sommet cherche = new Sommet();
		
//On commence à explorer dans tout les sens!
		int i=0;
		boolean cheminFini=false;
		while(!cheminFini){
			Sommet.explorer(aExplorer, listeAreteAExplorer, listeSommetAtteint);
			listeSommetAtteint.get(listeSommetAtteint.size()-1).comparerRecord(aExplorer, depart);
			depart = aExplorer.getSortant();
			aExplorer = Sommet.choisirProchaineAreteAExplorer(listeAreteAExplorer);
			i++;
			
			for(Sommet s : listeSommetAtteint){
				if(arrive.equals(s)){cheminFini = true;}
			}
		}
		
//Bon la on a chopé le sommet qui correspond à la fin du parcours, maintenant il faut remonter jusqu'à la source!
		
		/*for(Sommet s : listeSommetGraphe){
			if(listeSommetAtteint.get(listeSommetAtteint.size()-1).equals(s)){cherche = s.remonteChemin(depart);}
		}*/
		cherche = listeSommetAtteint.get(listeSommetAtteint.size()-1).remonteChemin(listeSommetAtteint.get(0));
		
		Coordonnees c = new Coordonnees(cherche.getCoordonnees().getAbscisse()-listeSommetAtteint.get(0).getCoordonnees().getAbscisse(),cherche.getCoordonnees().getOrdonnee()-listeSommetAtteint.get(0).getCoordonnees().getOrdonnee());
		return c.coordonneeToDirection();
	}
	
	
	public static ArrayList<Sommet> creationGraphe(Plateau plateau,int typeDuRobot){
		ArrayList<Sommet> listeSommetGraphe = new ArrayList<Sommet>();
		//creation de tous les sommets avant tout
		for(int i=0;i<plateau.getY();i++){
			for(int j=0;j<plateau.getX();j++){
				listeSommetGraphe.add(new Sommet(new Coordonnees(i,j),new ArrayList<Arete>()));
			}
		}
		if(typeDuRobot==1){
			for(Sommet s : listeSommetGraphe){
				s.remplirVoisinsChar(plateau, s.getCoordonnees(), listeSommetGraphe);
			}
		}else{
			for(Sommet s : listeSommetGraphe){
				s.remplirVoisinsTireurPiegeur(plateau, s.getCoordonnees(), listeSommetGraphe);
			}
		}
		return listeSommetGraphe;
	}
}