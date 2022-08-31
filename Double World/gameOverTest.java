import java.awt.*;
import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.applet.AppletStub;
/*Applet to make the ganme over in my game
 * Amanda Ramos, June 3rd, 2014
 */

public class gameOverTest extends Applet implements Runnable, AppletStub
{
    Thread main= new Thread(this);
    Font font= new Font( "Chiller", 1, 280);
    Color myBrown=new Color( 110, 70, 60 );
    Image gameover,stage;
    int TimeCt=0;
    Image buffer;
    Applet appletToLoad= new Applet();
    Graphics bufferG;
    
    public void appletResize(int width, int length)
    {
        resize( 1300, 700 );  
    }
    
    public void init()
    {
        this.setLayout(null);
        resize(1300,700);

        gameover=this.getImage(this.getCodeBase(),"red screen test.png");
        stage=this.getImage(this.getCodeBase(),"stage test 19.png");
        
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
            if(TimeCt>10)
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
            try
            {main.sleep(550);}
            catch(Exception e){}
        }
        while(true);
    }

    public void paint(Graphics g)
    {
        bufferG.setColor(Color. white);
        bufferG.fillRect(0,0,10000,10000);
        bufferG.drawImage(stage,0,0,1300,700,this);
        bufferG.drawImage(gameover,0,0,1300,700,this);
        bufferG.drawString("GAME OVER",10,400);
        bufferG.setFont(font);
        bufferG.setColor(Color. red);
        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }
}