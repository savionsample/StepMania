/*********************************************************************************
 * Note class
 * Stores all of the information about a single note including:
 * x pos, y pos, length of note, width of note, and "time" the note should come in
 ***********************************************************************************/
import java.awt.*;

class Note
{
  private int x;
  private int y;
  private int time;
  private int length = 100;
  private int width = 50; 
  private Color color;
  
  
  private int speed;
  private int multiplier;

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
  public Note (int xIn, int t, int noteSpeed, int noteMultiplier)
  {
	speed = noteSpeed;
	multiplier = noteMultiplier;
	  
    x = xIn;
    time = t;
    y = multiplier * time;
   
    if (x == StepMain.COL_ONE)
    {
    	color = Color.blue;
    }
    else if (x == StepMain.COL_TWO)
    {
    	color = Color.green;
    }
    else if (x == StepMain.COL_THREE)
    {
    	color = Color.red;
    }
    else if (x == StepMain.COL_FOUR)
    {
    	color = Color.yellow;
    }
  }
  
  public int getY()
  {
	  return y;
  }
  
  public int getX()
  {
	  return x;
  }
  
  public int getBottomY()
  {
	  int yBottom = y + width;
	  return yBottom;
  }

  public void move()
  {
	  y = y + speed;
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


