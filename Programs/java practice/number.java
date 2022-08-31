import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

/*A simple applet to gat inches and yards
Amanda Ramos Oct 3 2012
 */

public class number extends Applet implements ActionListener
{
    TextField oneTF= new TextField();
    TextField twoTF= new TextField();

    Button clearBtn= new Button("Clear");
    Button answerBtn= new Button("Get Answer");

    String oneInput="";
    String twoInput="";
    String output="";

    double one;
    double two;

    public void init()
    {
        this.setLayout(null);

        oneTF.setBounds(50, 100, 100, 40 );
        this.add(oneTF);

        twoTF.setBounds(50, 150, 100, 40 );
        this.add(twoTF);

        answerBtn.setBounds( 50, 250, 100, 30 );
        this.add(answerBtn);
        answerBtn.addActionListener(this);

        clearBtn.setBounds( 50, 300, 100, 30 );
        this.add(clearBtn);
        clearBtn.addActionListener(this);
    }

    public void actionPerformed (ActionEvent e)
    {
        if(e.getSource() == answerBtn)
        {
            oneInput= oneTF.getText();
            one= Double.parseDouble(oneInput);
            twoInput= twoTF.getText();
            two= Double.parseDouble(twoInput);
            if (one>two)
                output= one+ " is greater";
            else
                output= two+" is greater.";
        }
        if(e.getSource() == clearBtn)
        {
            oneTF.setText("");
            twoTF.setText("");
            output="";
        }
        repaint();
    }

    public void paint (Graphics g)
    {
        g.drawString( "Type two different numbers in and I will tell which of the two is the greatest", 400, 50 );
        g.drawString( "Enter first number here", 160, 110 );
        g.drawString( "Enter second number here", 160, 160 );
        g.drawString( output, 160, 260 );
    }
}
