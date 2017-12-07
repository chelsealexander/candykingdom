package boardgame.cards;

import java.awt.image.BufferedImage;

import boardgame.Assets;

public class DGreenCard extends Cards {

	/*The way these cards work is that we are going to pass in (construct) the ID from the array
	 * Then we're going to get the correct image and pass that back up.*/
	public DGreenCard(int cardID) {
		super(Assets.dGreenCard, cardID);
		
	}
	
	@Override
	public boolean isSingle(){
		return false;
	}
	
	@Override
	public boolean isDouble(){
		return true;
	}

}
