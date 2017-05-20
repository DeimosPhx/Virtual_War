package joueur;

import com.sun.media.sound.DirectAudioDeviceProvider;

import javafx.scene.shape.Rectangle;
import unite.Robot;
import terrain.Direction;

public class Hitboxe {
	private Rectangle hitboxe;
	private Robot robot;
	private Direction direction;
	
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
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
	public Hitboxe(Rectangle hitboxe, Robot robot,Direction direction) {
		this.hitboxe = hitboxe;
		this.robot = robot;
		this.direction = direction;
	}

}
