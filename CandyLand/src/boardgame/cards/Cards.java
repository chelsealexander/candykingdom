package boardgame.cards;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import boardgame.Game;

public class Cards {
	
	/*All our cards go in this array here. */
	
	public static Cards[] deck = new Cards[17];
	public static Cards dpurplecardarray = new DPurpleCard(0);
	public static Cards dgreencardarray = new DGreenCard(1);
	public static Cards dpinkcardarray = new DPinkCard(2);
	public static Cards dbluecardarray = new DBlueCard(3);
	public static Cards dyellowcardarray = new DYellowCard(4);
	public static Cards dorangecardarray = new DOrangeCard(5);
	public static Cards purplecardarray = new PurpleCard(6);
	public static Cards greencardarray = new GreenCard(7);
	public static Cards pinkcardarray = new PinkCard(8);
	public static Cards bluecardarray = new BlueCard(9);
	public static Cards yellowcardarray = new YellowCard(10);
	public static Cards orangecardarray = new OrangeCard(11);
	public static Cards cupcakecardarray = new CupcakeCard(12);
	public static Cards cookiecardarray = new CookieCard(13);
	public static Cards icecreamcardarray = new IceCreamCard(14);
	public static Cards snowflakecardarray = new SnowflakeCard(15);
	public static Cards backofcardarray = new CardBack(16);
	
	/*We'll use one class to encompass all cards
	 * And another to specify the different types */
	
	//let's protect all these variables so they don't get screwed up
	protected BufferedImage cardImage;
	protected int cardID;
	
	//Our tiles are 32 x 64 so we're actually doubling them in size here
		public static final int CARDWIDTH = 64, CARDHEIGHT = 128;
	
	public Cards(BufferedImage cardImage, int cardID){
		this.cardImage = cardImage;
		this.cardID = cardID;
		
		deck[cardID] = this;
	}
			

	public void tick(){
		
	}
	
	public void render(Graphics g, int x, int y){
		g.drawImage(cardImage, x, y, CARDWIDTH, CARDHEIGHT, null);
	}
	
	
	
	//put our booleans here and then override them when needed
	//our default is a single card; doubles need to be overridden.
	public boolean isSingle(){
		return true;
	}
	
	public boolean isDouble(){
		return false;
	}
	
	//we can use this to get the color, later, to match them with the tiles
	public int getId(){
		return cardID;
	}
	
}
