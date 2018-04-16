package sorting;

import java.util.Comparator;

/**
 * Class full of static sorting methods. Used to sort packets received from a
 * server containing image metadata.
 * 
 * TODO: Implement mergeSort() and selectionSort().
 * 
 * insertionSort is implemented for you as an example.
 * 
 * @author pattersp
 *
 */

public class PacketSorter {
    /**
     * Sorts the given array of packets in ascending order according to the
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
     *            the packets to sort
     * @param comparator
     *            The comparator the will be used to compare two packets.
     */
    public static void mergeSort(Packet[] array, Comparator<Packet> comparator) {
        // TODO: Add your merge sort algorithm here.
    	Packet[] newArray = mergeSortRecurse(array,comparator);
    	for(int i = 0; i < newArray.length; i++){
    		// Cannot overwrite reference to array, thus simply
    		// overwrite each index.
    		array[i] = newArray[i];
    	}
    }
    
    private static Packet[] mergeSortRecurse(Packet[] input, Comparator<Packet> comparator){
    	if(input.length < 2){
    		return input;
    	}else{int mid = input.length/2;
    		Packet[] smallerInput = new Packet[mid];
    		Packet[] largerInput = new Packet[input.length - mid];
    		for(int i = 0; i < mid; i++){
    			smallerInput[i] = input[i];
    		}
    		for(int i = mid; i < input.length; i++){
    			largerInput[i-(mid)] = input[i];
    		}
    		Packet[] smallerHalf = mergeSortRecurse(smallerInput,comparator);
    		Packet[] largerHalf = mergeSortRecurse(largerInput, comparator);
    		return mergeSortCombine(smallerHalf, largerHalf, comparator);
    	}
    }
    
    private static Packet[] mergeSortCombine(Packet[] input1, Packet[] input2, Comparator<Packet> comparator){
    	int input1Pointer = 0;
    	int input2Pointer = 0;
    	int resultPointer = 0;
    	Packet[] result = new Packet[input1.length + input2.length];
    	while(input1Pointer < input1.length && input2Pointer < input2.length){
    		Packet input1Element = input1[input1Pointer];
    		Packet input2Element = input2[input2Pointer];
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
     * Sort the array of packets in ascending order using selection sort.
     * 
     * A note about ascending order:
     * 
     * When the method is finished, it should be true that:
     * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
     * array.length.
     * 
     * @param array
     *            the array of packets that will be sorted.
     * @param comparator
     *            The comparator the will be used to compare two packets.
     */
    public static void selectionSort(Packet[] array,
            Comparator<Packet> comparator) {
        // TODO: Add your selection sort algorithm here.
    	int sortedBoundary = 0;
    	for(int i = 0; i < array.length; i++){
    		Packet min = array[i];
    		int minIndex = i;
    		for(int j = array.length-1; j >= sortedBoundary; j--){
    			// Finds smallest value that is unsorted.
    			if(comparator.compare(array[j], min) <= 0){
    				min = array[j];
    				minIndex = j;
    			}
    		}
    		Packet temp = array[i];
    		array[i] = array[minIndex];
    		array[minIndex] = temp;
    		
    		sortedBoundary++;
    	}
    }

    /**
     * Sort the array of packets in ascending order using insertion sort.
     * 
     * A note about ascending order:
     * 
     * When the method is finished, it should be true that:
     * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
     * array.length.
     * 
     * @param array
     *            the array of packets that will be sorted.
     * @param comparator
     *            The comparator the will be used to compare two packets.
     */
    public static void insertionSort(Packet[] array,
            Comparator<Packet> comparator) {
        for (int outerIndex = 1; outerIndex < array.length; outerIndex++) {
            Packet currentPacket = array[outerIndex];
            int innerIndex = outerIndex - 1;
            while (innerIndex >= 0
                    && comparator.compare(currentPacket, array[innerIndex]) < 0) {
                array[innerIndex + 1] = array[innerIndex];
                innerIndex--;
            }
            array[innerIndex + 1] = currentPacket;
        }
    }
}
