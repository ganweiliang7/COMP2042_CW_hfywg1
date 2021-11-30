package test;

public class WallController
{




    public void setBallXSpeed(int s){
        Wall.ballController.setXSpeed(s);
    }

    public void setBallYSpeed(int s){
        Wall.ballController.setYSpeed(s);
    }

    public void resetBallCount(){
        Wall.ballCount = 3;
    }

    public void nextLevel()
    {

        Wall.bricks = Wall.levels[Wall.level++];

        Wall.brickCount = Wall.bricks.length;
        if(Wall.level == 5)
        {
            Wall.ballController.setSpeed(5,-5);
        }
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
                    Wall.ballController.reverseY();
                    return b.setImpact(Wall.ball.down, Crack.UP);
                case Brick.DOWN_IMPACT:
                    Wall.ballController.reverseY();
                    return b.setImpact(Wall.ball.up,Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    Wall.ballController.reverseX();
                    return b.setImpact(Wall.ball.right,Crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    Wall.ballController.reverseX();
                    return b.setImpact(Wall.ball.left,Crack.LEFT);
            }
        }
        return false;
    }




}

