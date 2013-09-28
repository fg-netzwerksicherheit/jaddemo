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
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;

import de.fhffm.jNetPcapFacade.sniffer.data.Packet;
import de.fhffm.jNetPcapFacade.sniffer.processor.PacketProcessor;
import de.fhffm.jNetPcapFacade.sniffer.util.ToStringHelper;
import de.fhffm.jad.demo.jad.DataFrame;

/**
 * This class receives all packets from our JNetPcap Packet-Queue
 * @author Denis Hock
 *
 */
public class FeatureProcessor implements PacketProcessor {

	private Ip4 ip4 = new Ip4();
	private Tcp tcp = new Tcp();
	
    @Override
    /**
     * This function is called when a packet is captured
     */
    public void forwardPacket(Packet packet) {
    	
    	//Get Packet to process the interesting contents ...
        PcapPacket pcapPacket = packet.getPcapPacket();
        String framelen = "";
        String ipsrc = "";
        String tcpdst = "";
        
        //Packet size
        framelen = Integer.toString(pcapPacket.getTotalSize());
        
        //Tcp/Udp ...
        if (pcapPacket.hasHeader(ip4)) {
        	ipsrc = ToStringHelper.IpToString(ip4.source());
        }

        //TCP Ports and Flags
        if (pcapPacket.hasHeader(tcp)) {    
            tcpdst = Integer.toString(tcp.destination());           
        }
        
        //Push everything to the GNU R Class ..
        DataFrame.add(new String[] {ipsrc, tcpdst, framelen});
         
    }
       
}

