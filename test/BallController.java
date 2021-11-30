package test;

import java.awt.*;
import java.awt.geom.RectangularShape;

public class BallController {
    public void moveTo(Point p){
        Ball.center.setLocation(p);

        RectangularShape tmp = (RectangularShape) Ball.ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((Ball.center.getX() -(w / 2)),(Ball.center.getY() - (h / 2)),w,h);
        Ball.ballFace = tmp;
    }
    public void setPoints(double width,double height){
        Ball.up.setLocation(Ball.center.getX(),Ball.center.getY()-(height / 2));
        Ball.down.setLocation(Ball.center.getX(),Ball.center.getY()+(height / 2));

        Ball.left.setLocation(Ball.center.getX()-(width / 2),Ball.center.getY());
        Ball.right.setLocation(Ball.center.getX()+(width / 2),Ball.center.getY());
    }
    public void setXSpeed(int s){
        Ball.speedX = s;
    }

    public void setYSpeed(int s){
        Ball.speedY = s;
    }

    public void reverseX(){
        Ball.speedX *= -1;
    }

    public void reverseY(){
        Ball.speedY *= -1;
    }

    public void setSpeed(int x, int y){

        Ball.speedX = x;
        Ball.speedY = y;
    }
}
