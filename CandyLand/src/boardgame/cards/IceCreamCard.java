package boardgame.cards;

import java.awt.image.BufferedImage;

import boardgame.Assets;

public class IceCreamCard extends Cards {

	public IceCreamCard(int cardID) {
		super(Assets.iceCreamCard, cardID);
		
	}
	
	@Override
	public boolean isSingle(){
		return false;
	}
	
	@Override
	public boolean isDouble(){
		return false;
	}

}
