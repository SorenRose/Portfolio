import java.awt.*;
/* Object to record baseball player information
 * Amanda Ramos, April 14th, 2014
 */
public class BaseballPlayer
{
    private String name;
    private String position;
    private int hits;
    private int atBats;
    private int sacrifices;
    
     public BaseballPlayer()
    {
        name="Tom";
        position="outfield";
        hits=10;
        atBats=25;
        sacrifices=2;
    }

    public BaseballPlayer(String inName, String inPosition, int inHits, int inAtBats, int inSacrifices)
    {
        name=inName;
        position=inPosition;
        hits=inHits;
        atBats=inAtBats;
        sacrifices=inSacrifices;
    }

    public BaseballPlayer(String inName, String inPosition)
    {
        name=inName;
        position=inPosition;
    }
    
    public void setName(String inName)
    {
        name=inName;
    }
    
    public void setPosition(String inPosition)
    {
        position=inPosition;
    }
    
    public void setHits(int inHits)
    {
        if(inHits>0)
        hits=inHits;
        else
        hits=0;
    }
    
    public void setAtBats(int inAtBats)
    {
        if(inAtBats>0)
        atBats=inAtBats;
        else
        atBats=0;
    }
    
    public void setSacrifices(int inSacrifices)
    {
        if(inSacrifices>0)
        sacrifices=inSacrifices;
        else
        sacrifices=0;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getPosition()
    {
        return position;
    }

    public int getHits()
    {
        return hits;
    }
    
    public int getAtBats()
    {
        return atBats;
    }
    
    public int getNetAtBats()
    {
        return atBats-sacrifices;
    }

    public int getSacrifice()
    {
        return sacrifices;
    }
    
    public double getBattingAverage()
    {
        return hits/getNetAtBats();
    }
    
     public String toString()
    {
        String output= name+" "+position+" ";
        if(hits>0)
            output+=hits;
            output+=" ";
        if(atBats>0)
            output+=atBats;
            output+=" ";
        if(sacrifices>0)
            output+=sacrifices;
        return output;
    }
}