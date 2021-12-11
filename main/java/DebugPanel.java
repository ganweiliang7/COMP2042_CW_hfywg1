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
package main.java;

import wall.WallController;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Hashtable;


public class DebugPanel extends JPanel {

    private static final Color DEF_BKG = Color.WHITE;


    private JButton skipLevel;
    private JButton resetBalls;

    private JSlider ballXSpeed;
    private JSlider ballYSpeed;

    private JLabel ballXSpeedLabel;
    private JLabel ballYSpeedLabel;

    private WallController wall;

    public DebugPanel(WallController wall){




        this.wall = wall;

        initialize();

        skipLevel = makeButton("Skip Level",e -> wall.nextLevel());
        resetBalls = makeButton("Reset Balls",e -> wall.resetBallCount());

        ballXSpeed = makeXSlider(-4,4,e -> wall.setBallXSpeed(ballXSpeed.getValue()));
        ballYSpeed = makeYSlider(-4,4,e -> wall.setBallYSpeed(ballYSpeed.getValue()));



        this.add(skipLevel);
        this.add(resetBalls);

        this.add(ballXSpeed);
        this.add(ballYSpeed);


    }

    private void initialize(){
        this.setBackground(DEF_BKG);
        this.setLayout(new GridLayout(2,2));
    }

    /**
     * this method creates a button for the user to skip levels/ reset ball
     * @param title <- Title of the button
     * @param e     <- listener to any event
     * @return      <- a button
     */
    private JButton makeButton(String title, ActionListener e){
        JButton out = new JButton(title);
        out.addActionListener(e);
        return  out;
    }

    /**
     * this method is to create a slider that will adjust the speed of the x value of the ball
     * @param min <- minimum value for the x value of the ball
     * @param max <- maximum value for the x value of the ball
     * @param e   <- listener to any change in the slider
     * @return    <- a JSLider to adjust the speed of the x value of the ball
     */

    private JSlider makeXSlider(int min, int max, ChangeListener e){
        JSlider out = new JSlider(min,max);
        out.setMajorTickSpacing(1);
        out.setSnapToTicks(true);
        out.setPaintTicks(true);
        out.addChangeListener(e);
        Hashtable<Integer, JLabel> labels = new Hashtable<>();
        labels.put(min, new JLabel("-4"));
        labels.put(0, new JLabel("BallXSpeed"));
        labels.put(max, new JLabel("4"));
        out.setLabelTable(labels);

        out.setPaintLabels(true);

        return out;
    }
    /**
     * this method is to create a slider that will adjust the speed of the y value of the ball
     * @param min <- minimum value for the y value of the ball
     * @param max <- maximum value for the y value of the ball
     * @param e   <- listener to any change in the slider
     * @return    <- a JSLider to adjust the speed of the y value of the ball
     */
    private JSlider makeYSlider(int min, int max, ChangeListener e){
        JSlider out = new JSlider(min,max);
        out.setMajorTickSpacing(1);
        out.setSnapToTicks(true);
        out.setPaintTicks(true);
        out.addChangeListener(e);
        Hashtable<Integer, JLabel> labels = new Hashtable<>();
        labels.put(min, new JLabel("-4"));
        labels.put(0, new JLabel("BallYSpeed"));
        labels.put(max, new JLabel("4"));
        out.setLabelTable(labels);

        out.setPaintLabels(true);

        return out;
    }


    public void setValues(int x,int y){
        ballXSpeed.setValue(x);
        ballYSpeed.setValue(y);
    }

}
