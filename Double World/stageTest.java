import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.applet.Applet;

/*Applet to work with the blank stage in Double World
 * Amanda Ramos, March 4th, 2014
 */

public class stageTest extends Applet implements Runnable
{
    Thread main= new Thread(this);
    Font font= new Font( "Chiller", 1, 40);
    Image stage;
    Image object1,object2,object3Pic;
    MovingPic object3;
    boolean forward=true, backward=false;
    int x1=100,x2=900;
    Image buffer;
    Graphics bufferG;

    public void init()
    {
        this.setLayout(null);
        resize(1300,700);

        stage=this.getImage(this.getCodeBase(),"stage test 14.png");
        object1=this.getImage(this.getCodeBase(),"object test 1.png");
        object2=this.getImage(this.getCodeBase(),"object test 2.png");
        
        object3=new MovingPic (object3Pic,10,100,380,0,0);
        object3Pic=this.getImage(this.getCodeBase(),"object test 3.png");
        object3.setAlive(true);
        object3.start();
        
        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();

        main.start();
    }

    public void run()
    {
        do
        {
            repaint();
            
            if(object3.getX()>450)
            {
                backward=true;
                forward=false;
            }
            if(object3.getX()<90)
            {
                forward=true;
                backward=false;
            }
            if(forward)
            {
                object3.setXChange(10);
            }
            if(backward)
            {
                object3.setXChange(-10);
            }

            try
            {main.sleep(100);}
            catch(Exception e){}
        }
        while(true);
    }

    public void paint(Graphics g)
    {
        bufferG.setColor(Color. black);
        bufferG.fillRect(0,0,10000,10000);
        bufferG.setFont(font);

        bufferG.drawImage(stage,0,0,1300,700,this);
        bufferG.drawImage(object1,x1,60,200,200,this);
        bufferG.drawImage(object2,x2,50,200,200,this);
        bufferG.drawImage(object3Pic,object3.getX(),object3.getY(),200,220,this);

        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

}