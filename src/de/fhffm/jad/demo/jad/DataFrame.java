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
import java.util.ArrayList;

import de.fhffm.jad.data.DataWrapper;
import de.fhffm.jad.data.EInputFields;
import de.fhffm.jad.data.IDataFieldEnum;

/**
 * This class synchronizes the access to our Data.Frame
 * Added Observations are stored in a local ArrayList.
 * Use 'write' to send everything to GNU R.
 * @author Denis Hock
 */
public class DataFrame {
	private static DataWrapper dw = null;
	private static ArrayList<String[]> rows = new ArrayList<>();
	
	/**
	 * @return Singleton Instance of the GNU R Data.Frame
	 */
	public static DataWrapper getDataFrame(){		
		if (dw == null){
			//Create the data.frame for GNU R:
			dw = new DataWrapper("data");
			clear();
		}
		return dw;
	}
	
	/**
	 * Delete the old observations and send all new observations to Gnu R
	 * @return 
	 */
	public synchronized static boolean write(){
		
		if (rows.size() < 1){
			return false;
		}
		
		//Clear the R-Data.Frame
		clear();
		//Send all new Observations to Gnu R
		for(String[] row : rows)
			dw.addObservation(row);
		//Clear the local ArrayList
		rows.clear();
		
		return true;
	}
	
	/**
	 * These Observations are locally stored and wait for the write() command
	 * @param row
	 */
	public synchronized static void add(String[] row){
        //Store everything in an ArrayList
		rows.add(row);
	}
	
	/**
	 * Clear local ArrayList and GNU R Data.Frame
	 */
	private static void clear(){		 
		ArrayList<IDataFieldEnum> fields = new ArrayList<IDataFieldEnum>();
		fields.add(EInputFields.ipsrc);
		fields.add(EInputFields.tcpdstport);
		fields.add(EInputFields.framelen);
		dw.createEmptyDataFrame(fields);
	}

}
