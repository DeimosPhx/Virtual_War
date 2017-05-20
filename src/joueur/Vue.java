package joueur;
import terrain.Parcelle;
import terrain.Coordonnees;
import terrain.Plateau;
import unite.Mine;
public class Vue {

	int equipe;
	Plateau plateau;
	
	public Vue(int equipe, Plateau plateau){
		this.equipe = equipe;
		this.plateau= plateau;
	}
	public String toString(){
		String out = "";
		Parcelle parcelle;
		for(int a =0; a< plateau.getX();a++){
			for(int o=0;o<plateau.getY();o++){
				parcelle = plateau.getContenu(new Coordonnees(a, o));
				if(parcelle instanceof Mine
				&& parcelle.getEquipe() != this.equipe){
					out += "|" + "_" + new Parcelle(new Coordonnees(a, o)) + "_" + "|";
				}
				else{out += "|" + "_" + parcelle + "_" + "|";}
			}
			out += "\n";
		}
		return out;
	}
}
