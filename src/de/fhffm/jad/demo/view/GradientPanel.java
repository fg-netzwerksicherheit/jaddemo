
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
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


/**
 * This Panel draws the gradient background
 * @author Denis Hock
 *
 */
public class GradientPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2572693685931203630L;

	@Override
	public void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g.create();
		    GradientPaint gp1 = new GradientPaint(getWidth()/2, 0, Color.GRAY, getWidth()/2, getHeight(), Color.WHITE, true);
		 
		    g2d.setPaint(gp1);
		    g2d.fillRect(0, 0, getWidth(), getHeight());
		 
	}
}
