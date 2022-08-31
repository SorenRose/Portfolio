import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

/*An applet to make a game of Cards, not sure of the name
 * Amanda Ramos March 18th, 2014
 */

public class CardGame extends Applet implements ActionListener, MouseListener
{   
    Font font= new Font( "Monotype Corsiva", 1, 30);
    Color myGreen=new Color( 0, 150, 0 );
    Deck d= new Deck();
    Card playPile[]=new Card[3];
    Card matchPile[]=new Card[3];
    int cardsLeft=46;
    boolean card1=false, card2=false, card3=false;
    boolean rules=true;
    String output="";
    int cardCt=4;
    int clickCt=0;
    Button startBtn= new Button("Start");
    Button playAgainBtn=new Button("Play Again");
    Image cardBack, empty;
    Image buffer;
    Graphics bufferG;

    public void init()
    {
        resize(1300,700);
        addMouseListener(this);
        this.setLayout(null);

        cardBack=this.getImage(this.getCodeBase(),"cardBack 2.png");
        //taken from Bing Images, search: playing card back

        empty=this.getImage(this.getCodeBase(),"empty card.png");
        //just an empty pic

        startBtn.setBounds(500,600,100,40);
        this.add(startBtn);
        startBtn.addActionListener(this);

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
            d.shuffle();
            for(int p=0;p<3;p++)
                matchPile[p]=d.deal();
            for(int r=0;r<3;r++)
                playPile[r]=d.deal();
            rules=false;
            startBtn.setVisible(false);

        }

        repaint();
    }

    public void paint (Graphics g)
    {
        bufferG.setColor(myGreen);
        bufferG.fillRect(0,0,10000,10000);
        bufferG.setColor(Color. black);
        bufferG.setFont(font);

        if(rules==true)
        {
            //bufferG.drawRect(100,100,1050,500);
            bufferG.drawString("Matches",600,90);
            bufferG.drawString("Welcome to Matches! This is a game of cards where a player must match their",50,140);
            bufferG.drawString("three cards on the bottom with any of the three on the top. If any of your cards", 50, 190);
            bufferG.drawString("on the bottom are one more or one less in value then you place that card on the bottom",50, 240);
            bufferG.drawString("on the top deck. You cannot place matching value on top of each other. Then deal a new ",50,290);
            bufferG.drawString("card on the bottom to fill the empty space. To deal new cards, press the pile of cards face down.",50,340);
            bufferG.drawString("If no cards on the bottom match the cards on the top, you have to deal a new set of three cards",50,390);
            bufferG.drawString("to the top. If the deck runs out and you and you still have cards on the bottom, you lose.",50,440);
            bufferG.drawString("If you have no cards on the bottom, you win. To start, click the START button. To play,",50,490);
            bufferG.drawString("click the cards facing up to match them, be careful on what you click! Good Luck! ",50, 540);
        }
        else
        {
            for(int p=0;p<3;p++)
                bufferG.drawImage(matchPile[p].getImage(this),100*p+500,100,this);
            for(int r=0;r<3;r++)
                if(playPile[r]!=null)    
                    bufferG.drawImage(playPile[r].getImage(this),100*r+500,300,this);

            if(cardsLeft<=0)
                cardsLeft=0;

            if(cardsLeft<=0)
            {
                for(int r=0;r<3;r++)
                    if(playPile[r]==null) 
                        output="You win!";
                    else
                        output="You lose";
            }

            bufferG.drawString("Cards left: "+cardsLeft,100,200);
            bufferG.drawImage(cardBack, 900, 500, 71, 96, this);
            bufferG.drawString(""+clickCt,100,400);
            bufferG.drawString(""+output,100,600);
        }

        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void mouseClicked(MouseEvent e)
    {
        int mouseX=e.getX();
        int mouseY=e.getY();

        if(clickCt==0)
        {
            if(mouseX>500 && mouseX< 571 && mouseY>300 && mouseY<396)
            {   
                clickCt+=1;
                card1=true;
                card2=false;
                card3=false;
            }
        }
        if(clickCt==0)
        {
            if(mouseX>600 && mouseX< 671 && mouseY>300 && mouseY<396)
            {   
                clickCt+=1;
                card2=true;
                card1=false;
                card3=false;
            }
        }
        if(clickCt==0)
        {
            if(mouseX>700 && mouseX< 771 && mouseY>300 && mouseY<396)
            {   
                clickCt+=1;
                card3=true;
                card1=false;
                card2=false;
            }
        }

        if(mouseX>900 && mouseX< 971 && mouseY>500 && mouseY<596)
        {
            cardsLeft-=3;
            for(int r=0;r<3;r++)
                if(d.numCardsLeft()>0)   
                    matchPile[r]=d.deal();
        }

        if(card1)
        {
            if(clickCt==1)
            {
                if(mouseX>500 && mouseX< 571 && mouseY>100 && mouseY<196)
                {
                    if(playPile[0].getValue()==14 && matchPile[0].getValue()==2)
                    {
                        matchPile[0]=playPile[0];
                        if(d.numCardsLeft()>0) 
                            playPile[0]=d.deal();
                        else
                            playPile[0]=null;
                        clickCt=0; 
                        cardsLeft-=1;
                    }
                    if(playPile[0].getValue()==2 && matchPile[0].getValue()==14)
                    {
                        matchPile[0]=playPile[0];
                        if(d.numCardsLeft()>0) 
                            playPile[0]=d.deal();
                        else
                            playPile[0]=null;
                        clickCt=0; 
                        cardsLeft-=1;
                    }
                    if(playPile[0].getValue()==matchPile[0].getValue()+1 || playPile[0].getValue()==matchPile[0].getValue()-1)
                    {
                        matchPile[0]=playPile[0];
                        if(d.numCardsLeft()>0) 
                            playPile[0]=d.deal();
                        else
                            playPile[0]=null;
                        clickCt=0;
                        cardsLeft-=1;
                    }
                }
                if(mouseX>600 && mouseX< 671 && mouseY>100 && mouseY<196)
                {
                    if(playPile[0].getValue()==14 && matchPile[1].getValue()==2)
                    {
                        matchPile[1]=playPile[0];
                        if(d.numCardsLeft()>0) 
                            playPile[0]=d.deal();
                        else
                            playPile[0]=null;
                        clickCt=0;
                        cardsLeft-=1;
                    }
                    if(playPile[0].getValue()==2 && matchPile[1].getValue()==14)
                    {
                        matchPile[1]=playPile[0];
                        if(d.numCardsLeft()>0) 
                            playPile[0]=d.deal();
                        else
                            playPile[0]=null;
                        clickCt=0;
                        cardsLeft-=1;
                    }
                    if(playPile[0].getValue()==matchPile[1].getValue()+1 || playPile[0].getValue()==matchPile[1].getValue()-1)
                    {
                        matchPile[1]=playPile[0];
                        if(d.numCardsLeft()>0) 
                            playPile[0]=d.deal();
                        else
                            playPile[0]=null;
                        clickCt=0;
                        cardsLeft-=1;
                    }
                }
                if(mouseX>700 && mouseX< 771 && mouseY>100 && mouseY<196)
                {
                    if(playPile[0].getValue()==14 && matchPile[2].getValue()==2)
                    {
                        matchPile[2]=playPile[0];
                        if(d.numCardsLeft()>0) 
                            playPile[0]=d.deal();
                        else
                            playPile[0]=null;
                        clickCt=0;
                        cardsLeft-=1;
                    }
                    if(playPile[0].getValue()==2 && matchPile[2].getValue()==14)
                    {
                        matchPile[2]=playPile[0];
                        if(d.numCardsLeft()>0) 
                            playPile[0]=d.deal();
                        else
                            playPile[0]=null;
                        clickCt=0;
                        cardsLeft-=1;
                    }
                    if(playPile[0].getValue()==matchPile[2].getValue()+1 || playPile[0].getValue()==matchPile[2].getValue()-1)
                    {
                        matchPile[2]=playPile[0];
                        if(d.numCardsLeft()>0) 
                            playPile[0]=d.deal();
                        else
                            playPile[0]=null;
                        clickCt=0;
                        cardsLeft-=1;
                    }
                }
            }
        }

        if(card2)
        {
            if(clickCt==1)
            {
                if(mouseX>500 && mouseX< 571 && mouseY>100 && mouseY<196)
                {
                    if(playPile[1].getValue()==14 && matchPile[0].getValue()==2)
                    {
                        matchPile[0]=playPile[1];
                        if(d.numCardsLeft()>0) 
                            playPile[1]=d.deal();
                        else
                            playPile[1]=null;
                        clickCt=0;
                        cardsLeft-=1;
                    }
                    if(playPile[1].getValue()==2 && matchPile[0].getValue()==14)
                    {
                        matchPile[0]=playPile[1];
                        if(d.numCardsLeft()>0) 
                            playPile[1]=d.deal();
                        else
                            playPile[1]=null;
                        clickCt=0;
                        cardsLeft-=1;
                    }
                    if(playPile[1].getValue()==matchPile[0].getValue()+1 || playPile[1].getValue()==matchPile[0].getValue()-1)
                    {
                        matchPile[0]=playPile[1];
                        if(d.numCardsLeft()>0) 
                            playPile[1]=d.deal();
                        else
                            playPile[1]=null;
                        clickCt=0;
                        cardsLeft-=1;
                    }
                }
                if(mouseX>600 && mouseX< 671 && mouseY>100 && mouseY<196)
                {
                    if(playPile[1].getValue()==14 && matchPile[1].getValue()==2)
                    {
                        matchPile[1]=playPile[1];
                        if(d.numCardsLeft()>0) 
                            playPile[1]=d.deal();
                        else
                            playPile[1]=null;
                        clickCt=0;
                        cardsLeft-=1;
                    }
                    if(playPile[1].getValue()==2 && matchPile[1].getValue()==14)
                    {
                        matchPile[1]=playPile[1];
                        if(d.numCardsLeft()>0) 
                            playPile[1]=d.deal();
                        else
                            playPile[1]=null;
                        clickCt=0;
                        cardsLeft-=1;
                    }
                    if(playPile[1].getValue()==matchPile[1].getValue()+1 || playPile[1].getValue()==matchPile[1].getValue()-1)
                    {
                        matchPile[1]=playPile[1];
                        if(d.numCardsLeft()>0) 
                            playPile[1]=d.deal();
                        else
                            playPile[1]=null;
                        clickCt=0;
                        cardsLeft-=1;
                    }
                }
                if(mouseX>700 && mouseX< 771 && mouseY>100 && mouseY<196)
                {
                    if(playPile[1].getValue()==14 && matchPile[2].getValue()==2)
                    {
                        matchPile[2]=playPile[1];
                        if(d.numCardsLeft()>0) 
                            playPile[1]=d.deal();
                        else
                            playPile[1]=null;
                        clickCt=0;
                        cardsLeft-=1;
                    }
                    if(playPile[1].getValue()==2 && matchPile[2].getValue()==14)
                    {
                        matchPile[2]=playPile[1];
                        if(d.numCardsLeft()>0) 
                            playPile[1]=d.deal();
                        else
                            playPile[1]=null;
                        clickCt=0;
                        cardsLeft-=1;
                    }
                    if(playPile[1].getValue()==matchPile[2].getValue()+1 || playPile[1].getValue()==matchPile[2].getValue()-1)
                    {
                        matchPile[2]=playPile[1];
                        if(d.numCardsLeft()>0) 
                            playPile[1]=d.deal();
                        else
                            playPile[1]=null;
                        clickCt=0;
                        cardsLeft-=1;
                    }
                }
            }
        }

        if(card3)
        {
            if(clickCt==1)
            {
                if(mouseX>500 && mouseX< 571 && mouseY>100 && mouseY<196)
                {
                    if(playPile[2].getValue()==14 && matchPile[0].getValue()==2)
                    {
                        matchPile[0]=playPile[2];
                        if(d.numCardsLeft()>0) 
                            playPile[2]=d.deal();
                        else
                            playPile[2]=null;
                        clickCt=0;
                        cardsLeft-=1;
                    }
                    if(playPile[2].getValue()==2 && matchPile[0].getValue()==14)
                    {
                        matchPile[0]=playPile[2];
                        if(d.numCardsLeft()>0) 
                            playPile[2]=d.deal();
                        else
                            playPile[2]=null;
                        clickCt=0;
                        cardsLeft-=1;
                    }
                    if(playPile[2].getValue()==matchPile[0].getValue()+1 || playPile[2].getValue()==matchPile[0].getValue()-1)
                    {
                        matchPile[0]=playPile[2];
                        if(d.numCardsLeft()>0) 
                            playPile[2]=d.deal();
                        else
                            playPile[2]=null;
                        clickCt=0;
                        cardsLeft-=1;
                    }
                }
                if(mouseX>600 && mouseX< 671 && mouseY>100 && mouseY<196)
                {
                    if(playPile[2].getValue()==14 && matchPile[1].getValue()==2)
                    {
                        matchPile[1]=playPile[2];
                        if(d.numCardsLeft()>0) 
                            playPile[2]=d.deal();
                        else
                            playPile[2]=null;
                        clickCt=0;
                        cardsLeft-=1;
                    }
                    if(playPile[2].getValue()==2 && matchPile[1].getValue()==14)
                    {
                        matchPile[1]=playPile[2];
                        if(d.numCardsLeft()>0) 
                            playPile[2]=d.deal();
                        else
                            playPile[2]=null;
                        clickCt=0;
                        cardsLeft-=1;
                    }
                    if(playPile[2].getValue()==matchPile[1].getValue()+1 || playPile[2].getValue()==matchPile[1].getValue()-1)
                    {
                        matchPile[1]=playPile[2];
                        if(d.numCardsLeft()>0) 
                            playPile[2]=d.deal();
                        else
                            playPile[2]=null;
                        clickCt=0;
                        cardsLeft-=1;
                    }
                }
                if(mouseX>700 && mouseX< 771 && mouseY>100 && mouseY<196)
                {
                    if(playPile[2].getValue()==14 && matchPile[2].getValue()==2)
                    {
                        matchPile[2]=playPile[2];
                        if(d.numCardsLeft()>0) 
                            playPile[2]=d.deal();
                        else
                            playPile[2]=null;
                        clickCt=0;
                        cardsLeft-=1;
                    }
                    if(playPile[2].getValue()==2 && matchPile[2].getValue()==14)
                    {
                        matchPile[2]=playPile[2];
                        if(d.numCardsLeft()>0) 
                            playPile[2]=d.deal();
                        else
                            playPile[2]=null;
                        clickCt=0;
                        cardsLeft-=1;
                    }
                    if(playPile[2].getValue()==matchPile[2].getValue()+1 || playPile[2].getValue()==matchPile[2].getValue()-1)
                    {
                        matchPile[2]=playPile[2];
                        if(d.numCardsLeft()>0) 
                            playPile[2]=d.deal();
                        else
                            playPile[2]=null;
                        clickCt=0;
                        cardsLeft-=1;
                    }
                }
            }
        }

        repaint();
    }

    public void mouseExited(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

}