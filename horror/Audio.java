import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.applet.AppletStub;
import java.applet.*;
import java.util.*;
/*Applet to make a game
 * Amanda Ramos, May 29th, 2015
 */

public class Audio extends Applet implements Runnable,KeyListener
{
    Thread main= new Thread(this);    
    Font font= new Font( "Chiller", 1, 40);
    Font introFont= new Font("Segoe UI Blpockyack", 1, 28);
    boolean play;
    int hit=0,time=0;
    AudioClip g;
    Image buffer;
    Graphics bufferG;

    public void appletResize(int width, int length)
    {
        resize( 1300, 700 );  
    }

    public void init()
    {
        this.setLayout(null);
        this.addKeyListener(this);
        resize(1300,700);
        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();

        g=this.getAudioClip(this.getCodeBase(),"puppet.wav");
        g.play();
        main.start();
    }

    public void run()
    {
        do
        {

            repaint();
            time++;;
            if(time>60)
            {
                g.stop();
                play=false;
            }
            else
            {
                
                play=true;
            }
            try
            {main.sleep(300);}
            catch(Exception e){}
        }
        while(true);
    }

    public void paint(Graphics g)
    {
        bufferG.setColor(Color. black);
        bufferG.fillRect(0,0,10000,10000);
        bufferG.setFont(font);
        bufferG.setColor(Color. red);
        //bufferG.drawString(""+hit,100,100);
        bufferG.drawString(""+time,100,100);
        bufferG.drawString(""+play,200,100);
        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void keyTyped(KeyEvent e){}

    public void keyReleased(KeyEvent e){}

    public void keyPressed(KeyEvent e)
    {
        int code=e.getKeyCode();
        if(code==e.VK_SPACE)
        {
            //hit++;
        }
        repaint();
    }
}