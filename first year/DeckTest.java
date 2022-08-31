import java.awt.*;
import java.applet.Applet;
import java.util.Random;

/*An applet to test a deck of cards
 * Amanda Ramos Febuary 24th, 2014
 * 
 */

public class DeckTest extends Applet
{
    Font font= new Font( "Century", 1, 30);
    Deck d= new Deck();
    Card c=new Card();
    Image buffer;
    Graphics bufferG;

    public void init()
    {
        resize(1300,700);
        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();
        
        d.shuffle();
        c=d.deal();

        
    }

    public void paint (Graphics g)
    {
        bufferG.setColor(Color. black);
        bufferG.fillRect(0,0,10000,10000);
        bufferG.setColor(Color. red);
        bufferG.setFont(font);
        bufferG.drawImage(c.getImage(this),100,100,this);
        bufferG.drawString("Cards left is "+d.numCardsLeft(),200,200);
        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }
}