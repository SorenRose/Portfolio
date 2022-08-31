import java.awt.*;
import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.applet.AppletStub;
/*Applet to work with a hallway
 * Amanda Ramos, June 2nd, 2014, working on this on 6/12/14
 */

public class hall extends Applet implements Runnable, KeyListener, AppletStub
{
    Thread main= new Thread(this);
    Font font= new Font( "Chiller", 1, 40);
    Font gameoverFont= new Font( "Chiller", 1, 280);
    Image stage[]=new Image[4];
    boolean creepyStage=false,gameover,section3=true,dogChase=false,bunnyRun=true;
    int mouseCt, TimeLimitCt=50,TimeGameOverCt=0;
    MovingPic aurora;
    int lifeCt=5,stageCt=0;
    MovingPic decorations[]=new MovingPic[10];
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

        stage[0]=this.getImage(this.getCodeBase(),"stage test 19.png");
        stage[1]=this.getImage(this.getCodeBase(),"stage test 21.png");
        stage[2]=this.getImage(this.getCodeBase(),"stage test 10.png");
        stage[3]=this.getImage(this.getCodeBase(),"stage test 27.png");

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

        for(int i=0; i<10;i++)
        {
            decorationsPics=this.getImage(this.getCodeBase(),"collum test.png");//spelled it wrong
            decorations[i]=new MovingPic (decorationsPics,0,0+400*i,-70,0,0);
            decorations[i].setAlive(true);
            decorations[i].start();
        }

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
            repaint();
            Rectangle bunnyRect=new Rectangle(bunny.getX(), bunny.getY(),60,120);
            Rectangle auroraRect=new Rectangle(aurora.getX(),aurora.getY()+80, 60,140);
            /* if(creepyStage=true && aurora.getX()>1230)
            {
            section3=false;
            }
            if(lifeCt==0)
            gameover=true;
            if(gameover==true)
            {
            TimeGameOverCt++;
            }*/
            if(stageCt==2)
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
            if(stageCt==1 &&bunnyRun==false)
            {
                bunny.setX(550);
                bunny.setY(310);
                dog.setXChange(-20);
                bunny.setXChange(-20);
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
            if(aurora.getX()>1210&&stageCt==1)
            {
                stageCt=3;
                aurora.setX(30);
                aurora.setY(480);
                creepyStage=true;
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
            if(creepyStage==true)
            {
                TimeLimitCt--;
                try
                {main.sleep(850);}
                catch(Exception e){}
                if(TimeLimitCt==0)
                    gameover=true;
            }            
        }
        while(true);//gameover=false || section3);
        //work with applets!
        //not working because this time, there are TWO applets if you die!
        /*if(section3==false)
        {
        try
        {
        Class applet2=Class.forName("nextArea");
        appletToLoad=(Applet)applet2.newInstance();
        appletToLoad.setStub(this);
        add(appletToLoad);
        appletToLoad.init();
        appletToLoad.start();
        }
        catch (Exception p){}
        }
        if(gameover)
        {
        if(TimeGameOverCt>10)
        {
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
        }*/
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
            if(bunnyRun==false && aurora.getY()>450)
            {
                bufferG.drawImage(bunnyPic,bunny.getX(),bunny.getY(),60,120,this);
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
                bufferG.drawString(""+bunny.getY()+"  "+bunny.getX(),30,30);
            }
            else
            {
                bufferG.drawImage(textBox,100,20,1100,160,this);
                bufferG.drawString("You got a bunny!",140,80);
                bufferG.drawString("Maybe this will get the dog's attention.",140,120);
            }
        }
        if(stageCt==3)
        {
            bufferG.drawImage(stage[3],0,0,2300,700,this);
            bufferG.drawImage(auroraPic,aurora.getX(),450,60,140,this);
            bufferG.setColor(Color.red);
            bufferG.drawString(""+TimeLimitCt,10,40);
            for(int i=0 ;i<10; i++)
                bufferG.drawImage(decorations[i].getImage(),decorations[i].getX(),decorations[i].getY(),200,800,this);
            //add clock?
            //add creepy things? 
            //this area is supposed to bring a little fear, this is a nightmare after all
            /*if(aurora.getX()>100)
            bufferG.drawString("Hey",100,50);
            if(aurora.getX()>200)
            bufferG.drawString("Where are you going?",200,100);
            if(aurora.getX()>300)
            bufferG.drawString("Come play with me",300,150);
            if(aurora.getX()>400)
            bufferG.drawString("Don't ignore me",400,200);
            if(aurora.getX()>500)
            bufferG.drawString("Play with me!",500,250);
            if(aurora.getX()>600)
            bufferG.drawString("Stay here with me!",600,300);
            if(aurora.getX()>700)
            bufferG.drawString("Don't leave me!",700,350);
            if(aurora.getX()>1100)
            bufferG.drawString("You'll never escape...",500,450);*/
        }
        //use red screed for the hallway
        if(gameover)
        {
            bufferG.setColor(Color.white);
            bufferG.setFont(gameoverFont);
            bufferG.drawImage(gameoverScreen,0,0,1300,700,this);
            bufferG.drawString("GAME OVER",10,400);
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
        if(creepyStage==false)
        {
            if(code==e.VK_RIGHT)
            {
                aurora.setXChange(0);
                if(stageCt==2)
                    bunny.setXChange(0);
            }
            if(code==e.VK_LEFT)
            {
                aurora.setXChange(0);
                if(stageCt==2)
                    bunny.setXChange(0);
            }
            if(code==e.VK_UP)
            {
                aurora.setYChange(0);
                if(stageCt==2)
                    bunny.setYChange(0);
            }
            if(code==e.VK_DOWN)
            {
                aurora.setYChange(0);
                if(stageCt==2)
                    bunny.setYChange(0);
            }
        }
        else
        {
            if(code==e.VK_RIGHT)
            {
                aurora.setXChange(0);
                for(int i=0;i<10;i++)
                    decorations[i].setXChange(0);
            }
            if(code==e.VK_LEFT)
            {
                aurora.setXChange(0);
                for(int i=0;i<10;i++)
                    decorations[i].setXChange(0);
            }
        }
    }

    public void keyTyped(KeyEvent e){}

    public void keyPressed(KeyEvent e)
    {
        int code=e.getKeyCode();

        if(creepyStage==false)
        {
            if(code==e.VK_RIGHT)
            {
                aurora.setXChange(10);
                if(stageCt==2)
                    bunny.setXChange(-10);
            }
            if(code==e.VK_LEFT)
            {
                aurora.setXChange(-10);
                if(stageCt==2)
                    bunny.setXChange(10);
            }
            if(code==e.VK_DOWN)
            {
                aurora.setYChange(10);
                if(stageCt==2)
                    bunny.setYChange(-10);
            }
            if(code==e.VK_UP)
            {
                aurora.setYChange(-10);
                if(stageCt==2)
                    bunny.setYChange(10);
            }
        }
        else
        {
            if(code==e.VK_RIGHT)
            {
                aurora.setXChange(3);
                for(int i=0;i<10;i++)
                    decorations[i].setXChange(-5);
            }
            if(code==e.VK_LEFT)
            {
                aurora.setXChange(-3);
                for(int i=0;i<10;i++)
                    decorations[i].setXChange(5);
            }
        }
        repaint();
    }
}