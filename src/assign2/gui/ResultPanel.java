/**
 * 
 * This file is part of the SearchSuggestion Project, written as 
 * part of the assessment for INB370, semester 1, 2014. 
 * a simple panel to hold a text area to allow text based reporting of results
 * 
 * ChartPanel.java
 * assign2.gui
 * 19/05/2014
 * 
 */
package assign2.gui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ResultPanel extends JPanel{
	JTextArea textDisplay;
	public ResultPanel(){
		 textDisplay = new JTextArea("",28,50);
		 textDisplay.setEditable(false);
		 textDisplay.setBackground(Color.BLACK);
		 textDisplay.setForeground(Color.GREEN);
		 JScrollPane scroll= new JScrollPane(textDisplay);
		 add(scroll);
	}
	
	public void setResult(String result){
		textDisplay.setText(result);
	}
}
