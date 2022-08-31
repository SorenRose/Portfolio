import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

/*Simplet Applet to practice MouseListener
 * Amanda Ramos October 28th, 2013
 */

public class mouseRings extends Applet implements MouseListener, Runnable
{
    private int x=100, y=100;
    Thread main= new Thread(this);
    int count=0;
    int radius=0;
    int diameter=0;

    public void init()
    {
        addMouseListener(this);
        main.start();
        resize(1300,700);
    }

    public void run()
    {
        while(true)
        {
            while (count<1000)
            {
                count++;
                radius=15*count;
                diameter=2*radius;
                Graphics g=getGraphics();
                paint(g);
                try
                {main.sleep(100);}
                catch(Exception e){}
            }
        }
    }

    public void paint(Graphics g)
    {
        g.drawOval(x-radius, y-radius, diameter, diameter);
        g.drawRect(x-radius, y-radius, diameter, diameter);
        // g.drawLine(x-radius, y-radius, diameter, diameter);
    }

    public void mouseClicked(MouseEvent e)
    {
        x=e.getX();
        y=e.getY();
        count=0;
    }

    public void mouseExited(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}
}