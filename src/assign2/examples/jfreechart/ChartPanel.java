/**
 * 
 * This file is part of the SearchSuggestion Project, written as 
 * part of the assessment for INB370, semester 1, 2014. 
 *
 * ChartPanel.java
 * assign2.examples.jfreechart
 * 19/04/2014
 * 
 */
package assign2.examples.jfreechart;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import assign2.ngram.NGramException;
import assign2.ngram.NGramStore;

@SuppressWarnings("serial")
public class ChartPanel extends org.jfree.chart.ChartPanel {
	ChartPanel chartPanel;
	public void setChartPanel(String context) throws NGramException {
		//super(chart);
        // This will create the dataset 
        CategoryDataset dataset = createDataset(context);
        // based on the dataset we create the chart
        JFreeChart chart = createChart(dataset);
        // we put the chart into a panel
        chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        // add it to our application
       // setContentPane(chartPanel);
    }
	public ChartPanel getCP(){
		return chartPanel;
	}
	/**
     * Creates a sample dataset 
	 * @throws NGramException 
     */
    private  DefaultCategoryDataset createDataset(String context) throws NGramException {
    	//String context="be or not to";
    	NGramStore ngn=new NGramStore();
    	if(ngn.getNGramsFromService(context, 5)){
    		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        	String[] predictions=ngn.getNGram(context).getPredictions();
        	Double[] probabilities=ngn.getNGram(context).getProbabilities();
        	for(int i=0; i < predictions.length;i++){
        	dataset.setValue(probabilities[i], context, predictions[i]);
        	}
        	return dataset;
    	}else
    		return null;	
    }
    
    /**
     * Creates a chart
     */
    private JFreeChart createChart(CategoryDataset dataset) {
    	String title="SearchSuggestion";
    	JFreeChart chart = ChartFactory.createBarChart3D(
    		title, 
     		"Phrase _____",
    		"Probability",
    		dataset, 
    		PlotOrientation.VERTICAL, 
    		true, 
    		false, 
    		false
    	);
    	
    	chart.setBackgroundPaint(Color.LIGHT_GRAY);
    	chart.setBorderVisible(true);
    	chart.setBorderPaint(Color.BLACK);
    	chart.getTitle().setPaint(Color.BLUE); 
    	CategoryPlot p = chart.getCategoryPlot(); 
    	p.setRangeGridlinePaint(Color.red); 
    			
        return chart;
    }
    
	public ChartPanel(JFreeChart chart) {
		super(chart);
		// TODO Auto-generated constructor stub
	}
}
