import java.awt.*;
import java.applet.Applet;
import java.util.Random;

/*An applet to test Point
 * Amanda Ramos Febuary 7th, 2014
 */

public class TestTheBox extends Applet
{
    Box b=new Box();
    Font font= new Font( "Century", 1, 20);

    public void init()
    {
        b.setH(-27);
    }
    
    public void paint (Graphics g)
    {
        g.setFont(font);
        g.drawString("Height is "+b.getH()+", length is "+b.getL()+", width is "+b.getW(),100,100);
    }
}