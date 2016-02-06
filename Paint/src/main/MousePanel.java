package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JPanel;
import java.awt.event.*;

public class MousePanel extends JPanel implements MouseListener, MouseMotionListener {
    
	private static final long serialVersionUID = -8595660419538273421L;
	public static MousePanel inst; //Instance of the MousePanel
    private int button = -1;				//determines which button is pressed based on a number
    Point sPoint = new Point(-1, -1);  //Points used to align shapes and mouse drag
    Point ePoint = new Point(-1, -1);
    
    public MousePanel()
    {
        setBackground(Color.WHITE); // Background Color for canvas
        addMouseListener(this); //Used to do live track, etc.
        addMouseMotionListener(this); 
    }
    public static MousePanel getInstance()
    {
        if(inst == null) // If there is no instance...
            inst =  new MousePanel(); // Make one!
         return inst; // Send back the made instance
        
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g); //Inherits Graphics
        Graphics2D g2 = (Graphics2D)g;
        switch(button){   // Switch on which button was pressed.  There may be a better way
        case 0: 
        	ePoint.x = -1; 			//Cleans line tracking variables for next line made
        	ePoint.y = -1;
            sPoint.x = -1;  
            sPoint.y = -1;
        break;  // The following shapes have weird offsets,as to make the dragging of a shape feel less insane!
        case 1: g2.fillRect(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y); repaint(); break;		// Draw filled rectangle
        case 2: g2.drawRect(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y); repaint(); break; 		// Draw empty rectangle
        case 3: g2.fillOval(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y); repaint(); break; 		// Draw filled oval
        case 4: g2.drawOval(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y); repaint(); break;		// Draw empty oval
        case 5: g2.drawLine(sPoint.x, sPoint.y, ePoint.x, ePoint.y); repaint(); break; 							// Draw Line
        }
        
    }
    
    public void setButton(int button) {  // Sets our button tracking variable
    	this.button = button;
    }
    public void cleanUp()							// Currently on the chopping block
    { 
    	ePoint.x = -1; 								//Cleans line tracking variables for next line made
    	ePoint.y = -1;
        sPoint.x = -1;  
        sPoint.y = -1;
    }
    public void mousePressed(MouseEvent e){		// Initial coords for shape
    	//System.out.println("mouse pressed");
    	e.consume();  
    	ePoint.x = e.getX(); 
    	ePoint.y = e.getY();
        sPoint.x = e.getX();  
        sPoint.y = e.getY();
        //repaint();
    	}
    public void mouseReleased(MouseEvent e){	//Final coords for shape
    	//System.out.println("mouse released");
    	e.consume();  
        ePoint.x = e.getX();  
        ePoint.y = e.getY();
        repaint();
    	}
	public void mouseDragged(MouseEvent e) { 	//makes the shape a live-drag
    	e.consume();  
        ePoint.x = e.getX();  
        ePoint.y = e.getY();
        System.out.println(e.getY() + " x " + e.getX());
        repaint();
		}
	public void mouseMoved(MouseEvent e) { } //This will be useful soon, adding mouse coords to a tooltip in the bottom right corner    
	public void mouseExited(MouseEvent e){//System.out.println("mouse exited");
		}
    public void mouseEntered(MouseEvent e){//System.out.println("mouse entered");
    	}
    public void mouseClicked(MouseEvent e){//System.out.println("mouse clicked");
    }
}
