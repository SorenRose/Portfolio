import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

/*An applet to make Zoro move left, right, jump, duck and stand still
 * Amanda Ramos April 21st, 2014
 */

public class ZoroAnimMovePic extends Applet implements Runnable, KeyListener
{   
    Thread main= new Thread(this);
    Image picArrays[]=new Image[4];
    Image picArrayr[]=new Image[8];
    Image picArrayd[]=new Image[3];
    Font font= new Font( "Rockwell", 1, 17);
    int runCt=0; 
    boolean jump=false, duck=false;
    AnimMovePic Zoro;
    Image buffer;
    Graphics bufferG;

    public void init()
    {
        resize(1300,700);
        this.addKeyListener(this);

        picArrays[0]=this.getImage(this.getCodeBase(),"stand 1.png");
        picArrays[1]=this.getImage(this.getCodeBase(),"stand 2.png");
        picArrays[2]=this.getImage(this.getCodeBase(),"stand 3.png");
        picArrays[3]=this.getImage(this.getCodeBase(),"stand 4.png");

        picArrayr[0]=this.getImage(this.getCodeBase(),"Zoro 1.png");
        picArrayr[1]=this.getImage(this.getCodeBase(),"Zoro 2.png");
        picArrayr[2]=this.getImage(this.getCodeBase(),"Zoro 3.png");
        picArrayr[3]=this.getImage(this.getCodeBase(),"Zoro 4.png");
        picArrayr[4]=this.getImage(this.getCodeBase(),"Zoro 5.png");
        picArrayr[5]=this.getImage(this.getCodeBase(),"Zoro 6.png");
        picArrayr[6]=this.getImage(this.getCodeBase(),"Zoro 7.png");
        picArrayr[7]=this.getImage(this.getCodeBase(),"Zoro 8.png");

        picArrayd[0]=this.getImage(this.getCodeBase(),"duck 1.png");
        picArrayd[1]=this.getImage(this.getCodeBase(),"duck 2.png");
        picArrayd[2]=this.getImage(this.getCodeBase(),"duck 3.png");
        //only use the second pic in this array!
        //all the pictures were taken from Google Images, search Roronoa Zoro Sprites
        
        
        Zoro=new AnimMovePic(picArrays,50,100,400,0,0);
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
            
            runCt++;
            if(runCt==8)
                runCt=0;

            if(jump)
            {
                Zoro.setYChange(Zoro.getYChange()+1);
                if(Zoro.getY()>=400)
                {
                    jump=false;
                    Zoro.setYChange(0);   
                    Zoro.setY(400);
                }
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
        bufferG.setFont(font);
        bufferG.setColor(Color. black);
        bufferG.drawString("To make Zoro move, press the Left or Right keys.",50,50); 
        bufferG.drawString("To jump, press Up, to duck, press Down",50,100);
        if(duck)
                bufferG.drawImage(picArrayd[1], Zoro.getX(),Zoro.getY(), 85,100,this);
        else if(Zoro.getXChange()>=0)
            bufferG.drawImage(Zoro.getImage(), Zoro.getX(),Zoro.getY(), 80,100,this);
        else if(Zoro.getXChange()<0)
            bufferG.drawImage(picArrayr[runCt], Zoro.getX()+80,Zoro.getY(), -80,100,this);

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
            Zoro.setXChange(0);
            Zoro.setImage(picArrays);
        }
        if(code==e.VK_LEFT)
        {
            Zoro.setXChange(0);
            Zoro.setImage(picArrays);
        }
        if(code==e.VK_DOWN)
        {
           duck=false;
        }
    }

    public void keyPressed(KeyEvent e)
    {
        int code=e.getKeyCode();
        if(code==e.VK_RIGHT && Zoro.getXChange()==0)
        {
            Zoro.setXChange(10);
            Zoro.setImage(picArrayr);
        }
        if(code==e.VK_LEFT)
        {
            Zoro.setXChange(-10);
        }
        if(code==e.VK_UP && !jump)
        {
            jump=true;
            Zoro.setYChange(-15);
            Zoro.setY(Zoro.getY()-1);
        }
        if(code==e.VK_DOWN)
        {
            duck=true;
        }
    }

    public void keyTyped(KeyEvent e){}
}