import java.awt.BorderLayout;



import javax.swing.*;

 



 

public class GameFrame extends JFrame {

    GameFrame() {

        super("Asteroids");

        GameCanvas canvas = new GameCanvas();

        this.add(canvas, BorderLayout.CENTER);

        this.setSize(600, 400);

        this.setVisible(true);      

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        canvas.createBufferStrategy(2);

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                new GameFrame();

            }

        });

    }

}    