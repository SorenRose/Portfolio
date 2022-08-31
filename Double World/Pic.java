import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.applet.Applet;

/*Applet to work with the Alyssa's picture of Nightma-Ra
 * Amanda Ramos, March 4th, 2014
 */

public class Pic extends Applet
{
    Font font= new Font( "Chiller", 1, 40);
    Image Nightmare;
    Image buffer;
    Graphics bufferG;

    public void init()
    {
        this.setLayout(null);
        resize(1300,700);

        Nightmare=this.getImage(this.getCodeBase(),"nightmare.jpg");

        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();

    }

    public void paint(Graphics g)
    {
        bufferG.setColor(Color. black);
        bufferG.fillRect(0,0,10000,10000);
        bufferG.setFont(font);
        
        //bufferG.drawImage(Nightmare,10,10,1432,2268,this);
        //bufferG.drawImage(Nightmare,10,10,716,1134,this);
        //bufferG.drawImage(Nightmare,10,10,358,557,this);
        //bufferG.drawImage(Nightmare,10,10,477,756,this);
        //bufferG.drawImage(Nightmare,10,10,409,648,this);
        bufferG.drawImage(Nightmare,10,10,397,630,this);
        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

}