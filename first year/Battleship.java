import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

/*Simplet Applet to make a game to create Battleship
 * Amanda Ramos and Conor Lilley, December 3rd, 2013
 */

public class Battleship extends Applet implements ActionListener
{
    Font font= new Font( "Rockwell", 1, 17);
    Button shipBtn[][]=new Button[10][10];
    Button startBtn= new Button("Start");
    int boats[][]=new int [10][10];
    int carrierCt=5, destroyerCt=4, battleshipCt=3, submarineCt=3, patrolCt=2,hitCt, pageCt;
    String output="";
    Image ocean, buffer;
    Graphics bufferG;

    public void init()
    {
        this.setLayout(null);
        resize(1300,700);
        buffer=createImage(this.getWidth(),this.getHeight());
        bufferG=buffer.getGraphics();

        ocean=this.getImage(this.getCodeBase(),"ocean pic.jpg");
        //taken from Bing Images, search: ocean picture

        startBtn.setBounds(750,600,60,40);
        this.add(startBtn);
        startBtn.addActionListener(this);

        for(int r=0; r<10; r++)
            for(int c=0; c<10; c++)
            { 
                shipBtn[r][c]=new Button();
                shipBtn[r][c].setBounds(300+65*c,30+65*r,60,60);
                shipBtn[r][c].addActionListener(this);
                this.add(shipBtn[r][c]);
                shipBtn[r][c].setVisible(false);
        }
        for(int r=7; r<9; r++)
            for(int c=8;c<9;c++)
                boats[r][c]=1;
        for(int r=1; r<4; r++)
            for(int c=9;c<=9;c++)
                boats[r][c]=2;
        for(int r=6; r<7; r++)
            for(int c=2;c<5;c++)
                boats[r][c]=3;
        for(int r=1; r<5; r++)
            for(int c=1;c<2;c++)
                boats[r][c]=4;
        for(int r=4; r<5; r++)
            for(int c=3;c<8;c++)
                boats[r][c]=5;
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==startBtn)
        {
            pageCt=1;
            startBtn.setVisible(false);
            for(int r=0; r<10; r++)
                for(int c=0; c<10; c++)
                    shipBtn[r][c].setVisible(true);
        }
        for(int r=0; r<10; r++)
            for(int c=0; c<10; c++)
                if(e.getSource()==shipBtn[r][c])
                {
                    hitCt++;
                    shipBtn[r][c].setEnabled(false);
                    if(boats[r][c]==0)
                    {
                        shipBtn[r][c].setBackground(Color. black);
                        output="You missed";
                    }
                    if(boats[r][c]==1)
                    {
                        patrolCt--;
                        shipBtn[r][c].setBackground(Color. red);
                        if(patrolCt>0)
                            output="You hit the patrol boat";
                        if(patrolCt==0)
                            output="You sunk the patrol boat";
                    }
                    if(boats[r][c]==2)
                    {
                        submarineCt--;
                        shipBtn[r][c].setBackground(Color. red);
                        if(submarineCt>0)
                            output="You hit the submarine";
                        if(submarineCt==0)
                            output="You sunk the submarine";
                    }
                    if(boats[r][c]==3)
                    {
                        battleshipCt--;
                        shipBtn[r][c].setBackground(Color. red);
                        if(battleshipCt>0)
                            output="You hit the battleship";
                        if(battleshipCt==0)
                            output="You sunk the battleship";
                    }
                    if(boats[r][c]==4)
                    {
                        destroyerCt--;
                        shipBtn[r][c].setBackground(Color. red);
                        if(destroyerCt>0)
                            output="You hit the destroyer";
                        if(destroyerCt==0)
                            output="You sunk the destroyer";
                    }
                    if(boats[r][c]==5)
                    {
                        carrierCt--;
                        shipBtn[r][c].setBackground(Color. red);
                        if(carrierCt>0)
                            output="You hit the aircraft carrier";;
                        if(carrierCt==0)
                            output="You sunk the aircraft carrier";
                    }
        }
        if(patrolCt==0 && battleshipCt==0 && carrierCt==0 && destroyerCt==0 && submarineCt==0)
        {
            output="You sunk all the ships!";
            for(int r=0; r<10; r++)
                for(int c=0; c<10; c++)
                    shipBtn[r][c].setEnabled(false);
        }
        repaint();
    }
    public void paint(Graphics g)
    {
        bufferG.drawImage(ocean, 0,0,1300,700, this);
        bufferG.setFont(font);
        bufferG.setColor(Color.black);
        if(pageCt==0)
        {
            bufferG.drawString("BattleShip", 550, 50);
            bufferG.drawString("Welcome to BattleShip! To play you must click the squares to find the hidden ships.",100, 100);
            bufferG.drawString("There are 5 ships total hidden across the board,the Aircraft Carrier, Destroyer, Battleship, Submarine, and Patrol Boat.",100,150);
            bufferG.drawString("If the square you clicked turns black, you missed a ship. If it turns red, you hit a ship.",100,200);
            bufferG.drawString("The total ammount of clicks you make appears on the right of the screen: this is your score.",100,250);
            bufferG.drawString("In the top right corner, all five ships are listed and the number of shots needed to sink them are written next to them.",100,300);
            bufferG.drawString("The game ends when all five ships have been sunk. Have fun and good luck!",100,350);
        }
        if(pageCt==1)
        {
            bufferG.drawString("Aircraft carrier: "+carrierCt,1000,50);
            bufferG.drawString("Destroyer: "+destroyerCt,1000,75);
            bufferG.drawString("Battleship: "+battleshipCt,1000,100);
            bufferG.drawString("Submarine: "+submarineCt,1000,125);
            bufferG.drawString("Patrol Boat: "+patrolCt,1000,150);
            bufferG.drawString(output, 1000, 200);
            bufferG.drawString("Total shots: "+hitCt, 1000, 225);
        }
        g.drawImage(buffer,0,0,this);
    }
    public void update(Graphics g)
    {
        paint(g);
    }
}