package highscoremenu;

import main.java.GameFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HighScoreMenu extends JComponent implements ActionListener, MouseMotionListener {
    Font titlefont = new Font(Font.DIALOG, Font.BOLD, 30);
    Font f2 = new Font(Font.DIALOG, Font.BOLD, 15);
    Color textareaColour = new Color(0,0,0);


    private JButton backbutton;
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


    /**
     * set wallpaper for the highscore menu and call the methods to draw the buttons and textarea
     * @param frame gameframe
     */
   public void showHighScoreMenu(GameFrame frame)
   {
       this.gameFrame = frame;
       BufferedImage homeMenuImage = null;
       // read from the file
       String pathtoWallpaper = "src/main/resources/HighScoreMenuWallpaper.jpg";
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
       drawComponent(frame);//function to call functions to draw the buttons,labels and text areas
       JLabel Title = new JLabel("HighScore");
       Title.setBounds(150,20,200,50);
       Title.setForeground(Color.BLACK);
       Title.setFont(titlefont);
       frame.add(Title);
       frame.setVisible(true);
   }
    public void drawComponent(GameFrame frame) // draw the buttons and textarea for highscoremenu
    {

        drawIndex(frame);
        drawButton(frame);
        drawTextArea(frame);


    }
    public void drawTextArea(GameFrame frame) // text area that contains the top 7 scores read from the file Score.txt
    {
        JTextArea txtarea = new JTextArea();
        txtarea.setBounds(150, 90, 172, 339);
        displayScore(txtarea);
        frame.add(txtarea);
        txtarea.setFont(new Font(Font.DIALOG, Font.BOLD,20));
        txtarea.setOpaque(false);
        txtarea.setForeground(textareaColour);

    }
    public void drawButton(JFrame frame) //draw the back button at the top left corner of highscore menu
    {
        backbutton = new JButton("Back");
        backbutton.setForeground(textareaColour);
        backbutton.setForeground(new Color(255,255,255));
        backbutton.setBounds(0, 0, 100, 50);
        backbutton.addActionListener(this);

        backbutton.setBackground(new Color(0,0,0));

        frame.add(backbutton);

    }
    public void drawIndex(JFrame frame) // draw index (1-7) for the highscores
    {
        JLabel index1 = new JLabel("1");
        JLabel index2 = new JLabel("2");
        JLabel index3 = new JLabel("3");
        JLabel index4 = new JLabel("4");
        JLabel index5 = new JLabel("5");
        JLabel index6 = new JLabel("6");
        JLabel index7 = new JLabel("7");


        index1.setFont(f2);
        index2.setFont(f2);
        index3.setFont(f2);
        index4.setFont(f2);
        index5.setFont(f2);
        index6.setFont(f2);
        index7.setFont(f2);
        index1.setBounds(140,97,10,10);
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
        index1.setForeground(Color.BLACK);
        index2.setForeground(Color.BLACK);
        index3.setForeground(Color.BLACK);
        index4.setForeground(Color.BLACK);
        index5.setForeground(Color.BLACK);
        index6.setForeground(Color.BLACK);
        index7.setForeground(Color.BLACK);


    }

   public void displayScore(JTextArea txtarea) //function to display score at the textarea
   {
       try (Scanner scanner = new Scanner(new File("Score.txt")).useDelimiter("\\s*-\\s*"))
       {
           // \\s* in regular expressions means "any number or whitespaces".
           // We could've said simply useDelimiter("-") and Scanner would have
           // included the whitespaces as part of the data it extracted.
           while (scanner.hasNextLine()) {

               String currLine = scanner.nextLine();// scanner read file line by line
               if (currLine != null && currLine.trim().length() > 0 && currLine.matches("^[0-9]*$"))//scanner reads integer
               {

                   scores.add(Integer.parseInt(currLine)); // add the integers to an arraylist - scores
               }

           }
       }

       catch (FileNotFoundException e)
       {
           System.out.println("file not found");
       }

       for(Integer a: scores)
       {


           txtarea.append("   "+ Integer.toString(a) + "\n\n");// append the scores to textarea



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