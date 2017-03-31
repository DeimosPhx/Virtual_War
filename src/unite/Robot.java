package unite;

import Terrain.Coordonnees;
import Terrain.Direction;
import Terrain.Parcelle;
import Terrain.Plateau;

public abstract class Robot extends Parcelle {
	
	public Robot(Coordonnees cord) {
		super(cord);
	}
	int energie;
	Coordonnees coordonnees;
	Plateau plateau;
	
	abstract int getEnergie();
	abstract int getDegat();
	abstract int getPortee();
	abstract int getDeplacement();
	abstract int getCout();
	abstract int getCoutAvancer();
	abstract int getEnergieRecupEnBase();
	abstract boolean tirer(Direction direction);
	abstract boolean peutTirer(Direction direction);
	abstract boolean subitDegatsEtMeurtPotentiellement(int degats);
	abstract void recuperationEnergie();
	public int getAbscisse(){
		return coordonnees.getAbscisse();
	}
	public int getOrdonnee(){
		return coordonnees.getOrdonnee();
	}
}
	
