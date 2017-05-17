package pentPac2017;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class RalphHelper  {
	
	
	// Ralph is the computer move generator for the pente game
	private PenteGameBoard myBoard;
	private int myStoneColor, opponentStoneColor;
	private int boardWidthSquares;
	private Square[][] theGameBoard;
	
	private boolean timeToMakeAMove = false;
	private boolean moveToMake = false;
	private int moveToDealWithRow;
	private int moveToDealWithCol;
	
	//Big addition for opponent groups..
 	private ArrayList<OpponentGroup> groups4 = new ArrayList<OpponentGroup>();
	private ArrayList<OpponentGroup> groups3 = new ArrayList<OpponentGroup>();
	private ArrayList<OpponentGroup> groups2 = new ArrayList<OpponentGroup>();
	private ArrayList<OpponentGroup> groups1 = new ArrayList<OpponentGroup>();
	
	

	public RalphHelper(PenteGameBoard b, int stoneColor) {
		// TODO Auto-generated constructor stub
		myBoard = b;	
		myStoneColor = stoneColor;
		this.setOpponentStoneColor();
		boardWidthSquares = b.getBoardWidthInSquares();
	    theGameBoard = b.getActualGameBoard();
	    //JOptionPane.showMessageDialog(null, "Hi, Ralph here. Ready to play!");   
	}
	
	
	
	
	
	public void setOpponentStoneColor(){
		if( myStoneColor == PenteMain.BLACKSTONE){
			opponentStoneColor = PenteMain.WHITESTONE;
		} else {
			opponentStoneColor = PenteMain.BLACKSTONE;
		}
	}
	
	public Square doComputerMove(int lastMoveRow, int lastMoveCol ){	
		
		//System.out.println("in doMove about to call assess board");
		this.assessBoard( lastMoveRow,  lastMoveCol);  //To use opponent groups...
        //System.out.println("Back from assess board ready to move");
		
		
		Square nextMove = null;
		nextMove = this.blockItEverybody(groups4, 4);  //You Write this method..
		if(nextMove == null){
			nextMove = this.blockItEverybody(groups3, 3);
			if(nextMove == null){
				nextMove = captureATwo();  //This is my special capture method for  2's
				if(nextMove == null){
					nextMove = this.blockItEverybody(groups2, 2);
					if(nextMove == null){
						nextMove = this.makeARandomMove();
					}
							
							
				}
						
						
			}
		}
		
		
		return nextMove;
	}	
	
	
	public Square captureATwo(){
		//*** THis looks only at the two groups
		Square nextMove = null;
		
		if(groups2.size() > 0){
			boolean done = false;
			int groupIndex = 0;
			
			while(!done && groupIndex < groups2.size()){
				 OpponentGroup currentGroup = groups2.get(groupIndex);
				 Square e1 = groups2.get(groupIndex).getEnd1Square();
				 Square e2 = groups2.get(groupIndex).getEnd2Square();
				 
				 System.out.println("At top of loop in captureATwo and groupIndex is " + groupIndex);
				 
				 groupIndex++;
				 
				 //Your Code Here
				 if((e1 != null && e1.getState() == this.myStoneColor) && (e2 != null && e2.getState() == PenteMain.EMPTY)){
					 nextMove = e2;
				 } else {
					 if((e2 != null && e2.getState() == this.myStoneColor) && (e1 != null && e1.getState() == PenteMain.EMPTY)){
						 nextMove = e1;
					 } 
				 }
				 
			}	
		}	
		return nextMove;
	}
	
	public Square blockItEverybody(ArrayList<OpponentGroup> whatGroup, int whatGroupSize){
		
		Square nextMove = null;
		//Your code here
		System.out.println("In BlockItEveryBody for this group the size is " + whatGroup.size() );
		
		if(whatGroup.size() > 0){
			
			boolean done = false;
			int groupIndex = 0;
			
			while(!done && groupIndex < whatGroup.size()){
				 OpponentGroup currentGroup = whatGroup.get(groupIndex);
				 Square e1 = whatGroup.get(groupIndex).getEnd1Square();
				 Square e2 = whatGroup.get(groupIndex).getEnd2Square();
			
				 System.out.println("At top of loop in BlockIt and groupIndex is " + groupIndex);
				 
				 groupIndex++;
		
				 if(currentGroup.getInMiddleGroupStatus() == true){
					 nextMove= currentGroup.getInMiddleGroupSquare();
				 } else {
					  
					 // So here we now know that e1 is empty...
					 if((e1 != null && e1.getState() == PenteMain.EMPTY ) && (e2 != null && e2.getState() == PenteMain.EMPTY )){
						 System.out.println("We have found for this opponent group both e1 and e2 are empty");
						 int r = (int)(Math.random() * 100);
						 if(r > 50){
							 nextMove = e1;
						 } else {
							 nextMove = e2;
						 }
						 done=true;
					 }else {
						nextMove = null;
						System.out.println("Special check for 3's what group size is " + whatGroupSize );
						if(whatGroupSize == 4 || whatGroupSize == 2){
						 
							 if (e1 != null && e1.getState() == PenteMain.EMPTY) {
								 System.out.println("e1 is empty in blockIt");
								 nextMove = e1;
								 done = true;
								 
							 } else {
								 if(e2 != null && e2.getState() == PenteMain.EMPTY){
									 System.out.println("e2 is empty in blockIt");
									 nextMove = e2;
									 done = true;
								 }
							 }
						 
						} else {
							System.out.println("Hi Skipping the move because its a 3 next move is " + nextMove);
						}
						 
					 }
				 } 
			}
		}
		
		System.out.println("about to return nextMove from blockEverybody and the move is " + nextMove);
		return nextMove;
		
	}
	
	public Square specialProcessingForThree(OpponentGroup g){
		
		Square squareToReturn = null;
		Square e1 = g.getEnd1Square();
		Square e2 = g.getEnd2Square();
		
		
		
		
		
		return squareToReturn;
		
	}
	
	
	
	
	
	
	
	
	public Square blockIt(ArrayList<OpponentGroup> whatGroup){
		
		Square nextMove = null;
		//Your code here
		System.out.println("In BlockIt for this group the size is " + whatGroup.size() );
		
		if(whatGroup.size() > 0){
			
			boolean done = false;
			int groupIndex = 0;
			
			while(!done && groupIndex < whatGroup.size()){
				 OpponentGroup currentGroup = whatGroup.get(groupIndex);
				 Square e1 = whatGroup.get(groupIndex).getEnd1Square();
				 Square e2 = whatGroup.get(groupIndex).getEnd2Square();
			
				 System.out.println("At top of loop in BlockIt and groupIndex is " + groupIndex);
				 
				 groupIndex++;
				 
				 
				 if(currentGroup.getInMiddleGroupStatus() == true){
					 nextMove = currentGroup.getInMiddleGroupSquare();
					 
				 } else {
				 
					  
					 // So here we now know that e1 is empty...
					 if((e1 != null && e1.getState() == PenteMain.EMPTY ) && (e2 != null && e2.getState() == PenteMain.EMPTY )){
						 System.out.println("We have found for this opponent group both e1 and e2 are empty");
						 int r = (int)(Math.random() * 100);
						 if(r > 50){
							 nextMove = e1;
						 } else {
							 nextMove = e2;
						 }
						 done=true;
					 }else {
						 
						 if (e1 != null && e1.getState() == PenteMain.EMPTY) {
							 System.out.println("e1 is empty in blockIt");
							 nextMove = e1;
							 done = true;
							 
						 } else {
							 if(e2 != null && e2.getState() == PenteMain.EMPTY){
								 System.out.println("e2 is empty in blockIt");
								 nextMove = e2;
								 done = true;
							 }
						 }
						 
					 }
				 }
			}
		}
		
		
		return nextMove;	
		
	}
	
	
	//You are programming these.......
	public Square blockAFourAtHome(){
		System.out.println("HI in new blockAFour- 4group has size " +  groups4.size());
		return blockIt(groups4);	
	}
	
	
	public Square blockAThree(){
		System.out.println("Hi in new blockAThree- 3group has size " +  groups3.size());
		
		Square nextMove = null;
		
		if(groups3.size() > 0){
			
			boolean done = false;
			int groupIndex = 0;
			
			while(!done && groupIndex < groups3.size()){
				 OpponentGroup currentGroup = groups3.get(groupIndex);
				 Square e1 = groups3.get(groupIndex).getEnd1Square();
				 Square e2 = groups3.get(groupIndex).getEnd2Square();
			
				 System.out.println("At top of loop in BlockAThreeAtHome and groupIndex is " + groupIndex);
				 
				 groupIndex++;
				 
				 
				 if(currentGroup.getInMiddleGroupStatus() == true){
					 nextMove = currentGroup.getInMiddleGroupSquare();
					 
				 } else {
				 
					  
					 // So here we now know that e1 is empty...
					 if((e1 != null && e1.getState() == PenteMain.EMPTY ) && (e2 != null && e2.getState() == PenteMain.EMPTY )){
						 System.out.println("We have found for this opponent group both e1 and e2 are empty");
						 int r = (int)(Math.random() * 100);
						 if(r > 50){
							 nextMove = e1;
						 } else {
							 nextMove = e2;
						 }
						 done=true;
					 }else {
						 
						 if (e1 != null && e1.getState() == PenteMain.EMPTY) {
							 System.out.println("e1 is empty in blockIt");
							 nextMove = e1;
							 done = true;
							 
						 } else {
							 if(e2 != null && e2.getState() == PenteMain.EMPTY){
								 System.out.println("e2 is empty in blockIt");
								 nextMove = e2;
								 done = true;
							 }
						 }
						 
					 }
				 }
			}
		}
		
		
		return nextMove;	
	}
		
		


		
		
	
	
	
//NEW METHODS TO MAKE RALPH SMARTER 
// 1 -- ASSESS THE BOARD FOR OPPONENT GROUPS
public void assessBoard( int lastMoveRow, int lastMoveCol){
		
	groups4.clear();
	groups3.clear(); 
	groups2.clear();
	groups1.clear();
		
	this.doInMiddleCheck(4);
	this.doInMiddleCheck(3);
	this.lookForGroupsHorizontally(  lastMoveRow,  lastMoveCol);
	this.lookForGroupsVertically( lastMoveRow,  lastMoveCol );
	this.lookForGroupsDiagRightInClass( lastMoveRow,  lastMoveCol );
	this.lookForGroupsDiagLeftFromClass( lastMoveRow, lastMoveCol);
	
		
}
	
	//2A -- LOOK FOR GROUPS HORIZONTALLY  ************
public void lookForGroupsHorizontally( int lastMoveRow, int lastMoveCol){
		//Amazing code will go here.....
		int curCol;
		for( int row = 0 ; row < boardWidthSquares; ++ row){	
			curCol = 0;
			while(curCol < boardWidthSquares){
			//Step 1 skip over stones until you find an opponent stone
			Square newStart = findOpponentStartHorizontal( row,  curCol);
			if(newStart != null ){
				//Now I can do stuff
				//  Make an object group
				OpponentGroup newGroup = new OpponentGroup( OpponentGroup.HORIZONTAL_GROUP);
				// Add add stone to array
				newGroup.addSquareToGroup(newStart);
				// Check Edge
				int startRow = newStart.getRow();
				int startCol = newStart.getCol();
				if( startCol <= 0){
					newGroup.setEnd1Square(null);
				} else {
					newGroup.setEnd1Square(theGameBoard[startRow][startCol-1]);
				}
				
				// Check to see if the current player move is this stone
				if( startRow == lastMoveRow && startCol == lastMoveCol){
					newGroup.setCurrentMoveIsInThisGroup(true);
					newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength()-1);
				}
				
				// Start getting neighbors
				startCol++;
				while(startCol < boardWidthSquares  && 
						theGameBoard[startRow][startCol].getState() == this.opponentStoneColor){
						newGroup.addSquareToGroup(theGameBoard[startRow][startCol]);
						if( startRow == lastMoveRow && startCol == lastMoveCol){
							newGroup.setCurrentMoveIsInThisGroup(true);
							newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength()-1);
						}
						
						startCol++;  // Mr. Roche is a chicken but lives safely...
				}
				
				// Set the second edge  this is abridged from first edge
				if( startCol >= boardWidthSquares){
					newGroup.setEnd2Square(null);
				} else {
					newGroup.setEnd2Square(theGameBoard[startRow][startCol]);
				}
				curCol = startCol;
				
				//Finally add this to the group list
				this.addNewGroupToGroupLists(newGroup);
				
			} else {
				// If startSquare is null
				// this forces it to end the loop
				curCol = boardWidthSquares;
				
			}
		}	
	}	
}
	
	
	
	
	// 2B-- FIND A HORIZONTAL START GROUP
public Square findOpponentStartHorizontal(int whatRow, int whatCol){
		
		Square opponentStart = null;
		//Lots of good code....
		boolean done = false; 
		int currentCol = whatCol;
		
		while( !done && currentCol < boardWidthSquares ){
			if(theGameBoard[whatRow][currentCol].getState() == this.opponentStoneColor){
				opponentStart = theGameBoard[whatRow][currentCol];
				done = true;	
			}
			currentCol++;
		}
		return opponentStart;
	}
	
	
	
	
	// 3 -- ADD ANY GROUP TO A LIST
	private void addNewGroupToGroupLists(OpponentGroup ng){
		System.out.println("In addNewGroup opponent group length is " + ng.getGroupLength());
		switch(ng.getGroupLength()){
		
			case 1:
				
				groups1.add(ng);
				break;
				
			case 2:
				System.out.println("I have an " + ng.getGroupTypeText() + 
						" Group with two opponent stones");
				groups2.add(ng);
				break;
				
			case 3:
				System.out.println("I have an " + ng.getGroupTypeText() + 
						" Group with three opponent stones");
				groups3.add(ng);
				break;	
				
			case 4:
				System.out.println("I have an " + ng.getGroupTypeText() + 
						" Group with four opponent stones");
				groups4.add(ng);
				break;		
			default:
				System.out.println("I have an " + ng.getGroupTypeText() + 
						" Group with " + ng.getGroupLength() + " opponent stones");
				System.out.println("Something is really messed up.");
				break;
		
		}	
	}
	
	

	
	
	
	
	
	public void lookForGroupsVertically( int lastMoveRow, int lastMoveCol){
		
		for( int col = 0; col < boardWidthSquares; ++col ){
			
			int curRow = 0;
			
			while(curRow < boardWidthSquares){
				//Find the start of an opponent group
				Square groupStart = findOpponentStartVertical( curRow,  col );
				
				//if there is a start find make an opponent group and set the first edge
				if(groupStart != null){
					//System.out.println("Hi found a start at " + groupStart.getRow() + 
					//		", " + groupStart.getCol());
					OpponentGroup newGroup = new OpponentGroup(OpponentGroup.VERTICAL_GROUP);
					newGroup.addSquareToGroup(groupStart);
					int startRow = groupStart.getRow();
					int startCol = groupStart.getCol();
					// ********* add check for group with current move ********
						if(startRow <= 0){
							newGroup.setEnd1Square(null);
						} else {
							newGroup.setEnd1Square(theGameBoard[startRow-1][col]);
							
						}
						
					curRow = groupStart.getRow() + 1;
					
				//load the Squares of the opponent group until there is an end
					boolean done = false;
					while(curRow < boardWidthSquares && !done){
						if(theGameBoard[curRow][col].getState() == this.opponentStoneColor){
							//this method in squareGroup handles adding length etc...
							newGroup.addSquareToGroup(theGameBoard[curRow][col]);
							curRow++;
						} else {
							done=true;
						}
					}

				// if there is an end set the end edge
					if(curRow >= boardWidthSquares){
						newGroup.setEnd2Square(null);
					} else {
						newGroup.setEnd2Square(theGameBoard[curRow][col]);
					}
				
				// based on the length of the opponent group add it to the lists.
					this.addNewGroupToGroupLists(newGroup);
					
				
				} else {
					curRow = boardWidthSquares;
				}
			}
		}
	}
		
		
public Square findOpponentStartVertical(int whatRow, int whatCol){
		
		//System.out.println(" Hello from start of findOpponentStartVertical");
		Square opponentStart = null;
		boolean done = false; 
		int currentRow = whatRow;
		
		while( !done && currentRow < boardWidthSquares ){
			if(theGameBoard[currentRow][whatCol].getState() == this.opponentStoneColor){
				opponentStart = theGameBoard[currentRow][whatCol];
				done = true;	
			}
			currentRow++;
		}
		
		//System.out.println(" Hello from bottom of findOpponentStartVertical just about to return a start square");
		return opponentStart;
}

public void lookForGroupsDiagRightInClass( int lastMoveRow, int lastMoveCol ){
	//Do Part 1 of the Diagonal...
	for(int row = 0 ; row < boardWidthSquares; ++row ){
		int curCol = 0;
		int curRow = row;
		while(curCol < boardWidthSquares - row && curRow < boardWidthSquares) {
			
			Square groupStart = findOpponentDiagRight1( curRow,  curCol, 0);
			
			if( groupStart != null ) {	
				//You have a start so set up a new group!
				//System.out.println ("Hi I found a group start at " + groupStart.getRow() + ", " + groupStart.getCol() );
				OpponentGroup newGroup = new OpponentGroup(OpponentGroup.DIAG_RIGHT_GROUP);
				newGroup.addSquareToGroup(groupStart);
				int startRow = groupStart.getRow();
				int startCol = groupStart.getCol();
				
				// Check first edge
				if(startRow - 1 >= 0 && startCol - 1 >= 0) {
					newGroup.setEnd1Square(theGameBoard[startRow-1][startCol-1]);
				} else {
					newGroup.setEnd1Square(null);
					
				}
				//see if current move is part of this group
				if( startRow == lastMoveRow && startCol == lastMoveCol ){
					newGroup.setCurrentMoveIsInThisGroup(true);
					newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength());
				}
				
				//look for additional group members
				startCol++;
				startRow++;
				boolean done = false;
				
				// Loop to collects the length opponent stones
				while( startCol < boardWidthSquares - row && startRow < boardWidthSquares && !done){
					if(theGameBoard[startRow][startCol].getState() == this.opponentStoneColor ) {
						newGroup.addSquareToGroup(theGameBoard[startRow][startCol]);		
						if( startRow == lastMoveRow && startCol == lastMoveCol ){
							newGroup.setCurrentMoveIsInThisGroup(true);
							newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength());
						}
	
						startRow++;
						startCol++;
					} else {
						done = true;
					}	
				}	
				//check other edge 
				if(startRow  < boardWidthSquares && startCol < boardWidthSquares) {
					newGroup.setEnd2Square(theGameBoard[startRow][startCol]);
				} else {
					newGroup.setEnd2Square(null);
					
				}
				
				//Important to stop infinite loop
				curCol = startCol;
				curRow = startRow;
				//add group to list
				this.addNewGroupToGroupLists(newGroup);
				
			} else {
				//get out of loop!!
				curRow = boardWidthSquares;
			}	
		}
		
	}
	
	
	
	//Do Part 2 of the Diagonal
	for(int col = 1 ; col < boardWidthSquares; ++col ){
		
		int curCol = col;
		int curRow = 0;
		
		while(curRow < boardWidthSquares - col && curCol < boardWidthSquares) {
			
			Square groupStart = findOpponentDiagRight1( curRow,  curCol, 0);
			
			if(groupStart != null){
				
				//System.out.println ("Hi I found a group start at " + groupStart.getRow() + ", " + groupStart.getCol() );
				OpponentGroup newGroup = new OpponentGroup(OpponentGroup.DIAG_RIGHT_GROUP);
				newGroup.addSquareToGroup(groupStart);
				int startRow = groupStart.getRow();
				int startCol = groupStart.getCol();
				
				// Check first edge  same problem so same code from above should work...
				if(startRow - 1 >= 0 && startCol - 1 >= 0) {
					newGroup.setEnd1Square(theGameBoard[startRow-1][startCol-1]);
				} else {
					newGroup.setEnd1Square(null);
					
				}
				//see if current move is part of this group
				if( startRow == lastMoveRow && startCol == lastMoveCol ){
					newGroup.setCurrentMoveIsInThisGroup(true);
					newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength());
				}
				
				//look for additional group members
				startCol++;
				startRow++;
				boolean done = false;
				
				// this loop in part 2 collect the length of the opp group
				while( startCol < boardWidthSquares  && startRow < boardWidthSquares-col && !done){
					if(theGameBoard[startRow][startCol].getState() == this.opponentStoneColor ) {
						newGroup.addSquareToGroup(theGameBoard[startRow][startCol]);		
						if( startRow == lastMoveRow && startCol == lastMoveCol ){
							newGroup.setCurrentMoveIsInThisGroup(true);
							newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength());
						}
	
						startRow++;
						startCol++;
					} else {
						done = true;
					}		
				}
				
				//check other edge
				if(startRow  < boardWidthSquares && startCol  < boardWidthSquares) {
					newGroup.setEnd2Square(theGameBoard[startRow][startCol]);
				} else {
					newGroup.setEnd2Square(null);
					
				}
				
				//Important to stop infinite loop
				curCol = startCol;
				curRow = startRow;
				//add group to list
				this.addNewGroupToGroupLists(newGroup);
				
				
			} else {
				
				//get out of loop
				curCol = boardWidthSquares;
				
			}
		}
	}	
}




public Square findOpponentDiagRight1( int whatRow, int whatCol, int r ){
	
	Square opponentStart = null;
	boolean done = false; 
	int currentCol = whatCol;
	int currentRow = whatRow;
	
	while( !done && currentCol < boardWidthSquares-r && currentRow < boardWidthSquares){
		if(theGameBoard[currentRow][currentCol].getState() == opponentStoneColor ){
			opponentStart = theGameBoard[currentRow][currentCol];
			done = true;
		}
		currentRow++;
		currentCol++;	
	}
	return opponentStart;
}
	
	
// *** second version of diag left from class model

public void lookForGroupsDiagLeftFromClass( int lastMoveRow, int lastMoveCol ){
	//Do Part 1 of the Diagonal...
	for(int row = 0 ; row < boardWidthSquares; ++row ){
		int curCol = boardWidthSquares-1; 
		int curRow = row;
		//while(curCol < boardWidthSquares - row && curRow < boardWidthSquares) {
		while(curCol >= row  && curRow < boardWidthSquares) { 
			
			Square groupStart = findOpponentStartDiagLeft( curRow,  curCol);
			
			if( groupStart != null ) {	
				//You have a start so set up a new group!
				//System.out.println ("Hi I found a group start at " + groupStart.getRow() + ", " + groupStart.getCol() );
				OpponentGroup newGroup = new OpponentGroup(OpponentGroup.DIAG_LEFT_GROUP);
				newGroup.addSquareToGroup(groupStart);
				int startRow = groupStart.getRow();
				int startCol = groupStart.getCol();
				
				// Check first edge
				if(startRow - 1 >= 0 && startCol + 1 < boardWidthSquares) {
					newGroup.setEnd1Square(theGameBoard[startRow-1][startCol+1]);
				} else {
					newGroup.setEnd1Square(null);
					
				}
				//see if current move is part of this group
				if( startRow == lastMoveRow && startCol == lastMoveCol ){
					newGroup.setCurrentMoveIsInThisGroup(true);
					newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength());
				}
				
				//look for additional group members
				startCol--;  // startCol[__];
				startRow++;
				boolean done = false;
				//Loop to collect length of Opponent Group
				while( startCol >= row && startRow < boardWidthSquares && !done){
					if(theGameBoard[startRow][startCol].getState() == this.opponentStoneColor ) {
						newGroup.addSquareToGroup(theGameBoard[startRow][startCol]);		
						if( startRow == lastMoveRow && startCol == lastMoveCol ){
							newGroup.setCurrentMoveIsInThisGroup(true);
							newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength());
						}
	
						startRow++;
						startCol--;
					} else {
						done = true;
					}	
				}	
				//check other edge
				if(startRow  < boardWidthSquares && startCol >= 0) {
					newGroup.setEnd2Square(theGameBoard[startRow][startCol]);
				} else {
					newGroup.setEnd2Square(null);
					
				}
				
				//Important to stop infinite loop
				curCol = startCol;
				curRow = startRow;
				//add group to list
				this.addNewGroupToGroupLists(newGroup);
				
			} else {
				//get out of loop!!
				curRow = boardWidthSquares;
				curCol = row-1; 
			}	
		}	
	}
	
	//System.out.println("Start of second part of diagonal");
	//Do Part 2 of the Diagonal
	for(int col = boardWidthSquares-2 ; col >= 0; --col ){
		
		int curCol = col;
		int curRow = 0;
		
		//System.out.println("At start of searching loop cur row is " + curRow + " and curCol is " + curCol);
		
		//while(curRow < boardWidthSquares - col && curCol < boardWidthSquares) {
		while(curRow <= col  && curCol >= 0) {	
			
			//System.out.println("Right before findOpponentStartDiagLeft curRow and col are " + curRow + ",  " +
			//curCol);
			Square groupStart = findOpponentStartDiagLeft( curRow,  curCol);
			
			if(groupStart != null){
				
				//System.out.println ("Hi I found a group start at " + groupStart.getRow() + ", " + groupStart.getCol() );
				OpponentGroup newGroup = new OpponentGroup(OpponentGroup.DIAG_LEFT_GROUP);
				newGroup.addSquareToGroup(groupStart);
				int startRow = groupStart.getRow();
				int startCol = groupStart.getCol();
				
				// Check first edge  same problem so same code from above should work...
				if(startRow - 1 >= 0 && startCol + 1 < boardWidthSquares) {
					newGroup.setEnd1Square(theGameBoard[startRow-1][startCol+1]);
				} else {
					newGroup.setEnd1Square(null);
					
				}
				//see if current move is part of this group
				if( startRow == lastMoveRow && startCol == lastMoveCol ){
					newGroup.setCurrentMoveIsInThisGroup(true);
					newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength());
				}
				
				//look for additional group members
				startCol--;  // startCol[__];
				startRow++;
				boolean done = false;
				
				//Loop to collect the length of the Opponent Group
				while( !done && startCol >=0  && startRow < boardWidthSquares ){	
					if(theGameBoard[startRow][startCol].getState() == this.opponentStoneColor ) {
						newGroup.addSquareToGroup(theGameBoard[startRow][startCol]);		
						if( startRow == lastMoveRow && startCol == lastMoveCol ){
							newGroup.setCurrentMoveIsInThisGroup(true);
							newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength());
						}
	
						startRow++;
						startCol--;
					} else {
						done = true;
					}		
				}
				
				//check other edge
				if(startRow  < boardWidthSquares && startCol  >= 0) {
					newGroup.setEnd2Square(theGameBoard[startRow][startCol]);
				} else {
					newGroup.setEnd2Square(null);
				}
				
				//Important to stop infinite loop
				curCol = startCol;
				curRow = startRow;
				//add group to list
				this.addNewGroupToGroupLists(newGroup);		
			} else {		
				//get out of loop
				curCol = -1;
				//System.out.println();	
			}
		}
	}		
}


public Square findOpponentStartDiagLeft( int whatRow, int whatCol ){
	
	//System.out.println();
	//System.out.println("At top of findOpponentStartDiagonalLEFT whatRow is " +
	//		whatRow + " and whatCol is " + whatCol);
	Square opponentStart = null;
	boolean done = false; 
	int currentCol = whatCol;
	int currentRow = whatRow;
	
	while( !done && currentCol >= 0 && currentRow < boardWidthSquares){
		
	//	System.out.println("In findOpponentDiagLEFT loop, checking currentRow " + currentRow + " and currentCol  " + currentCol );
		
		if(theGameBoard[currentRow][currentCol].getState() == this.opponentStoneColor){
			opponentStart = theGameBoard[currentRow][currentCol];
			done = true;	
		}
		currentCol--;
		currentRow++;
	}
	
	//System.out.println(" Hello from bottom of findOpponentDiagRightTop just about to return a start square");
	return opponentStart;
}



// ***********  Last Part  DIAG LEFT

public void lookForGroupsDiagonallyLeft( int lastMoveRow, int lastMoveCol){
	
	// ***** Check the first half of the board....starting from TOP and RIGHT
	
		for(int col = boardWidthSquares-1 ; col >= 0; col-- ){
			
			int curCol = boardWidthSquares-1;
			int curRow = (boardWidthSquares-1) - col; // start at the top, but far right side curRow is 0, then 1, then 2... etc.
			
			System.out.println("Hi at start diagLEFT curRow is " + curRow + " and curCol is " + curCol);
			
			while( curCol >=0 && curRow >= 0 ){	
			
				
				//System.out.println("Top of while loop for locateGroupsDiagLEFT 1st Part--> curRow is " + curRow + ", curCol is " + curCol + " and col is: "  + col);
				
				Square groupStart = findOpponentDiagonalLeft( curRow,  curCol);
				
				//if there is a start find make an opponent group and set the first edge
				if( groupStart != null ){
					System.out.println( "Hi Back from FIND START LEFT found a DIAG LEFT start 1st Part at " + groupStart.getRow() + 
							", " + groupStart.getCol());
					OpponentGroup newGroup = new OpponentGroup(OpponentGroup.DIAG_LEFT_GROUP);
					
					newGroup.addSquareToGroup(groupStart);
					int startRow = groupStart.getRow();
					int startCol = groupStart.getCol();
					// ********* add check for group with current move ********
					
					if(startCol + 1 >= boardWidthSquares || startRow + 1 >= boardWidthSquares){
						newGroup.setEnd1Square(null);
					} else {
						newGroup.setEnd1Square(theGameBoard[startRow+ 1][startCol+1]);
					}	
						
					curCol = groupStart.getCol() - 1;
					curRow = startRow - 1;
					
					
					
				//load the Squares of the opponent group until there is an end
					boolean done = false;
					//System.out.println("Have a start Square starting to load Group..");
					while(curRow >= 0 && curCol >= 0 && !done){
						//System.out.println("In Load Loop DL First Part checking square: curRow is " +
						//		curRow + " and curCol is " + curCol);
						if(theGameBoard[curRow][curCol].getState() == this.opponentStoneColor){
							//this method in squareGroup handles adding length etc...
							newGroup.addSquareToGroup(theGameBoard[curRow][curCol]);
							curCol--;
							curRow--;
						} else {
							done=true;
						}
					}

				// if there is an end set the end edge
					if(curCol < 0 || curRow < 0){
						newGroup.setEnd2Square(null);
					} else {
						newGroup.setEnd2Square(theGameBoard[curRow][curCol]);
					}
				
					// based on the length of the opponent group add it to the lists.
					addNewGroupToGroupLists(newGroup);
					//System.out.println();
				
				} else {
					// if you didn't find any start on this diagonal, then go to next iteration
					curCol = -1;
					curRow = -1;
				}
			}
		}	
	
	
	//   Second part for Left DIAG
	

	
	for(int col = boardWidthSquares-2 ; col >= 0; col-- ){
		//System.out.println();
		int curCol = col;
		int curRow = boardWidthSquares-1; // start at the top, but far right side curRow is 0, then 1, then 2... etc.
	
		System.out.println("Hi at start diagLEFT 2nd Part curRow is " + curRow + " and curCol is " + curCol);
		
		//while(curCol <= row && curRow >= 0){
		while( curCol >=0 && curRow >= 0 ){	
		
		//Find the start of an opponent group
		//System.out.println("Top of while loop 2nd Part for locateGroupsDiagLEFT looking for start --> curRow is " +
		//	curRow + ", curCol is " + curCol + " and col is: "  + col);
		
			Square groupStart = findOpponentDiagonalLeft( curRow,  curCol);
			
			//if there is a start find make an opponent group and set the first edge
			if( groupStart != null ){
				System.out.println("Hi Back from FIND START LEFT 2nd Part found a DIAG LEFT  at "  + groupStart.getRow() + 
						", " + groupStart.getCol());
				OpponentGroup newGroup = new OpponentGroup(OpponentGroup.DIAG_LEFT_GROUP);
				
				newGroup.addSquareToGroup(groupStart);
				
				int startRow = groupStart.getRow();
				int startCol = groupStart.getCol();
				
				// ********* add check for group with current move ********
					if( startCol + 1 >= boardWidthSquares || startRow + 1 >= boardWidthSquares ){
						newGroup.setEnd1Square(null);
					} else {
						newGroup.setEnd1Square(theGameBoard[startRow + 1][startCol + 1]);
					}	
				
					
				curCol = groupStart.getCol() - 1;
				curRow = groupStart.getRow() - 1;
				
				//load the Squares of the opponent group until there is an end
				boolean done = false;
				//System.out.println("Have a start Square starting to load Group..");
				
				while(curRow >= 0 && curCol >= 0 && !done){
					//System.out.println("In Load Loop DL 2nd Part checking square: curRow is " + curRow + " and curCol is " + curCol);
					
					if(theGameBoard[curRow][curCol].getState() == this.opponentStoneColor){
						//this method in squareGroup handles adding length etc...
						newGroup.addSquareToGroup(theGameBoard[curRow][curCol]);
						curCol--;
						curRow--;
					} else {
						done=true;
					}
				}

			// if there is an end set the end edge
				if(curCol < 0 || curRow < 0){
					newGroup.setEnd2Square(null);
				} else {
					newGroup.setEnd2Square(theGameBoard[curRow][curCol]);
				}
			
				// based on the length of the opponent group add it to the lists.
				addNewGroupToGroupLists(newGroup);
				//System.out.println();
			
			} else {
				curCol = -1;
				curRow = -1;
			}
		}
	}
}


// ****************** FIND STARTS going diag left... and UP
public Square findOpponentDiagonalLeft( int whatRow, int whatCol ){
	
	//System.out.println();
	System.out.println("At top of findOpponentStartDiagonalLEFT whatRow is " +
			whatRow + " and whatCol is " + whatCol);
	Square opponentStart = null;
	boolean done = false; 
	int currentCol = whatCol;
	int currentRow = whatRow;
	
	while( !done && currentCol >= 0 && currentRow >= 0 ){
		
	//	System.out.println("In findOpponentDiagLEFT loop, checking currentRow " + currentRow + " and currentCol  " + currentCol );
		
		if(theGameBoard[currentRow][currentCol].getState() == this.opponentStoneColor){
			opponentStart = theGameBoard[currentRow][currentCol];
			done = true;	
		}
		currentCol--;
		currentRow--;
	}
	
	//System.out.println(" Hello from bottom of findOpponentDiagRightTop just about to return a start square");
	return opponentStart;
}




// ****************** End of Diag Left
		
	
	
	public Square setUpForACapture(int lastMoveRow, int lastMoveCol){
		Square winBlock = null;
		
		return winBlock;
	}
	
	public Square getACapture(int lastMoveRow, int lastMoveCol){
		Square winBlock = null;
		
		return winBlock;
	}
	
	public Square blockAThree(int lastMoveRow, int lastMoveCol){
		Square winBlock = null;
		
		return winBlock;
	}
	
	

	public Square makeARandomMove(){
		
		System.out.println("Hi in doComputerMove lastMoveRow, lastMoveCol " );
		int newMoveRow, newMoveCol;
		do {
		 newMoveRow = (int)(Math.random()* boardWidthSquares);
	     newMoveCol = (int)(Math.random()* boardWidthSquares);
		
		} while(theGameBoard[newMoveRow][newMoveCol].getState()!= PenteMain.EMPTY );
		
		
		 System.out.println("Hi Ralph wants to move to [" + newMoveRow + ", " + newMoveCol + "]");
		 
		timeToMakeAMove = false;
		return theGameBoard[newMoveRow][newMoveCol];
	}
	

	//New added April 30 for in Middle Checks
	public void doInMiddleCheck( int groupSize ){
			
			for(int row = 0; row < boardWidthSquares; ++row){
				for(int col = 0; col < boardWidthSquares; ++col){
					if(theGameBoard[row][col].getState() == PenteMain.EMPTY){
						checkForBlockInMiddle(row, col, groupSize);
					}
				}
			}
	}
	
	
	public void checkForBlockInMiddle(int row, int col, int groupSize){
		
		boolean done = false;
		int[] myDys = {-1, 0, 1};
        int whichDy = 0;
  
		while(!done && whichDy < 3){
			checkForBlockInMiddleAllAround(row, col, groupSize, myDys[whichDy], 1 );
			whichDy++;
		}
		checkForBlockInMiddleAllAround(row, col, groupSize, 1, 0 );	
	}
	
	
	public void checkForBlockInMiddleAllAround(int row, int col, int groupSize, int dy, int dx)
	{
	
		int sRow = row;
		int sCol = col;
		//System.out.println("In checkForBlockInMiddleAllAround sRow and sCol is [" + 
		//sRow + ", " + sCol + "]");
		
		//for a right-check and left...
		int howManyRight = 0;
		int howManyLeft = 0;
		
		//loop to check right side of where stone s is
		int step = 1;
		//System.out.println("In checkForWinAllInOne sRow and sCol are [" + sRow + ", " + sCol + "]");
		//System.out.println("In checkForWinAllInOne dy and dx are [" + dy + ", " + dx + "]");
		while((sCol + (step * dx) < boardWidthSquares) && (sRow + (step * dy) < boardWidthSquares) &&
				(sCol + (step * dx) >= 0) && (sRow + (step * dy) >= 0) &&
				(theGameBoard[sRow + (step * dy)][sCol + (step * dx)].getState() == this.opponentStoneColor)){
			howManyRight++;
			step++;
		}
		//Moving Left....
		step = 1;
		while((sCol - (step * dx) >= 0) &&  (sRow - (step * dy) >= 0) &&
				(sCol - (step * dx) < boardWidthSquares) && (sRow - (step * dy) < boardWidthSquares) &&
				(theGameBoard[sRow - (step * dy)][sCol - (step * dx)].getState() == this.opponentStoneColor)){
			howManyLeft++;
			step++;
		}
		
		
		if((howManyRight + howManyLeft) >= groupSize){
			//If you have this then you want to set Up an Opponent group for this
			System.out.println("For square at " + row + ", " + col + " we have group of size of " + (howManyRight + howManyLeft));
			OpponentGroup newGroup;
			if( groupSize == 4 ) {
				String middleGroupText = getMiddleGroupText(dx, dy, 4);
				newGroup = new OpponentGroup(OpponentGroup.MIDDLE_4_GROUP);
				newGroup.setGroupRanking(4);
				newGroup.setGroupLength(4);
				newGroup.setGroupTypeText(middleGroupText);
			} else {
				String middleGroupText = getMiddleGroupText(dx, dy, 3);
				newGroup = new OpponentGroup(OpponentGroup.MIDDLE_3_GROUP);
				newGroup.setGroupRanking(3);
				newGroup.setGroupLength(3);
				newGroup.setGroupTypeText(middleGroupText);
			}
			
			newGroup.setInMiddleGroupStatus(true);
			newGroup.setInMiddleGroupSquare(theGameBoard[row][col]);
			this.addNewGroupToGroupLists(newGroup);		
		}	
	}
	
	public String getMiddleGroupText(int dx, int dy, int groupSize){
		String gs = "";
		if(groupSize == 4){
			gs = "4";
		} else {
			gs = "3";
		}
		String theType = "";
		if(dx == 1){
			if(dy == 1) theType = "Diag Right";
			if(dy == 0) theType = "Horizontal";
			if(dy == -1) theType = "Diag Left";
		} else {
			theType = "Vertical";
		}
		
		return "Middle " + gs + ": " + theType;
	}
	
	
	public ArrayList<OpponentGroup> getVanellopeGroups4(){
		return groups4;
	}
	
	public ArrayList<OpponentGroup> getVanellopeGroups3(){
		return groups3;
	}
	
	public ArrayList<OpponentGroup> getVanellopeGroups2(){
		return groups2;
	}
	
	public ArrayList<OpponentGroup> getVanellopeGroups1(){
		return groups1;
	}
	
	
}
	
	
	/* 
	 * 
	 * 
	 * 
	
	

	
	public void lookForGroupsHorizontally2( int lastMoveRow, int lastMoveCol){
		
		for( int row = 0; row < boardWidthSquares; ++row ){
			
			int curCol = 0;
			
			while(curCol < boardWidthSquares){
				
				//STEP 1 -- Find the start of an opponent group
				Square groupStart = findOpponentStartHorizontal2( row,  curCol);
				
				//STEP 2 -- if there is a start find make an opponent group object and set the first edge (and check current move)
				if(groupStart != null){
					//System.out.println("Hi found a start at " + groupStart.getRow() + 
					//		", " + groupStart.getCol());
					//STEP 2A -- Make the opponent group
					OpponentGroup newGroup = new OpponentGroup(OpponentGroup.HORIZONTAL_GROUP);
					newGroup.addSquareToGroup(groupStart);
					int startRow = groupStart.getRow();
					int startCol = groupStart.getCol();
					
					//STEP 2B --check to see if current move is involved with this group and set a variable for this...
					if( startRow == lastMoveRow && startCol == lastMoveCol ){
						newGroup.setCurrentMoveIsInThisGroup(true);
						newGroup.setCurrentMoveArrayListLocation(0);
					}
					//STEP 2C -- check first edge (square beside the start of the group...)
					if(startCol <= 0){
						newGroup.setEnd1Square(null);
					} else {
						newGroup.setEnd1Square(theGameBoard[row][startCol-1]);
							
					}
						
					curCol = groupStart.getCol() + 1;
					
				//STEP 3 --load the Squares of the opponent group until there is an end
					boolean done = false;
					while(curCol < boardWidthSquares && !done){
						if(theGameBoard[row][curCol].getState() == this.opponentStoneColor){
							//this method in squareGroup handles adding length etc...
							newGroup.addSquareToGroup(theGameBoard[row][curCol]);
							//STEP 3A Check the current move and set a variable if this group includes the current move
							if( startRow == row && curCol == lastMoveCol ){
								newGroup.setCurrentMoveIsInThisGroup(true);
								newGroup.setCurrentMoveArrayListLocation(newGroup.getArrayListSizeFromArray()-1);
							}
							curCol++;
						} else {
							done=true;
						}
					}

				// STEP 4 -- check the other edge of the group (look to see if there is a stone) 
					if(curCol >= boardWidthSquares){
						newGroup.setEnd2Square(null);
					} else {
						newGroup.setEnd2Square(theGameBoard[row][curCol]);
					}
				
				// STEP 5 --  based on the length of the opponent group add it to the lists.
					this.addNewGroupToGroupLists(newGroup);
				
				
				} else {
					curCol = boardWidthSquares;
				}
			}
			
		}
	}
	
	
	public void addNewGroupToGroupLists2(OpponentGroup ng){
		
		switch(ng.getGroupLength()){
		case 1:
			//System.out.println("I have an H group with one opponent stone");
			groups1.add(ng);
			break;
		case 2:
			System.out.println("I have a " + ng.getGroupTypeText() + " group with two opponent stones");
			groups2.add(ng);
			break;
		case 3:
			System.out.println("I have a " + ng.getGroupTypeText() + " group with three opponent stones");
			groups3.add(ng);
			break;
		case 4:
			System.out.println("I have a " + ng.getGroupTypeText() + " group with four opponent stones");
			groups4.add(ng);
			break;
		default:
			System.out.println("I have a " + ng.getGroupTypeText() + " group with " +
					ng.getGroupLength()
					+ " opponent stones -- this is the default");
			break;
	
		}
		
		
		
	}
		

	public Square findOpponentStartHorizontal2(int whatRow, int whatCol){
			
			//System.out.println(" Hello from start of findOpponentStartHorizontal");
			Square opponentStart = null;
			boolean done = false; 
			int currentCol = whatCol;
			
			while( !done && currentCol < boardWidthSquares ){
				if(theGameBoard[whatRow][currentCol].getState() == this.opponentStoneColor){
					opponentStart = theGameBoard[whatRow][currentCol];
					done = true;	
				}
				currentCol++;
			}
			
			//System.out.println(" Hello from bottom of findOpponentStartHorizontal just about to return a start square");
			return opponentStart;
	}
	
	
	
		
public void lookForGroupsDiagonallyRight( int lastMoveRow, int lastMoveCol){
		
		for( int row = 0; row < boardWidthSquares; ++row ){
			//System.out.println();
			int curCol = 0;
			int curRow = row;
			
			//System.out.println("Hi at start diagRight curRow is " +
			//curRow + " and curCol is " + curCol);
			
			while(curCol <= row && curRow >= 0){
				//Find the start of an opponent group
				//System.out.println("Top of while loop for locateGroupsDiagRight looking for start --> curRow is " +
				//		curRow + ", curCol is " + curCol + " and row is: "  + row);
				Square groupStart = findOpponentDiagonalRightTop( curRow,  curCol);
				
				//if there is a start find make an opponent group and set the first edge
				if( groupStart != null ){
					//System.out.println("Hi found a DIAG RIGHT start at " + groupStart.getRow() + 
					//		", " + groupStart.getCol());
					OpponentGroup newGroup = new OpponentGroup(OpponentGroup.DIAG_RIGHT_GROUP);
					newGroup.addSquareToGroup(groupStart);
					int startRow = groupStart.getRow();
					int startCol = groupStart.getCol();
					// ********* add check for group with current move ********
						if(startCol - 1 < 0 || startRow + 1 >= boardWidthSquares){
							newGroup.setEnd1Square(null);
						} else {
							newGroup.setEnd1Square(theGameBoard[startRow+ 1][startCol-1]);
						}	
					
						
					curCol = groupStart.getCol() + 1;
					curRow = groupStart.getRow() - 1;
					
					//load the Squares of the opponent group until there is an end
					boolean done = false;
					//System.out.println("Have a start Square starting to load Group..");
					while(curRow >= 0 && curCol < boardWidthSquares && !done){
						//System.out.println("In Load Loop checking square: curRow is " +
						//		curRow + " and curCol is " + curCol);
						if(theGameBoard[curRow][curCol].getState() == this.opponentStoneColor){
							//this method in squareGroup handles adding length etc...
							newGroup.addSquareToGroup(theGameBoard[curRow][curCol]);
							curCol++;
							curRow--;
						} else {
							done=true;
						}
					}

				// if there is an end set the end edge
					if(curCol >= boardWidthSquares || curRow < 0){
						newGroup.setEnd2Square(null);
					} else {
						newGroup.setEnd2Square(theGameBoard[curRow][curCol]);
					}
				
				// based on the length of the opponent group add it to the lists.
					switch(newGroup.getGroupLength()){
						case 1:
							//System.out.println("I have an DR group with one opponent stone");
							groups1.add(newGroup);
							break;
						case 2:
							System.out.println("I have an DR Horiz group with two opponent stones");
							groups2.add(newGroup);
							break;
						case 3:
							System.out.println("I have an DR group with three opponent stones");
							groups3.add(newGroup);
							break;
						case 4:
							System.out.println("I have an DR group with four opponent stones");
							groups4.add(newGroup);
							break;
						default:
							System.out.println("I have an DR group with " +
									newGroup.getGroupLength()
									+ " opponent stones -- this is the default");
							break;
					}
				
				} else {
					curCol = boardWidthSquares;
					curRow = -1;
				}
			}
			
		}
		
		// ***** Check the other half of the board....
		
		for(int col = 1; col < this.boardWidthSquares; ++ col){
			
			int curCol = col;
			int curRow = this.boardWidthSquares-1; // start at the bottom
			
			//System.out.println("Hi at start diagRight curRow is " +
			//		curRow + " and curCol is " + curCol);
			
			while(curCol < this.boardWidthSquares && curRow >= col){
				
				//System.out.println("Top of while loop for locateGroupsDiagRight 2nd Part looking for start 2nd Part --> curRow is " +
				//		curRow + ", curCol is " + curCol + " and col is: "  + col);
				
				Square groupStart = findOpponentDiagonalRightTop( curRow,  curCol);
				
				//if there is a start find make an opponent group and set the first edge
				if( groupStart != null ){
					//System.out.println("Hi found a DIAG RIGHT start 2nd Part at " + groupStart.getRow() + 
					//		", " + groupStart.getCol());
					OpponentGroup newGroup = new OpponentGroup(OpponentGroup.DIAG_RIGHT_GROUP);
					newGroup.addSquareToGroup(groupStart);
					int startRow = groupStart.getRow();
					int startCol = groupStart.getCol();
					// ********* add check for group with current move ********
					
					if(startCol - 1 < 0 || startRow + 1 >= boardWidthSquares){
						newGroup.setEnd1Square(null);
					} else {
						newGroup.setEnd1Square(theGameBoard[startRow+ 1][startCol-1]);
					}	
						
					curCol = groupStart.getCol() + 1;
					curRow = startRow - 1;
					
				//load the Squares of the opponent group until there is an end
					boolean done = false;
					//System.out.println("Have a start Square starting to load Group..");
					while(curRow >= 0 && curCol < boardWidthSquares && !done){
						//System.out.println("In Load Loop checking square: curRow is " +
						//		curRow + " and curCol is " + curCol);
						if(theGameBoard[curRow][curCol].getState() == this.opponentStoneColor){
							//this method in squareGroup handles adding length etc...
							newGroup.addSquareToGroup(theGameBoard[curRow][curCol]);
							curCol++;
							curRow--;
						} else {
							done=true;
						}
					}

				// if there is an end set the end edge
					if(curCol >= boardWidthSquares || curRow < 0){
						newGroup.setEnd2Square(null);
					} else {
						newGroup.setEnd2Square(theGameBoard[curRow][curCol]);
					}
				
				// based on the length of the opponent group add it to the lists.
					switch(newGroup.getGroupLength()){
						case 1:
							//System.out.println("I have an DR 2nd Part group with one opponent stone");
							groups1.add(newGroup);
							break;
						case 2:
							System.out.println("I have an DR 2nd Part group with two opponent stones");
							groups2.add(newGroup);
							break;
						case 3:
							System.out.println("I have an DR 2nd Part group with three opponent stones");
							groups3.add(newGroup);
							break;
						case 4:
							System.out.println("I have an DR 2nd Part group with four opponent stones");
							groups4.add(newGroup);
							break;
						default:
							System.out.println("I have an DR 2nd Part group with " +
									newGroup.getGroupLength()
									+ " opponent stones -- this is the default");
							break;
					
					}
				
				} else {
					curCol = boardWidthSquares;
					curRow = -1;
				}
			}
		}
	}





public Square findOpponentDiagonalRightTop( int whatRow, int whatCol ){
	
	//System.out.println(" Hello from start of findOpponentStartHorizontal");
	//System.out.println("At top of findOpponentDiagonalRightTop whatRow is " +
	//		whatRow + " and whatCol is " + whatCol);
	Square opponentStart = null;
	boolean done = false; 
	int currentCol = whatCol;
	int currentRow = whatRow;
	
	while( !done && currentCol < boardWidthSquares && currentRow >= 0){
		
		//System.out.println("In findOpponentDiagRightTop loop, checking currentRow " +
		//		currentRow + " and currentCol  " + currentCol );
		if(theGameBoard[currentRow][currentCol].getState() == this.opponentStoneColor){
			opponentStart = theGameBoard[currentRow][currentCol];
			done = true;	
		}
		currentCol++;
		currentRow--;
	}
	
	//System.out.println(" Hello from bottom of findOpponentDiagRightTop just about to return a start square");
	return opponentStart;
}


	
	*/

