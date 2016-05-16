/*********************************************************************************
 * Ball class
 * Stores all of the information about a single ball including:
 *      center, velocity, radius and color
 * It provides methods to move the ball, handle bouncing within a rectangle
 *      and draw itself.
 ***********************************************************************************/
import java.awt.*;
import javax.swing.*;

class Note
{
  private int time;
  private int x;
  private int y;
  private int length = 100;
  private int width = 50; 
  private Color color;
  
  private final int SPEED = 7;
  
  private boolean alreadyHit = false;
  private boolean alreadyCheckedAcc = false;
  

  /**
   * Ball constructor initializes the Ball object
   *
   * @param xIn       x coordinate of center
   * @param yIn       y coordinate of center
   * @param dxIn      x velocity
   * @param dyIn      y velocity
   * @param radiusIn  radius
   * @param colorIn   color
   */
  public Note (int xIn, int xNumber)
  {
    x = xIn;
    time = xNumber;
    y = -25 * time;
    
    if (x == 300)
    {
    	color = Color.blue;
    }
    else if (x == 400)
    {
    	color = Color.green;
    }
    else if (x == 500)
    {
    	color = Color.red;
    }
    else if (x == 600)
    {
    	color = Color.yellow;
    }
  }
  
  public int getY()
  {
	  return y;
  }
  
  public int getBottomY()
  {
	  int yBottom = y + width;
	  return yBottom;
  }


  // move the note downwards toward the bar
  public void move()
  {
    y = y + SPEED;
  }
  
  public void setHit()
  {
	  alreadyHit = true;
  }
  
  public boolean getAlreadyHit()
  {
	  return alreadyHit;
  }
  
  public void setCheckedAcc()
  {
	  alreadyCheckedAcc = true;
  }
  
  public boolean getAlreadyCheckedAcc()
  {
	  return alreadyCheckedAcc;
  }
  public void draw(Graphics g)
  {
    g.setColor(color);

    g.fillRect(x, y, length, width); 
  }
}


