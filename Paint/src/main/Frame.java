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
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Frame extends JFrame implements ActionListener
{

	private static final long serialVersionUID = 986906333940524590L; //This is apparently important.  It got rid of a warning, so...
	public static Frame frame;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu;
	private JMenuItem menuItem;
	private final Dimension MINSIZE = new Dimension(800,600);
	private final TitledBorder CTITLE = new TitledBorder("Colors");
	private final TitledBorder BTITLE = new TitledBorder("Shapes");
	private final Dimension BPSIZE = new Dimension(110, 220);
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

		menu = new JMenu("File");
		menuItem = new JMenuItem("New");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Open");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();

		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);

		menu = new JMenu("View");
		menuItem = new JMenuItem("Undo");
		menuItem.setEnabled(false);
		menuItem.addActionListener(this);

		menu.add(menuItem);

		menuItem = new JMenuItem("Redo");
		menuItem.setEnabled(false);
		menuItem.addActionListener(this);

		menu.add(menuItem);

		menuBar.add(menu);

		menu = new JMenu("Help");
		menuItem = new JMenuItem("About...");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);
		this.setJMenuBar(menuBar);

	}
	public void actionPerformed(ActionEvent arg0) {
		String btn = ((JMenuItem) arg0.getSource()).getText();
		System.out.println(btn);
		if(btn.equals("New")){
			MousePanel.getInstance().clearAll();
			ColorPanel.getInstance().rstColors();
		}
		else if (btn.equals("Open")) {
			try { 
				MousePanel.getInstance().load();
			}
			catch (IOException e) {
				//That file does not exist
			}
		}
		else if (btn.equals("Save")) {
			try {
				MousePanel.getInstance().save();
			}
			catch (IOException e){
				//Cannot save
			}
		}
		else if (btn.equals("Exit")) System.exit(0);  								// Exits the program with Code 0
		else if (btn.equals("Undo")) MousePanel.getInstance().undo();
		else if (btn.equals("Redo"));
		else if (btn.equals("About...")) new AboutUs();
		else;

	}
	public static Frame getInstance()	//gets instance of frame and makes one if none exist
	{
		if(frame == null)
			frame = new Frame();

		return frame;
	}
}
