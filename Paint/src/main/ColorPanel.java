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

	private static final long serialVersionUID = -5212711338639497262L;
	public static ColorPanel colorP;
	
	private final Color BDEF = Color.WHITE;		// Default Colors to be set here
	private final Color FDEF = Color.BLACK;
	private final Color ODEF = Color.BLACK;
	private final Dimension PREF = new Dimension(90,26); 						// The preferred button dimensions
	private final Dimension PREF2 = new Dimension(80,20); 						// The preferred reset button dimension

	private JButton buttonBColor; // Background Color
	private Color bColor = Color.WHITE;
	
	private JButton buttonOColor; // Outline Color
	private Color oColor = Color.BLACK;
	
	private JButton buttonFColor; // Fill Color
	private Color fColor = Color.BLACK;
	
	private JButton buttonRST;
	
	public ColorPanel()

	{
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
		
		buttonRST = new JButton("Reset");								//Color button
		add(buttonRST);
		buttonRST.setPreferredSize(PREF2);
		buttonRST.addActionListener(this);
		buttonRST.setToolTipText("Reset all colors to their defaults"); 
		buttonRST.setEnabled(true);
		
	}
	public static ColorPanel getInstance()	{					//get instance of color panel
		if(colorP == null)
			colorP =  new ColorPanel();
		return colorP; }

	public void actionPerformed(ActionEvent arg0) {
		Color fColorTemp = fColor;  // These are here, just in case the user clicks cancel
		Color oColorTemp = fColor;
		Color bColorTemp = fColor;
		Object bt = arg0.getSource();
		if(bt == buttonRST){
			fColor = FDEF;  // Sets back to final default colors
			bColor = BDEF;
			oColor = ODEF;
			buttonFColor.setBackground(fColor);
			buttonOColor.setBackground(oColor);
			buttonBColor.setBackground(bColor);
		} else if(bt == buttonFColor){
			System.out.println("Fill Color");
			fColor = JColorChooser.showDialog(null, "Pick a Color", fColor);
			if(fColor != null)
			{
				buttonFColor.setBackground(fColor);

			} else fColor = fColorTemp;
		}
		else if(bt == buttonOColor){
			System.out.println("Outline Color");
			oColor = JColorChooser.showDialog(null, "Pick a Color", oColor);
			if(fColor != null)
			{
				buttonOColor.setBackground(oColor);

			} else oColor = oColorTemp;
		}
		else if(bt == buttonBColor){
			System.out.println("Background Color");
			bColor = JColorChooser.showDialog(null, "Pick a Color", bColor);
			if(fColor != null)
			{
				buttonBColor.setBackground(bColor);
			} else bColor = bColorTemp;
		}
	}
	public Color getColor(int c){
		switch(c){
		case 0: return fColor; // Fill Color
		case 1: return oColor; // Outline Color
		case 2: return bColor; // Background Color
		default:return Color.BLACK;
		}
	}
}
