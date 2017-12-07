package boardgame.states;

import java.awt.Graphics;

import boardgame.players.*;
import boardgame.Handler;
import boardgame.worlds.World;
import boardgame.Assets;
import boardgame.Game;
import tiles.Tile;
import boardgame.cards.*;
import java.awt.event.MouseEvent;
import boardgame.input.TurnManager;

//this is to get our random number
import java.util.Random;

public class GameState extends State {
	
	private World candyLand;
	private AI AI;
	private PlayerOne playerOne;
	private boolean gameOver = false;
	private int lastTileX;
	private int lastTileY;
	
	//this assumes the game board's last snake goes from right to left and follows the other conventions I've outlined
	//in World
	
	public GameState(Handler handler) {
		super(handler);
		candyLand = new World(handler, "res/Worlds/candyland.txt");
		handler.setWorld(candyLand);
		playerOne = new PlayerOne(handler, 120, 160, 64, 64);
		AI = new AI(handler, 160, 160, 64, 64);
	}

	@Override
	public void tick() {
		//tick our world and player here in the game state
		candyLand.tick();
		playerOne.tick();
		AI.tick();
		
		lastTileX = handler.getWorld().startingX(handler.getWorld().findStartOfXUsingArray(3));
		lastTileY = handler.getWorld().startingY(handler.getWorld().findStartOfYUsingArray(((handler.getWorld().getHeight()) - 3)));
		
		/*if (handler.getWorld().startingX((int)AI.getX()) == lastTileX && handler.getWorld().startingY((int)AI.getY()) == lastTileY){
			gameOver = true;
		}
		
		if (handler.getWorld().startingX((int)playerOne.getX()) == lastTileX && handler.getWorld().startingY((int)playerOne.getY()) == lastTileY){
			gameOver = true;
		}*/
		
		}

	@Override
	public void render(Graphics g) {
		//render them in the game state as well, player last so that it sits on top of the world
		candyLand.render(g);
		
		if (gameOver){
			g.drawString("GAME OVER", 400, 400);
		}
		
		AI.render(g);
		playerOne.render(g);
	}
	
	public boolean isGameOver(){
		return gameOver;
	}
	public void gameIsOver(boolean gameOver){
		this.gameOver = gameOver;
	}
	
}
