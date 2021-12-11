package wall;

import ball.Ball;
import ball.BallModel;
import bricks.Brick;
import bricks.Crack;
import gameboard.GameBoard;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.geom.Point2D;
import java.io.IOException;

public class WallController
{

    public void setBallXSpeed(int s){
        Wall.ballController.setXSpeed(s);
    }

    public void setBallYSpeed(int s){
        Wall.ballController.setYSpeed(s);
    }

    public void resetBallCount(){
        WallModel.setBallCount(3);
    }

    public void nextLevel()
    {

        Wall.bricks = WallModel.levels[WallModel.level++];

        WallModel.setBrickCount(Wall.bricks.length);
        if(WallModel.level == 5)
        {
            Wall.ballController.setSpeed(5,-5); // set the ball speed to be fixed to a higher speed of 5 if level == 5
        }
    }


    public void wallReset(){
        for(Brick b : Wall.bricks)
            b.repair();
        WallModel.setBrickCount(Wall.bricks.length);
        WallModel.setBallCount(3);
    }

    /**
     * Impact bricks
     * @return <- a boolean value that states whether the brick is broken or not
     */
    public boolean impactWall()
    {

        for(Brick b : Wall.bricks){
            switch(b.findImpact(Wall.ball)) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    Wall.ballController.reverseY();
                    return b.setImpact(Ball.down, Crack.UP);
                case Brick.DOWN_IMPACT:
                    Wall.ballController.reverseY();
                    return b.setImpact(Ball.up,Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    Wall.ballController.reverseX();
                    return b.setImpact(Ball.right,Crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    Wall.ballController.reverseX();
                    return b.setImpact(Ball.left,Crack.LEFT);
            }
        }
        return false;
    }

    /**
     * find the movement of the player(slider) and also the ball
     */
    public void move(){
        Wall.playerController.move();
        Wall.ballController.move();
    }

    /**
     * find the various type of impacts (e.g. ball impact with the slider, ball impact with the border or ball impact with the bricks and reverse the ball posiiton
     * if no impact,i.e. ball falls below the frame, then decrement ball count
     */
    public void findImpacts() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if(Wall.playerController.impact(Wall.ball)){
            Wall.ballController.reverseY();

        }
        else if(this.impactWall()){
            /*for efficiency reverse is done into method impactWall
             * because for every brick program checks for horizontal and vertical impacts
             */
            WallModel.brickDestroyedSound();
            WallModel.decrementBrickCount();
            GameBoard.scoreController.incrementScore(10);


        }
        else if(impactBorder()) {
            Wall.ballController.reverseX();

        }
        else if(BallModel.getPosition().getY() < Wall.area.getY()){
            Wall.ballController.reverseY();

        }
        else if(BallModel.getPosition().getY() > Wall.area.getY() + Wall.area.getHeight()){
            WallModel.decrementBallCount();
            WallModel.setBallLostTrue();

        }
    }


    /**
     *
     * @return <- a boolean value that states whether the ball hit the left/right border of the frame
     */
    private boolean impactBorder(){
        Point2D p = BallModel.getPosition();
        return ((p.getX() < Wall.area.getX()) ||(p.getX() > (Wall.area.getX() + Wall.area.getWidth())));
    }

    /**
     * Reset the ball position and set the initial speed of the ball based on the level.
     */
    public void ballReset()
    {
        Wall.playerController.moveTo(Wall.startPoint);
        Wall.ballController.moveTo(Wall.startPoint);
        int speedX,speedY;

        if(WallModel.getLevel() == 5)
        {
            speedX = 5;
            speedY = -5;
        }
        else
        {
            do {
                speedX = Wall.rnd.nextInt(5) - 2;
            } while (speedX == 0);
            do {
                speedY = -Wall.rnd.nextInt(3);
            } while (speedY == 0);
        }

        Wall.ballController.setSpeed(speedX,speedY);
        WallModel.setBallLostFalse();

    }
}

