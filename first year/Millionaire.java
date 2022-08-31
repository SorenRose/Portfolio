import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;

/*Simplet Applet to make Who Wants to be a Millionaire
 * Amanda Ramos October 15th, 2013
 */

public class Millionaire extends Applet implements ActionListener
{  
    Font font= new Font( "FangSong", 1, 20);
    String questions[]=new String[9];
    String aAnswers[]=new String[9];
    String bAnswers[]=new String[9];
    String cAnswers[]=new String[9];
    String dAnswers[]=new String[9];
    String Audience[]=new String[8];
    int moneyValue[]= new int[8];
    Button aBtn= new Button ("A");
    Button bBtn= new Button ("B");
    Button cBtn= new Button ("C");
    Button dBtn= new Button ("D");
    Button startBtn= new Button ("START");
    Button restartBtn= new Button("Restart");
    Button walkBtn= new Button ("Walk Away");
    Button fiveBtn= new Button("50/50");
    Button phoneBtn= new Button ("Phone a Friend");
    Button peopleBtn= new Button("Ask the Audience");
    Color myPurple=new Color( 210, 1, 210 );
    Color myBlue=new Color( 20, 170, 250 );
    int pageCt=0;
    int questionCt=0;
    int moneyWon=0;
    char choice='z';
    char [] answers={'C','D','B','B','D','B','A','B'};
    Image wallpaper;
    Image background;
    Image background1;
    String output="";
    String output1="";

    public void init()
    {
        this.setLayout(null);
        wallpaper=this.getImage(this.getCodeBase(),"MajorasMask.jpg");
        background=this.getImage(this.getCodeBase(),"majoras mask link.png");
        background1=this.getImage(this.getCodeBase(),"MajoraBackground2.jpg");

        aBtn.setBounds(200,350,40,40);
        this.add(aBtn);
        aBtn.addActionListener(this);
        aBtn.setVisible(false);

        bBtn.setBounds(650,350,40,40);
        this.add(bBtn);
        bBtn.addActionListener(this);
        bBtn.setVisible(false);

        cBtn.setBounds(200,500,40,40);
        this.add(cBtn);
        cBtn.addActionListener(this);
        cBtn.setVisible(false);

        dBtn.setBounds(650,500,40,40);
        this.add(dBtn);
        dBtn.addActionListener(this);
        dBtn.setVisible(false);

        startBtn.setBounds(630,530,55,40);
        this.add(startBtn);
        startBtn.addActionListener(this);

        restartBtn.setBounds(750,600,60,40);
        this.add(restartBtn);
        restartBtn.addActionListener(this);
        restartBtn.setVisible(false);

        walkBtn.setBounds(170,600,65,40);
        this.add(walkBtn);
        walkBtn.addActionListener(this);
        walkBtn.setVisible(false);

        phoneBtn.setBounds(270,600,90,40);
        this.add(phoneBtn);
        phoneBtn.addActionListener(this);
        phoneBtn.setVisible(false);

        fiveBtn.setBounds(400,600,60,40);
        this.add(fiveBtn);
        fiveBtn.addActionListener(this);
        fiveBtn.setVisible(false);

        peopleBtn.setBounds(500,600,100,40);
        this.add(peopleBtn);
        peopleBtn.addActionListener(this);
        peopleBtn.setVisible(false);

        questions[0]="When was Majora's Mask released?";
        aAnswers[0]="1997";
        bAnswers[0]="1996";
        cAnswers[0]="2000";
        dAnswers[0]="1999";
        questions[1]="What is the first temple's name?";
        aAnswers[1]="Great Bay Temple";
        bAnswers[1]="Snowhead Temple";
        cAnswers[1]="Stone Tower Temple";
        dAnswers[1]="Woodfall Temple";
        questions[2]="What does the Skull Kid want?";
        aAnswers[2]="to destroy everything";
        bAnswers[2]="friends";
        cAnswers[2]="love";
        dAnswers[2]="to take over the world";
        questions[3]="Kafei tries to save what item?";
        aAnswers[3]="an ocarina";
        bAnswers[3]="the Sun Mask";
        cAnswers[3]="a sword";
        dAnswers[3]="Majora's Mask";
        questions[4]="Sharp can be saved by";
        aAnswers[4]="defeating him";
        bAnswers[4]="defeating his brother Flat";
        cAnswers[4]="playing the Song of Healing";
        dAnswers[4]="playing the Song of Storms";
        questions[5]="The Fierce Deity Mask can only be used";
        aAnswers[5]="in boss battles";
        bAnswers[5]="in minigames";
        cAnswers[5]="in dungeons";
        dAnswers[5]="anytime";
        questions[5]="Captain Keeta was the leader of";
        aAnswers[5]="the Dekus";
        bAnswers[5]="the Ikana Army";
        cAnswers[5]="the Zoras";
        dAnswers[5]="the Gerudo Pirates";
        questions[6]="The Giant's Mask can only be used in";
        aAnswers[6]="the battle with Twinmold";
        bAnswers[6]="the Stone Tower Temple";
        cAnswers[6]="the battle with Twinrova";
        dAnswers[6]="Gerudo Pirates Hideout";
        questions[7]="Who's soul was tranformed into the Zora's mask?";
        aAnswers[7]="Dampe";
        bAnswers[7]="Mikau";
        cAnswers[7]="Darmani";
        dAnswers[7]="Zubora";
        questions[8]="Congrats! You Won Who Wants To Be A Millionaire!";
        aAnswers[8]="";
        bAnswers[8]="";
        cAnswers[8]="";
        dAnswers[8]="";
        moneyValue[0]=1000;
        moneyValue[1]=2000;
        moneyValue[2]=5000;
        moneyValue[3]=10000;
        moneyValue[4]=25000;
        moneyValue[5]=100000;
        moneyValue[6]=500000;
        moneyValue[7]=1000000;
        Audience[0]="The people think: A:20% B:25% C:35% D:20%";
        Audience[1]="The people think: A:18% B:16% C:25% D:41%";
        Audience[2]="The people think: A:16% B:49% C:15% D:20%";
        Audience[3]="The people think: A:23% B:37% C:15% D:25%";
        Audience[4]="The people think: A:26% B:12% C:34% D:28%";
        Audience[5]="The people think: A:38% B:18% C:34% D:10%";
        Audience[6]="The people think: A:38% B:18% C:31% D:13%";
        Audience[7]="The people think: A:23% B:26% C:27% D:24%";
    }

    public void actionPerformed(ActionEvent e)
    {
        if( e.getSource()==restartBtn)
        {
            restartBtn.setVisible(false);
            pageCt=0;
            questionCt=0;
            startBtn.setVisible(true);
            walkBtn.setVisible(false);
            fiveBtn.setVisible(false);
            peopleBtn.setVisible(false);
            phoneBtn.setVisible(false);
            moneyWon=0;
        }
        else if( e.getSource()==walkBtn)
        {
            moneyWon=moneyValue[(questionCt-1)];
            aBtn.setVisible(false);
            bBtn.setVisible(false);
            cBtn.setVisible(false);
            dBtn.setVisible(false);
            restartBtn.setVisible(true);
            walkBtn.setVisible(false);
            fiveBtn.setVisible(false);
            peopleBtn.setVisible(false);
            phoneBtn.setVisible(false);
        }
        else if( e.getSource()==peopleBtn)
        {
            output=Audience[questionCt];     
            peopleBtn.setVisible(false);
        }
        else if( e.getSource()==phoneBtn)
        {
            output1="Your friend thinks the answer might be "+answers[questionCt];     
            phoneBtn.setVisible(false);
        }
        else if( e.getSource()==fiveBtn)
        {
            fiveBtn.setVisible(false);
            if(questionCt==0)
            {
                aBtn.setVisible(false);
                dBtn.setVisible(false);
            }
            if(questionCt==1)
            {
                aBtn.setVisible(false);
                bBtn.setVisible(false);
            }
            if(questionCt==2)
            {
                aBtn.setVisible(false);
                cBtn.setVisible(false);
            }
            if(questionCt==3)
            {
                aBtn.setVisible(false);
                dBtn.setVisible(false);
            }
            if(questionCt==4)
            {
                aBtn.setVisible(false);
                cBtn.setVisible(false);
            }
            if(questionCt==5)
            {
                cBtn.setVisible(false);
                dBtn.setVisible(false);
            }
            if(questionCt==6)
            {
                bBtn.setVisible(false);
                cBtn.setVisible(false);
            }
            if(questionCt==7)
            {
                cBtn.setVisible(false);
                dBtn.setVisible(false);
            }
        }
        else if( e.getSource()==startBtn)
        {
            pageCt=1;
            questionCt=0;
            aBtn.setVisible(true);
            bBtn.setVisible(true);
            cBtn.setVisible(true);
            dBtn.setVisible(true);
            startBtn.setVisible(false);
            fiveBtn.setVisible(true);
            restartBtn.setVisible(false);
            peopleBtn.setVisible(true);
            phoneBtn.setVisible(true);
        }
        else
        {
            if( e.getSource()==aBtn)
            {
                choice='A';
                aBtn.setVisible(true);
                bBtn.setVisible(true);
                cBtn.setVisible(true);
                dBtn.setVisible(true);
                output="";
                output1="";
            }
            if( e.getSource()==bBtn)
            {
                choice='B';
                aBtn.setVisible(true);
                bBtn.setVisible(true);
                cBtn.setVisible(true);
                dBtn.setVisible(true);
                output="";
                output1="";
            }
            if( e.getSource()==cBtn)
            {
                choice='C';
                aBtn.setVisible(true);
                bBtn.setVisible(true);
                cBtn.setVisible(true);
                dBtn.setVisible(true);
                output="";
                output1="";
            }
            if( e.getSource()==dBtn)
            {
                choice='D';
                aBtn.setVisible(true);
                bBtn.setVisible(true);
                cBtn.setVisible(true);
                dBtn.setVisible(true);
                output="";
                output1="";
            }
            if(choice==answers[questionCt])
            {
                questionCt++;
                if(questionCt>0)
                    walkBtn.setVisible(true);
            }
            else
            {
                if(questionCt>3)
                    moneyWon=moneyValue[3];
                else
                    moneyWon=0;
                aBtn.setVisible(false);
                bBtn.setVisible(false);
                cBtn.setVisible(false);
                dBtn.setVisible(false);
                restartBtn.setVisible(true);
                fiveBtn.setVisible(false);
                walkBtn.setVisible(false);
                peopleBtn.setVisible(false);
                phoneBtn.setVisible(false);
            }
        }
        if(questionCt==8)
        {
            aBtn.setVisible(false);
            bBtn.setVisible(false);
            cBtn.setVisible(false);
            dBtn.setVisible(false);
            restartBtn.setVisible(true);
            fiveBtn.setVisible(false);
            walkBtn.setVisible(false);
            peopleBtn.setVisible(false);
            phoneBtn.setVisible(false);
        }
        repaint();
    }

    public void paint (Graphics g)
    {   
        g.setColor( myBlue);
        startBtn.setForeground( Color. white );
        startBtn.setBackground( Color. red );
        g.setFont(font);
        if(pageCt==0)
        {
            g.drawImage(wallpaper, 0,0, 1280,700,this);
            g.drawString("Welcome to Who Wants To Be A Millionaire!",100,50);
            g.drawString("The topic is about a Nintendo game called Majora's Mask.",100,100);
            g.drawString("To win you must answer all the questions correctly.",100,150);
            g.drawString("If you get a question wrong, you don't win the million.",100,200);
            g.drawString("Test your knowledge and/or luck! To begin press the Start button! Good Luck!",100,250);
        }
        if(pageCt==1)
        {
            g.drawImage(background1, 0,0, 1280,700,this);
            if(questionCt==8)
                g.drawImage(background, 0,0, 1280,700,this);
            if(questionCt<8)
            {
                g.setColor(Color. black);
                g.fillRect(250, 355, 300,25);
                g.fillRect(700, 355, 300,25);
                g.fillRect(250, 505, 300,25);
                g.fillRect(700, 505, 300,25);
            }
            g.setColor(myBlue);
            g.drawString(""+questions[questionCt], 250, 200);
            g.drawString(""+aAnswers[questionCt], 250, 375);
            g.drawString(""+bAnswers[questionCt], 700, 375);
            g.drawString(""+cAnswers[questionCt], 250, 525);
            g.drawString(""+dAnswers[questionCt], 700, 525);
            if(questionCt<8)
            {
                for(int m=0; m<8; m++)
                {
                    if(questionCt==m)
                        g.setColor(Color. green);
                    else
                        g.setColor(Color. blue);
                    if(m<8)
                        g.drawString("$"+moneyValue[m], 1150, 450-50*m);
                }
                g.setColor(Color.red);
                g.drawString("$"+moneyWon,850,620);
            }
            g.setColor(myPurple);
            g.drawString(""+output,175,560);
            g.drawString(""+output1,175,580);
        }

    }
}