import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

/*Simplet Applet to make a game to create Brickbreaker
 * Amanda Ramos, December 11th, 2013
 */

public class Brickbreaker extends Applet implements KeyListener, Runnable
{
    Thread main= new Thread(this);
    int blocks[][]= new int [3][10];
    Color myPurple=new Color( 210, 1, 210 );
    int ballX=500, ballY=500, XChange=-5, YChange=-5, paddleX=400, paddleY=600;
    boolean gameover=false;
    Image buffer;
    Graphics bufferG;

    public void init()
    {
        this.setLayout(null);
        this.addKeyListener(this);
        resize(1300,700);
        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();
        main.start();
        for(int r=0; r<3; r++)
            for(int c=0; c<10; c++)
                blocks[r][c]=1;
                
        blocks[2][4]=3;
        blocks[1][8]=3;
        blocks[0][1]=3;
        blocks[2][6]=3;
        blocks[0][0]=3;
        blocks[0][8]=3;
        blocks[2][2]=2;
        blocks[1][6]=2;
        blocks[1][9]=2;
        blocks[2][1]=2;
        blocks[1][2]=2;
        blocks[0][4]=2;
    }

    public void drawBlocks(Graphics g)
    {
        for(int r=0; r<3; r++)
            for(int c=0; c<10; c++)
            { 
                if(blocks[r][c]==1)    
                    bufferG.setColor(Color. red);    
                if(blocks[r][c]==2)    
                    bufferG.setColor(Color. green);
                if(blocks[r][c]==3)    
                    bufferG.setColor(Color. blue);  
                if(blocks[r][c]>0)
                bufferG.fillRect(10+100*c, 100+50*r, 98, 48);
        }
    }

    public void run()
    {
        while(!gameover)
        {
            repaint();

            ballX+=XChange;
            ballY+=YChange;
            
            Rectangle ballRect= new Rectangle(ballX, ballY, 30, 30);
            Rectangle paddleRect= new Rectangle(paddleX, paddleY, 70, 20);
            
            boolean hit=false;
            for(int r=0; r<3 && !hit; r++)
                for(int c=0; c<10 && !hit; c++)
                {
                    if(blocks[r][c]>0)
                    {   Rectangle blockRect= new Rectangle(10+100*c, 100+50*r, 98, 48);
                        if(blockRect.intersects(ballRect))
                        {
                            blocks[r][c]--;
                            YChange*=-1;
                            hit=true;
                        }
                    }
            }

            if(ballRect.intersects(paddleRect))
            {
                YChange*=-1;
            }

            try
            {main.sleep(50);}
            catch(Exception e){}

            if(ballX<10)
            {
                XChange*=-1;

            }
            else if (ballX>1000)
            {
                XChange*=-1;

            }
             if(ballY<100)
            {
                YChange*=-1;

            }
        }
    }

    public void keyReleased(KeyEvent e){}    

    public void keyPressed(KeyEvent e)
    {
        int code=e.getKeyCode();
        if(code==e.VK_RIGHT)
        {
            paddleX+=20;
        }
        if(code==e.VK_LEFT)
        {
            paddleX-=20;
        }
    }

    public void keyTyped(KeyEvent e){}

    public void paint(Graphics g)
    {
        bufferG.setColor(Color. yellow);
        bufferG.fillRect(0,0,10000,10000);
        bufferG.setColor(Color. black);
        bufferG.fillRect(5,90,1010,550);
        drawBlocks(g);
        bufferG.setColor(Color.red);
        bufferG.drawOval(ballX, ballY, 30,30);
        bufferG.drawRect(paddleX, paddleY, 70, 20);
        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }
}