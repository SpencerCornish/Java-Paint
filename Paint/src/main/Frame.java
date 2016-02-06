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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit on close
		Container c = getContentPane(); // Returns frame
		setMinimumSize(new Dimension(800, 600));  // Minimum window size allowed, subject to change
		ButtonPanel.getInstance().setPreferredSize(new Dimension(110,30) ); // Sets total size of the container 
		c.add(ButtonPanel.getInstance(), BorderLayout.WEST); // Puts ButtonPanel West
		c.add(MousePanel.getInstance(), BorderLayout.CENTER); // Centers canvas
		setVisible(true); 
	}
	public static Frame getInstance()
	{
		if(frame == null)
			frame = new Frame();

		return frame;
	}
}
