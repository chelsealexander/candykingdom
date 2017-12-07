package boardgame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
	public boolean[] keys;
	public boolean up, down, left, right;
	
	/*Our KeyManager method creates booleans for all 256 keys in an array
	 *Each key can be pressed (true) or not pressed (false) */
	
	public KeyManager(){
		keys = new boolean[256];
	}
	
	/*These control the Up, Down, Left, and Right arrows that are
	 * NOT on the NumPad. The NumPad ones are VK_KP_UP etc.
	 * So this takes the boolean up and stores the value of the keys boolean
	 * As it relates to whether or not that key is being pressed.*/
	public void tick(){
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
	}
	
	/*KeyManager inherits from an abstract class
	 *This means that it must implement the same methods
	 *From that abstract class. That's what all the
	 *@Override stuff is about. */
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		System.out.println("Pressed!");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
			
	}
	
}
