/**
 * 
 * This file is part of the SearchSuggestion Project, written as 
 * part of the assessment for INN370, semester 1, 2014. 
 *
 * SimpleFrame
 * assign2.examples.swing
 * 19/04/2014
 * 
 */

package assign2.examples.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import assign2.gui.BarChart;
import assign2.gui.ChartPanel;
import assign2.ngram.NGramException;
import assign2.ngram.NGramStore;

/**
 * @author hogan
 *
 */
public class SimpleFrame extends JFrame implements ActionListener, Runnable {
	
	private static final long serialVersionUID = -7031008862559936404L;
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;

	private JPanel btmPanel;
	private JPanel textPanel;
	private JTextField textField;
	private JTextArea textDisplay;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new SimpleFrame("Swing GUI Demo"));
	}
	
	
	/**
	 * @param arg0 - the Frame Title
	 */
	public SimpleFrame(String arg0) throws HeadlessException {
		super(arg0);
	}


	// helper method to construct the GUI 
	private void createGUI() throws NGramException {
		setSize(WIDTH, HEIGHT);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());
    
	    textDisplay = new JTextArea("the result is displaied here",18,42);
	    textDisplay.setEditable(false);
	    textDisplay.setBackground(Color.BLACK);
	    textDisplay.setForeground(Color.GREEN);
	    JScrollPane scroll= new JScrollPane(textDisplay);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    
	    JLabel NgramLabel = new JLabel("Text:");
		
	    textField = new JTextField("I'm a pig, so are you",20);
		textField.setColumns(1);
		//textField.setLayout(new FlowLayout());
		
	    textPanel = new JPanel(); 
	    textPanel.setBackground(Color.LIGHT_GRAY);
	    textPanel.setLayout(new BorderLayout());
	    
	    textPanel.add(textField,BorderLayout.NORTH);
	    textPanel.add(textDisplay,BorderLayout.CENTER);
	    //textPanel.add(NgramLabel,BorderLayout.NORTH);
	    //textPanel.add(scroll,BorderLayout.CENTER);
	    
	    this.getContentPane().add(textPanel,BorderLayout.CENTER);
	    

	    
	    
	    btmPanel = new JPanel();
	    btmPanel.setBackground(Color.LIGHT_GRAY);
        btmPanel.setLayout(new FlowLayout());

		
 	    JButton blueButton = new JButton("Commit");
	    blueButton.setBackground(Color.WHITE);
	    blueButton.addActionListener(this);
	    btmPanel.add(blueButton);

	    JButton blackButton = new JButton("Diagram");
	    blackButton.setBackground(Color.WHITE);
	    blackButton.addActionListener(this);
	    btmPanel.add(blackButton);
	    
	    JButton clearButton = new JButton("Clear");
	    clearButton.setBackground(Color.WHITE);
	    clearButton.addActionListener(this);
	    btmPanel.add(clearButton);

	    this.getContentPane().add(btmPanel, BorderLayout.SOUTH);	
	}

	@Override
	public void actionPerformed(ActionEvent e){
		String buttonString = e.getActionCommand();
		String context;
		  if (buttonString.equals("Commit")) {
			  context=this.textField.getText().trim();
			  NGramStore ngn=new NGramStore();
			  try {
				  	String[] phrases=ngn.parseInput(context);
					String str="";
				  	for(int i=0; i < phrases.length;i++){
						if(ngn.getNGramsFromService(phrases[i], 5)){
							str+=ngn.getNGram(phrases[i]).toString()+"\n";
						  }else{
							  this.textDisplay.setText("NGram Results for Query: "+context+" \n"
										+ "No ngram predictions were returned.\n"
										+ "Please try another query");
						}
					}
					this.textDisplay.setText(str);
			} catch (NGramException e1) {
				this.textDisplay.setText(e1.toString());
			}
		  } else if (buttonString.equals("Diagram")) {
			  context=this.textField.getText().trim();
			  NGramStore ngn=new NGramStore();
		
				  	String[] phrases;
					try {
						
				  		BarChart barChart=new BarChart(context);
						ChartPanel chartPanel=new ChartPanel(barChart.getJFreeChart());
						this.getContentPane().add(chartPanel.getNGramChartPanel(), BorderLayout.EAST);	
					
					} catch (NGramException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//			  	BarChart bar;
//				try {
//					context=this.textField.getText().trim();
//				    ChartPanel chartPanel=new ChartPanel(context);
//					this.getContentPane().add(chartPanel.getCP(),BorderLayout.EAST);
//				} catch (NGramException e1) {
//					e1.printStackTrace();
//				}
			  
		  }else if (buttonString.equals("Clear")){
			  
		  }
	}

	@Override
	public void run() {
		try {
			createGUI();
		} catch (NGramException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setMaximumSize(new Dimension(WIDTH,HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH,HEIGHT));
		this.pack();
		this.setVisible(true);
	}
}
