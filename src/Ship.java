import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.util.ArrayList;

public class Ship implements Drawable{
	private double positionX, positionY; // where the car is
	private double velocityX, velocityY; // speed on each axis
	private double drag; // how fast the car slows down
	private double angle; // the rotation of the car, in radians
	private double angularVelocity; //speed the car is spinning, in radians
	private double power = 10; //how fast car can accelerate
	private double turnSpeed = 5; //how fast to turn
	private double angularDrag=0; //how fast the car stops spinning
	private String direct;
	private static int WIDTH = 20;
	private static int LENGTH = 20;
	
	public Ship() {
		positionX = 200;
		positionY = 200;
	}
	public boolean turnLeft() {
		angularVelocity -= turnSpeed;
		return true;
	}
	public boolean turnRight() {
		angularVelocity += turnSpeed;
		return true;
	}
	public void update() {
		positionX += velocityX;
		positionY += velocityY;
		velocityX *= drag;
		velocityY *= drag;
		angle += angularVelocity;
		angularVelocity *= angularDrag;
	}
	
	public boolean accelerate() {
		velocityX += Math.sin(angle) * power;
		velocityY += Math.cos(angle) * power;
		return true;
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect((int)positionX, (int)positionY, 10, 10);
	}


	
}