import java.awt.*;
import java.applet.Applet;
import java.util.Random;

/*An applet to test Point
 * Amanda Ramos Febuary 10th, 2014
 * NOTE! WHEN NAMING A CARD, DO NOT NAME IT "G", g.toString WILL NOT WORK!
 * NOTE! CARD(SUIT,VALUE)
 */

public class CardTest extends Applet
{
    Card c=new Card(3,8);
    Card d=new Card(0,11);
    Card e=new Card(2,14);
    Card f=new Card(1,6);
    Card h=new Card(2,2);
    Font font= new Font( "Century", 1, 30);
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
        //bufferG.drawString("Suit is "+c.getSuit()+" value is "+c.getValue(),100,100);
        //bufferG.drawString(c.toString(),100,100);
        bufferG.drawImage(c.getImage(this),100,100,this);
        bufferG.drawImage(d.getImage(this),100,200,this);
        bufferG.drawImage(e.getImage(this),100,300,this);
        bufferG.drawImage(f.getImage(this),100,400,this);
        bufferG.drawImage(h.getImage(this),100,500,this);
       
        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }
}