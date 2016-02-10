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
import javax.swing.*;

public class Frame extends JFrame 
{

	private static final long serialVersionUID = 986906333940524590L; //This is apparently important.  It got rid of a warning, so...
	public static Frame frame;

	public Frame()
	{
		super("Canvas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  						// Exit on close
		Container c = getContentPane(); 										// Returns frame
		setMinimumSize(new Dimension(800, 600));  								// Minimum window size allowed, subject to change
		setSize(new Dimension(800,600));
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		container.add(ButtonPanel.getInstance(), BorderLayout.NORTH);
		container.add(ColorPanel.getInstance(), BorderLayout.SOUTH);
		ButtonPanel.getInstance().setPreferredSize(new Dimension(110,30) ); 	// Sets total size of the container 
		c.add(ButtonPanel.getInstance(), BorderLayout.WEST); 					// Puts ButtonPanel West
		c.add(MousePanel.getInstance(), BorderLayout.CENTER); 					// Centers canvas
		setVisible(true); 
	}
	public static Frame getInstance()	//gets instance of frame and makes one if none exist
	{
		if(frame == null)
			frame = new Frame();

		return frame;
	}
}
