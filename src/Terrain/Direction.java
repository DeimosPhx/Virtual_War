package Terrain;

	public enum Direction {
		HAUT(new Coordonnees(-1,0)),
		BAS(new Coordonnees(1,0)),
		DROITE(new Coordonnees(0,1)),
		GAUCHE(new Coordonnees(0,-1)),
		HAUT_GAUCHE(new Coordonnees(-1,-1)),
		HAUT_DROITE(new Coordonnees(-1,1)),
		BAS_GAUCHE(new Coordonnees(1,-1)),
		BAS_DROITE(new Coordonnees(1,1));
		
		private Coordonnees direction;
		private Direction(Coordonnees direction) {this.direction=direction;}
		public Coordonnees getCoordonnees() { return direction; }
	}