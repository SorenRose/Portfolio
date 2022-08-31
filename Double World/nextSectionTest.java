import java.awt.*;
import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.applet.AppletStub;
import java.applet.*;
/*Applet to work with another section in my game
 * Amanda Ramos, June 2nd, 2014
 */

public class nextSectionTest extends Applet implements Runnable, KeyListener, AppletStub
{
    Thread main= new Thread(this);
    Font font= new Font( "Chiller", 1, 40);
    Font gameoverFont= new Font( "Chiller", 1, 280);
    Image stage[]=new Image[3];
    boolean section3=true,dogChase=false,bunnyRun=true;
    int mouseCt, TimeLimitCt=50,TimeGameOverCt=0;
    MovingPic aurora;
    int lifeCt=5,stageCt=0;
    AudioClip bark; //recorded from my dog while begging for ham
    AudioClip theme;
    MovingPic dog, bunny;
    Image buffer, auroraPic, gameoverScreen, decorationsPics;
    Image dogPic, bunnyPic, textBox;
    Applet appletToLoad= new Applet();
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

        bark=this.getAudioClip(this.getCodeBase(),"sadie bark 2.wav");
        
        theme=this.getAudioClip(this.getCodeBase(),"Blisful Nightmare.wav");
        theme.loop();

        stage[0]=this.getImage(this.getCodeBase(),"stage test 19.png");
        stage[1]=this.getImage(this.getCodeBase(),"stage test 21.png");
        stage[2]=this.getImage(this.getCodeBase(),"stage test 10.png");

        gameoverScreen=this.getImage(this.getCodeBase(),"red screen test.png");
        textBox=this.getImage(this.getCodeBase(),"hint text.png");

        dog=new MovingPic (dogPic,0,930,300,0,0);
        dogPic=this.getImage(this.getCodeBase(),"dog.png");
        dog.setAlive(true);
        dog.start();

        bunny=new MovingPic (bunnyPic,0,550,180,0,0);
        bunnyPic=this.getImage(this.getCodeBase(),"bunny.png");
        bunny.setAlive(true);
        bunny.start();

        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();

        aurora=new MovingPic (auroraPic,0,1200,340,0,0);
        auroraPic=this.getImage(this.getCodeBase(),"character.png");
        aurora.setAlive(true);
        aurora.start();

        main.start();
    }

    public void run()
    {
        do
        {
            Rectangle bunnyRect=new Rectangle(bunny.getX(), bunny.getY(),60,120);
            Rectangle auroraRect=new Rectangle(aurora.getX(),aurora.getY()+80, 60,140);
            repaint();
            if(aurora.getX()>1220 && stageCt==1)
            {
                section3=false;
            }
            if(stageCt==1 && bunnyRun==false)
            {
                dog.setXChange(-15);
                bunny.setXChange(-15);
                bark.play();
            }
            if(stageCt==2 && bunnyRun==true)
            {
                //I want him to stay in a certain area...
                if(bunny.getY()<80)
                    bunny.setYChange(aurora.getYChange()*-1);
                if(bunny.getX()<130)
                    bunny.setXChange(aurora.getXChange());
                if(bunny.getY()>380)
                    bunny.setYChange(aurora.getYChange()*-1);
                if(bunny.getX()>900)
                    bunny.setXChange(aurora.getXChange());
                if(auroraRect.intersects(bunnyRect))
                {
                    bunnyRun=false;
                }
            }

            if(aurora.getY()<10 &&stageCt==0)
            {
                stageCt=1;
                aurora.setY(600);
            }
            if(aurora.getY()>630&&stageCt==1)
            {
                stageCt=0;
                aurora.setY(30);
            }
            if(aurora.getY()<10&&stageCt==1)
            {
                stageCt=2;
                aurora.setY(600);
            }
            if(aurora.getY()>630&&stageCt==2)
            {
                stageCt=1;
                aurora.setY(30);
            }          
            try
            {main.sleep(100);}
            catch(Exception e){} 
        }
        while(section3);
        try
        {
            Class applet2=Class.forName("finalArea");
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
        if(stageCt==0)
        {
            bufferG.drawImage(stage[0],0,0,1300,700,this);
            bufferG.drawImage(auroraPic,aurora.getX(),aurora.getY(),60,140,this);
        }
        if(stageCt==1)
        {
            bufferG.drawImage(stage[1],0,0,1300,700,this);
            if(bunnyRun==true)
                bufferG.drawImage(auroraPic,600,aurora.getY(),60,140,this);
            else
                bufferG.drawImage(auroraPic,aurora.getX(),aurora.getY(),60,140,this);
            bufferG.drawImage(dogPic,dog.getX(),dog.getY(),-200,160,this);
            if(aurora.getY()<350 && bunnyRun==true)
            {
                bufferG.drawImage(textBox,100,510,1100,160,this);
                bufferG.drawString("The dog is just staring... He won't let you pass.",140,550);
                bufferG.drawString("Find something to distract the dog and move on.",140,590);
            }
            if(bunnyRun==false)
            {
                bufferG.drawImage(bunnyPic,bunny.getX(),310,60,120,this);
                bufferG.drawImage(dogPic,dog.getX(),dog.getY(),-200,160,this);
            }
        }
        if(stageCt==2)
        {
            bufferG.drawImage(stage[2],0,0,1300,700,this);
            bufferG.drawImage(auroraPic,aurora.getX(),aurora.getY(),60,140,this);
            if(bunnyRun==true)
            {
                bufferG.drawImage(bunnyPic,bunny.getX(),bunny.getY(),60,120,this);
            }
            else
            {
                bufferG.drawImage(textBox,100,20,1100,160,this);
                bufferG.drawString("You got a bunny!",140,80);
                bufferG.drawString("Maybe this will get the dog's attention.",140,120);
            }
        }
        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void keyReleased(KeyEvent e)
    {
        int code=e.getKeyCode();
        if(code==e.VK_RIGHT)
        {
            aurora.setXChange(0);
            if(stageCt==2 && bunnyRun==true)
                bunny.setXChange(0);
        }
        if(code==e.VK_LEFT)
        {
            aurora.setXChange(0);
            if(stageCt==2 && bunnyRun==true)
                bunny.setXChange(0);
        }
        if(code==e.VK_UP)
        {
            aurora.setYChange(0);
            if(stageCt==2 && bunnyRun==true)
                bunny.setYChange(0);
        }
        if(code==e.VK_DOWN)
        {
            aurora.setYChange(0);
            if(stageCt==2 && bunnyRun==true)
                bunny.setYChange(0);
        }
    }

    public void keyTyped(KeyEvent e){}

    public void keyPressed(KeyEvent e)
    {
        int code=e.getKeyCode();
        if(code==e.VK_RIGHT)
        {
            aurora.setXChange(10);
            if(stageCt==2 && bunnyRun==true)
                bunny.setXChange(-10);
        }
        if(code==e.VK_LEFT)
        {
            aurora.setXChange(-10);
            if(stageCt==2 && bunnyRun==true)
                bunny.setXChange(10);
        }
        if(code==e.VK_DOWN)
        {
            aurora.setYChange(10);
            if(stageCt==2 && bunnyRun==true)
                bunny.setYChange(-10);
        }
        if(code==e.VK_UP)
        {
            aurora.setYChange(-10);
            if(stageCt==2 && bunnyRun==true)
                bunny.setYChange(10);
        }
        repaint();
    }
    }