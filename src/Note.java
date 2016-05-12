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
  // DATA:
  private int x;
  private int y= 0;
  private int length = 100; // ----
  private int width = 50; // |    |
  private Color color = Color.blue;    // Color of the ball
  private int speed = 7;
  
  private int time;
  
  private boolean flash = false;

  // METHODS:

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
  public Note (int xIn)
  {
    x = xIn;
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


  // Move the ball.  Add the velocity to its center.
  public void move()
  {
    y = y + speed;
  }

 
  public void draw(Graphics g)
  {
    g.setColor(color);

    g.setColor(Color.blue);
    g.fillRect(x, y, length, width); 
    
    
  }
}


