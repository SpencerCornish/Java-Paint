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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Frame extends JFrame implements ActionListener
{

	private static final long serialVersionUID = 986906333940524590L; //This is apparently important.  It got rid of a warning, so...
	public static Frame frame;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu,submenu;
	private JMenuItem menuItem;
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
		
		menu = new JMenu("File");
		menuItem = new JMenuItem("New");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Open...");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuBar.add(menu);
		
		menu = new JMenu("Edit");
		menuItem = new JMenuItem("Undo");
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
		if(btn == "New"){
			MousePanel.getInstance().clearAll();
			ColorPanel.getInstance().rstColors();
		}
		else if (btn == "Open...") System.out.println("Work In Progress!");
		else if (btn == "Exit") System.exit(0);  // Exits the program with Code 0
		else if (btn == "Undo") MousePanel.getInstance().undo();
		else if (btn == "Redo");
		else if (btn == "About...") aboutUs();
		
	}
	public static Frame getInstance()	//gets instance of frame and makes one if none exist
	{
		if(frame == null)
			frame = new Frame();

		return frame;
	}
	public void aboutUs()
	{
		JDialog about = new JDialog(null, "About", Dialog.ModalityType.APPLICATION_MODAL);
		JFrame aFrame = new JFrame();
		Container c = getContentPane();
		
		
		
		about.setMinimumSize(new Dimension(600,300));
		about.setResizable(false);
		about.setLocationRelativeTo(null);
		about.setVisible(true);
	}
}
