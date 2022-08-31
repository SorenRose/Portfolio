import java.awt.*;
import java.applet.Applet;
import java.util.Random;

/*Simplet Applet to output stuff using arrays
 * Amanda Ramos September 16th, 2013
 */

public class arrayPractice extends Applet
{
    int [] numbers = new int [8];
    int [] testScores = new int [8];
    int [] random = new int [8];

    public void init()
    {
        for(int i=0; i <=7; i++)
            numbers[i]=2;
        random[0]=-5;
        random[1]=6;
        random[2]=3;
        random[3]=-7;
        random[4]=2;
        random[5]=8;
        random[6]=-1;
        random[7]=4;
        testScores[0]=83;
        testScores[1]=92;
        testScores[2]=5;
        testScores[3]=70;
        testScores[4]=100;
        testScores[5]=60;
        testScores[6]=87;
        testScores[7]=89;
    }

    public void outputVert(int [] anyArray, Graphics g)
    {
        for(int p=0; p<=7; p++)
            g.drawString(""+p+"           "+numbers[p], 10, 50*p+50);
    }

    public int getSum (int [] anyArray)
    {
        int sum=0;
        for(int p=0; p<=7; p++)
            sum=anyArray[p]+sum;
        return sum;
    }

    public int getAverage(int[] anyArray)
    {
        return getSum(anyArray)/7;
    }

    public int countNegative(int[] anyArray)
    {
        int negativeCt=0;
        for(int n=0; n<=7; n++)
        { 
            if(anyArray[n]<0)
                negativeCt++;
        }
        return negativeCt;
    }

    public int getLowest(int[] anyArray)
    {
        int lowest=1000;
        for(int i=0; i<=7; i++)
        { 
            if(anyArray[i]<lowest)
                lowest=anyArray[i];
        }
        return lowest;
    }

    public void outputLetterGrades(int[] anyArray,Graphics g)
    {
        int A=0, B=0, C=0, D=0, F=0;
        for(int i=0; i<=7; i++)
        { 
            if(anyArray[i]>95)
                A++;
            else if(anyArray[i]<95&&anyArray[i]>90)
                B++;
            else if(anyArray[i]<90&&anyArray[i]>85)
                C++;
            else if(anyArray[i]<85&&anyArray[i]>70)
                D++;
            else
                F++;
        }
        g.drawString("A "+A+" B "+B+" C "+C+" D "+D+" F "+F,100,250);
    }
    
    public void reverseArray(int[] anyArray, Graphics g)
    {
        
        for(int r=0; r<8; r++)
        {
            anyArray[r]=r-(r++);
        }
        for(int p=0; p<=7; p++)
            g.drawString(""+p+"           "+anyArray[p], 300, 50*p+50);
    }

    public void paint (Graphics g)
    {
        g.setColor( Color. blue);
        outputVert(numbers,g);
        g.drawString("The sum is " +getSum(numbers), 100,50);
        g.drawString("The average is " +getAverage(random), 100,100);
        g.drawString("Negative Numbers: "+countNegative(random),100, 150);
        g.drawString("Low Numbers: "+getLowest(random),100, 200);
        outputLetterGrades(testScores,g);
        reverseArray(random,g);
    }
}