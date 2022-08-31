import java.awt.*;
import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.applet.AppletStub;
import java.applet.*;
/*Applet to make a cutscene of Double World
 * Amanda Ramos, May 30th, 2014
 */

public class Opening extends Applet implements Runnable, AppletStub
{
    Thread main= new Thread(this);
    Font font= new Font( "Chiller", 1, 40);
    Color myBrown=new Color( 110, 70, 60 );
    Image background1;
    AudioClip theme;
    Applet appletToLoad= new Applet();
    int TimeCt=0;
    Image buffer,logo,intro;
    Graphics bufferG;

    public void appletResize(int width, int length)
    {
        resize( 1300, 700 );  
    }
    
    public void init()
    {
        this.setLayout(null);
        resize(1300,700);

        theme=this.getAudioClip(this.getCodeBase(),"Blisful Nightmare.wav");
        theme.play();
        
        background1=this.getImage(this.getCodeBase(),"throne test 1.png");
        logo=this.getImage(this.getCodeBase(),"company logo.png");
        intro=this.getImage(this.getCodeBase(),"intro.png");
        //intro is drawn by Kaitlyn Kresge
        
        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();

        main.start();
    }

    public void run()
    {
        do
        {
            repaint();
            TimeCt++;
            
            if(TimeCt>114)
            {
                try
                    {
                        Class applet2=Class.forName("DemoPlay");
                        appletToLoad=(Applet)applet2.newInstance();
                        appletToLoad.setStub(this);
                        add(appletToLoad);
                        appletToLoad.init();
                        appletToLoad.start();
                    }
                    catch (Exception p){}
            }
            try
            {main.sleep(560);}//slow this down so the people can read the intro
            catch(Exception e){}
        }
        while(TimeCt<115);
    }

    public void paint(Graphics g)
    {
        bufferG.setColor(Color. black);
        bufferG.fillRect(0,0,10000,10000);
        bufferG.drawImage(background1,0,0,1300,700,this);
        bufferG.setFont(font);
        bufferG.setColor(Color. red);
        if(TimeCt>3 && TimeCt<19)
        {
            bufferG.drawString("There is an enchanted dreamland for children.",250, 280);
            bufferG.drawString("Here is where their dreams and imaginary creations live.",250, 320);
            bufferG.drawString("It was a peaceful and enjoyable place until… ",250, 360);
        }
        if(TimeCt>20 && TimeCt<50)
        {
            bufferG.drawString("Something mysterious appeared that changed everything.",250,280);
            bufferG.drawString("Nightmares started to flood the dreamland and",250,320);
            bufferG.drawString("the children received nothing but fear.",250,360);
            bufferG.drawString("And now Nightma-Ra and his dark magic have taken",250,400);
            bufferG.drawString("over the land and just when everything seemed hopeless…",250,440);
        }
        if(TimeCt>51 && TimeCt<64)
        {
            bufferG.drawString("Aurora happens, the chosen one. ",250,280);
        }
        if(TimeCt>65 && TimeCt<79)
        {
            bufferG.drawString("Today, we look at one little part of this dreamland.",250,280);
            bufferG.drawString("I will show you the Castle of Disorder...",250,320);
        }
        if(TimeCt>80 && TimeCt<94)
        {
            bufferG.drawImage(logo,100,50,1050,500,this);
            bufferG.drawString("Presents:",550,600);
        }
        if(TimeCt>95 && TimeCt<114)
        {
            bufferG.drawImage(intro,100,10,1050,630,this);
            bufferG.drawString("Nightma-Ra, Aurora, and Pinky in...",500,50);
            bufferG.drawString("Land of Dreams?",550,90);
        }
        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }
}