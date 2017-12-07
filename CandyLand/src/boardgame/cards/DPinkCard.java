package boardgame.cards;

import java.awt.image.BufferedImage;

import boardgame.Assets;

public class DPinkCard extends Cards {

	public DPinkCard(int cardID) {
		super(Assets.dPinkCard, cardID);
		
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
