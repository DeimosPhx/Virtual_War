package unite;

import Terrain.Coordonnees;
import Terrain.Direction;
import Terrain.Obstacle;
import Terrain.Parcelle;
import Terrain.Plateau;

public class Char extends Robot{
	public Char(int equipe, Coordonnees coord, Plateau plateau) {
		super(coord);
		super.energie = 60;
		super.equipe = equipe;
		super.plateau = plateau;
	}
	
	private Robot getRobotFromPlateau(Direction direction){
		return (Robot) plateau.getContenu(super.cord.cibler(direction.getCoordonnees().multiplier(distanceDeTir(direction))));
	}
	
	private int distanceDeTir(Direction direction){
		for(int facteur = 1; facteur <= getPortee(); facteur++){
				if(plateau.getContenu(super.cord.cibler(direction.getCoordonnees().multiplier(facteur))) instanceof Robot){
						return facteur;
			}
		}
		return 0;
	}
	public int getEnergie() {		return super.energie;}
	public int getDegat() {			return 6;}
	public int getPortee() {		return 10;}
	public int getDeplacement() {	return 2;}
	public int getCout() {			return 1;}
	public int getCoutAvancer() { 	return 5;}
	public int getEnergieRecupEnBase() {return 2;}
	
	public boolean tirer(Direction direction) {
		return getRobotFromPlateau(direction).subitDegatsEtMeurtPotentiellement(this.getDegat());
	}

	public boolean peutTirer(Direction direction) { //KISS KISS ON TE DIT
		for(int facteur = 1; facteur <= getPortee(); facteur++){
			if(plateau.estDans(super.cord.cibler(direction.getCoordonnees().multiplier(facteur)))){
				if(plateau.getContenu(super.cord.cibler(direction.getCoordonnees().multiplier(facteur))) instanceof Robot
						||  plateau.getContenu(super.cord.cibler(direction.getCoordonnees().multiplier(facteur))) instanceof Obstacle){
					facteur = getPortee();
						if(plateau.getContenu(super.cord.cibler(direction.getCoordonnees().multiplier(facteur))) instanceof Robot){
							if(getRobotFromPlateau(direction).getEquipe() != this.equipe){
								return true;
							}
						}
				}
			}
		}
		return false;
	}
	
	public boolean subitDegatsEtMeurtPotentiellement(int degats) {
		this.energie -= degats;
		if(this.energie <= 0){
			return true;
		}
		return false;
	}
	public void recuperationEnergie() {
		this.energie += this.getEnergieRecupEnBase();
	}
	public String toString(){
		if(this.equipe == 1){
			return "C";
		}
		return "c";
	}
}
