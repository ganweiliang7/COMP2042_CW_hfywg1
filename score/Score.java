package score;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Score {
    ArrayList<Integer> scores = new ArrayList<>();

    public int totalscore = 0;

    public void ScoreReset()
    {
        totalscore = 0;
    }

    public int returnScore()
    {


        return totalscore;
    }


    /**
     * create file if file does not exist
     */
    public void createFile()
    {
        File myObj = new File("Score.txt");

    }

    /**
     *
     * @param filename <- check if file "Score.txt' exist
     * @return <- a boolean value that state whether file exist or no
     */
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





}
