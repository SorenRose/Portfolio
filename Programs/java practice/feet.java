import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

/*A simple applet to gat inches and yards
 Amanda Ramos Oct 3 2012
 */

public class feet extends Applet implements ActionListener
{
   TextField feetTF= new TextField();
   
   Button clearBtn= new Button("Clear");
   Button inchesBtn= new Button("Get inches");
   Button yardsBtn= new Button("Get yards");
   
   String feetInput="";
   String output="";
   String answer="";
   
   double totalInches;
   double totalYards;
   double feet;
   
   public void init()
   {
       this.setLayout(null);
       
       feetTF.setBounds(50, 100, 100, 40 );
       this.add(feetTF);
       
       inchesBtn.setBounds( 50, 250, 100, 30 );
       this.add(inchesBtn);
       inchesBtn.addActionListener(this);
       
       yardsBtn.setBounds( 50, 290, 100, 30 );
       this.add(yardsBtn);
       yardsBtn.addActionListener(this);
       
       clearBtn.setBounds( 50, 330, 100, 30 );
       this.add(clearBtn);
       clearBtn.addActionListener(this);
   }
   
   public void actionPerformed (ActionEvent e)
   {
       if(e.getSource() == inchesBtn)
       {
           feetInput= feetTF.getText();
           feet= Double.parseDouble(feetInput);
           totalInches= feet*12;
           output= "If there are "+ feet+ " feet, then there are " +totalInches+ " inches.";
        }
       if(e.getSource() == yardsBtn)
       {
           feetInput= feetTF.getText();
           feet= Double.parseDouble(feetInput);
           totalYards= feet*3;
           answer= "If there are "+ feet+ " feet, then there are " +totalYards+ " yards.";
       }
       if(e.getSource() == clearBtn)
       {
           feetTF.setText("");
           output="";
           answer="";
       }
       repaint();
   }
   public void paint (Graphics g)
   {
           g.drawString( "Determines the number of inches or yards", 400, 50 );
           g.drawString( "Enter number of feet here", 160, 110 );
           g.drawString( output, 160, 260 );
           g.drawString( answer, 160, 300 );
   }
}
           