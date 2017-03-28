package unite;
import Terrain.Coordonnees;
import Terrain.Parcelle;
public class Mine extends Parcelle{
	int equipe;
	
	public Mine(int equipe, Coordonnees coordonnees){
		super(coordonnees);
		this.equipe = equipe;
	}
}
