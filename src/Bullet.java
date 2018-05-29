import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
	
	double startX, startY, currentX, currentY;
	double deltaX, deltaY;
	public double endDistance = 300;
	double speed = 20;
	
	public Bullet(double x, double y, double velocityX, double velocityY,double angle) {
		// x and y should be the ship's current x and y
		
		deltaX = (2*Math.cos(angle)) + (0.5*velocityX);
		deltaY = (2*Math.sin(angle)) + (0.5 *velocityY);
		
		currentX = startX = x;
		currentY = startY = y;
	}

	public void move(double width, double height) {
		endDistance --;
		
		currentX += deltaX;
		currentY += deltaY;
		
		if (currentX < 0) {
			currentX += width;
		}
		else if (currentX > width) {
			currentX -= width;
		}
		
		if (currentY < 0) {
			currentY += height;
		}
		else if (currentY > height) {
			currentY -= height;
		}
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval((int) currentX, (int) currentY, 3, 3);
	}
	
}
