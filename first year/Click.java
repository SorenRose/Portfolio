import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.util.Random;

/*Simplet Applet Click It
 * Amanda Ramos, Nick Cardini September 25th, 2013
 */

public class Click extends Applet implements ActionListener, Runnable
{
    Thread main= new Thread(this);    
    Button[] clickBtn= new Button[25];
    Font font= new Font( "Comic Sans MS", 1, 35);
    Random rand= new Random();
    int numbers[]={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
    int y= 100;
    int x= 350;
    int nextNum=1;
    int pgCt=0;
    Button nextBtn= new Button("Begin");
    Button restartBtn= new Button("Restart");
    long beginTime, endTime;
    double elapsedTime;
    String output;
    int timeCt=0;
    Image buffer;
    Graphics bufferG;

    public void randomize(int numbers[])
    {
        for(int i=0; i<25; i++)
        {
            int randPlace=rand.nextInt(25);
            int temp= numbers[i];
            numbers[i]=numbers[randPlace];
            numbers[randPlace]=temp;
        }
    }

    public void init()
    {
        resize(1300,700);
        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();
        main.start();
        this.setLayout(null);
        randomize(numbers);

        nextBtn.setBounds(700,400,75,40);
        this.add(nextBtn);
        nextBtn.addActionListener(this);

        restartBtn.setBounds(800,400,75,40);
        this.add(restartBtn);
        restartBtn.addActionListener(this);
        restartBtn.setVisible(false);

        for(int p=0; p<25; p++)
        {
            clickBtn[p]= new Button(""+(numbers[p]));
            clickBtn[p].setBounds(x+55*p,y,50,40);
            clickBtn[p].addActionListener(this);
            this.add(clickBtn[p]);
            clickBtn[p].setVisible(false);
            if(p<4)
            {
                x=350;
                y=100;
            }
            if(p>3&&p<9)
            {
                y=150;
                x=75;
            }
            if(p>8&&p<14)
            {
                y=200;
                x=-200;
            }
            if(p>13&&p<19)
            {
                y=250;
                x=-475;
            }
            if(p>18&&p<26)
            {
                y=300;
                x=-750;
            }
        }

    }

    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource() == nextBtn )
        {  pgCt=1;
            nextBtn.setVisible (false);
            beginTime=System.currentTimeMillis();

            for(int p=0; p<25; p++)
                clickBtn[p].setVisible(true);
        }

        for(int p=0; p<25; p++)
        {
            if(e.getSource() == clickBtn[p] )
            {
                if(nextNum==Integer.parseInt(clickBtn[p].getLabel()))
                {
                    nextNum++;
                    clickBtn[p].setVisible(false);
                    randomize(numbers);
                    for(int i=0; i<25; i++)
                    {
                        if(numbers[i]>=nextNum)
                        { 
                            clickBtn[i].setLabel(""+numbers[i]);
                            clickBtn[i].setVisible(true);
                        }
                        else
                            clickBtn[i].setVisible(false);
                    }
                }
                //
                

                if(nextNum==26)
                {
                    pgCt=2;
                    restartBtn.setVisible(true);
                    endTime=System.currentTimeMillis();
                    elapsedTime=(endTime-beginTime)/1000.0;
                    output="Total time is "+elapsedTime;
                }
            }
        }
        if(e.getSource()==restartBtn)
        {
            randomize(numbers);
            pgCt=0;
            restartBtn.setVisible(false);
            nextBtn.setVisible(true);
            for(int p=0; p<25; p++)
            {
                clickBtn[p].setLabel (""+(numbers[p]));
            }
        }
        repaint();
    }

    public void run()
    {
        do
        {
            if(pgCt==0)
                timeCt=0;
            if(pgCt==1)
            {
                timeCt++;
            }
            repaint();
            try
            {main.sleep(98);}
            catch(Exception e){}

        }
        while(pgCt<3);
    }

    public void paint (Graphics g)
    {   
        bufferG.setColor( Color. black);
        bufferG.fillRect(0,0,10000,100000);
        bufferG.setColor( Color. green);
        bufferG.setFont( font );
        if(pgCt==0)
        {
            bufferG.drawString("The Clicking Game",500,100);
            bufferG.drawString("The object of the game is to click the numbers in order from 1-25.", 50, 200);
            bufferG.drawString("Try to finish it as fast as possible!", 50, 300);
            bufferG.drawString("If you don't click the numbers in order, the game will not progress.", 50,250);
            bufferG.drawString("Click the button below to start the game.", 50, 350);
            bufferG.drawString("Good Luck!", 50, 400);
        }
        if(pgCt==1)
        {
            bufferG.drawString(""+timeCt/10.0, 700, 250);
            bufferG.drawString(""+nextNum, 100, 100);
        }
        if(pgCt==2)
        {
            bufferG.drawString(""+output, 500, 250);
        }
        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }
}