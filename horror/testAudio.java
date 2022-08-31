import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.applet.AppletStub;
import java.applet.*;
import java.util.*;
/*Applet to make a game
 * Amanda Ramos, May 29th, 2015
 */

public class testAudio extends Applet implements Runnable,KeyListener
{
    Thread main= new Thread(this);    
    Font font= new Font( "Chiller", 1, 40);
    Font introFont= new Font("Segoe UI Blpockyack", 1, 28);
    int hit=0;
    //ArrayList theme=new ArrayList<AudioClip>();
    AudioClip[] t=new AudioClip[4];
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

        /*theme.add(this.getAudioClip(this.getCodeBase(),"Disappearance.wav"));
        theme.add(this.getAudioClip(this.getCodeBase(),"Prelude.wav"));
        theme.add(this.getAudioClip(this.getCodeBase(),"Shousou.wav"));
        theme.add(this.getAudioClip(this.getCodeBase(),"Disappearance.wav"));*/

        t[0]=this.getAudioClip(this.getCodeBase(),"Disappearance.wav");
        t[1]=this.getAudioClip(this.getCodeBase(),"Prelude.wav");
        t[2]=this.getAudioClip(this.getCodeBase(),"Shousou.wav");
        t[3]=this.getAudioClip(this.getCodeBase(),"puppet.wav");

        g=this.getAudioClip(this.getCodeBase(),"puppet.wav");
        
        //theme.get(hit).play();
        t[hit].play();
        //if(hit>1)
        //t[hit-1].stop();
        
        main.start();
    }

    public void run()
    {
        do
        {
            repaint();
            //t[hit].play();
            //g.play();
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
        bufferG.setColor(Color. red);
        bufferG.drawString(""+hit,100,100);
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
            t[hit].stop();
            if(hit<t.length)
                hit++;
                t[hit].play();
        }
        repaint();
    }
}