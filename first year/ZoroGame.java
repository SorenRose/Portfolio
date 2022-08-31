import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

/*An applet to make a game with Zoro  doing stuff
 * Amanda Ramos April 28th, 2014
 */

public class ZoroGame extends Applet implements Runnable, KeyListener
{   
    Thread main= new Thread(this);
    Image picArrayr[]=new Image[8];
    MovingPic[] blocks= new MovingPic[5];
    Font font= new Font( "Rockwell", 1, 17);
    int runCt=0, worldCt=0; 
    boolean jump=false, duck=false, worlds=false, onBlock=false;
    AnimMovePic Zoro;
    Image buffer, blockPic;
    Graphics bufferG;

    public void init()
    {
        resize(1300,700);
        this.addKeyListener(this);

        picArrayr[0]=this.getImage(this.getCodeBase(),"Zoro 1.png");
        picArrayr[1]=this.getImage(this.getCodeBase(),"Zoro 2.png");
        picArrayr[2]=this.getImage(this.getCodeBase(),"Zoro 3.png");
        picArrayr[3]=this.getImage(this.getCodeBase(),"Zoro 4.png");
        picArrayr[4]=this.getImage(this.getCodeBase(),"Zoro 5.png");
        picArrayr[5]=this.getImage(this.getCodeBase(),"Zoro 6.png");
        picArrayr[6]=this.getImage(this.getCodeBase(),"Zoro 7.png");
        picArrayr[7]=this.getImage(this.getCodeBase(),"Zoro 8.png");
        //all the pictures were taken from Google Images, search Roronoa Zoro Sprites

        for(int i=0; i<5;i++)
        {
            blockPic=this.getImage(this.getCodeBase(),"blockPic.png");
            blocks[i]=new MovingPic (blockPic,50,200+300*i,600-50*i,-5,0);
            blocks[i].setAlive(true);
            blocks[i].start();
        }

        Zoro=new AnimMovePic(picArrayr,50,100,blocks[0].getY()-88,0,0);
        Zoro.setAlive(true);
        Zoro.start();
        main.start();

        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();
    }

    public void run()
    {
        while(Zoro.getY()<=700)
        {
            repaint();
            Rectangle blocksRect[]=new Rectangle[10];
            Rectangle zoroFeetRect=new Rectangle(Zoro.getX(),Zoro.getY()+80, 80,20);
            
            onBlock=false;
            
            for(int i=0;i<5;i++)
            {
                blocksRect[i]=new Rectangle(blocks[i].getX(),blocks[i].getY(),100,80 );
                if(zoroFeetRect.intersects(blocksRect[i]))
                {
                    jump=false;
                    onBlock=true;
                    Zoro.setYChange(0);
                    Zoro.setY(blocks[i].getY()-88);
                }
            }

            if(!onBlock && /*Zoro.getY()<700*/ !jump)
            Zoro.setYChange(11);
            
            
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

        bufferG.drawImage(Zoro.getImage(), Zoro.getX(), Zoro.getY(), 80,100,this);
        for(int i=0 ;i<5; i++)
            bufferG.drawImage(blocks[i].getImage(),blocks[i].getX(),blocks[i].getY(),this);

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
        }
        if(code==e.VK_LEFT)
        {
            Zoro.setXChange(0);
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
    }

    public void keyTyped(KeyEvent e){}
}