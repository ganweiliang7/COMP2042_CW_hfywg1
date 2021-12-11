package ball;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 *
 *
 */
abstract public class Ball {





    public static Point2D up;
    public static Point2D down;
    public static Point2D left;
    public  static Point2D right;

    /**
     * constructor for the ball
     * @param center (x,y) point of the center of the ball
     * @param radiusA horizontal radius
     * @param radiusB vertical radius
     * @param inner inner colour of the ball
     * @param border border colour of the ball
     */
    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border){
        BallModel.center = center;

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        up.setLocation(center.getX(),center.getY()-(radiusB / 2));
        down.setLocation(center.getX(),center.getY()+(radiusB / 2));

        left.setLocation(center.getX()-(radiusA /2),center.getY());
        right.setLocation(center.getX()+(radiusA /2),center.getY());


        BallModel.ballFace = makeBall(center,radiusA,radiusB);
        BallModel.border = border;
        BallModel.inner  = inner;
        BallModel.speedX = 0;
        BallModel.speedY = 0;
    }

    /**
     * method to make the ball face
     * @param center (x,y) coordinateof the center fo the ball
     * @param radiusA radius of the height of the ball
     * @param radiusB radius of the width of the ball
     * @return a shape
     */
    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);





}
