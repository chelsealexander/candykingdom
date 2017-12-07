package tiles;


	import java.awt.Graphics;
	import boardgame.input.ClickManager;
	import java.awt.image.BufferedImage;

public class Tile {
		
		//Stationary stuff goes in here.
	
	//tile [] was tile[256] but we'll try just 20
		public static Tile[] tiles = new Tile[20];
		public static Tile purpleTile = new PurpleTile(0);
		public static Tile greenTile = new GreenTile(1);
		public static Tile pinkTile = new PinkTile(2);
		public static Tile blueTile = new BlueTile(3);
		public static Tile yellowTile = new YellowTile(4);
		public static Tile orangeTile = new OrangeTile(5);
		public static Tile cupcakeTile = new CupcakeTile(6);
		public static Tile cookieTile = new CookieTile(7);
		public static Tile iceCreamTile = new IceCreamTile(8);
		public static Tile snowflakeTile = new SnowflakeTile(9);
		public static Tile grassTile = new GrassTile(10);
		public static Tile whiteTile = new WhiteTile(11);
		public static Tile cardBackTile = new CardBackTile(12);
		
		//The actual class is over here
		
		//Our tiles are 32 x 32 so we're actually doubling them in size here
		public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
		
		//The texture will tell us what resource we're accessing
		//the id tells us its spot in the array
		protected BufferedImage texture;
		protected final int id;
		
		//here's our tile constructor
		public Tile(BufferedImage texture, int id) {
			this.texture = texture;
			this.id = id;
			
			tiles[id] = this;
		}
		
		//we'll need this later maybe
		public void tick(){
			
		}
		
		//DRAW TO THE SCREEEEEEEEEEN
		public void render(Graphics g, int x, int y){
			g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
		}
		
		public boolean isSolid() {
			return false;
		}
		
		public int getId(){
			return id;
		}
}
