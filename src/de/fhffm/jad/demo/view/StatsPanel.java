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

import java.awt.Color;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * JPanel at the bottom - shows the Packetcount per Second
 * @author Denis Hock
 *
 */
public class StatsPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8842392835116435077L;
	private JLabel pclabel;
	
	public StatsPanel(){
		setBackground(Color.WHITE);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		pclabel = new JLabel("Packetcount: ");		
		pclabel.setForeground(Color.GRAY);
		pclabel.setFont(new Font("Arial", Font.BOLD, 14));	
		add(pclabel);		
	}
	
	public void setPacketPerSec(double value){
		pclabel.setText("Packets per Second: " + value);
	}
	
	public void setLabelColor(Color color){
		pclabel.setForeground(color);
	}

}
