package boardgame.players;

import boardgame.Handler;

import boardgame.input.ClickManager;
import boardgame.Assets;
import java.awt.Graphics;
import boardgame.input.TurnManager;
import tiles.Tile;
import boardgame.cards.Cards;

public class PlayerOne extends Players {

	protected float speed;
	protected float xMove, yMove;
	protected int xMoveInt, yMoveInt;
	private Tile tileClicked;
	protected int tileValue;
	protected int cardID;
	
	
	
	
	public static final int DEFAULT_WIDTH = 64, DEFAULT_HEIGHT = 64;
	
	public PlayerOne(Handler handler, float x, float y, int height, int width) {
		super(handler, x, y, height, width);
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = 64;
		bounds.height = 64;
		
		xMove = 0;
		yMove = 0;
	}
	
	private void getInput(){
		xMove = 0;
		yMove = 0;
		
		boolean weMoved = false;
		
		
		//We need to go get the turnManager instance in the game class from our handler
		TurnManager turnManager = handler.getTurnManager();
		
		if (turnManager.isTimeToMove()){
			//first we need to get the clickmanager and determine if the mouse has been clicked. Then
			//we need to get the x and y axis of the mouse and determine which tile was clicked on
			//so we'll need to get the tile as well. we need to assign the tiles values
			//somewhere, not here in order to figure out where they are on the board and what color they are
			//get the value of the card and match to the tile
			//then, if and only if the CORRECT tile has been clicked, can you move the player forward.
		
			if (handler.getClickManager().isMouseClicked()){
				//original code:
				//y = handler.getClickManager().getMouseY();
				//x = handler.getClickManager().getMouseX();
				
				//new code
				xMove = handler.getClickManager().getMouseX();
				yMove = handler.getClickManager().getMouseY();
				
				//cast to an int value so that we can use the values in getTile
				xMoveInt = (int) xMove;
				yMoveInt = (int) yMove;
				
				
				//we need to find out what the top left corner is of our current tile
				int tilesX = handler.getWorld().startingX((int)x);
				int tilesY = handler.getWorld().startingY((int)y);
				
				//we also need to find the top left corner of the tile the player is trying to move to
				//(could be correct or incorrect)
				int newTilesX = handler.getWorld().startingX(xMoveInt);
				int newTilesY = handler.getWorld().startingY(yMoveInt);
				
				//this code here will get our tile, store it in a tile object, and then compare
				//with every available tile. we'll just set a tileValue to compare it to our
				//cards to make things easier
				
				//this finds the ARRAY VALUE of the tile we're trying to move to
				//this is terrible naming; store in another variable
				xMoveInt = handler.getWorld().xOfTile(xMoveInt);
				yMoveInt = handler.getWorld().yOfTile(yMoveInt);
				//xMoveInt = handler.getWorld().xOfTile(newTilesX);
				//yMoveInt = handler.getWorld().yOfTile(newTilesY);
				
				//this gets the tile and stuffs it into tileClicked
				tileClicked = handler.getWorld().getTile(xMoveInt, yMoveInt);
				cardID = handler.getGame().getCardID();
				
				//Handle for double cards first
				//algorithm: find what tile we're currently on (VALUE IN ARRAY)
				//get the next color.
				//HOLD ON. We need to make sure we're getting the RIGHT new color
				//not the same color as the tile we're already on
				//then get the next one after that one (call function twice)
				//then move it if and only if you clicked on that very particular tile.
				if (cardID >= 0 && cardID < 6){
					
					int arrayValueX;
					int arrayValueY;
					int newX;
					int newY;
					
					
					//go figure out where in the array we are
					arrayValueX = handler.getWorld().xOfTile((int)x);
					arrayValueY = handler.getWorld().yOfTile((int)y);
					
					//then pass that value in to find out where ON THE MAP the next color is
					newX = handler.getWorld().getNextColorTileX(arrayValueX, arrayValueY, cardID);
					newY = handler.getWorld().getNextColorTileY(arrayValueX, arrayValueY, cardID);
					
					//now go get where in the array THAT tile is
					arrayValueX = handler.getWorld().xOfTile(newX);
					arrayValueY = handler.getWorld().yOfTile(newY);
					
					//and now find the next color, returning the LOCATION (an x and y we can put our
					//player character at
					/*
					 * WARNING -> single tile NEVER USES this getNextColorTile code
					 * This could be the root cause of the issue
					 */
					newX = handler.getWorld().getNextColorTileX(arrayValueX, arrayValueY, cardID);
					newY = handler.getWorld().getNextColorTileY(arrayValueX, arrayValueY, cardID);
					
					//if the startingX point of the tile we clicked is equal to the startingX
					//of the next correct color tile (after skipping one)
					//go ahead and move
					
					System.out.println("starting x is: " + (handler.getWorld().startingX((int)xMove)));
					System.out.println("newX is: " + newX);
					//xMoveInt has been turned into an array storage; might want to rename it. so we'll just use
					//xmove and ymove again and cast to an int value
					if (handler.getWorld().startingX((int)xMove) == newX && handler.getWorld().startingY((int)yMove) == newY){
						x = xMove;
						y = yMove;
						weMoved = true;
					}//end if handler.getWorld()......
				} else {
					
					//THIS CODE HANDLES OUR SINGLE AND SPECIAL CARDS
				
				//if xMove minus x is greater than the tilewidth times 7 (to account for
				//a special tile being in the way + 6 regulars),
				//we've gone beyond the bounds of a single line of colors
				//if we've got a single color card, just return
				if ((xMove - x > (Tile.TILEWIDTH * 7)) && (cardID > 6 && cardID < 12)){
					return;
				} //end if xMove - x...
				
				//that only works if we're on the first line of the board. 
				//the second line moves the opposite direction. So
				//if the xMove (x we're trying to move to) minus our current x results in a
				//negative number greater than the negative tilewidth * 7, return
				if (xMove - x < (-1 * (Tile.TILEWIDTH * 7)) && (cardID > 6 && cardID < 12)){
					return;
				} //end if xMove - x...
				
				//ok but what if somebody decides to just move backwards? have to account for that
				//we need to figure out if we have changed lines on the board, and if we haven't...
				if ((newTilesY - tilesY <= 0) && cardID < 12){
					//unfortunately the above still runs if you moved UP and backwards sooooo
					if (newTilesY - tilesY <= -60){
						return;
					}//end if newTilesY - TilesY <= -60
					
					//This code ASSUMES THE BOARD SNAKES LEFT TO RIGHT and has a border of white space
					//and a border of green
					//to handle a right to left board, new code would have to be written
					//in this case, the first number in the array in which our tile is stored
					//is going to be 2. Every line that moves left to right on the board
					//will be 4 after it. Thus, if you subtract 2 and then see what the remainder is
					//when divided by 4, if it's a left to right line, it will be 0.
					//and of course 2-2/4 is still 0 in the remainder
					//this uses the yMoveInt because this is the value of the y in the array
					
					if ((yMoveInt - 2) % 4 == 0 ){
						//if we're moving left to right, we shouldn't have a negative xMove - x.
						//if we do, return
						
						System.out.println("moving left to right");
						if (xMove - x < 0){
							return;
						}//end if xMove -x
					} else {
						//if we're going right to left, we shouldn't have a positive xMove - x.
						//if we do, return.
						System.out.println("moving right to left");
						if (xMove - x > 0){
							return;
						}//end if xMove - x
					} //end if/else statement
				}//end if newTilesY - tilesY <= 0 && cardID < 12
				
				
				
				if (tileClicked == Tile.tiles[0]) {
					tileValue = 0;
				} else if (tileClicked == Tile.tiles[1]){
					tileValue = 1;
				} else if (tileClicked == Tile.tiles[2]){
					tileValue = 2;
				} else if (tileClicked == Tile.tiles[3]){
					tileValue = 3;
				} else if (tileClicked == Tile.tiles[4]){
					tileValue = 4;
				} else if (tileClicked == Tile.tiles[5]){
					tileValue = 5;
				} else if (tileClicked == Tile.tiles[6]){
					tileValue = 6;
				} else if (tileClicked == Tile.tiles[7]){
					tileValue = 7;
				} else if (tileClicked == Tile.tiles[8]){
					tileValue = 8;
				} else if (tileClicked == Tile.tiles[9]){
					tileValue = 9;
				} else if (tileClicked == Tile.tiles[10]){
					tileValue = 10;
				} else if (tileClicked == Tile.tiles[11]){
					tileValue = 11;
				}
				
				
				//If you clicked on the correct tile, we'll go ahead and move the player.
				if (cardID == 6 && tileValue == 0){
					//purple
					x = xMove;
					y = yMove;
					weMoved = true;
				} else if (cardID == 7 && tileValue == 1){
					//green
					x = xMove;
					y = yMove;
					weMoved = true;
				} else if (cardID == 8 && tileValue == 2){
					//pink
					x = xMove;
					y = yMove;
					weMoved = true;
				} else if (cardID == 9 && tileValue == 3){
					//blue
					x = xMove;
					y = yMove;
					weMoved = true;
				} else if (cardID == 10 && tileValue == 4){
					//yellow
					x = xMove;
					y = yMove;
					weMoved = true;
				} else if (cardID == 11 && tileValue == 5){
					//orange
					x = xMove;
					y = yMove;
					weMoved = true;
				} else if (cardID == 12 && tileValue == 6){
					//cupcake
					x = xMove;
					y = yMove;
					weMoved = true;
				} else if (cardID == 13 && tileValue == 7){
					//cookie
					x = xMove;
					y = yMove;
					weMoved = true;
				} else if (cardID == 14 && tileValue == 8){
					//icecream
					x = xMove;
					y = yMove;
					weMoved = true;
				} else if (cardID == 15 && tileValue == 9){
					//snowflake
					x = xMove;
					y = yMove;
					weMoved = true;
				} //end else ifs
				
				//we don't need another else; if none of the conditions are true, code won't run and
				//player won't move.
				
				} //END HANDLING OF SPECIAL AND SINGLE CARDS/ End larger if/else	
			} //END IF/ELSE OF 
			
			if (weMoved){
			//if we actually moved, let's change the turn
				//for testing, just set it back to 1
			turnManager.setTurn(3);
			}//end if weMoved
		}//end if "it's time to move"
		
		
	}//end getInput

	@Override
	public void tick() {
		getInput();
		
	}//end tick

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.player, (int)x, (int)y, null);
		
	}//end render

}//end class
