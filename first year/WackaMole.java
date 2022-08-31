import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import java.util.Random;

/*Simple Applet to make a game of Whacka Mole
 * Amanda Ramos, January 2nd, 2014
 */

public class WackaMole extends Applet implements MouseListener, Runnable, KeyListener
{
    Thread main= new Thread(this);
    Random rand= new Random ();
    Font font= new Font( "Century", 1, 20);
    Color myBrown=new Color( 110, 70, 60 );
    int randR,randC=0;
    Toolkit toolkit=Toolkit.getDefaultToolkit();
    Image hammer=toolkit.getImage("hammer.png");
    //taken from bing images, search "mario hammer"
    Image moleHole, hitMole;
    Cursor brokenCursor=toolkit.createCustomCursor(hammer, new Point(0,0),"img");
    boolean board[][]=new boolean [4][4];
    boolean gameOn=true, hit=false, gold=false;
    int click1Ct=0, click2Ct=0, frameCt=0, timeCt=0, player=1, pageCt=0;
    Image buffer;
    Image wackaArray[]= new Image[13];
    Image goldWacka[]= new Image[13];
    Graphics bufferG;

    public void init()
    {
        this.setLayout(null);
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.setCursor(brokenCursor);
        resize(1300,700);

        wackaArray[1]=getImage( this.getCodeBase(),"wacka mole 1.png");
        wackaArray[0]=getImage( this.getCodeBase(),"wacka mole 2.png");
        wackaArray[2]=getImage( this.getCodeBase(),"wacka mole 3.png");
        wackaArray[3]=getImage( this.getCodeBase(),"wacka mole 4.png");
        wackaArray[4]=getImage( this.getCodeBase(),"wacka mole 5.png");
        wackaArray[5]=getImage( this.getCodeBase(),"wacka mole 6.png");
        wackaArray[11]=getImage( this.getCodeBase(),"wacka mole.png");
        wackaArray[6]=getImage( this.getCodeBase(),"wacka mole 6.png");
        wackaArray[7]=getImage( this.getCodeBase(),"wacka mole 5.png");
        wackaArray[8]=getImage( this.getCodeBase(),"wacka mole 4.png");
        wackaArray[9]=getImage( this.getCodeBase(),"wacka mole 3.png");
        wackaArray[10]=getImage( this.getCodeBase(),"wacka mole 2.png");
        wackaArray[11]=getImage( this.getCodeBase(),"wacka mole 1.png");

        goldWacka[1]=getImage( this.getCodeBase(),"wacka mole gold 1.png");
        goldWacka[0]=getImage( this.getCodeBase(),"wacka mole gold 2.png");
        goldWacka[2]=getImage( this.getCodeBase(),"wacka mole gold 3.png");
        goldWacka[3]=getImage( this.getCodeBase(),"wacka mole gold 4.png");
        goldWacka[4]=getImage( this.getCodeBase(),"wacka mole gold 5.png");
        goldWacka[5]=getImage( this.getCodeBase(),"wacka mole gold 6.png");
        goldWacka[11]=getImage( this.getCodeBase(),"wacka mole gold.png");
        goldWacka[6]=getImage( this.getCodeBase(),"wacka mole gold 6.png");
        goldWacka[7]=getImage( this.getCodeBase(),"wacka mole gold 5.png");
        goldWacka[8]=getImage( this.getCodeBase(),"wacka mole gold 4.png");
        goldWacka[9]=getImage( this.getCodeBase(),"wacka mole gold 3.png");
        goldWacka[10]=getImage( this.getCodeBase(),"wacka mole gold 2.png");
        goldWacka[11]=getImage( this.getCodeBase(),"wacka mole gold 1.png");
        //taken from bing images, search "wacka mole"

        moleHole=this.getImage(this.getCodeBase(),"wacka mole hole.png");
        hitMole=this.getImage(this.getCodeBase(),"wacka mole 7.png");

        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();

    }

    public void run()
    {
        do
        {
            if(gameOn)
            {
                repaint();
                timeCt++;
                frameCt++;
                if(timeCt>100&&timeCt<115||timeCt>200&&timeCt<215||timeCt>400&&timeCt<410||timeCt>500&&timeCt<510) 
                    gold=true;
                else
                    gold=false;                
                if(timeCt==300)
                {
                    gameOn=false;
                    player=2;
                }
                if(timeCt==600)
                {
                    gameOn=false;
                }
                if(frameCt==11)
                {
                    board[randR][randC]=true;
                }
                if(frameCt>11)
                {
                    frameCt=0;
                    board[randR][randC]=false;
                    randR=rand.nextInt(4);
                    randC=rand.nextInt(4);
                    board[randR][randC]=true;

                }
                try
                {main.sleep(100);}
                catch(Exception e){}
            }
        }
        while(timeCt<600);
    }

    public void keyTyped(KeyEvent e){}

    public void paint(Graphics g)
    {
        bufferG.setColor(Color. green);
        bufferG.fillRect(0,0,10000,10000);
        bufferG.setFont(font);
        if(pageCt==0)
        {
            bufferG.setColor(Color. black);
            bufferG.drawString("Welcome to Whacka Mole! This is a two player game.", 100, 150);
            bufferG.drawString("The first game will be player one, try to hit as many moles as you can. A mole earns you a point.", 100, 180);
            bufferG.drawString("If you hit a golden mole you get three points. After 30 seconds, player two will play. For player two to start,", 100, 210);
            bufferG.drawString("you MUST press the R button on your keyboard. Try to beat each other by hitting the most moles within", 100, 240);
            bufferG.drawString("the time limit. To start, press the S button on your keyboard.", 100, 270);
            bufferG.drawImage(hitMole,500,400,100,100,this); 
        }
        if(pageCt==1)
        {
            for(int r=0;r<4;r++)
                for(int c=0;c<4;c++)
                {
                    bufferG.setColor(myBrown);
                    bufferG.drawRect(500+71*c,150+71*r,70,70);
                    bufferG.drawImage(moleHole,500+71*c,150+71*r,70,70,this); 
                    if(board[r][c]==true)
                    {
                        if(gold)
                        {
                            bufferG.drawImage(goldWacka[frameCt],500+71*c,150+71*r,70,70,this); 
                        }
                        else
                            bufferG.drawImage(wackaArray[frameCt],500+71*c,150+71*r,70,70,this);
                    }
            }

            bufferG.setColor(Color. black);
            bufferG.drawString("Player 1 Score:"+click1Ct, 100,300);
            bufferG.drawString("Player 2 Score:"+click2Ct, 1000,300);

            if(timeCt==600)
            {
                if(click1Ct>click2Ct)
                    bufferG.drawString("Player 1 wins!", 500, 550);
                if(click1Ct<click2Ct)
                    bufferG.drawString("Player 2 wins!", 500, 550);
            }

            bufferG.drawString("Time: "+(timeCt/10), 500,100);
        }
        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void mouseClicked(MouseEvent e)
    {
        int row=(e.getY()-150)/71;
        int col=(e.getX()-500)/71;

        if(board[row][col]==true && gameOn)
        {
            if(player==1)
            {
                if (gold==true)
                    click1Ct=click1Ct+3;
                else
                    click1Ct++;
                gold=false;
            }
            if(player==2)
            {
                if (gold==true)
                    click2Ct=click2Ct+3;
                else
                    click2Ct++;
                gold=false;
            }
            frameCt=0;
            board[randR][randC]=false;
            randR=rand.nextInt(4);
            randC=rand.nextInt(4);
            board[randR][randC]=true;
        }

        repaint();
    }

    public void mouseExited(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void keyReleased(KeyEvent e){}    

    public void keyPressed(KeyEvent e)
    {
        int code=e.getKeyCode();
        if(code==e.VK_R)
        {
            gameOn=true;
        }
        if(code==e.VK_S)
        {
            main.start();
            pageCt=1;
            frameCt=0;
            timeCt=0;
        }
    }
}