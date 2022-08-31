import java.awt.*;
import java.util.*;
import java.applet.*;
/* Make the cutscenes
 * Amanda Ramos, May 27th, 2015
 */
public class cut extends Thread
{
    private int delay;
    private int x=0;
    //private ArrayList<Image> pic;
    //private ArrayList<AudioClip> talk;
    private Image[] pic;
    private AudioClip[] talk;
    private boolean on;

    public cut(int inDelay,Image[] inPic,AudioClip[] inTalk,int inX, boolean inOn)
    {
        setDelay(inDelay);
        talk=inTalk;
        pic=inPic;
        on=inOn;
        x=inX;
    }

    public void run()
    {
        while(on)
        {
            //talk.get(x).play();

            setX(getX()+1);
            try
            {sleep(delay);}
            catch(Exception e) {}
        }
    }

    public void setDelay(int inDelay)
    {
        if(inDelay>0)
            delay=inDelay;
        else
            delay=20;
    }

    public void setImage(Image[] inPic)
    {
        pic=inPic;
    }

    public Image getImage()
    {
        return pic[getX()];
    }

    public void setX(int inX)
    {
        x=inX;
    }
    
    public void setOn(boolean inOn)
    {
        on=inOn;
    }

    public int getX()
    {
        return x;
    }

    public int getSize()
    {
        return talk.length;
    }
    
    public void setAudio(AudioClip[] inTalk)
    {
        talk=inTalk;
    }

    public AudioClip getAudio()
    {
        return talk[getX()];
    }

    public void nextScene()
    {
        if(on)
        {
            talk[getX()].stop();
            if(getX()<pic.length && getX()<talk.length)
            {
                setX(getX()+1);
                talk[getX()].play();
            }
        }
    }

    public boolean getOn()
    {
        return on;
    }
}