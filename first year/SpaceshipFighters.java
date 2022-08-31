import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

/*An applet to make fighting spaceships
 * Amanda Ramos April 8th, 2014
 */

public class SpaceshipFighters extends Applet implements Runnable, KeyListener
{   
    Thread main= new Thread(this);
    Image picL, picR, background, text;
    Font font= new Font( "Rockwell", 1, 20);
    MovingPic leftLasers[]=new MovingPic[40];
    MovingPic rightLasers[]=new MovingPic[40];
    int leftShotCt=0, rightShotCt=0;
    int rightHit=0, leftHit;
    boolean play=false;
    MovingPic mpL, mpR;
    Image buffer;
    Graphics bufferG;

    public void init()
    {
        this.setLayout(null);
        this.addKeyListener(this);
        resize(1300,700);

        picL=this.getImage(this.getCodeBase(),"spaceship L.png");
        picR=this.getImage(this.getCodeBase(),"ufo.png");
        //taken from Bing, search png spaceship
        background=this.getImage(this.getCodeBase(),"Stars.jpg");
        //taken from Bing, search space background
        text=this.getImage(this.getCodeBase(),"outText.png");
        //made it myselft
        mpL=new MovingPic(picL,100,50,300,0,0);
        mpR=new MovingPic(picR,100,900,300,0,0);

        for(int p=0;p<40;p++)
        {
            leftLasers[p]=new MovingPic(picL,100,0,0,10,0);
            leftLasers[p].setAlive(true);
            leftLasers[p].start();

            rightLasers[p]=new MovingPic(picR,100,0,0,10,0);
            rightLasers[p].setAlive(true);
            rightLasers[p].start();
        }

        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();
    }

    public void run()
    {
        while(play==true)
        {
            repaint();

            Rectangle rightShipRect=new Rectangle(mpR.getX(),mpR.getY(), 150,150);
            Rectangle leftShipRect=new Rectangle(mpL.getX(),mpL.getY(), 150,150);

            for(int p=0;p<40;p++)
            {
                Rectangle leftLaserRect=new Rectangle(leftLasers[p].getX(),leftLasers[p].getY(),40,10);
                if(leftLaserRect.intersects(rightShipRect))
                {
                    rightHit++;
                    leftLasers[p].setX(2000);
                }

                Rectangle rightLaserRect=new Rectangle(rightLasers[p].getX(),rightLasers[p].getY(),40,10);
                if(rightLaserRect.intersects(leftShipRect))
                {
                    leftHit++;
                    rightLasers[p].setX(-2000);
                }

            }
            for(int p=0;p<40;p++)
            {
                Rectangle leftLaserRect=new Rectangle(leftLasers[p].getX(),leftLasers[p].getY(),40,10);
                for(int r=0;r<40;r++)
                {
                    Rectangle rightLaserRect=new Rectangle(rightLasers[r].getX(),rightLasers[r].getY(),40,10);
                    if(rightLaserRect.intersects(leftLaserRect))
                    {
                        rightLasers[p].setX(-2000);
                        leftLasers[p].setX(2000);
                    }
                }
            }

            if(mpL.getX()<=0)
                mpL.setXChange(10);
            else if(mpL.getY()<=0)
                mpL.setYChange(10);
            else if(mpL.getY()>=550)
                mpL.setYChange(-10);
            else if(mpL.getX()>=350)
                mpL.setXChange(-10);

            if(mpR.getX()<=700)
                mpR.setXChange(10);
            else if(mpR.getY()<=0)
                mpR.setYChange(10);
            else if(mpR.getY()>=550)
                mpR.setYChange(-10);
            else if(mpR.getX()>=1100)
                mpR.setXChange(-10);

            try
            {main.sleep(50);}
            catch(Exception e){}
        }
    }

    public void paint (Graphics g)
    {
        bufferG.setColor(Color. black);
        bufferG.fillRect(0,0,10000,10000);
        bufferG.setFont(font);
        if(play==false)
        {
            bufferG.setColor(Color.white);
            bufferG.drawString("Space Fighters", 475,100);
            bufferG.drawImage(text,330,-10,225,250,this);
            bufferG.drawString("Welcome to Spaceship Fighters! Your ships have been hit and the controls are messed up!",100,250);
            bufferG.drawString("To move the left ship press E to go up, S to go left, D to go right and X to go down. Press Z to shoot.",100,300);
            bufferG.drawString("To move the right ship just press the arrow keys. Press M to Shoot. To Start, press ENTER. GOOD LUCK!",100,350);
        }
        if(play==true)
        {
            bufferG.drawImage(background,0,0,1300,700,this);
            for(int p=0;p<40;p++)
            {
                bufferG.drawImage(leftLasers[p].getImage(), leftLasers[p].getX(),leftLasers[p].getY(),40,10,this);
                bufferG.drawImage(rightLasers[p].getImage(), rightLasers[p].getX(),rightLasers[p].getY(),40,10,this);
            }
            bufferG.drawImage(mpL.getImage(), mpL.getX(),mpL.getY(), 150,150,this);
            bufferG.drawImage(mpR.getImage(), mpR.getX(),mpR.getY(), 200,150,this);
            bufferG.setColor(Color. white);
            bufferG.drawString("Left Hit: "+leftHit,100,100);
            bufferG.drawString("Right Hit: "+rightHit,800,100);
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
        if(code==e.VK_X)
        {
            mpL.setYChange(10);
        }
        if(code==e.VK_S)
        {
            mpL.setXChange(-10);
        }
        if(code==e.VK_E)
        {
            mpL.setYChange(-10);
        }
        if(code==e.VK_D)
        {
            mpL.setXChange(10);
        }
        if(code==e.VK_Z)
        {
            leftLasers[leftShotCt].setX(mpL.getX()+50);
            leftLasers[leftShotCt].setY(mpL.getY()+65);
            leftLasers[leftShotCt].setXChange(30);
            leftShotCt++;
            if(leftShotCt==40)
                leftShotCt=0;
        }

        if(code==e.VK_DOWN)
        {
            mpR.setYChange(10);
        }
        if(code==e.VK_LEFT)
        {
            mpR.setXChange(-10);
        }
        if(code==e.VK_UP)
        {
            mpR.setYChange(-10);
        }
        if(code==e.VK_RIGHT)
        {
            mpR.setXChange(10);
        }
        if(code==e.VK_M)
        {
            rightLasers[rightShotCt].setX(mpR.getX()+50);
            rightLasers[rightShotCt].setY(mpR.getY()+65);
            rightLasers[rightShotCt].setXChange(-30);
            rightShotCt++;
            if(rightShotCt==40)
                rightShotCt=0;
        }
    }

    public void keyPressed(KeyEvent e)
    {
        int code=e.getKeyCode();
        if(code==e.VK_ENTER)
        {
            mpL.setAlive(true);
            mpL.start();
            mpR.setAlive(true);
            mpR.start();
            play=true;
            main.start();
        }
    }

    public void keyTyped(KeyEvent e){}

}