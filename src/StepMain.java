import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.Color;
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
	
	private int counter = 0;
	private int pos = 0;
	
	private static final int MAX_WIDTH = 1000;  // Window size
	private static final int MAX_HEIGHT = 1100;  // Window size
	private static final int TOP_OF_WINDOW = 22; // Top of the visible window
	private static final int DELAY_IN_MILLISEC = 30; // Time delay between ball
	
	private int score = 0;

	public static void main(String[] args) 
	{
		FileInput.fileInput();
		interpretText();
		System.out.println(FileInput.arr.size());
		 StepMain sm = new StepMain(); // creates the window
		 sm.addKeyListener(sm);
	}
	
	public StepMain()
	{
		System.out.println(FileInput.arr.size());
		bar = new BaseBar();
		
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
	      playClip("drumWav.wav");
	      if (checkIfHit())
	      {
	    	  score++;
	      }
	    }
	    else if (keyCode == KeyEvent.VK_F)
	    {
	    	playClip("drumWav.wav");
	    }
	    else if (keyCode == KeyEvent.VK_J)
	    {
	    	playClip("drumWav.wav");
	    }
	    else if (keyCode == KeyEvent.VK_K)
	    {
	    	playClip("drumWav.wav");
	    }
	    else if (keyCode == KeyEvent.VK_1)
	    {
	    	playClip("catmeow.wav");
	    }


	  repaint();
	 }
	 
	 public boolean checkIfHit()
	 {
		 // implement later: use for each loop to check each individual note so two notes at the same
		 // time give 2 points instead of 1
		 return noteList.get(0).getY() > bar.getTopBar() && 
				 noteList.get(0).getBottomY() < bar.getBottomBar();
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

/*		for (int i = 0; i < notes.length; i++)
		{
			g.setColor(Color.blue);
			notes[i].draw(g);
		}*/
		
		for (Note n : noteList)
		{
			n.draw(g);
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
		
		String letterOnly = FileInput.arr.get(pos).substring(0,1);
		String numberOnly = FileInput.arr.get(pos).substring(1);
		
		/*if(Integer.parseInt(numberOnly) == counter)
		{
			if (letterOnly.equals("B"))
			{
				g.setColor(Color.blue);
				notes.draw(g);
			}
		}
		counter++;*/
		

		
		
	}
	
	public static void interpretText()
	{
		for(String s: FileInput.arr)
		{
			String letterOnly = s.substring(0,1);
			String numberOnly = s.substring(1);
			
			if(letterOnly.equals("A"))
			{
				noteList.add(new Note(300));
			}
			else if (letterOnly.equals("B"))
			{
				noteList.add(new Note(400));
			}
			else if(letterOnly.equals("C"))
			{
				noteList.add(new Note(500));
			}
			else if(letterOnly.equals("D"))
			{
				noteList.add(new Note(600));
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
			clip.start();
		}
		catch (Exception exc)
		{
			exc.printStackTrace(System.out);
		}
	}

}
