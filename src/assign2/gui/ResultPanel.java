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
	/**
	 * Constructor of resultPanel
	 * initialize the resultpanel
	 * 
	 *  @author Chou,Shu-Hung(n7622236)
	 */
	public ResultPanel(){
		setLayout(new BorderLayout());
		 textDisplay = new JTextArea();
		 textDisplay.setEditable(false);
		 textDisplay.setBackground(Color.BLACK);
		 textDisplay.setForeground(Color.GREEN);
		 JScrollPane scroll= new JScrollPane(textDisplay);
		 scroll.setPreferredSize(new Dimension(600,600));
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
}
