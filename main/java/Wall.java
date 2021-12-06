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

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


public class Wall {


    public static Ball ball;
    public static Random rnd;
    public static Rectangle area;


    static Brick[] bricks;
    Player player;
    Levels lvl = new Levels();



    public static Point startPoint;
    public static BallController ballController = new BallController();
    public static PlayerController playerController = new PlayerController();


    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos){

        startPoint = new Point(ballPos);

        WallModel.levels = lvl.makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        WallModel.level = 0;

        WallModel.ballCount = 3;
        WallModel.ballLost = false;

        rnd = new Random();

        makeBall(ballPos);
        int speedX,speedY;

        if(WallModel.level == 5)
        {
            speedX = 5;
            speedY = -5;
        }
        else
        {
            do {
                speedX = rnd.nextInt(5) - 2;
            } while (speedX == 0);
            do {
                speedY = -rnd.nextInt(3);
            } while (speedY == 0);
        }

        ballController.setSpeed(speedX,speedY);

        player = new Player((Point) ballPos.clone(),150,10, drawArea);

        area = drawArea;
        


    }

    private void makeBall(Point2D ballPos){
        ball = new RubberBall(ballPos);
    }










}
