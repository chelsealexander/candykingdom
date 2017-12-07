package tiles;

import boardgame.Assets;

public class GreenTile extends Tile {

		/*Each tile has its own constructor?? (Method??) that takes in the id from the Tile class
		 *and then assigns it the correct asset. Why do we have separate classes for each?
		 *Each tile might do something different.*/
		
		public GreenTile(int id) {
			
			//we use super to use the Tile constructor and pass this back up to Tile
			super(Assets.green, id);
		}
		
	
}
