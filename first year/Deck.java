import java.util.Random;
/* Applet to make a deck of card
 * Amanda Ramos, Febuary 24th, 2014
 */
public class Deck
{
    private Card[] cards=new Card[52];
    private int top;

    public Deck()
    {
        top=-1;
        int place=0;

        for(int s=0;s<4;s++)
            for(int v=2;v<15;v++)
            {
                cards[place]=new Card (s,v);
                place++;
        }
    }

    public int numCardsLeft()
    {
        return 51-top;
    }
    
    public Card deal()
    {
        top++;
        if(top<=51)
            return cards[top];
        else
            return null;
    }
    
    public void shuffle()
    {
        top=-1;
        Random rand= new Random();
        for(int i=0; i<52; i++)
        {
            int randPlace=rand.nextInt(52);
            Card temp=cards[randPlace];
            cards[randPlace]=cards[i];
            cards[i]=temp;
        }
    }
}