package main;

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame 
{

	private static final long serialVersionUID = 986906333940524590L; //This is apparently important.  It got rid of a warning, so...
	public static Frame frame;

	public Frame()
	{
		super("Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		setMinimumSize(new Dimension(800, 600));
		c.add(ButtonPanel.getInstance(), BorderLayout.WEST);
		getContentPane().add(MousePanel.getInstance(), BorderLayout.CENTER);
		//setSize(700, 700); test
		setVisible(true);
	}
	public static Frame getInstance()
	{
		if(frame == null)
			frame = new Frame();

		return frame;
	}
}
