import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

/*An applet to test MovingPic
 * Amanda Ramos April 7th, 2014
 */

public class AnimPicTest extends Applet implements Runnable
{   
    Thread main= new Thread(this);
    Image picArray[]=new Image[8];
    Image background;
    AnimMovePic Zoro;
    Image buffer;
    Graphics bufferG;

    public void init()
    {
        resize(1300,700);

        picArray[0]=this.getImage(this.getCodeBase(),"Zoro 1.png");
        picArray[1]=this.getImage(this.getCodeBase(),"Zoro 2.png");
        picArray[2]=this.getImage(this.getCodeBase(),"Zoro 3.png");
        picArray[3]=this.getImage(this.getCodeBase(),"Zoro 4.png");
        picArray[4]=this.getImage(this.getCodeBase(),"Zoro 5.png");
        picArray[5]=this.getImage(this.getCodeBase(),"Zoro 6.png");
        picArray[6]=this.getImage(this.getCodeBase(),"Zoro 7.png");
        picArray[7]=this.getImage(this.getCodeBase(),"Zoro 8.png");
        
        Zoro=new AnimMovePic(picArray,50,100,400,5,0);
        Zoro.setAlive(true);
        Zoro.start();
        main.start();

        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();
    }

    public void run()
    {
        while(true)
        {
            repaint();
            
            if(Zoro.getX()>=1140)
            {
                Zoro.setXChange(0);
                Zoro.setYChange(-10);
            }
            if(Zoro.getY()<=20)
            {
                Zoro.setYChange(0);
                Zoro.setXChange(-10);
            }
            if(Zoro.getX()<=10)
            {
                Zoro.setXChange(0);
                Zoro.setYChange(10);
            }
            if(Zoro.getY()>=600)
            {
                Zoro.setYChange(0);
                Zoro.setXChange(10);
            }
            
            try
            {main.sleep(50);}
            catch(Exception e){}
        }
    }

    public void paint (Graphics g)
    {
        bufferG.setColor(Color. white);
        bufferG.fillRect(0,0,10000,10000);
        bufferG.drawImage(Zoro.getImage(), Zoro.getX(),Zoro.getY(), 80,100,this);
        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

}