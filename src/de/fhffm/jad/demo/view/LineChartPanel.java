package de.fhffm.jad.demo.view;


/**
 * Copyright (c) 2013 Jad
 * 
 * This file is part of Jad.
 * Jad is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jad is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Jad. If not, see <http://www.gnu.org/licenses/>.
 */
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * A Time Series with JFreeChart.
 * @author Denis Hock
 *
 */
public class LineChartPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8040702430162463417L;
	private XYSeries series1;
	private XYSeries series2;
	private int x = 9;
	private JFreeChart chart;
	
    /**
     * Creates a new chart panel
     */
    public LineChartPanel() {
        
        final XYDataset dataset = createDataset();
        chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        add(chartPanel);
    }
    
    /**
     * Creates a sample dataset.
     * 
     * @return a sample dataset.
     */
    private XYDataset createDataset() {
        
        series1 = new XYSeries("IP.Src Entrophy");
        series1.add(1.0, 0);
        series1.add(2.0, 0);
        series1.add(3.0, 0);
        series1.add(4.0, 0);
        series1.add(5.0, 0);
        series1.add(6.0, 0);
        series1.add(7.0, 0);
        series1.add(8.0, 0);
        
        series2 = new XYSeries("TCP.Dst.Port Entrophy");
        series2.add(1.0, 1);
        series2.add(2.0, 1);
        series2.add(3.0, 1);
        series2.add(4.0, 1);
        series2.add(5.0, 1);
        series2.add(6.0, 1);
        series2.add(7.0, 1);
        series2.add(8.0, 1);

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
		return dataset;
    }
    
    /**
     * Creates a sample chart.
     * 
     * @param dataset  a dataset.
     * 
     * @return The chart.
     */
    private JFreeChart createChart(final XYDataset dataset) {
        
        // create the chart...
    	 final JFreeChart chart = ChartFactory.createXYLineChart(
    	            "Realtime Anomaly Detection", // chart title
    	            "Time",                      // x axis label
    	            "Percent",                      // y axis label
    	            dataset,                  // data
    	            PlotOrientation.VERTICAL,
    	            true,                     // include legend
    	            true,                     // tooltips
    	            false                     // urls
    	        );

    	// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
         chart.setBackgroundPaint(Color.white);

         // get a reference to the plot for further customisation...
         final XYPlot plot = chart.getXYPlot();
         plot.setBackgroundPaint(Color.lightGray);
         plot.setDomainGridlinePaint(Color.white);
         plot.setRangeGridlinePaint(Color.white);
         
         final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
         plot.setRenderer(renderer);

         // change the auto tick unit selection to integer units only...
         final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
         rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
         // OPTIONAL CUSTOMISATION COMPLETED.
                 
         return chart;
    }
    
    /**
     * Add a value to one of the lines
     * @param y
     * @param series
     */
    public void addValue(double y, int series){
    	//Grab the line to add a value
    	XYSeries current = null;
    	if (series == 1){
    		current = series1;
    	}
    	else if (series == 2){
    		current = series2;
    	}    	
    	else {
    		return;
    	}
    	current.remove(0);
    	current.add(x, y);    	
    }
    
    /**
     * Re-Draw the chart
     */
    public void update(){
    	chart.fireChartChanged();
    	x++;
    }
}