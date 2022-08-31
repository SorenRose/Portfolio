import java.awt.*;
import java.applet.Applet;
import java.util.Random;

/*An applet to test Point
 * Amanda Ramos Febuary 10th, 2014
 */

public class MyTimeClock extends Applet implements Runnable
{
    MyTime t=new MyTime();
    MyTime clock=new MyTime(9,25,0);
    Font font= new Font( "Century", 1, 35);
    Thread main= new Thread(this);
    Image buffer;
    Graphics bufferG;

    public void init()
    {
        main.start();
        resize(400,400);
        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();

        
    }

    public void run()
    {
        do
        {
            repaint();

            t.addSec();
            clock.addSec();
            try
            {main.sleep(1000);}
            catch(Exception e){}
        }
        while(true);
    }

    public void paint (Graphics g)
    {
        bufferG.setColor(Color.black);
        bufferG.fillRect(0,0,10000,10000);
        bufferG.setColor(Color. green);
        bufferG.setFont(font);
        bufferG.drawString(t.toString(),100,100);
        bufferG.drawString(clock.toString(),100,200);
        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }
}