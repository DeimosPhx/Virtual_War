package unite;

import Terrain.Base;
import Terrain.Coordonnees;
import Terrain.Direction;
import Terrain.Obstacle;
import Terrain.Plateau;

public class Piegeur extends Robot{
	public Piegeur(int equipe, Coordonnees coord, Plateau plateau) {
		super(coord);
		super.energie = 50;
		super.equipe = equipe;
		super.plateau = plateau;
	}
	
	public int getEnergie() {		return super.energie;}
	public int getDegat() {			return 2;}
	public int getPortee() {		return 1;}
	public int getDeplacement() {	return 1;}
	public int getCout() {			return 2;}
	public int getCoutAvancer() { 	return 2;}
	public int getEnergieRecupEnBase() {return 2;}

	void tirer(Direction direction) {
		this.plateau.setMine(coordonnees.cibler(direction.getCoordonnees()), new Mine(this.equipe));
	}

	public boolean peutTirer(Direction direction) {
		if(plateau.estDans(this.coordonnees.cibler(direction.getCoordonnees()))){
			if(!((plateau.getContenu(this.coordonnees.cibler(direction.getCoordonnees()))) instanceof Robot)
				&& !((plateau.getContenu(this.coordonnees.cibler(direction.getCoordonnees()))) instanceof Base)
				&&!((plateau.getContenu(this.coordonnees.cibler(direction.getCoordonnees()))) instanceof Obstacle)){
				return true;
			}
		}
		return false;
	}
	boolean subitDegatsEtMeurtPotentiellement(int degats) {
		this.energie -= degats;
		if(this.energie <= 0){
			return true;
		}
		return false;
	}
	void recuperationEnergie() {
		this.energie += this.getEnergieRecupEnBase();
	}

}
