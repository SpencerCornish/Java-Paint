package main;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;
import java.awt.event.*;


public class ButtonPanel extends JPanel implements ActionListener, MouseListener {
	
	private static final long serialVersionUID = -923758246107102096L;
	
	Cursor cHair = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
	Cursor cDef = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	
    public static ButtonPanel btnPnl; // Instance of Button Panel
    
    private JButton buttonClear; // Clear the screen
    private JButton buttonRectangleF; // Draw a Filled Rectangle
    private JButton buttonRectangleE; // Draw a Filled Rectangle
    private JButton buttonOvalF; // Draw a Filled Oval
    private JButton buttonOvalE; // Draw an Empty Oval 
    private JButton buttonLineD; // Draw a line

    public ButtonPanel()
    {
    	setLayout(new GridLayout(6,1));
        setBackground(Color.GRAY);	
        
        buttonClear = new JButton("Clear");	//button 0
        Dimension buttonSize = buttonClear.getPreferredSize();
        buttonRectangleF = new JButton("Filled Rectangle");		//button 1
        buttonRectangleE = new JButton("Rectangle"); 			//button 2
        buttonOvalF = new JButton("Filled Oval");				//button 3
        buttonOvalE = new JButton("Empty Oval");				//button 4
        buttonLineD = new JButton("Line");						//button 5
        add(buttonLineD);
        add(buttonOvalE);
        add(buttonOvalF);
        add(buttonRectangleE);
        add(buttonRectangleF);
        add(buttonClear);
        buttonClear.setToolTipText("HOLY SHIT BEANS MY ASS");
        buttonClear.addActionListener(this);
        buttonRectangleF.addActionListener(this);
        buttonRectangleE.addActionListener(this);
        buttonOvalF.addActionListener(this);
        buttonOvalE.addActionListener(this);
        buttonLineD.addActionListener(this);  
    }
     public static ButtonPanel getInstance()
    {
        if(btnPnl == null)
           btnPnl =  new ButtonPanel();
        return btnPnl;
    }

     public void actionPerformed(ActionEvent ae)
     {
         System.out.println(ae.getActionCommand());
     	 MousePanel.getInstance().setCursor(cHair); //Sets the mouse Cursor to a Cross hair for editing
     	 MousePanel.getInstance().cleanUp();
         if(ae.getSource() == buttonClear) {
        	 MousePanel.getInstance().setButton(0);
        	 MousePanel.getInstance().setCursor(cDef);
         }
         if(ae.getSource() == buttonRectangleF) MousePanel.getInstance().setButton(1);
         if(ae.getSource() == buttonRectangleE) MousePanel.getInstance().setButton(2);		//feeds button 2 (because it's a rectangle) to button variable in MousePanel class
         if(ae.getSource() == buttonOvalF) MousePanel.getInstance().setButton(3);
         if(ae.getSource() == buttonOvalE) MousePanel.getInstance().setButton(4);
         if(ae.getSource() == buttonLineD) MousePanel.getInstance().setButton(5);
    	 MousePanel.getInstance().repaint();
     }
	public void mouseClicked(MouseEvent arg0) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}
}
