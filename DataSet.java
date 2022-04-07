package com.bleh.www;

import java.io.*;
import java.util.*;
/**
 * This class allows the creation of a DataSet object that will hold header and 
 * integer array information for each file read in.
 * @author connor
 *
 */
public class DataSet {
	private String header_;
	private int[] data_;
	/**
	 * Constructor to create a DataSet Instance.
	 * @param fileName is the name of the file.
	 * @throws Exception if the file cannot be opened.
	 */
	DataSet (String fileName) throws Exception {
		Scanner scanIn = new Scanner(new FileInputStream (new File (fileName)));
		header_ = scanIn.nextLine();
		LinkedList<Integer> temp = new LinkedList<Integer>();
		while (scanIn.hasNextInt()) {
		temp.add(scanIn.nextInt());
		}
		data_ = new int[temp.size()];
		for(int i =0; i<temp.size(); i++) {
			data_[i] = temp.get(i);
		}
	}
	/**
	 * A getter to retrieve the header of the input file.
	 * @return the header of the input file.
	 */
	public String getHeader() {
		return header_;
	}
	/**
	 * A getter to retrieve the data of the input file.
	 * @return the data of the input file.
	 */
	public int[] getData() {
		return data_;
	}
	/**
	 * A method to print the data/array of the data file out to a 
	 * new file.
	 * @param ps is the PrintStream to be used.
	 */
	public void printData(PrintStream ps) {
		ps.println(header_);
		for(int i=0; i<data_.length; i++) {
			ps.println(data_[i]);
		}
	}
	
}
