import java.awt.Color;
import java.awt.Graphics;

public class Asteroid implements Drawable{
	
	private String size;
	private double angle;
	public static int Height = 1000;
	public static int Width = 1000;
	private double positionX, positionY;
	private int shape;
	private double speed;
	private double radius;
	int[] CoordsX, CoordsY;
	double [] centerX, centerY;
	double[] X={0,-10,-8,-10,0,10,8,10},Y={-8,-10,0,10,8,10,0,-10};
	public Asteroid(String Size) {
		this.size = Size;
		this.angle = Math.random() * (Math.PI*2);	
		centerX=new double[8]; 
		centerY=new double[8];
		CoordsX= new int[8];
		CoordsY = new int[8];
		if (this.size.equals("Large")) {
			speed = .5;
			for(int x= 0; x<8; x++) {
			centerX[x] = X[x]*3;
			centerY[x] = Y[x]*3;
			}
			radius = 30;
		}
		else if (this.size.equals("Medium")) {
			speed = 1;
			for(int x= 0; x<8; x++) {
				centerX[x] = X[x]*2;
				centerY[x] = Y[x]*2;
				}
			radius = 20;
		}
		else {
			speed = 1.5;
			centerX = X;
			centerY = Y;
			radius = 10;
		}	
		
		positionX = (int)(Math.random() * Width);
		positionY = (int)(Math.random() * Height);
	}
	public double getPositionx() {
		return positionX;
	}
	public double getPositiony() {
		return positionY;
	}
	public double getRadius() {
		return radius;
	}
	public void update() {
		positionX += speed * Math.cos(angle);
		positionY += speed * Math.sin(angle);
		if(positionX<0)
			 positionX+=Width; 
		else if(positionX>Width)
			 positionX-=Width;
		if(positionY<0)
			 positionY+=Height;
		else if(positionY>Height)
			 positionY-=Height; 
		
	}
	public void changeSpeed(double temp) { //once we create levels this will be one of the ways to make it harder
		speed= speed*temp;
	}
	@Override
	public void draw(Graphics g) {
		for(int x=0;x<8;x++){
			 CoordsX[x]=(int)(centerX[x]*Math.cos(angle)- 
			 centerY[x]*Math.sin(angle)+
			 positionX+.5); 
			 CoordsY[x]=(int)(centerX[x]*Math.sin(angle)+ 
			 centerY[x]*Math.cos(angle)+
			 positionY+.5); 
			 }
		g.setColor(Color.white); 
		g.drawPolygon(CoordsX,CoordsY,8); 
	}
}
