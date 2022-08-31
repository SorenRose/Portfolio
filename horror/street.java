import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.applet.AppletStub;
import java.applet.*;
import java.util.*;
/*Applet to make a game
 * Amanda Ramos,June 1st April 23rd, 2015
 */

public class street extends Applet implements Runnable, KeyListener, MouseListener, AppletStub
{
    Thread main= new Thread(this);
    Applet appletToLoad= new Applet();
    Font font= new Font( "Chiller", 1, 40);
    Font introFont= new Font("Segoe UI Blpockyack", 1, 28);
    Color myBrown=new Color( 110, 70, 60 );
    Dove d;
    int x=0;//move the stage
    cut scene7;
    Image[] person7=new Image[4];
    AudioClip[] talk7=new AudioClip[4];
    boolean  streetOn=true;
    int HeroFrame=1;
    Image buffer, background,black;
    Image[] Nottina=new Image[13];
    AudioClip alleys;
    Graphics bufferG;

    public void appletResize(int width, int length)
    {
        resize( 1300, 700 );  
    }

    public void init()
    {
        this.setLayout(null);
        this.addKeyListener(this);
        this.addMouseListener(this);
        resize(1300,700);

        Nottina[0]=getImage(this.getCodeBase(),"N stand.png");
        for(int g=1;g<13;g++)
        {
            Nottina[g]=this.getImage(this.getCodeBase(),"nottina"+(g)+".png");
        }

        d=new Dove (Nottina,10,450,0);
        d.setAlive(true);
        d.start();

        background=this.getImage(this.getCodeBase(),"stage.png");
        black=this.getImage(this.getCodeBase(),"black.png");

        alleys=this.getAudioClip(this.getCodeBase(),"Inquiry.wav");
        alleys.loop();

        scene7=new cut (20, person7,talk7,0,true);
        for(int t=0;t<4;t++)
        {
            talk7[t]=this.getAudioClip(this.getCodeBase(),"talk"+(t+29)+".wav");
            person7[t]=this.getImage(this.getCodeBase(),"pic"+(t+29)+".png");
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

            if(d.getRun()==true)
            {
                HeroFrame++;
                if(HeroFrame>13)
                    HeroFrame=1;
            }

            if(d.getX()>1200)
            {
                streetOn=false;
                alleys.stop();
            }

            try
            {main.sleep(110);}
            catch(Exception e){}
        }
        while(streetOn);

        try
        {
            Class applet2=Class.forName("boss");
            appletToLoad=(Applet)applet2.newInstance();
            appletToLoad.setStub(this);
            add(appletToLoad);
            appletToLoad.init();
            appletToLoad.start();
        }
        catch (Exception p){}
    }

    public void paint(Graphics g)
    {
        bufferG.setColor(Color. black);
        bufferG.fillRect(0,0,10000,10000);
        bufferG.setFont(font);
        bufferG.setColor(Color. red);
        bufferG.drawImage(background, x,0,2600,700,this);

        if(d.getRun()==true)
        {
            if(d.getDirection()==true)
                bufferG.drawImage(Nottina[HeroFrame], d.getX()+120,470,-120,180,this);
            else
                bufferG.drawImage(Nottina[HeroFrame], d.getX(),470,120,180,this);
        }
        else
        {
            if(d.getDirection()==true)
                bufferG.drawImage(Nottina[0], d.getX()+120,470,-120,180,this);
            else
                bufferG.drawImage(Nottina[0], d.getX(),470,120,180,this);
        }

        for(int x=0;x<d.getHP();x++)
        {
            bufferG.fillRect(20+1*x,20,1,30);
        }

        if(scene7.getOn()==true)
        {
            bufferG.drawImage(black,0,0,1300,700,this);
            bufferG.drawImage(scene7.getImage(),0,0,1300,700,this);
        }

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
            if(scene7.getOn()==false)
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
        }

        if(code==e.VK_LEFT)
        {
            if(scene7.getOn()==false)
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
        }

        repaint();
    }

    public void mouseClicked(MouseEvent e)
    {
        int mouseX=e.getX();
        int mouseY=e.getY();
        if(mouseX>0 && mouseX<1300 && mouseY>0 && mouseY<700)
        {   
            if(scene7.getOn()==true)
            {
                scene7.getAudio().play();
                if(scene7.getX()>=scene7.getSize()-1)
                {
                    scene7.setOn(false);
                    scene7.getAudio().stop();
                }
                else 
                    scene7.nextScene();
            }
        }
        repaint();
    }

    public void mouseExited(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}
}