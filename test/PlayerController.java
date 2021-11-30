package test;

import java.awt.*;

public class PlayerController {


    public void moveTo(Point p){
        Player.ballPoint.setLocation(p);
        Player.playerFace.setLocation(Player.ballPoint.x - (int)Player.playerFace.getWidth()/2,Player.ballPoint.y);
    }

    public void move(){
        double x = Player.ballPoint.getX() + Player.moveAmount;
        if(x < Player.min || x > Player.max)
            return;
        Player.ballPoint.setLocation(x,Player.ballPoint.getY());
        Player.playerFace.setLocation(Player.ballPoint.x - (int)Player.playerFace.getWidth()/2,Player.ballPoint.y);
    }

    public void moveLeft(){
        Player.moveAmount = -Player.DEF_MOVE_AMOUNT;
    }

    public void movRight(){
        Player.moveAmount = Player.DEF_MOVE_AMOUNT;
    }
}
