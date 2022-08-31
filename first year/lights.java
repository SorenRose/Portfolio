import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

/*Simplet Applet to make a game of LIGHTS
 * Amanda Ramos, October 31th, 2013
 * HAPPY HALLOWEEN!!!!!
 */

public class lights extends Applet implements MouseListener, ActionListener
{
    private int x=100, y=100;
    boolean lights[][]=new boolean[7][7];
    Button restartBtn= new Button("Restart");
    String history="";
    Image buffer;
    Graphics bufferG;

    public void init()
    {
        addMouseListener(this);
        this.setLayout(null);
        resize(1300,700);
        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();

        restartBtn.setBounds(750,600,60,40);
        this.add(restartBtn);
        restartBtn.addActionListener(this);

        /*
         * H
         *A
         * L 
         *L
         * O 
         *W 
         * E
         *E
         * N
         */
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if( e.getSource()==restartBtn)
        {
            for(int r=1;r<6;r++)
                for(int c=1;c<6;c++)
                {
                    lights[r][c]=false;
            }
            history="";
        }
        repaint();
    }

    public void paint(Graphics g)
    {
        bufferG.setColor(Color. red);
        bufferG.fillRect(0,0,10000,10000);
        for(int r=1;r<6;r++)
            for(int c=1;c<6;c++)
            {
                if(lights[r][c]==true)
                    bufferG.setColor(Color. yellow);
                else
                    bufferG.setColor(Color. black);
                bufferG.fillRect(100*c,100*r,98,98);
        }
        bufferG.setColor(Color. green);
        bufferG.drawString(""+history, 50,50);
        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void mouseClicked(MouseEvent e)
    {
        int row=e.getY()/100;
        int col=e.getX()/100;
        lights[row][col]=!lights[row][col];
        lights[row+1][col]=!lights[row+1][col];
        lights[row-1][col]=!lights[row-1][col];
        lights[row][col+1]=!lights[row][col+1];
        lights[row][col-1]=!lights[row][col-1];
        history+="("+row+","+col+")"+" ";
        repaint();
    }

    public void mouseExited(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}
}