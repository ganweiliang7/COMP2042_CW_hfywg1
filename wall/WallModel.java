package wall;

import bricks.Brick;

public class WallModel {
    public static int level;
    public static Brick[][] levels;


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
}
