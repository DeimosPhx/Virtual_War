package unite;

import terrain.Coordonnees;
import terrain.Direction;
import terrain.Parcelle;
import terrain.Plateau;

public abstract class Robot extends Parcelle {
	private boolean estMort = false;
	public Robot(Coordonnees cord) {
		super(cord);
	}
	protected int energie;
	Plateau plateau;
	public boolean getEstMort(){
		return estMort;
	}
	public void setEstmort(){
		estMort = true;
	}
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
	public abstract Robot getRobotFromPlateau(Direction direction);
}
	
