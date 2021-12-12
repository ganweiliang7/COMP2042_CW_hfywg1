package highscoremenu;

import java.awt.*;
import java.util.ArrayList;

public class HighScoreMenuModel {
    Font titlefont = new Font(Font.DIALOG, Font.BOLD, 30);
    Font f2 = new Font(Font.DIALOG, Font.BOLD, 15);
    Color textareaColour = new Color(0,0,0);
    ArrayList<Integer> scores = new ArrayList<>();




    public Font getTitleFont()
    {
        return titlefont;
    }

    public Color getTextareaColour()
    {
        return textareaColour;
    }
    public Font getF2()
    {
        return f2;
    }
}
