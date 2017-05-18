package joueur;

import javafx.scene.shape.Rectangle;
import unite.Robot;

public class Hitboxe {
	private Rectangle hitboxe;
	private Robot robot;
	public Rectangle getHitboxe() {
		return hitboxe;
	}
	public void setHitboxe(Rectangle hitboxe) {
		this.hitboxe = hitboxe;
	}
	public Robot getRobot() {
		return robot;
	}
	public void setRobot(Robot robot) {
		this.robot = robot;
	}
	public Hitboxe(Rectangle hitboxe, Robot robot) {
		this.hitboxe = hitboxe;
		this.robot = robot;
	}
	
}
