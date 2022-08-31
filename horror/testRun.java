import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.applet.AppletStub;
import java.applet.*;
import java.util.*;
/*Applet to make a game
 * Amanda Ramos, June 4th, 2015
 */

public class testRun extends Applet implements Runnable, KeyListener
{
    Thread main= new Thread(this);
    Font font= new Font( "Chiller", 1, 40);
    Font introFont= new Font("Segoe UI Blpockyack", 1, 28);
    Color myBrown=new Color( 110, 70, 60 );
    int x=0; //to move house
    int y=1755;//for entrance position
    int z=0;//for the ent entrance width
    Dove d;
    int timer=0;
    ArrayList theme=new ArrayList<AudioClip>();
    Image buffer, background,open;
    Image[] Nottina=new Image[12];
    int HeroFrame=0;
    AudioClip creepy;
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

        d=new Dove (Nottina,10,250,0);
        //Nottina=this.getImage(this.getCodeBase(),"pocky.png");
        d.setAlive(true);
        d.start();
        
        for(int g=0;g<12;g++)
        {
            Nottina[g]=this.getImage(this.getCodeBase(),"nottina"+(g+1)+".png");
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
           // if(d.getRun()==true)
            //{
            HeroFrame++;
            if(HeroFrame>12)
            HeroFrame=0;
        //}
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
        bufferG.drawImage(background, x,0,2800,700,this);
        bufferG.drawString(""+x,200,200);
        //if(d.getRun()==true)
        bufferG.drawImage(Nottina[HeroFrame], d.getX(),100,300,350,this);
       // else
        //bufferG.drawImage(Nottina[0], d.getX(),460,80,180,this);
        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void keyTyped(KeyEvent e){}

    public void keyReleased(KeyEvent e)
    {
        d.setRun(false);
    }

    public void keyPressed(KeyEvent e)
    {
        int code=e.getKeyCode();
        if(code==e.VK_RIGHT)
        {
            x-=6;
            y-=4;
            z-=6;
            d.setRun(true);
            d.setX(d.getX()+2);
        }
        if(code==e.VK_LEFT)
        {
            x+=6;
            y+=4;
            z+=6;
            d.setRun(true);
            d.setX(d.getX()-2);
        }
        repaint();
    }
}