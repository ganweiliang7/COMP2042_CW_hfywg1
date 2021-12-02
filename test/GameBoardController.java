package test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameBoardController
{
    public void controllerKeyPressed(KeyEvent keyEvent, GameBoard gameBoard)
    {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_A:
                Wall.playerController.moveLeft();
                break;
            case KeyEvent.VK_D:
                Wall.playerController.movRight();
                break;
            case KeyEvent.VK_ESCAPE:
                gameBoard.showPauseMenu = !gameBoard.showPauseMenu;
                gameBoard.repaint();
                gameBoard.gameTimer.stop();
                break;
            case KeyEvent.VK_SPACE:
                if(!gameBoard.showPauseMenu)
                    if(gameBoard.gameTimer.isRunning())
                        gameBoard.gameTimer.stop();
                    else
                        gameBoard.gameTimer.start();
                break;
            case KeyEvent.VK_F1:
                if(keyEvent.isAltDown() && keyEvent.isShiftDown())
                    gameBoard.debugConsole.setVisible(true);
            default:
                gameBoard.wall.player.stop();
        }
    }
    public void controllerMouseCLicked(MouseEvent mouseEvent, GameBoard gameBoard)
    {
        Point p = mouseEvent.getPoint();
        if(!gameBoard.showPauseMenu)
            return;
        if(gameBoard.continueButtonRect.contains(p)){
            gameBoard.showPauseMenu = false;
            gameBoard.repaint();
        }
        else if(gameBoard.restartButtonRect.contains(p)){
            gameBoard.message = "Restarting Game...";
            gameBoard.wallController.ballReset();
            gameBoard.wallController.wallReset();
            gameBoard.showPauseMenu = false;
            gameBoard.repaint();
        }
        else if(gameBoard.exitButtonRect.contains(p)){
            System.exit(0);
        }

    }
    public void controllerMouseMoved(MouseEvent mouseEvent,  GameBoard gameboard)
    {
        Point p = mouseEvent.getPoint();
        if(gameboard.exitButtonRect != null && gameboard.showPauseMenu) {
            if (gameboard.exitButtonRect.contains(p) || gameboard.continueButtonRect.contains(p) || gameboard.restartButtonRect.contains(p))
                gameboard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                gameboard.setCursor(Cursor.getDefaultCursor());
        }
        else{
            gameboard.setCursor(Cursor.getDefaultCursor());
        }
    }

}



