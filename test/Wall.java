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
package test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


public class Wall {


    public static Ball ball;
    private Random rnd;
    private Rectangle area;


    static Brick[] bricks;
    Score score = new Score();
    Player player;
    Levels lvl = new Levels();
    public static Brick[][] levels;

    public static int level;

    private Point startPoint;
    public static int brickCount;
    public static int ballCount;
    private boolean ballLost;
    WallController wallController = new WallController();

    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos){

        this.startPoint = new Point(ballPos);

        levels = lvl.makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        level = 0;

        ballCount = 3;
        ballLost = false;

        rnd = new Random();

        makeBall(ballPos);
        int speedX,speedY;

        if(level == 5)
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

        ball.setSpeed(speedX,speedY);

        player = new Player((Point) ballPos.clone(),150,10, drawArea);

        area = drawArea;


    }


    private void makeBall(Point2D ballPos){
        ball = new RubberBall(ballPos);
    }



    public void move(){
        player.move();
        ball.move();
    }

    public void findImpacts(){
        if(player.impact(ball)){
            ball.reverseY();
        }
        else if(wallController.impactWall()){
            /*for efficiency reverse is done into method impactWall
             * because for every brick program checks for horizontal and vertical impacts
             */

            brickCount--;
            GameBoard.score.incrementScore(10);

        }
        else if(impactBorder()) {
            ball.reverseX();
        }
        else if(ball.getPosition().getY() < area.getY()){
            ball.reverseY();
        }
        else if(ball.getPosition().getY() > area.getY() + area.getHeight()){
            ballCount--;
            ballLost = true;
        }
    }



    private boolean impactBorder(){
        Point2D p = ball.getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

    public int getBrickCount(){
        return brickCount;
    }

    public int getBallCount(){
        return ballCount;
    }

    public boolean isBallLost(){
        return ballLost;
    }

    public void ballReset()
    {
        player.moveTo(startPoint);
        ball.moveTo(startPoint);
        int speedX,speedY;
        System.out.println(level);
        if(level == 5)
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

        ball.setSpeed(speedX,speedY);
        ballLost = false;
        System.out.println(ball.getSpeedX());
        System.out.println(ball.getSpeedY());
    }




    public boolean ballEnd(){
        return ballCount == 0;
    }

    public boolean isDone(){
        return brickCount == 0;
    }



    public boolean hasLevel(){
        return level < levels.length;
    }





}
