import java.awt.*;
import java.util.*;
/* Make my enemy: shadow being, Niente
 * Amanda Ramos, May 11th, 2014
 */
public class Enemy extends Thread
{
    private int delay;
    private int x;
    private int xChange;
    private int HP=100,att=10;
    private int timer=0;
    private boolean alive, attack=false,left=false,right=false;
    Image[] bad=new Image[10];
    final int width=50;
    final int height=100;

    public Enemy(Image[] inPic,int inDelay,int inX,int inXc)
    {
        setDelay(inDelay);
        x=inX;
        xChange=inXc;
        bad=inPic;
    }

    public Enemy(Image[] inPic, int inX)
    {
        bad=inPic;
        x=inX;
    }

    public void run()
    {
        while(alive)
        {
            x+=xChange;
            if(getHP()==0)
            {
                alive=false;
            }
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

    public void setHP(int inHP)
    {
        HP=inHP;
    }

    public void setLeft(boolean L)
    {
        left=L;
    }

    public void setRight(boolean R)
    {
        right=R;
    }

    public void setAtt(int inAtt)
    {
        att=inAtt;
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
        return bad;
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

    public void setTimer(int time)
    {
        timer=time;
    }

    public void setAttack(boolean a)
    {
        attack=a;
    }

    public void direction(int target)
    {
        if(getAlive())
        {
            if((target-50)>getX())
            {
                setXChange(3);
                setAttack(false);
                setLeft(true);
                setRight(false);
            }
            else if((target+60)<getX())
            {
                setXChange(-3);
                setAttack(false);
                setRight(true);
                setLeft(false);
            }
            else
            {
                setAttack(true);
                setXChange(0);
            }
        }
    }

    public int getDelay()
    {
        return delay;
    }

    public int getTimer()
    {
        return timer;
    }

    public int getAtt()
    {
        return att;
    }

    public boolean getAttack()
    {
        return attack;
    }

    public boolean getLeft()
    {
        return left;
    }

    public boolean getRight()
    {
        return right;
    }

    public int getHP()
    {
        return HP;
    }
}