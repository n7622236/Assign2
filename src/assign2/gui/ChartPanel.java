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
package assign2.gui;

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
	JFreeChart jChart;
	public ChartPanel(JFreeChart chart) {
		super(chart);
		this.jChart=chart;
       
	}
	
	public ChartPanel getNGramChartPanel(){
		chartPanel = new ChartPanel(this.jChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		return this.chartPanel;
	}
}
