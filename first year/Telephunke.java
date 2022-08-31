import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

/*An applet to make a game of Poker
 * Amanda Ramos March 17th, 2014
 */

public class Telephunke extends Applet implements ActionListener
{   
    Font font= new Font( "Monotype Corsiva", 1, 30);
    Color myGreen=new Color( 0, 150, 0 );
    Deck d= new Deck();
    Card user[]=new Card[5];
    Card dealer[]=new Card[5];
    int userNumCards=5, dealerNumCards=5;
    int userTotal, dealerTotal;
    boolean showDealerCards=false;
    boolean userDone=false,dealerDone=false;
    boolean rules=true;
    String output="";
    int cardCt=4;
    int moneyCt=1000;
    int bet=0;
    TextField betTF= new TextField();
    Button startBtn= new Button("Start");
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

        startBtn.setBounds(500,600,100,40);
        this.add(startBtn);
        startBtn.addActionListener(this);

        betTF.setBounds(50,200,75,40);
        this.add(betTF);
        betTF.addActionListener(this);
        betTF.setVisible(false);

        placeBetBtn.setBounds(50,275,100,40);
        this.add(placeBetBtn);
        placeBetBtn.addActionListener(this);
        placeBetBtn.setVisible(false);

        dealBtn.setBounds(600,500,100,40);
        this.add(dealBtn);
        dealBtn.addActionListener(this);
        dealBtn.setEnabled(false);
        dealBtn.setVisible(false);

        stayBtn.setBounds(710,500,100,40);
        this.add(stayBtn);
        stayBtn.addActionListener(this);
        stayBtn.setVisible(false);

        playAgainBtn.setBounds(820,500,100,40);
        this.add(playAgainBtn);
        playAgainBtn.addActionListener(this);
        playAgainBtn.setVisible(false);

        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==startBtn)
        {
            placeBetBtn.setVisible(true);
            betTF.setVisible(true);
            rules=false;
            //playAgainBtn.setVisible(false);
        }

        repaint();
    }

    public void paint (Graphics g)
    {
        bufferG.setColor(myGreen);
        bufferG.fillRect(0,0,10000,10000);
        bufferG.setColor(Color. black);
        bufferG.setFont(font);

        if(rules)
        {
            bufferG.drawString("Poker",600,90);
            bufferG.drawString("Blah",100,100);
        }

        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

}