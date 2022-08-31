import java.awt.*;
import java.applet.Applet;
import java.util.Random;

/*Simplet Applet to output stuff using loops
 * Amanda Ramos September 9th, 2013
 */

public class numbers extends Applet
{
    String output; 
    int x=0;

    public void init()
    {

    }

    public void paint (Graphics g)
    {
        g.setColor( Color. blue);
        g.drawString("number", 5, 10);
        g.drawString("square", 70,10);
        g.drawString("number", 235, 10);
        g.drawString("x5", 310,10);
        for(int num=1; num<=400; num=num+1)
        {   
            g.drawString(""+num+"                  "+(num*num), 20 ,21*num );
        }
        for(int num=1; num<=500; num=num+1)
        {   
            g.drawString(""+num+"                  "+(num*5), 250 ,21*num );
        }
        for(int num=1; num<=500; num=num+1)
        {   
            x++;
            g.drawString(""+num+"                  "+(num*x), 450 ,21*num );
        }
        for(int num=2; num<=500; num=num+1 )
        {   
            
            x++;
            g.drawString(""+2+"                  "+(Math.pow(2,x)), 650 ,21*num-21 );
        }
    }
}