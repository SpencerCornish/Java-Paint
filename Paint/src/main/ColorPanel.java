package main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ColorPanel extends JPanel implements ActionListener  {
	public static ColorPanel colorP;
	private final Dimension PREF = new Dimension(50,26); 						// The preferred button dimensions
	private JButton buttonBColor; // Background Color
	private JButton buttonOColor; // Outline Color
	private JButton buttonFColor; // Fill Color
	public ColorPanel()
	
	{
		
		
		buttonBColor = new JButton("Color...");								//Color button
		add(buttonBColor);
		buttonBColor.setPreferredSize(PREF);
		buttonBColor.addActionListener(this);
		buttonBColor.setToolTipText("Choose a color"); 
		buttonBColor.setEnabled(true);
		
		
		buttonOColor = new JButton("Color...");								//Color button
		add(buttonOColor);
		buttonOColor.setPreferredSize(PREF);
		buttonOColor.addActionListener(this);
		buttonOColor.setToolTipText("Choose a color"); 
		buttonOColor.setEnabled(true);
		
		
		buttonFColor = new JButton("Color...");								//Color button
		add(buttonFColor);
		buttonFColor.setPreferredSize(PREF);
		buttonFColor.addActionListener(this);
		buttonFColor.setToolTipText("Choose a color"); 
		buttonFColor.setEnabled(true);
	}
	public static ColorPanel getInstance()	{					//get instance of button panel
		if(colorP == null)
			colorP =  new ColorPanel();
		return colorP; }
	public void actionPerformed(ActionEvent arg0) {
		Object jk = arg0.getSource();
		if(jk == buttonFColor){
			System.out.println("Fill Color");
			
		}
		else if(jk == buttonOColor){
			System.out.println("Outline Color");
		}
		else if(jk == buttonBColor){
			System.out.println("Background Color");
		}
	}

}
