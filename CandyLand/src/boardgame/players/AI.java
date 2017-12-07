package boardgame.players;

import java.awt.Graphics;

import boardgame.Assets;
import boardgame.Handler;

public class AI extends Players {

	protected float speed;
	protected float xMove, yMove;
	protected int colorID;
	
	
	public AI(Handler handler, float x, float y, int height, int width) {
		super(handler, x, y, height, width);
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = 64;
		bounds.height = 64;
		
		xMove = 0;
		yMove = 0;
	}
	
	private void getInput(){
		//we need to figure out what tile we're on
		//then go get the next tile
		//which matches the color of the card that we got from the game
		if (handler.getTurnManager().isAITurn()){
			
			//make sure the card on the screen was set by the AI
			if (handler.getGame().wasCardPlayed()){
				
				//go get our time delay value
				int timeDelay = handler.getGame().getAITimeDelay();
				
				//if it's greater than 0 just go back
				if ( timeDelay > 0) {
					handler.getGame().setAITimeDelay((timeDelay - 1));
					return;
				}
				
			//otherwise keep going and finally move the AI.
			System.out.println("made it inside cardwasplayed in ai");
			int cardID = handler.getGame().getCardID();
			System.out.println("cardID: " + cardID);
			
			if(cardID == 0 || cardID == 6){
				colorID = 0;
			} else if (cardID == 1 || cardID == 7){
				colorID = 1;
			} else if (cardID == 2 || cardID == 8){
				colorID = 2;
			} else if (cardID == 3 || cardID == 9){
				colorID = 3;
			} else if (cardID == 4 || cardID == 10){
				colorID = 4;
			} else if (cardID == 5 || cardID == 11){
				colorID = 5;
			} else if (cardID == 12){
				colorID = 6;
			} else if (cardID == 13){
				colorID = 7;
			} else if (cardID == 14){
				colorID = 8;
			} else if (cardID == 15){
				colorID = 9;
			}
			
			System.out.println("color ID was: " + colorID);
			
			//gets the spot in the array of where the AI is currently
			int storeX = handler.getWorld().xOfTile((int)x);
			int storeY = handler.getWorld().yOfTile((int)y);
			
			System.out.println("Storex is: " + storeX + " store y is: " + storeY);
			
			if (cardID >=12){
				System.out.println("cardID was greater than 12");
					x = handler.getWorld().findXOfSpecial(colorID) - 40;
					y = handler.getWorld().findYOfSpecial(colorID) - 40;
					System.out.println("greater than 11 X is: " + x + " Y is: " + y);
			}
			
			if (cardID >= 6 && cardID < 12){
				System.out.println("cardID was between 6 and 11");
				x = handler.getWorld().getNextColorTileX(storeX, storeY, colorID) - 40;
				y = handler.getWorld().getNextColorTileY(storeX, storeY, colorID) - 40;
				System.out.println("Between 6 and 12, X is: " + x + " Y is: " + y);
			}
			
			if (cardID < 6){
				System.out.println("cardID was less than 6");
				int firstX = 0;
				int firstY = 0;
				
				int arrayX = 0;
				int arrayY = 0;
				
				
				firstX = handler.getWorld().getNextColorTileX(storeX, storeY, colorID) - 40;
				firstY = handler.getWorld().getNextColorTileY(storeX, storeY, colorID) - 40;
				
				arrayX = handler.getWorld().xOfTile(firstX);
				arrayY = handler.getWorld().yOfTile(firstY);
				
				x = handler.getWorld().getNextColorTileX(arrayX, arrayY, colorID) - 40;
				y = handler.getWorld().getNextColorTileY(arrayX, arrayY, colorID) - 40;
				System.out.println("less than 6 X is: " + x + " Y is: " + y);
			}
			
			//set the turn back to the player's first phase
			//and make sure that we reset the card played value
			handler.getTurnManager().setTurn(1);
			handler.getGame().setCardPlayed(false);
			}
		}
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		getInput();
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(Assets.villain, (int)x, (int)y, null);
	}

}
