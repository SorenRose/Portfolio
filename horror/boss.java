import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.applet.AppletStub;
import java.applet.*;
import java.util.*;
/*Applet to make a game
 * Amanda Ramos, May 15th, 2015
 */

public class boss extends Applet implements Runnable, KeyListener,AppletStub, MouseListener
{
    Thread main= new Thread(this);
    Applet appletToLoad= new Applet();
    Font font= new Font( "Chiller", 1, 40);
    Font introFont= new Font("Segoe UI Blpockyack", 1, 28);
    Wizard w;
    Image[] wizard=new Image[6];
    Image[] fire=new Image[2];
    Dove d;
    cut scene8, scene9;
    Image[] person8=new Image[2];
    AudioClip[] talk8=new AudioClip[2];
    Image[] person9=new Image[1];
    AudioClip[] talk9=new AudioClip[1];
    ArrayList theme=new ArrayList<AudioClip>();
    Image buffer, background, black;
    Image[] Nottina=new Image[20];
    int HeroFrame=1, HeroAttack=13;
    int frame=0, frame2=4, flameCt;
    int timer;
    boolean flame=false,bossOn=true;
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

        d=new Dove (Nottina,10,100,0);
        Nottina[0]=getImage(this.getCodeBase(),"N stand.png");
        for(int g=1;g<13;g++)
        {
            Nottina[g]=this.getImage(this.getCodeBase(),"nottina"+(g)+".png");
        }
        for(int h=13;h<20;h++)
            Nottina[h]=this.getImage(this.getCodeBase(),"Nottina attack"+(h-12)+".png");
        d.setAlive(true);
        d.start();

        w=new Wizard (wizard,100,1100);
        w.setAlive(true);
        w.start();
        for(int r=0;r<6;r++)
        {
            wizard[r]=this.getImage(this.getCodeBase(),"wizard"+(r+1)+".png");
        }

        for(int j=1;j<2;j++)
        {
            fire[j]=this.getImage(this.getCodeBase(),"fire"+(j+1)+".png");
        }

        background=this.getImage(this.getCodeBase(),"final stage.png");
        black=this.getImage(this.getCodeBase(),"black.png");

        creepy=this.getAudioClip(this.getCodeBase(),"puppet.wav");//taken from youtube, soundtrack of Ib
        creepy.loop();

        scene8=new cut (20, person8,talk8,0,true);
        for(int t=0;t<2;t++)
        {
            talk8[t]=this.getAudioClip(this.getCodeBase(),"talk"+(t+33)+".wav");
            person8[t]=this.getImage(this.getCodeBase(),"pic"+(t+33)+".png");
        }

        scene9=new cut (20, person9,talk9,0,true);
        scene9.setOn(false);
        talk9[0]=this.getAudioClip(this.getCodeBase(),"talk35.wav");
        person9[0]=this.getImage(this.getCodeBase(),"pic35.png");

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
                if(HeroFrame>12)
                    HeroFrame=1;
            }

            if(d.getAttack()==true)
            {
                HeroAttack++;
                if(HeroAttack>20)
                    d.setAttack(false);
            }

            frame++;
            if(frame>3)
                frame=0;
            if(w.getHP()<=0)
            {
                w.setAlive(false);
            }
            if(w.getAlive()==true)
            {
                w.direction(d.getX());
            }
            if(w.getAttack() && w.getAlive()==true)
            {
                frame2++;
                if(frame2>wizard.length)
                    frame2=4;
                if(w.getTimer()>6)
                {
                    d.setHP(d.getHP()-w.getAtt());
                    flameCt++;
                    flame=true;
                    w.setTimer(0);
                }
                else
                {
                    w.setTimer(w.getTimer()+1);
                    flameCt=0;
                    flame=false;
                }
            }

            if(w.getAlive()==false)
            {

                scene9.setOn(true);
            }

            if(scene9.getX()>=scene9.getSize()-1 && w.getAlive()==false)
            {
                timer++;
                if(timer==40)
                bossOn=false;
            }

            try
            {main.sleep(110);}
            catch(Exception e){}
        }
        while(bossOn);

        try
        {
            Class applet2=Class.forName("Ending");
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
        bufferG.drawImage(background, 0,0,1300,700,this);

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

        if(w.getAlive())
        {
            if(w.getAttack()==false)
            {
                if(w.getLeft())
                    bufferG.drawImage(wizard[frame], w.getX()+130,450,-150,190,this);
                if(w.getRight())
                    bufferG.drawImage(wizard[frame], w.getX(),450,150,190,this);
            }
            if(w.getAttack()==true)
            {
                if(w.getLeft())
                    bufferG.drawImage(wizard[frame2], w.getX()+130,450,-150,190,this);
                if(w.getRight())
                    bufferG.drawImage(wizard[frame2], w.getX(),450,150,190,this);
                if(flame)
                {
                    bufferG.drawImage(fire[flameCt], d.getX(),470,120,180,this);
                }
            }
        }

        for(int x=0;x<d.getHP();x++)
        {
            bufferG.fillRect(20+1*x,20,1,30);
        }
        bufferG.drawString(""+d.getBottles(),20,100);

        if(scene8.getOn()==true)
        {
            bufferG.drawImage(black,0,0,1300,700,this);
            bufferG.drawImage(scene8.getImage(),0,0,1300,700,this);
        }

        if(scene9.getOn()==true)
        {
            bufferG.drawImage(black,0,0,1300,700,this);
            bufferG.drawImage(scene9.getImage(),0,0,1300,700,this);
            creepy.stop();
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
        int code=e.getKeyCode();
        if(code==e.VK_RIGHT)
        {
            d.setRun(false);
        }
        if(code==e.VK_LEFT)
        {
            d.setRun(false);
        }
    }

    public void keyPressed(KeyEvent e)
    {
        int code=e.getKeyCode();
        if(code==e.VK_RIGHT)
        {
            if(scene8.getOn()==false && scene9.getOn()==false)
            {
                d.setX(d.getX()+5);
                d.setRun(true);
                d.setDirection(true);
            }
        }

        if(code==e.VK_LEFT)
        {
            if(scene8.getOn()==false && scene9.getOn()==false)
            {
                d.setX(d.getX()-5);
                d.setRun(true);
                d.setDirection(false);
            }
        }

        if(code==e.VK_SPACE)
        {
            if(scene8.getOn()==false && scene9.getOn()==false)
            {
                d.setAttack(true);
                HeroAttack=13;
                if(w.getAttack())
                {
                    w.setHP(w.getHP()-d.getAtt());
                }
            }
        }

        if(code==e.VK_R)
        {
            if(scene8.getOn()==false && scene9.getOn()==false)
            {
                d.restore();
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
            if(scene8.getOn()==true)
            {
                scene8.getAudio().play();
                if(scene8.getX()>=scene8.getSize()-1)
                {
                    scene8.setOn(false);
                    scene8.getAudio().stop();
                    //fight=true;
                    //enemies.loop();
                }
                else 
                    scene8.nextScene();
            }

            if(scene9.getOn()==true)
            {
                scene9.getAudio().play();
                if(scene9.getX()>=scene9.getSize()-1)
                {
                    scene9.setOn(false);
                    scene9.getAudio().stop();
                }
                else 
                {
                    creepy.stop();
                    scene9.nextScene();
                }
            }
        }
        repaint();
    }

    public void mouseExited(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}
}