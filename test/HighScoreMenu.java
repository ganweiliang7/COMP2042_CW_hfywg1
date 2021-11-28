package test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HighScoreMenu extends JComponent implements ActionListener, MouseMotionListener {
    Font f1 = new Font(Font.DIALOG, Font.BOLD, 20);
    Font f2 = new Font(Font.DIALOG, Font.BOLD, 15);
    Color textareaColour = new Color(200,100,0);


    private JButton backbutton;
    private HomeMenu homeMenu;
    private GameFrame gameFrame;

    ArrayList<Integer> scores = new ArrayList<>();

    class ImagePanel extends JComponent {
        private Image image;
        public ImagePanel(Image image) {
            this.image = image;
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }




   public void showHighScoreMenu(GameFrame frame)
   {
       this.gameFrame = frame;
       BufferedImage homeMenuImage = null;
       String pathtoWallpaper = "resource/HighScoreMenuWallpaper.jpg";
       try
       {
           homeMenuImage = ImageIO.read(new File(pathtoWallpaper));

       }
       catch(IOException e)
       {
           e.printStackTrace();
       }



       frame.setTitle("highScore");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLayout(null);
       frame.setSize(500, 450);
       frame.setContentPane(new ImagePanel(homeMenuImage));
       drawComponent(frame);
       JLabel Title = new JLabel("HighScore");
       Title.setBounds(150,50,100,50);
       Title.setForeground(Color.white);
       Title.setFont(f1);
       frame.add(Title);
       frame.setVisible(true);
   }
    public void drawComponent(GameFrame frame) // draw the buttons and textarea for highscoremenu
    {

        drawIndex(frame);
        drawButton(frame);
        drawTextArea(frame);


    }
    public void drawTextArea(GameFrame frame)
    {
        JTextArea txtarea = new JTextArea();
        txtarea.setBounds(150, 90, 172, 339);
        displayScore(txtarea);
        frame.add(txtarea);
        txtarea.setFont(new Font(Font.DIALOG, Font.BOLD,20));
        txtarea.setBackground(new Color(0,50,50,150));
        txtarea.setForeground(textareaColour);

    }
    public void drawButton(JFrame frame)
    {
        backbutton = new JButton("Back");
        backbutton.setForeground(textareaColour);
        backbutton.setBounds(0, 0, 100, 50);
        backbutton.addActionListener(this);

        backbutton.setBackground(new Color(0,50,50,150));

        frame.add(backbutton);

    }
    public void drawIndex(JFrame frame)
    {
        JLabel index1 = new JLabel("1");
        JLabel index2 = new JLabel("2");
        JLabel index3 = new JLabel("3");
        JLabel index4 = new JLabel("4");
        JLabel index5 = new JLabel("5");
        JLabel index6 = new JLabel("6");
        JLabel index7 = new JLabel("7");

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
        frame.add(index1);
        frame.add(index2);
        frame.add(index3);
        frame.add(index4);
        frame.add(index5);
        frame.add(index6);
        frame.add(index7);

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
    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent mouseEvent)
   {
        Point p = mouseEvent.getPoint();
        if(backbutton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

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