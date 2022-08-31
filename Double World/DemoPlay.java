import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.applet.AppletStub;
import java.applet.*;
/*Applet to make a demo of Double World
 * Amanda Ramos, started: March 2nd, 2014, working in class: May 28th, 2014
 */

public class DemoPlay extends Applet implements Runnable, KeyListener, AppletStub
{
    Thread main= new Thread(this);
    Font font= new Font( "Chiller", 1, 40);
    Font introFont= new Font("Segoe UI Black", 1, 28);
    Color myBrown=new Color( 110, 70, 60 );
    Image stage[]=new Image[5];
    AudioClip theme; //written and played by Cynthia Melillo
    int stageCt=0, keyCt=0;
    boolean key1=false, key2=false, key3=false, section1=true;
    MovingPic aurora, gotKey1, gotKey2, gotKey3;
    //much easier to work with for me if they're moving images plus I may want them to move but not sure yet
    Image buffer, auroraPic, keyPic, door;
    //unless stated otherwise, these pictures were made by me
    Applet appletToLoad= new Applet();
    Graphics bufferG;
    //aurora does not have a picture so i used a rectangle for now...
    
    public void appletResize(int width, int length)
    {
        resize( 1300, 700 );  
    }

    public void init()
    {
        this.setLayout(null);
        this.addKeyListener(this);
        resize(1300,700);
        
        theme=this.getAudioClip(this.getCodeBase(),"Blisful Nightmare.wav");
        theme.loop();

        stage[0]=this.getImage(this.getCodeBase(),"stage test 1.png");
        stage[1]=this.getImage(this.getCodeBase(),"stage test 2.png");
        stage[2]=this.getImage(this.getCodeBase(),"stage test 3.png");
        stage[3]=this.getImage(this.getCodeBase(),"stage test 15.png");
        stage[4]=this.getImage(this.getCodeBase(),"stage test 17.png");

        gotKey1=new MovingPic (keyPic,0,625,400,0,0);
        keyPic=this.getImage(this.getCodeBase(),"key.png");
        gotKey1.setAlive(true);
        gotKey1.start();

        gotKey2=new MovingPic (keyPic,0,450,300,0,0);
        keyPic=this.getImage(this.getCodeBase(),"key.png");
        gotKey2.setAlive(true);
        gotKey2.start();

        gotKey3=new MovingPic (keyPic,0, 1000,490,0,0);
        keyPic=this.getImage(this.getCodeBase(),"key.png");
        gotKey3.setAlive(true);
        gotKey3.start();

        door=this.getImage(this.getCodeBase(),"door.png");

        aurora=new MovingPic (auroraPic,0,10,480,0,0);
        auroraPic=this.getImage(this.getCodeBase(),"character.png");
        aurora.setAlive(true);
        aurora.start();

        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();

        main.start();
    }

    public void run()
    {
        do
        {
            Rectangle key1Rect=new Rectangle(gotKey1.getX(), gotKey1.getY(),70,25);
            Rectangle key2Rect=new Rectangle(gotKey2.getX(), gotKey2.getY(),70,25);
            Rectangle key3Rect=new Rectangle(gotKey3.getX(), gotKey3.getY(),70,25);
            Rectangle auroraRect=new Rectangle(aurora.getX(),aurora.getY()+80, 60,140);

            repaint();
            if(keyCt==3 && aurora.getY()<0 && stageCt==2)
            {
                section1=false;
            }
            if(aurora.getX()>1050 &&stageCt==0)
            {
                stageCt=1;
                aurora.setX(1000);
            }
            if(aurora.getX()<0&&stageCt==1)
            {
                stageCt=2;
                aurora.setX(1270);
                aurora.setY(310);
            }
            if(aurora.getX()<0&&stageCt==2)
            {
                stageCt=3;
                aurora.setX(1200);
                aurora.setY(210);
            }
            if(aurora.getX()>1230&&stageCt==3)
            {
                stageCt=2;
                aurora.setX(10);
                aurora.setY(310);
            }
            if(aurora.getY()>650&&450<aurora.getX()&&aurora.getX()<700&&stageCt==2)
            {
                stageCt=4;
                aurora.setY(50);
            }
            if(aurora.getY()<-10&&450<aurora.getX()&&aurora.getX()<800&&stageCt==4)
            {
                stageCt=2;
                aurora.setY(650);
            }
            if(key1==false && stageCt==2)
            {
                if(auroraRect.intersects(key1Rect))
                {
                    key1=true;
                    keyCt++;
                }
            }
            if(key2==false && stageCt==3)
            {
                if(auroraRect.intersects(key2Rect))
                {
                    key2=true;
                    keyCt++;
                }
            }
            if(key3==false && stageCt==4)
            {
                if(auroraRect.intersects(key3Rect))
                {
                    key3=true;
                    keyCt++;
                }
            }
            try
            {main.sleep(100);}
            catch(Exception e){}
        }
        while(section1);
        try
        {
            Class applet2=Class.forName("gatekeeperTest");
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
        if(stageCt==0)
        {
            bufferG.drawImage(stage[0],0,0,1300,700,this);
            bufferG.drawImage(auroraPic, aurora.getX(),480,60,140,this);
            bufferG.setColor(Color.black);
            bufferG.setFont(introFont);
            bufferG.drawString("Welcome to my demo! To move press the arrow keys.",50,100);
            bufferG.drawString("If any text appears, press the SPACE key.",50,150);
            bufferG.drawString("Some areas may require you to use your mouse.",50,200);
            bufferG.drawString("If you can't move Aurora, click the screen.",50,250);
            bufferG.drawString("To start your adventure, enter the castle then move left.",50,300);
            bufferG.drawString("Have fun solving the puzzles and enjoy!",50,350);
            bufferG.drawString("Remember: This is a DEMO, ideas and tests are not fully decided yet.",50,400);
        }
        if(stageCt==1)
        {
            bufferG.drawImage(stage[1],0,0,1300,700,this);
            bufferG.drawImage(auroraPic,aurora.getX(),480,60,140,this);
        }
        if(stageCt==2)
        {
            bufferG.drawImage(stage[2],0,0,1300,700,this);
            if(keyCt<3)
            {
                bufferG.setColor(Color. black);
                bufferG.drawString("Find enough keys to unlock this door.",460,50);
                bufferG.drawImage(door,520,80,250,250,this);
            }
            bufferG.drawImage(auroraPic,aurora.getX(),aurora.getY(),60,140,this);
            if(key1==false)
                bufferG.drawImage(keyPic,gotKey1.getX(),gotKey1.getY(),70,25,this);
        }
        if(stageCt==3)
        {
            bufferG.drawImage(stage[3],0,0,1300,700,this);
            bufferG.drawImage(auroraPic,aurora.getX(),aurora.getY(),60,140,this);
            if(key2==false)
                bufferG.drawImage(keyPic,gotKey2.getX(),gotKey2.getY(),70,25,this);
        }
        if(stageCt==4)
        {
            bufferG.drawImage(stage[4],0,0,1300,600,this);
            bufferG.drawImage(auroraPic,aurora.getX(),aurora.getY(),60,140,this);
            if(key3==false)
                bufferG.drawImage(keyPic,gotKey3.getX(),gotKey3.getY(),70,25,this);
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
            aurora.setXChange(0);
        }
        if(code==e.VK_LEFT)
        {
            aurora.setXChange(0);
        }
        if(code==e.VK_UP)
        {
            aurora.setYChange(0);
        }
        if(code==e.VK_DOWN)
        {
            aurora.setYChange(0);
        }
    }

    public void keyPressed(KeyEvent e)
    {
        int code=e.getKeyCode();
        if(code==e.VK_RIGHT)
        {
            aurora.setXChange(10);
        }
        if(code==e.VK_LEFT)
        {
            aurora.setXChange(-10);
        }
        if(code==e.VK_DOWN)
        {
            aurora.setYChange(10);
        }
        if(code==e.VK_UP)
        {
            aurora.setYChange(-10);
        }
        repaint();
    }
}