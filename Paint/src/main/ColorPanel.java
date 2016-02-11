package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;

public class ColorPanel extends JPanel implements ActionListener  {
	public static ColorPanel colorP;
	private static ColorChooser cDialog;
	private JColorChooser chooser;
	private final Dimension PREF = new Dimension(90,26); 						// The preferred button dimensions
	private JButton buttonBColor; // Background Color
	private Color bColor = Color.WHITE;
	private JButton buttonOColor; // Outline Color
	private Color oColor = Color.BLACK;
	private JButton buttonFColor; // Fill Color
	private Color fColor = Color.BLACK;
	public ColorPanel()
	
	{
		cDialog = new ColorChooser();
		setAlignmentX(Component.CENTER_ALIGNMENT); 				// Button alignment within panel
		
		buttonBColor = new JButton("BG Color");								//Color button
		add(buttonBColor);
		buttonBColor.setPreferredSize(PREF);
		buttonBColor.addActionListener(this);
		buttonBColor.setToolTipText("Choose a color"); 
		buttonBColor.setEnabled(true);
		buttonBColor.setBackground(bColor);
		buttonBColor.setBorderPainted(false);
		
		buttonOColor = new JButton("Outline");								//Color button
		add(buttonOColor);
		buttonOColor.setPreferredSize(PREF);
		buttonOColor.addActionListener(this);
		buttonOColor.setToolTipText("Choose a color"); 
		buttonOColor.setEnabled(true);
		buttonOColor.setBackground(oColor);
		buttonOColor.setBorderPainted(false);
		
		buttonFColor = new JButton("Fill");								//Color button
		add(buttonFColor);
		buttonFColor.setPreferredSize(PREF);
		buttonFColor.addActionListener(this);
		buttonFColor.setToolTipText("Choose a color"); 
		buttonFColor.setEnabled(true);
		buttonFColor.setBackground(fColor);
		buttonFColor.setBorderPainted(false);
	}
	public static ColorPanel getInstance()	{					//get instance of color panel
		if(colorP == null)
			colorP =  new ColorPanel();
		return colorP; }
	
	public void actionPerformed(ActionEvent arg0) {
		Object bt = arg0.getSource();
		if(bt == buttonFColor){
			System.out.println("Fill Color");
			fColor = cDialog.showColors();
		}
		else if(bt == buttonOColor){
			System.out.println("Outline Color");
			oColor = cDialog.showColors();

		}
		else if(bt == buttonBColor){
			System.out.println("Background Color");
			bColor = cDialog.showColors();
			buttonBColor.setBackground(bColor);
		}
	}
	

}
