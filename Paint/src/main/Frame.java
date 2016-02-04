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
       
       c.add(ButtonPanel.getInstance(), BorderLayout.WEST);
       getContentPane().add(MousePanel.getInstance(), BorderLayout.CENTER);
       setSize(1000, 1000);
       setVisible(true);
   }
   public static Frame getInstance()
   {
       if(frame == null)
          frame = new Frame();
      
        return frame;
   }


}
