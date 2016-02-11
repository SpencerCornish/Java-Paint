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
		switch(shape) {
		case 1: g.setColor(Color.BLACK); g.fillRect(x, y, getIconWidth(), getIconHeight()); break;
		case 2: g.setColor(Color.BLACK); g.drawRect(x, y, getIconWidth(), getIconHeight()); break;
		case 3: g.setColor(Color.BLACK); g.fillOval(x, y, getIconWidth(), getIconHeight()); break;
		case 4: g.setColor(Color.BLACK); g.drawOval(x, y, getIconWidth(), getIconHeight()); break;
		case 5: g.setColor(Color.BLACK); g.drawLine(8, 18, 40, 8); break;
		case 6: g.setColor(ColorPanel.getInstance().getColor(2)); g.fillRect(x, y, 10, 15); break;
		case 7: g.setColor(ColorPanel.getInstance().getColor(1)); g.drawRect(x, y, 10, 15); break;
		case 8: g.setColor(ColorPanel.getInstance().getColor(0)); g.fillRect(x, y, 10, 15); break;
		}
	}
}
