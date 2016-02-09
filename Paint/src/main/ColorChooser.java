package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ColorChooser extends JPanel {
	private static final long serialVersionUID = -9134135157727043422L;
	private JFrame frame;
	protected JColorChooser chooser;

	public ColorChooser() {
		super(new BorderLayout());
		chooser = new JColorChooser();
		frame = new JFrame("Pick a Color");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JComponent content =  chooser;
		content.setOpaque(true);
		frame.setContentPane(content);
		frame.pack();
	}
	public void showColors()
	{
		frame.setVisible(true);
	
	}
	public Color getColor()
	{
		return chooser.getColor();//s
	}
	

}
