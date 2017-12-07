package boardgame.cards;

import java.awt.image.BufferedImage;

import boardgame.Assets;

public class CookieCard extends Cards{

	public CookieCard(int cardID) {
		super(Assets.cookieCard, cardID);
	
	}
	
	@Override
	public boolean isSingle(){
		return false;
	}
	
	public boolean isDouble(){
		return false;
	}
	

}
