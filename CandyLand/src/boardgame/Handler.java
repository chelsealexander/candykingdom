package boardgame;

import boardgame.Game;
import boardgame.input.KeyManager;
import boardgame.input.TurnManager;
import boardgame.worlds.World;
import boardgame.input.ClickManager;



/*This class allows us to cleanly pass along certain variables
 * by only ever having to pass a HANDLER object in.
 * It's just going into our game object, snagging all this stuff
 * And turning it into Handler object. */

public class Handler {
	
	private Game game;
	private World world;
	
	
	public Handler (Game game){
		this.game = game;
	}

	public KeyManager getKeyManager(){
		return game.getKeyManager();
	}
	
	public ClickManager getClickManager(){
		return game.getClickManager();
	}
	
	public TurnManager getTurnManager(){
		return game.getTurnManager();
	}
	
	public int getWidth(){
		return game.getWidth();
	}
	
	public int getHeight(){
		return game.getHeight();
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
		
}
