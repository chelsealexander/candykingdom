package boardgame.states;

import java.awt.Graphics;

import boardgame.Handler;
import boardgame.states.State;

public abstract class State {

	//Stores our State object and allows us to set and get it
		private static State currentState = null;
		
		//set state method passes in a state object and makes that state the current one
		public static void setState(State state){
			currentState = state;
		}
		
		//get state just shows us what state we're in
		public static State getState(){
			return currentState;
		}
		
		//Class stuff:
		protected Handler handler;
		
		public State(Handler handler){
			this.handler = handler;
		}
		
		//These abstract methods are the methods that every state extending this must have
		public abstract void tick();
		
		public abstract void render(Graphics g);
}
