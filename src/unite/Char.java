package unite;

import terrain.Coordonnees;
import terrain.Direction;
import terrain.Obstacle;
import terrain.Parcelle;
import terrain.Plateau;

public class Char extends Robot{

	public Char(int equipe, Coordonnees coord, Plateau plateau) {
		super(coord);
		super.energie = 60;
		super.equipe = equipe;
		super.plateau = plateau;
	}

	public Robot getRobotFromPlateau(Direction direction){
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
		boolean sortir = false;
		if(direction.equals(Direction.BAS)||direction.equals(Direction.HAUT)||direction.equals(Direction.GAUCHE)||direction.equals(Direction.DROITE)){
			for(int facteur = 1; facteur <= getPortee(); facteur++){
				if(plateau.estDans(super.cord.cibler(direction.getCoordonnees().multiplier(facteur)))){
					if(plateau.getContenu(super.cord.cibler(direction.getCoordonnees().multiplier(facteur))) instanceof Robot
							|| plateau.getContenu(super.cord.cibler(direction.getCoordonnees().multiplier(facteur))) instanceof Obstacle ){
						sortir = true;
						if(plateau.getContenu(super.cord.cibler(direction.getCoordonnees().multiplier(facteur))) instanceof Robot){
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
		this.energie += getEnergieRecupEnBase();
		if(this.energie>=60){
			this.energie=60;
		}
	}
	public String toString(){
		if(this.equipe == 1){
			return "C";
		}
		return "c";
	}
}
