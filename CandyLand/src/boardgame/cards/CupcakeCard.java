package boardgame.cards;

import java.awt.image.BufferedImage;

import boardgame.Assets;

public class CupcakeCard extends Cards{

	public CupcakeCard(int cardID) {
		super(Assets.cupcakeCard, cardID);
		
	}
	
	@Override
	public boolean isSingle(){
		return false;
	}
	
	public boolean isDouble(){
		return false;
	}

}
