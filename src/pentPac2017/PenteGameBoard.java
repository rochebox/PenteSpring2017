package pentPac2017;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class PenteGameBoard extends JPanel implements MouseListener {

	private int bWidthPixels;
	private int bWidthSquares;
	private int bSquareWidth;
	private int currentTurn = PenteMain.BLACKSTONE; // black goes first
	//Color boardSquareColor = new Color(150, 111, 51); // nice woody color..
	
	private Square [] [] theBoard;
	
	//THis is for counting captures
	private int whiteStoneCaptures = 0;
	private int blackStoneCaptures = 0;
	
	Ralph computerMoveGenerator = null;
	boolean playingAgainstRalph = false;
	int ralphStoneColor;
	
	//Timer repaintTimer;
	int howLongToWait = 50;
	boolean waitingForRalph = false;
	

	public PenteGameBoard(int bWPixel, int  bWSquares) {
		// TODO Auto-generated constructor stub
		
		 bWidthPixels = bWPixel-10;
		 bWidthSquares = bWSquares;
		 //compute the width of the b squares...
		 bSquareWidth = (int)(Math.ceil(bWidthPixels/bWidthSquares))+2;
		 
		 this.setSize(bWidthPixels, bWidthPixels );
		 this.setBackground(Color.CYAN);
		 
		 //testSquare = new Square(0, 0, bSquareWidth);
		 
		 theBoard = new Square[bWidthSquares][bWidthSquares];
		 
		 //make the squares
		for(int row = 0 ; row < bWidthSquares; ++row) {
			 for(int col = 0 ; col < bWidthSquares; ++col){
				 theBoard[row][col] = new Square((col*bSquareWidth), (row*bSquareWidth) , 
						 bSquareWidth, row, col);
			 }
		}
		
		
		
		
		
		//set the first stone
		theBoard[(int)(bWidthSquares/2)][(int)(bWidthSquares/2)].setState(PenteMain.BLACKSTONE);
		
		String computerAnswer = JOptionPane.showInputDialog("Hi, would you like to play against the computer? (y or n)");
		if(computerAnswer.equals("Y") ||
				computerAnswer.equals("Yes") ||
				computerAnswer.equals("y") ||
				computerAnswer.equals("Yeah") ||
				computerAnswer.equals("Yaddah")){
		   computerMoveGenerator = new Ralph( this, currentTurn );
		   playingAgainstRalph = true;
		   ralphStoneColor = currentTurn;
		}
		this.changeTurn();
		//Get the MouseListener working
		
		this.addMouseListener(this);
		
		//repaintTimer = new Timer(10, this);
		//repaintTimer.start();
		
	}
	
	//Overriding Method!!! Overides PaintComponent in JPanel
	public void paintComponent(Graphics g){
		
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, bWidthPixels, bWidthPixels);
		
		//testSquare.drawMe(g);
		for(int row = 0 ; row < bWidthSquares; ++row) {
			for(int col = 0 ; col < bWidthSquares; ++ col){
				 theBoard[row][col].drawMe(g);
			 }
		}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("You clicked at [" + e.getX() + ", " + e.getY() + "]");
		playGame(e);
		//repaint();
	}

	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void playGame(MouseEvent e){
		Square s = whichSquareClicked(e.getX(), e.getY());
		if(s.getState() == PenteMain.EMPTY){
			this.doPlay(s);
			repaint();
			this.paintImmediately(0, 0, this.bWidthPixels, this.bWidthPixels);
			//s.setState(currentTurn);
			//this.repaint();
			//this.checkForCaptures(s); // we do want this
			//this.checkForWinOnCaptures();
			//this.checkForWin2(s);  // We need this too!
			//this.changeTurn();

			if(playingAgainstRalph == true && currentTurn == ralphStoneColor){
				
				//waitingForRalph = true;
				//howLongToWait = 1000;
				
				for(int i=0; i<10000; ++i){
					System.out.println("Ralph is Thinking");
				}
				System.out.println("calling computer move generator");
				Square cs = computerMoveGenerator.doComputerMove(s.getRow(), s.getCol());
				//Have the game wait for a second before showing the move
				this.doPlay(cs);
				//repaint();
				//cs.setState(currentTurn);
				//this.repaint();
				//this.checkForCaptures(cs); // we do want this
				//this.checkForWinOnCaptures();
				//this.checkForWin2(cs);  // We need this too!
				//this.changeTurn();
				//this.requestFocus();


			}
			
		}
	}
	
	public void doPlay(Square s){

		s.setState(currentTurn);
		//this.repaint();
		this.checkForCaptures(s); // we do want this
		this.checkForWinOnCaptures();
		this.checkForWin2(s);  // We need this too!
		this.changeTurn();
	}
	
	public Square whichSquareClicked(int mouseX, int mouseY){
		
		Square pickedSquare = null;
		for(int row = 0 ; row < bWidthSquares; ++row) {
			for(int col = 0 ; col < bWidthSquares; ++ col){
				 if(theBoard[row][col].youClickedMe(mouseX, mouseY) == true){
					 pickedSquare = theBoard[row][col];
					 System.out.println("The square is at row/col [" + row + ", " + col + "]");
				 }	 
			 }
		}
		
		return pickedSquare;
		
	}
	
	public void changeTurn(){
		//if currentTurn is blackstone, make it whitestone
		//if currentTurn is whitestone, make it blackstone
		if(currentTurn == PenteMain.BLACKSTONE){
			currentTurn = PenteMain.WHITESTONE;
		} else {
			currentTurn = PenteMain.BLACKSTONE;
		}
	}
	
	public void 	checkForCaptures(Square s){
		
		int sRow = s.getRow();
		int sCol = s.getCol();
		//System.out.println("In checkForCaptures sRow and sCol is [" + sRow + ", " + sCol + "]");
		int theOpposite = this.getTheOppositeState(s);
		//for a right-horizontal-check
		for( int dy = -1 ; dy <= 1; ++ dy){	
			if((dy > 0 && sRow < bWidthSquares - 3) || (dy < 0 && sRow >= 3) || dy == 0){	
			// *** ****** THIS DOES LEFT AND RIGHT
				for( int dx = -1 ; dx <= 1 ; ++ dx) {
					if((dx > 0 && sCol < bWidthSquares - 3) || (dx < 0 && sCol >= 3) || dx == 0){
						// Make sure the Row is first!!!
						if(theBoard[sRow + (1 * dy) ][sCol+ (1 * dx) ].getState() == theOpposite){
							//You are going to have to code from here.
							// You ARE AWESOME AND YOU CAN WRITE THIS CODE!!!
							if(theBoard[sRow + (2 * dy)][sCol+ (2 * dx) ].getState() == theOpposite){
								if(theBoard[sRow + (3 * dy)][sCol+ (3 * dx) ].getState() == currentTurn){
									//System.out.println("We have a Horizontal Capture checking right");
									this.takeStones(sRow + (1 * dy), sCol+ (1 * dx), sRow + (2 * dy), 
											sCol+ (2 * dx), currentTurn);
									//repaint();
								}
							}
						}
					}
		
			} // end of dx loop
		}  //End of sRow edge check
			
	} //end of dy loop
			
	}
	
	public int getTheOppositeState(Square s){
		if (s.getState() == PenteMain.BLACKSTONE){
			return PenteMain.WHITESTONE;
		} else {
			return PenteMain.BLACKSTONE;
		}
	}
	
	public void takeStones(int r1, int c1, int r2, int c2, int taker){
		//this is clear the stones
		theBoard[r1][c1].setState(PenteMain.EMPTY);
		theBoard[r2][c2].setState(PenteMain.EMPTY);
		
		if(taker == PenteMain.BLACKSTONE){
			++blackStoneCaptures;
		} else {
			++whiteStoneCaptures;
		}
		
		//this.checkForWinOnCaptures();
		
	}
	
	public void checkForWinOnCaptures(){
		if(blackStoneCaptures >= 5){
			//there should be some phenomenal win thing...
			JOptionPane.showMessageDialog(null, "Black wins!!! with " +
			blackStoneCaptures + " captures");
		}
		
		if(whiteStoneCaptures >= 5){
			
			String myMessage = "<p style='font-size: 40px;'>White Wins </p>";
			//there should be some phenomenal win thing...
			JOptionPane.showMessageDialog(null, myMessage +"  with " +
			whiteStoneCaptures + " captures");
		}
	}
	
	public void checkForWin2(Square s){
		
		boolean done = false;
		int[] myDys = {-1, 0, 1};
        int whichDy = 0;
  
		while(!done && whichDy < 3){
			if (checkForWinAllInOne(s,myDys[whichDy], 1 ) == true){
				weHaveAWinner();
				done = true;
			}
			whichDy++;
		}
		if(!done){
			if (checkForWinAllInOne(s, 1, 0) == true){
				weHaveAWinner();
			}
		}
		
	}
	
	public boolean checkForWinAllInOne(Square s, int dy, int dx)
	{
		boolean isThereAWin = false;
		int sRow = s.getRow();
		int sCol = s.getCol();
		//System.out.println("In checkForCaptures sRow and sCol is [" + 
		//sRow + ", " + sCol + "]");
		
		//for a right-horizontal-check and left...
		int howManyRight = 0;
		int howManyLeft = 0;
		
		//loop to check right side of where stone s is
		int step = 1;
		//System.out.println("In checkForWinAllInOne sRow and sCol are [" + sRow + ", " + sCol + "]");
		//System.out.println("In checkForWinAllInOne dy and dx are [" + dy + ", " + dx + "]");
		while((sCol + (step * dx) < bWidthSquares) && (sRow + (step * dy) < bWidthSquares) &&
				(sCol + (step * dx) >= 0) && (sRow + (step * dy) >= 0) &&
				(theBoard[sRow + (step * dy)][sCol + (step * dx)].getState() == currentTurn)){
			howManyRight++;
			step++;
		}
		//Moving Left....
		step = 1;
		while((sCol - (step * dx) >= 0) &&  (sRow - (step * dy) >= 0) &&
				(sCol - (step * dx) < bWidthSquares) && (sRow - (step * dy) < bWidthSquares) &&
				(theBoard[sRow - (step * dy)][sCol - (step * dx)].getState() == currentTurn)){
			howManyLeft++;
			step++;
		}
		
		if((howManyRight + howManyLeft + 1) >= 5){
			isThereAWin = true;
		}
		
		return isThereAWin;
	}
		
	
		
		
	
	
	
	public void checkForWin(Square s){
		int sRow = s.getRow();
		int sCol = s.getCol();
		//this.repaint();
		//System.out.println("In checkForCaptures sRow and sCol is [" + sRow + ", " + sCol + "]");
		//for a right-horizontal-check
		for( int dy = -1 ; dy <= 1; ++ dy){	
			if((dy > 0 && sRow < bWidthSquares - 4) || (dy < 0 && sRow >= 4) || dy == 0){	
			// *** ****** THIS DOES LEFT AND RIGHT
				for( int dx = -1 ; dx <= 1 ; ++ dx) {
					if(!(dx==0 && dy==0)){
					if((dx > 0 && sCol < bWidthSquares - 4) || (dx < 0 && sCol >= 4) || dx == 0){
						// Looking for 4 more squares like the current player!!!
						//System.out.println("Checking sRow " + sRow + " sCol " + sCol + " dy is " +
						//dy + " and dx is " + dx);
						if(theBoard[sRow + (1 * dy) ][sCol+ (1 * dx) ].getState() == currentTurn){
							//System.out.println("FoundSecondStone at " + 
									//(sRow + (1 * dy))  + (sCol+ (1 * dx)) );
							if(theBoard[sRow + (2 * dy)][sCol+ (2 * dx) ].getState() == currentTurn) 
								if(theBoard[sRow + (3 * dy)][sCol+ (3 * dx) ].getState() == currentTurn)
									if(theBoard[sRow + (4 * dy)][sCol+ (4 * dx) ].getState() == currentTurn)
										this.weHaveAWinner();
										//repaint();
									}				
						
					}	
					}
				} // end of dx loop
			}  //End of sRow edge check
		} //end of dy loop
	}


	public void weHaveAWinner(){
		String theWinner = null;
		if( currentTurn == PenteMain.BLACKSTONE ){
			 theWinner = "Blackstone Player!!!";
		} else {
			 theWinner = "Whitestone Player!!!";
		}
		
		JOptionPane.showMessageDialog(null, "Congratulations " + theWinner + " You Won!!! Great Job");
				
	}
	
	
	public int getBoardWidthInSquares(){
		return bWidthSquares;
	}
	
	public Square[][] getActualGameBoard(){
		return theBoard;
	}

	
	/*
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Hi from action performed");
		System.out.println("Waiting for Ralph is: " + waitingForRalph);
		System.out.println("How Long to wait is:  " + howLongToWait);
		
		if(waitingForRalph == true && howLongToWait > 0){
			howLongToWait--;
			
		} else {
			repaint();
			howLongToWait = 50;
			waitingForRalph = false;
		}
		
	}
	
	*/
}
		
	
		
	




