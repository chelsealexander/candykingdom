package boardgame.input;

import boardgame.Handler;

public class TurnManager {
	
	//protected Handler handler;
	private int currentTurn;
	
	public TurnManager (int currentTurn) {
		//this.handler = handler;
		this.currentTurn = currentTurn;
	}
	
	public void tick(){
		if (currentTurn == 1) {
			//algorithm time!
			//if we're on the player's turn, check to see if
			//a card has been clicked. If it has
			//change the turn to 2, where the player cannot pick another card
			//but can move their avatar to the right space
			
		} else if (currentTurn == 2) {
			//let the player move, but not access a new card
			//pause here until player moves to correct spot
			//then move on the turn 3
		} else if (currentTurn == 3){
			//this will be the AI's turn
			//when the AI is done, change back to first turn
		}
	}
	
	public boolean isPlayersTurn() {
		if (currentTurn == 1) {
		return true;
		} else {
			return false;
		}
	}
	
	public boolean isTimeToMove(){
		if (currentTurn == 2){
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isAITurn(){
		if (currentTurn == 3){
			return true;
		} else {
			return false;
		}
	}
	
	public int getTurn() {
		return currentTurn;
	}
	
	public void setTurn(int currentTurn){
		this.currentTurn = currentTurn;
	}

}
