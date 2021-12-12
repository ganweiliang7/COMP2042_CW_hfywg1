package instructionmenu;

public class InstructionMenuModel
{

    String instruction = "Controls:\n-A to move LEFT\n-D to move Right \n-SPACE to start game or freeze the game \n-ESC to pause the game \n-Alt + Shift + F1 to adjust the settings of the game\n\n Rules\n-The game consist of 5 levels in total\n-Every player gets 3 balls initilally,if ball count reaches\n 0,the player lose";

    /**
     *
     * @return the instruction string
     */
    public String getInstructionString()
    {
        return instruction;
    }



}
