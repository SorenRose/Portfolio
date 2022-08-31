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
    Image logo;
    int TimeCt=0;
    AudioClip end; //taken from Youtube, by Hocus Pocus
    AudioClip theme;
    Image buffer;
    Graphics bufferG;

    public void init()
    {
        this.setLayout(null);
        resize(1300,700);

        //end=this.getAudioClip(this.getCodeBase(),"come little children.wav");
        //end.play();
        //originally gonna play this song but I think I'll let the other play :)

        theme=this.getAudioClip(this.getCodeBase(),"Blisful Nightmare.wav");
        theme.loop();
        
        logo=this.getImage(this.getCodeBase(),"company logo.png");

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
            try
            {main.sleep(500);}
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
        if(3<TimeCt&& TimeCt<20)
        {
            bufferG.drawString("Double World", 550, 300);
        }
        if(6<TimeCt&&TimeCt<23)
        {
            bufferG.drawString("Demo", 600, 340);
        }
        if(24<TimeCt&&TimeCt<41)
        {
            bufferG.drawString("Producer", 550, 300);
        }
        if(27<TimeCt&&TimeCt<44)
        {
            bufferG.drawString("Amanda Ramos", 600, 340);
        }
        if(45<TimeCt&&TimeCt<62)
        {
            bufferG.drawString("Art", 550, 300);
        }
        if(48<TimeCt&&TimeCt<65)
        {
            bufferG.drawString("Amanda Ramos", 600, 340);
        }
        if(51<TimeCt&&TimeCt<68)
        {
            bufferG.drawString("Alyssa Broadwater",550,380); //dd's friend
        }
        if(54<TimeCt&&TimeCt<71)
        {
            bufferG.drawString("Kayla Armstrong",600,420); //dd's friend
        }
        if(72<TimeCt&&TimeCt<89)
        {
            bufferG.drawString("Kaitlyn Kresge",550,300);
        }
        if(90<TimeCt&&TimeCt<107)
        {
            bufferG.drawString("Music", 550, 300);
        }
        if(93<TimeCt&&TimeCt<110)
        {
            bufferG.drawString("Blissful Nightmare-Cynthia Melillo", 600, 340);
        }
        if(96<TimeCt&&TimeCt<113)
        {
            bufferG.drawString("Emily Reder",550, 380);
        }
        if(99<TimeCt&&TimeCt<116)
        {
            bufferG.drawString("Come Little Children-Hocus Pocus",600, 420);
        }
        if(117<TimeCt&&TimeCt<134)
        {
            bufferG.drawString("Sound Effects",550,300);
        }
        if(120<TimeCt&&TimeCt<137)
        {
            bufferG.drawString("Sadie Ramos (My dog)",600,340);
        }
        if(138<TimeCt&&TimeCt<155)
        {
            bufferG.drawString("Published by",550,300);
        }
        if(141<TimeCt&&TimeCt<158)
        {
            bufferG.drawString("Awesomee Studios", 600,340);
        }
        if(159<TimeCt&&TimeCt<176)
        {
            bufferG.drawString("Special Thanks To",550,300);
        }
        if(162<TimeCt&&TimeCt<179)
        {
            bufferG.drawString("Andrea Ramos", 600,340);
        }
        if(165<TimeCt&&TimeCt<182)
        {
            bufferG.drawString("Brianna Williams",550,380);
        }
        if(168<TimeCt&&TimeCt<185)
        {
            bufferG.drawString("Alyssa Broadwater",600, 420);
        }
        if(186<TimeCt&&TimeCt<203)
        {
            bufferG.drawString("Kayla Armstrong",550,300);
        }
        if(189<TimeCt&&TimeCt<206)
        {
            bufferG.drawString("Oscar Ramos",600,340);
        }
        if(192<TimeCt&&TimeCt<209)
        {
            bufferG.drawString("Mr. Luciano",550,380);
        }
        if(195<TimeCt&&TimeCt<212)
        {
            bufferG.drawString("Cynthia Melillo",600, 420);
        }
        if(213<TimeCt&&TimeCt<230)
        {
            bufferG.drawString("Xander Newport",550, 300);
        }
        if(216<TimeCt&&TimeCt<233)
        {
            bufferG.drawString("Marisol Ramos",600, 340);
        }
         if(219<TimeCt&&TimeCt<236)
        {
            bufferG.drawString("Sadie Ramos",550, 380);
        }
         if(222<TimeCt&&TimeCt<239)
        {
            bufferG.drawString("Kaitlyn Kresge",600, 420);
        }
        if(240<TimeCt&&TimeCt<257)
        {
            bufferG.drawString("Thanks for playing!",550,300);
        }
        if(243<TimeCt&&TimeCt<260)
        {
            bufferG.drawString("Hope you enjoyed!",600, 340);
        }
        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }
}