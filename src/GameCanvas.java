//import javax.microedition.lcdui.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;
 
// OK this the class where we will draw
public class GameCanvas extends Canvas implements ActionListener, KeyListener{
	int level = 1;
	int numAsteroids = 5;
	Color backCol = Color.BLACK;
	Ship s = new Ship();
	ArrayList<Asteroid> asteroidList = new ArrayList<Asteroid>();
	

    // a flag if repaint in progress (needed if our computation are to long)
    boolean repaintInProgress = false;

    // this is a Canvas but I wont't let the system when to repaint it I will do it myself
    GameCanvas() {
        // so ignore System's paint request I will handle them
        setIgnoreRepaint(true);
        // a random place to start the ball
        // build Chrono that will call me
        Chrono chrono = new Chrono(this);
        // ask the chrono to calll me every 60 times a second so every 16 ms
        new Timer(16, chrono).start();
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
    }
    public boolean newLevel() {
	 	if(asteroidList.size()==0) {
	 		level++;
	 		if(level%5==0)
	 			numAsteroids++;
	 	return true;

	 	}
	 	return false;
 }

    // my own paint method that repaint off line and switch the displayed buffer
    // according to the VBL
    public void myRepaint() {
        // wasting too much time doing the repaint... ignore it
        if(repaintInProgress)

            return;        // so I won't be called 2 times in a row for nothing
        repaintInProgress = true;
        Dimension size = getSize();
        BufferStrategy strategy = getBufferStrategy();
        Graphics graphics = strategy.getDrawGraphics();
        graphics.setColor(backCol);
        graphics.fillRect(0, 0, size.width, size.height);

        s.Height = size.height;
        s.Width = size.width;
        s.update();
        s.draw(graphics);
        if(newLevel()) {
        		for(int x=0; x<numAsteroids; x++) {
        			asteroidList.add(new Asteroid("Large"));
        	}
        }
        for(int x=0;x< asteroidList.size();x++) {
        	asteroidList.get(x).update();
        	asteroidList.get(x).draw(graphics);
        }
        
        for(Bullet b: s.bulletList) {
        	if (b.endDistance >= 0) {
        		b.move(size.width, size.height);
        		b.draw(graphics);
        	}
        }


        if(graphics != null)
        	graphics.dispose();
        // show next buffer
        strategy.show();
        // synchronized the blitter page shown
        Toolkit.getDefaultToolkit().sync();
        // ok I can be called again
        repaintInProgress = false;
    }


    int stop;
    	@Override
	public void actionPerformed(ActionEvent e) {
    		
		myRepaint();
	}
  

    	
    	@Override
    	public void keyPressed(KeyEvent e) {
    		int code = e.getKeyCode();
    		
    		if (code == KeyEvent.VK_N) {
    			backCol = Color.WHITE;
    		}
    		
    		//forward
    		if (code == KeyEvent.VK_UP) {
    			s.setAccel(true);
    		}
    		
    		//left
    		if (code == KeyEvent.VK_LEFT) {
    			s.setLeft(true);
    		}
    		
    		//right
    		if (code == KeyEvent.VK_RIGHT) {
    			s.setRight(true);
    		}

    		if (code == KeyEvent.VK_SPACE) {
    			s.isShooting = true;
    		}
    		
	}

    	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_N) {
			backCol = Color.BLACK;
		}
		if (code == KeyEvent.VK_UP) {
			s.setAccel(false);
		}
		
		//left
		if (code == KeyEvent.VK_LEFT) {
			s.setLeft(false);
		}
		
		//right
		if (code == KeyEvent.VK_RIGHT) {
			s.setRight(false);
		}
		
		if (code == KeyEvent.VK_SPACE) {
			s.shoot();
			s.isShooting = false;
		}
	}

    
}