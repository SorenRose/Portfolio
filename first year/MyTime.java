/* Applet to make a clock
 * Amanda Ramos, Febuary 10th, 2014
 */
public class MyTime
{
    private int hour,min,sec;
    private boolean AM;

    public MyTime()
    {
        hour=11;min=55;sec=0;AM=true;
    }
    
    public MyTime(int inHour, int inMin, int inSec)
    {
        setHour(inHour);
        setMin(inMin);
        setSec(inSec);
    }

    public void setHour(int inHour)
    {
        if(inHour>0 && inHour<13)
            hour=inHour;
        else
            hour=12;
    }

    public void setMin(int inMin)
    {
        if(inMin>-1 && inMin<60)
            min=inMin;
        else
            min=1;
    }

    public void setSec(int inSec)
    {
        if(inSec>-1 && inSec<60)
            sec=inSec;
        else
            sec=1;
    }
    
    public int getHour()
    {
        return hour;
    }

    public int getMin()
    {
        return min;
    }

    public int getSec()
    {
        return sec;
    }

    public void addHour()
    {
        hour++;
        if(hour==13)
        hour=1;
        if(hour==12)
        AM=!AM;
    }
    
    public void addMin()
    {
        min++;
        if(min==60)
        {
            min=0;
            addHour();
        }
    }
    
    public void addSec()
    {
        sec++;
        if(sec==60)
        {
            sec=0;
            addMin();
        }
    }

    public String toString()
    {
        String output= hour+":";
        if(min<10)
            output+="0";
        output+=min+":";
        if(sec<10)
            output+="0";
        output+=sec;
        if(AM)
            output+=" AM";
        else
            output+=" PM";
        return output;
    }
}