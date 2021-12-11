package homeMenu;

import main.java.GameFrame;

import java.awt.*;

public class HomeMenuModel extends Component {



    public Rectangle menuFace;
    public Rectangle startButton;
    public  Rectangle menuButton;
    public  Rectangle highscoreButton;
    public  Rectangle InstructionButton;

    public GameFrame owner;

    public static boolean startClicked;
    public static boolean menuClicked;
    public static boolean highScoreClicked;
    public static boolean instructionclicked;

    public void setStartClickedTrue()
    {
        startClicked = true;
    }
    public void setMenuClickedTrue()
    {
        menuClicked = true;
    }
    public void setHighScoreClickedTrue()
    {
        highScoreClicked = true;
    }
    public void setInstructionClickedTrue()
    {
        instructionclicked = true;
    }
    public void setStartClickedFalse()
    {
        startClicked = false;
    }
    public void setMenuClickedFalse()
    {
        menuClicked = false;
    }
    public void setHighScoreClickedFalse()
    {
        highScoreClicked = false;
    }
    public void setInstructionClickedFalse()
    {
        instructionclicked = false;
    }

    public int getHomeMenuButtonX()
    {
        return menuButton.x;
    }
    public int getHomeMenuButtonY()
    {
        return menuButton.y;
    }
    public int getStartMenuButtonX()
    {
        return startButton.x;
    }
    public int getStartMenuButtonY()
    {
        return startButton.y;
    }
    public int getHighScoreMenuButtonX()
    {
        return highscoreButton.x;
    }
    public int getHighScoreMenuButtonY()
    {
        return highscoreButton.y;
    }
    public int getInstructionMenuButtonX()
    {
        return InstructionButton.x;
    }
    public int getInstructionMenuButtonY()
    {
        return InstructionButton.y;
    }
    public boolean startButtonContainsPoint(Point p)
    {
        return startButton.contains(p);
    }
    public boolean exitButtonContainsPoint(Point p)
    {
        return menuButton.contains(p);
    }
    public boolean highScoreButtonContainsPoint(Point p)
    {
        return highscoreButton.contains(p);
    }
    protected boolean instructionButtonContainsPoint(Point p)
    {
        return InstructionButton.contains(p);
    }
    public void startgameboard()
    {
        owner.enableGameBoard();
    }
    public void starthighscore()
    {
        owner.enableHighScoreMenu();
    }

    public void  startinstruction()
    {
        owner.enableInstructionMenu();
    }

}
