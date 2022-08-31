import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

/*An applet to make a game of BlackJack
 * Amanda Ramos March 5th, 2014
 */

public class BlackJack extends Applet implements ActionListener
{   
    Font font= new Font( "Monotype Corsiva", 1, 30);
    Color myGreen=new Color( 0, 150, 0 );
    Deck d= new Deck();
    Card user[]=new Card[10];
    Card dealer[]=new Card[10];
    int userNumCards=2, dealerNumCards=2;
    int userTotal, dealerTotal;
    boolean showDealerCards=false;
    boolean userDone=false,dealerDone=false;
    boolean BlackJack;
    String output="";
    int cardCt=4;
    int moneyCt=1000;
    int bet=0;
    TextField betTF= new TextField();
    Button placeBetBtn= new Button("Place Bet");
    Button dealBtn=new Button("Deal");
    Button stayBtn=new Button("Stay");
    Button playAgainBtn=new Button("Play Again");
    Image buffer;
    Graphics bufferG;

    public void init()
    {
        
        resize(1300,700);
        this.setLayout(null);

        betTF.setBounds(600,300,75,40);
        this.add(betTF);
        betTF.addActionListener(this);

        placeBetBtn.setBounds(700,300,100,40);
        this.add(placeBetBtn);
        placeBetBtn.addActionListener(this);

        dealBtn.setBounds(600,500,100,40);
        this.add(dealBtn);
        dealBtn.addActionListener(this);
        dealBtn.setEnabled(false);

        stayBtn.setBounds(710,500,100,40);
        this.add(stayBtn);
        stayBtn.addActionListener(this);
        stayBtn.setEnabled(false);

        playAgainBtn.setBounds(820,500,100,40);
        this.add(playAgainBtn);
        playAgainBtn.addActionListener(this);
        playAgainBtn.setEnabled(false);

        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == placeBetBtn )
        {
            d.shuffle();
            userNumCards=2;
            dealerNumCards=2;
            for(int p=0;p<10;p++)
            {   
                user[p]=d.deal();
            }
            for(int r=0;r<10;r++)
            {
                dealer[r]=d.deal();
            }

            bet=Integer.parseInt(betTF.getText());  

            if(0<bet&&bet<=moneyCt)
            {
                moneyCt=moneyCt-bet;
                output="Nice bet";
                placeBetBtn.setEnabled(false);
                dealBtn.setEnabled(true);
                stayBtn.setEnabled(true);
                betTF.setEnabled(false);
            }
            else 
            {
                output="Invalid bet";
            }
        }
        if(e.getSource()==dealBtn)
        {
            userNumCards++;
        }
        if(e.getSource()==stayBtn)
        {
            userDone=true;
            dealBtn.setEnabled(false);
            stayBtn.setEnabled(false);

            int dealerAceCt=0;
            dealerTotal=0;
            for(int p=0;p<dealerNumCards;p++)
            {
                dealerTotal+=dealer[p].blackJackValue();
                if(dealer[p].blackJackValue()==11)
                    dealerAceCt++;
            }
            while(dealerTotal>21 && dealerAceCt>0)
            {
                dealerTotal-=10;
                dealerAceCt--;
            }
            while(dealerTotal<17)
            {
                dealer[dealerNumCards]=d.deal();
                dealerNumCards++;
                dealerAceCt=0;
                dealerTotal=0;
                for(int p=0;p<dealerNumCards;p++)
                {
                    dealerTotal+=dealer[p].blackJackValue();
                    if(dealer[p].blackJackValue()==11)
                        dealerAceCt++;
                }
                while(dealerTotal>21 && dealerAceCt>0)
                {
                    dealerTotal-=10;
                    dealerAceCt--;
                }
            }
        }
        if(e.getSource()==playAgainBtn)
        {
            userDone=false;
            dealerDone=false;
            userNumCards=0;
            dealerNumCards=0;
            placeBetBtn.setEnabled(true);
            betTF.setEnabled(true);
            betTF.setText("");
            playAgainBtn.setEnabled(false);
        }

        int userAceCt=0;
        userTotal=0;
        for(int p=0;p<userNumCards;p++)
        {
            userTotal+=user[p].blackJackValue();
            if(user[p].blackJackValue()==11)
                userAceCt++;
        }
        while(userTotal>21 && userAceCt>0)
        {
            userTotal-=10;
            userAceCt--;
        }
        if(userTotal>21)
        {
            dealBtn.setEnabled(false);
        }

        if(dealerTotal>=22)
        {
            dealerDone=true;
            playAgainBtn.setEnabled(true);
        }
        if(dealerTotal>16)
        {
            dealerDone=true;
            playAgainBtn.setEnabled(true);
        }

        if(userDone && dealerDone)
        {
            if(userTotal==21 && userNumCards==2)
                moneyCt+=(bet*2.5);
            if(userTotal>21)
                bet=0;
            if(dealerTotal>21)
                moneyCt+=(bet*2);
            if(userTotal>dealerTotal)
                moneyCt+=(bet*2);
            if(dealerTotal>userTotal)
                bet=0;
            if(userTotal==dealerTotal)
                moneyCt+=bet;
        }
        
        if(moneyCt==0 && userDone && dealerDone)
        {
            output="No more money, you lose.";
            placeBetBtn.setEnabled(false);
            dealBtn.setEnabled(false);
            stayBtn.setEnabled(false);
            playAgainBtn.setEnabled(false);
            betTF.setEnabled(false);
        }

        repaint();
    }

    public void paint (Graphics g)
    {
        bufferG.setColor(myGreen);
        bufferG.fillRect(0,0,10000,10000);
        bufferG.setColor(Color. black);
        bufferG.setFont(font);

        for(int p=0;p<userNumCards;p++)
            bufferG.drawImage(user[p].getImage(this),30*p+500,400,this);

        if(userDone)
        {
            for(int r=0;r<dealerNumCards;r++)
            {  
                bufferG.drawImage(dealer[r].getImage(this),30*r+500,50,this);
            }
        }
        else
            bufferG.drawImage(dealer[1].getImage(this),30*1+500,50,this);

        bufferG.drawString(""+userTotal,400,400);
        if(userDone==true)
            bufferG.drawString(""+dealerTotal,400,50);

        bufferG.drawString("Money left: $"+moneyCt, 100,300);
        bufferG.drawString("Your bet: $"+bet,100,340);
        bufferG.drawString(""+output,100,380);

        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

}