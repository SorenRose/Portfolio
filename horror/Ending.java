import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.applet.Applet;

/*Applet to make the ending of Double World
 * Amanda Ramos, March 1st, 2014
 */

public class Ending extends Applet implements Runnable
{
    Thread main= new Thread(this);
    Font font= new Font( "Chiller", 1, 40);
    Color myBrown=new Color( 110, 70, 60 );
    Image logo,knife,nottina,tigressa,pannino,mistero;
    int TimeCt=0;
    int y=-5;
    AudioClip end; //taken from Youtube, by Hocus Pocus
    AudioClip theme;
    Image buffer;
    Graphics bufferG;

    public void init()
    {
        this.setLayout(null);
        resize(1300,700);

        theme=this.getAudioClip(this.getCodeBase(),"Old Puppet.wav");
        theme.loop();

        knife=this.getImage(this.getCodeBase(),"knife.png");
        nottina=this.getImage(this.getCodeBase(),"Nottina transparent.png");
        tigressa=this.getImage(this.getCodeBase(),"tigressa.png");
        pannino=this.getImage(this.getCodeBase(),"pannino.png");
        mistero=this.getImage(this.getCodeBase(),"mistero.png");

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
            y-=2;
            try
            {main.sleep(230);}
            catch(Exception e){}
        }
        while(true);
    }

    public void paint(Graphics g)
    {
        bufferG.setColor(Color. black);
        bufferG.fillRect(0,0,10000,10000);
        bufferG.setFont(font);
        bufferG.setColor(Color. red);
        if(3<TimeCt&& TimeCt<19)
        {
            bufferG.drawString("Circus Despair", 550, 300);
        }
        if(8<TimeCt&&TimeCt<24)
        {
            bufferG.drawString("Demo", 600, 340);
        }
        if(13<TimeCt)
        {
            bufferG.drawImage(knife, 130, 810+y,200,800,this);

            bufferG.drawImage(nottina,950,1000+y,200,460,this);
            
            bufferG.drawImage(pannino, 1000,1500+y,250,600,this);
            
            bufferG.drawImage(tigressa, 130,1750+y,250,450,this);
            
            bufferG.drawImage(mistero, 1000, 2200+y,200,850,this);
        }
        if(13<TimeCt)
        {
            bufferG.drawString("Producer", 550, 800+y);

            bufferG.drawString("Amanda Ramos", 600, 870+y);

            bufferG.drawString("Art", 550, 940+y);

            bufferG.drawString("Amanda Ramos", 600, 1010+y);

            bufferG.drawString("Music", 550, 1080+y);

            bufferG.drawString("All music rights belong to Ib", 600, 1150+y);
            
            bufferG.drawString("Voice Actors",550,1220+y);

            bufferG.drawString("Andrea Ramos",600,1290+y);

            bufferG.drawString("Chloe Marzzacco",600,1360+y);

            bufferG.drawString("Matt Kaczorowski",600,1430+y);

            bufferG.drawString("Amanda Ramos",600,1500+y);

            bufferG.drawString("Published by",550,1570+y);
            
            bufferG.drawString("Awesomee Studios", 600,1640+y);
            
            bufferG.drawString("Special Thanks To",550,1710+y);
            
            bufferG.drawString("Andrea Ramos", 600,1780+y);
            
            bufferG.drawString("Oscar Ramos",600,1850+y);
            
            bufferG.drawString("Mr. Luciano",600,1920+y);
            
            bufferG.drawString("Chloe Marzzacco",600,1990+y);
            
            bufferG.drawString("Erika White",600,2060+y);
            
            bufferG.drawString("Marisol Ramos",600, 2130+y);
            
            bufferG.drawString("Sadie Ramos",600, 2200+y);
            
            bufferG.drawString("Matt Kaczorowski",600,2270+y);
        
            bufferG.drawString("Thanks for playing!",550,2400+y);
        
            bufferG.drawString("Hope you enjoyed!",600, 2470+y);
        }
        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

}