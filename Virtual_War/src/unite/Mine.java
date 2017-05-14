package unite;
import Terrain.Coordonnees;
import Terrain.Parcelle;
public class Mine extends Parcelle{
	
	public Mine(int equipe, Coordonnees coordonnees){
		super(coordonnees);
		this.equipe = equipe;
	}
	public String toString(){
		if (this.equipe == 1){return "M";}
		return "m";
	}
}