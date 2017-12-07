package boardgame.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import boardgame.Handler;
import java.util.Random;



public class ClickManager implements MouseListener {
	
	
	public boolean mouseClick = false;
	public int mouseX;
	public int mouseY;
	public boolean cardClicked = false;
	public boolean mouseReleased = false;
	public boolean mouseHeld = false;
	
	//testing
	private boolean inBoundsX;
	private boolean inBoundsY;
	
	

	
	
	/*Whenever the mouse is pressed, get the x & y coordinates, print them
	 *And then print them to the console and set our mouseClick boolean to true */
	@Override
	public void mouseClicked (MouseEvent e) {
	}
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		mouseClick = true;
	}
	
/*Once the mouse is released, set our mouseClick back to false*/
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
		 mouseReleased = true;
		 cardClicked = false;
		 mouseClick = false;
		 
	}
	
	//take the boolean value we created and use it to control a method called isMouseClicked()
	public boolean isMouseClicked(){
		return mouseClick;
	}
	
	//we need a way to access the x and y of the mouse outside of this class, sooooo
	public int getMouseX(){
		return mouseX;
	}
	
	public int getMouseY(){
		return mouseY;
	}
	
	//TEST one more time
	public void tick() {

		if (this.getMouseX() >= 1020 && this.getMouseX() <= 1084){
			inBoundsX = true;
		} else {
			inBoundsX = false;
		}

		if (this.getMouseY() >= 620 && this.getMouseY() <= 768){
			inBoundsY = true;
		} else {
			inBoundsY = false;
		}

		//Testing
		
		
		if(mouseClick == true && inBoundsX && inBoundsY){
			System.out.println("MADE IT.");
			cardClicked = true;
			mouseClick = false;
		}

	}
	
	//make a method to check if the card is clicked by retrieving our boolean
	public boolean isCardClicked(){
		return cardClicked;
	}
	
	public void setCardClicked(boolean thisCardClicked){
		cardClicked = thisCardClicked;
	}
	

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
