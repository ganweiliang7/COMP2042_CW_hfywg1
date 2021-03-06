package bricks;

import ball.Ball;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by filippo on 04/09/16.
 *
 */
abstract public class Brick  {

    public static final int MIN_CRACK = 1;
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;


    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;


    Shape brickFace;

    private Color border;
    private Color inner;
    private String name;

    private int fullStrength;
    private int strength;

    private boolean broken;

    /**
     * Constructor for Brick
     * @param name name of the brick
     * @param pos position of the brick
     * @param size size of the brick
     * @param border border colour of the brick
     * @param inner inner colour of the brick
     * @param strength stength of the brick
     */
    public Brick(String name, Point pos,Dimension size,Color border,Color inner,int strength){

        broken = false;
        this.name = name;
        brickFace = makeBrickFace(pos,size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }

    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    /**
     * set Impact on the Brick
     * @param point point of impact
     * @param dir direction of the impact
     * @return boolean value which indicate whether the brick is broken or not
     *
     */
    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }

    public abstract Shape getBrick();



    public Color getBorderColor(){
        return  border;
    }

    public Color getInnerColor(){
        return inner;
    }

    /**
     *
     * @param b <- ball
     * @return the position of the ball impact on the brick
     */

    public final int findImpact(Ball b){
        if(broken)
            return 0;
        int out  = 0;
        if(brickFace.contains(b.right))
            out = LEFT_IMPACT;
        else if(brickFace.contains(b.left))
            out = RIGHT_IMPACT;
        else if(brickFace.contains(b.up))
            out = DOWN_IMPACT;
        else if(brickFace.contains(b.down))
            out = UP_IMPACT;
        return out;
    }

    /**
     *
     *
     * @return a boolean value that states whether the brick is broken or not
     */
    public final boolean isBroken(){
        return broken;
    }
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     * decrease the strength and check if brick is broken
     */
    public void impact(){
        strength--;
        broken = (strength == 0);
    }



}





