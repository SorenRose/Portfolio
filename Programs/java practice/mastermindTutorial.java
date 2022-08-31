import java.until.Random;
import java.awt.*;
import java.applet.Applet;
import java.awt.event;
/*A simple applet to make a tutorual for MasterMind)
By: Amanda Ramos Oct 26, 2012
 */

public class mastermindTutorial extends Applet implements ActionListener
{
    Button nextBtn= new Button("next");
    Button backBtn= new Button("back");
    Button endBtn= new Button("end");
    Font myFont= new Font("Papyrus", 1, 27);
    
    int pageCt=0;
    
    public void init()
    {
        this.setLayout(null);
        
        nextBtn.setBounds(500,600,100,40);
        this.add (nextBtn);
        nextBtn.addActionListener(this);
        
        backBtn.setBounds(500,600,100,40);
        this.add (backBtn);
        backBtn.addActionListener(this);
        
        endBtn.setBounds(500,600,100,40);
        this.add (endBtn);
        endBtn.addActionListener(this);
        
    }
    
    public void paint (Graphics g)
    {
        g.setColor(Color. red);
        g.setRect(0, 0, 1000, 1000);
        g.setColor(Color. black);
        g.setFont (myFont);
        if(pageCt==0)
        {
            g.drawString("Welcome to this Mastermind tutorial, press next to continue.",300, 300);
        }
        if(pageCt==1)
        {
            g.drawString("Mastermind is a game of logic. To play it, one must make a 3 letter secret code of a set of colors,", 10, 30);
            g.drawString("they are: Red, Blue, Orange, Magenta, White, Green, Purple, and Yellow. While one creates a ", 10, 70);
            g.drawString("code using ONLY with the first letters of the color the other person  who does not know the code", 10, 110);
            g.drawString("tries to guess which 3 letters it is. The one who tries to guess the letters has about 6 chances", 10, 150);
            g.drawString("to get the correct code. Out of 6 chances the player must get the colors and pegs (placement)", 10, 190);
            g.drawString("correct. Once they have made a 3 letter guess, he/she goes to the player who made the code", 10, 230);
            g.drawString("and that person says how many colors out of the guess 
    