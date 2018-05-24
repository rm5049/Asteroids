import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class AsteroidBoard {
	Ship ship1 = new Ship();
	public int[][] board = new int[200][200];
	int boardSize = board.length;
	
	
	
	public AsteroidBoard(){
		
	}
	



	public void drawBoard(Graphics g){
		for(int r=0; r<board.length; r++){
			for(int c =0; c<board[0].length; c++){
				if(board[r][c]==0){
					g.setColor(Color.WHITE);
					g.drawRect(r*(boardSize/board.length), c*(boardSize/board.length), boardSize/board.length, boardSize/board.length);
					g.fillRect(r*(boardSize/board.length), c*(boardSize/board.length), boardSize/board.length, boardSize/board.length);
				}
				else if(board[r][c]==1){
					g.setColor(Color.BLACK);
					g.drawRect(r*(boardSize/board.length), c*(boardSize/board.length), boardSize/board.length, boardSize/board.length);
					g.fillRect(r*(boardSize/board.length), c*(boardSize/board.length), boardSize/board.length, boardSize/board.length);
				}
				else{
					g.setColor(Color.RED);
					g.drawRect(r*(boardSize/board.length), c*(boardSize/board.length), boardSize/board.length, boardSize/board.length);
					g.fillRect(r*(boardSize/board.length), c*(boardSize/board.length), boardSize/board.length, boardSize/board.length);
				}
			}
		}
	}
}