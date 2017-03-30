package unite;

import Terrain.Coordonnees;
import Terrain.Direction;
import Terrain.Plateau;

public class Char extends Robot{

	public Char(int equipe, Coordonnees coord, Plateau plateau) {
		super(coord);
		super.energie = 60;
		super.equipe = equipe;
		super.plateau = plateau;
	}
	
	private Robot getRobotFromPlateau(Direction direction){
		if(this.peutTirer(direction)){
		return (Robot) plateau.getContenu(this.coordonnees.cibler(direction.getCoordonnees()));
		}
		System.out.println("Ce n'est pas un robot !");
		return null;
	}
	
	public int getEnergie() {		return super.energie;}
	public int getDegat() {			return 6;}
	public int getPortee() {		return 10;}
	public int getDeplacement() {	return 2;}
	public int getCout() {			return 1;}
	public int getCoutAvancer() { 	return 5;}
	public int getEnergieRecupEnBase() {return 2;}
	
	void tirer(Direction direction) {
		getRobotFromPlateau(direction).subitDegatsEtMeurtPotentiellement(this.getDegat());
	}

	public boolean peutTirer(Direction direction) { //KISS KISS ON TE DIT
		for(int facteur = 1; facteur <= getPortee(); facteur++){
			if(plateau.estDans(this.coordonnees.cibler(direction.getCoordonnees().multiplier(facteur)))){
				if(plateau.getContenu(this.coordonnees.cibler(direction.getCoordonnees().multiplier(facteur))) instanceof Robot){
					if(getRobotFromPlateau(direction).equipe == this.equipe){
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
}