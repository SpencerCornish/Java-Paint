package main;

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame {

    public static Frame inst;

   Frame( )
   {
       super("Frame");
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       Container c = getContentPane();
       
       c.add(ButtonPanel.getInstance(), BorderLayout.NORTH);
       getContentPane().add(MousePanel.getInstance(), BorderLayout.CENTER);
       setSize(500, 500);
       setVisible(true);
   }
   public static Frame getInstance()
   {
       if(inst == null)
          inst = new Frame();
      
        return inst;
   }


}
