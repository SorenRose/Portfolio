import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.applet.AppletStub;
import java.applet.*;
import java.util.*;
/*Applet to make a game
 * Amanda Ramos, May 12th, 2015
 */

public class house extends Applet implements Runnable, KeyListener, MouseListener, AppletStub
{
    Thread main= new Thread(this);
    Applet appletToLoad= new Applet();
    Font font= new Font( "Chiller", 1, 40);
    Font introFont= new Font("Segoe UI Blpockyack", 1, 28);
    ArrayList<Enemy> baddy=new ArrayList<Enemy>();
    Image[] bad=new Image[8];
    int frame=0;
    int x=0; //to move house
    Dove d;
    int timer=0;
    cut scene4,scene5,scene6;
    Image[] person4=new Image[6];
    AudioClip[] talk4=new AudioClip[6];
    Image[] person5=new Image[3];
    AudioClip[] talk5=new AudioClip[3];
    Image[] person6=new Image[4];
    AudioClip[] talk6=new AudioClip[4];
    boolean key, streetOn=true;
    ArrayList theme=new ArrayList<AudioClip>();
    Image buffer, background,black;
    Image[] Nottina=new Image[13];
    int HeroFrame=1;
    AudioClip creepy,enemies;
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
        Nottina[0]=getImage(this.getCodeBase(),"N stand.png");
        for(int g=1;g<13;g++)
        {
            Nottina[g]=this.getImage(this.getCodeBase(),"nottina"+(g)+".png");
        }
        d.setAlive(true);
        d.start();

        for(int g=0;g<8;g++)
        {
            bad[g]=this.getImage(this.getCodeBase(),"shade"+(g+1)+".png");
        }
        baddy.add(new Enemy (bad,100,-200,4));
        baddy.add(new Enemy (bad,100,-260,6));
        baddy.add(new Enemy (bad,100,-380,5));
        baddy.add(new Enemy (bad,100,-430,8));
        baddy.add(new Enemy (bad,100,-500,9));
        baddy.add(new Enemy (bad,100,-140,10));
        baddy.add(new Enemy (bad,100,-570,7));
        baddy.add(new Enemy (bad,100,-630,9));
        baddy.add(new Enemy (bad,100,-690,8));
        baddy.add(new Enemy (bad,100,-730,9));
        baddy.add(new Enemy (bad,100,-800,10));
        baddy.add(new Enemy (bad,100,-850,7));
        baddy.add(new Enemy (bad,100,-910,9));
        for(Enemy e:baddy)
            e.start();        

        background=this.getImage(this.getCodeBase(),"home3.png");
        black=this.getImage(this.getCodeBase(),"black.png");

        creepy=this.getAudioClip(this.getCodeBase(),"Disappearance.wav");//taken from youtube, soundtrack of Ib
        creepy.loop();
        enemies=this.getAudioClip(this.getCodeBase(),"Shousou.wav");

        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();

        scene4=new cut (20, person4,talk4,0,true);
        for(int t=0;t<6;t++)
        {
            talk4[t]=this.getAudioClip(this.getCodeBase(),"talk"+(t+16)+".wav");
            person4[t]=this.getImage(this.getCodeBase(),"pic"+(t+16)+".png");
        }

        scene4.getAudio().play();

        scene5=new cut (20, person5,talk5,0,true);
        scene5.setOn(false);
        for(int t=0;t<3;t++)
        {
            talk5[t]=this.getAudioClip(this.getCodeBase(),"talk"+(t+22)+".wav");
            person5[t]=this.getImage(this.getCodeBase(),"pic"+(t+22)+".png");
        }

        scene6=new cut (20, person6,talk6,0,true);
        scene6.setOn(false);
        for(int t=0;t<4;t++)
        {
            talk6[t]=this.getAudioClip(this.getCodeBase(),"talk"+(t+25)+".wav");
            person6[t]=this.getImage(this.getCodeBase(),"pic"+(t+25)+".png");
        }

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

            if(d.getX()>970 && scene5.getX()<scene5.getSize()-1)
            {
                scene5.setOn(true); 
            }
            if(scene6.getOn()==true)
                creepy.stop();

            frame++;
            if(frame>7)
                frame=0;
            for(Enemy e:baddy)
            {
                if(x>-1410)
                {
                    for(Enemy v:baddy)
                        v.setXChange(1);
                }
                else
                {
                    for(Enemy v:baddy)
                        v.setXChange(3);
                }
                if(e.getAlive()==true)
                {
                    e.direction(d.getX());
                    e.setX(e.getX()+e.getXChange());
                }
            }

            if(d.getX()>1000 && key==true)
            {
                streetOn=false;
                enemies.stop();
            }
            try
            {main.sleep(110);}
            catch(Exception e){}
        }
        while(streetOn);

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
        bufferG.drawImage(background, x,0,2700,700,this);
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

        for(int x=0;x<baddy.size();x++)
        {
            if(baddy.get(x).getAlive())
            {
                if(baddy.get(x).getAttack()==false)
                {
                    if(baddy.get(x).getLeft())
                        bufferG.drawImage(bad[frame], baddy.get(x).getX(),470,180,220,this);
                    if(baddy.get(x).getRight())
                        bufferG.drawImage(bad[frame], baddy.get(x).getX()+180,470,-180,220,this);
                }
            }
        }

        for(int x=0;x<d.getHP();x++)
        {
            bufferG.fillRect(20+1*x,20,1,30);
        }
        
        if(scene4.getOn()==true)
        {
            bufferG.drawImage(black,0,0,1300,700,this);
            bufferG.drawImage(scene4.getImage(),0,0,1300,700,this);
        }

        if(scene5.getOn()==true)
        {
            bufferG.drawImage(black,0,0,1300,700,this);
            bufferG.drawImage(scene5.getImage(),0,0,1300,700,this);
        }

        if(scene6.getOn()==true)
        {
            bufferG.drawImage(black,0,0,1300,700,this);
            bufferG.drawImage(scene6.getImage(),0,0,1300,700,this);
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
            if(scene4.getOn()==false && scene5.getOn()==false && scene6.getOn()==false)
            {
                if(x>-1410)
                {
                    x-=6;
                    d.setX(d.getX()+2);

                    for(Enemy v:baddy)
                        v.setXChange(1);
                }
                else
                {
                    d.setX(d.getX()+5);
                    for(Enemy v:baddy)
                        v.setXChange(3);
                }
                d.setRun(true);
                d.setDirection(true);
            }
        }
        if(code==e.VK_LEFT)
        {
            if(scene4.getOn()==false && scene5.getOn()==false && scene6.getOn()==false)
            {
                if(x<0)
                {
                    x+=6;
                    d.setX(d.getX()-2);
                }
                else
                    d.setX(d.getX()-5);
                d.setRun(true);
                d.setDirection(false);
            }
        }  

        if(code==e.VK_S)
        {
            if(d.getX()>=160 && d.getX()<=260 &&x>=-20)
            {
                key=true;
                scene6.setOn(true);
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
            if(scene4.getOn()==true)
            {
                if(scene4.getX()>=scene4.getSize()-1)
                {
                    scene4.setOn(false);
                    scene4.getAudio().stop();
                }
                else 
                    scene4.nextScene();
            }

            if(scene5.getOn()==true)
            {
                if(scene5.getX()>=scene5.getSize()-1)
                {
                    scene5.setOn(false);
                    scene5.getAudio().stop();
                }
                else 
                {
                    scene5.nextScene();
                    scene5.getAudio().play();
                }
            }

            if(scene6.getOn()==true)
            {
                if(scene6.getX()>=scene6.getSize()-1)
                {
                    scene6.setOn(false);
                    scene6.getAudio().stop();

                    enemies.play();
                    for(Enemy b:baddy)
                        b.setAlive(true);
                }
                else 
                {
                    scene6.nextScene();
                    scene6.getAudio().play();
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