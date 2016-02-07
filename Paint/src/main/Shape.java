package main;

import java.awt.Graphics;

public class Shape {
	
	private int x1, y1, x2, y2;
	
	public Shape(int x, int y, int x2, int y2) {
		x1 = x;
		y1 = y;
		this.x2 = x2;
		this.y2 = y2;
	}
	public void draw(Graphics g, int shape) {
		switch (shape) {
		case 1: g.fillRect(x1, y1, x2, y2); break;
		case 2: g.drawRect(x1, y1, x2, y2); break;
		case 3: g.fillOval(x1, y1, x2, y2); break;
		case 4: g.drawOval(x1, y1, x2, y2); break;
		case 5: g.drawLine(x1, y1, x2, y2); break;
		}
		/* case 1: g2.fillRect(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y); repaint(); break;		// Draw filled rectangle
        case 2: g2.drawRect(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y); repaint(); break; 		// Draw empty rectangle
        case 3: g2.fillOval(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y); repaint(); break; 		// Draw filled oval
        case 4: g2.drawOval(sPoint.x, sPoint.y, ePoint.x-sPoint.x, ePoint.y-sPoint.y); repaint(); break;		// Draw empty oval
        case 5: g2.drawLine(sPoint.x, sPoint.y, ePoint.x, ePoint.y); repaint(); break; 							// Draw Line */
	}
}
