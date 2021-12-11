/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package gameboard;

import ball.BallModel;
import bricks.Brick;
import main.java.DebugConsole;
import main.java.GameFrame;
import player.Player;
import score.Score;
import score.ScoreController;
import score.ScoreView;
import sound.Sound;
import time.Time;
import time.TimeController;
import time.TimeView;
import wall.Wall;
import wall.WallController;
import wall.WallModel;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.io.File;
import java.io.IOException;

public class GameBoard extends JComponent implements KeyListener, MouseListener, MouseMotionListener
{


    private Score score;
    private ScoreView scoreView = new ScoreView();
    public static ScoreController scoreController;
    private Time timeModel;
    private TimeView timeView;
    public static TimeController timeController;
    public Timer gameTimer;
    public Time time;
    public Wall wall;
    public WallModel wallModel;
    public WallController wallController;
    private GameBoardModel gameBoardModel;
    private final GameBoardController gameBoardController;
    public Rectangle continueButtonRect;
    public Rectangle exitButtonRect;
    public Rectangle restartButtonRect;
    public Rectangle backButtonRect;
    public GameFrame owner;



    public DebugConsole debugConsole;

    /**
     * constructor for the gameboard
     * @param owner frame
     */
    public GameBoard(GameFrame owner){
        super();
        this.owner = owner;
        timeModel = new Time();
        timeView = new TimeView();
        timeController = new TimeController(timeModel,timeView);
        wallController = new WallController();
        gameBoardController = new GameBoardController();
        gameBoardModel = new GameBoardModel();
        wallModel = new WallModel();
        gameBoardModel.setStringLength(0);
        GameBoardModel.setPauseMenuFalse();
        gameBoardModel.setMenuFont(new Font("Monospaced",Font.PLAIN,gameBoardModel.getTextSize()));
        this.initialize();
        gameBoardModel.setMessageString("");
        gameBoardModel.setHighScoreString("");
        gameBoardModel.setTimeString("");
        wall = gameBoardModel.createWall(gameBoardModel.getDefHeight(),gameBoardModel.getDefWidth());
        score = gameBoardModel.createScore();
        time = gameBoardModel.createTime();
        debugConsole = new DebugConsole(owner,wallController,this);
        scoreController = new ScoreController(score, scoreView);
        //initialize the first level
        wallController.nextLevel();


        gameTimer = new Timer(10,e ->{

            try {
                gameBoardController.actionsInGameTimer(wallModel,wallController,scoreController,timeController,gameTimer);
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                ex.printStackTrace();
            }

            repaint();
        });

    }

    void initialize(){
        this.setPreferredSize(new Dimension(gameBoardModel.getDefWidth(), gameBoardModel.getDefHeight()));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }







    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        clear(g2d);
        g2d.setFont(new Font("Times Roman", Font.BOLD,17));
        g2d.setColor(Color.BLUE);
        g2d.drawString(gameBoardModel.getMessageString(),0,15);
        g2d.drawString(gameBoardModel.getHighScoreString(),250,15);
        g2d.drawString(gameBoardModel.getTimeString(),500,15);

        drawBall(g2d);

        for(Brick b : Wall.bricks)
            if(!b.isBroken())
                drawBrick(b,g2d);

        drawPlayer(wall.player,g2d);

        if(GameBoardModel.showPauseMenu)
            drawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();
    }

    private void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(gameBoardModel.getBackgroundColour());
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    /**
     * draw bricks
     * @param brick the current brick
     * @param g2d component used to draw
     */
    private void drawBrick(Brick brick,Graphics2D g2d){
        Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());


        g2d.setColor(tmp);
    }

    /** draw Ball
     *
     * @param g2d component used to draw
     */
    private void drawBall(Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = BallModel.getBallFace();

        g2d.setColor(BallModel.getInnerColor());
        g2d.fill(s);

        g2d.setColor(BallModel.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    /**
     *
     * @param p player/slider of the gameboard
     * @param g2d component used to draw
     */
    private void drawPlayer(Player p, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(Player.INNER_COLOR);
        g2d.fill(s);

        g2d.setColor(Player.BORDER_COLOR);
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    /**
     * method to call other method to draw pause menu
     * @param g2d component used to draw
     */
    private void drawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    /**
     * make gameboard at the back to be unclear
     * @param g2d component used to draw pause menu
     */
    private void obscureGameBoard(Graphics2D g2d){

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.55f);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0, gameBoardModel.getDefWidth(), gameBoardModel.getDefHeight());

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    /**
     * draw the pause menu when user pressed ESC in the game
     * @param g2d component used to draw
     */

    public void drawPauseMenu(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();


        g2d.setFont(gameBoardModel.getMenuFont());
        g2d.setColor(gameBoardModel.getMenuColor());

        if(gameBoardModel.getStringLength() == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            gameBoardModel.setStringLength(gameBoardModel.getMenuFont().getStringBounds(gameBoardModel.getPauseString(),frc).getBounds().width);
        }

        int x = (this.getWidth() - gameBoardModel.getStringLength()) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(gameBoardModel.getPauseString(),x,y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 4;


        if(continueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect = gameBoardModel.getMenuFont().getStringBounds(gameBoardModel.getContinueString(),frc).getBounds();
            continueButtonRect.setLocation(x,y-continueButtonRect.height);
        }

        g2d.drawString(gameBoardModel.getContinueString(),x,y);

        y += 100;

        if(restartButtonRect == null){
            restartButtonRect = (Rectangle) continueButtonRect.clone();
            restartButtonRect.setLocation(x,y-restartButtonRect.height);
        }

        g2d.drawString(gameBoardModel.getRestartString(),x,y);

        y += 100;

        if(exitButtonRect == null){
            exitButtonRect = (Rectangle) continueButtonRect.clone();
            exitButtonRect.setLocation(x,y-exitButtonRect.height);
        }

        g2d.drawString(gameBoardModel.getExitString(),x,y);

        y+=100;
        if(backButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            backButtonRect = (Rectangle) continueButtonRect.clone() ;
            backButtonRect.setLocation(x,y-backButtonRect.height);
        }

        g2d.drawString(gameBoardModel.getBackString(),x,y);


        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        gameBoardController.controllerKeyPressed(keyEvent,this);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        wall.player.stop();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        gameBoardController.controllerMouseCLicked(mouseEvent,this,owner);

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        gameBoardController.controllerMouseMoved(mouseEvent,this);
    }

    public void onLostFocus(){
        gameTimer.stop();
        gameBoardModel.setMessageString("Focus Lost");
        repaint();
    }

    public static void gameOverSound() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        File url = new File("src/main/resources/gameOver.wav");
        Sound.playSound(url);

    }

}
