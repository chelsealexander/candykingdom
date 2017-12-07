package boardgame.cards;

import java.awt.image.BufferedImage;

import boardgame.Assets;

public class CardBack extends Cards {

	public CardBack(int cardID) {
		super(Assets.cardBack, cardID);
		
	}
	
	@Override
	public boolean isSingle(){
		return false;
	}
	
	public boolean isDouble(){
		return false;
	}
	
}

