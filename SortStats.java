package com.bleh.www;
import java.io.*;
/**
 * A top level class that brings in unsorted data sets of integers, applies
 * multiple types of sorts, collects statistics on efficiency of each sort,
 * and then prints all of that data to a new related file.
 * @author connor
 *
 */
public class SortStats {
//These are the global variables that will hold a count of comparisons and
//interchanges while a sort executes.	
static int bubbleComparison = 0;
static int bubbleSwap = 0;
static int quickPartCount = 0;
static int quickPartCompare = 0;
static int quickPartSwapOne = 0;
static int quickPartSwapTwo = 0;
static int insertShiftCount = 0;
static int insertCount = 0;
	/**
	 * This method executes a bubble sort on an integer array.
	 * @param a is the array to be used.
	 */
	public static void bubbleSort(int[] a) {
	    boolean sorted = false;
	    int temp;
	    while(!sorted) {
	        sorted = true;
	        for (int i = 0; i < a.length - 1; i++) {
	        	bubbleComparison++; //Comparison counter added here
	        	if (a[i] > a[i+1]) {
	        		bubbleSwap++;//Swamp counter added here
	                temp = a[i];
	                a[i] = a[i+1];
	                a[i+1] = temp;
	                sorted = false;
	            }
	        }
	    }
	}
	/**
	 * This method partitions/divides an array for the quicksort
	 * to use later on.
	 * @param array is the array to be used
	 * @param begin is the first index/element of the array.
	 * @param end is the end of the array.
	 * @return is the element of the array to be used (initially as a pivot).
	 */
	static int partition(int[] array, int begin, int end) {
	    quickPartCount++;
		int pivot = end;
	    
	    int counter = begin;
	    for (int i = begin; i < end; i++) {
	    	quickPartCompare++;
	        if (array[i] < array[pivot]) {
	        	quickPartSwapOne++;//quick swap counter add
	            int temp = array[counter];
	            array[counter] = array[i];
	            array[i] = temp;
	            counter++;
	        }
	    }
	    quickPartSwapTwo++;
	    int temp = array[pivot];
	    array[pivot] = array[counter];
	    array[counter] = temp;

	    return counter;
	}
	/**
	 * This method will be the main driver of the quicksort and will use the 
	 * partition method as well as itself recursively to divide, compare, and swap
	 * an array's elements until it is fully sorted.
	 * @param array is the array to be sorted.
	 * @param begin is the starting element/index.
	 * @param end is the ending element/index.
	 */
	public static void quickSort(int[] array, int begin, int end) {
	    if (end <= begin) return;
	    int pivot = partition(array, begin, end);
	    quickSort(array, begin, pivot-1);
	    quickSort(array, pivot+1, end);
	}
	/**
	 * This method performs an insertion style sort on an array.
	 * @param array is the array to be sorted.
	 */
	public static void insertionSort(int[] array) {
	    for (int i = 1; i < array.length; i++) {
	        int current = array[i];
	        int j = i - 1;
	        while(j >= 0 && current < array[j]) {
	        	insertShiftCount++;
	            array[j+1] = array[j];
	            j--;
	        }
	        array[j+1] = current;
	        insertCount++;
	    }
	}
	/**
	 * This method is used to process data sets from input, sort it, and then print it
	 * in the correct format.
	 * @param inFile is the name of input file.
	 * @param outFile is the name of output file.
	 * @throws Exception if the file cannot be opened.
	 */
	public static void processData(String inFile, String outFile) throws Exception {
		PrintStream ps = new PrintStream(new FileOutputStream(new File(outFile)));
		DataSet temp = new DataSet(inFile);
		temp.printData(ps);
		int[] data1 = temp.getData().clone();
		int[] data2 = temp.getData().clone();
		int[] data3 = temp.getData().clone();
		
		bubbleComparison = 0;
		bubbleSwap = 0;
		bubbleSort(data1);
		ps.println("\nBubbleSort");
		printArray(ps,data1);
		ps.println("The number of comparisons within the Bubble Sort: "+bubbleComparison);
		ps.println("The number of swaps within the Bubble Sort: "+bubbleSwap);
		
		quickPartCount = 0;
		quickPartCompare = 0;
		quickPartSwapOne = 0;
		quickPartSwapTwo = 0;
		quickSort(data2, 0, data2.length-1);
		ps.println("\nQuickSort");
		printArray(ps,data2);
		ps.println("The number of total Partitions within the Quick Sort: "+quickPartCount);
		ps.println("The number of comparisons within the Quick Sort: "+quickPartCompare);
		ps.println("The number of Initial Swaps within the Quick Sort: "+quickPartSwapOne);
		ps.println("The number of Secondary Swaps within the Quick Sort: "+quickPartSwapTwo);

		insertShiftCount = 0;
		insertCount = 0;
		insertionSort(data3);
		ps.println("\nInsertSort");
		printArray(ps,data3);
		ps.println("The number of Shifts within the Insertion Sort: "+insertShiftCount);
		ps.println("The number of Insertions within the Insertion Sort: "+insertCount);
	}
	/**
	 * This is a helper function to print the elements of a data set/array
	 * @param ps is the PrintStream being used.
	 * @param array is the array to be printed.
	 */
	public static void printArray(PrintStream ps, int[] array) {
		ps.println("Sorted: ");
		for(int i=0; i<array.length; i++) {
			ps.println(array[i]);
		}
	}
	/**
	 * Main is the location where we provide all input and output file names.
	 * @param args command line arguments
	 * @throws Exception if no file can be opened or found.
	 */
	public static void main(String[] args) throws Exception {
		
		processData("FiftyAscending.txt", "FiftyAscendingResult.txt");
		processData("FiftyDescending.txt", "FiftyDescendingResult.txt");
		processData("FiftyRandom.txt", "FiftyRandomResult.txt");
		processData("ThirtyAscending.txt", "ThirtyAscendingResult.txt");
		processData("ThirtyDescending.txt", "ThirtyDescendingResult.txt");
		processData("ThirtyRandom.txt", "ThirtyRandomResult.txt");
		processData("TenAscend.txt", "TenAscendResult.txt");
		processData("TenDescend.txt", "TenDescendResult.txt");
		processData("TenRandom.txt", "TenRandomResult.txt");
		
	}

}
