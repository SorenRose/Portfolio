 import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

/*An applet to make a team of Baseball players
 * Amanda Ramos April 14th, 2014
 */

public class  BaseBallTeam extends Applet
{   
    BaseballPlayer team[]=new BaseballPlayer[9];
    Image buffer;
    Graphics bufferG;

    public void init()
    {
        resize(1300,700);
        
        team[0]="Rob Lucci","Pitcher",0,0,0;
        team[1]="Kaku","First Base",0,0,0;
        team[2]="Spandam","Third Base",0,0,0;
        team[3]="Jabra","outfield",0,0,0;
        team[4]="Fukidori","catcher",0,0,0;
        team[5]="Chapapa","second base",0,0,0;
        team[6]="Coby","outfield",0,0,0;
        team[7]="Rob Lucci","outfield",0,0,0;
        team[8]="Rob Lucci","shortstop",0,0,0;

        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();
    }

    public void paint (Graphics g)
    {
        bufferG.setColor(Color. white);
        bufferG.fillRect(0,0,10000,10000);
        bufferG.setColor(Color. red);
        bufferG.drawString(b.toString(),10,10);
        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

}