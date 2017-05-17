package pentPac2017;

import java.awt.Color;
import java.awt.Graphics;


public class Square {

	private int xLoc, yLoc;  // top left corner position of square on board
	private int sWidth;  //This is the width of the square also the height
	private Color boardSquareColor = new Color(255, 209, 26); // more golden
	private Color squareOutlineColor = new Color(255, 219, 26); // more golden
	private Color crossHairColor = new Color(150, 111, 51); // nice woody color..
	private int squareState = PenteMain.EMPTY;
	
	//This is added for captures
	private int myRow, myCol;

	public Square(int x, int y, int w, int r, int c) {
		// TODO Auto-generated constructor stub	
		xLoc = x;
		yLoc = y;
		sWidth = w;
		myRow = r;
		myCol = c;
	}
	
	public void drawMe(Graphics g){
		// Draw a solid square
		g.setColor(boardSquareColor);
		g.fillRect(xLoc, yLoc, sWidth, sWidth);
		
		// Cross Hair
		g.setColor(crossHairColor);
		g.drawLine(xLoc + (int)(sWidth/2), yLoc, xLoc + (int)(sWidth/2), yLoc + sWidth);
		
		//You make the other cross hair!!!
		g.drawLine(xLoc, yLoc + (int)(sWidth/2), xLoc + sWidth, yLoc + (int)(sWidth/2));
		
		//g.setColor(Color.RED);
		g.setColor(squareOutlineColor);
		g.drawRect(xLoc, yLoc, sWidth, sWidth);
		
		
		if(squareState == PenteMain.BLACKSTONE){
			g.setColor(Color.BLACK);
			g.fillOval(xLoc+3, yLoc+3, sWidth-6, sWidth-6);
		}
		
		if(squareState == PenteMain.WHITESTONE){
			g.setColor(Color.WHITE);
			g.fillOval(xLoc+3, yLoc+3, sWidth-6, sWidth-6);
		}
	}
	
	public void setState(int newState){
		squareState= newState;
	}
	
	public int getState(){
		return squareState;
	}
	
	//Adding accessor methods for captures..
	public int getRow(){
		return myRow;
	}
	
	public int getCol(){
		return myCol;
	}
	
	public boolean youClickedMe(int mouseX, int mouseY){
		boolean didYouClickMe = false;
		
		if(mouseX > xLoc && mouseX < xLoc+ sWidth){
			if(mouseY > yLoc && mouseY < yLoc+ sWidth){
				didYouClickMe = true;
			}
		}
		return didYouClickMe;
		
	}


}
