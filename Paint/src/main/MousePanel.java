package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.image.BufferedImage;


public class MousePanel extends JPanel implements MouseListener, MouseMotionListener {
    
	private static final long serialVersionUID = -8595660419538273421L;
	public static MousePanel mouseP; 				//Instance of the MousePanel
    private int button = -1;						//determines which button is pressed based on a number
    private Point sPoint = new Point(-1, -1);  				//Points used to align shapes and mouse drag
    private Point ePoint = new Point(-1, -1);
    private BufferedImage bufferImg;
    private Graphics g;
    public MousePanel() {
        
    	
    	//setDoubleBuffered(false);       
        //addMouseListener(new MouseAdapter() {
          //public void mousePressed(MouseEvent e) {
            // save coord when mouse is pressed
         // sPoint.x = e.getX();
           // sPoint.y = e.getY();
        //  }
       // });
    	
    	//FIX ME FIX ME
    	bufferImg = new BufferedImage(50,50,BufferedImage.TYPE_INT_RGB); // FIX ME FIX ME FIX ME FIX ME
    	//FIX ME FIX ME 
    	
    	Graphics buffer = bufferImg.getGraphics();
    	buffer.setColor(Color.ORANGE);
    	buffer.fillRect(0, 0, bufferImg.getWidth(),bufferImg.getHeight());
        addMouseListener(this); 				//Used to do live track, etc.
        addMouseMotionListener(this); 
        repaint();
    }
    public static MousePanel getInstance()
    {
        if(mouseP == null) 						// If there is no instance...
            mouseP =  new MousePanel(); 		// Make one!
         return mouseP; 						// Send back the made instance
        
    }

    public void paintComponent(Graphics g)
    {
    	g.drawImage(bufferImg, 0, 0, getWidth(), getHeight(), null);
    	RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    	((Graphics2D) g).setRenderingHints(rh);
    }
    
    public void setButton(int button) {  			// Sets our button tracking variable
    	this.button = button;

    	//buffer.fillRect(30,30, 300, 100);
    	//repaint();
    	//ePoint.x = 20; 						//Cleans line tracking variables for next line made
    	//ePoint.y = 20;
        //sPoint.x = 200;  
        //sPoint.y = 200;
    }
    public void clearAll()
    {
    	Graphics buffer = bufferImg.createGraphics();
    	buffer.setColor(Color.WHITE);
    	buffer.fillRect(0, 0, bufferImg.getHeight(), bufferImg.getWidth());
    	repaint();
    	
    }
    public void mousePressed(MouseEvent e){			// Initial coords for shape
    	System.out.println("mouse pressed");
    	e.consume();
        sPoint.x = e.getX();  
        sPoint.y = e.getY();
    	}
    public void mouseReleased(MouseEvent e){		//Final coords for shape
    	System.out.println("mouse released");
    	Graphics buffer = bufferImg.createGraphics();
    	buffer.setColor(Color.RED);
    	e.consume();  
        ePoint.x = e.getX();  
        ePoint.y = e.getY();
        switch(button){   						// Switch on which button was pressed.  There may be a better way
        case 0: break;  // The following shapes have weird offsets,as to make the dragging of a shape feel less insane!
        case 1: buffer.fillRect(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y);  break;		// Draw filled rectangle
        case 2: buffer.drawRect(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y);  break; 		// Draw empty rectangle
        case 3: buffer.fillOval(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y);  break; 		// Draw filled oval
        case 4: buffer.drawOval(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y);  break;		// Draw empty oval
        case 5: buffer.drawLine(sPoint.x, sPoint.y, ePoint.x, ePoint.y); break; 							// Draw Line
        default:break;
        }
        repaint();
    	}
	public void mouseDragged(MouseEvent e) { 		//makes the shape a live-drag
    	//e.consume();  
        //ePoint.x = e.getX();  
       // ePoint.y = e.getY();
        System.out.println(e.getY() + " x " + e.getX());
       // repaint();
		}
	public void mouseMoved(MouseEvent e) { } //This will be useful soon, adding mouse coords to a tooltip in the bottom right corner    
	public void mouseExited(MouseEvent e){//System.out.println("mouse exited");
		}
    public void mouseEntered(MouseEvent e){//System.out.println("mouse entered");
    	}
    public void mouseClicked(MouseEvent e){//System.out.println("mouse clicked");
    }
}
