package main;
/**
 * Paint Program
 * Authors:
 * 		Nate Tranel
 * 		Spencer Cornish
 * 		
 * 
 */

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.event.*;


public class ButtonPanel extends JPanel implements ActionListener { 

	private static final long serialVersionUID = -923758246107102096L;

	Cursor cHair = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR); 		// Crosshair cursor
	Cursor cDef = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR); 			// Default cursor
	private final Dimension PREF = new Dimension(50,26); 						// The preferred button dimensions
	private final Dimension PREF2 = new Dimension(101, 26);
	public static ButtonPanel btnP; 		// Instance of Button Panel
	private JButton buttonClear; 			// Clear the screen
	private JButton buttonUndo;				// Undo last change
	private JButton buttonRectangleF; 		// Draw a Filled Rectangle
	private JButton buttonRectangleE; 		// Draw a Filled Rectangle
	private JButton buttonOvalF; 			// Draw a Filled Oval
	private JButton buttonOvalE; 			// Draw an Empty Oval 
	private JButton buttonLineD; 			// Draw a line
	public ButtonPanel() {
		setAlignmentX(Component.CENTER_ALIGNMENT); 				// Button alignment within panel
		//setBackground(Color.GRAY);								// Button panel Background color

		buttonRectangleF = new JButton(new ButtonIcon(1));					//button 1
		add(buttonRectangleF);
		buttonRectangleF.setPreferredSize(PREF);
		buttonRectangleF.addActionListener(this);
		buttonRectangleF.setToolTipText("Draw a filled rectangle");

		buttonRectangleE = new JButton(new ButtonIcon(2)); 					//button 2
		add(buttonRectangleE);
		buttonRectangleE.setPreferredSize(PREF);
		buttonRectangleE.addActionListener(this);
		buttonRectangleE.setToolTipText("Draw an empty rectangle");

		buttonOvalF = new JButton(new ButtonIcon(3));						//button 3
		add(buttonOvalF);
		buttonOvalF.setPreferredSize(PREF);
		buttonOvalF.addActionListener(this);
		buttonOvalF.setToolTipText("Draw a filled oval");

		buttonOvalE = new JButton(new ButtonIcon(4));						//button 4
		add(buttonOvalE);
		buttonOvalE.setPreferredSize(PREF);
		buttonOvalE.addActionListener(this);
		buttonOvalE.setToolTipText("Draw an empty oval");

		buttonLineD = new JButton(new ButtonIcon(5));						//button 5
		add(buttonLineD); 
		buttonLineD.setPreferredSize(PREF);
		buttonLineD.addActionListener(this);  
		buttonLineD.setToolTipText("Draw a straight line");
		
		buttonUndo = new JButton("Undo");									//undo button - coming soon!
		add(buttonUndo);
		buttonUndo.setPreferredSize(PREF2);
		buttonUndo.addActionListener(this);
		buttonUndo.setToolTipText("Undo last change");
		buttonUndo.setEnabled(false);

		buttonClear = new JButton("Clear");									//button 0
		add(buttonClear);
		buttonClear.setPreferredSize(PREF2);
		buttonClear.addActionListener(this);
		buttonClear.setToolTipText("Clear the canvas");
	}

	public static ButtonPanel getInstance()	{					//get instance of button panel
		if(btnP == null)
			btnP =  new ButtonPanel();
		return btnP; }
	public void actionPerformed(ActionEvent ae) {
		MousePanel.getInstance().setCursor(cHair); 		//Sets the mouse Cursor to a Cross hair for editing

		if(ae.getSource() == buttonClear) {				//clears if user clicks clear button
			MousePanel.getInstance().setButton(0);
			MousePanel.getInstance().setCursor(cDef);
			MousePanel.getInstance().clearAll(); }
		else if(ae.getSource() == buttonUndo) MousePanel.getInstance().undo();
		else if(ae.getSource() == buttonRectangleF) MousePanel.getInstance().setButton(1);
		else if(ae.getSource() == buttonRectangleE) MousePanel.getInstance().setButton(2);		//feeds button 2 (because it's a rectangle) to button variable in MousePanel class
		else if(ae.getSource() == buttonOvalF) MousePanel.getInstance().setButton(3);
		else if(ae.getSource() == buttonOvalE) MousePanel.getInstance().setButton(4);
		else if(ae.getSource() == buttonLineD) MousePanel.getInstance().setButton(5);
		if(ae.getSource() == null) ;
		} }
