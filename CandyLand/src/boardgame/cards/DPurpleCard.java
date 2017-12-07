package boardgame.cards;

import java.awt.image.BufferedImage;

import boardgame.Assets;

public class DPurpleCard extends Cards {

	public DPurpleCard(int cardID) {
		super(Assets.dPurpleCard, cardID);
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
