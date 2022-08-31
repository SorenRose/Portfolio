import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.applet.Applet;
import java.util.Random;

/*An applet to make a game with Nyan Cat
 * Amanda Ramos April 28th, 2014
 */

public class catGame extends Applet implements Runnable, KeyListener
{   
    Thread main= new Thread(this);
    Random rand= new Random();
    Image picArrayCat[]=new Image[5];
    MovingPic blocks[]= new MovingPic[5];
    Font font= new Font( "Lucida Bright", 1, 17);
    int runCt=0, worldCt=0, jumpCt=0; 
    boolean jump=false, duck=false, worlds=false, onBlock=false;
    AnimMovePic cat;
    AudioClip theme; //taken from Youtube, Nyan Cat (original)
    Image buffer, blockPic, stage;
    Graphics bufferG;

    public void init()
    {
        resize(1300,700);
        this.addKeyListener(this);

        theme=this.getAudioClip(this.getCodeBase(),"nyan cat.wav");
        theme.loop();

        picArrayCat[0]=this.getImage(this.getCodeBase(),"cat 1.png");
        picArrayCat[1]=this.getImage(this.getCodeBase(),"cat 2.png");
        picArrayCat[2]=this.getImage(this.getCodeBase(),"cat 3.png");
        picArrayCat[3]=this.getImage(this.getCodeBase(),"cat 4.png");
        picArrayCat[4]=this.getImage(this.getCodeBase(),"cat 5.png");
        //all the pictures were taken from Google Images, search Roronoa cat Sprites
        stage=this.getImage(this.getCodeBase(),"nyan cat.jpg");
        //bing images, search nyan cat background

        for(int i=0; i<5;i++)
        {
            blockPic=this.getImage(this.getCodeBase(),"blockPic.png");
            blocks[i]=new MovingPic (blockPic,50,200+350*i,600-50*i,-5,0);

            blocks[i].setAlive(true);
            blocks[i].start();
        }
        cat=new AnimMovePic(picArrayCat,50,100,blocks[0].getY()-51,0,0);
        cat.setAlive(true);
        cat.start();
        main.start();

        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();
    }


    public void run()
    {
        while(cat.getY()<=750)
        {
            repaint();
            Rectangle blocksRect[]=new Rectangle[5];
            Rectangle catRect=new Rectangle(cat.getX(),cat.getY()+50, 100,20);

            onBlock=false;

            for(int i=0;i<5;i++)
            {
                blocksRect[i]=new Rectangle(blocks[i].getX(),blocks[i].getY(),150,80 );
                if(catRect.intersects(blocksRect[i]))
                {
                    jump=false;
                    onBlock=true;
                    jumpCt=0;
                    cat.setYChange(0);
                    cat.setY(blocks[i].getY()-51);
                }

                if(blocks[i].getX()<-100)
                {
                    blocks[i].setX(1600);
                    blocks[i].setY(rand.nextInt(650));
                }
                
                if(blocks[i].getY()<400)
                     blocks[i].setY(rand.nextInt(650));
            }

            if(!onBlock && cat.getY()<700 && !jump)
                cat.setYChange(11);

            runCt++;
            if(runCt==5)
                runCt=0;

            if(jump)
            {
                cat.setYChange(cat.getYChange()+1);
                if(cat.getY()>=400)
                {
                    jump=false;
                    cat.setYChange(0);   
                    cat.setY(400);
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
        bufferG.drawImage(stage,0,0,1300,700,this);
        bufferG.setFont(font);
        bufferG.setColor(Color. black);

        bufferG.drawString("Welcome to Nyan Cat!",50,50);
        bufferG.drawString("To move, click left or right.",50,80);
        bufferG.drawString("To jump, press up",50,110);
        bufferG.drawString("If you fall, it's GAME OVER, not hard right?", 50,140);

        bufferG.drawImage(cat.getImage(), cat.getX(), cat.getY(), 100,50,this);
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
            cat.setXChange(0);
        }
        if(code==e.VK_LEFT)
        {
            cat.setXChange(0);
        }
    }

    public void keyPressed(KeyEvent e)
    {
        int code=e.getKeyCode();
        if(code==e.VK_RIGHT && cat.getXChange()==0)
        {
            cat.setXChange(10);
            cat.setImage(picArrayCat);
        }
        if(code==e.VK_LEFT)
        {
            cat.setXChange(-10);
        }
        if(jumpCt<2)
        {
            if(code==e.VK_UP && !jump)
            {
                jump=true;
                jumpCt++;
                cat.setYChange(-20);
                cat.setY(cat.getY()-5);
            }
        }
    }

    public void keyTyped(KeyEvent e){}
}