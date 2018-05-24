
public class Asteroid {
	
	private String size;
	private double angle;
	private double[] currentLoc = new double[2];
	private int shape;
	private double speed;
	
	public Asteroid(String Size) {
		this.size = Size;
		this.angle = Math.random() * 360;
		shape = (int) (Math.random() * 3);	
		
		//setting speed based on size
		if (this.size.equals("Large")) {
			speed = 20.0/1000;
		}
		else if (this.size.equals("Medium")) {
			speed = 35.0/1000;
		}
		else {
			speed = 50.0/1000;
		}	
		
		currentLoc[0] = (int) Math.random() * 200;
		currentLoc[1] = (int) Math.random() * 200;
	}
	

	public void update() {
		currentLoc[0] += speed * Math.cos(angle);
		currentLoc[1] += speed * Math.sin(angle);
		
	}
	
	
}
