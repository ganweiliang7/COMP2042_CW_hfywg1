package ball;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

public class BallModel
{
    public static Color border;
    public static Color inner;
    public static Shape ballFace;

    public static Point2D center;

    public static int speedX;
    public static int speedY;


    /**
     *
     * @return colour of the ball border
     */
    public static Color getBorderColor(){
        return border;
    }

    /**
     *
     * @return colour of the inner part of the ball
     */
    public static Color getInnerColor(){
        return inner;
    }

    /**
     *
     * @return center of the ball
     */
    public static Point2D getPosition(){
        return center;
    }

    public static Shape getBallFace(){
        return ballFace;
    }





    public static int getSpeedX(){
        return speedX;
    }

    public static int getSpeedY(){
        return speedY;
    }

    /**
     * set the center of the ball to a particular position
     */
    public static void setCenterLocation()
    {
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
    }

    public static void setSpeedX(int x)
    {
        speedX = x;
    }
    public static void setSpeedY(int y)

    {
        speedY = y;
    }

    /**
     * reverse the x speed of the ball
     */
    public static void setReverseX()
    {
        speedX*=-1;
    }

    /**
     * reverse y speed of the ball
     */
    public static  void setReverseY()
    {
        speedY*=-1;
    }

    /**
     *set the up position of the ball
     * @param height height of the ball
     */
    public static void setBallUpPosition(double height)
    {
        Ball.up.setLocation(BallModel.center.getX(), BallModel.center.getY()-(height / 2));
    }

    /**
     * set down position of the ball
     * @param height height of the ball
     */
    public static void setBallDownPosition(double height)
    {
        Ball.down.setLocation(BallModel.center.getX(), BallModel.center.getY()+(height / 2));
    }

    /**
     * set left position of the ball
     * @param width width of the ball
     */
    public static void setBallLeftPosition(double width)
    {
        Ball.left.setLocation(BallModel.center.getX()-(width / 2), BallModel.center.getY());
    }

    /**
     * set right position of the ball
     * @param width width of the ball
     */
    public static void setBallRightPosition(double width)
    {
        Ball.right.setLocation(BallModel.center.getX()+(width / 2), BallModel.center.getY());
    }

    /**
     * get x coordinate of the center of the ball
     * @return x coordinate of the center of the ball
     */

    public static double getBallCenterX()
    {
        return center.getX();
    }

    /**
     *
     * @return y coordinate of the center of the ball
     */
    public static double getBallCenterY()
    {
        return center.getY();
    }

    public static void setBallFace(RectangularShape tmp)
    {
        ballFace = tmp;
    }





}
