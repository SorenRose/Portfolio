import java.awt.*;
import java.util.*;
/* Make my enemy: shadow being, Niente
 * Amanda Ramos,May 20th, 2014
 */
public class Wizard extends Thread
{
    private int delay;
    private int x;
    private int HP=500,att=20;
    private int timer=0;
    private boolean alive, attack=false,left=false,right=false;
    Image[] bad=new Image[6];
    final int width=50;
    final int height=100;

    public Wizard(Image[] inPic,int inDelay,int inX)
    {
        setDelay(inDelay);
        x=inX;
        bad=inPic;
    }

    public Wizard(Image[] inPic, int inX)
    {
        bad=inPic;
        x=inX;
    }

    public void run()
    {
        while(alive)
        {
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
    
    public void direction(int target)
    {
        if(getAlive())
        {
            if((target-600)>getX())
            {
                setAttack(false);
                setLeft(true);
                setRight(false);
            }
            else if((target+600)<getX())
            {
                setAttack(false);
                setRight(true);
                setLeft(false);
            }
            else
            {
                setAttack(true);
            }
        }
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

    public void setTimer(int time)
    {
        timer=time;
    }

    public void setAttack(boolean a)
    {
        attack=a;
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