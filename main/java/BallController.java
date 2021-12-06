package main.java;

import java.awt.*;
import java.awt.geom.RectangularShape;

public class BallController {
    /**
     * find and set the position of the ball
     */
    public void move(){
        RectangularShape tmp = (RectangularShape) BallModel.ballFace;
        BallModel.center.setLocation((BallModel.center.getX() + BallModel.speedX),(BallModel.center.getY() + BallModel.speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((BallModel.center.getX() -(w / 2)),(BallModel.center.getY() - (h / 2)),w,h);
        this.setPoints(w,h);


        BallModel.ballFace = tmp;
    }

    /**
     * move the ball back to the center of the frame
     * @param p <- (x,y) coordinate
     */
    public void moveTo(Point p){
        BallModel.center.setLocation(p);

        RectangularShape tmp = (RectangularShape) BallModel.ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((BallModel.center.getX() -(w / 2)),(BallModel.center.getY() - (h / 2)),w,h);
        BallModel.ballFace = tmp;
    }

    /**
     * se
     * @param width <- width of the ball
     * @param height <- height of the ball
     */
    public void setPoints(double width,double height){
        Ball.up.setLocation(BallModel.center.getX(), BallModel.center.getY()-(height / 2));
        Ball.down.setLocation(BallModel.center.getX(), BallModel.center.getY()+(height / 2));

        Ball.left.setLocation(BallModel.center.getX()-(width / 2), BallModel.center.getY());
        Ball.right.setLocation(BallModel.center.getX()+(width / 2), BallModel.center.getY());
    }

    public void setXSpeed(int s){
        BallModel.speedX = s;
    }

    public void setYSpeed(int s){
        BallModel.speedY = s;
    }

    public void reverseX(){
        BallModel.speedX *= -1;
    }

    public void reverseY(){
        BallModel.speedY *= -1;
    }

    public void setSpeed(int x, int y){

        BallModel.speedX = x;
        BallModel.speedY = y;
    }
}
