import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

/*Simplet Applet to make a game to create Survivor
 * Amanda Ramos, November 22th, 2013
 */

public class Survivor extends Applet implements MouseListener, ActionListener
{
    private int x=100, y=100;
    Font font= new Font( "FangSong", 1, 20);  
    int chart[][]=new int [10][10];
    Color myPurple=new Color( 210, 1, 210 );
    int player=1;
    int p1Row=4;
    int p1Col=1;
    int p2Row=5;
    int p2Col=8;
    int p3Row=1;
    int p3Col=5;
    int p4Row=8;
    int p4Col=4;
    Image buffer;
    Graphics bufferG;

    public void init()
    {
        addMouseListener(this);
        this.setLayout(null);
        resize(1300,700);
        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();

        for(int r=0;r<10;r++)
            for(int c=0;c<10;c++)
            {
                if(r==0 || r==9 || c==0 ||c==9)
                    chart[r][c]=9;
        }
        
        chart[4][1]=1;
        chart[5][8]=2;
        chart[1][5]=3;
        chart[8][4]=4;

    }

    public boolean hasMove(int row, int col)
    {
        if(chart[row-1][col-1]==0 || chart[row-1][col]==0 || chart[row-1][col+1]==0 ||chart[row][col-1]==0 ||
        chart [row][col+1]==0 || chart[row+1][col-1]==0 || chart[row+1][col]==0 || chart[row+1][col+1]==0)
            return true;
        else
            return false;
    }

    public void actionPerformed(ActionEvent e)
    {

        repaint();
    }

    public void paint(Graphics g)
    {
        bufferG.setColor(Color. black);
        bufferG.fillRect(0,0,10000,10000);
        bufferG.setFont(font);
        for(int r=0;r<10;r++)
            for(int c=0;c<10;c++)
            {
                if(chart[r][c]==0)    
                    bufferG.setColor(Color. white);    
                if(chart[r][c]==9)    
                    bufferG.setColor(myPurple);
                if(chart[r][c]==1)    
                    bufferG.setColor(Color. red);
                if(chart[r][c]==2)    
                    bufferG.setColor(Color. blue);
                if(chart[r][c]==3)    
                    bufferG.setColor(Color. green);
                if(chart[r][c]==4)
                    bufferG.setColor(Color. yellow);
                    
                bufferG.drawString("Player " +player+" is up!", 900, 200);
                
                bufferG.fillRect(20+65*c,10+65*r,64,64);
        }
        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void mouseClicked(MouseEvent e)
    {
        int row=(e.getY()-10)/65;
        int col=(e.getX()-20)/65;
        
        
        if(player==1 && chart[row][col]==0 && row>=p1Row-1 && row<=p1Row+1 && col>=p1Col-1 && col<=p1Col+1)
        {
            chart[row][col]=1;
            chart[p1Row][p1Col]=9;
            p1Row=row;
            p1Col=col;
            player=2;
        }
        else if(player==2 && chart[row][col]==0 && row>=p2Row-1 && row<=p2Row+1 && col>=p2Col-1 && col<=p2Col+1)
        {
            chart[row][col]=2;
            chart[p2Row][p2Col]=9;
            p2Row=row;
            p2Col=col;
            player=3;
        }
        else if(player==3 && chart[row][col]==0 && row>=p3Row-1 && row<=p3Row+1 && col>=p3Col-1 && col<=p3Col+1)
        {
            chart[row][col]=3;
            chart[p3Row][p3Col]=9;
            p3Row=row;
            p3Col=col;
            player=4;
        }
        else if(player==4 && chart[row][col]==0 && row>=p4Row-1 && row<=p4Row+1 && col>=p4Col-1 && col<=p4Col+1)
        {
            chart[row][col]=4;
            chart[p4Row][p4Col]=9;
            p4Row=row;
            p4Col=col;
            player=1;
        }

        if(player==1 && hasMove(p1Row,p1Col)==false)
        {
            player=2;
            chart[p1Row][p1Col]=9;
        }
        if(player==2 && hasMove(p2Row,p2Col)==false)
            {
            player=3;
            chart[p2Row][p2Col]=9;
        }
        if(player==3 && hasMove(p3Row,p3Col)==false)
           {
            player=4;
            chart[p3Row][p3Col]=9;
        }
        if(player==4 && hasMove(p4Row,p4Col)==false)
            {
            player=1;
            chart[p4Row][p4Col]=9;
        }
        if(player==1 && hasMove(p1Row,p1Col)==false)
            {
            player=2;
            chart[p1Row][p1Col]=9;
        }
        

        repaint();
    }

    public void mouseExited(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}
}