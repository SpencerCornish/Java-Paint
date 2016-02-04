package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class MousePanel extends JPanel implements MouseListener /*, MouseMotionListener */{	//maybe need to use motion listener to draw stuff
    //BufferedImage grid;
    //Graphics2D gc;
    public static MousePanel inst;
    private int button = -1;	//determines which button is pressed based on a number
    Point sPoint = new Point(-1, -1); 
    Point ePoint = new Point(-1, -1);
    
    MousePanel()
    {
        setBackground(Color.GREEN);
        addMouseListener(this);
        //addMouseMotionListener(this);
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
        
        //System.out.println("called "+ g);
        Graphics2D g2 = (Graphics2D)g;
        /*if (grid == null) {				//this commented out code is what is creating random grey box in mousepanel
        	int w = this.getWidth();
        	int h = this.getHeight();
        	grid = (BufferedImage)(this.createImage(w,h));
        	gc = grid.createGraphics();
        /
        g2.drawImage(grid, null, 0, 0); */
        switch(button){
        case 0: MousePanel.getInstance().removeAll(); break;
        case 1: g2.fillRect(100, 200, 200, 100); break;	//draw filled rectangle
        case 2: g2.drawRect(100, 200, 200, 100); break; //draw empty rectangle
        case 3: g2.fillOval(100, 200, 200, 200); break; //draw filled oval
        case 4: g2.drawOval(100, 200, 200, 200); break;	//draw empty oval
        case 5: g2.drawLine(sPoint.x, sPoint.y, ePoint.x, ePoint.y); repaint(); break; 
        }
        
    }
    
    public void setButton(int button) {
    	this.button = button;
    }
    public void mouseExited(MouseEvent e){System.out.println("mouse exited");}
    public void mouseEntered(MouseEvent e){System.out.println("mouse entered");}
    public void mouseClicked(MouseEvent e){System.out.println("mouse clicked");}
    public void mousePressed(MouseEvent e){
    	System.out.println("mouse pressed");
    	e.consume();  
    	ePoint.x = e.getX(); 
    	ePoint.y = e.getY();
        sPoint.x = e.getX();  
        sPoint.y = e.getY();
        repaint();
    	}
    public void mouseReleased(MouseEvent e){
    	System.out.println("mouse released");
    	e.consume();  
        ePoint.x = e.getX();  
        ePoint.y = e.getY();
        repaint();
    	}
	//public void mouseDragged(MouseEvent e) {//System.out.println("mouse dragged");} //There are here because of implementations
	//public void mouseMoved(MouseEvent e) {//System.out.println("mouse moved");}
}
