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

package de.fhffm.jad.demo.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Controller to change the window state and update labels and graphics.
 * @author Denis Hock
 *
 */
public class Controller {

	private static JFrame frame;
    private static LineChartPanel linechart;
    private static StatsPanel stats;
    private static PCStatusPanel statepanel;

    public static void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("JAD Demo");
        //Taskbar Icon
        frame.setIconImage(new ImageIcon("bertico.png").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 550);
        
        //Add content to the window.
        
        //Create Linechart within mainpanel
        linechart = new LineChartPanel();
        //Create Statspanel
        stats = new StatsPanel();
        //main.add(stats, BorderLayout.NORTH);    
        
        GradientPanel background = new GradientPanel();
        background.add(linechart);
        frame.add(background, BorderLayout.CENTER);
        frame.add(stats, BorderLayout.SOUTH);
        
        //Status Panel shows an Anomaly or OK State
        statepanel = new PCStatusPanel();
        frame.add(statepanel,BorderLayout.NORTH);                       
        //Display the window.
        frame.setVisible(true);
    }

    public static void addLineChartValue(double value, ETimeSeries series){
    	if (series == ETimeSeries.ipSrcEntrophy) {
    		linechart.addValue(value,1);
    	}
    	else if (series == ETimeSeries.tcpDstPortEntrophy){
    		linechart.addValue(value,2);
    	}
    	linechart.update();
    }
    
    public static void setState(EState state){
    	statepanel.setState(state);
    }
    
    public static void setPacketPerSec(double value){
    	stats.setPacketPerSec(value);
    }
    
    public static void setPacketPerSecColor(Color color){
    	stats.setLabelColor(color);
    }
}
