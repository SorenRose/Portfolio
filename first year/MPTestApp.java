import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

/*An applet to test MovingPic
 * Amanda Ramos April 7th, 2014
 */

public class MPTestApp extends Applet implements Runnable
{   
    Thread main= new Thread(this);
    Image pic;
    MovingPic mp;
    Image buffer;
    Graphics bufferG;

    public void init()
    {
        resize(1300,700);

        pic=this.getImage(this.getCodeBase(),"paper yoshi.png");
        mp=new MovingPic(pic,100,50,200,5,0);
        mp.setAlive(true);
        mp.start();
        main.start();

        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();
    }

    public void run()
    {
        while(true)
        {
            repaint();
            try
            {main.sleep(mp.getDelay());}
            catch(Exception e){}
        }
    }

    public void paint (Graphics g)
    {
        bufferG.setColor(Color. white);
        bufferG.fillRect(0,0,10000,10000);
        bufferG.drawImage(mp.getImage(), mp.getX(),mp.getY(), 100,100,this);
        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

}