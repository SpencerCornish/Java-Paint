package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class MousePanel extends JPanel implements MouseListener{

    public static MousePanel inst;
    
    MousePanel()
    {
        setBackground(Color.BLACK);
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
        BufferedImage grid;
        Graphics2D gc;
        if (grid == null) {
        	int w = this.getWidth();
        	int h = this.getHeight();
        	grid = (BufferedImage)(this.createImage(w,h));
        	gc = grid.createGraphics();
        }
        
        g2.drawImage(grid, null, 0, 0);
        Random x = new Random(System.currentTimeMillis());
        int x1 = x.nextInt(500);
        int y1 = x.nextInt(500);
        int x2 = x.nextInt(500);
        int y2 = x.nextInt(500);
        g2.drawRect(x1, y1, x2, y2);
    }

    public void mouseExited(MouseEvent e){ System.out.println("mouse exited"); }
    public void mouseEntered(MouseEvent e){System.out.println("mouse entered");}
    public void mouseClicked(MouseEvent e){ 
         System.out.println("mouse clicked");
    }
    public void mousePressed(MouseEvent e){System.out.println("mouse pressed");}
    public void mouseReleased(MouseEvent e){System.out.println("mouse released");}

}
