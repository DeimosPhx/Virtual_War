package unite;

import Terrain.Coordonnees;
import Terrain.Direction;
import Terrain.Obstacle;
import Terrain.Parcelle;
import Terrain.Plateau;

public class Tireur extends Robot {
	
	public Tireur(int equipe, Coordonnees coord, Plateau plateau) {
		super(coord);
		this.coordonnees = coord;
		super.energie = 40;
		super.equipe = equipe;
		super.plateau = plateau;
	}
	
	private Robot getRobotFromPlateau(Direction direction){
		return (Robot) plateau.getContenu(this.coordonnees.cibler(direction.getCoordonnees().multiplier(distanceDeTir(direction))));
	}
	public Coordonnees getCoordonnees(){return this.coordonnees;}
	public int getEnergie() {		return super.energie;}
	public int getDegat() {			return 3;}
	public int getPortee() {		return 3;}
	public int getDeplacement() {	return 1;}
	public int getCout() {			return 2;}
	public int getCoutAvancer() { 	return 1;}
	public int getEnergieRecupEnBase() {return 2;}
	
	public boolean tirer(Direction direction) {
		return getRobotFromPlateau(direction).subitDegatsEtMeurtPotentiellement(this.getDegat());
	}
	
	public boolean peutTirer(Direction direction) { //KISS KISS ON TE DIT
		boolean sortir = false;
		for(int facteur = 1; facteur <= getPortee(); facteur++){
			if(plateau.estDans(this.coordonnees.cibler(direction.getCoordonnees().multiplier(facteur)))){
				if(plateau.getContenu(this.coordonnees.cibler(direction.getCoordonnees().multiplier(facteur))) instanceof Robot
						|| plateau.getContenu(this.coordonnees.cibler(direction.getCoordonnees().multiplier(facteur))) instanceof Obstacle ){
					sortir = true;
					if(plateau.getContenu(this.coordonnees.cibler(direction.getCoordonnees().multiplier(facteur))) instanceof Robot){
						if(getRobotFromPlateau(direction).getEquipe() != this.equipe){
							return true;
						}
					}
				}
			}
			if(sortir){
				return false;
			}
		}
		return false;
	}
	
	private int distanceDeTir(Direction direction){
		for(int facteur = 1; facteur <= getPortee(); facteur++){
				if(plateau.getContenu(this.coordonnees.cibler(direction.getCoordonnees().multiplier(facteur))) instanceof Robot){
						return facteur;
			}
		}
		return 0;
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
		if(getEquipe()==1){return "T";}
		return "t";
	}
}