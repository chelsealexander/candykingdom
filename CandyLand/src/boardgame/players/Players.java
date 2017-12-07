package boardgame.players;

import boardgame.Handler;
import boardgame.Game;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Players {
	
	protected Handler handler;
	protected float x, y;
	protected int height, width;
	protected Rectangle bounds;

	public Players (Handler handler, float x, float y, int height, int width){
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.handler = handler;
		
		bounds = new Rectangle(0, 0, width, height);
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
