import java.awt.*;
/* Object to make moving pictures
 * Amanda Ramos, April 7th, 2014
 */
public class MovingPic extends Thread
{
    private int delay;
    private int x;
    private int y;
    private int xChange;
    private int yChange;
    private Image pic;
    private boolean alive;

    public MovingPic(Image inPic, int inDelay, int inX, int inY, int inXc, int inYc)
    {
        setDelay(inDelay);
        x=inX;
        y=inY;
        yChange=inYc;
        xChange=inXc;
        pic=inPic;
        alive=false;
    }

    public MovingPic(Image inPic, int inX, int inY)
    {
        pic=inPic;
        x=inX;
        y=inY;
    }
    
    public void run()
    {
        while(alive)
        {
            x+=xChange;
            y+=yChange;
            try
            {sleep(delay);}
            catch(Exception e) {}
        }
    }
    
    public void setAlive(boolean inAlive)
    {
        alive=inAlive;
    }
    
    public void setX(int inX)
    {
        x=inX;
    }
    
    public void setDelay(int inDelay)
    {
        if(inDelay>0)
        delay=inDelay;
        else
        delay=20;
    }
    
    public void setY(int inY)
    {
        y=inY;
    }
    
    public void setXChange(int inXc)
    {
        xChange=inXc;
    }
    
    public void setYChange(int inYc)
    {
        yChange=inYc;
    }
    
    public Image getImage()
    {
        return pic;
    }
    
    public boolean getAlive()
    {
        return alive;
    }

    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }

    public int getXChange()
    {
        return xChange;
    }
    
    public int getYChange()
    {
        return yChange;
    }

    public int getDelay()
    {
        return delay;
    }
}