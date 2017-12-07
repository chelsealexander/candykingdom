package boardgame;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	//our sprite sheet is nothing but a buffered image that we name sheet
	private BufferedImage sheet;
	
	//we tell the SpriteSheet that it takes in a buffered image called sheet
	public SpriteSheet(BufferedImage sheet){
		this.sheet = sheet;
	}
	
	//Then we take that sheet and return a subimage of that sheet, effectively cropping it
	public BufferedImage crop(int x, int y, int width, int height){
		return sheet.getSubimage(x, y, width, height);
	}
}
