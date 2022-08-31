import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.applet.Applet;

/*An applet to make a game of Frogger
 * Amanda Ramos May 5th, 2014
 */

public class Frogger extends Applet implements Runnable, KeyListener
{   
    Thread main= new Thread(this);
    Font font= new Font( "Lucida Bright", 1, 20);
    Image picArrayFrogUp[]=new Image[4];
    Image picArrayFrogDown[]=new Image[2];
    MovingPic picArrayCar1[]=new MovingPic[4];
    Image Car1[]=new Image[4];
    MovingPic picArrayCar2[]=new MovingPic[4];
    Image Car2[]=new Image[4];
    MovingPic picArrayCar3[]=new MovingPic[4];
    Image Car3[]=new Image[4];
    MovingPic picArrayCar4[]=new MovingPic[4];
    Image Car4[]=new Image[4];
    AnimMovePic frog; 
    int pageCt=0,lifeCt=3, stagesClearedCt=0;
    Image buffer,stage,standUp;
    Graphics bufferG;

    public void init()
    {
        resize(1300,700);
        this.addKeyListener(this);

        standUp=this.getImage(this.getCodeBase(),"frog 1.png");
        picArrayFrogUp[0]=this.getImage(this.getCodeBase(),"frog 1.png");
        picArrayFrogUp[1]=this.getImage(this.getCodeBase(),"frog 2.png");
        picArrayFrogUp[2]=this.getImage(this.getCodeBase(),"frog 3.png");
        picArrayFrogUp[3]=this.getImage(this.getCodeBase(),"frog 4.png");
        picArrayFrogDown[0]=this.getImage(this.getCodeBase(),"frog 5.png");
        picArrayFrogDown[1]=this.getImage(this.getCodeBase(),"frog 6.png");

        frog=new AnimMovePic(picArrayFrogUp,50,500,650,0,0);
        frog.setAlive(true);
        frog.start();
        //all the pictures were taken from Shy Guy Kingdom, search "Frog", first set of pics

        stage=this.getImage(this.getCodeBase(),"frog stage.png");
        //made this on my own, in Paint

        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();
    }

    public void run()
    {
        while(pageCt==1)
        {
            repaint();

            Rectangle cars1Rect[]=new Rectangle[4];
            Rectangle cars2Rect[]=new Rectangle[4];
            Rectangle cars3Rect[]=new Rectangle[4];
            Rectangle cars4Rect[]=new Rectangle[4];
            Rectangle frogRect=new Rectangle(frog.getX(),frog.getY(), 25,35);

            for(int i=0;i<4;i++)
            {
                cars1Rect[i]=new Rectangle(picArrayCar1[i].getX(),picArrayCar1[i].getY()+50,110,40 );
                if(frogRect.intersects(cars1Rect[i]))
                {
                    lifeCt--;
                    frog.setY(650);
                }
                if(picArrayCar1[i].getX()>1300)
                    picArrayCar1[i].setX(-440);
            }
            for(int p=0;p<4;p++)
            {
                cars2Rect[p]=new Rectangle(picArrayCar2[p].getX()-120,picArrayCar2[p].getY()+50,110,40 );
                if(frogRect.intersects(cars2Rect[p]))
                {
                    lifeCt--;
                    frog.setY(650);
                }
                if(picArrayCar2[p].getX()<-100)
                    picArrayCar2[p].setX(1740);
            } 
            for(int r=0;r<4;r++)
            {
                cars3Rect[r]=new Rectangle(picArrayCar3[r].getX(),picArrayCar3[r].getY()+50,110,40 );
                if(frogRect.intersects(cars3Rect[r]))
                {
                    lifeCt--;
                    frog.setY(350);
                }
                if(picArrayCar3[r].getX()>1300)
                    picArrayCar3[r].setX(-470);
            }     
            for(int c=0;c<4;c++)
            {
                cars4Rect[c]=new Rectangle(picArrayCar4[c].getX()-120,picArrayCar4[c].getY()+50,110,40 );
                if(frogRect.intersects(cars4Rect[c]))
                {
                    lifeCt--;
                    frog.setY(350);
                }
                if(picArrayCar4[c].getX()<-100)
                    picArrayCar4[c].setX(1820);
            } 
            if(frog.getY()<0)
            {
                stagesClearedCt++;
                frog.setY(680);
                for(int i=0;i<4;i++)
                    picArrayCar1[i].setXChange(picArrayCar1[i].getXChange()+2);
                for(int p=0;p<4;p++)
                    picArrayCar2[p].setXChange(picArrayCar2[p].getXChange()-2);
                for(int r=0;r<4;r++)
                    picArrayCar3[r].setXChange(picArrayCar3[r].getXChange()+2);
                for(int c=0;c<4;c++)
                    picArrayCar4[c].setXChange(picArrayCar4[c].getXChange()-2);
            }
            if(lifeCt==0)
            {
                pageCt=2;
            }
            try
            {main.sleep(50);}
            catch(Exception e){}
        }
        if(lifeCt==0)
        {
            for(int i=0;i<4;i++)
            {
                picArrayCar1[i].setAlive(false);
            }
            for(int p=0;p<4;p++)
            {
                picArrayCar2[p].setAlive(false);
            }
            for(int r=0;r<4;r++)
            {
                picArrayCar3[r].setAlive(false);
            }
            for(int c=0;c<4;c++)
            {
                picArrayCar4[c].setAlive(false);
            }
        }
    }

    public void paint (Graphics g)
    {
        bufferG.setColor(Color. black);
        bufferG.fillRect(0,0,10000,10000);
        bufferG.setFont(font);
        bufferG.setColor(Color. black);

        if(pageCt==0)
        {
            bufferG.setColor(Color. blue);
            bufferG.drawString("Welcome to Frogger!", 100,100);
            bufferG.drawString("The object of the game is to cross the road without hitting any of the cars.",100,150);
            bufferG.drawString("To win, you must get the frog all the way to the top of the stage.",100,200);
            bufferG.drawString("If you lose your three lives, it's GAME OVER.",100,250);
            bufferG.drawString("Everytime you make it across, you restart at the bottom and the cars speed up.",100,300);
            bufferG.drawString("Try to clear as many stages as you can!",100,350);
            bufferG.drawString("To move your frog, click the arrow keys to move, left, right, up and down.",100,400);
            bufferG.drawString("To start, press the ENTER key.",100,450);
            bufferG.drawString("Good luck!",100,500);
        }
        if(pageCt==1)
        {
            bufferG.drawImage(stage,0,0,1300,700,this);
            bufferG.drawString("Lives: "+lifeCt,50,60);
            bufferG.drawString("Stage: "+(stagesClearedCt+1),1100,60);
            if(frog.getXChange()!=0 || frog.getYChange()!=0)
                bufferG.drawImage(frog.getImage(), frog.getX(), frog.getY(), 25,35,this);
            else
                bufferG.drawImage(standUp, frog.getX(), frog.getY(), 25,35,this);
            for(int c=0;c<4;c++)
                bufferG.drawImage(picArrayCar4[c].getImage(), picArrayCar4[c].getX(), picArrayCar4[c].getY(), -110,100,this);
            for(int r=0;r<4;r++)
                bufferG.drawImage(picArrayCar3[r].getImage(), picArrayCar3[r].getX(), picArrayCar3[r].getY(), 110,100,this);
            for(int p=0;p<4;p++)
                bufferG.drawImage(picArrayCar2[p].getImage(), picArrayCar2[p].getX(), picArrayCar2[p].getY(), -110,100,this);
            for(int i=0;i<4;i++)
                bufferG.drawImage(picArrayCar1[i].getImage(), picArrayCar1[i].getX(), picArrayCar1[i].getY(), 110,100,this);
        }
        if(pageCt==2)
        {
            bufferG.setColor(Color.red);
            bufferG.drawString("GAME OVER!",500,300);
            bufferG.drawString("Stages Cleared: "+stagesClearedCt,500,350);
        }
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
            frog.setXChange(0);
        }
        if(code==e.VK_LEFT)
        {
            frog.setXChange(0);
        }
        if(code==e.VK_UP)
        {
            frog.setYChange(0);
        }
        if(code==e.VK_DOWN)
        {
            frog.setYChange(0);
        }
    }

    public void keyPressed(KeyEvent e)
    {
        int code=e.getKeyCode();
        if(code==e.VK_RIGHT)
        {
            if(frog.getXChange()==0)
            {
                frog.setXChange(15);
                frog.setImage(picArrayFrogUp);
            }
        }
        if(code==e.VK_LEFT)
        {
            if(frog.getXChange()==0)
            {
                frog.setXChange(-15);
                frog.setImage(picArrayFrogUp);
            }
        }
        if(code==e.VK_UP)
        {
            if(frog.getYChange()==0)
            {
                frog.setYChange(-10);
                frog.setImage(picArrayFrogUp);
            }
        }
        if(code==e.VK_DOWN)
        {
            if(frog.getYChange()==0)
            {
                frog.setYChange(10);
                frog.setImage(picArrayFrogDown);
            }
        }
        if(code==e.VK_ENTER)
        {
            pageCt=1;
            for(int i=0; i<4;i++)
            {
                Car1[0]=this.getImage(this.getCodeBase(),"car 1.png");
                Car1[1]=this.getImage(this.getCodeBase(),"car 3.png");
                Car1[2]=this.getImage(this.getCodeBase(),"car 15.png");
                Car1[3]=this.getImage(this.getCodeBase(),"car 5.png");
                picArrayCar1[i]=new MovingPic (Car1[i],50,0+440*i,465,15,0);
                picArrayCar1[i].setAlive(true);
                picArrayCar1[i].start();
            }
            for(int p=0; p<4;p++)
            {
                Car2[0]=this.getImage(this.getCodeBase(),"car 6.png");
                Car2[1]=this.getImage(this.getCodeBase(),"car 7.png");
                Car2[2]=this.getImage(this.getCodeBase(),"car 14.png");
                Car2[3]=this.getImage(this.getCodeBase(),"car 9.png");
                picArrayCar2[p]=new MovingPic (Car2[p],50,0+460*p,370,-20,0);
                picArrayCar2[p].setAlive(true);
                picArrayCar2[p].start();
            }
            for(int r=0; r<4;r++)
            {
                Car3[0]=this.getImage(this.getCodeBase(),"car 8.png");
                Car3[1]=this.getImage(this.getCodeBase(),"car 12.png");
                Car3[2]=this.getImage(this.getCodeBase(),"car 10.png");
                Car3[3]=this.getImage(this.getCodeBase(),"car 13.png");
                picArrayCar3[r]=new MovingPic (Car3[r],50,0+470*r,180,20,0);
                picArrayCar3[r].setAlive(true);
                picArrayCar3[r].start();
            }
            for(int c=0; c<4;c++)
            {
                Car4[0]=this.getImage(this.getCodeBase(),"car 4.png");
                Car4[1]=this.getImage(this.getCodeBase(),"car 14.png");
                Car4[2]=this.getImage(this.getCodeBase(),"car 11.png");
                Car4[3]=this.getImage(this.getCodeBase(),"car 15.png");
                picArrayCar4[c]=new MovingPic (Car4[c],50,0+480*c,80,-25,0);
                picArrayCar4[c].setAlive(true);
                picArrayCar4[c].start();
            }
            //pictures taken from Bing Images, search "car sprites"
            main.start();
        }
    }

    public void keyTyped(KeyEvent e){}
}