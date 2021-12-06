package ball;

import java.awt.*;
import java.awt.geom.Point2D;

public class BallModel
{
    public static Color border;
    public static Color inner;
    public static Shape ballFace;

    public static Point2D center;

    public static int speedX;
    public static int speedY;



    public static Color getBorderColor(){
        return border;
    }

    public static Color getInnerColor(){
        return inner;
    }

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

}
