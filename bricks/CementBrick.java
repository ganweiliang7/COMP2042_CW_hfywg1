package bricks;

import gameboard.GameBoard;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;


public class CementBrick extends Brick {


    private static final String NAME = "Cement Brick";
    private static final Color DEF_INNER = new Color(147, 147, 147);
    private static final Color DEF_BORDER = new Color(217, 199, 175);
    private static final int CEMENT_STRENGTH = 2;
    private Crack crack;
    private Shape brickFace;

    /**
     * Constructor for cement brick
     * @param point (x,y) coordinate
     * @param size size of the brick
     */
    public CementBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
        crack = new Crack(DEF_CRACK_DEPTH,DEF_STEPS);
        brickFace = super.brickFace;
    }

    @Override

    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     *
     * @param point point of impact
     * @param dir direction of the impact
     * @return a boolean value that states whether the brick is broken or not
     *
     */
    @Override
    public boolean setImpact(Point2D point, int dir)
    {
        if(super.isBroken())
            return false;
        super.impact();
        if(!super.isBroken()){
            crack.makeCrack(point,dir,this);
            updateBrick();
            GameBoard.scoreController.incrementScore(10);
            return false;
        }
        return true;
    }

    /**
     *
     * @return a shape of the brickface
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }

    private void updateBrick(){
        if(!super.isBroken()){
            GeneralPath gp = crack.draw();
            gp.append(super.brickFace,false);
            brickFace = gp;
        }
    }

    /**
     * repair cement brick
     */
    public void repair(){
        super.repair();
        crack.reset();
        brickFace = super.brickFace;
    }
}
