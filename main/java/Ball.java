package main.java;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by filippo on 04/09/16.
 *
 */
abstract public class Ball {




    public static Point2D up;
    public static Point2D down;
    public static Point2D left;
    public  static Point2D right;


    BallController ballController = new BallController();

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

    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);





}
