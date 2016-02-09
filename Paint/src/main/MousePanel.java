package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;


public class MousePanel extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = -8595660419538273421L;
	public static MousePanel mouseP; 				//Instance of the MousePanel
	private BufferedImage bufferImg;				//buffered image to draw on
	private BufferedImage bufferImgLive;			//Buffered image for live drawing
	private boolean paintStatusFlag = false;		//Tells paintComponent the source to paint on the panel
	private int button = -1;						//Determines which button is pressed based on a number
	private Point startP = new Point(-1, -1);		//Starting point for live drag
	private Point sPoint = new Point(-1, -1);  		//Start Point
	private Point ePoint = new Point(-1, -1);		//End Point

	public MousePanel() {
		bufferImg = new BufferedImage(2000,2000, BufferedImage.TYPE_INT_RGB); 
		Graphics buffer = bufferImg.getGraphics();
		buffer.setColor(Color.WHITE);
		buffer.fillRect(0, 0, bufferImg.getWidth(),bufferImg.getHeight());

		addMouseListener(this); 				//Used to get coordinates of shape
		addMouseMotionListener(this); 			//Used for mouseDragged()
		repaint(); 								//Repaints panel to initialize
	}
	public static MousePanel getInstance() 	// Returns Instance of MousePanel
	{
		if(mouseP == null) 						
			mouseP =  new MousePanel(); 		
		return mouseP; 							
	}

	public void paintComponent(Graphics g)	//no need to call superclass.
	{
		if (bufferImg == null) {			//fills the buffer if it hasn't been filled already
			bufferImg = (BufferedImage) createImage(getSize().width, getSize().height);
			g = (Graphics2D) bufferImg.getGraphics();
			clearAll();
		}

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);	//Turns on Antialiasing
		RenderingHints rh2 = new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);  //Uses quality rendering
		((Graphics2D) g).setRenderingHints(rh); 		//Sets Hints
		((Graphics2D) g).setRenderingHints(rh2);
		if(paintStatusFlag == true)						//Used for the live preview
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
			if(startP.x > ePoint.x){
				int sTemp = startP.x;
				startP.x = ePoint.x;
				ePoint.x = sTemp;
			}
			if(sPoint.y > ePoint.y){
				int sTemp = sPoint.y;
				sPoint.y = ePoint.y;
				ePoint.y = sTemp;
			}
		}

	}
	public void fixLiveDirections()	
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
	public static BufferedImage deepCopy(BufferedImage bi) {
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}

	public void mousePressed(MouseEvent e){			// Initial coords for shape
		System.out.println("mouse pressed");
		e.consume();
		sPoint.x = e.getX();  						// Sets start points
		sPoint.y = e.getY();
		startP.x = sPoint.x;
		startP.y = sPoint.y;
		paintStatusFlag = true; 					// Starts painting the live preview
	}
	public void mouseDragged(MouseEvent e) { 		//makes the shape a live-drag
		bufferImgLive = deepCopy(bufferImg);
		Graphics buffer2 = bufferImgLive.createGraphics();
		e.consume();  
		ePoint.x = e.getX();  
		ePoint.y = e.getY();
		System.out.println(e.getY() + " x " + e.getX());
		buffer2.setColor(Color.BLACK);
		fixLiveDirections();
		switch(button){   						// Switch on which button was pressed
		case 0: break;  
		case 1: buffer2.fillRect(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y);  break;			// Draw filled rectangle
		case 2: buffer2.drawRect(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y);  break; 		// Draw empty rectangle
		case 3: buffer2.fillOval(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y);  break; 		// Draw filled oval
		case 4: buffer2.drawOval(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y);  break;			// Draw empty oval
		case 5: buffer2.drawLine(sPoint.x, sPoint.y, ePoint.x, ePoint.y); break; 							// Draw Line
		default: break;
		}
		bufferImgLive.flush();
		repaint();
		buffer2.dispose();
	}
	public void mouseReleased(MouseEvent e){		//Final coords for shape
		paintStatusFlag = false;
		repaint();
		System.out.println("mouse released");
		Graphics buffer = bufferImg.createGraphics();
		buffer.setColor(Color.BLACK);
		e.consume();  
		ePoint.x = e.getX();  
		ePoint.y = e.getY();
		fixDirections();
		switch(button){   //Switch on which button was pressed
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
	public void mouseMoved(MouseEvent e) {}  
	public void mouseExited(MouseEvent e){//System.out.println("mouse exited");	//commented out code is for testing
	}
	public void mouseEntered(MouseEvent e){//System.out.println("mouse entered");
	}
	public void mouseClicked(MouseEvent e){//System.out.println("mouse clicked");
	}
}
