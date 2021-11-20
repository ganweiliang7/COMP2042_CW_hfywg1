package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class HighScoreMenu implements ActionListener {
    Font f1 = new Font(Font.DIALOG, Font.BOLD, 20);
    Font f2 = new Font(Font.DIALOG, Font.BOLD, 15);


    private JButton backbutton;
    private HomeMenu homeMenu;
    private GameFrame gameFrame;
    ArrayList<Integer> scores = new ArrayList<>();


   public void showHighScoreMenu(GameFrame frame)
   {


       this.gameFrame = frame;
       this.homeMenu = homeMenu;
       backbutton = new JButton("Back");
       JTextArea txtarea = new JTextArea();
       JLabel index1 = new JLabel("1");
       JLabel index2 = new JLabel("2");
       JLabel index3 = new JLabel("3");
       JLabel index4 = new JLabel("4");
       JLabel index5 = new JLabel("5");
       JLabel index6 = new JLabel("6");
       JLabel index7 = new JLabel("7");



       JLabel Title = new JLabel("HighScore");
       index1.setBounds(140,97,10,10);
       index1.setFont(f2);
       index2.setFont(f2);
       index3.setFont(f2);
       index4.setFont(f2);
       index5.setFont(f2);
       index6.setFont(f2);
       index7.setFont(f2);

       index2.setBounds(140,150,10,15);
       index3.setBounds(140,203,10,15);
       index4.setBounds(140,255,10,15);
       index5.setBounds(140,305,10,15);
       index6.setBounds(140,360,10,15);
       index7.setBounds(140,410,10,15);
       Title.setBounds(200,50,100,50);
       Title.setFont(f1);
       backbutton.setBounds(0, 0, 100, 50);

       backbutton.addActionListener(this);
       frame.setTitle("highScore");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLayout(null);
       frame.setSize(500, 450);


        frame.add(Title);
       frame.add(backbutton);

       txtarea.setBounds(150, 90, 172, 339);

       displayScore(txtarea);
       frame.add(txtarea);
       frame.add(index1);
       frame.add(index2);
       frame.add(index3);
       frame.add(index4);
       frame.add(index5);
       frame.add(index6);
       frame.add(index7);

       frame.getContentPane().setBackground(Color.MAGENTA);
       txtarea.setFont(new Font(Font.DIALOG, Font.BOLD,20));
       txtarea.setBackground(Color.YELLOW);
       frame.setVisible(true);
   }


   public void displayScore(JTextArea txtarea)
   {
       try (Scanner scanner = new Scanner(new File("Score.txt")).useDelimiter("\\s*-\\s*"))
       {
           // \\s* in regular expressions means "any number or whitespaces".
           // We could've said simply useDelimiter("-") and Scanner would have
           // included the whitespaces as part of the data it extracted.
           while (scanner.hasNextLine()) {

               String currLine = scanner.nextLine();
               if (currLine != null && currLine.trim().length() > 0 && currLine.matches("^[0-9]*$"))
               {

                   scores.add(Integer.parseInt(currLine));
               }

           }
       }

       catch (FileNotFoundException e)
       {
           System.out.println("file not found");
       }

       for(Integer a: scores)
       {


           txtarea.append("   "+ Integer.toString(a) + "\n\n");



       }
   }










    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == backbutton)
        {
            gameFrame.dispose();

            EventQueue.invokeLater(() -> new GameFrame().initialize());

        }

    }
}