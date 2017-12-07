package boardgame;

public class Launcher {
	public static void main (String[] args) {
		//Let's get our game object, title it, and set its height and width
		Game game = new Game("Candy Kingdom", 1280, 760);
		
		//Then go ahead and fire it up
		game.start();
	}
}
