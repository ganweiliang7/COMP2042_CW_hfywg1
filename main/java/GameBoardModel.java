package main.java;

import java.awt.*;

public class GameBoardModel {

    public static Score score;
    public static boolean showPauseMenu;
    public Time time;
    public Wall wall;
    public Wall createWall(int DEF_HEIGHT,int DEF_WIDTH)
    {
       wall = new Wall(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),30,3,6/2,new Point(300,430));
       return wall;

    }

    public Score createScore()
    {
        score = new Score();
        return score;
    }

    public Time createTime()
    {
        time = new Time();
        return time;
    }



}
