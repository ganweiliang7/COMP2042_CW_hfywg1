package gameboard;

import main.java.GameFrame;
import score.ScoreController;
import time.TimeController;
import wall.Wall;
import wall.WallController;
import wall.WallModel;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GameBoardController
{
    /**
     * handles the operations when a key is pressed
     * @param keyEvent <- events from the keyboard
     * @param gameBoard <- current game board
     */
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

    /**
     * handles the operation when button is clicked in the pause menu
     * @param mouseEvent the event of the mouse
     * @param gameBoard current gameboard
     * @param owner the original gamframe
     */
    public void controllerMouseCLicked(MouseEvent mouseEvent, GameBoard gameBoard,GameFrame owner)
    {
        Point p = mouseEvent.getPoint();
        if(!GameBoardModel.showPauseMenu)
            return;
        if(gameBoard.continueButtonRect.contains(p)){
            GameBoardModel.setPauseMenuFalse();
            gameBoard.repaint();
        }
        else if(gameBoard.restartButtonRect.contains(p)){
            GameBoardModel.message = "Restarting Game...";
            gameBoard.wallController.ballReset();
            gameBoard.wallController.wallReset();
            gameBoard.time.reset();
            GameBoardModel.setPauseMenuFalse();
            gameBoard.repaint();
        }
        else if(gameBoard.exitButtonRect.contains(p)){
            System.exit(0);
        }
        else if(gameBoard.backButtonRect.contains(p))
        {


            owner.backToHomeMenu(gameBoard);


        }



    }

    /**
     * detect movement and position of the mouse
     * @param mouseEvent event of the mouse
     * @param gameboard current gameboard
     */
    public void controllerMouseMoved(MouseEvent mouseEvent,  GameBoard gameboard)
    {
        Point p = mouseEvent.getPoint();
        if(gameboard.exitButtonRect != null && GameBoardModel.showPauseMenu) {
            if (gameboard.exitButtonRect.contains(p) || gameboard.continueButtonRect.contains(p) || gameboard.restartButtonRect.contains(p) || gameboard.backButtonRect.contains(p))
                gameboard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                gameboard.setCursor(Cursor.getDefaultCursor());
        }
        else{
            gameboard.setCursor(Cursor.getDefaultCursor());
        }
    }

    /**
     * perform all the actions in the gametimer and use the relevant controller if necessary
     * @param wall <- wall created by the gameboard model
     * @param wallController <- controller class of the wall
     * @param score <- controller class of score
     * @param time <- controller class of time
     * @param gameTimer <- gametimer at the gameboard view
     */

    public void actionsInGameTimer(WallModel wall, WallController wallController, ScoreController score, TimeController time, Timer gameTimer) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        try {
            time.starttime();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        wallController.move();
        wallController.findImpacts();


        GameBoardModel.message = String.format("Bricks: %d Balls %d",wall.getBrickCount(),wall.getBallCount());
        GameBoardModel.highScore=String.format("HighScore:%d",score.scoreView.getScore(score));
        GameBoardModel.timeStr = String.format("Time:%02d:%02d", time.timeView.displayMinutes(time), time.timeView.displaySeconds(time));

        if(wall.isBallLost())
        {
            if(wall.ballEnd())
            {
                score.addScoretoList(time.timeModel.getMinutes(),time.timeModel.getSeconds());
                score.writeScore();
                score.scoreModel.ScoreReset();
                wallController.wallReset();
                time.timeModel.reset();
                GameBoard.gameOverSound();
                GameBoardModel.message = "Game over";
            }
            wallController.ballReset();
            gameTimer.stop();
        }
        else if(wall.isDone()){
            if(wall.hasLevel()){
                GameBoardModel.message = "Go to Next Level";
                gameTimer.stop();
                wallController.ballReset();
                wallController.wallReset();
                wallController.nextLevel();
            }
            else{

                score.addScoretoList(time.timeModel.getMinutes(),time.timeModel.getSeconds());
                score.writeScore();
                time.timeModel.reset();
                GameBoardModel.message = "ALL WALLS DESTROYED";
                gameTimer.stop();
            }
        }
    }

}



