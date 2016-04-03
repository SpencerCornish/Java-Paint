package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Paint Program
 * Authors:
 * 		Nate Tranel
 * 		Spencer Cornish
 * 		
 * 
 */
public class Main {

	public static void main(String[] args){
    	try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Frame.getInstance();					//starts up the GUI
	}
}
