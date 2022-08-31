import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import java.util.Random;

/*Simple Applet to make a game of Space Inavaders
 * Amanda Ramos, January 28th, 2014
 */

public class SpaceInvaders extends Applet implements Runnable, KeyListener
{
    Thread main= new Thread(this);
    Random rand= new Random ();
    Font font= new Font( "Century", 1, 20);
    Color myBrown=new Color( 110, 70, 60 );
    int aliensX[][]=new int[4][6];
    int aliensY[][]=new int[4][6];
    int xChange=10;
    int yChangeCt=1;
    int xShip=500;
    Image buffer;
    Graphics bufferG;

    public void init()
    {
        this.setLayout(null);
        this.addKeyListener(this);
        resize(1300,700);

        for(int r=0; r<4; r++)
            for(int c=0; c<6; c++)
            {
                aliensX[r][c]=200+60*c;
                aliensY[r][c]=50+50*r;
        }

        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();

        main.start();
    }

    public void run()
    {
        do
        {
            repaint();

            for(int r=0; r<4; r++)
                for(int c=0; c<6; c++)
                {
                    aliensX[r][c]=aliensX[r][c]+xChange;
                    
                    if(aliensX[r][c]==1000)
                    {
                        yChangeCt++;
                        aliensX[r][c]=200;
                        aliensY[r][c]= aliensY[r][c]=50+50*r;
                    }
            }
            
            
            
            try
            {main.sleep(100);}
            catch(Exception e){}
        }
        while(true);
    }

    public void paint(Graphics g)
    {
        bufferG.setColor(Color. black);
        bufferG.fillRect(0,0,10000,10000);
        bufferG.setFont(font);
        bufferG.setColor(Color. green);
        for(int r=0; r<4; r++)
            for(int c=0; c<6; c++)
            {
                bufferG.fillRect(aliensX[r][c],aliensY[r][c],40,40);

        }
        bufferG.setColor(Color. white);
        bufferG.fillRect(xShip, 550,60,40);

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
        if(code==e.VK_RIGHT)
        {
            xShip+=20;
        }
        if(code==e.VK_LEFT)
        {
            xShip-=20;
        }
    }
}