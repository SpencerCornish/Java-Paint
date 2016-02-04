package main;

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame {

    public static Frame frame;

   Frame( )
   {
       super("Frame");
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       Container c = getContentPane();
       
       c.add(ButtonPanel.getInstance(), BorderLayout.NORTH);
       getContentPane().add(MousePanel.getInstance(), BorderLayout.CENTER);
       setSize(700, 700);
       setVisible(true);
   }
   public static Frame getInstance()
   {
       if(frame == null)
          frame = new Frame();
      
        return frame;
   }
}
