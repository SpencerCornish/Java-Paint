package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

public class ButtonIcon implements Icon{
	private int shape = 0;
	
	public ButtonIcon(int shape) {
		this.shape = shape;
	}

	public int getIconHeight() {
		return 15;
	}

	public int getIconWidth() {
		return 20;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		g.setColor(Color.BLACK);
		switch(shape) {
		case 1: g.fillRect(x, y, getIconWidth(), getIconHeight()); break;
		case 2: g.drawRect(x, y, getIconWidth(), getIconHeight()); break;
		case 3: g.fillOval(x, y, getIconWidth(), getIconHeight()); break;
		case 4: g.drawOval(x, y, getIconWidth(), getIconHeight()); break;
		case 5: g.drawLine(8, 18, 40, 8); break;
		}
	}
}
