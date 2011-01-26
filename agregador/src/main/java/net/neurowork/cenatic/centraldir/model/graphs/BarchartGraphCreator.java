/*
 * Copyright 2010 CENATIC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.neurowork.cenatic.centraldir.model.graphs;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.Dataset;

/**
 *
 * @author Alejandro Sanchez Acosta <asanchez@neurowork.net> http://www.neurowork.net
 * @since 16/11/2010
 */
public class BarchartGraphCreator implements GraphChartCreator {

	@Override
	public JFreeChart createGraphChart(String title, String yAxis, Dataset data) {
		PlotOrientation orientation = PlotOrientation.VERTICAL;
		
		JFreeChart chart = ChartFactory.createBarChart(title,
		        "",  // x-axis label
		        yAxis,  // y-axis label
		        (CategoryDataset)data,
		        orientation,
		        false,      // legend displayed
		        true,      // tooltips displayed
		        false);   // no URLs*/
			 
		CategoryPlot categoryPlot = chart.getCategoryPlot();
		CategoryAxis domainAxis = categoryPlot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		
		return chart;
	}

}
