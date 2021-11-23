package test;

public class WallController
{




    public void setBallXSpeed(int s){
        Wall.ball.setXSpeed(s);
    }

    public void setBallYSpeed(int s){
        Wall.ball.setYSpeed(s);
    }

    public void resetBallCount(){
        Wall.ballCount = 3;
    }

    public void nextLevel()
    {

        Wall.bricks = Wall.levels[Wall.level++];

        Wall.brickCount = Wall.bricks.length;
    }
    public void wallReset(){
        for(Brick b : Wall.bricks)
            b.repair();
        Wall.brickCount = Wall.bricks.length;
        Wall.ballCount = 3;
    }
    public boolean impactWall()
    {

        for(Brick b : Wall.bricks){
            switch(b.findImpact(Wall.ball)) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    Wall.ball.reverseY();
                    return b.setImpact(Wall.ball.down, Crack.UP);
                case Brick.DOWN_IMPACT:
                    Wall.ball.reverseY();
                    return b.setImpact(Wall.ball.up,Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    Wall.ball.reverseX();
                    return b.setImpact(Wall.ball.right,Crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    Wall.ball.reverseX();
                    return b.setImpact(Wall.ball.left,Crack.LEFT);
            }
        }
        return false;
    }




}

