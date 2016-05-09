import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class StepMain extends JFrame implements ActionListener, KeyListener
{
	
	private Note[] notes;
	private BaseBar bar;
	
	private static final int MAX_WIDTH = 1000;  // Window size
	private static final int MAX_HEIGHT = 1100;  // Window size
	private static final int TOP_OF_WINDOW = 22; // Top of the visible window
	private static final int DELAY_IN_MILLISEC = 30; // Time delay between ball
	
	private int score = 0;

	public static void main(String[] args) 
	{
		 StepMain sm = new StepMain(); // creates the window
		 sm.addKeyListener(sm);
	}
	
	public StepMain()
	{
		
		notes = new Note[1];
		bar = new BaseBar();

		for (int i = 0; i < 1; i++)
		{
			int x = 300;
			int y = 0;
			int noteSpeed = 7;
	
		    notes[i] = new Note(x, y, noteSpeed);
		}
		
		setSize(MAX_WIDTH, MAX_HEIGHT);
		setVisible(true);
		
		Timer clock = new Timer(DELAY_IN_MILLISEC, this);
		clock.start(); 
	}
	
	 public void keyPressed(KeyEvent e)     // #4A
	  {
	    int keyCode = e.getKeyCode();
	    if (keyCode == KeyEvent.VK_D)
	    {
	      if (checkIfHit())
	      {
	    	  score++;
	    	  
	      }
	    }
	    else if (keyCode == KeyEvent.VK_F)
	    {

	    }
	    else if (keyCode == KeyEvent.VK_J)
	    {

	    }
	    else if (keyCode == KeyEvent.VK_K)
	    {

	    }


	  repaint();
	 }
	 
	 public boolean checkIfHit()
	 {
		 return notes[0].getY() > bar.getTopBar() && 
				 notes[0].getBottomY() < bar.getBottomBar();
	 }


	  // Called when typing of a key is completed
	  // Required for any KeyListener
	  // @param e  Contains info about the key typed
	  // keyTyped = find chars that are typed,
	  // keyPressed = to obtain raw key presses
	 public void keyTyped(KeyEvent e)     // #4B
	 {
		 
	 }

	  // Called when a key is released
	  // Required for any KeyListener
	  // @param e  Contains info about the key released.
	 public void keyReleased(KeyEvent e)     // #4C
	 {
		 
	 }
	 
	 public void actionPerformed(ActionEvent e) // NEW #5 !!!!!!!!!!
	{
		for (int i = 0; i < 1; i++)
		{
			notes[i].move();
			//notes[i].bounce(0, MAX_WIDTH, TOP_OF_WINDOW, MAX_HEIGHT); // side of screen
		}

		repaint();
	}
	
	public void paint(Graphics g)
	{
		// Clear the window.
		g.setColor(Color.white);
		g.fillRect(0, 0, MAX_WIDTH, MAX_HEIGHT);

		for (int i = 0; i < notes.length; i++)
		{
			g.setColor(Color.blue);
			notes[i].draw(g);
		}
		
		for (int i = 0; i < 4; i++)
		{
			g.drawRect(300 + i * 100, 900, 100, 90);
		}
		
		for (int i = 0; i < 5; i++)
		{
			g.setColor(Color.black);
			g.drawLine(300 + 100 * i, 20, 300 + 100 * i, 900);
		}
		
		bar.draw(g);
		
		g.setColor(Color.green);
		g.setFont(new Font("Monospaced", Font.BOLD, 50)); 
		g.drawString("Score " + score, 700, 100);
	}

}
