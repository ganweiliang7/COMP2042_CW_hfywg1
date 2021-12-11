package wall;

import bricks.Brick;
import sound.Sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class WallModel {
    public static int level;
    public static Brick[][] levels;
    public String soundlink = "\"src/main/resources/brickimpact.wav\"";

    public static int brickCount;
    public static int ballCount;
    public static boolean ballLost;

    public int getBrickCount(){
        return brickCount;
    }

    public int getBallCount(){
        return ballCount;
    }

    public boolean isBallLost(){
        return ballLost;
    }




    public boolean ballEnd(){
        return ballCount == 0;
    }

    public boolean isDone(){
        return brickCount == 0;
    }



    public boolean hasLevel(){
        return level < levels.length;
    }

    public static void brickDestroyedSound() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        File url = new File("src/main/resources/brickimpact.wav");
        Sound.playSound(url);
    }

    public static void decrementBallCount()
    {
        ballCount--;
    }

    public static void setBallLostTrue()
    {
        ballLost = true;
    }

    public static int getLevel()
    {
        return level;
    }

    public static void setBallLostFalse()
    {
        ballLost = false;
    }

    public static void decrementBrickCount()
    {
        brickCount--;
    }

    public static void setBallCount(int count)
    {
        ballCount = count;
    }

    public static void setBrickCount(int count)
    {
        brickCount = count;
    }

}
