package bricks;

import gameboard.GameBoard;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class VibraniumBrick extends Brick{
    private static final String NAME = "Vibranium Brick";
    private static final Color DEF_INNER = new Color(0, 20, 255);
    private static final Color DEF_BORDER = Color.WHITE;
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.2;

    private Random rnd;
    private Shape brickFace;

    public VibraniumBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER.darker(),STEEL_STRENGTH);
        rnd = new Random();
        brickFace = super.brickFace;
    }


    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    @Override
    public Shape getBrick() {
        return brickFace;
    }

    public  boolean setImpact(Point2D point , int dir){
        if(super.isBroken())
            return false;
        impact();
        return  super.isBroken();
    }

    public void impact(){
        if(rnd.nextDouble() < STEEL_PROBABILITY){
            GameBoard.scoreController.incrementScore(30);
            super.impact();
        }
    }
}
