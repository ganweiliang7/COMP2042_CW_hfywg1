package instructionmenu;

import main.java.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InstructionMenuController {
    /**
     * actions performed in instruction menu on back button
     * @param e action
     * @param instructionMenu instruction menu view
     * @param gameFrame game frame
     */
    public void instructionMenuActionPerformed(ActionEvent e, InstructionMenu instructionMenu, JFrame gameFrame)
    {
        if(e.getSource() == instructionMenu.backbutton)
        {
            gameFrame.dispose();

            EventQueue.invokeLater(() -> new GameFrame().initialize());

        }
    }
}
