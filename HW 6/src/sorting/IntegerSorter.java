package sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Class full of static sorting methods. Used to sort Integers.
 * 
 * TODO: Implement mergeSort() and selectionSort().
 * 
 * insertionSort is implemented for you as an example.
 * 
 * @author pattersp
 *
 */

public class IntegerSorter {
    /**
     * Sorts the given array of integers in ascending order according to the
     * comparator using mergesort. You may create as many private helper
     * functions as you wish to implement this method.
     * 
     * A note about ascending order:
     * 
     * When the method is finished, it should be true that:
     * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
     * array.length.
     * 
     * @param array
     *            the integers to sort
     * @param comparator
     *            The comparator the will be used to compare two integers.
     */
    public static void mergeSort(Integer[] array, Comparator<Integer> comparator) {
        // TODO: Add your merge sort algorithm here.
    	
    	Integer[] newArray = mergeSortRecurse(array,comparator);
    	for(int i = 0; i < newArray.length; i++){
    		// Cannot overwrite reference to array, thus simply
    		// overwrite each index.
    		array[i] = newArray[i];
    	}
    }
    
    private static Integer[] mergeSortRecurse(Integer[] input, Comparator<Integer> comparator){
    	if(input.length < 2){
    		return input;
    	}else{int mid = input.length/2;
    		Integer[] smallerInput = new Integer[mid];
    		Integer[] largerInput = new Integer[input.length - mid];
    		for(int i = 0; i < mid; i++){
    			smallerInput[i] = input[i];
    		}
    		for(int i = mid; i < input.length; i++){
    			largerInput[i-(mid)] = input[i];
    		}
    		Integer[] smallerHalf = mergeSortRecurse(smallerInput,comparator);
    		Integer[] largerHalf = mergeSortRecurse(largerInput, comparator);
    		return mergeSortCombine(smallerHalf, largerHalf, comparator);
    	}
    }
    
    private static Integer[] mergeSortCombine(Integer[] input1, Integer[] input2, Comparator<Integer> comparator){
    	int input1Pointer = 0;
    	int input2Pointer = 0;
    	int resultPointer = 0;
    	Integer[] result = new Integer[input1.length + input2.length];
    	while(input1Pointer < input1.length && input2Pointer < input2.length){
    		int input1Element = input1[input1Pointer];
    		int input2Element = input2[input2Pointer];
    		int comparison = comparator.compare(input1Element, input2Element);
    		if(comparison < 0){
    			result[resultPointer] = input1Element;
    			input1Pointer++;
    		}else if(comparison >= 0){
    			result[resultPointer] = input2Element;
    			input2Pointer++;
    		}
    		resultPointer++;
    	}
    	if(input1Pointer >= input1.length){
    		for(int i = input2Pointer; i < input2.length; i++){
    			result[resultPointer] = input2[i];
    			resultPointer++;
    		}
    	}else if(input2Pointer >= input2.length){
    		for(int i = input1Pointer; i < input1.length; i++){
    			result[resultPointer] = input1[i];
    			resultPointer++;
    		}
    	}
    	return result;
    }

    /**
     * Sort the array of integers in ascending order according to the comparator
     * using selection sort.
     * 
     * A note about ascending order:
     * 
     * When the method is finished, it should be true that:
     * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
     * array.length.
     * 
     * @param array
     *            the array of integer that will be sorted.
     * @param comparator
     *            The comparator the will be used to compare two integers.
     */
    public static void selectionSort(Integer[] array,
            Comparator<Integer> comparator) {
        // TODO: Add your selection sort algorithm here.
    	int sortedBoundary = 0;
    	for(int i = 0; i < array.length; i++){
    		int min = array[i];
    		int minIndex = i;
    		for(int j = array.length-1; j >= sortedBoundary; j--){
    			// Finds smallest value that is unsorted.
    			if(comparator.compare(array[j], min) <= 0){
    				min = array[j];
    				minIndex = j;
    			}
    		}
    		int temp = array[i];
    		array[i] = array[minIndex];
    		array[minIndex] = temp;
    		
    		sortedBoundary++;
    	}
    }
    

    /**
     * Sort the array of integers in ascending order according to the comparator
     * using insertion sort.
     * 
     * A note about ascending order:
     * 
     * When the method is finished, it should be true that:
     * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
     * array.length.
     * 
     * @param array
     *            the array of integers that will be sorted.
     * @param comparator
     *            The comparator the will be used to compare two integer.
     */
    public static void insertionSort(Integer[] array,
            Comparator<Integer> comparator) {
        for (int outerIndex = 1; outerIndex < array.length; outerIndex++) {
            Integer currentInt = array[outerIndex];
            int innerIndex = outerIndex - 1;
            while (innerIndex >= 0
                    && comparator.compare(currentInt, array[innerIndex]) < 0) {
                array[innerIndex + 1] = array[innerIndex];
                innerIndex--;
            }
            array[innerIndex + 1] = currentInt;
        }
    }
}
