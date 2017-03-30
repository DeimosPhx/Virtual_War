package unite;

import Terrain.Coordonnees;
import Terrain.Direction;
import Terrain.Plateau;

public class Char extends Robot{

	public Char(int equipe, Coordonnees coord, Plateau plateau) {
		super(coord);
		this.coordonnees = coord;
		super.energie = 60;
		super.equipe = equipe;
		super.plateau = plateau;
	}
	
	private Robot getRobotFromPlateau(Direction direction){
		return (Robot) plateau.getContenu(this.coordonnees.cibler(direction.getCoordonnees()));
	}
	
	public int getEnergie() {		return super.energie;}
	public int getDegat() {			return 6;}
	public int getPortee() {		return 10;}
	public int getDeplacement() {	return 2;}
	public int getCout() {			return 1;}
	public int getCoutAvancer() { 	return 5;}
	public int getEnergieRecupEnBase() {return 2;}
	
	public void tirer(Direction direction) {
		getRobotFromPlateau(direction).subitDegatsEtMeurtPotentiellement(this.getDegat());
	}

	public boolean peutTirer(Direction direction) { //KISS KISS ON TE DIT
		for(int facteur = 1; facteur <= getPortee(); facteur++){
			if(plateau.estDans(this.coordonnees.cibler(direction.getCoordonnees().multiplier(facteur)))){
				if(plateau.getContenu(this.coordonnees.cibler(direction.getCoordonnees().multiplier(facteur))) instanceof Robot){
					if(getRobotFromPlateau(direction).getEquipe() != this.equipe){
						return true;
					}
				}
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
	public String toString(){
		if(getEquipe() == 1){
			return "C";
		}
		return "c";
	}
}
