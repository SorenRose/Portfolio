import java.awt.*;
import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.applet.AppletStub;
import java.applet.*;
/*Applet to make a intro of Circus Despair, taken from Double world project and fixed
 * Amanda Ramos, May 13th, 2015
 */

public class Opening extends Applet implements Runnable, AppletStub
{
    Thread main= new Thread(this);
    Font font= new Font( "Perpetua", 1, 25);
    Image background1;
    AudioClip theme;
    Applet appletToLoad= new Applet();
    int TimeCt=0;
    Image buffer,logo,intro,presents,king,overlook,people,forbidden;
    Graphics bufferG;

    public void appletResize(int width, int length)
    {
        resize( 1300, 700 );  
    }
    
    public void init()
    {
        this.setLayout(null);
        resize(1300,700);

        theme=this.getAudioClip(this.getCodeBase(),"Prelude.wav");
        theme.play();
        
        background1=this.getImage(this.getCodeBase(),"throne test 1.png");
        logo=this.getImage(this.getCodeBase(),"logo.png");
        presents=this.getImage(this.getCodeBase(),"presents.png");
        king=this.getImage(this.getCodeBase(),"scene king 2.png");
        overlook=this.getImage(this.getCodeBase(),"zombra scene 1.png");
        intro=this.getImage(this.getCodeBase(),"title.png");
        people=this.getImage(this.getCodeBase(),"types.png");
        forbidden=this.getImage(this.getCodeBase(),"forbidden2.png");
        
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
            if(TimeCt>145)
            theme.stop();
            if(TimeCt>156)
            {                
                try
                    {
                        Class applet2=Class.forName("tent");
                        appletToLoad=(Applet)applet2.newInstance();
                        appletToLoad.setStub(this);
                        add(appletToLoad);
                        appletToLoad.init();
                        appletToLoad.start();
                    }
                    catch (Exception p){}
            }
            try
            {main.sleep(570);}//slow this down so the people can read the intro
            catch(Exception e){}
        }
        while(TimeCt<157);
    }

    public void paint(Graphics g)
    {
        bufferG.setColor(Color. black);
        bufferG.fillRect(0,0,10000,10000);
        bufferG.drawImage(background1,0,0,1300,700,this);
        bufferG.setFont(font);
        bufferG.setColor(Color. white);
        if(TimeCt>2 && TimeCt<24)
        {
            bufferG.drawImage(people,0,0,1300,700,this);
        }
        if(TimeCt>2 && TimeCt<13)
        {
            bufferG.drawString("In a land where species of humans exist...",350, 550);
        }
        if(TimeCt>13 && TimeCt<24)
        {
            bufferG.drawString("Only one kind was the most feared...",350,550);
        }
        if(TimeCt>24 && TimeCt<33)
        {
            bufferG.drawImage(forbidden,0,0,1300,700,this);
        }
        if(TimeCt>24 && TimeCt<33)
        {
            bufferG.drawString("They are called the Forbidden",350,550);
        }
        if(TimeCt>33 && TimeCt<45)
        {
            bufferG.drawImage(king,0,0,1300,700,this);
            bufferG.drawString("The King of this land was betrayed by his advisor.",350,550);
            bufferG.drawString("Who turned out was a Forbidden.",350,580);
        }
        if(TimeCt>45 && TimeCt<68)
        {
            bufferG.drawImage(overlook,0,0,1300,700,this);
        }
        if(TimeCt>45 && TimeCt<57)
        {
            bufferG.drawString("Under his magic, the advisor, Zombra, and the Forbidden",350,550);
            bufferG.drawString("took over the land and it's citizens.",350,580);
        }
        if(TimeCt>57 && TimeCt<68)
        {
            bufferG.drawString("Now a place of fear, no one dared oppose him...",350,550);
        }
        if(TimeCt>68 && TimeCt<79)
        {
            bufferG.drawString("However, there is a small ray of an unknown hope.",350,280);
        }
        if(TimeCt>79 && TimeCt<90)
        {
            bufferG.drawString("Nottina, Pannino, beast Tigressa, along with a Forbidden",350,280);
            bufferG.drawString("Mistera, teamed up to take down the tyrant and restore the peace.",350,310);
        }
        if(TimeCt>90 && TimeCt<101)
        {
            bufferG.drawString("At day, these children hide from the advisor as",350,280);
            bufferG.drawString("an ordinary circus troop...",350,310);
        }
        if(TimeCt>101 && TimeCt<112)
        {
            bufferG.drawString("At night, they go out to find a way to take down ",350,280);
            bufferG.drawString("and slowly liberate their land.",350,310);
        }
        if(TimeCt>112 && TimeCt<123)
        {
            bufferG.drawString("With each piece to their past's puzzle, they get closer",250,280);
            bufferG.drawString("to defeating him.",250,310);
        }
        if(TimeCt>123 && TimeCt<134)
        {
            bufferG.drawImage(logo,0,0,1300,700,this);
        }
        if(TimeCt>132 && TimeCt<145)
        {
            bufferG.drawImage(presents,0,0,1300,700,this);
        }
        if(TimeCt>143 && TimeCt<156)
        {
            bufferG.drawImage(intro,0,0,1300,700,this);
        }
        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }
}