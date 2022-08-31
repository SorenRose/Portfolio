// WARNING: This file is auto-generated and any changes to it will be overwritten
import lang.stride.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import java.util.Random;

/**
 * 
 */
public class SpaceInvaders extends Applet implements Runnable, KeyListener
{
    /* Simple Applet to make a game of Space Inavaders Amanda Ramos, January 28th, 2014*/
    protected Thread main =  new  Thread(this);
    protected Random rand =  new  Random();
    protected Font font =  new  Font("Century", 1, 20);
    protected Color myBrown =  new  Color(110, 70, 60);
    protected int aliensX =  new  int[4][6];
    protected int aliensY =  new  int[4][6];
    protected int xChange = 10;
    protected int yChangeCt = 1;
    protected int xShip = 500;
    protected Image buffer;
    protected Graphics bufferG;

    /**
     * 
     */
    public void init()
    {
        this.setLayout(null);
        this.addKeyListener(this);
        resize(1300, 700);
        for (int r = 0;r <= 3;r++) {
            for (int c = 0;c <= 5;c++) {
                aliensX[r][c] = 200 + 60 * c;
                aliensY[r][c] = 50 + 50 * r;
            }
        }
        buffer = createImage(this.getWidth(), this.getHeight());
        bufferG = buffer.getGraphics();
        main.start();
    }

    /**
     * 
     */
    public void run()
    {
        repaint();
        for (int r = 0;r <= 3;r++) {
            for (int c = 0;c <= 5;c++) {
                aliensX[r][c] = aliensX[r][c] + xChange;
                if (aliensX[r][c] == 1000) {
                    yChangeCt = yChangeCt + 1;
                    aliensX[r][c] = 200;
                    aliensY[r][c] = aliensY[r][c] = 50 + 50 * r;
                }
            }
        }
        try {
            main.sleep(100);
        }
        catch (Exception e) {
        }
    }

    /**
     * 
     */
    public void paint(Graphics g)
    {
        bufferG.setColor(Color.black);
        bufferG.fillRect(0, 0, 10000, 10000);
        bufferG.setFont(font);
        bufferG.setColor(Color.green);
        for (int r = 0;r <= 3;r++) {
            for (int c = 0;c <= 5;c++) {
                bufferG.fillRect(aliensX[r][c], aliensY[r][c], 40, 40);
            }
        }
        bufferG.setColor(Color.white);
        bufferG.fillRect(xShip, 550, 60, 40);
        g.drawImage(buffer, 0, 0, this);
    }

    /**
     * 
     */
    public void update(Graphics g)
    {
        paint(g);
    }

    /**
     * 
     */
    public void keyReleased(KeyEvent e)
    {
    }

    /**
     * 
     */
    public void keyTyped(KeyEvent e)
    {
    }

    /**
     * 
     */
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();
        if (code == e.VK_RIGHT) {
            xShip = xShip + 20;
        }
        if (code == e.VK_LEFT) {
            xShip = xShip - 20;
        }
    }
}
