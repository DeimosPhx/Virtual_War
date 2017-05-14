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
	Plateau plateau;
	
	public void deployer(Coordonnees cord){
		super.cord = cord;
	}
	public abstract int getEnergie();
	public abstract int getDegat();
	public abstract int getPortee();
	public abstract int getDeplacement();
	public abstract int getCout();
	public abstract int getCoutAvancer();
	public abstract int getEnergieRecupEnBase();
	public abstract boolean tirer(Direction direction);
	public abstract boolean peutTirer(Direction direction);
	public abstract boolean subitDegatsEtMeurtPotentiellement(int degats);
	public abstract void recuperationEnergie();
	public abstract String toString();
	public int getAbscisse(){
		return super.cord.getAbscisse();
	}
	public int getOrdonnee(){
		return super.cord.getOrdonnee();
	}
}
	
