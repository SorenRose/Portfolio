import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

/*An applet to make a game of Impossible Solitare
 * Amanda Ramos Febuary 26th, 2014
 */

public class ImpossibleSolitare extends Applet implements KeyListener
{
    Font font= new Font( "Century", 1, 30);
    Color myGreen=new Color( 0, 150, 0 );
    Deck d= new Deck();
    Card c[]=new Card[52];
    boolean selected;
    int cardCt=4;
    int lastcard=3;
    Image buffer;
    Graphics bufferG;

    public void init()
    {
        this.setLayout(null);
        this.addKeyListener(this);
        resize(1300,700);

        d.shuffle();
        for(int p=0;p<4;p++)
            c[p]=d.deal();

        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();
    }

    public void paint (Graphics g)
    {
        bufferG.setColor(myGreen);
        bufferG.fillRect(0,0,10000,10000);
        bufferG.setColor(Color. red);
        bufferG.setFont(font);
        for(int p=0;p<=lastcard;p++)
            bufferG.drawImage(c[p].getImage(this),30*p+50,100,this);
        if(cardCt==52 && c[lastcard].getSuit()!=c[lastcard-3].getSuit() && c[lastcard].getValue()!=c[lastcard-3].getValue() )
            bufferG.drawString("GAME OVER", 100,300);
        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void keyReleased(KeyEvent e){}    

    public void keyTyped(KeyEvent e){}

    public void keyPressed(KeyEvent e)
    {
        int code=e.getKeyCode();
        if(code==e.VK_D)
        {
            lastcard++;
            cardCt++;
            c[lastcard]=d.deal();
        }
        if(code==e.VK_R&&c[lastcard].getSuit()==c[lastcard-3].getSuit())
        {
            c[lastcard-2]=c[lastcard];
            lastcard-=2;
        }
        if(code==e.VK_E&&c[lastcard].getValue()==c[lastcard-3].getValue())
        {
            lastcard-=4;
        }
        repaint();
    }
}