package test;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

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
    public void addScoretoList(int minutes,int seconds)
    {
        int totalsec = minutes * 60 + seconds;

        totalscore = (int) Math.round(totalscore * (1800 - totalsec) * 0.01);

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
            try (Scanner scanner = new Scanner(new File("Score.txt")).useDelimiter("\\s*-\\s*"))
            {
                // \\s* in regular expressions means "any number or whitespaces".
                // We could've said simply useDelimiter("-") and Scanner would have
                // included the whitespaces as part of the data it extracted.
                while (scanner.hasNextLine()) {
                    String currLine = scanner.nextLine();
                    if (currLine != null && currLine.trim().length() > 0 && currLine.matches("^[0-9]*$"))
                        scores.add(Integer.parseInt(currLine));
                }
            }

            catch (FileNotFoundException e)
            {
                // Handle the potential exception
            }



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
