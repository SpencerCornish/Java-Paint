package main;
/**
 * Paint Program
 * Authors:
 * 		Nate Tranel
 * 		Spencer Cornish
 * 		
 * 
 */
import javax.swing.*;
import java.awt.*;


public class ColorChooser extends JPanel {
	private static final long serialVersionUID = -9134135157727043422L;
	private JFrame frame;
	protected JColorChooser chooser;
	private Color bgColor;
	private Color olColor;
	private static Color fColor = Color.BLACK;	//fill color

	public ColorChooser() {
		super(new BorderLayout());
		chooser = new JColorChooser();
		//frame = new JFrame("Pick a Color");
		//frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JComponent content =  chooser;
		content.setOpaque(true);
		//frame.setContentPane(content);
		//frame.pack();
	}
	public void showColors()
	{
		//frame.setVisible(true);
		fColor = JColorChooser.showDialog(null, "Pick a Color", null);
	}
	public static Color getColor() // We should meet and talk about our color options.. We have quite a few choices re: color selections!
	{
		/* switch(source)
		{
		case "outline": return olColor;
		case "fill": return fColor;
		case "background": return olColor;
		default:break;
		}
		return new Color(0); */
		return fColor;
		
	}
	
	public static Color resetColor(){
		fColor = Color.BLACK;
		return fColor;
	}


}
