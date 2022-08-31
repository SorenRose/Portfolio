import java.awt.*;
import java.applet.Applet;
import java.util.Random;

/*Simplet Applet to output stuff using loops
 * Amanda Ramos September 9th, 2013
 */

public class warmup1 extends Applet
{
    int y=0;
    int x=10;
    Random rand= new Random();
    
    public void init()
    {
        
    }
    
    public void paint (Graphics g)
    {
        g.setColor( Color. blue);
        for(int num=2; num<=200; num=num+2)
        {
            g.drawString(""+num, x+12*num,y );
            if(num<100)
            y=10;
           else
           {
               y=30;
               x=-1180;
           }
        }
    }
}