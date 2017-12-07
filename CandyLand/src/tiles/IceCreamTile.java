package tiles;

import boardgame.Assets;

public class IceCreamTile extends Tile {

	/*Each tile has its own constructor?? (Method??) that takes in the id from the Tile class
	 *and then assigns it the correct asset. Why do we have separate classes for each?
	 *Each tile might do something different.*/
	
	public IceCreamTile(int id) {
		
		//we use super to use the Tile constructor and pass this back up to Tile
		super(Assets.iceCream, id);
	}
}
