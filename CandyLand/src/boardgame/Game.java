package boardgame;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import boardgame.input.ClickManager;
import boardgame.input.KeyManager;
import boardgame.input.TurnManager;
import boardgame.states.State;
import boardgame.states.GameState;
import boardgame.Handler;
import boardgame.cards.Cards;

/*This is really our main class, where the meat of everything ends up
 * It implements "runnable" because this is going to run on its own thread */
public class Game implements Runnable {
	
	/*These tell us if the game is running, how to make the display
	 * and that we're going to run this game on its own thread */
	private boolean running = false;
	private Display display;
	private Thread thread;
	
	//We need these for our initialization
	public String title;
	private int width, height;
	
	//our buffer strategy and graphics objects are very important
	private BufferStrategy bs;
	private Graphics g;
	
	//input
	private KeyManager keymanager;
	private ClickManager clickmanager;
	
	//here's our handler
	private Handler handler;
	
	//States
	private State gameState;
	
	//Turns
	private TurnManager turnmanager;
	
	//timedelay for our AI
	private int AITimeDelay;
	
	//This is for figuring out if we need to shuffle the cards and shuffling them
	//We also initialize the new hand just in case.
	
	Random random = new Random();
	public int shuffleDeck = random.nextInt(16);
	public int newHand = shuffleDeck - 1;
	public int timeDelay;
	public int clickCount = 0;
	private int cardID;
	private boolean cardWasPlayed= false;
	
	
	
	/*We need to construct our game object for the launcher. */
	
	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
		keymanager = new KeyManager();
		clickmanager = new ClickManager();
		turnmanager = new TurnManager(1);
		
	
}
	
	 
	/*This method will initialize the game */
	private void initialize(){
		display = new Display(title, width, height);
		
		//initialize our assets...
		Assets.initialize();

		/*THIS IS A CHANGE from the tutorial. In the tutorial,
		 * we used getFrame not getCanvas, and the FRAME not the canvas
		 * was focusable. However, the mouse clicks the canvas not the frame
		 * so we're going to use this until it breaks. */
		display.getFrame();
		
		display.getCanvas().addMouseListener(clickmanager);
		display.getCanvas().addKeyListener(keymanager);
		
		handler = new Handler(this);
		
		/*our gamestate basically contains everything that's going to show up on the screen
		 *so we create the object as part of our GameState class and then set it
		 *if we had a different state, we could set it like MenuState(handler) .setState(menustate) etc */
		
		gameState = new GameState(handler);
		State.setState(gameState);
	}
	
	/* We'll be using our tick and render methods within our tool for refreshing the screen,
	 * so we need to make those now.*/
	
	
	private void tick() {
		//figure out if a key has been pressed or something has been clicked
		keymanager.tick();
		clickmanager.tick();
		
		
		//and then go ahead and update the game state
		if(State.getState() != null){
			State.getState().tick();
		}
	}
	
	//This method "renders" our game (paints it to the screen)
	private void render(){
		
		/*our bufferstrategy object is set equal to the canvas 
		 * (which we ask to go get the buffer strategy)
		 * if there isn't one, we ask the display to get the canvas and
		 * ask the canvas to go ahead and make one (well, three) and then return */
		
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		/* our graphics object will now store the buffer strategy
		 * which we ask to go retrieve the graphics*/
		
		g = bs.getDrawGraphics();
		
		//clear the screen
		g.clearRect(0,0,width,height);
		
		//Draw Here!
		
		
		if(State.getState() != null){
			State.getState().render(g);
		  }
		
		if (timeDelay > 0){
			timeDelay--;
			return;
			}
		
			/*figure out if we need to get a new card and shuffle the deck*/
		
		//go fetch the turn manager and shuffle the deck if it's the player's turn, phase one.
		if (turnmanager.getTurn() == 1){
			
				
			
				if (handler.getClickManager().isCardClicked()){
					
					
					
					//this works like actual cards, kind of. We get the last card on top of the deck
					//and then shuffle the deck again (because we only have a few cards)
					//if we got the same card, go one card down instead because getting
					//the same card over and over is annoying
					if (newHand == shuffleDeck){
						newHand -= 1;
					} else{
						newHand = shuffleDeck;
					}
					shuffleDeck = random.nextInt(16);
					cardID = newHand;
					
					//set timeDelay to 15 instead of 60
					timeDelay = 15;
					clickCount = clickCount + 1;
					
					//set card clicked back to false or it will run forever
					handler.getClickManager().setCardClicked(false);
					
					//set the turn to phase 2, now it isTimeToMove in the turnManager
					turnmanager.setTurn(2);
					
					//testing
					return;
				}
				
		}
		
		if (turnmanager.getTurn() == 3){
			
			//only do this if we haven't yet played a card this turn
			
			if (cardWasPlayed == false){
			//immediately set the value to true
				cardWasPlayed = true;
				if (newHand == shuffleDeck){
					newHand -= 1;
				} else{
					newHand = shuffleDeck;
				}
				shuffleDeck = random.nextInt(16);
				cardID = newHand;
			
			//we need to set the time delay somehow
			AITimeDelay = 30;
			
			}
		}
				
		
				if (clickCount >= 1){
				//draw our new hand, but only after we've clicked the deck.
				Cards.deck[newHand].render(g, 800, 620);
				}
				
				//draw deck here, 1020, 620
				//we're going to draw right over this, but there are really cards underneath
				//Technically we don't need them there but whatever.
				
				
				Cards.deck[shuffleDeck].render(g, 1020, 620);
				
				//Draw the back of the card over the deck by drawing this after
				Cards.deck[16].render(g, 1020, 620);
		
		
		
		//End Drawing!
		
		//show the bufferstrategy and then dispose of the graphics
		bs.show();
		g.dispose();
	}
	
	
	public void run(){
		/*Let's create our method for refreshing the screen.*/
		/*We need an integer to count from 0 up to 1, so this double asks, is it one second yet? */
	
		initialize();
		
		double oneSecond = 0;
	
		/* One billion nano seconds over 30 should set our frames-per-second to thirty 
		 * Get the system time in nanoseconds to keep this consistent*/
	
		//FPS is at 10 frames per second? Why? Because the shuffle works weird
		//Need to figure out how to fix it before attempting to up the framerate
		
		//let's change fps to 15
		double fps = 1000000000 / 15;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
	
		while (running){
			now = System.nanoTime();
			oneSecond += (now - lastTime) / fps;
			timer += (now - lastTime);
			lastTime = now;
		
			if (oneSecond >= 1){
				tick();
				render();
				ticks++;
				oneSecond--;
			}
		
			/* This next bit is just for us to see if it's working properly in the console. */
			if (timer >= 1000000000){
				ticks = 0;
				timer = 0;
			}
		}
	
	/*If we're outside of this loop, we're no longer running and we need to call the stop
	*to stop the thread. */
	
	stop();	
		
	}
	
	//here are some methods to get things if we need them
	
	public KeyManager getKeyManager(){
		return keymanager;
	}
	
	public ClickManager getClickManager(){
		return clickmanager;
	}
	
	public TurnManager getTurnManager(){
		return turnmanager;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getClickCount(){
		return clickCount;
	}
	
	public int getCardID(){
		return cardID;
	}
	
	public boolean wasCardPlayed(){
		return cardWasPlayed;
	}
	
	public void setCardPlayed(boolean cardPlayed){
		cardWasPlayed = cardPlayed;
	}
	
	public int getAITimeDelay(){
		return AITimeDelay;
	}
	
	public void setAITimeDelay(int newDelay){
		AITimeDelay = newDelay;
	}
	/*This is our thread! This will help the running of our game. First
	 * we need to start it up. If it's running, get out of this method because
	 * something has gone wrong. Otherwise, change the running variable to true
	 * make a new thread, and start it up. */
	public synchronized void start(){
		if (running){
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	/*Now we need to figure out how to stop this thing. If we're already
	 * not running, then just return! Otherwise, we have this auto try-catch
	 * That will hopefully shut it down. */
	public synchronized void stop(){
		if (!running){
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
