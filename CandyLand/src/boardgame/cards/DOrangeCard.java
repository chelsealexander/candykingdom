package boardgame.cards;

import java.awt.image.BufferedImage;

import boardgame.Assets;

public class DOrangeCard extends Cards {

	public DOrangeCard(int cardID) {
		super(Assets.dOrangeCard, cardID);
		
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
