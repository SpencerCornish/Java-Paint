package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;


public class MousePanel extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = -8595660419538273421L;
	public static MousePanel mouseP; 				//Instance of the MousePanel
	private int button = -1;						//determines which button is pressed based on a number
	private Point sPoint = new Point(-1, -1);  		//Points used to align shapes and mouse drag
	private Point ePoint = new Point(-1, -1);
	private BufferedImage bufferImg;				//buffered image to draw on
	private BufferedImage bufferImgLive;
	private boolean paintStatusLive = false;
	public MousePanel() {
		bufferImg = new BufferedImage(5000,5000, BufferedImage.TYPE_INT_RGB); 
		Graphics buffer = bufferImg.getGraphics();
		buffer.setColor(Color.WHITE);
		buffer.fillRect(0, 0, bufferImg.getWidth(),bufferImg.getHeight());
		addMouseListener(this); 				//Used to do live track, etc.
		addMouseMotionListener(this); 
		repaint(); 
	}
	public static MousePanel getInstance()
	{
		if(mouseP == null) 						// If there is no instance...
			mouseP =  new MousePanel(); 		// Make one!
		return mouseP; 							// Send back the made instance
	}

	public void paintComponent(Graphics g)	//no call to super so override fully works
	{
		if (bufferImg == null) {		//create new image if none exist
			bufferImg = (BufferedImage) createImage(getSize().width, getSize().height);
			g = (Graphics2D) bufferImg.getGraphics();
			clearAll();
		}
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);	//makes shapes look better
		RenderingHints rh2 = new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		((Graphics2D) g).setRenderingHints(rh);
		((Graphics2D) g).setRenderingHints(rh2);
		if(paintStatusLive == true)	//live drag
		{
			g.drawImage(bufferImgLive, 0, 0, null);
		}
		else
		{
			g.drawImage(bufferImg, 0, 0, null);			
		}
		
	}

	public void setButton(int button) {  			// Sets our button tracking variable
		this.button = button;
	}
	public void clearAll()		//clears mousepanel by painting over the background image
	{
		Graphics buffer = bufferImg.createGraphics();
		buffer.setColor(Color.WHITE);
		buffer.fillRect(0, 0, bufferImg.getHeight(), bufferImg.getWidth());
		repaint();
	}
	public void fixDirections()	//allows user to draw from any direction
	{
		if(button != 5){
			if(sPoint.x > ePoint.x){
				int sTemp = sPoint.x;
				sPoint.x = ePoint.x;
				ePoint.x = sTemp;
			}
			if(sPoint.y > ePoint.y){
				int sTemp = sPoint.y;
				sPoint.y = ePoint.y;
				ePoint.y = sTemp;
			}
		}

	}
	public void mousePressed(MouseEvent e){			// Initial coords for shape
		System.out.println("mouse pressed");
		e.consume();
		sPoint.x = e.getX();  
		sPoint.y = e.getY();
		//paintStatusLive = true;
	}
	public void mouseReleased(MouseEvent e){		//Final coords for shape
		//paintStatusLive = false;
		repaint();
		System.out.println("mouse released");
		Graphics buffer = bufferImg.createGraphics();
		buffer.setColor(Color.BLACK);
		e.consume();  
		ePoint.x = e.getX();  
		ePoint.y = e.getY();
		fixDirections();
		switch(button){   // Switch on which button was pressed
		case 0: break;
		case 1: buffer.fillRect(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y);  break;		// Draw filled rectangle
		case 2: buffer.drawRect(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y);  break; 		// Draw empty rectangle
		case 3: buffer.fillOval(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y);  break; 		// Draw filled oval
		case 4: buffer.drawOval(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y);  break;		// Draw empty oval
		case 5: buffer.drawLine(sPoint.x, sPoint.y, ePoint.x, ePoint.y); break; 						// Draw Line
		default: break;
		}
		repaint();
	}
	public void mouseDragged(MouseEvent e) { 		//makes the shape a live-drag
		bufferImgLive = new BufferedImage(5000,5000, BufferedImage.TYPE_INT_ARGB);	//draw on buffered image instead of panel component
		Graphics buffer2 = bufferImgLive.createGraphics();
		((Graphics2D) buffer2).setComposite(AlphaComposite.Clear);
		buffer2.fillRect(0, 0, bufferImg.getWidth(), bufferImg.getHeight());
		e.consume();  
		ePoint.x = e.getX();  
		ePoint.y = e.getY();
		System.out.println(e.getY() + " x " + e.getX());
		((Graphics2D) buffer2).setComposite(AlphaComposite.SrcIn);
		buffer2.setColor(Color.GREEN);
		//fixDirections();
		switch(button){   	// Switch on which button was pressed, again
		case 0: break;  	//these statements are repeated here for live drag
		case 1: buffer2.fillRect(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y);  break;			// Draw filled rectangle
		case 2: buffer2.drawRect(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y);  break; 		// Draw empty rectangle
		case 3: buffer2.fillOval(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y);  break; 		// Draw filled oval
		case 4: buffer2.drawOval(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y);  break;			// Draw empty oval
		case 5: buffer2.drawLine(sPoint.x, sPoint.y, ePoint.x, ePoint.y); break; 							// Draw Line
		default: break;
		}
		repaint();
		//buffer2.dispose();
	}
	public void mouseMoved(MouseEvent e) {}  
	public void mouseExited(MouseEvent e){//System.out.println("mouse exited");	//commented out code is for testing
	}
	public void mouseEntered(MouseEvent e){//System.out.println("mouse entered");
	}
	public void mouseClicked(MouseEvent e){//System.out.println("mouse clicked");
	}
}
