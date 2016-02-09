package main;


import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.event.*;


public class ButtonPanel extends JPanel implements ActionListener, MouseListener { //remove mouselistener :D

	private static final long serialVersionUID = -923758246107102096L;

	Cursor cHair = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR); 		// Crosshair cursor
	Cursor cDef = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR); 			// Default cursor
	private Dimension PREF;					// The preferred button dimension
	public static ButtonPanel btnP; 		// Instance of Button Panel
	private JButton buttonClear; 			// Clear the screen
	private JButton buttonRectangleF; 		// Draw a Filled Rectangle
	private JButton buttonRectangleE; 		// Draw a Filled Rectangle
	private JButton buttonOvalF; 			// Draw a Filled Oval
	private JButton buttonOvalE; 			// Draw an Empty Oval 
	private JButton buttonLineD; 			// Draw a line

	public ButtonPanel()
	{

		setAlignmentX(Component.CENTER_ALIGNMENT); 				// Button panel alignment? This needs some testing
		setBackground(Color.GRAY);								// Button panel Background color
		buttonClear = new JButton("Clear");						//button 0
		buttonRectangleF = new JButton("Filled Rect.");			//button 1
		buttonRectangleE = new JButton("Empty Rect."); 			//button 2
		buttonOvalF = new JButton("Filled Oval");				//button 3
		buttonOvalE = new JButton("Empty Oval");				//button 4
		buttonLineD = new JButton("Line");						//button 5
		add(buttonLineD); 										// Add these. We need some polymorphisim in here! :)
		add(buttonOvalE);
		add(buttonOvalF);
		add(buttonRectangleE);
		add(buttonRectangleF);
		add(buttonClear);
		PREF = buttonRectangleE.getPreferredSize();
		buttonRectangleF.setPreferredSize(PREF);
		buttonClear.setPreferredSize(PREF);
		buttonOvalF.setPreferredSize(PREF);
		buttonOvalE.setPreferredSize(PREF);
		buttonLineD.setPreferredSize(PREF);
		buttonClear.setToolTipText("");
		buttonClear.addActionListener(this); 
		buttonRectangleF.addActionListener(this);
		buttonRectangleE.addActionListener(this);
		buttonOvalF.addActionListener(this);
		buttonOvalE.addActionListener(this);
		buttonLineD.addActionListener(this);  
	}
	public static ButtonPanel getInstance()
	{
		if(btnP == null)
			btnP =  new ButtonPanel();
		return btnP;
	}
	public void actionPerformed(ActionEvent ae)
	{
		System.out.println(ae.getActionCommand());
		MousePanel.getInstance().setCursor(cHair); //Sets the mouse Cursor to a Cross hair for editing

		if(ae.getSource() == buttonClear) {
			MousePanel.getInstance().setButton(0);
			MousePanel.getInstance().setCursor(cDef);
			MousePanel.getInstance().clearAll();
			//MousePanel.getInstance().repaint();
		}
		else if(ae.getSource() == buttonRectangleF) MousePanel.getInstance().setButton(1);
		else if(ae.getSource() == buttonRectangleE) MousePanel.getInstance().setButton(2);		//feeds button 2 (because it's a rectangle) to button variable in MousePanel class
		else if(ae.getSource() == buttonOvalF) MousePanel.getInstance().setButton(3);
		else if(ae.getSource() == buttonOvalE) MousePanel.getInstance().setButton(4);
		else if(ae.getSource() == buttonLineD) MousePanel.getInstance().setButton(5);
		MousePanel.getInstance().repaint();
	}
	public void mouseClicked(MouseEvent arg0) {}

	public void mouseEntered(MouseEvent e) {System.out.println("Deez");}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}
}
