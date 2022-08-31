import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.applet.AppletStub;
import java.applet.*;
import java.util.*;
/*Applet to make a game
 * Amanda Ramos, May 12th, 2015
 */

public class tent extends Applet implements Runnable, KeyListener, MouseListener, AppletStub
{
    Thread main= new Thread(this);
    Applet appletToLoad= new Applet();
    Font font= new Font( "Chiller", 1, 40);
    Font introFont= new Font("Segoe UI Blpockyack", 1, 28);
    int x=0; //to move house
    int y=1755;//for entrance position
    int z=0;//for the ent entrance width
    Dove d;
    int timer=0;
    cut scene1;
    boolean tentOn=true;
    Image[] person1=new Image[7];
    AudioClip[] talk1=new AudioClip[7];
    ArrayList theme=new ArrayList<AudioClip>();
    Image buffer, background,open,black;
    Image[] Nottina=new Image[13];
    int HeroFrame=1;
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
        this.addMouseListener(this);
        resize(1300,700);

        d=new Dove (Nottina,10,250,0);
        d.setAlive(true);
        d.start();

        Nottina[0]=getImage(this.getCodeBase(),"N stand.png");
        for(int g=1;g<13;g++)
        {
            Nottina[g]=this.getImage(this.getCodeBase(),"nottina"+(g)+".png");
        }

        background=this.getImage(this.getCodeBase(),"tent1.png");
        open=this.getImage(this.getCodeBase(),"open door.png");

        creepy=this.getAudioClip(this.getCodeBase(),"Dining Room.wav");//taken from youtube, soundtrack of Ib
        creepy.loop();

        scene1=new cut (20, person1,talk1,0,true);
        for(int t=0;t<7;t++)
        {
            talk1[t]=this.getAudioClip(this.getCodeBase(),"talk"+(t+1)+".wav");
            person1[t]=this.getImage(this.getCodeBase(),"pic"+(t+1)+".png");
        }

        black=this.getImage(this.getCodeBase(),"black.png");

        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();

        if(scene1.getOn()==true)
            scene1.getAudio().play();

        main.start();
    }

    public void run()
    {
        do
        {
            repaint();

            if(d.getRun()==true && scene1.getOn()==false)
            {
                HeroFrame++;
                if(HeroFrame>13)
                    HeroFrame=1;
            }
            if(d.getX()>1100 && x<=-1520)
            {
                creepy.stop();
                tentOn=false;
            }
            try
            {main.sleep(100);}
            catch(Exception e){}
        }
        while(tentOn);

        try
        {
            Class applet2=Class.forName("alley");
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
        bufferG.drawImage(background, x,0,2800,700,this);

        if(d.getRun()==true)
        {
            if(d.getDirection()==true)
                bufferG.drawImage(Nottina[HeroFrame], d.getX()+200,440,-200,250,this);
            else
                bufferG.drawImage(Nottina[HeroFrame], d.getX(),440,200,250,this);
        }
        else
        {
            if(d.getDirection()==true)
                bufferG.drawImage(Nottina[0], d.getX()+200,440,-200,250,this);
            else
                bufferG.drawImage(Nottina[0], d.getX(),440,200,250,this);
        }

        bufferG.drawImage(open,1400+x,0,600+z,700,this);
        
        for(int x=0;x<d.getHP();x++)
        {
            bufferG.fillRect(20+1*x,20,1,30);
        }

        if(scene1.getOn()==true)
        {
            bufferG.drawImage(black,0,0,1300,700,this);
            bufferG.drawImage(scene1.getImage(),0,0,1300,700,this);
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
            if(scene1.getOn()==false)
            {
                if(x>-1520)
                {
                    x-=6;
                    y-=4;
                    z-=6;
                    d.setX(d.getX()+2);
                }
                else

                    d.setX(d.getX()+5);
                d.setRun(true);
                d.setDirection(true);
            }
        }
        if(code==e.VK_LEFT)
        {
            if(scene1.getOn()==false)
            {
                if(x<0)
                {
                    x+=6;
                    y+=4;
                    z+=6;

                    d.setX(d.getX()-2);
                }
                else
                    d.setX(d.getX()-5);
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
            if(scene1.getX()>=scene1.getSize()-1)
            {
                scene1.setOn(false);
                scene1.getAudio().stop();

            }
            else 
                scene1.nextScene();
        }
        repaint();
    }

    public void mouseExited(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}
}