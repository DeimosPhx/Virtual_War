package Terrain;

import unite.Mine;

public class Vue {
	private Plateau plateau;
	private int equipe;
	
	public Vue(Plateau plateau, int equipe){
		this.equipe = equipe;
		this.plateau = plateau;
	}
	
	public int getEquipe() {return equipe;}
	public Parcelle getContenu(Coordonnees cible){
		if( this.plateau.getContenu(cible) instanceof Mine){
			return null;
		}
		return this.plateau.getContenu(cible);
	}
	
}
