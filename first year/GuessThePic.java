import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

/*Simplet Applet to make a game to uncover a picture
 * Amanda Ramos, November 12th, 2013
 */

public class GuessThePic extends Applet implements MouseListener, ActionListener, Runnable
{
    Thread main= new Thread(this);
    private int x=100, y=100;
    Font font= new Font( "FangSong", 1, 20);  
    boolean cover[][]=new boolean[10][10];
    boolean gameOn=true;
    int lowScore=1000;
    Button restartBtn= new Button("Restart");
    Button startBtn= new Button("START");
    Button submitBtn= new Button("submit answer");
    TextField guessTF= new TextField ();
    String input;
    int loopCt;   
    int page=0;
    boolean win;
    Image turtle;
    Image buffer;
    Graphics bufferG;

    public void init()
    {
        addMouseListener(this);
        this.setLayout(null);
        resize(1300,700);
        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();

        for(int r=0;r<10;r++)
            for(int c=0;c<10;c++)
            {
                cover[r][c]=true;
        }

        turtle=this.getImage(this.getCodeBase(),"cute-turtle.jpg");
        /*taken from http://www.bing.com/images/search?q=cute+turtle&FORM=HDRSC2&adlt=strict#view=detail&id=EF11A0F68E2945E3279A565EF6512309E2195A1C&selectedIndex=8
         * from Bing Images, search "cute turtle"
         */

        startBtn.setBounds(700,600,60,40);
        this.add(startBtn);
        startBtn.addActionListener(this);

        submitBtn.setBounds(1050,400,90,35);
        this.add(submitBtn);
        submitBtn.addActionListener(this);
        submitBtn.setVisible(false);

        restartBtn.setBounds(750,600,60,40);
        this.add(restartBtn);
        restartBtn.addActionListener(this);
        restartBtn.setVisible(false);

        guessTF.setBounds( 900, 400, 110, 30 );
        this.add( guessTF );
        guessTF.setVisible(false);

        main.start();
    }

    public void run()
    {
        do
        {
            while(win==false)
            {
                loopCt++;
                repaint();
                try
                {main.sleep(100);}
                catch(Exception e){}
            }
            if(loopCt<lowScore)
                lowScore=loopCt;
        }
        while(gameOn==true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if( e.getSource()==startBtn)
        {
            guessTF.setVisible(true);
            startBtn.setVisible(false);
            submitBtn.setVisible(true);
            page=1;
            loopCt=0;
        }
        if(e.getSource()==submitBtn)
        {
            input= guessTF.getText();
            guessTF.setText("");
            if(input.matches("turtle")||input.matches("its a turtle")||input.matches("cute turtle")||input.matches("is a turtle")||input.matches("strawberry and turtle")||input.matches("Turtle"))
            {
                win=true;
                for(int r=0;r<10;r++)
                    for(int c=0;c<10;c++)
                    {
                        cover[r][c]=false;
                }
            }
            else if(input.matches("turtl")||input.matches("tutrle")||input.matches("turlte")||input.matches("tutle"))
            {
                //everyone complains about misspelling so I'm trying to make it a bit easier so its a bit fair
                //just a few mispells just in case
                loopCt-=20;
            }
            else
            {
                loopCt+=100;
            }
        }
        if( e.getSource()==restartBtn)
        {
            win=false;
            for(int r=0;r<10;r++)
                for(int c=0;c<10;c++)
                {
                    cover[r][c]=true;
            }
            guessTF.setText("");
            input="";
            page=0;
            loopCt=0;
            startBtn.setVisible(true);
            restartBtn.setVisible(false);
        }
        repaint();

    }

    public void paint(Graphics g)
    {
        bufferG.setColor(Color. red);
        bufferG.fillRect(0,0,10000,10000);
        bufferG.setFont(font);
        if(page==0)
        {
            bufferG.setColor(Color. black);
            bufferG.drawString("Welcome to Guess the Picture!", 100,100);
            bufferG.drawString("To win, try to uncover the blocks and type in the textbox and press SUMBIT to guess the picture", 100,150);
            bufferG.drawString("Try to make the fastest time to guess the picture.", 100, 200);
            bufferG.drawString("The best time will be shown and you may restart and try to beat the best time.", 100, 250);
            bufferG.drawString("If you make an incorrect guess or wrong spelling, a few seconds will be added or subtracted to your time.", 100,300);
            bufferG.drawString("The time added or subtracted will depend on the mistake you make.", 100, 350);
            bufferG.drawString("To begin, press the START button. Good Luck!", 100, 400);
        }
        if(page==1)
        {
            bufferG.drawImage(turtle, 20, 10, 640, 620, this);
            for(int r=0;r<10;r++)
                for(int c=0;c<10;c++)
                {
                    if(cover[r][c]==true)
                    {
                        bufferG.setColor(Color. yellow);    
                        bufferG.fillRect(20+65*c,10+65*r,64,64);   
                        bufferG.setColor(Color. black);
                        bufferG.drawString("Time "+ loopCt/10.0, 1000, 250);
                    }
            }
            bufferG.setColor(Color. black);
            bufferG.drawString("Guess the Picture", 900,100);
            if(win==true)
            {
                bufferG.setColor(Color. black);
                bufferG.drawString("YOU WIN!!!", 900, 200);
                bufferG.drawString("Your time is: "+loopCt/10.0, 1000,250);

                bufferG.drawString("Best Times: "+lowScore/10.0,1000, 300);

                restartBtn.setVisible(true);
                guessTF.setVisible(false);
                submitBtn.setVisible(false);
            }
        }
        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void mouseClicked(MouseEvent e)
    {
        int row=(e.getY()-10)/65;
        int col=(e.getX()-20)/65;
        cover[row][col]=false;
        if(cover[row][col]==cover[0][0])
        {
            cover[1][5]=false;
            cover[2][6]=false;
            cover[7][9]=false;
            cover[6][2]=false;
            cover[5][5]=false;
        }
        if(cover[row][col]==cover[4][7])
        {
            cover[9][5]=false;
            cover[1][4]=false;
            cover[0][9]=false;
            cover[6][0]=false;
            cover[8][9]=false;
        }
        if(cover[row][col]==cover[7][2])
        {
            cover[4][2]=false;
            cover[3][8]=false;
            cover[0][3]=false;
            cover[2][4]=false;
            cover[3][3]=false;
        }
        if(cover[row][col]==cover[3][1])
        {
            cover[6][1]=false;
            cover[3][2]=false;
            cover[8][3]=false;
            cover[6][4]=false;
            cover[3][5]=false;
        }
        if(cover[row][col]==cover[8][7])
        {
            cover[6][7]=false;
            cover[4][9]=false;
            cover[9][9]=false;
            cover[8][5]=false;
            cover[2][3]=false;
        }
        repaint();
    }

    public void mouseExited(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}
}