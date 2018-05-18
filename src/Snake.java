import java.awt.List;
import java.util.ArrayList;

public class Snake {
	private int length;
	private String direct;
	private int x;
	private int y;
	
	ArrayList<Integer> LocationsX = new ArrayList<Integer>();
	ArrayList<Integer> LocationsY = new ArrayList<Integer>();
	ArrayList<Integer> LocationsXCopy;
	ArrayList<Integer> LocationsYCopy;
	
	//LocationsX.add(10);
	//LocationsY.add(10);

	private static int WIDTH = 20;
	private static int LENGTH = 20;
	
	public Snake(int l) {
		length = l;
		//x = (int) (Math.random() * 20);
		//y = (int) (Math.random() * 20);
		x = 10;
		y = 10;
		this.setDirection("left");
		LocationsX.add(x);
		LocationsY.add(y);

	}
	

	
	public void move(String direction) {
		LocationsX.add(0,x);
		LocationsY.add(0,y);
		
		System.out.println("[" + LocationsX.get(0) + "," + LocationsY.get(0) + "]");
		
		direct = direction;
		
		if(direct.compareTo("left") == 0) {
				if(x-1 >= 0) {
					x--;
					//System.out.println(x);
				}
				else {
					System.out.println("hit wall");
				}
				
		}
		else if(direct.compareTo("right") == 0) {
			if(x+1 <= WIDTH) {
				x++;
				//System.out.println(x);
			}
			else {
				System.out.println("hit wall");
			}
		}
		else if(direct.compareTo("up") == 0) {
			if(y+1 <= LENGTH) {
				y++;
				//System.out.println(x);
			}
			else {
				System.out.println("hit wall");
			}
		}
		else if(direct.compareTo("down") == 0) {
			if(y-1 >= 0) {
				y--;
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
	
	public ArrayList<Integer> getLocationsX() {		
		LocationsXCopy= new ArrayList<Integer> (LocationsX.subList(0, length));
		return LocationsXCopy;
	}
	public ArrayList<Integer> getLocationsY() {		
		LocationsYCopy = new ArrayList<Integer> (LocationsY.subList(0, length));
		return LocationsYCopy;
	}
	
	public boolean eatDot() {
		length++;
		return true;
	}
	
}
