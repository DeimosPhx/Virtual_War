package terrain;

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
	public Coordonnees multiplier(int facteur){
		return new Coordonnees(getAbscisse()*facteur, getOrdonnee()*facteur);
	}
	public Coordonnees cibler(Coordonnees cord){
		return new Coordonnees(this.getAbscisse() + cord.getAbscisse(),this.getOrdonnee() + cord.getOrdonnee());
	}
    public Direction coordonneeToDirection(){
    	if(this.getAbscisse()== -1 && this.getOrdonnee()==  0){return Direction.HAUT;}
    	if(this.getAbscisse()==  1 && this.getOrdonnee()==  0){return Direction.BAS;}
    	if(this.getAbscisse()==  0 && this.getOrdonnee()==  1){return Direction.DROITE;}
    	if(this.getAbscisse()==  0 && this.getOrdonnee()== -1){return Direction.GAUCHE;}
    	if(this.getAbscisse()== -1 && this.getOrdonnee()== -1){return Direction.HAUT_GAUCHE;}
    	if(this.getAbscisse()== -1 && this.getOrdonnee()==  1){return Direction.HAUT_DROITE;}
    	if(this.getAbscisse()==  1 && this.getOrdonnee()== -1){return Direction.BAS_GAUCHE;}
    	if(this.getAbscisse()==  1 && this.getOrdonnee()==  1){return Direction.BAS_DROITE;}
    	return Direction.NULL;
    }
    
    
    
    
    
}