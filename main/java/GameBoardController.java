package main.java;

import javax.swing.*;
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
                GameBoardModel.showPauseMenu = !GameBoardModel.showPauseMenu;
                gameBoard.repaint();
                gameBoard.gameTimer.stop();
                break;
            case KeyEvent.VK_SPACE:
                if(!GameBoardModel.showPauseMenu)
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
        if(!GameBoardModel.showPauseMenu)
            return;
        if(gameBoard.continueButtonRect.contains(p)){
            GameBoardModel.showPauseMenu = false;
            gameBoard.repaint();
        }
        else if(gameBoard.restartButtonRect.contains(p)){
            gameBoard.message = "Restarting Game...";
            gameBoard.wallController.ballReset();
            gameBoard.wallController.wallReset();
            gameBoard.time.reset();
            GameBoardModel.showPauseMenu = false;
            gameBoard.repaint();
        }
        else if(gameBoard.exitButtonRect.contains(p)){
            System.exit(0);
        }

    }
    public void controllerMouseMoved(MouseEvent mouseEvent,  GameBoard gameboard)
    {
        Point p = mouseEvent.getPoint();
        if(gameboard.exitButtonRect != null && GameBoardModel.showPauseMenu) {
            if (gameboard.exitButtonRect.contains(p) || gameboard.continueButtonRect.contains(p) || gameboard.restartButtonRect.contains(p))
                gameboard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                gameboard.setCursor(Cursor.getDefaultCursor());
        }
        else{
            gameboard.setCursor(Cursor.getDefaultCursor());
        }
    }

    public void actionsInGameTimer(WallModel wall, WallController wallController, Score score, Time time, Timer gameTimer)
    {
        try {
            time.starttime();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        wallController.move();
        wallController.findImpacts();


        GameBoard.message = String.format("Bricks: %d Balls %d",wall.getBrickCount(),wall.getBallCount());
        GameBoard.highScore=String.format("HighScore:%d",score.returnScore());
        GameBoard.timeStr = String.format("Time:%02d:%02d", time.getMinutes(), time.getSeconds());

        if(wall.isBallLost())
        {
            if(wall.ballEnd())
            {
                score.addScoretoList(time.getMinutes(),time.getSeconds());
                score.writeScore();
                score.ScoreReset();
                wallController.wallReset();
                time.reset();
                GameBoard.message = "Game over";
            }
            wallController.ballReset();
            gameTimer.stop();
        }
        else if(wall.isDone()){
            if(wall.hasLevel()){
                GameBoard.message = "Go to Next Level";
                gameTimer.stop();
                wallController.ballReset();
                wallController.wallReset();
                wallController.nextLevel();
            }
            else{

                score.addScoretoList(time.getMinutes(),time.getSeconds());
                score.writeScore();
                time.reset();
                GameBoard.message = "ALL WALLS DESTROYED";
                gameTimer.stop();
            }
        }
    }

}



