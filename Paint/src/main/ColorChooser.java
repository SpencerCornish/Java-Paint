package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ColorChooser extends JPanel {
	private static final long serialVersionUID = -9134135157727043422L;
	protected JColorChooser chooser;
	private Color colorChoice;

	public ColorChooser() {
		super(new BorderLayout());
		chooser = new JColorChooser();
		JFrame frame = new JFrame("Pick a Color");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JComponent content =  chooser;
		content.setOpaque(true);
		frame.setContentPane(content);
		
		frame.pack();
		frame.setVisible(true);
	}

}
