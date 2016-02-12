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
import javax.swing.border.TitledBorder;

public class Frame extends JFrame 
{

	private static final long serialVersionUID = 986906333940524590L; //This is apparently important.  It got rid of a warning, so...
	public static Frame frame;
	private JMenuBar menuBar = new JMenuBar();
	private final Dimension MINSIZE = new Dimension(800,600);
	private final TitledBorder CTITLE = new TitledBorder("Colors");
	private final TitledBorder BTITLE = new TitledBorder("Shapes");
	private final Dimension BPSIZE = new Dimension(110, 240);
	private final Dimension CPSIZE = new Dimension(110, 150);
	public Frame()
	{
		super("Canvas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  						// Exit on close
		Container c = getContentPane(); 										// Returns frame
		JPanel westContainer = new JPanel();
		makeMenu();
				
		setMinimumSize(MINSIZE);  								// Minimum window size allowed, subject to change
		setSize(MINSIZE);
		ColorPanel.getInstance().setBorder(CTITLE);		
		ButtonPanel.getInstance().setBorder(BTITLE);
		
		westContainer.setLayout(new BorderLayout());
		westContainer.add(ButtonPanel.getInstance(), BorderLayout.NORTH);
		westContainer.add(ColorPanel.getInstance(), BorderLayout.SOUTH);
		
		ButtonPanel.getInstance().setPreferredSize(BPSIZE); 	// Sets total size of the container 
		ColorPanel.getInstance().setPreferredSize(CPSIZE); 
		c.add(westContainer, BorderLayout.WEST); 					// Puts ButtonPanel West
		c.add(MousePanel.getInstance(), BorderLayout.CENTER); 					// Centers canvas
		setVisible(true); 
	}
	public void makeMenu()
	{
		
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu helpMenu = new JMenu("Help");
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(helpMenu);
		
		this.setJMenuBar(menuBar);
		
	}
	public static Frame getInstance()	//gets instance of frame and makes one if none exist
	{
		if(frame == null)
			frame = new Frame();

		return frame;
	}
}
