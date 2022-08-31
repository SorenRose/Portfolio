import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

/*An applet to test BaseballPlayer
 * Amanda Ramos April 14th, 2014
 */

public class letsPlayBall extends Applet
{   
    BaseballPlayer b=new BaseballPlayer();
    BaseballPlayer c=new BaseballPlayer("Soren","catcher",12,30,5);
    Image buffer;
    Graphics bufferG;

    public void init()
    {
        resize(1300,700);

        b.setHits(10);
        b.setAtBats(34);
        b.setSacrifices(4);

        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();
    }

    public void paint (Graphics g)
    {
        bufferG.setColor(Color. white);
        bufferG.fillRect(0,0,10000,10000);
        bufferG.setColor(Color. red);
        bufferG.drawString(b.toString(),10,10);
        bufferG.drawString(""+b.getNetAtBats(),10,30);
        bufferG.drawString("Batting Average: "+b.getBattingAverage(),10,50);
        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

}