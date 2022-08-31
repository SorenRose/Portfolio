import java.awt.*;
import java.util.*;
import java.applet.*;
/* Make the cutscenes
 * Amanda Ramos, May 27th, 2015
 */
public class cutscene extends Thread
{
    private int delay;
    private int x=0;
    private ArrayList<Image> pic;
    private ArrayList<AudioClip> talk;
    //private Image pic;
    //private AudioClip talk;
    private boolean on;

    public cutscene(int inDelay,ArrayList<Image> inPic,ArrayList<AudioClip> inTalk, boolean inOn)
    {
        setDelay(inDelay);
        talk=inTalk;
        pic=inPic;
        on=inOn;
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

    public void setImage(ArrayList<Image> inPic)
    {
        pic=inPic;
    }

    public Image getImage()
    {
        return pic.get(getX());
    }

    public void setX(int inX)
    {
        x=inX;
    }
    
    public int getX()
    {
        return x;
    }

    public void setAudio(ArrayList<AudioClip> inTalk)
    {
        talk=inTalk;
    }

    public AudioClip getAudio()
    {
        return talk.get(getX());
    }

    public void nextScene()
    {
        //if(on)
        if(getX()<pic.size()-1 && getX()<talk.size()-1)
        {
                setX(getX()+1);
        }
        else
        on=false;
    }

    public boolean getOn()
    {
        return on;
    }
}