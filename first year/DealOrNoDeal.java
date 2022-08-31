import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.util.Random;

/*Simplet Applet Deal or No Deal
 * Amanda Ramos October 7th, 2013
 */

public class DealOrNoDeal extends Applet implements ActionListener
{  
    Button[] caseBtn= new Button[26];
    Random rand= new Random();
    Font font= new Font( "Comic Sans MS", 1, 15);
    int money[]={1,2,5,10,25,50,75,100,200,300,400,500,750,1000,5000,10000,25000,50000,75000,100000,200000,300000,400000,500000,750000,1000000};
    int prizeInside[]={1,2,5,10,25,50,75,100,200,300,400,500,750,1000,5000,10000,25000,50000,75000,100000,200000,300000,400000,500000,750000,1000000};
    int case1[]={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};
    int [] bankOffers= new int [10];
    int caseCt=0, userCase, moneyInCase;
    int yourMoney=0;
    //int bankCt=-1;
    Button dealBtn= new Button("Deal");
    Button noDealBtn= new Button("No Deal");
    int totalMoneyLeft=0;
    boolean Banker=false;
    

    public void randomize(int prizeInside[])
    {
        for(int i=0; i<26; i++)
        {
            int randPlace=rand.nextInt(25);
            int temp= prizeInside[i];
            prizeInside[i]=prizeInside[randPlace];
            prizeInside[randPlace]=temp;
        }
    }

    public void init()
    {
        this.setLayout(null);
        randomize(prizeInside);

        dealBtn.setBounds(250,450,75,40);
        this.add(dealBtn);
        dealBtn.addActionListener(this);
        dealBtn.setVisible(false);

        noDealBtn.setBounds(360,450,75,40);
        this.add(noDealBtn);
        noDealBtn.addActionListener(this);
        noDealBtn.setVisible(false);

        for(int p=0; p<26; p++)
        {
            caseBtn[p]= new Button(""+(case1[p]));
            if(p<5)
                caseBtn[p].setBounds(250+60*p,60,50,40);
            else if(p<10)
                caseBtn[p].setBounds(-50+60*p,110,50,40);
            else if(p<15)
                caseBtn[p].setBounds(-350+60*p,160,50,40);
            else if(p<20)
                caseBtn[p].setBounds(-650+60*p,210,50,40);
            else
                caseBtn[p].setBounds(-980+60*p,260,50,40);
            caseBtn[p].addActionListener(this);
            this.add(caseBtn[p]);
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        for(int p=0; p<26; p++)
        {
            if(e.getSource() == caseBtn[p] )
            {
                caseBtn[p].setVisible(false);
                caseCt++;
                if(caseCt==1)
                    userCase=p;
                else
                {
                    moneyInCase=prizeInside[p];
                    for(int i=0; i<26; i++)
                    {
                        if(moneyInCase==money[i])
                            money[i]=0;
                    }
                }
            }
        }
        
        if(caseCt==7||caseCt==12||caseCt==16||caseCt==19||caseCt>=21&&caseCt<26)
            Banker=true;
            
        if(Banker==true)
        {
            for(int p=0; p<26; p++)
                caseBtn[p].setEnabled(false);
            dealBtn.setVisible(true);
            noDealBtn.setVisible(true);
            if(e.getSource() == dealBtn)
            {
                totalMoneyLeft=yourMoney;
            }
            if(e.getSource() == noDealBtn) 
            {
                for(int p=0; p<26; p++)
                    caseBtn[p].setEnabled(true);
                Banker=false;
                dealBtn.setVisible(false);
                noDealBtn.setVisible(false);
            }
            
        }
        for(int p=0; p<10; p++)
                totalMoneyLeft=bankOffers[1];
        repaint();
    }

    public void paint (Graphics g)
    {   
        g.setColor( Color. black);
        g.fillRect(0,0,10000,100000);
        g.setColor( Color. green);
        g.setFont(font);
        g.drawString("Your case is "+(userCase+1), 500, 450);
        g.drawString("That case had "+ moneyInCase, 500, 500);
        for(int m=0; m<26; m++)
        {
            if(m<13)
                g.drawString(""+money[m], 10, 20+20*m);
            else
                g.drawString(""+money[m], 700, -240+20*m);
        }
        if(Banker==true)
        {
            for(int b=0; b<26; b++)
            {
                totalMoneyLeft=totalMoneyLeft+money[b];
            }
            g.drawString("Bank offers: $"+(totalMoneyLeft/200.00), 300, 350);
        }
        g.drawString("Your money is: $"+yourMoney, 700, 600);
        g.drawString("Previous Bank Offers:", 850, 20);
        if(caseCt<7)
        //casetopick=7-castCt
        for(int m=0; m<10; m++)
        {
            g.drawString(""+bankOffers[m], 920, 40+20*m);
        }
    }
}