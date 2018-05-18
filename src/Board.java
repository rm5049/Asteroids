import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class Board {
	Snake snake = new Snake(1);
	public int[][] board = new int[20][20];
	int boardSize = 800;
	public Board(){
		getSnakeLoc();
		placeDot();
	}
	public void getSnakeLoc(){
		ArrayList<Integer> xLoc = snake.getLocationsX();
		ArrayList<Integer> yLoc = snake.getLocationsY();
		for(int r: yLoc) {
			for (int c: xLoc) {
				board[r][c] = 1;
			}
		}
	}
	
	public void placeDot(){
		if(snake.eatDot()){
			int randx = (int) (Math.random() * 20);
			int randy = (int) (Math.random() * 20);
			while(board[randx][randy] == 1){
				randx = (int) (Math.random() * 20);
				randy = (int) (Math.random() * 20);
			}
			board[randx][randy] = 2;
		}

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