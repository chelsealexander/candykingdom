package boardgame;

import java.awt.image.BufferedImage;

import boardgame.ImageLoader;
import boardgame.SpriteSheet;

public class Assets {

	/*We make extra certain that these values never change */
	private static final int width = 32, height = 32, dheight = 64;
	
	
	
	//We want to get these images all as Buffered Images.
	public static BufferedImage player, purple, green, pink, blue, yellow, orange, villain;
	public static BufferedImage cupcake, cookie, iceCream, snowflake, grass, white, cardBack;
	public static BufferedImage dPurpleCard, dGreenCard, dPinkCard, dBlueCard, dYellowCard;
	public static BufferedImage dOrangeCard, purpleCard, greenCard, pinkCard, blueCard;
	public static BufferedImage yellowCard, orangeCard, cupcakeCard, cookieCard, iceCreamCard, snowflakeCard;
	
	
	public static void initialize(){
		
		SpriteSheet candyLandSheet = new SpriteSheet(ImageLoader.loadImage("/textures/CandyLandSprites.png"));
		
		/*Everything is 32 x 32 on this sprite sheet, so to get to the next sprite
		 * by using the width and height values. */
		player = candyLandSheet.crop(0, 0, width, height);
		purple = candyLandSheet.crop(width, 0, width, height);
		green = candyLandSheet.crop(width * 2, 0, width, height);
		pink = candyLandSheet.crop(width * 3, 0, width, height);
		blue = candyLandSheet.crop(0, height, width, height);
		yellow = candyLandSheet.crop(width, height, width, height);
		orange = candyLandSheet.crop(width * 2, height, width, height);
		villain = candyLandSheet.crop(width * 3, height, width, height);
		cupcake = candyLandSheet.crop(0, height * 2, width, height);
		cookie = candyLandSheet.crop(width, height * 2, width, height);
		iceCream = candyLandSheet.crop(width * 2, height * 2, width, height);
		snowflake = candyLandSheet.crop(width * 3, height * 2, width, height);
		grass = candyLandSheet.crop(0, height * 3, width, height);
		white = candyLandSheet.crop(width * 2, height * 3, width, height);
		cardBack = candyLandSheet.crop(width * 3, height * 3, width, height);
		
		SpriteSheet candyLandCards = new SpriteSheet(ImageLoader.loadImage("/textures/CandyLandCards.png"));
		
		dPurpleCard = candyLandCards.crop(0, 0, width, dheight);
		dGreenCard = candyLandCards.crop(width, 0, width, dheight);
		dPinkCard = candyLandCards.crop(width * 2, 0, width, dheight);
		dBlueCard = candyLandCards.crop(width * 3, 0, width, dheight);
		dYellowCard = candyLandCards.crop(0, dheight, width, dheight);
		dOrangeCard = candyLandCards.crop(width, dheight, width, dheight);
		purpleCard = candyLandCards.crop(width * 2, dheight, width, dheight);
		greenCard = candyLandCards.crop(width * 3, dheight, width, dheight);
		
		SpriteSheet candyLandCards2 = new SpriteSheet(ImageLoader.loadImage("/textures/CandyLandCards2.png"));
		
		pinkCard = candyLandCards2.crop(0, 0, width, dheight);
		blueCard = candyLandCards2.crop(width, 0, width, dheight);
		yellowCard = candyLandCards2.crop(width * 2, 0, width, dheight);
		orangeCard = candyLandCards2.crop(width * 3, 0, width, dheight);
		cupcakeCard = candyLandCards2.crop(0, dheight, width, dheight);
		cookieCard = candyLandCards2.crop(width, dheight, width, dheight);
		iceCreamCard = candyLandCards2.crop(width * 2, dheight, width, dheight);
		snowflakeCard = candyLandCards2.crop(width * 3, dheight, width, dheight);
		
	}
}