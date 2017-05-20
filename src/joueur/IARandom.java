package joueur;

import java.util.Random;
import terrain.Base;
import terrain.Direction;
import terrain.Plateau;
import joueur.Joueur;
import joueur.Vue;
import unite.Char;
import unite.Piegeur;
import unite.Robot;
import unite.Tireur;

public class IARandom extends Joueur {

	private Random r = new Random();

	public IARandom(int equipe) {
		super(equipe);
		// TODO Auto-generated constructor stub
	}

	public IARandom(int equipe, Base base, Vue vue) {
		super(equipe, base, vue);
		// TODO Auto-generated constructor stub
	}

	public Robot selecUnite() {
		int taille = super.getListeRobot().size()-1;
		int numRobot = r.nextInt(taille);
		return super.getListeRobot().get(numRobot);
	}

	public void action(Robot rob, Plateau ter) {

		boolean deplacementEffectue = false;

		if ( rob instanceof Tireur ) {
			if ( rob.peutTirer(Direction.HAUT) == true) {
				rob.tirer(Direction.HAUT);
			}
			else if ( rob.peutTirer(Direction.BAS) == true ) {
				rob.tirer(Direction.BAS);
			}
			else if ( rob.peutTirer(Direction.GAUCHE) == true ) {
				rob.tirer(Direction.GAUCHE);
			}
			else if ( rob.peutTirer(Direction.DROITE) == true ) {
				rob.tirer(Direction.DROITE);
			}
			else {
				while (deplacementEffectue == false) {
					switch ( r.nextInt(9)+1 ) {
					case 1:
						if ( ter.deplacerTest(this,rob,Direction.HAUT) == true ) {
							deplacementEffectue = true;
						}
						break;
					case 2:
						if ( ter.deplacerTest(this,rob,Direction.BAS) == true ) {
							deplacementEffectue = true;
						}
						break;
					case 3:
						if ( ter.deplacerTest(this,rob,Direction.GAUCHE) == true ) {
							deplacementEffectue = true;
						}
						break;
					case 4:
						if ( ter.deplacerTest(this,rob,Direction.DROITE) == true ) {
							deplacementEffectue = true;
						}
						break;
					case 5:
						if ( ter.deplacerTest(this,rob,Direction.BAS_DROITE) == true ) {
							deplacementEffectue = true;
						}
						break;
					case 6:
						if ( ter.deplacerTest(this,rob,Direction.BAS_GAUCHE) == true ) {
							deplacementEffectue = true;
						}
						break;
					case 7:
						if ( ter.deplacerTest(this,rob,Direction.HAUT_DROITE) == true ) {
							deplacementEffectue = true;
						}
						break;
					case 8:
						if ( ter.deplacerTest(this,rob,Direction.HAUT_GAUCHE) == true ) {
							deplacementEffectue = true;
						}
						break;

					}
				}
			}
		}
		else if ( rob instanceof Char ) {
			if ( rob.peutTirer(Direction.HAUT) == true) {
				rob.tirer(Direction.HAUT);
			}
			else if ( rob.peutTirer(Direction.BAS) == true ) {
				rob.tirer(Direction.BAS);
			}
			else if ( rob.peutTirer(Direction.GAUCHE) == true ) {
				rob.tirer(Direction.GAUCHE);
			}
			else if ( rob.peutTirer(Direction.DROITE) == true ) {
				rob.tirer(Direction.DROITE);
			}
			else {
				while (deplacementEffectue == false) {
					switch ( r.nextInt(9)+1 ) {
					case 1:
						if ( ter.deplacerTest(this,rob,Direction.HAUT) == true ) {
							ter.deplacerTest(this,rob,Direction.HAUT);
							deplacementEffectue = true;
						}
						break;
					case 2:
						if ( ter.deplacerTest(this,rob,Direction.BAS) == true ) {
							ter.deplacerTest(this,rob,Direction.BAS);
							deplacementEffectue = true;
						}
						break;
					case 3:
						if ( ter.deplacerTest(this,rob,Direction.GAUCHE) == true ) {
							ter.deplacerTest(this,rob,Direction.GAUCHE);
							deplacementEffectue = true;
						}
						break;
					case 4:
						if ( ter.deplacerTest(this,rob,Direction.DROITE) == true ) {
							ter.deplacerTest(this,rob,Direction.DROITE);
							deplacementEffectue = true;
						}
					}
				}
			}
		}
		else if ( rob instanceof Piegeur ) {
			if ( rob.peutTirer(Direction.HAUT) == true) {
				rob.tirer(Direction.HAUT);
			}
			else if ( rob.peutTirer(Direction.BAS) == true ) {
				rob.tirer(Direction.BAS);
			}
			else if ( rob.peutTirer(Direction.GAUCHE) == true ) {
				rob.tirer(Direction.GAUCHE);
			}
			else if ( rob.peutTirer(Direction.DROITE) == true ) {
				rob.tirer(Direction.DROITE);
			}
			else if ( rob.peutTirer(Direction.HAUT_DROITE) == true) {
				rob.tirer(Direction.HAUT_DROITE);
			}
			else if ( rob.peutTirer(Direction.HAUT_GAUCHE) == true ) {
				rob.tirer(Direction.HAUT_GAUCHE);
			}
			else if ( rob.peutTirer(Direction.BAS_GAUCHE) == true ) {
				rob.tirer(Direction.BAS_GAUCHE);
			}
			else if ( rob.peutTirer(Direction.BAS_DROITE) == true ) {
				rob.tirer(Direction.BAS_DROITE);
			}
			else {
				while (deplacementEffectue == false) {
					switch ( r.nextInt(9)+1 ) {
					case 1:
						if ( ter.deplacerTest(this,rob,Direction.HAUT) == true ) {
							deplacementEffectue = true;
						}
						break;
					case 2:
						if ( ter.deplacerTest(this,rob,Direction.BAS) == true ) {
							deplacementEffectue = true;
						}
						break;
					case 3:
						if ( ter.deplacerTest(this,rob,Direction.GAUCHE) == true ) {
							deplacementEffectue = true;
						}
						break;
					case 4:
						if ( ter.deplacerTest(this,rob,Direction.DROITE) == true ) {
							deplacementEffectue = true;
						}
						break;
					case 5:
						if ( ter.deplacerTest(this,rob,Direction.BAS_DROITE) == true ) {
							deplacementEffectue = true;
						}
						break;
					case 6:
						if ( ter.deplacerTest(this,rob,Direction.BAS_GAUCHE) == true ) {
							deplacementEffectue = true;
						}
						break;
					case 7:
						if ( ter.deplacerTest(this,rob,Direction.HAUT_DROITE) == true ) {
							deplacementEffectue = true;
						}
						break;
					case 8:
						if ( ter.deplacerTest(this,rob,Direction.HAUT_GAUCHE) == true ) {
							deplacementEffectue = true;
						}
						break;

					}
				}
			}
		}
	}

}