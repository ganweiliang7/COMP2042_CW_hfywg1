package main.java;

import java.awt.*;
import java.awt.event.MouseEvent;

public class HomeMenuController {


    HomeMenuModel homeMenuModel = new HomeMenuModel();
    public void homeMenuMouseCLicked(MouseEvent mouseEvent)
    {
        Point p = mouseEvent.getPoint();
        if(homeMenuModel.startButton.contains(p)){
            homeMenuModel.owner.enableGameBoard();

        }
        else if(homeMenuModel.menuButton.contains(p)){
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
        else if(homeMenuModel.highscoreButton.contains(p))
        {
            homeMenuModel.owner.enableHighScoreMenu();

        }
        else if(homeMenuModel.InstructionButton.contains(p))
        {
            homeMenuModel.owner.enableInstructionMenu();

        }

    }


    public void homeMenuMousePressed(MouseEvent mouseEvent, HomeMenu homeMenuView)
    {
        Point p = mouseEvent.getPoint();
        if(this.homeMenuModel.startButton.contains(p)){
            this.homeMenuModel.startClicked = true;
            homeMenuView.repaint(homeMenuModel.startButton.x,homeMenuModel.startButton.y,homeMenuModel.startButton.width+1,homeMenuModel.startButton.height+1);

        }
        else if(homeMenuModel.menuButton.contains(p)){
            homeMenuModel.menuClicked = true;
            homeMenuView.repaint(homeMenuModel.menuButton.x,homeMenuModel.menuButton.y,homeMenuModel.menuButton.width+1,homeMenuModel.menuButton.height+1);
        }
        else if(homeMenuModel.highscoreButton.contains(p))
        {
            homeMenuModel.highScoreClicked = true;
            homeMenuView.repaint(homeMenuModel.menuButton.x,homeMenuModel.menuButton.y,homeMenuModel.menuButton.width+1,homeMenuModel.menuButton.height+1);

        }
        else if(homeMenuModel.InstructionButton.contains(p))
        {
            homeMenuModel.instructionclicked = true;
            homeMenuView.repaint(homeMenuModel.menuButton.x,homeMenuModel.menuButton.y,homeMenuModel.menuButton.width+1,homeMenuModel.menuButton.height+1);
        }
    }


    public void homeMenuMouseReleased(MouseEvent mouseEvent, HomeMenu homeMenuView)
    {
        if(homeMenuModel.startClicked ){
            homeMenuModel.startClicked = false;
            homeMenuView.repaint(homeMenuModel.startButton.x,homeMenuModel.startButton.y,homeMenuModel.startButton.width+1,homeMenuModel.startButton.height+1);
        }
        else if(homeMenuModel.menuClicked){
            homeMenuModel.menuClicked = false;
            homeMenuView.repaint(homeMenuModel.menuButton.x,homeMenuModel.menuButton.y,homeMenuModel.menuButton.width+1,homeMenuModel.menuButton.height+1);
        }
        else if(homeMenuModel.highScoreClicked)
        {
            homeMenuModel.highScoreClicked = false;
            homeMenuView.repaint(homeMenuModel.highscoreButton.x,homeMenuModel.highscoreButton.y,homeMenuModel.highscoreButton.width,homeMenuModel.highscoreButton.height);
        }
        else if(homeMenuModel.instructionclicked)
        {
            homeMenuModel.instructionclicked = false;
            homeMenuView.repaint(homeMenuModel.InstructionButton.x,homeMenuModel.InstructionButton.y,homeMenuModel.InstructionButton.width,homeMenuModel.InstructionButton.height);

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
