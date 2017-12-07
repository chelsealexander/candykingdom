package boardgame;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {

	private JFrame frame;
	private Canvas canvas;
	private String title;
	private int width, height;
	
	public Display(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		
		createDisplay();
	}
	
	private void createDisplay(){
		frame = new JFrame(title);
		frame.setSize(width, height);
		
		/*We need to make sure we can close out of the frame! */
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*We don't want anyone messing with the size of the board  */
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		/*We need to make sure we can see it! */
		frame.setVisible(true);
		
		/*The frame is the picture frame, and the canvas is what we paint to
		 * so we need to make sure we have a canvas to paint on and that
		 * it matches the dimensions of our frame, unless we want the frame
		 * to be larger for whatever reason. Here, we don't. */
		canvas = new Canvas();
		
		/*Set ALLLL the sizes to cover all bases */
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		
		/*This is the change from the tutorial.
		 * In the tutorial, canvas is NOT focusable
		 * However, if the canvas isn't focusable, it doesn't pick up
		 * on the mouse movements. So until this breaks something
		 * we're going to use this.*/
		canvas.setFocusable(true);
		
		/*Don't forget to add the canvas to the frame */
		frame.add(canvas);
		
		/*This makes sure the size is set to the correct dimensions */
		frame.pack();
	}
	
	/*Eventually we'll need methods to get the frame and canvas
	 *so let's put those here; just tell it to return those objects
	 *and voila, we're done. */
	public Canvas getCanvas(){
		return canvas;
	}
	
	public JFrame getFrame(){
		return frame;
	}
	
}
