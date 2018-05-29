import java.awt.Color;

import java.awt.Graphics;



public class Bullet {

	double currentX, currentY;

	double deltaX, deltaY;

	public double endDistance = 50;

	double speed = 6, width = 600, height = 400;

	public Bullet(double x, double y,double angle) {

		deltaX = (speed*Math.cos(angle));

		deltaY = (speed*Math.sin(angle)); 

		currentX = x;

		currentY = y;



	}

	public boolean collision(Asteroid temp) {

		if(temp==null) {

			return false;

		}

		if((((temp.getPositionx()+temp.getRadius())>currentX)&&((temp.getPositionx()-temp.getRadius())<currentX))

		&&(((temp.getPositiony()+temp.getRadius())>currentY)&&((temp.getPositiony()-temp.getRadius())<currentY))){

			return true;

		}

		return false;

	}





	public void move() {

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

		g.fillOval((int) currentX, (int) currentY, 1, 1);

	}



	



}