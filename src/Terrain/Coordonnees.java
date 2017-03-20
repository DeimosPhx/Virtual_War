package Terrain;

public class Coordonnees {
	private int abscisse;
	private int ordonnee;
	public Coordonnees(int abscisse, int ordonnee) {
		super();
		this.abscisse = abscisse;
		this.ordonnee = ordonnee;
	}
	public int getAbscisse() {
		return abscisse;
	}
	public int getOrdonnee() {
		return ordonnee;
	}
	
	public Coordonnees cibler(Coordonnees cord){
		return new Coordonnees(this.getAbscisse() + cord.getAbscisse(),this.getOrdonnee() + cord.getOrdonnee());
	}
}
