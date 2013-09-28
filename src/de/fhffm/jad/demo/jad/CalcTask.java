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

package de.fhffm.jad.demo.jad;
import java.awt.Color;
import java.util.TimerTask;

import de.fhffm.jad.demo.Constants;
import de.fhffm.jad.data.DataWrapper;
import de.fhffm.jad.data.EInputFields;
import de.fhffm.jad.demo.view.Controller;
import de.fhffm.jad.demo.view.EState;
import de.fhffm.jad.demo.view.ETimeSeries;
import de.fhffm.jad.mathematics.SimpleOperations;

/**
 * This class is a TimerTask using JAD to periodically calculate 
 * the Anomaly Detection Features.
 * @author Denis Hock
 *
 */
public class CalcTask extends TimerTask {	
	private DataWrapper dw;
	
	public CalcTask(){
		this.dw = DataFrame.getDataFrame();		
	}
	
    public void run() {		
    	//Set Default-Values:
    	double ipsrc_ent = 0.5;
    	double tcpdstport_ent = 0.5;
    	double meanframelen = 0;
    	double packetspersec = 0;
    	
    	//Try to send all captured observations to GNU R
    	if (DataFrame.write()) {    	
	    	SimpleOperations so = new SimpleOperations(dw);
	    	
	    	//Calculate Mean Frame.Len
	    	meanframelen = so.mean(EInputFields.framelen);
	    	System.out.println("Mean Frame.len: " + meanframelen);    	
	    	//Calculate ip.src Entrophy
	    	ipsrc_ent = so.entrophy(EInputFields.ipsrc);	    	
	    	
	    	System.out.println("Entrophy ip.src: " + ipsrc_ent);
	    	//Calculate tcp.dst.port Entrophy
	    	tcpdstport_ent = so.entrophy(EInputFields.tcpdstport);
	    	System.out.println("Entrophy tcp.dst.port: " +tcpdstport_ent);
	    	packetspersec = (dw.getObservationCount() / Constants.TIMEPERIOD);
	    	System.out.println("Packets per Second : " + packetspersec);
	    	System.out.println("========================");    	
	    	
	    	//Small hack to avoid some wired anomalies
	    	if (packetspersec < 10 && ipsrc_ent == 0){
	    		ipsrc_ent = 0.5;
	    	}
	    	//Small hack to avoid some wired anomalies
	    	if (packetspersec < 10 && tcpdstport_ent == 0){
	    		ipsrc_ent = 0.5;
	    	}
	    	
    	}
    	//Set new State
    	if (ipsrc_ent > 0.9 || ipsrc_ent < 0.1 || tcpdstport_ent > 0.9 || tcpdstport_ent < 0.1) {
    		Controller.setState(EState.anomaly);
    	}
    	else {
    		Controller.setState(EState.ok);
    	}
    	if (packetspersec > 100){
    		Controller.setPacketPerSecColor(Color.red);
    	}
    	else {
    		Controller.setPacketPerSecColor(Color.GRAY);
    	}
    
    	
    	Controller.addLineChartValue(ipsrc_ent*100, ETimeSeries.ipSrcEntrophy);
    	Controller.addLineChartValue(tcpdstport_ent*100, ETimeSeries.tcpDstPortEntrophy);
    	Controller.setPacketPerSec(packetspersec);
    }
}