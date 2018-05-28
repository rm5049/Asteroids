import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.UIManager;

public class AsteroidsPanel extends JPanel {


	Ship s = new Ship();
	private AsteroidBoard asteroidboard;
	private JPanel panel;
	
	Timer timer = new Timer(1,null);
	public static void main(String[] args) {
		try {
			// Set System L&F
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		JFrame frame = new JFrame("Asteroids!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		AsteroidsPanel sp = new AsteroidsPanel();
		frame.add(sp);
		sp.setPreferredSize(new Dimension(800,800));
		frame.pack();
		frame.setVisible(true);
		sp.setUpKeyMappings();
		sp.startGame();
	}
	
	public AsteroidsPanel(){
		asteroidboard = new AsteroidBoard();
		this.setPreferredSize(new Dimension(800,800));
		//board.placeDot();
	}
	
	private void setUpKeyMappings() {

		this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),"left");
		this.getActionMap().put("left",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the left arrow is pressed?
				//System.out.println("Hit left arrow!!");
				s.turnLeft();
			}
		});
		this.requestFocusInWindow();
		
		this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),"right");
		this.getActionMap().put("right",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the right arrow is pressed?
				//System.out.println("Hit right arrow!!");
				s.turnRight();
			}
		});
		this.requestFocusInWindow();
		
		this.getInputMap().put(KeyStroke.getKeyStroke("UP"),"up");
		this.getActionMap().put("up",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the up arrow is pressed?
				//System.out.println("Hit up arrow!!");
				s.accelerate();
			}
		});
		this.requestFocusInWindow();
		
		this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"),"down");
		this.getActionMap().put("down",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the left arrow is pressed?
			}
		});
		this.requestFocusInWindow();
		
	}
		
	private void startGame() {
		timer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				tick();
			}
			
		});
		timer.start();
	}
	
	protected void tick() {
		//System.out.println("Timer went off!");
		s.update();
		repaint();
	}
	
	@Override
	public void paint(Graphics g){
		super.paintComponent(g);
		asteroidboard.drawBoard(g);
		s.draw(g);
		repaint();
	}
	
}