import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.applet.AppletStub;
import java.applet.*;
import java.util.*;
/*Applet to make a game
 * Amanda Ramos, April 27th, 2015
 */

public class testCutscene extends Applet implements KeyListener
{
    Font font= new Font( "Chiller", 1, 40);
    Font introFont= new Font("Segoe UI Blpockyack", 1, 28);
    int hit=0;
    cut home;
    //ArrayList theme=new ArrayList<AudioClip>();
    //ArrayList person=new ArrayList<Image>();
    Image[] person=new Image[4];
    //Image[] person;
    //AudioClip[] theme;
    AudioClip[] theme=new AudioClip[4];
    //Image person;
    //AudioClip theme;
    Image buffer;
    Graphics bufferG;

    public void appletResize(int width, int length)
    {
        resize( 1300, 700 );  
    }

    public void init()
    {
        this.setLayout(null);
        this.addKeyListener(this);
        resize(1300,700);
        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();

        home=new cut (20, person,theme,0,true);
        theme[0]=this.getAudioClip(this.getCodeBase(),"Disappearance.wav");
        theme[1]=this.getAudioClip(this.getCodeBase(),"Prelude.wav");
        theme[2]=this.getAudioClip(this.getCodeBase(),"Shousou.wav");
        theme[3]=this.getAudioClip(this.getCodeBase(),"puppet.wav");
        //home.setAudio(theme);
        person[0]=this.getImage(this.getCodeBase(),"pocky.png");
        person[1]=this.getImage(this.getCodeBase(),"home3.png");
        person[2]=this.getImage(this.getCodeBase(),"pocky.png");
        person[3]=this.getImage(this.getCodeBase(),"home3.png");

        if(home.getOn()==true)
        home.getAudio().play();
        //home.setImage(person);

    }

    public void paint(Graphics g)
    {
        bufferG.setColor(Color. black);
        bufferG.fillRect(0,0,10000,10000);
        bufferG.setFont(font);
        bufferG.setColor(Color. red);
        bufferG.drawString(""+hit,100,100);
        bufferG.drawString(""+home.getOn(),400,200);
        
            bufferG.drawString(""+home.getX(),400,100);
            if(home.getOn()==true)
        bufferG.drawImage(home.getImage(),500,300,this);
        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void keyTyped(KeyEvent e){}

    public void keyReleased(KeyEvent e){}

    public void keyPressed(KeyEvent e)
    {
        int code=e.getKeyCode();
        if(code==e.VK_Y)
        {

            if(home.getX()>=home.getSize()-1)
            {
                home.setOn(false);
            }
            else 
                home.nextScene();
        }
        if(code==e.VK_SPACE)
        {
            hit++;
        }
        repaint();
    }
}