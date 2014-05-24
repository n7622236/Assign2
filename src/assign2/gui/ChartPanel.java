package assign2.gui;

import org.jfree.chart.JFreeChart;

/**
 * creates a ChartPanel to hold JFreeChart object
 * 
 * @author from BlackBoard
 */
@SuppressWarnings("serial")
public class ChartPanel extends org.jfree.chart.ChartPanel {

	public ChartPanel(JFreeChart chart) {
		super(chart);
	}

	public ChartPanel(JFreeChart chart, boolean useBuffer) {
		super(chart, useBuffer);
	}

	public ChartPanel(JFreeChart chart, boolean properties, boolean save,
			boolean print, boolean zoom, boolean tooltips) {
		super(chart, properties, save, print, zoom, tooltips);
	}

	public ChartPanel(JFreeChart chart, int width, int height,
			int minimumDrawWidth, int minimumDrawHeight, int maximumDrawWidth,
			int maximumDrawHeight, boolean useBuffer, boolean properties,
			boolean save, boolean print, boolean zoom, boolean tooltips) {
		super(chart, width, height, minimumDrawWidth, minimumDrawHeight,
				maximumDrawWidth, maximumDrawHeight, useBuffer, properties,
				save, print, zoom, tooltips);
	}

	public ChartPanel(JFreeChart chart, int width, int height,
			int minimumDrawWidth, int minimumDrawHeight, int maximumDrawWidth,
			int maximumDrawHeight, boolean useBuffer, boolean properties,
			boolean copy, boolean save, boolean print, boolean zoom,
			boolean tooltips) {
		super(chart, width, height, minimumDrawWidth, minimumDrawHeight,
				maximumDrawWidth, maximumDrawHeight, useBuffer, properties,
				copy, save, print, zoom, tooltips);
	}

}
