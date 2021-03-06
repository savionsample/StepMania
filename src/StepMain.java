import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.sampled.*;

// by Ben Z, Savion, Alex, Nitya

public class StepMain extends JFrame implements ActionListener, KeyListener
{
	
	private static ArrayList<Note> noteList = new ArrayList<Note>();
	private BaseBar bar;

	private static final int MAX_WIDTH = 1100;  // Window size
	private static final int MAX_HEIGHT = 1100;  // Window size
	private static final int DELAY_IN_MILLISEC = 30; // Time delay

	public static final int COL_ONE = 300;
	public static final int COL_TWO = 400;
	public static final int COL_THREE = 500;
	public static final int COL_FOUR = 600;

	private int index = 0;
	private int score = 0;
	private int totalPossibleScore;

	private boolean perfectAcc = false;
	
	private static String notes = "";

	public static void main(String[] args) 
	{
		Scanner console = new Scanner(System.in);
		System.out.println("print song name with no spaces: Paris, TearInMyHeart, Dragonforce, Freeze");
		notes = console.nextLine();
		playClip(notes + ".wav"); //PLAYING SONG CLIP
		
		interpretText();
		StepMain sm = new StepMain();
		sm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sm.addKeyListener(sm);
	}

	public StepMain()
	{
		bar = new BaseBar();
		
		setSize(MAX_WIDTH, MAX_HEIGHT);
		setVisible(true);
		createBufferStrategy(2);

		Timer clock = new Timer(DELAY_IN_MILLISEC, this);
		clock.start(); 
	}

	public void keyPressed(KeyEvent e)
	{
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_D)
		{
			playClip("drumWav.wav");
			if (checkIfHit(index, COL_ONE))
			{
				noteList.get(index).setHit();
				score++;
			}
		}
		else if (keyCode == KeyEvent.VK_F)
		{
			playClip("drumWav.wav");
			if (checkIfHit(index, COL_TWO))
			{
				noteList.get(index).setHit();
				score++;
			}
		}
		else if (keyCode == KeyEvent.VK_J)
		{
			playClip("drumWav.wav");
			if (checkIfHit(index, COL_THREE))
			{
				noteList.get(index).setHit();
				score++;
			}
		}
		else if (keyCode == KeyEvent.VK_K)
		{
			playClip("drumWav.wav");
			if (checkIfHit(index, COL_FOUR))
			{
				noteList.get(index).setHit();
				score++;
			}
		}
		else if (keyCode == KeyEvent.VK_1)
		{
			// extra secret special clip!!!1!1!11
			playClip("catmeow.wav");
		}
		else if (keyCode == KeyEvent.VK_2)
		{
			playClip("Friday.wav");
		}
		else if (keyCode == KeyEvent.VK_3)
		{
			playClip("Fetch.wav");
		}
		else if (keyCode == KeyEvent.VK_0) 
		{ 
			perfectAcc = true; 
		}
		repaint();
	}

	public boolean checkIfHit(int col, int row) // row = up down
	{
		if (totalPossibleScore != 0)
		{
			col = col % noteList.size();
		}

		return noteList.get(col).getY() > bar.getTopBar() && 
				noteList.get(col).getBottomY() < bar.getBottomBar() &&
				noteList.get(col).getX() == row &&
				!(noteList.get(col).getAlreadyHit());
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
		BufferStrategy bf = this.getBufferStrategy();
		if (bf == null)
			return;

		Graphics g2 = null;

		try 
		{
			g2 = bf.getDrawGraphics();

			// myPaint does the actual drawing
			myPaint(g2);
		} 
		finally 
		{
			// It is best to dispose() a Graphics object when done with it.
			g2.dispose();
		}

		// Shows the contents of the backbuffer on the screen.
		bf.show();

		//Tell the System to do the Drawing now, otherwise it can take a few extra ms until 
		//Drawing is done which looks very jerky
		Toolkit.getDefaultToolkit().sync();		
	}

	public void myPaint(Graphics g)
	{
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
			g.setColor(Color.blue);
			g.drawRect(300 + i * 100, 700, 100, 90);
		}

		// draw the columnns
		for (int i = 0; i < 5; i++)
		{
			g.setColor(Color.black);
			g.drawLine(300 + 100 * i, 20, 300 + 100 * i, 700);
		}

		for (Note n : noteList)
		{
			if (n.getY() > bar.getBottomBar() && !n.getAlreadyCheckedAcc())
			{
				n.setCheckedAcc();
				totalPossibleScore++;
				index++;
			}
		}

		g.setColor(Color.green);
		g.setFont(new Font("Monospaced", Font.PLAIN, 50)); 
		g.drawString("Score " + score, 700, 100);
		
		if (perfectAcc)
		{
			g.drawString("Acc: 100.0%", 700, 300);
		}
		else
		{
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
	}

	public static void interpretText()
	{
		for(String s: FileInput.readInput(notes))
		{
			int speed = 0;
			int multiplier = 0;
			if (notes.equals("Paris"))
			{
				speed = 11;
				multiplier = -15;
			}
			else if (notes.equals("TearInMyHeart"))
			{
				speed = 8;
				multiplier = -25;
			}
			else if (notes.equals("Dragonforce"))
			{
				speed = 42;
				multiplier = -15;
			}
			else if (notes.equals("Freeze"))
			{
				speed = 42;
				multiplier = -15;
			}
			
			String letterOnly = s.substring(0,1);
			String numberOnly = s.substring(1);

			if(letterOnly.equals("A"))
			{
				noteList.add(new Note(COL_ONE, Integer.parseInt(numberOnly), speed, multiplier));
			}
			else if (letterOnly.equals("B"))
			{
				noteList.add(new Note(COL_TWO, Integer.parseInt(numberOnly), speed, multiplier));
			}
			else if(letterOnly.equals("C"))
			{
				noteList.add(new Note(COL_THREE, Integer.parseInt(numberOnly), speed, multiplier));
			}
			else if(letterOnly.equals("D"))
			{
				noteList.add(new Note(COL_FOUR, Integer.parseInt(numberOnly), speed, multiplier));
			}
		}
	}

	public static void playClip(String filename)
	{
		try
		{
			File audioFile = new File(filename);
			final BigClip clip = new BigClip();
			
	        AudioInputStream ais = AudioSystem.getAudioInputStream(audioFile);
	        clip.open(ais);
	        clip.start();

			
			clip.addLineListener(new LineListener()
			{
				@Override
				public void update(LineEvent event)
				{
					if (event.getType() == LineEvent.Type.STOP)
						clip.close();
				}
			});		
			
		}
		catch (Exception exc)
		{
			exc.printStackTrace(System.out);
		}
	}

}