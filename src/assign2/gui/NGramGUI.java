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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import assign2.ngram.NGramException;
import assign2.ngram.NGramStore;
/**
 * Creates GUI in order to implement Research Suggestion program
 * 
 * @author Chou,Shu-Hung(n7622236), Weiwei Nong(n8742600)
 */
public class NGramGUI  extends JFrame implements ActionListener, Runnable{
	private static final long serialVersionUID = 1L;
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
	private JButton resetButton;
	
	private NGramStore nGramStore;
	private BarChart barChart;
	private String context;
	
	/**
	 * Constructor
	 * 
	 * @param frameTitle - the Frame Title
	 */
	public NGramGUI(String frameTitle) throws HeadlessException {
		super(frameTitle);
	}
	/**
	 * Hepler method to construct the GUI
	 * offers a interface to query the suggestion and display result in two ways
	 * displayed by literary and graphical
	 * 
	 * @author Chou,Shu-Hung(n7622236)
	 */
	private void createGUI() throws NGramException {
		setSize(WIDTH, HEIGHT);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());   

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
	    
	    resetButton = new JButton("Reset");
	    resetButton.setBackground(Color.WHITE);
	    resetButton.addActionListener(this);
	    btmPanel.add(resetButton);
	    this.getContentPane().add(btmPanel, BorderLayout.SOUTH);	
	}
	/**
	 * the listener for each button
	 * press "Commit" when request a query 
	 *        after display the query then two buttons(Text and Diagram) can be used 
	 * press "Text" the result is shown by text
	 * press "Diagram" the result is illustrated by bar chart
	 * press "Reset" GUI is reseted 
	 * 
	 * @author Chou,Shu-Hung(n7622236)
	 */
	@Override
	public void actionPerformed(ActionEvent ae){
		String buttonString = ae.getActionCommand();
		  if (buttonString.equals("Commit")) {
			  context=this.textField.getText().trim();
			  nGramStore=new NGramStore();
			  try {
				  	String[] phrases=nGramStore.parseInput(context);
					String strResult="";
					//produce the result
				  	for(int i=0; i < phrases.length;i++){
						strResult+="NGram Results for Query: "+phrases[i]+"\n\n";
						if(nGramStore.getNGramsFromService(phrases[i].trim(), 5)){
							  strResult+=nGramStore.getNGram(phrases[i]).toString()+"\n";
						}else{
							  strResult+="NGram Results for Query: "+phrases[i]+"\n\n"
										+"No ngram predictions were returned.\n"
										+ "Please try another query.\n\n";
						}
					}
				  	// create ResultPanel for displaying results in text
				  	resultPanel.setResult(strResult);
				  
				  	//produce the bar chart
				  	barChart=new BarChart(context,nGramStore);
				  	if(this.chartPanel != null){
				  		chartPanel.removeAll();
				  		chartPanel.revalidate();
				  		chartPanel.setChart(barChart.getJFreeChart());
						this.getContentPane().add(chartPanel, BorderLayout.CENTER);
						this.getContentPane().repaint();
				  	}else{
				  		chartPanel=new ChartPanel(barChart.getJFreeChart());
						chartPanel.setPreferredSize(new java.awt.Dimension(500, 270)); 
						this.getContentPane().add(this.chartPanel, BorderLayout.CENTER);
						this.getContentPane().repaint();
				  	}
				  	
					//displays the text first
					resultPanel.setVisible(true);
					chartPanel.setVisible(false);
					//enable the buttons
					textButton.setEnabled(true);
					diagramButton.setEnabled(true);
			 } catch (NGramException ne) {
				 	reset();
				 	resultPanel.setResult(ne.getMessage());
			 }
		  }else if (buttonString.equals("Text")){
			  chartPanel.setVisible(false);					
			  resultPanel.setVisible(true);	
		  }else if (buttonString.equals("Diagram")) {
			  chartPanel.setVisible(true);					
			  resultPanel.setVisible(false);			  
		  }else if (buttonString.equals("Reset")) {
			  reset();
		  }	 
	}
	/**
	 * initialize the GUI
	 * 
	 * @author Chou,Shu-Hung(n7622236)
	 */
	public void reset(){  
		  //nGramStore.removeNGram(context);
		  textField.setText("");
		  resultPanel.setResult("");
		  chartPanel=null;
		  resultPanel.setVisible(true);
		  textButton.setEnabled(false);
		  diagramButton.setEnabled(false);
	}
	/**
	 * setup the GUI
	 * 
	 *@author Chou, Shu-Hung 
	 */
	@Override
	public void run() {
		try {
			createGUI();
		} catch (NGramException ne) {
			JOptionPane.showConfirmDialog(this,resultPanel,
					ne.getMessage(),
					JOptionPane.OK_CANCEL_OPTION);
		}
		this.setMaximumSize(new Dimension(WIDTH,HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH,HEIGHT));
		this.pack();
		this.setVisible(true);
	}
}
