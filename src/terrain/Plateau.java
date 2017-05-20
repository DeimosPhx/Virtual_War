package terrain;

import unite.Char;
import unite.Mine;
import unite.Piegeur;
import unite.Robot;
import unite.Tireur;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import joueur.Joueur;
import joueur.Vue;

/*import javax.swing.*;
import java.awt.*;*/
/*
 * Ebauche de plateau graphique intï¿½grï¿½ au code.
 */
public class Plateau extends JPanel {
	// attributs
	public static Parcelle[][] grille;
	private int tailleX, tailleY;
	private Random r = new Random();
	// constructors
	// CHANGEMENT
	private boolean tourJ1 = true;

	public Plateau(int taillex, int tailley) {
		this.grille = new Parcelle[taillex][tailley];
		this.tailleX = taillex;
		this.tailleY = tailley;

		for (int i = 0; i < taillex; i++) {
			for (int j = 0; j < tailley; j++) {
				this.grille[i][j] = new Parcelle(new Coordonnees(i, j));
			}
		}

	}

	public Parcelle[][] getGrille() {
		return this.grille;
	}

	public int getX() {
		return tailleX;
	}

	public int getY() {
		return tailleY;
	}

	public Plateau(int taillex, int tailley, char remplir) {
		this(taillex, tailley);
		// this.remplir(remplir);
	}

	// methodes
	/*
	 * public void remplir(char c){ for(int x=0;x<this.tailleX;x++){ for(int
	 * y=0;y<this.tailleY;y++){ this.grille[x][y] = c; } } }
	 */
	public boolean estVide(Coordonnees cord) {
		return grille[cord.getAbscisse()][cord.getOrdonnee()].estVide();
	}

	public void setObstacle(Coordonnees cord) {
		grille[cord.getAbscisse()][cord.getOrdonnee()] = new Obstacle(cord);
	}

	public void setBase(Base base) {
		grille[base.getCord().getAbscisse()][base.getCord().getOrdonnee()] = base;
	}

	public void setRobot(Robot robot) {
		grille[robot.getAbscisse()][robot.getOrdonnee()] = robot;
	}

	public Parcelle getContenu(Coordonnees cord) {
		return grille[cord.getAbscisse()][cord.getOrdonnee()];
	}

	public void setMine(Coordonnees cord, Mine mine) {
		grille[cord.getAbscisse()][cord.getOrdonnee()] = mine;
	}

	public boolean deplacerTest(Joueur j, Robot rob, Direction direc) {
		/*
		 * test de deplacement simple
		 */
		/*
		 * cible:
		 * this.grille[cord_unit.cibler(direc.getCoordonnees()).getAbscisse()][
		 * cord_unit.cibler(direc.getCoordonnees()).getOrdonnee()] coordonnees
		 * unit�:
		 * this.grille[cord_unit.getAbscisse()][cord_unit.getOrdonnee()]
		 * coordonnees de la destination:
		 * this.grille[direc.getCoordonnees().getAbscisse()][direc.
		 * getCoordonnees().getOrdonnee()]
		 */
		Coordonnees cord_unit = rob.getCord();

		if (!this.estDans(this.grille[cord_unit.getAbscisse()][cord_unit.getOrdonnee()].getCord()
				.cibler(direc.getCoordonnees()))) {
			/*
			 * on ne fait rien car on sort du tableau
			 */
			return false;
		} else if (this.grille[cord_unit.cibler(direc.getCoordonnees()).getAbscisse()][cord_unit
				.cibler(direc.getCoordonnees()).getOrdonnee()] instanceof Obstacle
				|| this.grille[cord_unit.cibler(direc.getCoordonnees()).getAbscisse()][cord_unit
						.cibler(direc.getCoordonnees()).getOrdonnee()] instanceof Robot) {
			/*
			 * on ne fait rien car on rencontre un obstacle ou un robot
			 */
			return false;
		} else {
			if (this.grille[cord_unit.cibler(direc.getCoordonnees()).getAbscisse()][cord_unit
					.cibler(direc.getCoordonnees()).getOrdonnee()] instanceof Mine) {
				rob.subitDegatsEtMeurtPotentiellement(new Piegeur(0, null, null).getDegat());
			}
			if (!(this.grille[cord_unit.cibler(direc.getCoordonnees()).getAbscisse()][cord_unit
					.cibler(direc.getCoordonnees()).getOrdonnee()] instanceof Base)) {
				this.grille[cord_unit.cibler(direc.getCoordonnees()).getAbscisse()][cord_unit
						.cibler(direc.getCoordonnees()).getOrdonnee()] = rob;
				rob.deployer(new Coordonnees(cord_unit.cibler(direc.getCoordonnees()).getAbscisse(),
						cord_unit.cibler(direc.getCoordonnees()).getOrdonnee()));
				if (j.getBase().estDans(rob)) {
					j.getBase().removeRobot(rob);
				}
			} else if (this.grille[cord_unit.cibler(direc.getCoordonnees()).getAbscisse()][cord_unit
					.cibler(direc.getCoordonnees()).getOrdonnee()].getEquipe() == rob.getEquipe()) {
				// On entre dans base
				rob.deployer(j.getBase().getCord());
				j.getBase().addRobot(rob);
			}
			if (!(this.grille[cord_unit.getAbscisse()][cord_unit.getOrdonnee()] instanceof Base)) {
				this.grille[cord_unit.getAbscisse()][cord_unit.getOrdonnee()] = new Parcelle(
						new Coordonnees(cord_unit.getAbscisse(), cord_unit.getOrdonnee()));
			}
			return true;
		}
	}

	public boolean peuxDeplacer(Joueur j, Robot rob, Direction direc) {
		/*
		 * Meme chose que deplacertest, mais qui ne renvoie qu'un boolean.
		 */
		Coordonnees cord_unit = rob.getCord();
		if (rob instanceof Char && (direc.equals(Direction.BAS_DROITE) || direc.equals(Direction.BAS_GAUCHE)
				|| direc.equals(Direction.HAUT_DROITE) || direc.equals(Direction.HAUT_GAUCHE))) {
			return false;
		}

		if (!this.estDans(this.grille[cord_unit.getAbscisse()][cord_unit.getOrdonnee()].getCord()
				.cibler(direc.getCoordonnees()))) {
			/*
			 * on ne fait rien car on sort du tableau
			 */
			return false;
		} else if (this.grille[cord_unit.cibler(direc.getCoordonnees()).getAbscisse()][cord_unit
				.cibler(direc.getCoordonnees()).getOrdonnee()] instanceof Obstacle
				|| this.grille[cord_unit.cibler(direc.getCoordonnees()).getAbscisse()][cord_unit
						.cibler(direc.getCoordonnees()).getOrdonnee()] instanceof Robot) {
			/*
			 * on ne fait rien car on rencontre un obstacle ou un robot
			 */
			return false;
		} else {
			return true;
		}
	}

	public boolean estDans(Coordonnees cord) {
		return this.tailleX > cord.getAbscisse() && this.tailleY > cord.getOrdonnee() && cord.getAbscisse() >= 0
				&& cord.getOrdonnee() >= 0;
	}

	public String toString() {
		String out = new String("");
		for (int x = 0; x < this.tailleX; x++) {
			for (int y = 0; y < this.tailleY; y++) {
				out += "|" + "_" + this.grille[x][y].toString() + "_" + "|";
			}
			out += "\n";
		}
		return out;
	}

}
