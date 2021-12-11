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
package homeMenu;

import main.java.GameFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class HomeMenu extends JComponent implements MouseListener, MouseMotionListener {
    HomeMenuController homeMenuController = new HomeMenuController();
    
    private static final String GREETINGS = "Welcome to:";
    private static final String GAME_TITLE = "Brick Destroy";
    private static final String CREDITS = "GWL Version";
    private static final String START_TEXT = "Start";
    private static final String MENU_TEXT = "Exit";
    private static final String HIGHSCORE_TEXT = "High Score";
    private static final String INSTRUCTION_TEXT = "Instruction";
    private static final Color BORDER_COLOR = new Color(16,52,166); //Venetian Red
    private static final Color DASH_BORDER_COLOR = new  Color(2, 255, 200);//school bus yellow
    private static final Color TEXT_COLOR = new Color(0,0,0);//egyptian blue
    private static final Color CLICKED_BUTTON_COLOR = Color.YELLOW;
    private static final Color CLICKED_TEXT = Color.WHITE;
    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12,6};
    private BasicStroke borderStoke;
    private BasicStroke borderStoke_noDashes;

    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private Font buttonFont;



    public HomeMenu(GameFrame owner, Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        homeMenuController.homeMenuModel.owner = owner;



        homeMenuController.homeMenuModel.menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);


        Dimension hsbtnDim = new Dimension(area.width/2, area.height/12);//button dimension
        homeMenuController.homeMenuModel.startButton = new Rectangle(hsbtnDim);
        homeMenuController.homeMenuModel.menuButton = new Rectangle(hsbtnDim);
        homeMenuController.homeMenuModel.highscoreButton = new Rectangle(hsbtnDim);
        homeMenuController.homeMenuModel.InstructionButton = new Rectangle(hsbtnDim);

        borderStoke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);
        borderStoke_noDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);

        greetingsFont = new Font("Noto Mono",Font.PLAIN,25);
        gameTitleFont = new Font("Noto Mono",Font.BOLD|Font.ITALIC,40);
        creditsFont = new Font("Monospaced",Font.PLAIN,10);
        buttonFont = new Font("Monospaced",Font.BOLD,homeMenuController.homeMenuModel.startButton.height-2);



    }


    public void paint(Graphics g){
        drawMenu((Graphics2D)g);
    }


    public void drawMenu(Graphics2D g2d){

        drawContainer(g2d);

        /*
        all the following method calls need a relative
        painting directly into the HomeMenu rectangle,
        so the translation is made here so the other methods do not do that.
         */
        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = homeMenuController.homeMenuModel.menuFace.getX();
        double y = homeMenuController.homeMenuModel.menuFace.getY();

        g2d.translate(x,y);

        //methods calls
        drawText(g2d);
        drawButton(g2d);
        //end of methods calls

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
    }



    private void drawContainer(Graphics2D g2d){
        Color prev = g2d.getColor();

        BufferedImage homeMenuImage = null;
        String pathtoWallpaper = "src/main/resources/HomeScreenWallpaper.jpg";
        try
        {
            homeMenuImage = ImageIO.read(new File(pathtoWallpaper));

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        g2d.drawImage(homeMenuImage,0,0,null);

        Stroke tmp = g2d.getStroke();

        g2d.setStroke(borderStoke_noDashes);
        g2d.setColor(DASH_BORDER_COLOR);
        g2d.draw(homeMenuController.homeMenuModel.menuFace);

        g2d.setStroke(borderStoke);
        g2d.setColor(BORDER_COLOR);
        g2d.draw(homeMenuController.homeMenuModel.menuFace);

        g2d.setStroke(tmp);

        g2d.setColor(prev);
    }

    /**
     * method to draw all the text in the home menu
     * @param g2d <- to draw the components
     */

    private void drawText(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D greetingsRect = greetingsFont.getStringBounds(GREETINGS,frc);
        Rectangle2D gameTitleRect = gameTitleFont.getStringBounds(GAME_TITLE,frc);
        Rectangle2D creditsRect = creditsFont.getStringBounds(CREDITS,frc);

        int sX,sY;

        sX = (int)(homeMenuController.homeMenuModel.menuFace.getWidth() - greetingsRect.getWidth()) / 2;
        sY = (int)(homeMenuController.homeMenuModel.menuFace.getHeight() / 4);

        g2d.setFont(greetingsFont);
        g2d.drawString(GREETINGS,sX,sY);

        sX = (int)(homeMenuController.homeMenuModel.menuFace.getWidth() - gameTitleRect.getWidth()) / 2;
        sY += (int) gameTitleRect.getHeight() * 1.1;//add 10% of String height between the two strings

        g2d.setFont(gameTitleFont);
        g2d.drawString(GAME_TITLE,sX,sY);

        sX = (int)(homeMenuController.homeMenuModel.menuFace.getWidth() - creditsRect.getWidth()) / 2;
        sY += (int) creditsRect.getHeight() * 1.1;

        g2d.setFont(creditsFont);
        g2d.drawString(CREDITS,sX,sY);


    }

    /**
     *
     * method to draw the buttons(start button, exit button, highscore button and instruction button in the home menu
     * @param g2d <- to draw components
     */

    private void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D txtRect = buttonFont.getStringBounds(START_TEXT,frc);
        Rectangle2D mTxtRect = buttonFont.getStringBounds(MENU_TEXT,frc);
        Rectangle2D HighScRect = buttonFont.getStringBounds(HIGHSCORE_TEXT,frc);

        g2d.setFont(buttonFont);

        int x = (homeMenuController.homeMenuModel.menuFace.width - homeMenuController.homeMenuModel.startButton.width) / 2;
        int y =(int) ((homeMenuController.homeMenuModel.menuFace.height - homeMenuController.homeMenuModel.startButton.height) * 0.5);

        homeMenuController.homeMenuModel.startButton.setLocation(x,y);

        x = (int)(homeMenuController.homeMenuModel.startButton.getWidth() - txtRect.getWidth()) / 2;
        y = (int)(homeMenuController.homeMenuModel.startButton.getHeight() - txtRect.getHeight()) / 2;

        x += homeMenuController.homeMenuModel.startButton.x;
        y += homeMenuController.homeMenuModel.startButton.y + (homeMenuController.homeMenuModel.startButton.height * 0.9);




        if(HomeMenuModel.startClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(homeMenuController.homeMenuModel.startButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(START_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(homeMenuController.homeMenuModel.startButton);
            g2d.drawString(START_TEXT,x,y);
        }

        x = homeMenuController.homeMenuModel.startButton.x;
        y = homeMenuController.homeMenuModel.startButton.y;

        y *= 1.2;

        homeMenuController.homeMenuModel.menuButton.setLocation(x,y);




        x = (int)(homeMenuController.homeMenuModel.menuButton.getWidth() - mTxtRect.getWidth()) / 2;
        y = (int)(homeMenuController.homeMenuModel.menuButton.getHeight() - mTxtRect.getHeight()) / 2;

        x += homeMenuController.homeMenuModel.menuButton.x;
        y += homeMenuController.homeMenuModel.menuButton.y + (homeMenuController.homeMenuModel.startButton.height * 0.9);

        if(HomeMenuModel.menuClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(homeMenuController.homeMenuModel.menuButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(MENU_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(homeMenuController.homeMenuModel.menuButton);
            g2d.drawString(MENU_TEXT,x,y);
        }

        x = homeMenuController.homeMenuModel.menuButton.x;
        y = homeMenuController.homeMenuModel.menuButton.y;

        y += 40;
        homeMenuController.homeMenuModel.highscoreButton.setLocation(x,y);











        x = (int)(homeMenuController.homeMenuModel.highscoreButton.getWidth() - HighScRect.getWidth())/2 ;
        y = (int)(homeMenuController.homeMenuModel.highscoreButton.getHeight() - HighScRect.getHeight()) / 2;

        x += homeMenuController.homeMenuModel.highscoreButton.x;
        y += homeMenuController.homeMenuModel.highscoreButton.y + (homeMenuController.homeMenuModel.menuButton.height * 0.9);

        if(HomeMenuModel.highScoreClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(homeMenuController.homeMenuModel.highscoreButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(HIGHSCORE_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(homeMenuController.homeMenuModel.highscoreButton);
            g2d.drawString(HIGHSCORE_TEXT,x,y);
        }

        x = homeMenuController.homeMenuModel.highscoreButton.x;
        y = homeMenuController.homeMenuModel.highscoreButton.y;
        y += 40;
        homeMenuController.homeMenuModel.InstructionButton.setLocation(x,y);



        x = (int)(homeMenuController.homeMenuModel.InstructionButton.getWidth() - HighScRect.getWidth())/2 ;
        y = (int)(homeMenuController.homeMenuModel.InstructionButton.getHeight() - HighScRect.getHeight()) / 2;

        x += homeMenuController.homeMenuModel.InstructionButton.x;
        y += homeMenuController.homeMenuModel.InstructionButton.y + (homeMenuController.homeMenuModel.highscoreButton.height * 0.9);

        if(HomeMenuModel.instructionclicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(homeMenuController.homeMenuModel.InstructionButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(INSTRUCTION_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(homeMenuController.homeMenuModel.InstructionButton);
            g2d.drawString(INSTRUCTION_TEXT,x,y);
        }






    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent)
    {
        homeMenuController.homeMenuMouseCLicked(mouseEvent);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent)
    {
        homeMenuController.homeMenuMousePressed(mouseEvent,this);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        homeMenuController.homeMenuMouseReleased(mouseEvent,this);
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
        homeMenuController.homeMenuMouseMoved(mouseEvent,this);

    }
}
