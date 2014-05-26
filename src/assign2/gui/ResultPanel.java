package assign2.gui;
/**
 * creates a simple panel to hold a text area 
 * to allow text based reporting of results
 * 
 * @author Chou,Shu-Hung(n7622236)
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ResultPanel extends JPanel{
	JTextArea textDisplay;
	String strGreet="Welcome to The Search Suggestion System\n"
			 +"Please enter the context you would like to search.\n"
			 +"You are able to search a list queries "
			 + "by using commas for separating phrase.\n";
	/**
	 * Constructor of resultPanel
	 * initialize the resultpanel
	 * 
	 *  @author Chou,Shu-Hung(n7622236)
	 */
	public ResultPanel(){
		 textDisplay = new JTextArea(strGreet);
		 textDisplay.setEditable(false);
		 textDisplay.setBackground(Color.BLACK);
		 textDisplay.setForeground(Color.GREEN);
		 JScrollPane scroll= new JScrollPane(textDisplay);
		 scroll.setPreferredSize(new Dimension(600,600));
		 setLayout(new BorderLayout());
		 add(scroll, BorderLayout.CENTER);
	}
	
	/**
	 * set the result in text area
	 * 
	 * @author CHou,Shu-Hung(n7622236)
	 */
	public void setResult(String result){
		textDisplay.setText(result);
	}
	
	/**
	 * set the greet in text area
	 * 
	 * @author CHou,Shu-Hung(n7622236)
	 */
	public void setGreet(){
		textDisplay.setText(strGreet);
	}
}
