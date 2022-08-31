import java.awt.*;
import java.applet.Applet;
import java.util.Random;

/*Simplet Applet to make loops
 * Amanda Ramos October 28th, 2013
 */

public class loopPractice extends Applet
{
    
    public void paint (Graphics g)
    {
        for(int i=0; i<20; i++)
        g.drawOval(225+11*i,200+10*i,20,20);
        for(int p=0; p<20; p++)
        g.drawRect(375+5*p,150+5*p,40,40);
        for(int n=0; n<20; n++)
        g.drawString(""+(1+n)*2,150,150+15*n);
    }
}