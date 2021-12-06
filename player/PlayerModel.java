package player;

public class PlayerModel {
    public static final int DEF_MOVE_AMOUNT = 5;
    public static int moveAmount;
    public static int min;
    public static int max;


    public static void setPlayerMoveLeft()
    {
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    public static void setPlayerMoveRight()
    {
        moveAmount = DEF_MOVE_AMOUNT;
    }
}
