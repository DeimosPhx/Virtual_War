package unite;

import terrain.Coordonnees;
import terrain.Direction;
import terrain.Obstacle;
import terrain.Parcelle;
import terrain.Plateau;

public class Tireur extends Robot {
	
	public Tireur(int equipe, Coordonnees coord, Plateau plateau) {
		super(coord);
		super.energie = 40;
		super.equipe = equipe;
		super.plateau = plateau;
	}
	
	private Robot getRobotFromPlateau(Direction direction){
		return (Robot) plateau.getContenu(super.cord.cibler(direction.getCoordonnees().multiplier(distanceDeTir(direction))));
	}
	public Coordonnees getCoordonnees(){return super.cord;}
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
			if(plateau.estDans(super.cord.cibler(direction.getCoordonnees().multiplier(facteur)))){
				System.out.println("on est bien dans le plateau");
				if(plateau.getContenu(super.cord.cibler(direction.getCoordonnees().multiplier(facteur))) instanceof Robot
						|| plateau.getContenu(super.cord.cibler(direction.getCoordonnees().multiplier(facteur))) instanceof Obstacle ){
					sortir = true;
					System.out.println("un obstacle ou un robot allié gene le tir !");
					if(plateau.getContenu(super.cord.cibler(direction.getCoordonnees().multiplier(facteur))) instanceof Robot){
						System.out.println("y'a bien un robot");
						if(getRobotFromPlateau(direction).getEquipe() != this.equipe){
							System.out.println("Le robot est de l'équipe adverse ! yey on tire !");
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
				if(plateau.getContenu(super.cord.cibler(direction.getCoordonnees().multiplier(facteur))) instanceof Robot){
						return facteur;
			}
		}
		return 0;
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
		if(getEquipe()==1){return "T";}
		return "t";
	}
}