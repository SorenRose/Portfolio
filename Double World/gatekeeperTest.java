import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.applet.Applet;
import java.applet.AppletStub;
/*Applet to work with the a full stage's mechanics in my game
 * Amanda Ramos, April 7th, 2014
 */

public class gatekeeperTest extends Applet implements Runnable, KeyListener, MouseListener, AppletStub
{
    Thread main= new Thread(this);
    Font font= new Font( "Chiller", 1, 40);
    Image stage;
    AudioClip theme;
    boolean speak=false, answerQuestion=false, moveKey=true, wrongAnswer=false;
    //just make him say it's incorrect instead of losing a life, it dosnt work at the momment
    int mouseCt;
    MovingPic aurora;
    int textCt=0;
    Image buffer, auroraPic, enemyText, auroraText, gatekeeper;
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
        this.addMouseListener(this);
        resize(1300,700);

        theme=this.getAudioClip(this.getCodeBase(),"Blisful Nightmare.wav");
        theme.loop();

        stage=this.getImage(this.getCodeBase(),"stage test 18.png");
        enemyText=this.getImage(this.getCodeBase(),"gatekeeper Text.png");
        auroraText=this.getImage(this.getCodeBase(),"aurora Text.png");
        gatekeeper=this.getImage(this.getCodeBase(),"gatekeeper.png");

        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();

        aurora=new MovingPic (auroraPic,0,590,640,0,0);
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
            if(aurora.getX()<430 && aurora.getY()<350 && textCt<9)
            {
                speak=true;
            }
            if(speak==true || answerQuestion==true)
                moveKey=false;
            if(textCt>12)
            {
                speak=false;
                moveKey=true;
            }
            try
            {main.sleep(100);}
            catch(Exception e){}
        }
        while(aurora.getX()>0);
        try
        {
            Class applet2=Class.forName("nextSectionTest");
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

        bufferG.drawImage(stage,0,0,1300,700,this);
        bufferG.drawImage(auroraPic,aurora.getX(),aurora.getY(),60,140,this);
        bufferG.drawImage(gatekeeper,180,250,160,160,this);
        if(speak)
        {
            bufferG.setColor(Color. black);
            //easy question, this is a demo after all
            if(textCt==0)
            {
                bufferG.drawImage(enemyText,100,510,1100,160,this);
                bufferG.drawString("What do you want girlie?",140,550);
            }
            if(textCt==1)
            {
                bufferG.drawImage(auroraText,100,510,1100,160,this);
                bufferG.drawString("Let me through.",140,550);
            }
            if(textCt==2)
            {
                bufferG.drawImage(enemyText,100,510,1100,160,this);
                bufferG.drawString("No, no one is allowed past here.",140,550);
            }
            if(textCt==3)
            {
                bufferG.drawImage(auroraText,100,510,1100,160,this);
                bufferG.drawString("Who commanded it?",140,550);
            }
            if(textCt==4)
            {
                bufferG.drawImage(enemyText,100,510,1100,160,this);
                bufferG.drawString("Lord Nightma-Ra of course.",140,550);
            }
            if(textCt==5)
            {
                bufferG.drawImage(auroraText,100,510,1100,160,this);
                bufferG.drawString("(Of course he did. I got to pass but how?)",140,550);
            }
            if(textCt==6)
            {
                bufferG.drawImage(enemyText,100,510,1100,160,this);
                bufferG.drawString("However... If you truly want to pass, I'll let you...",140,550);
                bufferG.drawString("If you answer my question!",140,590);
            }
            if(textCt==7)
            {
                bufferG.drawImage(auroraText,100,510,1100,160,this);
                bufferG.drawString("Fair enough, ask your question!",140,550);
            }
            if(textCt==8)
            {
                bufferG.drawImage(enemyText,100,510,1100,160,this);
                bufferG.drawString("What is the capital of the United States?",140,550);
            }
            if(textCt==10)
            {
                bufferG.drawImage(enemyText,100,510,1100,160,this);
                bufferG.drawString("Heh, you passed my test.",140,550);
            }
            if(textCt==11)
            {
                bufferG.drawImage(auroraText,100,510,1100,160,this);
                bufferG.drawString("Wasn't hard. (Honestly, what an easy question)",140,550);
            }
            if(textCt==12)
            {
                bufferG.drawImage(enemyText,100,510,1100,160,this);
                bufferG.drawString("Heh, you've made your choice. I wish you luck.",140,550);
                bufferG.drawString("From here on out, this demented castle gets worse.",140,600);
            }
        }
        if(answerQuestion==true)
        {            
            bufferG.setColor(Color. black);
            bufferG.drawString("Washington DC",50,280);
            bufferG.drawRect(50,240,210,50);
            bufferG.drawString("Boston",900,300);
            bufferG.drawRect(900,260,100,50);
            bufferG.drawString("Philidelphia",200,100);
            bufferG.drawRect(200,60,160,50);
            bufferG.drawString("New York City",500,350);
            bufferG.drawRect(500,310,200,50);
            bufferG.drawString(""+mouseCt,10,30);
            if(wrongAnswer==true)
            {            
                bufferG.drawImage(enemyText,100,510,1100,160,this);
                bufferG.drawString("Incorrect.",140,550);
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
                if(textCt>8 && textCt<10)
                {    
                    speak=false;
                    answerQuestion=true;
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

    public void mouseClicked(MouseEvent e)
    {
        int mouseX=e.getX();
        int mouseY=e.getY();
        if(mouseX>50 && mouseX<260 && mouseY>240 && mouseY<290)
        {   
            answerQuestion=false;
            speak=true;
            textCt=10;
            wrongAnswer=false;
        }
        if(mouseX>900 && mouseX<1000 && mouseY>260 && mouseY<310)
        {   
            wrongAnswer=true;
        }
        if(mouseX>200 && mouseX<360 && mouseY>60 && mouseY<110)
        {   
            wrongAnswer=true;
        }
        if(mouseX>500 && mouseX<700 && mouseY>310 && mouseY<360)
        {   
            wrongAnswer=true;
        }
        repaint();
    }

    public void mouseExited(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}
}