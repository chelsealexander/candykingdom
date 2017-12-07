package boardgame.cards;

import java.awt.image.BufferedImage;

import boardgame.Assets;

public class DBlueCard extends Cards {

	public DBlueCard(int cardID) {
		super(Assets.dBlueCard, cardID);
		
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
