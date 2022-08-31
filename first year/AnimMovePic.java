import java.awt.*;

/*An applet to test MovingPic
 * Amanda Ramos April 7th, 2014
 */

public class AnimMovePic extends MovingPic
{   
    private int frameCt;
    private Image[] pics;

    public AnimMovePic(Image[] inPics, int inDelay, int inX, int inY, int inXChange, int inYChange)
    {
        super(null, inDelay, inX, inY, inXChange, inYChange);
        setImage(inPics);
    }

    public void setFrameCt(int inFrameCt)
    {
        if(inFrameCt>=0 && inFrameCt<pics.length)
            frameCt=inFrameCt;
    }

    public int getFrameCt()
    {
        return frameCt;
    }

    public Image getImage()
    {
        return pics[frameCt];
    }

    public void setImage(Image[] inPics)
    {
        pics=inPics;
        frameCt=0;
    }

    public void run()
    {
        while(getAlive())
        {
            setX(getX()+getXChange());
            setY(getY()+getYChange());
            frameCt++;
            if(frameCt==pics.length)
                frameCt=0;
            try
            {sleep(getDelay());}
            catch(Exception e){}
        }
    }
}