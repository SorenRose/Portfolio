import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.applet.AppletStub;
import java.applet.*;
import java.util.*;
/*Applet to make a game
 * Amanda Ramos, April 23rd, 2015
 */

public class testAttack extends Applet implements Runnable, KeyListener
{
    Thread main= new Thread(this);
    Font font= new Font( "Chiller", 1, 40);
    Font introFont= new Font("Segoe UI Blpockyack", 1, 28);
    Dove d;
    int x=0;//move the stage
    int timer=0;
    int HeroFrame=1, HeroAttack=13;
    Image buffer, background;
    Image[] Nottina=new Image[20];
    AudioClip enemies,alleys;
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

        Nottina[0]=getImage(this.getCodeBase(),"N stand.png");
        for(int g=1;g<13;g++)
        {
            Nottina[g]=this.getImage(this.getCodeBase(),"nottina"+(g)+".png");
        }
        for(int h=13;h<20;h++)
                Nottina[h]=this.getImage(this.getCodeBase(),"Nottina attack"+(h-12)+".png");
        

        d=new Dove (Nottina,10,450,0);
        d.setAlive(true);
        d.start();

        background=this.getImage(this.getCodeBase(),"stage.png");

        alleys=this.getAudioClip(this.getCodeBase(),"Inquiry.wav");
        alleys.loop();
        enemies=this.getAudioClip(this.getCodeBase(),"Shousou.wav");//taken from youtube, soundtrack of Ib
        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();

        main.start();
    }

    public void run()
    {
        do
        {
            repaint();
            //else
            //  scene2.setOn(false);

            if(d.getRun()==true)
            {
                HeroFrame++;
                if(HeroFrame>12)
                    HeroFrame=1;
            }

            if(d.getAttack()==true)
            {
                HeroAttack++;
                if(HeroAttack>20)
                    d.setAttack(false);
            }

            try
            {main.sleep(110);}
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
        bufferG.drawImage(background, x,0,2600,700,this);
        bufferG.drawString(""+d.getHP(),200,200);

        if(d.getRun()==true)
        {
            if(d.getDirection()==true)
                bufferG.drawImage(Nottina[HeroFrame], d.getX()+120,470,-120,180,this);
            else
                bufferG.drawImage(Nottina[HeroFrame], d.getX(),470,120,180,this);
        }
        else if(d.getAttack()==true)
        {
            if(d.getDirection()==true)
                bufferG.drawImage(Nottina[HeroAttack], d.getX()+120,470,-120,180,this);
            else
                bufferG.drawImage(Nottina[HeroAttack], d.getX(),470,120,180,this);
        }
        else
        {
            if(d.getDirection()==true)
                bufferG.drawImage(Nottina[0], d.getX()+120,470,-120,180,this);
            else
                bufferG.drawImage(Nottina[0], d.getX(),470,120,180,this);
        }
        
        bufferG.drawString(HeroFrame+"",700,100);
        
        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void keyTyped(KeyEvent e){}

    public void keyReleased(KeyEvent e)
    {
        int code=e.getKeyCode();
        if(code==e.VK_RIGHT)
        {
            d.setRun(false);
        }
        if(code==e.VK_LEFT)
        {
            d.setRun(false);
        }
        //if(code==e.VK_SPACE)
        //{
          //  d.setAttack(false);
        //}
    }

    public void keyPressed(KeyEvent e)
    {
        int code=e.getKeyCode();
        if(code==e.VK_RIGHT)
        {

            if(x>-1320)
            {

                x-=6;
                d.setX(d.getX()+2);

            }
            else
            {
                d.setX(d.getX()+5);
            }
            d.setRun(true);
            d.setDirection(true);

        }

        if(code==e.VK_LEFT)
        {
            if(x<0)
            {

                x+=6;
                d.setX(d.getX()-2);

            }
            else
            {   
                d.setX(d.getX()-5);
            }
            d.setRun(true);
            d.setDirection(false);

        }

        if(code==e.VK_SPACE)
        {
            d.setAttack(true);
            HeroAttack=13;
        }
        repaint();
    }
}