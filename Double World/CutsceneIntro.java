import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

/*Applet to make a cutscene of Double World
 * Amanda Ramos, Febuary 22nd, 2014
 */

public class CutsceneIntro extends Applet implements Runnable, KeyListener
{
    Thread main= new Thread(this);
    Font font= new Font( "Chiller", 1, 40);
    Color myBrown=new Color( 110, 70, 60 );
    Image Nightmare,piano;
    Image background1, background2;
    int TextBoxCt=0;
    Image buffer;
    Graphics bufferG;

    public void init()
    {
        this.setLayout(null);
        this.addKeyListener(this);
        resize(1300,700);

        Nightmare=this.getImage(this.getCodeBase(),"Nightma-Ra.png");
        piano=this.getImage(this.getCodeBase(),"organ piano thing.png");
        
        background1=this.getImage(this.getCodeBase(),"throne test 1.png");
        background2=this.getImage(this.getCodeBase(),"throne test.png");

        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();

        main.start();
    }

    public void run()
    {
        do
        {
            repaint();

            try
            {main.sleep(100);}
            catch(Exception e){}
        }
        while(true);
    }

    public void paint(Graphics g)
    {
        bufferG.setColor(Color. black);
        bufferG.fillRect(0,0,10000,10000);
        bufferG.drawImage(background1,0,0,1300,700,this);
        bufferG.setFont(font);
        bufferG.setColor(Color. red);
        if(TextBoxCt==0)
        {
            bufferG.drawString("Nightmares, who can stop them?", 100, 480);
            bufferG.drawString("No one,yes...", 100, 520);
            bufferG.drawString("Not a soul.", 100, 560);
        }
        if(TextBoxCt==1)
        {
            bufferG.drawString("I am the Master of Fear and Horror.",100,480);
            bufferG.drawString("I am the cause of Nightmares.",100,520);
            bufferG.drawString("Believe me, I love your screams, so wonderful to hear.",100,560);
        }
        if(TextBoxCt==2)
        {
            bufferG.drawString("If you look for me, you won't find me.",100,480);
            bufferG.drawString("I am everywhere and nowhere.",100,520);
        }
        if(TextBoxCt==3)
        {
            bufferG.drawString("I AM...",100,480);
            bufferG.drawString("AUGH! STEVE I TOLD YOU TO HOLD THE CAMERA STILL!!",100,520);
            bufferG.drawString("HOW AM I SUPPOSSED TO BE EVIL WITHOUT A PROPER INTRO?!?",100,560);
        }
        if(TextBoxCt==4)
        {
            bufferG.drawImage(background2,0,0,1300,700,this);
            //bufferG.drawImage(Nightmare, 500,125,265,300,this);
            //bufferG.drawImage(piano, 800,5,350,400,this);
            bufferG.setColor(Color.red);
            bufferG.drawString("*Ahem*, yes, hello. I'm Nightma-Ra.",100,480);
            bufferG.drawString("Master of Nightmares and the Terror of Dreams.",100,520);
            bufferG.drawString("Welcome to the Double World Demo.",100,560);
        }
        if(TextBoxCt==5)
        {
            bufferG.drawImage(background2,0,0,1300,700,this);
            bufferG.drawImage(Nightmare, 500,125,265,300,this);
            bufferG.drawImage(piano, 800,5,350,400,this);
            bufferG.setColor(Color. red);
            bufferG.drawString("To Start, press the optional buttons of the screen.",100,480);
            bufferG.drawString("Have fun!", 100,520);
        }
        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void keyReleased(KeyEvent e){}    

    public void keyTyped(KeyEvent e){}

    public void keyPressed(KeyEvent e)
    {
        int code=e.getKeyCode();
        if(code==e.VK_R)
        {
            TextBoxCt++;
        }
    }
}