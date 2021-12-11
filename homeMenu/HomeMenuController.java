package homeMenu;

import java.awt.*;
import java.awt.event.MouseEvent;

public class HomeMenuController {


    HomeMenuModel homeMenuModel = new HomeMenuModel();
    public void homeMenuMouseCLicked(MouseEvent mouseEvent)
    {
        Point p = mouseEvent.getPoint();
        if(homeMenuModel.startButtonContainsPoint(p)){
            homeMenuModel.startgameboard();

        }
        else if(homeMenuModel.exitButtonContainsPoint(p)){
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
        else if(homeMenuModel.highScoreButtonContainsPoint(p))
        {
            homeMenuModel.starthighscore();

        }
        else if(homeMenuModel.instructionButtonContainsPoint(p))
        {
            homeMenuModel.startinstruction();

        }

    }


    public void homeMenuMousePressed(MouseEvent mouseEvent, HomeMenu homeMenuView)
    {
        Point p = mouseEvent.getPoint();
        if(homeMenuModel.startButton.contains(p)){
            homeMenuModel.setStartClickedTrue();
            homeMenuView.repaint(homeMenuModel.getStartMenuButtonX(),homeMenuModel.getStartMenuButtonY(),homeMenuModel.startButton.width+1,homeMenuModel.startButton.height+1);

        }
        else if(homeMenuModel.menuButton.contains(p)){
            homeMenuModel.setMenuClickedTrue();
            homeMenuView.repaint(homeMenuModel.getHomeMenuButtonX(),homeMenuModel.getHomeMenuButtonY(),homeMenuModel.menuButton.width+1,homeMenuModel.menuButton.height+1);
        }
        else if(homeMenuModel.highscoreButton.contains(p))
        {
            homeMenuModel.setHighScoreClickedTrue();
            homeMenuView.repaint(homeMenuModel.getHighScoreMenuButtonX(),homeMenuModel.getHighScoreMenuButtonY(),homeMenuModel.highscoreButton.width+1,homeMenuModel.highscoreButton.height+1);

        }
        else if(homeMenuModel.InstructionButton.contains(p))
        {
            homeMenuModel.setInstructionClickedTrue();
            homeMenuView.repaint(homeMenuModel.getInstructionMenuButtonX(),homeMenuModel.getInstructionMenuButtonY(),homeMenuModel.menuButton.width+1,homeMenuModel.menuButton.height+1);
        }
    }


    public void homeMenuMouseReleased(MouseEvent mouseEvent, HomeMenu homeMenuView)
    {
        if(HomeMenuModel.startClicked){
            homeMenuModel.setStartClickedFalse();
            homeMenuView.repaint(homeMenuModel.getStartMenuButtonX(),homeMenuModel.getStartMenuButtonY(),homeMenuModel.startButton.width+1,homeMenuModel.startButton.height+1);
        }
        else if(HomeMenuModel.menuClicked){
            homeMenuModel.setMenuClickedFalse();
            homeMenuView.repaint(homeMenuModel.getHomeMenuButtonX(),homeMenuModel.getHomeMenuButtonY(),homeMenuModel.menuButton.width+1,homeMenuModel.menuButton.height+1);
        }
        else if(HomeMenuModel.highScoreClicked)
        {
            homeMenuModel.setHighScoreClickedFalse();
            homeMenuView.repaint(homeMenuModel.getHighScoreMenuButtonX(),homeMenuModel.getHighScoreMenuButtonY(),homeMenuModel.highscoreButton.width,homeMenuModel.highscoreButton.height);
        }
        else if(HomeMenuModel.instructionclicked)
        {
            homeMenuModel.setInstructionClickedFalse();
            homeMenuView.repaint(homeMenuModel.getInstructionMenuButtonX(),homeMenuModel.getInstructionMenuButtonY(),homeMenuModel.InstructionButton.width,homeMenuModel.InstructionButton.height);

        }
    }

    public void homeMenuMouseMoved(MouseEvent mouseEvent, HomeMenu homeMenuView)
    {
        Point p = mouseEvent.getPoint();
        if(homeMenuModel.startButton.contains(p) || homeMenuModel.menuButton.contains(p) || homeMenuModel.highscoreButton.contains(p) || homeMenuModel.InstructionButton.contains(p))
            homeMenuView.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            homeMenuView.setCursor(Cursor.getDefaultCursor());

    }
}
