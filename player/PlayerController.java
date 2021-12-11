package player;

import ball.Ball;
import ball.BallModel;

import java.awt.*;

public class PlayerController {




    /**
     * move the slider to the center position
     * @param p <- (x,y) coordinate
     */
    public void moveTo(Point p){
        Player.ballPoint.setLocation(p);
        Player.playerFace.setLocation(Player.ballPoint.x - (int)Player.playerFace.getWidth()/2,Player.ballPoint.y);
    }

    /**
     * set the position of the player(slider)
     */
    public void move(){
        double x = Player.ballPoint.getX() + PlayerModel.moveAmount;
        if(x < PlayerModel.min || x > PlayerModel.max)

            return;
        Player.ballPoint.setLocation(x,Player.ballPoint.getY());
        Player.playerFace.setLocation(Player.ballPoint.x - (int)Player.playerFace.getWidth()/2,Player.ballPoint.y);
    }

    public void moveLeft()
    {
        PlayerModel.setPlayerMoveLeft();
    }

    public void movRight()
    {
        PlayerModel.setPlayerMoveRight();

    }

    /**
     *
     * @param b <- ball
     * @return <- a boolean value that states whether the ball hits the slider or not
     */
    public boolean impact(Ball b){
        return Player.playerFace.contains(BallModel.getPosition()) && Player.playerFace.contains(Ball.down) ;
    }
}
