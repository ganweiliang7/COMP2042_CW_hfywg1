package test;
import java.io.File;
import java.io.FileWriter;  // Import the File class
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Score {
    ArrayList<Integer> scores = new ArrayList<>();

    private int totalscore = 0;

    public void ScoreReset()
    {
        totalscore = 0;
    }

    public int returnScore()
    {


        return totalscore;
    }
    public void addScoretoList()
    {
        scores.add(totalscore);
    }
    public void incrementScore(int score)
    {

        totalscore += score;
        System.out.println(totalscore);

    }
    public void createFile()
    {
        File myObj = new File("Score.txt");


    }
    public boolean checkFileExist(String filename)
    {
        File temp;
        try
        {
            temp = File.createTempFile(filename, ".txt");

            return temp.exists();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;

    }
    public void writeScore()
    {
        if(checkFileExist("Score.txt"))
        {
            Collections.sort(scores,Collections.reverseOrder());
            try
            {
                Writer wr = new FileWriter("Score.txt");
                for(Integer score:scores) {
                    wr.write(score + System.lineSeparator());
                }

                wr.close();
            }
            catch(IOException e)
            {
                System.out.println("An error occurred.");
            }

        }
        else
        {
            createFile();

            try
            {
                Writer wr = new FileWriter("Score.txt");
                for(Integer score:scores)
                {
                    wr.write(score + System.lineSeparator());
                }

                wr.close();
            }
            catch(IOException e)
            {
                System.out.println("An error occurred.");
            }

        }


    }




}
