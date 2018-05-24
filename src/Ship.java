import java.awt.List;
import java.util.ArrayList;

public class Ship {
	private double positionX, positionY; // where the car is
	private double velocityX, velocityY; // speed on each axis
	private double drag; // how fast the car slows down
	private double angle; // the rotation of the car, in radians
	private double angularVelocity; //speed the car is spinning, in radians
	private double power; //how fast car can accelerate
	private double turnSpeed; //how fast to turn
	private double angularDrag=0; //how fast the car stops spinning
	private String direct;
	private static int WIDTH = 20;
	private static int LENGTH = 20;
	
	public Ship() {
		positionX = 10;
		positionY = 10;
		this.setDirection("left");


	}
	public boolean turnLeft() {
		angularVelocity -= turnSpeed;
		return true;
	}
	public boolean turnRight() {
		angularVelocity -= turnSpeed;
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

	public void move(String direction) {
		
		direct = direction;
		
		if(direct.compareTo("left") == 0) {
				if(positionX-1 >= 0) {
					positionX--;
					//System.out.println(x);
				}
				else {
					System.out.println("hit wall");
				}
				
		}
		else if(direct.compareTo("right") == 0) {
			if(positionX+1 <= WIDTH) {
				positionX++;
				//System.out.println(x);
			}
			else {
				System.out.println("hit wall");
			}
		}
		else if(direct.compareTo("up") == 0) {
			if(positionY+1 <= LENGTH) {
				positionY++;
				//System.out.println(x);
			}
			else {
				System.out.println("hit wall");
			}
		}
		else if(direct.compareTo("down") == 0) {
			if(positionY-1 >= 0) {
				positionY--;
				//System.out.println(x);
			}
			else {
				System.out.println("hit wall");
			}
		}
	}
	
	public String getDirection() {
		return direct;
	}
	public void setDirection(String directi) {
		direct = directi;
	}
	
//	public ArrayList<Integer> getLocationsX() {		
//		LocationsXCopy= new ArrayList<Integer> (LocationsX.subList(0, length));
//		return LocationsXCopy;
//	}
//	public ArrayList<Integer> getLocationsY() {		
//		LocationsYCopy = new ArrayList<Integer> (LocationsY.subList(0, length));
//		return LocationsYCopy;
//	}
//	
//	public boolean eatDot() {
//		length++;
//		return true;
//	}
	
}
