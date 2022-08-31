import java.awt.*;
import java.applet.Applet;
import java.util.Random;

/*Simplet Applet to output stuff using loops
 * Amanda Ramos September 9th, 2013
 */

public class loopMadness extends Applet
{
    Image myPic;
    Random rand= new Random();
    
    public void init()
    {
        myPic=this.getImage(this.getCodeBase(),"paper yoshi.png");
    }
    
    public void paint (Graphics g)
    {
        g.setColor( Color. blue);
        g.drawString("Check out this picture!", 500, 30);
        
        /*for(int num=1; num<=5; num=num+1)
        {
           g.drawImage(myPic, 100, 100+100*num, 50, 50, this);
        }*/
        /*for(int num=1; num<=11; num=num+1)
        {
           g.drawImage(myPic, 0+100*num, 100, 50, 50, this);
        }*/
        for(int num=1; num<=100; num=num+1)
        {
           g.drawImage(myPic, rand.nextInt(1200), rand.nextInt(700), 50, 50, this);
        }
        /*for(int num=1; num<=5; num=num+1)
        {
           g.drawImage(myPic, 1100, 100+100*num, 50, 50, this);
        }*/
        repaint();
    }
}