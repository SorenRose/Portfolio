/* Applet to make an IQ
 * Amanda Ramos, Conor Lilley Febuary 21th, 2014
 */
public class IQ
{
    private int IQ;
    private boolean isGenius;

    public IQ()
    {
        IQ=96;
        isGenius=false;
    }

    public IQ(int inIQ)
    {
        setIQ(inIQ);
    }

    public void setIQ(int inIQ)
    {
        if(inIQ>=0 && inIQ<=250)
            IQ=inIQ;
        else
            IQ=0;
    }

    public int getIQ()
    {

        return IQ;
    }

    public boolean isGenius()
    {
        return isGenius;
    }

    public String toString()
    {
        String yourIQ="IQ equals "+IQ;

        if(160<=IQ)
        { 
            isGenius=true;
            yourIQ+=" You can truly be a genius";
        }
        else
        {
            isGenius=false;
            yourIQ+=" Not an Einstein";
        }
        return yourIQ;
    }
}