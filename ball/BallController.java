package ball;

import java.awt.*;
import java.awt.geom.RectangularShape;

public class BallController {
    /**
     * find and set the position of the ball
     */
    public void move(){
        RectangularShape tmp = (RectangularShape) BallModel.ballFace;
        BallModel.setCenterLocation();
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((BallModel.getBallCenterX() -(w / 2)),(BallModel.getBallCenterY() - (h / 2)),w,h);
        this.setPoints(w,h);


        BallModel.setBallFace(tmp);
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
     * set the position of the ball
     * @param width <- width of the ball
     * @param height <- height of the ball
     */
    public void setPoints(double width,double height){
       BallModel.setBallUpPosition(height);
       BallModel.setBallDownPosition(height);
       BallModel.setBallLeftPosition(width);
       BallModel.setBallRightPosition(width);
    }


    public void setXSpeed(int s){
        BallModel.setSpeedX(s);
    }

    public void setYSpeed(int s){
        BallModel.setSpeedY(s);
    }

    /**
     * reverse the x speed of the ball
     */
    public void reverseX(){
        BallModel.setReverseX();
    }

    /**
     * reverse the y speed of the ball
     */
    public void reverseY(){
        BallModel.setReverseY();
    }

    public void setSpeed(int x, int y){

        BallModel.setSpeedX(x);
        BallModel.setSpeedY(y);
    }
}
