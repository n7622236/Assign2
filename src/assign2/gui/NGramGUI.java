package assign2.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import assign2.ngram.NGramException;
import assign2.ngram.NGramStore;

public class NGramGUI  extends JFrame implements ActionListener, Runnable{
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;

	private JPanel btmPanel;
	private JPanel topPanel;
	
	private ResultPanel resultPanel;
	private ChartPanel chartPanel;
	
	private JTextField textField;
	private JLabel textLabel;
	
	private JButton commitButton;
	private JButton textButton;
	private JButton diagramButton;
	private JButton clearButton;
	
	private NGramStore ngn;
	private String context;
	
	/**
	 * @param arg0 - the Frame Title
	 */
	public NGramGUI(String arg0) throws HeadlessException {
		super(arg0);
	}


	// helper method to construct the GUI 
	private void createGUI() throws NGramException {
		setSize(WIDTH, HEIGHT);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());   
	    //set top panel
	    textField = new JTextField("");
		textField.setColumns(1);
		
		textLabel = new JLabel("Text : ");
	    textLabel.setLabelFor(textField);
	    
	    topPanel = new JPanel(); 
		topPanel.setBackground(Color.LIGHT_GRAY);
		topPanel.setLayout(new BorderLayout());
		
		topPanel.add(textLabel,BorderLayout.WEST);
		topPanel.add(textField,BorderLayout.CENTER);
		this.getContentPane().add(topPanel,BorderLayout.NORTH);

	    resultPanel = new ResultPanel();
	    this.getContentPane().add(resultPanel, BorderLayout.CENTER);
	    
	    btmPanel = new JPanel();
	    btmPanel.setBackground(Color.LIGHT_GRAY);
        btmPanel.setLayout(new FlowLayout());

        commitButton = new JButton("Commit");
        commitButton.setBackground(Color.WHITE);
        commitButton.addActionListener(this);
	    btmPanel.add(commitButton);
        
 	    textButton = new JButton("Text");
 	    textButton.setBackground(Color.WHITE);
 	    textButton.addActionListener(this);
	    textButton.setEnabled(false);
 	    btmPanel.add(textButton);

	    diagramButton = new JButton("Diagram");
	    diagramButton.setBackground(Color.WHITE);
	    diagramButton.addActionListener(this);
	    diagramButton.setEnabled(false);
	    btmPanel.add(diagramButton);
	    
	    clearButton = new JButton("Clear");
	    clearButton.setBackground(Color.WHITE);
	    clearButton.addActionListener(this);
	    btmPanel.add(clearButton);

	    this.getContentPane().add(btmPanel, BorderLayout.SOUTH);	
	}

	@Override
	public void actionPerformed(ActionEvent e){
		String buttonString = e.getActionCommand();
		
		  if (buttonString.equals("Commit")) {
			  context=this.textField.getText().trim();
			  ngn=new NGramStore();
			  try {
				  	String[] phrases=ngn.parseInput(context);
					String strReSult="";
					//assigns the result to text area
				  	for(int i=0; i < phrases.length;i++){
						if(ngn.getNGramsFromService(phrases[i], 5)){
							  strReSult+=ngn.getNGram(phrases[i]).toString()+"\n";
						  }else{
							  strReSult+="NGram Results for Query: "+phrases[i].toString()+"\n"
										+ "No ngram predictions were returned.\n"
										+ "Please try another query.\n\n";
						}
					}
				  	// create ResultPanel for displaying results in text
				  	resultPanel.setResult(strReSult);
				  
				  	//produce the bar chart
				  	BarChart barChart=new BarChart(context);
					chartPanel=new ChartPanel(barChart.getJFreeChart());
					this.getContentPane().add(chartPanel, BorderLayout.CENTER);
					
					//displays the text first
					
					resultPanel.setVisible(true);
					chartPanel.setVisible(false);
					
					//enable the buttons
					textButton.setEnabled(true);
					diagramButton.setEnabled(true);
			 } catch (NGramException e1) {
				 	resultPanel.setResult(e1.toString());
			 }
		  }else if (buttonString.equals("Text")){
			  chartPanel.setVisible(false);					
			  resultPanel.setVisible(true);	
		  }else if (buttonString.equals("Diagram")) {
			  chartPanel.setVisible(true);					
			  resultPanel.setVisible(false);			  
		  }else if (buttonString.equals("Clear")) {
			  ngn.removeNGram(context);
			  textField.setText("");
			  resultPanel.setResult("");
			  resultPanel.setVisible(true);
			  chartPanel.setVisible(false);
			  textButton.setEnabled(false);
			  diagramButton.setEnabled(false);
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
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	
}
