package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ColorPanel extends JPanel implements ActionListener  {

	private static final long serialVersionUID = -5212711338639497262L;
	public static ColorPanel colorP;

	private final Color BDEF = Color.WHITE;		// Default Colors to be set here
	private final Color FDEF = Color.BLACK;
	private final Color ODEF = Color.BLACK;

	private final Dimension PREF = new Dimension(90,26); 						// The preferred button dimensions
	private final Dimension PREF2 = new Dimension(80,20); 						// The preferred reset button dimension

	private JButton buttonBColor; // Background Color
	private JButton buttonOColor; // Outline Color
	private JButton buttonFColor; // Fill Color
	private JButton buttonRST;

	private Color fColor = Color.BLACK;  // Default starting colors
	private Color oColor = Color.BLACK;
	private Color bColor = Color.WHITE;


	public ColorPanel()

	{
		setAlignmentX(Component.CENTER_ALIGNMENT); 				// Button alignment within panel
		buttonBColor = new JButton("BG", new ButtonIcon(6));								//Color button
		add(buttonBColor);
		buttonBColor.setPreferredSize(PREF);
		buttonBColor.addActionListener(this);
		buttonBColor.setToolTipText("Choose a background color"); 

		buttonOColor = new JButton("OtLn", new ButtonIcon(7));								//Color button
		add(buttonOColor);
		buttonOColor.setPreferredSize(PREF);
		buttonOColor.addActionListener(this);
		buttonOColor.setToolTipText("Choose an outline color"); 

		buttonFColor = new JButton("Fill", new ButtonIcon(8));								//Color button
		add(buttonFColor);
		buttonFColor.setPreferredSize(PREF);
		buttonFColor.addActionListener(this);
		buttonFColor.setToolTipText("Choose a fill color"); 

		buttonRST = new JButton("Reset");								//Color button
		add(buttonRST);
		buttonRST.setPreferredSize(PREF2);
		buttonRST.addActionListener(this);
		buttonRST.setToolTipText("Reset all colors to their defaults"); 

	}
	public static ColorPanel getInstance()	{					// Get instance of color panel
		if(colorP == null)
			colorP =  new ColorPanel();
		return colorP; }

	public void actionPerformed(ActionEvent arg0) {
		Color fColorTemp = fColor;  // These are here, just in case the user clicks cancel
		Color oColorTemp = oColor;
		Color bColorTemp = bColor;
		Object bt = arg0.getSource();
		if(bt == buttonRST){
			rstColors();
		} else if(bt == buttonFColor){
			fColor = JColorChooser.showDialog(null, "Pick a Fill Color", fColor);
			if(fColor == null) fColor = fColorTemp; // If the user clicks cancel in the color panel
		}
		else if(bt == buttonOColor){
			oColor = JColorChooser.showDialog(null, "Pick an Outline Color", oColor);
			if(oColor == null) oColor = oColorTemp;
		}
		else if(bt == buttonBColor){
			bColor = JColorChooser.showDialog(null, "Pick a Background Color", bColor);
			if(bColor == null) bColor = bColorTemp;
		}
	}
	public Color getColor(int c){
		switch(c){	
		case 0: return fColor; // Fill Color
		case 1: return oColor; // Outline Color
		case 2: return bColor; // Background Color
		default: return Color.BLACK;
		}
	}
	public void rstColors() {
		fColor = FDEF;  // Sets back to final default colors
		bColor = BDEF;
		oColor = ODEF;
		repaint();
	}
}
