package highscoremenu;

import main.java.GameFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class HighScoreMenuController {

    public void highScoreMouseMoved(MouseEvent mouseEvent, HighScoreMenu highScoreMenu)
    {
        Point p = mouseEvent.getPoint();
        if(highScoreMenu.backbutton.contains(p))
            highScoreMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            highScoreMenu.setCursor(Cursor.getDefaultCursor());

    }

    public void highScoreMenuActionPerformed(ActionEvent e, HighScoreMenu highScoreMenu, GameFrame gameFrame)
    {
        if(e.getSource() == highScoreMenu.backbutton)
        {
            gameFrame.dispose();

            EventQueue.invokeLater(() -> new GameFrame().initialize());

        }
    }
}
