package boardgame.cards;

import java.awt.image.BufferedImage;

import boardgame.Assets;

public class SnowflakeCard extends Cards{

	public SnowflakeCard(int cardID) {
		super(Assets.snowflakeCard, cardID);
	}
	
	@Override
	public boolean isSingle(){
		return false;
	}
	
	public boolean isDouble(){
		return false;
	}

}
