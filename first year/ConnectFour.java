import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

/*Simplet Applet to make a game of Connect Four
 * Amanda Ramos, November 5th, 2013
 */

public class ConnectFour extends Applet implements MouseListener, ActionListener
{
    Font font= new Font( "FangSong", 1, 20);    
    private int x=100, y=100;
    int connect[][]=new int[6][7];
    int wins[]= new int[2];
    int player=1;
    Button restartBtn= new Button("Restart");
    Button undoBtn= new Button("Undo");
    int winner=0;
    int numMoves=0;
    Image buffer;
    Graphics bufferG;
    Color myBlue=new Color( 20, 170, 250 );

    public void init()
    {
        addMouseListener(this);
        this.setLayout(null);
        resize(1300,700);
        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();

        restartBtn.setBounds(800,550,60,40);
        this.add(restartBtn);
        restartBtn.addActionListener(this);

        /*undoBtn.setBounds(900,550,60,40);
        this.add(undoBtn);
        undoBtn.addActionListener(this);*/

    }

    public int checkColumnWin(int row, int col)
    {
        if(connect[row][col]==connect[row+1][col]&&
        connect[row+1][col]==connect[row+2][col]&&
        connect[row+2][col]==connect[row+3][col]&&
        connect[row][col]!=0)
            return connect[row][col];
        return 0;
    }

    public int checkRowWin(int row, int col)
    {
        if(connect[row][col]==connect[row][col+1]&&
        connect[row][col+1]==connect[row][col+2]&&
        connect[row][col+2]==connect[row][col+3]&&
        connect[row][col]!=0)
            return connect[row][col];
        return 0;
    }

    public int checkDiaRightWin(int row, int col)
    {
        if(connect[row][col]==connect[row-1][col+1]&&
        connect[row-1][col+1]==connect[row-2][col+2]&&
        connect[row-2][col+2]==connect[row-3][col+3]&&
        connect[row][col]!=0)
            return connect[row][col];
        return 0;
    }

    public int checkDiaLeftWin(int row, int col)
    {
        if(connect[row][col]==connect[row+1][col+1]&&
        connect[row+1][col+1]==connect[row+2][col+2]&&
        connect[row+2][col+2]==connect[row+3][col+3]&&
        connect[row][col]!=0)
            return connect[row][col];
        return 0;
    }

    public void actionPerformed(ActionEvent e)
    {
        if( e.getSource()==restartBtn)
        {
            for(int r=0;r<6;r++)
                for(int c=0;c<7;c++)
                    connect[r][c]=0;
            winner=0;
            player=1;
            numMoves=0;
        }
        if( e.getSource()==undoBtn)
        {
            
        }
        repaint();
    }

    public void paint(Graphics g)
    {
        bufferG.setColor(Color. red);
        bufferG.fillRect(0,0,10000,10000);
        bufferG.setFont(font);
        bufferG.setColor(Color. black);
        for(int r=0;r<6;r++)
            for(int c=0;c<7;c++)
            {
                if(connect[r][c]==1)
                    bufferG.setColor(Color. yellow);
                else if(connect[r][c]==2)
                    bufferG.setColor(Color. blue);
                else
                    bufferG.setColor(Color.black);
                bufferG.fillOval(100*c,100*r,98,98);                
        }
        bufferG.setColor(Color.black);
        if(winner==0 &&numMoves<42)
            bufferG.drawString("Player "+ player+"'s turn",800,100);
        if(winner==0&&numMoves==42)
            bufferG.drawString("Tie",800,100);    
        if(winner>0)
            bufferG.drawString("Player "+winner+" wins!",800,100);
        bufferG.drawString("Wins", 845,300);
        bufferG.drawString("Player 1", 765,350);
        bufferG.drawString("Player 2", 895,350);
        for(int w=0;w<2;w++)
            bufferG.drawString(""+wins[w], 800+130*w,400);
        if (winner==1)
            wins[0]++;
        else if(winner==2)
            wins[1]++;
        g.drawImage(buffer,0,0,this);
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    public void mouseClicked(MouseEvent e)
    {
        if(winner==0)
        {
            int row=e.getY()/100;
            int col=e.getX()/100;
            boolean found=false;

            if(player==1)
            {
                for(int r=5;r>=0 && found==false;r--)
                {
                    if(connect[r][col]==0)
                    {
                        connect[r][col]=1;
                        found=true;
                        player=2;
                        numMoves++;
                    }
                }
            }
            else if(player==2)
            {
                for(int r=5;r>=0 && found==false;r--)
                {
                    if(connect[r][col]==0)
                    {
                        connect[r][col]=2;
                        found=true;
                        player=1;
                        numMoves++;
                    }
                }
            }
            repaint();

            for(int r=0;r<=2 &&winner==0;r++)
                for(int c=0;c<=6 &&winner==0;c++)
                    winner=checkColumnWin(r,c);

            for(int r=0;r<=5 &&winner==0;r++)
                for(int c=0;c<=3 &&winner==0;c++)
                    winner=checkRowWin(r,c);

            for(int r=3;r<=5 &&winner==0;r++)
                for(int c=0;c<=3 &&winner==0;c++)
                    winner=checkDiaRightWin(r,c);

            for(int r=0;r<=5 &&winner==0;r++)
                for(int c=0;c<=3 &&winner==0;c++)
                    winner=checkDiaLeftWin(r,c);
            repaint();
        }
    }

    public void mouseExited(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}
}