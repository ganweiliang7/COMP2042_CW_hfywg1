package gameboard;

import score.Score;
import time.Time;
import wall.Wall;

import java.awt.*;

public class GameBoardModel {
    private int strLen;
    private static final Color MENU_COLOR = new Color(0,255,0);
    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;
    private static final Color BG_COLOR = Color.WHITE;
    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause Menu";
    private static final String BACK = "Back";
    private static final int TEXT_SIZE = 30;
    public static String message;
    public static String highScore;
    public static String timeStr;
    public static Score score;
    public static boolean showPauseMenu;
    private Font menuFont;
    public Wall wall;


    /**
     * create the wall for the game
     * @param DEF_HEIGHT height of the wall
     * @param DEF_WIDTH width of the wall
     * @return a wall obejct
     */
    public Wall createWall(int DEF_HEIGHT,int DEF_WIDTH)
    {

       return  new Wall(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),30,3,6/2,new Point(300,430));

    }

    /** create score object
     *
     * @return score object
     */
    public Score createScore()
    {

        return new Score();
    }

    /**
     * create time object
     * @return a time obejct
     */
    public Time createTime()
    {
        return new Time();
    }

    /**
     * set pause menu to be false
     */
    public static void setPauseMenuFalse()
    {
        showPauseMenu = false;
    }

    /**
     *
     * @return the text size
     */
    public int getTextSize()
    {
        return TEXT_SIZE;
    }

    /**
     *
     * @return  the string for pause
     */
    public String getPauseString()
    {
        return PAUSE;
    }

    /**
     *
     * @return string for continue
     */
    public String getContinueString()
    {
        return CONTINUE;
    }

    /**
     *
     * @return string for restart
     */
    public String getRestartString()
    {
        return RESTART;
    }
    /**
     *
     * @return string for exit
     */
    public String getExitString()
    {
        return EXIT;
    }
    /**
     *
     * @return string for back
     */
    public String getBackString(){return BACK;}

    /**
     * set string for message
     * @param s string for the text
     */
    public void setMessageString(String s)
    {
        message = s;
    }
    /**
     * set string for highscore
     * @param s string for the text
     */
    public void setHighScoreString(String s)
    {
        highScore = s;
    }
    /**
     * set string for time
     * @param s string for the text
     */
    public void setTimeString(String s)
    {
       timeStr = s;
    }
    /**
     *
     * @return string for message
     */

    public String getMessageString()
    {
        return message;
    }
    /**
     *
     * @return string for highscore
     */
    public String getHighScoreString()
    {
        return highScore;
    }
    /**
     *
     * @return string for time
     */
    public String getTimeString()
    {
        return timeStr;
    }
    /**
     *
     * @return integer for the gameboard height
     */

    public int getDefHeight()
    {
        return DEF_HEIGHT;
    }
    /**
     *
     * @return integer for the gameboard width
     */
    public int getDefWidth()
    {
        return DEF_WIDTH;
    }

    /**
     *
     * @return Colour for the background
     */
    public Color getBackgroundColour()
    {
        return BG_COLOR;
    }

    /**
     *
     * @return Colour for the pause menu
     */
    public Color getMenuColor()
    {
        return MENU_COLOR;
    }

    /**
     * set font for the pause menu
     * @param font Font
     */

    public void setMenuFont(Font font)
    {
        menuFont=font;

    }

    /**
     *
     * @return Font for the pause menu
     */
    public Font getMenuFont()
    {
        return menuFont;
    }

    /**
     * set the length of the string
     * @param length integer
     */
    public void setStringLength(int length)
    {
        strLen=length;
    }

    /**
     *
     * @return length of the string
     */
    public int getStringLength()
    {
        return strLen;
    }




}
