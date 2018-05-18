import java.awt.List;
import java.util.ArrayList;

public class Ship {
	private String direct;
	private int x;
	private int y;
	

	private static int WIDTH = 20;
	private static int LENGTH = 20;
	
	public Ship(int l) {
		x = 10;
		y = 10;
		this.setDirection("left");
	}
	
	public void move(String direction) {
		
		direct = direction;
		
		if(direct.compareTo("left") == 0) {
				if(x-1 >= 0) {
					x--;
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
			}
			else {
				System.out.println("hit wall");
			}
		}
		else if(direct.compareTo("down") == 0) {
			if(y-1 >= 0) {
				y--;
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

	
	
}
