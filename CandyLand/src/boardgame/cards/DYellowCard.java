package boardgame.cards;

import java.awt.image.BufferedImage;

import boardgame.Assets;

public class DYellowCard extends Cards {

	public DYellowCard(int cardID) {
		super(Assets.dYellowCard, cardID);
		
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
