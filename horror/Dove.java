import java.awt.*;
/* Make my character: Nottina
 * Amanda Ramos, April 7th, 2015
 */
public class Dove extends Thread
{
    private int delay;
    private int x;
    private int xChange;
    private int HP=1000,att=50,bottles=3;
    private Image[] pic;
    private boolean alive;
    private boolean run=false;
    private boolean direction=true;
    private boolean attack=false;

    public Dove(Image[] inPic,int inDelay,int inX,int inXc)
    {
        setDelay(inDelay);
        x=inX;
        xChange=inXc;
        pic=inPic;
    }

    public Dove(Image[] inPic, int inX)
    {
        pic=inPic;
        x=inX;
    }
    
    public void run()
    {
        while(alive)
        {
            x+=xChange;
            try
            {sleep(delay);}
            catch(Exception e) {}
        }
    }
    
    public void setAlive(boolean inAlive)
    {
        alive=inAlive;
    }
    
    public void setDirection(boolean inDirect)
    {
        direction=inDirect;
    }
    
    public void setAttack(boolean inAttack)
    {
        attack=inAttack;
    }
    
    public void setX(int inX)
    {
        x=inX;
    }
    
    public void setHP(int inHP)
    {
        HP=inHP;
    }
    
    public void setAtt(int inAtt)
    {
        att=inAtt;
    }
    
    public void setBottles(int inBot)
    {
        att=inBot;
    }
    
    public void setDelay(int inDelay)
    {
        if(inDelay>0)
        delay=inDelay;
        else
        delay=20;
    }
    
    public void setXChange(int inXc)
    {
        xChange=inXc;
    }
    
    public Image[] getImage()
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
    
    public int getXChange()
    {
        return xChange;
    }

    public int getDelay()
    {
        return delay;
    }
    
    public int getAtt()
    {
        return att;
    }
    
    public int getHP()
    {
        return HP;
    }
    
    public int getBottles()
    {
        return bottles;
    }
    
    public void setRun(boolean inRun)
    {
        run=inRun;
    }
    
    public boolean getRun()
    {
        return run;
    }
    
    public boolean getDirection()
    {
        return direction;//true if true, false if left
    }
    
    public boolean getAttack()
    {
        return attack;
    }
    
    public void restore()
    {
        if(getBottles()>0)
        HP=1000;
    }
}