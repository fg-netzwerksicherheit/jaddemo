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

package de.fhffm.jad.demo.sniffer;

import java.util.Timer;

import de.fhffm.jNetPcapFacade.config.Constants;
import de.fhffm.jNetPcapFacade.sniffer.util.PcapStartupHelper;
import de.fhffm.jad.demo.jad.CalcTask;

public class Sniffer {
	
	private static PcapStartupHelper sniffer = null;
	private static Timer timer;
	private static CalcTask task;
	
	/**
	 * Create all necessary objects to capture packets on the 'ANY'-Device with JNetPcap
	 * (class 'FeatureProcessor' is responsible for the processing of the packets) 
	 */
	private static void create(){
		//Create the StartupHelper to start the sniffer
		sniffer = new PcapStartupHelper(Constants.ANY_DEVICE);
			
		//create Packet Processor
		FeatureProcessor processor = new FeatureProcessor();
		sniffer.addProcessor(processor);
	}
	
	/**
	 * Starts to capture packets
	 */
	public static void start(){
	
		if (sniffer == null){
			create();
		}
		
		try {
			sniffer.start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		timer = new Timer();
		task = new CalcTask();
    	timer.schedule(task, 0, (long) (de.fhffm.jad.demo.Constants.TIMEPERIOD *1000));
		
	}
	
	/**
	 * Stop capturing packets
	 * TODO: Not working with Windows 8?
	 */
	public static void stop(){
		try {
			sniffer.stop();
			task.cancel();
			timer.purge();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
