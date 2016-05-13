import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;


public class StepMain extends JFrame implements ActionListener, KeyListener
{
	
	private static ArrayList<Note> noteList = new ArrayList<Note>();
	private BaseBar bar;
	
	private int pos = 0;
	
	private static final int MAX_WIDTH = 1100;  // Window size
	private static final int MAX_HEIGHT = 1100;  // Window size
	private static final int TOP_OF_WINDOW = 22; // Top of the visible window
	private static final int DELAY_IN_MILLISEC = 30; // Time delay between ball
	
	private static final int ROW_ONE = 300;
	private static final int ROW_TWO = 400;
	private static final int ROW_THREE = 500;
	private static final int ROW_FOUR = 600;
	
	private int score = 0;
	private int totalPossibleScore;
	
	int index = 0;
	

	public static void main(String[] args) 
	{
		playClip("TearInMyHeart.wav");
		FileInput.readInput();
		interpretText();
		System.out.println(FileInput.readInput().size());
		 StepMain sm = new StepMain(); // creates the window
		 sm.addKeyListener(sm);
	}
	
	public StepMain()
	{
		System.out.println(FileInput.readInput().size());
		bar = new BaseBar();
		
		setSize(MAX_WIDTH, MAX_HEIGHT);
		setVisible(true);
		
		Timer clock = new Timer(DELAY_IN_MILLISEC, this);
		clock.start(); 
	}
	
	 public void keyPressed(KeyEvent e)
	  {
	    int keyCode = e.getKeyCode();
	    if (keyCode == KeyEvent.VK_D)
	    {
	        playClip("drumWav.wav");
	        if (checkIfHit(index))
	        {
	    	    noteList.get(index).setHit();
	    	    score++;
	        }
	    }
	    else if (keyCode == KeyEvent.VK_F)
	    {
	    	playClip("drumWav.wav");
	    	if (checkIfHit(index))
		    {
	    		noteList.get(index).setHit();
		    	score++;
		    }
	    }
	    else if (keyCode == KeyEvent.VK_J)
	    {
	    	playClip("drumWav.wav");
	    	if (checkIfHit(index))
		    {
	    		noteList.get(index).setHit();
		    	score++;
		    }
	    }
	    else if (keyCode == KeyEvent.VK_K)
	    {
	    	playClip("drumWav.wav");
	    	if (checkIfHit(index))
		    {
	    		noteList.get(index).setHit();
		    	score++;
		    }
	    }
	    else if (keyCode == KeyEvent.VK_1)
	    {
	    	playClip("catmeow.wav");
	    }
	    


	  repaint();
	 }
	 
	 public boolean checkIfHit(int col)
	 {
		 // implement later: use for each loop to check each individual note so two notes at the same
		 // time give 2 points instead of 1
		 
		 if (totalPossibleScore != 0)
		 {
			 col = col % noteList.size();
		 }
		 
		 return noteList.get(col).getY() > bar.getTopBar() && 
				 noteList.get(col).getBottomY() < bar.getBottomBar() &&
				 !noteList.get(col).getAlreadyHit();
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
		//moving the notes
		for(Note n : noteList)
		{
			n.move();
		}
		repaint();
	}
	
	public void paint(Graphics g)
	{
		// Clear the window.
		g.setColor(Color.white);
		g.fillRect(0, 0, MAX_WIDTH, MAX_HEIGHT);
		
		// draw the notes
		for (Note n : noteList)
		{
			g.setColor(Color.blue);
			n.draw(g);
		}
		
		// draw the bars
		for (int i = 0; i < 4; i++)
		{
			g.drawRect(300 + i * 100, 900, 100, 90);
		}
		
		// draw the columnns
		for (int i = 0; i < 5; i++)
		{
			g.setColor(Color.black);
			g.drawLine(300 + 100 * i, 20, 300 + 100 * i, 900);
		}
		
		////
		for (Note n: noteList)
		{
			if (n.getY() > bar.getBottomBar() && !n.getAlreadyCheckedAcc())
			{
				n.setCheckedAcc();
				totalPossibleScore++;
				index++;
			}
		}
		
		
		bar.draw(g);
		
		g.setColor(Color.green);
		g.setFont(new Font("Monospaced", Font.BOLD, 50)); 
		g.drawString("Score " + score, 700, 100);
		
		if (totalPossibleScore == 0)
		{
			g.drawString("Acc: 100%", 700, 300);
		}
		else
		{
			double acc = (double)score / (double)totalPossibleScore * 100;
			g.drawString("Acc: " + acc + "%", 700, 300);
		}
		
	
	}
	
	public static void interpretText()
	{
		for(String s: FileInput.readInput())
		{
			String letterOnly = s.substring(0,1);
			String numberOnly = s.substring(1);
			
			if(letterOnly.equals("A"))
			{
				noteList.add(new Note(ROW_ONE, Integer.parseInt(numberOnly)));
			}
			else if (letterOnly.equals("B"))
			{
				noteList.add(new Note(ROW_TWO, Integer.parseInt(numberOnly)));
			}
			else if(letterOnly.equals("C"))
			{
				noteList.add(new Note(ROW_THREE, Integer.parseInt(numberOnly)));
			}
			else if(letterOnly.equals("D"))
			{
				noteList.add(new Note(ROW_FOUR, Integer.parseInt(numberOnly)));
			}
			
		}
	}
	
	public static void playClip(String filename) // Method that plays the sound
	{
		try
		{
			File audioFile = new File(filename);
			final Clip clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));
			clip.addLineListener(new LineListener()
			{
				@Override
				public void update(LineEvent event)
				{
					if (event.getType() == LineEvent.Type.STOP)
						clip.close();
				}
			});
			clip.open(AudioSystem.getAudioInputStream(audioFile));
			//clip.start();
			clip.loop(0);
		}
		catch (Exception exc)
		{
			exc.printStackTrace(System.out);
		}
	}

}