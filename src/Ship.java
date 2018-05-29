import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.util.ArrayList;

public class Ship implements Drawable{
	private int fireRate = 10;
	private int fireDelay = 0;
	public boolean isShooting;
	public ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
	private double positionX, positionY; 
	private double velocityX =0, velocityY=0; 
	private double drag = 0.98; 
	private double angle = 0; 
	private double angularVelocity = 0; 
	private double power = .7; 
	private double turnSpeed = .05; 
	private double angularDrag=0.75; 
	public static int Height = 400;
	public static int Width = 600;
	private boolean accelerate;
	private boolean Right;
	private boolean Left;
	final double[] centerX={14,-10,-6,-10},centerY={0,-8,0,8},
			 flamecenterX={-6,-23,-6},flamecenterY={-3,0,3};
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
	public boolean turnLeft() {
		angularVelocity -= turnSpeed;
		return true;
	}
	public boolean turnRight() {
		angularVelocity += turnSpeed;
		return true;
	}
	public void update() {
		if (fireDelay > 0) {
			fireDelay--;
		}
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
		if(accelerate&&velocityX<=3&&velocityY<=3&&velocityX>=-3&&velocityY>=-3) {
			velocityX += Math.cos(angle) * power;
			velocityY += Math.sin(angle) * power;
			}
		if(Left&&angularVelocity >= -.1) {
				angularVelocity -= turnSpeed;
			}
		if(Right&&angularVelocity <= .1) {
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
	
	public double getPositionx() {
		return positionX;
	}
	public double getPositiony() {
		return positionY;
	}
	
	
	public void shoot() {
		Bullet b = new Bullet(positionX,positionY,velocityX,velocityY,angle);
		bulletList.add(b);
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


