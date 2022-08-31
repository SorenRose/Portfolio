/* Applet to make write our own objects
 * Amanda Ramos, Febuary 7th, 2014
 */
public class Box
{
    private double L,W,H;

    public Box()
    {
        L=6;W=9;H=13;
    }
    
    public void setH(double inH)
    {
        if(inH>0)
        H=inH;
        else
        H=1;
    }
    
    public void setL(double inL)
    {
        if(inL>0)
        L=inL;
        else
        L=1;
    }
    
    public void setW(double inW)
    {
       if(inW>0)
        W=inW;
        else
        W=1;
    }
    
    public double getH()
    {
        return H;
    }
    
    public double getL()
    {
        return L;
    }
    
    public double getW()
    {
        return W;
    }
}