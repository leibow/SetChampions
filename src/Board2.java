package src;

public class Board2 {
	private int countTmp;
	
	public Board2(){
		countTmp = 0;
	}
	
	public void printBoard(){ 
	// For the viewing of the game, server side... (swing)
		
	}
	
	public boolean checkMove(Move move){
		boolean moveGood = true;
		if(moveGood) doMove(move);
		return moveGood;
	}
	
	private void doMove(Move move){
		
	}
	
	public String toString(){
		return "This is the board";
	}
}
