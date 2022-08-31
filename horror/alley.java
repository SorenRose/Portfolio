import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.applet.AppletStub;
import java.applet.*;
import java.util.*;
/*Applet to make a game
 * Amanda Ramos, April 23rd, 2015
 */

public class alley extends Applet implements Runnable, KeyListener, MouseListener, AppletStub
{
    Thread main= new Thread(this);
    Applet appletToLoad= new Applet();
    Font font= new Font( "Chiller", 1, 40);
    Font introFont= new Font("Segoe UI Blpockyack", 1, 28);
    ArrayList<Enemy> baddy=new ArrayList<Enemy>();
    Image[] bad=new Image[10];
    Dove d;
    int x=0;//move the stage
    int timer=0;
    cut scene2,scene3;
    Image[] person2=new Image[6];
    AudioClip[] talk2=new AudioClip[6];
    Image[] person3=new Image[2];
    AudioClip[] talk3=new AudioClip[2];
    boolean fight=false, allDead=false, alleyOn=true;
    int HeroFrame=1, HeroAttack=13;
    int frame=0, frame2=7;
    Image buffer, background,black;
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
        this.addMouseListener(this);
        resize(1300,700);

        Nottina[0]=getImage(this.getCodeBase(),"N stand.png");
        for(int g=1;g<13;g++)
        {
            Nottina[g]=this.getImage(this.getCodeBase(),"nottina"+(g)+".png");
        }
        for(int h=13;h<20;h++)
            Nottina[h]=this.getImage(this.getCodeBase(),"Nottina attack"+(h-12)+".png");

        for(int g=0;g<10;g++)
        {
            bad[g]=this.getImage(this.getCodeBase(),"shade"+(g+1)+".png");
        }

        baddy.add(new Enemy (bad,100,-300,4));
        baddy.add(new Enemy (bad,100,1500,6));
        baddy.add(new Enemy (bad,100,-600,5));
        baddy.add(new Enemy (bad,100,-1000,8));
        baddy.add(new Enemy (bad,100,-1400,9));
        baddy.add(new Enemy (bad,100,1800,10));
        baddy.add(new Enemy (bad,100,2100,7));
        baddy.add(new Enemy (bad,100,2400,9));

        d=new Dove (Nottina,10,450,0);
        d.setAlive(true);
        d.start();

        background=this.getImage(this.getCodeBase(),"stage.png");
        black=this.getImage(this.getCodeBase(),"black.png");

        alleys=this.getAudioClip(this.getCodeBase(),"Inquiry.wav");
        alleys.loop();
        enemies=this.getAudioClip(this.getCodeBase(),"Shousou.wav");//taken from youtube, soundtrack of Ib

        scene2=new cut (20, person2,talk2,0,true);
        scene2.setOn(false);
        for(int t=0;t<6;t++)
        {
            talk2[t]=this.getAudioClip(this.getCodeBase(),"talk"+(t+8)+".wav");
            person2[t]=this.getImage(this.getCodeBase(),"pic"+(t+8)+".png");
        }

        scene3=new cut (20, person3,talk3,0,true);
        scene3.setOn(false);
        for(int t=0;t<2;t++)
        {
            talk3[t]=this.getAudioClip(this.getCodeBase(),"talk"+(t+14)+".wav");
            person3[t]=this.getImage(this.getCodeBase(),"pic"+(t+14)+".png");
        }     

        for(Enemy e:baddy)
            e.start();

        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();

        main.start();
    }

    public void run()
    {
        do
        {
            repaint();

            if(x<=-1320 && scene2.getX()<scene2.getSize()-1)
                scene2.setOn(true);
            //else
            //  scene2.setOn(false);

            for(Enemy e:baddy)
            {
                if(fight==true)
                {
                    alleys.stop();
                    e.setAlive(true);
                }
            }

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
            if(frame>7)
                frame=0;
            for(Enemy e:baddy)
            {
                if(e.getHP()<=0)
                {
                    e.setAlive(false);
                }
                if(e.getAlive()==true)
                {
                    e.direction(d.getX());
                    e.setX(e.getX()+e.getXChange());
                }
                if(e.getAttack() && e.getAlive()==true)
                {
                    frame2++;
                    if(frame2>bad.length)
                        frame2=7;
                    if(e.getTimer()>6)
                    {
                        d.setHP(d.getHP()-e.getAtt());
                        e.setTimer(0);
                    }
                    e.setTimer(e.getTimer()+1);
                }
            }

            if(baddy.get(0).getAlive()==false && baddy.get(1).getAlive()==false &&
            baddy.get(2).getAlive()==false && baddy.get(3).getAlive()==false &&
            baddy.get(4).getAlive()==false && baddy.get(5).getAlive()==false &&
            baddy.get(6).getAlive()==false && baddy.get(7).getAlive()==false &&
            scene2.getX()==scene2.getSize()-1 && x<=-1320 && fight==true)
                allDead=true;

            for(Enemy e:baddy)
                if(allDead==true && x<=-1320 && fight==true)
                    scene3.setOn(true);

            if(allDead==true && scene3.getX()>=scene3.getSize()-1)
            {
                timer++;
                if(timer==30)
                alleyOn=false;
                enemies.stop();
            }

            try
            {main.sleep(110);}
            catch(Exception e){}
        }
        while(alleyOn);

        try
        {
            Class applet2=Class.forName("house");
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

        for(int x=0;x<baddy.size();x++)
        {
            if(baddy.get(x).getAlive())
            {
                if(baddy.get(x).getAttack()==false)
                {
                    if(baddy.get(x).getLeft())
                        bufferG.drawImage(bad[frame], baddy.get(x).getX(),500,100,140,this);
                    if(baddy.get(x).getRight())
                        bufferG.drawImage(bad[frame], baddy.get(x).getX()+100,500,-100,140,this);
                }
                if(baddy.get(x).getAttack()==true)
                {
                    if(baddy.get(x).getLeft())
                        bufferG.drawImage(bad[frame2], baddy.get(x).getX(),500,100,140,this);
                    if(baddy.get(x).getRight())
                        bufferG.drawImage(bad[frame2], baddy.get(x).getX()+100,500,-100,140,this);
                }
            }
        }
        
        for(int x=0;x<d.getHP();x++)
        {
            bufferG.fillRect(20+1*x,20,1,30);
        }
        
        if(scene2.getOn()==true)
        {
            bufferG.drawImage(black,0,0,1300,700,this);
            bufferG.drawImage(scene2.getImage(),0,0,1300,700,this);
        }

        if(scene3.getOn()==true)
        {
            bufferG.drawImage(black,0,0,1300,700,this);
            bufferG.drawImage(scene3.getImage(),0,0,1300,700,this);
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
            if(scene2.getOn()==false && scene3.getOn()==false)
            {
                if(x>-1320)
                {
                    if(fight==false)
                    {
                        x-=6;
                        d.setX(d.getX()+2);
                    }
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
            if(scene2.getOn()==false && scene3.getOn()==false)
            {
                if(x<0)
                {
                    if(fight==false)
                    {
                        x+=6;
                        d.setX(d.getX()-2);
                    }
                }
                else
                {   
                    d.setX(d.getX()-5);
                }
                if(fight==true)
                    d.setX(d.getX()-5);
                d.setRun(true);
                d.setDirection(false);
            }
        }

        if(code==e.VK_SPACE)
        {
            if(scene2.getOn()==false && scene3.getOn()==false)
            {
                d.setAttack(true);
                HeroAttack=13;
                for(int r=0;r<baddy.size();r++)
                {
                    if(baddy.get(r).getAttack())
                    {
                        baddy.get(r).setHP(baddy.get(r).getHP()-d.getAtt());
                    }
                }
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
            if(scene2.getOn()==true)
            {
                scene2.getAudio().play();
                if(scene2.getX()>=scene2.getSize()-1)
                {
                    scene2.setOn(false);
                    scene2.getAudio().stop();
                    fight=true;
                    enemies.loop();
                }
                else 
                    scene2.nextScene();
            }

            if(scene3.getOn()==true)
            {
                scene3.getAudio().play();
                if(scene3.getX()>=scene3.getSize()-1)
                {
                    scene3.setOn(false);
                    scene3.getAudio().stop();
                }
                else 
                {
                    enemies.stop();
                    scene3.nextScene();
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