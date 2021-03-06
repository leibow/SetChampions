package server;

import java.util.ArrayList;
import java.util.Random;

public class BoardUtilities 
{
	private Card[] gameBoard;
	public int numCardsOnBoard;
	private int numCardsDeck;
	private Card[] deck;
	
	/**
	 * Constructor
	 * @param gb gameBoard
	 * @param nc initial number of cards
	 */
	public BoardUtilities(Card[] gb, int nc){
		gameBoard = gb;
		numCardsOnBoard = nc;
		numCardsDeck = 81;
		deck = new Card[81];
		generateDeck();
		for(int i=0; i<nc; i++)
				gameBoard[i] = deck[i];
	}
	
	/**
	 * Returns all possible sets in the current gameBoard. If there are none, adds three more cards to the board
	 */
	public ArrayList<Move> getSets(){
		ArrayList<Move> sets = new ArrayList<Move>();
		for(int i=0; i<numCardsOnBoard; i++)
			for(int j=0; j<numCardsOnBoard; j++)
				for(int k=0; k<numCardsOnBoard; k++)
					if(!(i==j || i==k || j==k))
						if(checkSet(i, j, k)){
							Move cards = new Move();
							cards.cards[0] = gameBoard[i];
							cards.cards[1] = gameBoard[j];
							cards.cards[2] = gameBoard[k];
							sets.add(cards);
						}
				
		return sets;
	}
	
	public void makeMove(Move move){
		for(int i=0; i<numCardsOnBoard; i++)
			if(gameBoard[i].equals(move.cards[0]) || gameBoard[i].equals(move.cards[1]) || gameBoard[i].equals(move.cards[2])){
				gameBoard[i] = deck[--numCardsDeck];		//Replace the card on board with card in deck
				deck[numCardsDeck] = null;				//Remove card replaced in deck
			}
		
		if (getSets().isEmpty()){
			for(int i=0; i<3; i++){
				gameBoard[numCardsOnBoard++] = deck[numCardsDeck--];
				deck[numCardsDeck] = null;
			}
		}
	}
	
	public Card[] getBoard(){
		return gameBoard;
	}
	
	private boolean checkSet(int i, int j, int k){		
		return newXOR(gameBoard[i].getColor(), gameBoard[j].getColor(), gameBoard[k].getColor()) &&
				newXOR(gameBoard[i].getShape(), gameBoard[j].getShape(), gameBoard[k].getShape()) &&
				newXOR(gameBoard[i].getNumber(), gameBoard[j].getNumber(), gameBoard[k].getNumber()) &&
				newXOR(gameBoard[i].getFill(), gameBoard[j].getFill(), gameBoard[k].getFill());
	}
	
	private boolean newXOR(int i, int j, int k){
		return ((i==j) && (i==k)) || ((i!=j)&&(i!=k)&&(j!=k)); 
	}
	
	/**
	 * Generates the deck and shuffles it
	 */
	private void generateDeck(){
		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
				for(int k=0; k<3; k++)
					for(int m=0; m<3; m++)
						deck[i*27+j*9+k*3+m] = new Card(i,j,k,m);
		
		Random rnd = new Random();
		for(int i=0; i<200; i++){
			swap(rnd.nextInt(80), rnd.nextInt(80));
		}
	}
	
	private void swap(int i, int j){
		Card temp = deck[i];
		deck[i] = deck[j];
		deck[j] = temp;
	}
}
