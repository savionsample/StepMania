import java.awt.*;

import javax.swing.*;

/*********************************************************************************
 * Ball class
 * Stores all of the information about a single ball including:
 *      center, velocity, radius and color
 * It provides methods to move the ball, handle bouncing within a rectangle
 *      and draw itself.
 ***********************************************************************************/
public class BaseBar
{
  // DATA:
  private int topBar = 900;
  private int bottomBar = 990;

  
  public int getTopBar()
  {
	  return topBar;
  }
  
  public int getBottomBar()
  {
	  return bottomBar;
  }

  public void draw(Graphics g)
  {
    g.setColor(Color.red);
    g.drawRect(300, 900, 100, 90);
  }
}


