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
			if(accelerate()){ 
				 for(int x=0;x<3;x++){
				 CoordsflameX[x]=(int)(flamecenterX[x]*Math.cos(angle)-
				 flamecenterY[x]*Math.sin(angle)+
				 positionX+.5);
				 CoordsflameY[x]=(int)(flamecenterX[x]*Math.sin(angle)+
				 flamecenterY[x]*Math.cos(angle)+
				 positionY+.5);
				 }
				 g.setColor(Color.red); 
				 g.fillPolygon(CoordsflameX,CoordsflameY,3); 
				 }
			g.setColor(Color.white); 
			g.fillPolygon(CoordsX,CoordsY,4); 
	}


	
}