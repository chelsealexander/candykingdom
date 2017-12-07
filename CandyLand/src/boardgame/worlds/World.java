package boardgame.worlds;

import java.awt.Graphics;

import boardgame.Game;
import boardgame.Handler;
import tiles.Tile;
import boardgame.utils.Utils;

public class World {
	
	private Handler handler;
	private int width, height;
	private int [][] tiles;
	
	private int spawnX, spawnY;
	
	public World(Handler handler, String path){
		this.handler = handler;
		loadWorld(path);
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				getTile(x, y).render(g, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);
			}
		}
	}
	
	//this is looking for array values
	//x is height, y is width, and this is confusing.
	public Tile getTile(int x, int y){
		
		//We're going to have a tile object and call it t
		//We'll store in it the tile location that was passed into this
		//do a try catch because this throws an array index out of bounds error when the deck is clicked
		
		//it's doing this because the x and y aren't anything inside it; it's where it STARTS. We need to
		//figure out if an x and y is within its bounds instead
		try {
		Tile t = Tile.tiles[tiles[x][y]];
		//If there's nothing at that location, the game will break
				//so we instead return a generic tile in that case rather than break it
				if (t == null){
					return Tile.purpleTile;
				}
				
				//otherwise if everything is working properly, we return the tile
				//at the location that was passed in.
				return t;
		} catch (ArrayIndexOutOfBoundsException e) {
			return Tile.whiteTile;
		}
	}
	
	private void loadWorld(String path){
		//load up our file
		String file = Utils.loadFileAsString(path);
		//split up that string into individual numbers based on white space
		//store into array called tokens
		String[] tokens = file.split("\\s+");
		//the first values are the width and height of the board
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		//the second two are our x and y coordinates for the player
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		//initialize the tiles array
		tiles = new int[width][height];
		
		//cycle through
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				//get the right numbers
				//add 4 because the first 4 variables aren't tiles
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
	
	//This function takes in an x-coordinate to find the x value in the tiles array
public int xOfTile(int x){
		
	int tilex = 0;
	int widthTest = Tile.TILEWIDTH;
	//there's a border around the tiles so we don't start at 0, but at the tile width
	int nextXStart = Tile.TILEWIDTH;
		//the x value of the first tile is between 0 and tilewidth. the second is between 0 and tilewidth times 2
		//etc. 
		//so width times the tileWidth represents the last x starting point (because the frame starts at x of 0)
	
		//so check to see where the number falls to find out what the x (starting point) of the tile is
	
	while (tilex <= width){
		if (x == nextXStart || x < widthTest){
			return tilex;
		} else {
			//add another width to our width and to the starting point of x
			widthTest += Tile.TILEWIDTH;
			nextXStart += Tile.TILEWIDTH;
		}
	
	//if -1 is returned, there's an error/we're no longer in the part of the screen with the tiles
		tilex++;
	}	
		return -1;
}

//this function takes in an X COORDINATE returns X COORDINATE 
public int startingX(int x){
	//we're stealing the code to get the xOfTile to get the startingX
	//we'll need this later
	int tilex = 0;
	int widthTest = Tile.TILEWIDTH;
	int nextXStart = Tile.TILEWIDTH;
	while (tilex <= width){
		if (x == nextXStart || x < widthTest){
			return nextXStart;
		} else {
			widthTest += Tile.TILEWIDTH;
			nextXStart += Tile.TILEWIDTH;
		}
		tilex++;
	}	
		return -1;
}

//this function takes in a Y COORDINATE returns Y COORDINATE
public int startingY(int y){
	int tileY = 0;
	int heightTest = Tile.TILEHEIGHT;
	int nextYStart = Tile.TILEHEIGHT;
	
	while (tileY <= height){
		if (y == nextYStart || y < heightTest){
			return nextYStart;
		} else {
			//add another width to our width and to the starting point of x
			heightTest += Tile.TILEHEIGHT;
			nextYStart += Tile.TILEHEIGHT;
		}
	
	//if -1 is returned, there's an error/we're no longer in the part of the screen with the tiles
	tileY++;
		
	}

		return -1;
	}

//this function takes in a Y COORDINATE returns Y VALUE IN TILES ARRAY
public int yOfTile(int y){
	//what we're actually looking for is the value in the array, not the "x" or "y" of the tile
	//this code should determine where on the board we are with the y and then return which tile we're at
	int tileY = 0;
	int heightTest = Tile.TILEHEIGHT;
	int nextYStart = Tile.TILEHEIGHT;
	
	while (tileY <= height){
		if (y == nextYStart || y < heightTest){
			return tileY;
		} else {
			//add another width to our width and to the starting point of x
			heightTest += Tile.TILEHEIGHT;
			nextYStart += Tile.TILEHEIGHT;
		}
	
	//if -1 is returned, there's an error/we're no longer in the part of the screen with the tiles
	tileY++;
		
	}

		return -1;
	}

//this function finds the starting x position of a tile using the tile's
//position in the array (the parameter it takes in, x)
//it just cycles through each X value, adding another width until it finds the right one
public int findStartOfXUsingArray(int x){
	int tilex = 0;
	int nextXStart = Tile.TILEWIDTH;
	
	while (tilex != x){
		nextXStart += Tile.TILEWIDTH;
		tilex++;
	}
	
	return nextXStart;
}

//this function finds the starting y position of a tile using the tile's position
//in the array (the parameter it takes in, y)
//cycles through each Y value, adding another height until it finds right one
public int findStartOfYUsingArray(int y){
	int tiley = 0;
	int heightTest = Tile.TILEHEIGHT;
	while (tiley != y){
		heightTest += Tile.TILEHEIGHT;
		tiley++;
	}	
		return heightTest;
}

//TESTING
//HOLD UP. This works when we're going right-left....but not when we're going
//left right. Need to account for that.

//this function takes in the x and y ARRAY VALUES for tiles[][]
//and also a cardID. It returns the next tile that is of the correct color
public int getNextColorTileX(int x, int y, int cardID){
	
	boolean leftRight = false;
	
	
	if ((y -2) % 4 == 0){
		leftRight = true;
		System.out.println("GOING LEFT TO RIGHT.");
	}
	
	int tileValue = 0;
	
	//figure out what color we're looking for
	if (cardID == 0 || cardID == 6){
		tileValue = 0;
	} else if (cardID == 1 || cardID == 7){
		tileValue = 1;
	} else if (cardID == 2 || cardID == 8){
		tileValue = 2;
	} else if (cardID == 3 || cardID == 9){
		tileValue = 3;
	} else if (cardID == 4 || cardID == 10){
		tileValue = 4;
	} else if (cardID == 5 || cardID == 11){
		tileValue = 5;
	} else if (cardID == 12){
		tileValue = 6;
	} else if (cardID == 13){
		tileValue = 7;
	} else if (cardID == 14){
		tileValue = 8;
	} else if (cardID == 15){
		tileValue = 9;
	}
	
	
	//we want to start with the next tile value
	//not the current tile value, so move to the next tile
	//this is dependent on which direction we're moving
	if (leftRight){
		x += 1;
		//if adding one throws it out of bounds, go down a row
		if (x > width){
			y += 1;
			x = 0;
		}  //I think below code may be backwards!!!!!!!!
	} else if (x > 2) {
		//if we're going right to left, subtract one instead of adding
		x -= 1;
	} else if (x < 2){
		//if we're at the last tile in the row
		//go to the next one, set x to 0
		y += 1;
		x = 0;
	}
	
	//are we at or below the bottom of the board now? go back
		if (y > (height - 3)){
			return -1;
		}
	
	//while our y is valid (<= height), go through each value in the row (x)
	//find the next value that matches our tileValue, then return its x position
	//if you don't find anything in that row, go to the next row and set x to 0
	
	//height - 3 because we have 2 blank rows and grass
		
		int flag = 0;
		
	while (y < (height - 3)){
		//uh oh! Bug! we haven't accounted for a situation in which we move down a row!
		//in that case, leftRight may change value!!!
		
		if (leftRight){
			//if we're moving left to right, go through array like so
			//width - 2 because we have 2 blank columns
			while (x < (width - 2)){
				
				if (tileValue == tiles[x][y]){
					return findStartOfXUsingArray(x);
				}
				x++;
			}
			flag++;
		}
		else {
			//the first time we run through this, if we're STARTING with a right to left line
			//we don't want to set the x to the width
			//otherwise, we do
			if (flag > 0){
				x = (width - 1);
			}
			//if we're moving right to left, decrement instead
			//start at our current tile and  go through our array
			while (x >= 0){
				if (tileValue == tiles[x][y]){
					return findStartOfXUsingArray(x);
				}
				x--;
			}
			flag++;
		}
		//and move to the next line
		x = 0;
		y++;
		
		/*HEY! THERE'S A BUG HERE
		 * 
		 * 
		 * add code here to change our leftRight value when necessary!!!
		 */
		
		//if we have run through this code a multiple of two times, swap
		//the left/right boolean
		if ((flag % 2) == 0){
			if (leftRight){
				leftRight = false;
			} else {
				leftRight = true;
			}
			
		} //end if flag%2
		
		}//end while
	
	//if nothing was found
	return -1;
	}//end function getNextColorTileX

public int getNextColorTileY(int x, int y, int cardID){
int tileValue = 0;
boolean leftRight = false;

//need to handle for case in which going right to left
//on a far left tile
//need to find next tile (which is below)

//hey, did we account for the single tile rows????
//also I'm pretty damn sure this is all backwards
if ((y -2) % 4 == 0){
	leftRight = true;
}
	
	//figure out what color we're looking for
	if (cardID == 0 || cardID == 6){
		tileValue = 0;
	} else if (cardID == 1 || cardID == 7){
		tileValue = 1;
	} else if (cardID == 2 || cardID == 8){
		tileValue = 2;
	} else if (cardID == 3 || cardID == 9){
		tileValue = 3;
	} else if (cardID == 4 || cardID == 10){
		tileValue = 4;
	} else if (cardID == 5 || cardID == 11){
		tileValue = 5;
	} else if (cardID == 12){
		tileValue = 6;
	} else if (cardID == 13){
		tileValue = 7;
	} else if (cardID == 14){
		tileValue = 8;
	} else if (cardID == 15){
		tileValue = 9;
	}
	
	//we want to start with the next tile value
		//not the current tile value, so move to the next tile
		//this is dependent on which direction we're moving
		if (leftRight){
			x += 1;
			//if adding one throws it out of bounds, go down a row
			if (x > width){
				y += 1;
				x = 0;
			}
		} else if (x > 2) {
			//if we're going right to left, subtract one instead of adding
			x -= 1;
		} else if (x < 2){
			//if we're at the last tile in the row
			//go to the next one, set x to 0
			y += 1;
			x = 0;
		}
		
		//are we at or below the bottom of the board now? go back
			if (y > (height - 3)){
				return -1;
			}
		
	
	//while our y is valid (<= height), go through each value in the row (x)
	//find the next value that matches our tileValue, then return its y position
	//if you don't find anything in that row, go to the next row and set x to 0
	
			int flag = 0;
			
			while (y < (height - 3)){
				
				if (leftRight){
					//if we're moving left to right, go through array like so
					//width - 2 because we have 2 blank columns
					while (x < (width - 2)){
						if (tileValue == tiles[x][y]){
							return findStartOfYUsingArray(y);
						}
						x++;
					}
					flag++;
				}
				else {
					//the first time we run through this, if we're STARTING with a right to left line
					//we don't want to set the x to the width
					//otherwise, we do
					if (flag > 0){
						x = (width - 1);
					}
					//if we're moving right to left, decrement instead
					//start at our current tile and  go through our array
					while (x >= 0){
						if (tileValue == tiles[x][y]){
							return findStartOfYUsingArray(y);
						}
						x--;
					}
					flag++;
				}
				//and move to the next line
				x = 0;
				y++;
				
				/*
				 * Testing Below Code
				 * Seems to work! should keep it
				 * 
				 */
				
				if ((flag % 2) == 0){
					if (leftRight){
						leftRight = false;
					} else {
						leftRight = true;
					}
					
				} //end if flag%2
				
				} //end while

	
	//if nothing was found
	return -1;

}

//if we need to find the x and y of a special tile, just go through
//the whole board and find them; there's only one of these special
//tiles on the board. This is for our AI not our player.
public int findXOfSpecial(int cardID){
	int x = 0;
	int y = 2;
	
	while (y < height){
		while (x < width){
			if (cardID == tiles[x][y]){
				return findStartOfXUsingArray(x);
			}
			x++;
		}
		y++;
		x = 0;
	}
	
	return 0;
}

public int findYOfSpecial(int cardID){
	int x = 0;
	int y = 2;
	
	while (y < height){
		while (x < width){
			if (cardID == tiles[x][y]){
				return findStartOfYUsingArray(y);
			}
			x++;
		}
		y++;
		x = 0;
	}
	return 0;
}

}
