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

	public boolean tirer(Direction direction) {
		this.plateau.setMine(super.cord.cibler(direction.getCoordonnees()), new Mine(this.equipe, super.cord));
		return false;
	}

	public boolean peutTirer(Direction direction) {
		if(plateau.estDans(super.cord.cibler(direction.getCoordonnees()))){
			if(!((plateau.getContenu(super.cord.cibler(direction.getCoordonnees()))) instanceof Robot)
				&& !((plateau.getContenu(super.cord.cibler(direction.getCoordonnees()))) instanceof Base)
				&& !((plateau.getContenu(super.cord.cibler(direction.getCoordonnees()))) instanceof Obstacle)){
				return true;
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
		if(this.equipe==1){
			return "P";
		}
		return "p";
	}
}
