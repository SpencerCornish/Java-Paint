package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.event.*;
import java.util.Random;

public class MousePanel extends JPanel implements MouseListener{

    public static MousePanel inst;
    
    MousePanel()
    {
        setBackground(Color.WHITE);
        addMouseListener(this);
    }
    public static MousePanel getInstance()
    {
        if(inst == null)
            inst =  new MousePanel();
         return inst;
        
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        System.out.println("called");
        Graphics2D g2 = (Graphics2D)g;
        Random x = new Random(System.currentTimeMillis());
        int x1 = x.nextInt(500);
        int y1 = x.nextInt(500);
        int x2 = x.nextInt(500);
        int y2 = x.nextInt(500);
        g2.drawLine(x1, y1, x2, y2);
    }

    public void mouseExited(MouseEvent e){ }
    public void mouseEntered(MouseEvent e){}
    public void mouseClicked(MouseEvent e){ 
         System.out.println("yelp");
         repaint();
    }
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}

}
