import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.UIManager;

public class SnakePanel extends JPanel {


	Snake s = new Snake(1);
	private Board board;
	
	Timer timer = new Timer(500,null);
	public static void main(String[] args) {
		try {
			// Set System L&F
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		JFrame frame = new JFrame("Snake!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		SnakePanel sp = new SnakePanel();
		frame.add(sp);
		sp.setPreferredSize(new Dimension(800,600));
		frame.pack();
		frame.setVisible(true);
		sp.setUpKeyMappings();
		sp.startGame();
	}
	
	public SnakePanel(){
		board = new Board();
		this.setPreferredSize(new Dimension(800,800));
		//board.placeDot();
	}
	
	private void setUpKeyMappings() {

		this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),"left");
		this.getActionMap().put("left",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the left arrow is pressed?
				s.setDirection("left");

				//s.move("left");
				System.out.println("Hit left arrow!!");
				//s.move("stop");
			}
		});
		this.requestFocusInWindow();
		
		this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),"right");
		this.getActionMap().put("right",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the left arrow is pressed?
				s.setDirection("right");

				//s.move("right");
				System.out.println("Hit right arrow!!");
				//s.move("stop");
			}
		});
		this.requestFocusInWindow();
		
		this.getInputMap().put(KeyStroke.getKeyStroke("UP"),"up");
		this.getActionMap().put("up",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the left arrow is pressed?
				s.setDirection("up");
				//s.move("up");
				System.out.println("Hit up arrow!!");
				//s.move("stop");
			}
		});
		this.requestFocusInWindow();
		
		this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"),"down");
		this.getActionMap().put("down",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// what do you want to do when the left arrow is pressed?
				s.setDirection("down");
				//s.move("down");
				System.out.println("Hit down arrow!!");
				//s.move("stop");
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
		System.out.println("Timer went off!");
		s.move(s.getDirection());
		board.getSnakeLoc();
		board.placeDot();
		//s.move("left");
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		board.drawBoard(g);
		repaint();
	}
	
}