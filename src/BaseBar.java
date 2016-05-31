import java.awt.*;

public class BaseBar
{
  // DATA:
  private int topBar;
  private int bottomBar;
  private int barWidth = 90;
  //private int barLength = 100;
  
  public BaseBar()
  {
	  topBar = 700;
	  bottomBar = topBar + barWidth;
  }
  
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
    //g.setColor(Color.blue);
    //g.drawRect(300, topBar, barLength, barWidth);
  }
}


