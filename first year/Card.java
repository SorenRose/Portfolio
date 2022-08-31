import java.awt.*;
import java.applet.Applet;
/* Applet to make cards
 * Amanda Ramos, Febuary 19th, 2014
 * pictures gotten from oxymoron.com
 */
public class Card
{
    private int suit; //0=clubs, 1=diamonds, 2=hearts, 3=spades
    private int value; //2-14, 11=Jack, 12=Queen, 13=King, 14=Ace

    public Card()
    {
        suit=2;
        value=12;
    }
    
    public Image getImage(Applet a)
    {
        return a.getImage(a.getCodeBase(),"c"+(suit*13+value)+".gif");
    }
    
    public Card(int inSuit, int inValue)
    {
        setSuit(inSuit);
        setValue(inValue);
    }

    public void setSuit(int inSuit)
    {
        if(inSuit>=0 && inSuit<=3)
            suit=inSuit;
        else
            suit=0;
    }

    public void setValue(int inValue)
    {
        if(inValue>=2 && inValue<=14)
            value=inValue;
        else
            value=2;
    }

    public int getSuit()
    {
        return suit;
    }

    public int getValue()
    {
        return value;
    }

    public int blackJackValue()
    {
        if(value==14)
        return 11;
        else if(value>=10)
        return 10;
        else
        return value;
    }
    
    public String toString()
    {
        String[]suitNames={"Clubs","Diamonds","Hearts","Spades"};
        String[]valueNames={"Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King","Ace"};
        return valueNames[value-2]+ " of " +suitNames[suit];
    }
}