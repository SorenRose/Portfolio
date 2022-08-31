import java.awt.*;
import java.applet.Applet;
import java.util.Random;

/*An applet to test IQ
 * Amanda Ramos and Conor Lilley Febuary 21st, 2014
 * 
 */

public class IQTest extends Applet
{
    Font font= new Font( "Century", 1, 30);
    IQ p = new IQ();
    IQ m = new IQ(160);
    IQ n = new IQ(200);
    IQ o = new IQ(1640);
    Image buffer;
    Graphics bufferG;

    public void init()
    {
        resize(1300,700);
        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();
        
    }


    public void paint (Graphics g)
    {
        bufferG.setColor(Color. black);
        bufferG.fillRect(0,0,10000,10000);
        bufferG.setColor(Color. red);
        bufferG.setFont(font);
        bufferG.drawString(p.toString(),100,200);
        bufferG.drawString(m.toString(),100,300);
        bufferG.drawString(n.toString(),100,400);
        bufferG.drawString(o.toString(),100,500);
        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }
}