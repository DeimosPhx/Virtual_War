package Terrain;
import unite.*;

public class Parcelle {
	protected Coordonnees cord;
	private boolean estVide;
	protected int equipe;
	public Parcelle(Coordonnees cord){
		this.cord = cord;
		estVide = true;
	}
	public void vider(){
		this.estVide = true;
	}
	public boolean estVide(){
		return this.estVide;
	}
	public void setPasVide(){
		this.estVide = false;
	}
	public Coordonnees getCord(){
		return this.cord;
	}
	public int getEquipe(){
		return equipe;
	}
	public String toString(){
		String st = " ";
		if(this instanceof Base){
			st = "b";
		}
		else if(this instanceof Char){
			st = "c";
		}
		else if(this instanceof Mine){
			st = "m";
		}
		else if(this instanceof Piegeur){
			st = "p";
		}
		else if(this instanceof Tireur){
			st = "t";
		}
		else if(this instanceof Obstacle){
			st = "O";
		}
		if(equipe == 1 && !st.equals("O")){
			st = st.toUpperCase();
		}
		return st;
	}
	
	public boolean autoriserPlacementObstacle(Plateau plat,Coordonnees c){
		/*Coordonnees[] diags = new Coordonnees[4];
		diags[0]=Direction.HAUT_GAUCHE.getCoordonnees();
		diags[1]=Direction.HAUT_DROITE.getCoordonnees();
		diags[2]=Direction.BAS_GAUCHE.getCoordonnees();
		diags[3]=Direction.BAS_DROITE.getCoordonnees();
		for(int i=0;i<4;i++){
		if(plat.estDans(plat[c.getAbscisse()][c.getOrdonnee()].getCord().cibler(diags[i]))){
			return false;
		}
		}*/
		if(c.getAbscisse()>1 && c.getOrdonnee()>1){
			Coordonnees target = new Coordonnees(c.getAbscisse()+Direction.HAUT_GAUCHE.getCoordonnees().getAbscisse(),c.getOrdonnee()+Direction.HAUT_GAUCHE.getCoordonnees().getOrdonnee());
			if(!plat.estDans(target)){
				return false;
			}
			/*if(!plat.getGrille()[c.getAbscisse()+Direction.HAUT_GAUCHE.getCoordonnees().getAbscisse()][c.getOrdonnee()+Direction.HAUT_GAUCHE.getCoordonnees().getOrdonnee()].estVide()){
				return false;
			}*/
		}
		if(c.getAbscisse()!=0 && c.getOrdonnee()!=9){
			Coordonnees target = new Coordonnees(c.getAbscisse()+Direction.HAUT_DROITE.getCoordonnees().getAbscisse(),c.getOrdonnee()+Direction.HAUT_DROITE.getCoordonnees().getOrdonnee());
			if(!plat.estDans(target)){
				return false;
			}
			/*if(!plat.getGrille()[c.getAbscisse()+Direction.HAUT_DROITE.getCoordonnees().getAbscisse()][c.getOrdonnee()+Direction.HAUT_DROITE.getCoordonnees().getOrdonnee()].estVide()){
				return false;
			}*/
		}
		if(c.getAbscisse()!=9 && c.getOrdonnee()!=0){
			Coordonnees target = new Coordonnees(c.getAbscisse()+Direction.BAS_GAUCHE.getCoordonnees().getAbscisse(),c.getOrdonnee()+Direction.BAS_GAUCHE.getCoordonnees().getOrdonnee());
			if(!plat.estDans(target)){
				return false;
			}
			/*if(!plat.getGrille()[c.getAbscisse()+Direction.BAS_GAUCHE.getCoordonnees().getAbscisse()][c.getOrdonnee()+Direction.BAS_GAUCHE.getCoordonnees().getOrdonnee()].estVide()){
				return false;
			}*/
		}
		if(c.getAbscisse()!=9 && c.getOrdonnee()!=9){
			Coordonnees target = new Coordonnees(c.getAbscisse()+Direction.BAS_DROITE.getCoordonnees().getAbscisse(),c.getOrdonnee()+Direction.BAS_DROITE.getCoordonnees().getOrdonnee());
			if(!plat.estDans(target)){
				return false;
			}
			/*if(!plat.getGrille()[c.getAbscisse()+Direction.BAS_DROITE.getCoordonnees().getAbscisse()][c.getOrdonnee()+Direction.BAS_DROITE.getCoordonnees().getOrdonnee()].estVide()){
				return false;
			}*/
		}
		
		return true;
	}
}