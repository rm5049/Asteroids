//import javax.microedition.lcdui.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.Random;
import javax.swing.Timer;
 
// OK this the class where we will draw
public class GameCanvas extends Canvas implements ActionListener, KeyListener{
	
	Color ballCol = Color.YELLOW;
	Color backCol = Color.BLACK;
	Ship s = new Ship();
	
    // the initial position of the ball in the canvas that will be determine randomly
    double ballX = s.getPositionx(), ballY=s.getPositiony();
    // the delta we apply to the x and y position at each repaint
    // here it is set to 1... would should have a larger value in "real" life
    // but for testing purpose that will give you the fastest possible value for a 1 pixel update
    int deltaX = 1, deltaY = 1;
    // the size of the ball in pixels
    final int BALLSIZE = 25;
    // a flag if repaint in progress (needed if our computation are to long)
   boolean repaintInProgress = false;

    // a random object to determine where the initial position of the ball will be
    Random ran = new Random();
    // this is a Canvas but I wont't let the system when to repaint it I will do it myself
    GameCanvas() {
        // so ignore System's paint request I will handle them
        setIgnoreRepaint(true);
        // a random place to start the ball
        ballX = ran.nextInt(580);
        ballY = ran.nextInt(380);      
        // build Chrono that will call me
        Chrono chrono = new Chrono(this);
        // ask the chrono to calll me every 60 times a second so every 16 ms
        new Timer(16, chrono).start();
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
    }
     

    // my own paint method that repaint off line and switch the displayed buffer
    // according to the VBL
    public void myRepaint() {
        // wasting too much time doing the repaint... ignore it
        if(repaintInProgress)

            return;        // so I won't be called 2 times in a row for nothing
        repaintInProgress = true;
        // get actual Canvas size so I can check if I am out of bounds
        Dimension size = getSize();
        // test for all debordement possibilities on the X axis
        if(deltaX > 0) {
            if(ballX > size.width - BALLSIZE)
                deltaX = -deltaX;
        }
        else {
            if(ballX < 0)
                deltaX = -deltaX;
        }

        // check on the Y axis
        if(deltaY > 0) {

            if(ballY > size.height - BALLSIZE)
                deltaY = -deltaY;
        }
        else {
            if(ballY < 0)
                deltaY = -deltaY;
        }
        // update ball position
        ballX += deltaX;
        ballY += deltaY;
        // ok doing the repaint on the not showed page
        BufferStrategy strategy = getBufferStrategy();
        Graphics graphics = strategy.getDrawGraphics();
        // this is for testing purpose you would not do that in real life
        // we change the background color to that you will see that the page are flipped
        // again testing purpose we flip backgound color every repaint
        graphics.setColor(backCol);
        graphics.fillRect(0, 0, size.width, size.height);
        // now we draw the ball
        s.draw(graphics);
    
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
    		s.update();
		this.myRepaint();
	}
    	

    	
    	@Override
    	public void keyPressed(KeyEvent e) {
    		int code = e.getKeyCode();
    		
    		if (code == KeyEvent.VK_B) {
    			ballCol = Color.MAGENTA;
    		}
    		if (code == KeyEvent.VK_N) {
    			backCol = Color.WHITE;
    		}
    		
    		//forward
    		if (code == KeyEvent.VK_UP) {
    			deltaY = Math.abs(deltaY);
    		}
    		
    		//left
    		if (code == KeyEvent.VK_LEFT) {
    			deltaX = Math.abs(deltaX) * -1;
    		}
    		
    		//right
    		if (code == KeyEvent.VK_B) {
    			deltaX = Math.abs(deltaX);
    		}

    		
	}

    	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_B) {
			ballCol = Color.YELLOW;
		}		
		if (code == KeyEvent.VK_N) {
			backCol = Color.BLACK;
		}
	}

    
}
