import java.awt.Color;

import java.awt.Graphics;

import java.awt.List;

import java.util.ArrayList;



public class Ship implements Drawable{

	public int isShooting =1;

	public ArrayList<Bullet> bulletList = new ArrayList<Bullet>();

	private double positionX, positionY; 

	private double velocityX =0, velocityY=0; 

	private double drag = 0.98; 

	private double angle = (3*Math.PI)/2; 

	private double angularVelocity = 0; 

	private double power = .5; 

	private double turnSpeed = .05; 

	private double angularDrag=0.75; 

	private static int Height = 400;

	private static int Width = 600;

	private boolean accelerate;

	private boolean Right;

	private boolean Left;

	final double[] centerX={12,-10,-4,-10},centerY={0,-7,0,7},

			 flamecenterX={-4,-15,-4},flamecenterY={-4,0,4};

	final int radius=6;

	int[] CoordsX, CoordsY, CoordsflameX, CoordsflameY;

	

	public Ship() {

		positionX = 300;

		positionY = 200;

		CoordsX=new int[4]; // allocate space for the arrays

		CoordsY=new int[4];

		CoordsflameX=new int[3];

		CoordsflameY=new int[3]; 

	}

	public boolean collision(Asteroid temp) {

		if((((temp.getPositionx()+temp.getRadius())>(positionX-radius))&&((temp.getPositionx()-temp.getRadius())<(positionX+radius)))

		&&(((temp.getPositiony()+temp.getRadius())>(positionY-radius))&&((temp.getPositiony()-temp.getRadius())<(positionY+radius)))){

			return true;

		}

		return false;

	}

	public void update() {

		positionX += velocityX;

		positionY += velocityY;

		velocityX *= drag;

		velocityY *= drag;

		angle += angularVelocity;

		angularVelocity *= angularDrag;

		if(positionX<0)

			 positionX+=Width; 

		else if(positionX>Width)

			 positionX-=Width;

		if(positionY<0)

			 positionY+=Height;

		else if(positionY>Height)

			 positionY-=Height; 

		if(angle>(2*Math.PI)) 

			 angle-=(2*Math.PI); 

		if(accelerate&&velocityX<=2.5&&velocityY<=2.5&&velocityX>=-2.5&&velocityY>=-2.5) {

			velocityX += Math.cos(angle) * power;

			velocityY += Math.sin(angle) * power;

			}

		if(Left&&angularVelocity >= -.08) {

				angularVelocity -= turnSpeed;

			}

		if(Right&&angularVelocity <= .08) {

				angularVelocity += turnSpeed;

			}

	}

	public boolean setAccel(boolean temp) {

		accelerate = temp;

		return true;

	}

	public boolean setRight(boolean temp) {

		Right = temp;

		return true;

	}

	public boolean setLeft(boolean temp) {

		Left = temp;

		return true;

	}

	public double getAngle() {

		return angle;

	}

	public void shoot() {

		if(isShooting==1) {

		Bullet b = new Bullet(positionX,positionY,angle);

		bulletList.add(b);

		isShooting--;

		}

	}

	

	public double getPositionx() {

		return positionX;

	}

	public double getPositiony() {

		return positionY;

	}

	@Override

	public void draw(Graphics g) {

			for(int x=0;x<4;x++){

			 CoordsX[x]=(int)(centerX[x]*Math.cos(angle)- 

			 centerY[x]*Math.sin(angle)+

			 positionX+.5); 

			 CoordsY[x]=(int)(centerX[x]*Math.sin(angle)+ 

			 centerY[x]*Math.cos(angle)+

			 positionY+.5); 

			 }

			if(accelerate){ 

				 for(int x=0;x<3;x++){

				 CoordsflameX[x]=(int)(flamecenterX[x]*Math.cos(angle)-

				 flamecenterY[x]*Math.sin(angle)+

				 positionX+.5);

				 CoordsflameY[x]=(int)(flamecenterX[x]*Math.sin(angle)+

				 flamecenterY[x]*Math.cos(angle)+

				 positionY+.5);

				 }

				 g.setColor(Color.white);

				 g.drawPolygon(CoordsflameX,CoordsflameY,3); 

				 }

			g.setColor(Color.white); 

			g.drawPolygon(CoordsX,CoordsY,4); 

	}





	

}