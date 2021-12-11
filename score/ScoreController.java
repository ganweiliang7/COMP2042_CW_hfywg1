package score;

import java.io.*;
import java.util.Collections;
import java.util.Scanner;

public class ScoreController {

    public Score scoreModel;
    public ScoreView scoreView;

    public ScoreController(Score model,ScoreView view)
    {
        scoreModel = model;
        scoreView = view;
    }




    /**
     * adds the total score calculated to the arraylist
     * @param minutes <- total minutes used by the player to win/lose the game
     * @param seconds <- total seconds used by the player to win/lose the game
     */
    public void addScoretoList(int minutes,int seconds)
    {
        int totalsec = minutes * 60 + seconds;

        scoreModel.totalscore = (int) Math.round(scoreModel.totalscore * (1800 - totalsec) * 0.01);

        scoreModel.scores.add(scoreModel.totalscore);
    }

    /**
     * adds the score to the totalscore everytime a brick is destroyed
     * @param score <- score from each brick
     */
    public void incrementScore(int score)
    {

        scoreModel.totalscore += score;


    }

    /**
     * write the score available in the arraylist to the file "Score.txt
     */
    public void writeScore()
    {
        if(scoreModel.checkFileExist("Score.txt"))
        {
            try (Scanner scanner = new Scanner(new File("Score.txt")).useDelimiter("\\s*-\\s*"))
            {

                while (scanner.hasNextLine()) {
                    String currLine = scanner.nextLine();
                    if (currLine != null && currLine.trim().length() > 0 && currLine.matches("^[0-9]*$"))
                        scoreModel.scores.add(Integer.parseInt(currLine));
                }
            }

            catch (FileNotFoundException e)
            {
                // Handle the potential exception
            }



            Collections.sort(scoreModel.scores,Collections.reverseOrder());
            try
            {
                Writer wr = new FileWriter("Score.txt");
                for(Integer score:scoreModel.scores) {
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
            scoreModel.createFile();

            try
            {
                Writer wr = new FileWriter("Score.txt");
                for(Integer score:scoreModel.scores)
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
