package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class MousePanel extends JPanel implements MouseListener, MouseMotionListener {
    
	private static final long serialVersionUID = -8595660419538273421L;
	public static MousePanel mouseP; 				//Instance of the MousePanel
    private int button = -1;						//determines which button is pressed based on a number
    Point sPoint = new Point(-1, -1);  				//Points used to align shapes and mouse drag
    Point ePoint = new Point(-1, -1);
    private Image image;
    
    public MousePanel() {
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
          public void mousePressed(MouseEvent e) {
            // save coord when mouse is pressed
            sPoint.x = e.getX();
            sPoint.y = e.getY();
          }
        });
        setBackground(Color.WHITE); 			// Background Color for canvas
        addMouseListener(this); 				//Used to do live track, etc.
        addMouseMotionListener(this); 
    }
    public static MousePanel getInstance()
    {
        if(mouseP == null) 						// If there is no instance...
            mouseP =  new MousePanel(); 		// Make one!
         return mouseP; 						// Send back the made instance
        
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g); 				//Inherits Graphics
        Graphics2D g2 = (Graphics2D)g;
        Graphics2D g3 = (Graphics2D)g;
        switch(button){   						// Switch on which button was pressed.  There may be a better way
        case 0: 
        	ePoint.x = -1; 						//Cleans line tracking variables for next line made
        	ePoint.y = -1;
            sPoint.x = -1;  
            sPoint.y = -1;
            break;  // The following shapes have weird offsets,as to make the dragging of a shape feel less insane!
<<<<<<< Upstream, based on branch 'master' of https://github.com/SpencerCornish/Paint-prog.git
        case 1: Shape rF = new Shape(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y); rF.draw(g2, 1); break;		// Draw filled rectangle
        case 2: Shape rE = new Shape(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y); rE.draw(g2, 2); break; 		// Draw empty rectangle
        case 3: Shape oF = new Shape(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y); oF.draw(g2, 3); break; 		// Draw filled oval
        case 4: Shape oE = new Shape(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y); oE.draw(g2, 4); break;		// Draw empty oval
        case 5: Shape ln = new Shape(sPoint.x, sPoint.y, ePoint.x, ePoint.y); ln.draw(g2, 5); break; 						// Draw Line
=======
        //case 1: Shape rF = new Shape(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y); shapes.add(rF); rF.draw(g2, 1); break;		// Draw filled rectangle
        case 1: Rectangle2D r2 = new Rectangle2D.Float(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y);  g2.fill(r2); break;
        case 2: Shape rE = new Shape(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y); rE.draw(g, 2); shapes.add(rE); break; 		// Draw empty rectangle
        case 3: Shape oF = new Shape(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y); oF.draw(g, 3); shapes.add(oF); break; 		// Draw filled oval
        case 4: Shape oE = new Shape(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y); oE.draw(g, 4); shapes.add(oE); break;		// Draw empty oval
        case 5: Shape ln = new Shape(sPoint.x, sPoint.y, ePoint.x, ePoint.y); ln.draw(g, 5); shapes.add(ln); break; 						// Draw Line
>>>>>>> 087bd03 Fixed
        }
    }
    
    public void setButton(int button) {  			// Sets our button tracking variable
    	this.button = button;
    }
    public void cleanUp()							// Currently on the chopping block
    { 
    	ePoint.x = -1; 								//Cleans line tracking variables for next line made
    	ePoint.y = -1;
        sPoint.x = -1;  
        sPoint.y = -1;
    }
    public void mousePressed(MouseEvent e){			// Initial coords for shape
    	//System.out.println("mouse pressed");
    	e.consume();  
    	ePoint.x = e.getX(); 
    	ePoint.y = e.getY();
        sPoint.x = e.getX();  
        sPoint.y = e.getY();
        //repaint();
    	}
    public void mouseReleased(MouseEvent e){		//Final coords for shape
    	//System.out.println("mouse released");
    	e.consume();  
        ePoint.x = e.getX();  
        ePoint.y = e.getY();
        //repaint();
    	}
	public void mouseDragged(MouseEvent e) { 		//makes the shape a live-drag
    	//e.consume();  
        //ePoint.x = e.getX();  
       // ePoint.y = e.getY();
       // System.out.println(e.getY() + " x " + e.getX());
       // repaint();
>>>>>>> 087bd03 Fixed
		}
	public void mouseMoved(MouseEvent e) { } //This will be useful soon, adding mouse coords to a tooltip in the bottom right corner    
	public void mouseExited(MouseEvent e){//System.out.println("mouse exited");
		}
    public void mouseEntered(MouseEvent e){//System.out.println("mouse entered");
    	}
    public void mouseClicked(MouseEvent e){//System.out.println("mouse clicked");
    }
}
