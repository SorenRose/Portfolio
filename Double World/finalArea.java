import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.applet.Applet;
import java.applet.AppletStub;
import java.applet.*;
/*Applet to work with another section in my game
 * Amanda Ramos, June 4th, 2014
 */

public class finalArea extends Applet implements Runnable, KeyListener, AppletStub
{
    Thread main= new Thread(this);
    Font font= new Font( "Chiller", 1, 40);
    Image stage[]=new Image[6];
    int mouseCt, TimeLimitCt=50, textCt;
    AudioClip theme;
    boolean speak=false, moveKey=true, foundItem=false, gaveItem=false, section4=true;
    MovingPic aurora, box;
    int lifeCt=5,stageCt=0;
    Applet appletToLoad= new Applet();
    Image buffer, auroraPic,door, text, boxPic, hintText,enemyText;
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
        
        theme=this.getAudioClip(this.getCodeBase(),"Blisful Nightmare.wav");
        theme.loop();

        stage[0]=this.getImage(this.getCodeBase(),"stage test 22.png");
        stage[1]=this.getImage(this.getCodeBase(),"stage test 23.png");
        stage[2]=this.getImage(this.getCodeBase(),"stage test 24.png");
        stage[3]=this.getImage(this.getCodeBase(),"stage test 25.png");
        stage[4]=this.getImage(this.getCodeBase(),"stage test 26.png");
        stage[5]=this.getImage(this.getCodeBase(),"stage test 28.png");

        door=this.getImage(this.getCodeBase(),"special door.png");

        text=this.getImage(this.getCodeBase(),"dead text.png");
        hintText=this.getImage(this.getCodeBase(),"hint text.png");
        enemyText=this.getImage(this.getCodeBase(),"gatekeeper Text.png");//using the same text box

        box=new MovingPic (boxPic,0,590,290,0,0);
        boxPic=this.getImage(this.getCodeBase(),"box.png");
        box.setAlive(true);
        box.start();

        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();

        aurora=new MovingPic (auroraPic,0,20,340,0,0);
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
            Rectangle boxRect=new Rectangle(box.getX(), box.getY(),100,65);
            Rectangle auroraRect=new Rectangle(aurora.getX(),aurora.getY()+80, 60,140);
            if(textCt==8 && stageCt==5)
            {
                section4=false;
            }
            if(aurora.getX()>1220 &&stageCt==0)
            {
                stageCt=1;
                aurora.setX(20);
            }
            if(aurora.getX()<10&&stageCt==1)
            {
                stageCt=0;
                aurora.setX(1210);
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
            if(aurora.getX()>1230&&stageCt==1&&gaveItem==true)
            {
                stageCt=5;
                aurora.setX(30);
                aurora.setY(330);
                textCt=5;
            }
            if(aurora.getY()<10&&stageCt==2)
            {
                stageCt=3;
                aurora.setY(600);
            }
            if(aurora.getY()>630&&stageCt==3)
            {
                stageCt=2;
                aurora.setY(30);
            }
            if(aurora.getX()>1230&&stageCt==2)
            {
                stageCt=4;
                aurora.setX(30);
                textCt=0;
            }
            if(aurora.getX()<30&&stageCt==4)
            {
                stageCt=2;
                aurora.setX(1200);
            }
            if(stageCt==3)
            {
                if(auroraRect.intersects(boxRect))
                {
                    foundItem=true;
                }
            }
            if(stageCt==4)
            {
                if(aurora.getX()>500 && textCt<3)
                {
                    speak=true;
                    if(foundItem==true)
                        gaveItem=true;
                }
                if(speak==true)
                    moveKey=false;
                if(textCt>3)
                {
                    speak=false;
                    moveKey=true;
                }
            }
            if(stageCt==5)
            {
                if(aurora.getX()>550)
                {
                    speak=true;
                }
                if(speak==true)
                    moveKey=false;
            }
            try
            {main.sleep(100);}
            catch(Exception e){}
        }
        while(section4);   
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
        if(stageCt==0)
        {
            bufferG.drawImage(stage[0],0,0,1300,700,this);
            bufferG.drawImage(auroraPic,aurora.getX(),340,60,140,this);
        }
        if(stageCt==1)
        {
            bufferG.drawImage(stage[1],0,0,1300,700,this);
            if(foundItem==false && gaveItem==false)
                bufferG.drawImage(door,890,280,250,250,this);
            bufferG.drawImage(auroraPic,aurora.getX(),aurora.getY(),60,140,this);
        }
        if(stageCt==2)
        {
            bufferG.drawImage(stage[2],0,0,1300,700,this);
            bufferG.drawImage(auroraPic,aurora.getX(),aurora.getY(),60,140,this);
        }
        if(stageCt==3)
        {
            bufferG.drawImage(stage[3],0,0,1300,700,this);
            if(foundItem==false)
            {
                bufferG.drawImage(boxPic,box.getX(),box.getY(),100,65,this);
            }
            else
            {
                bufferG.drawImage(hintText,100,20,1100,160,this);
                bufferG.drawString("You got mysterious box. But... it's broken",140,80);
                bufferG.drawString("Sounds like something's inside but it's a bad idea to peek...",140,120);
            }
            bufferG.drawImage(auroraPic,aurora.getX(),aurora.getY(),60,140,this);
        }
        if(stageCt==4)
        {
            bufferG.drawImage(stage[4],0,0,1300,700,this);
            bufferG.drawImage(auroraPic,aurora.getX(),aurora.getY(),60,140,this);
            if(speak==true)
            {
                bufferG.drawImage(text,100,510,1100,160,this);
                if(textCt==0)
                {
                    bufferG.drawString("Psst... Girlie, I need something...",140,550);
                    bufferG.drawString("I died losing a precious item of mine...",140,600);
                }
                if(textCt==1)
                {
                    bufferG.drawString("If you retrieve it for me,",140,550);
                    bufferG.drawString("I'll let you through the main door...",140,600);
                }
                if(foundItem==false)
                {
                    if(textCt==2)
                    {
                        bufferG.drawString("Please, I'm asking you.",140,550);
                    }
                    if(textCt==3)
                    {
                        bufferG.drawString("Find it and I'll open the way...",140,550);
                    }
                }
                if(foundItem==true)
                {
                    if(textCt==2)
                    {
                        bufferG.drawString("...Wait! That's mine! Give it here!",140,550);
                    }
                    if(textCt==3)
                    {
                        bufferG.drawString("Yes... a deal's a deal.",140,550);
                        bufferG.drawString("You can pass through now.",140,600);
                    }
                }
            }
        }
        if(stageCt==5)
        {
            bufferG.drawImage(stage[5],0,0,1300,700,this);
            bufferG.drawImage(auroraPic,aurora.getX(),aurora.getY(),60,140,this);
            if(speak==true)
            {
                bufferG.drawImage(enemyText,100,510,1100,160,this);
                if(textCt==5)
                {
                    bufferG.drawString("Heh heh heh, is the little girlie lost?",140,550);
                    bufferG.drawString("You can't possible think to get out of here right?",140,600);
                }
                if(textCt==6)
                {
                    bufferG.drawString("As Master of Nightmares I won't allow you to progress!",140,550);
                }
                if(textCt==7)
                {
                    bufferG.drawString("Meet your fate now!",140,550);
                }
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

    public void keyTyped(KeyEvent e){}

    public void keyPressed(KeyEvent e)
    {
        int code=e.getKeyCode();
        if(moveKey==false)
        {
            if(code==e.VK_SPACE)
            {
                textCt++;
                if(textCt>3)
                {    
                    speak=false;
                }
            }
        }
        else
        {        
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
        }
        repaint();
    }
}