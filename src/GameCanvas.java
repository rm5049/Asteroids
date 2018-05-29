import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import java.awt.image.BufferStrategy;

import java.util.ArrayList;

import java.util.Collections;



import javax.swing.Timer;

 



public class GameCanvas extends Canvas implements ActionListener, KeyListener{

	int level = 1;

	int lives = 3;

	int points=0;

	int numAsteroids = 5;

	boolean paused = false;

	boolean gameOver = false;

	boolean gameStart = true;

	boolean temp = true;

	boolean Controls = false;

	boolean Scores = false;

	boolean mainFrame = false;

	Color backCol = Color.BLACK;

	Ship s = new Ship();

	ArrayList<Integer> scores = new ArrayList<Integer>();

	ArrayList<Asteroid> asteroidList = new ArrayList<Asteroid>();

	double trueTime = 0;

    Font font1 = new Font("Verdana", Font.PLAIN,12);

    Font font2 = new Font("Verdana", Font.PLAIN,50);

    Font font3 = new Font("Verdana", Font.PLAIN,75);

    Font font4 = new Font("Verdana", Font.PLAIN,25);

    boolean repaintInProgress = false;

    

    public GameCanvas() {

        setIgnoreRepaint(true);   

        Chrono chrono = new Chrono(this);

        new Timer(16, chrono).start();

        this.addKeyListener(this);

        this.setFocusable(true);

        this.setFocusTraversalKeysEnabled(false);

    }

    

    public void run() {

    			trueTime += .016;

    			newLevel();

    			asteroidCollisionsUpdate();

    			bulletCollisionsUpdate();

    }

    	public void startBackground() {  

    			if(temp) {

    	 		numAsteroids=15;

    	 		for(int x=0; x<numAsteroids; x++) {

	        		asteroidList.add(new Asteroid());

	        	}

    	 		numAsteroids =5;

    			}

    			temp = false;

    	 	}

     public void newLevel() {

    	 	if(asteroidList.size()==0) {

    	 		level++;

    	 		points+=500;

    	 		if(level%3==0)

    	 			numAsteroids++;

    	 		s.bulletList.clear();

	        	for(int x=0; x<numAsteroids; x++) {

	        		asteroidList.add(new Asteroid());

	        	}

	        }

     }

     

     public void asteroidCollisionsUpdate() {

    	 for(int x=0;x < asteroidList.size();x++) {

    		 Asteroid a = asteroidList.get(x);

    		 if(s.collision(a)) {

    			 points+=100;

    			 lives--;

    			 if(lives==0) {

    				 gameOver = true;

    				 mainFrame = false;

     			scores.add(points);

     			Collections.sort(scores);

     			Collections.reverse(scores);

    			 }

    			 s = new Ship();

    			 

    			 asteroidList = a.collision(asteroidList);

    			 x--;

    		 }

    	 }

     }

     

     public void bulletCollisionsUpdate() {

    	 	int size = asteroidList.size();

    	 for(int x=0;x < asteroidList.size();x++) {

    		 if(size!=asteroidList.size()) {

    			 x--;

    			 size = asteroidList.size();

    		 }

    		 Asteroid a = asteroidList.get(x);

    		 for(int y=0; y<s.bulletList.size();y++) {

    			 Bullet b=s.bulletList.get(y);

    			 if(b.endDistance < 0) {

    				 s.bulletList.remove(y);

    				 y--;

    			 }

    			 else {

    				 if(b.collision(a)) {

    					 points +=150;

    					 s.bulletList.remove(y);

    					 asteroidList = a.collision(asteroidList);

    					 y--; 

    				 }

    			 }

    		 }

    	 }

     }

    public void myRepaint() {

        if(repaintInProgress)

            return;   

        repaintInProgress = true;

        BufferStrategy strategy = getBufferStrategy();

        Graphics graphics = strategy.getDrawGraphics();

        Dimension size = getSize();

        if(gameStart) {

        	startBackground();

        	super.paint(graphics);

        	graphics.setColor(backCol);

        	graphics.fillRect(0, 0, size.width, size.height);

        	 for(int x=0;x < asteroidList.size();x++) {

         		Asteroid a = asteroidList.get(x);

         		a.update();

         		a.draw(graphics);

         }

        	 graphics.setColor(Color.WHITE);

         graphics.setFont(font3);

         graphics.drawString("ASTEROIDS", 75, 150);

         graphics.setFont(font4);

         graphics.drawString("PRESS ENTER TO PLAY", 150, 250);

         graphics.setFont(font1);

         graphics.drawString("PRESS C FOR CONTROLS", 225, 300);

        }

        if(Controls) {

        		super.paint(graphics);

        		graphics.setColor(backCol);

            	graphics.fillRect(0, 0, size.width, size.height);

            	for(int x=0;x < asteroidList.size();x++) {

             		Asteroid a = asteroidList.get(x);

             		a.update();

             		a.draw(graphics);

             }

        		graphics.setColor(Color.WHITE);

            graphics.setFont(font4);

            graphics.drawString("UP ARROW TO ACCELERATE", 125, 75);

            graphics.drawString("RIGHT ARROW TO TURN RIGHT", 100, 125);

            graphics.drawString("LEFT ARROW TO TURN LEFT", 122, 175);

            graphics.drawString("SPACE BAR TO SHOOT", 155, 225);

            graphics.drawString("P TO PAUSE", 230, 275);

            graphics.drawString("E TO EXIT", 240, 325);

            

        }

        if(mainFrame&&!paused) {

        run();

        graphics.setColor(backCol);

        graphics.fillRect(0, 0, size.width, size.height);

        graphics.setColor(Color.WHITE);

        graphics.setFont(font1);

        graphics.drawString("TIME ELAPSED: " +(int)trueTime, 480, 15);

        graphics.drawString("LEVEL: " + (level-1), 5, 15);

        graphics.drawString("POINTS: " + (points-500), 5, 32);

        graphics.drawString("LIVES: " + lives, 5, 49);

        s.update();

        s.draw(graphics);

        for(int x=0;x < asteroidList.size();x++) {

        		Asteroid a = asteroidList.get(x);

        		a.update();

        		a.draw(graphics);

        }

        	for(int y=0; y<s.bulletList.size();y++) {

        		Bullet b=s.bulletList.get(y);

        		b.move();

    			b.draw(graphics);

        	}

        }

        else if(paused){

        	 graphics.setColor(Color.WHITE);

         graphics.setFont(font2);

         graphics.drawString("PAUSED", 200, 200);

        }

        if(gameOver) {

        	super.paint(graphics);

        graphics.setColor(backCol);

        graphics.fillRect(0, 0, size.width, size.height);

        for(int x=0;x < asteroidList.size();x++) {

     		Asteroid a = asteroidList.get(x);

     		a.update();

     		a.draw(graphics);

     }

        if(!Scores) {

        graphics.setColor(Color.WHITE);

        graphics.setFont(font3);

        graphics.drawString("GAME OVER", 75, 180);

        graphics.setFont(font4);

        graphics.drawString("PRESS ENTER FOR MAIN SCREEN", 85, 220);

        graphics.drawString("PRESS H FOR HIGHSCORES", 125, 260);

        }

        else {

        		graphics.setColor(Color.WHITE);

        		graphics.setFont(font2);

        		graphics.drawString("HIGHSCORES", 125, 50 );

            graphics.setFont(font4);

            graphics.drawString("PRESS E TO EXIT", 200, 350);

            for(int x =0;x< scores.size(); x++) {

            graphics.drawString((x+1)+". "+scores.get(x), 250, (30*(x+1))+70);

            }

        }

       }

        

        if(graphics != null)

            graphics.dispose();

        strategy.show();

        Toolkit.getDefaultToolkit().sync();

        repaintInProgress = false;

    }



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

    			s.shoot();

    		}

    		if (code == KeyEvent.VK_P) {

    			if(mainFrame)

    			paused = !paused;

    		}

    		if (code == KeyEvent.VK_H) {

    			if(gameOver) {

    				Scores = true;

    			}

    		}

    		if (code == KeyEvent.VK_ENTER) {

    			if(gameOver) {

    			points = 0;

    			level = 1;

    			lives = 3;

    			trueTime = 0;

    			gameStart = true;

    			gameOver = false;

    			paused=false;

    			asteroidList.clear();

    			temp = true;

    			}

    			else {

    				if(gameStart) {

    				asteroidList.clear();

    				mainFrame = true;

    				}

    				gameStart = false;

    			}

    		}

    		if (code == KeyEvent.VK_C) {

    			if(gameStart) {

    			Controls = true;

    			gameStart = false;

    			}

    		}

    		if (code == KeyEvent.VK_E) {

    			if(Controls) {

    			Controls = false;

    			gameStart = true;

    			}

    			if(Scores) {

    				gameOver = true;

    				Scores = false;

    			}

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

			s.isShooting= 1;

		}
	}
}