
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

import javax.swing.JLabel;
import javax.swing.JPanel;



/**
 * @author Denis Hock
 * Shows a message with colored background dependent on the current state of the application
 */
public class PCStatusPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3285453146412212027L;
	private static JLabel lblstate;
	private static PCStatusPanel instance;
	
	public PCStatusPanel(){
		instance = this;
		lblstate = new JLabel();
		lblstate.setForeground(Color.GRAY);
		lblstate.setFont(new Font("Arial", Font.BOLD, 14));
		add(lblstate);
		setState(EState.ok);
	}
	
	public void setState(EState state){
		
		switch(state){
			case ok: {
				instance.setBackground(Color.GREEN);
				lblstate.setText("State: OK");
				break;
			}
			case anomaly: {
				instance.setBackground(Color.RED);
				lblstate.setText("State: Anomaly Detected");
				break;
			}
		}	
	}
}
