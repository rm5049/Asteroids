import java.awt.Color;

import java.awt.Graphics;

import java.util.ArrayList;

import java.util.List;



public class Asteroid implements Drawable{

	

	private String size;

	private double angle;

	private double Height = 400;

	private	double Width = 600;

	private double positionX=300, positionY=200;

	private double speed = 1;

	private double radius;

	int[] CoordsX, CoordsY;

	double [] centerX, centerY;

	double[] X={0,-10,-8,-10,0,10,8,10},Y={-8,-10,0,10,8,10,0,-10};

	public Asteroid() {

		size = "Large";

		angle = Math.random() * (Math.PI*2);

		while(positionX<(Width*5/7) && positionX>(Width*2/7)) {

		positionX = (int)(Math.random() * Width);

		}

		while(positionY<(Height*5/7) && positionY>(Height*2/7)) {

			positionY = (int)(Math.random() * Height);

			}

		centerX=new double[8]; 

		centerY=new double[8];

		CoordsX= new int[8];

		CoordsY = new int[8];

		speed =.5;

		for(int x= 0; x<8; x++) {

			centerX[x] = X[x]*3;

			centerY[x] = Y[x]*3;

			}

		radius = 30;	

	}

	public Asteroid(String Size, double px, double py) {

		size = Size;

		angle = Math.random() * (Math.PI*2);

		positionX = px;

		positionY = py;

		centerX=new double[8]; 

		centerY=new double[8];

		CoordsX= new int[8];

		CoordsY = new int[8];

		if (this.size.equals("Medium")) {

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

	public String getSize() {

		return size;

	}

	public ArrayList<Asteroid> collision(ArrayList<Asteroid> temp) {

		double x1=getPositionx();

		double y1=getPositiony();

		String Size  = getSize();

		temp.remove(this);

		if(Size.equals("Large")) {

			temp.add(new Asteroid("Medium",x1,y1));

			temp.add(new Asteroid("Medium",x1,y1));

		}

		else if(Size.equals("Medium")){

			temp.add(new Asteroid("Small",x1,y1));

			temp.add(new Asteroid("Small",x1,y1));

		}

		return temp;

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