package main;
/**
 * Paint Program
 * Authors:
 * 		Nate Tranel
 * 		Spencer Cornish
 * 		
 * 
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import java.io.*;


public class MousePanel extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = -8595660419538273421L;
	public static MousePanel mouseP; 				//Instance of the MousePanel
	private BufferedImage bufferImg;				//buffered image to draw on
	private BufferedImage bufferImgLive;			//Buffered image for live drawing

	private boolean paintStatusFlag = false;		//Tells paintComponent the source to paint on the panel
	private int button = -1;						//Determines which button is pressed based on a number

	private Point sPoint = new Point();  			//start Point
	private Point ePoint = new Point();				//end Point

	private String path;							//used for output 
	private FileOutputStream os;					//also used for output

	public MousePanel() {

		addMouseListener(this); 					//Used to get coordinates of shape
		addMouseMotionListener(this); 				//Used for mouseDragged()
		} 								

	public static MousePanel getInstance() { 		// Returns Instance of MousePanel
		if(mouseP == null) 						
			mouseP =  new MousePanel(); 		
		return mouseP; }

	public void paintComponent(Graphics g) {
		if (bufferImg == null) 
		{					//fills the buffer if it hasn't been filled already
			bufferImg = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
			g = bufferImg.createGraphics();
			clearAll(); 
		}
		else if (this.getWidth() > bufferImg.getWidth() || this.getHeight() > bufferImg.getHeight())
		{
			System.out.println("Window size changed");
			System.out.format("bufferImg: W:%d H:%d%n", bufferImg.getWidth(),bufferImg.getHeight());
			System.out.format("Window:    W:%d H:%d%n", this.getWidth(), this.getHeight());
			BufferedImage buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2dnew = buffer.createGraphics();
            g2dnew.drawImage(bufferImg, null, 0, 0);
            g2dnew.setColor(ColorPanel.getInstance().getColor(2));
            g2dnew.fillRect(bufferImg.getWidth(), 0, getWidth(), getHeight());
            g2dnew.fillRect(0, bufferImg.getHeight(), getWidth(), getHeight());
            g = g2dnew;
            bufferImg = buffer;
            bufferImgLive = buffer;
		}

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);	//Turns on Antialiasing
		RenderingHints rh2 = new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);  //Uses quality rendering
		((Graphics2D) g).setRenderingHints(rh); 		//Sets Hints
		((Graphics2D) g).setRenderingHints(rh2);

		if(paintStatusFlag == true) {					//used for live preview
			g.drawImage(bufferImgLive, 0, 0, null); }
		else {
			g.drawImage(bufferImg, 0, 0, null);	} }

	public void setButton(int button) {  				// Sets our button tracking variable
		this.button = button; }

	public void clearAll() { 							//clears mousepanel by painting over the background image
		Graphics buffer = bufferImg.createGraphics();
		buffer.setColor(Color.WHITE);
		buffer.fillRect(0, 0, bufferImg.getWidth(), bufferImg.getHeight());
		repaint();
	}
	public void fixDirections()	{						//makes sure final coordinates are good to paint with
		repaint();
		if(button != 5){
			if(sPoint.x > ePoint.x){
				int sTemp = sPoint.x;
				sPoint.x = ePoint.x;
				ePoint.x = sTemp;
				ePoint.x = ePoint.x; }
			if(sPoint.y > ePoint.y) {
				int sTemp = sPoint.y;
				sPoint.y = ePoint.y;
				ePoint.y = sTemp; } } }

	public static BufferedImage deepCopy(BufferedImage bi) 
	{
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);

	}
	
	public void mousePressed(MouseEvent e){			// Initial coords for shape
		e.consume();
		sPoint.x = e.getX();  						// Sets start points
		sPoint.y = e.getY();
		paintStatusFlag = true;

	}					// Starts painting the live preview

	public void mouseDragged(MouseEvent e) { 		//makes the shape a live-drag
		bufferImgLive = deepCopy(bufferImg);
		Graphics buffer2 = bufferImgLive.createGraphics();
		e.consume();  
		ePoint.x = e.getX();  
		ePoint.y = e.getY();
		int x1 = -1, y1 = -1, width = -1, height = -1;
		if (button != 5) {										//when drawing things other than a button...
			if (sPoint.x < ePoint.x && sPoint.y < ePoint.y) {	//the following statements allow live draw from all directions
				x1 = sPoint.x;
				y1 = sPoint.y;
				width = ePoint.x-sPoint.x;
				height = ePoint.y-sPoint.y; }
			else if (sPoint.x > ePoint.x && sPoint.y < ePoint.y) {
				x1 = ePoint.x;
				y1 = sPoint.y;
				width = sPoint.x-ePoint.x;
				height = ePoint.y-sPoint.y; }
			else if (sPoint.x < ePoint.x && sPoint.y > ePoint.y) {
				x1 = sPoint.x;
				y1 = ePoint.y;
				width = ePoint.x-sPoint.x;
				height = sPoint.y-ePoint.y; }
			else if (sPoint.x > ePoint.x && sPoint.y > ePoint.y) {
				x1 = ePoint.x;
				y1 = ePoint.y;
				width = sPoint.x-ePoint.x;
				height = sPoint.y-ePoint.y; }
		}
		switch(button){  
		case 0: break;
		case 1: rect(1, 1, x1, y1, width, height); break;		
		case 2: rect(1, 0, x1, y1, width, height); break;
		case 3: oval(1, 1, x1, y1, width, height); break;
		case 4: oval(1, 0, x1, y1, width, height); break;
		case 5: line(1, sPoint.x, sPoint.y, ePoint.x, ePoint.y); break;
		default: break; }
		buffer2.dispose();
		System.gc(); 	//Solves the issue of having a ton of Buffered Images stuck in the memory for the live preview.  I wish there was a better way
		repaint();
	}
	public void mouseReleased(MouseEvent e){		//Final coords for shape
		paintStatusFlag = false;	
		e.consume();  						
		ePoint.x = e.getX();  
		ePoint.y = e.getY();
		fixDirections();
		switch(button){   //Switch on which button was pressed
		case 0: break;
		case 1: rect(0, 1, sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y); break;		// Draw filled rectangle
		case 2: rect(0, 0, sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y); break; 		// Draw empty rectangle
		case 3: oval(0, 1, sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y); break;		// Draw filled oval
		case 4: oval(0, 0, sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y); break;		// Draw empty oval
		case 5: line(0, sPoint.x, sPoint.y, ePoint.x, ePoint.y); break;								// Draw Line
		default: break; } 
	}

	//Undo and Redo start here

	public void undo(){
	}
	public void redo(){	
	}

	// Saving and Loading starts here

	public boolean saveAs(boolean as) throws IOException {
		if (as == true) {				//if user is using save as, let them pick where to save
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"JPG & PNG Images", "jpg", "png");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showSaveDialog(getParent());
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				os = new FileOutputStream(chooser.getSelectedFile().getPath() + ".png");
				ImageIO.write(bufferImg, "png", os);
				path = chooser.getSelectedFile().getPath();
				return true;
			}
		}
		else if (as == false) {					//if user chooses save, save to same directory
			os = new FileOutputStream(path + ".png");
			ImageIO.write(bufferImg, "png", os);
			return true;
		}
		return false;
	}

	public boolean load() throws IOException {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"JPG & PNG Images", "jpg", "png");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(getParent());
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			bufferImg = ImageIO.read(chooser.getSelectedFile());
			repaint();
			return true;
		}
		return false;
	}


	// Shape Methods start here

	public void rect(int lv, int f, int x, int y, int width, int height) {
		Graphics buffer = bufferImg.createGraphics();
		Graphics buffer2 = bufferImgLive.createGraphics();
		if (lv == 0) {			//if lv (for live) is false - this part for drawing in mouseReleased()
			if (f == 1) {		//if f (for fill) is true
				buffer.setColor(ColorPanel.getInstance().getColor(0)); 
				buffer.fillRect(x, y, width, height); 
				buffer.setColor(ColorPanel.getInstance().getColor(1)); 
				buffer.drawRect(x, y, width, height);
			}
			else {				//if fill is false, then empty rect is drawn
				buffer.setColor(ColorPanel.getInstance().getColor(1)); 
				buffer.drawRect(x, y, width, height);
			} 
		}
		else {					//if live is true - for drawing in mouseDragged()
			if (f == 1) {	
				buffer2.setColor(ColorPanel.getInstance().getColor(0)); 
				buffer2.fillRect(x, y, width, height); 
				buffer2.setColor(ColorPanel.getInstance().getColor(1)); 
				buffer2.drawRect(x, y, width, height);
			}
			else {				
				buffer2.setColor(ColorPanel.getInstance().getColor(1)); 
				buffer2.drawRect(x, y, width, height);
			} 
		}
	}
	public void oval(int lv, int f, int x, int y, int width, int height) {
		Graphics buffer = bufferImg.createGraphics();
		Graphics buffer2 = bufferImgLive.createGraphics();
		if (lv == 0) {
			if (f == 1) {
				buffer.setColor(ColorPanel.getInstance().getColor(0)); 
				buffer.fillOval(x, y, width, height);  
				buffer.setColor(ColorPanel.getInstance().getColor(1));
				buffer.drawOval(x, y, width, height);
			}
			else {
				buffer.setColor(ColorPanel.getInstance().getColor(1)); 
				buffer.drawOval(x, y, width, height);
			}
		}
		else {
			if (f == 1) {
				buffer2.setColor(ColorPanel.getInstance().getColor(0)); 
				buffer2.fillOval(x, y, width, height);  
				buffer2.setColor(ColorPanel.getInstance().getColor(1));
				buffer2.drawOval(x, y, width, height);
			}
			else {
				buffer2.setColor(ColorPanel.getInstance().getColor(1)); 
				buffer2.drawOval(x, y, width, height);
			}
		}
	}
	public void line(int lv, int x1, int y1, int x2, int y2) {
		Graphics buffer = bufferImg.createGraphics();
		Graphics buffer2 = bufferImgLive.createGraphics();
		if (lv == 0) {
			buffer.setColor(ColorPanel.getInstance().getColor(1)); 
			buffer.drawLine(x1, y1, x2, y2);
		}
		else {
			buffer2.setColor(ColorPanel.getInstance().getColor(1)); 
			buffer2.drawLine(x1, y1, x2, y2);
		}
	}
	public void mouseMoved(MouseEvent e) {} 

	public void mouseExited(MouseEvent e){}

	public void mouseEntered(MouseEvent e){}

	public void mouseClicked(MouseEvent e){} 

}
