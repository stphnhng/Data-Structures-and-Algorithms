
public class BinaryHeap implements PriorityQueue{
	private class HeapData{
		private String data;
		private int priority;
		
		// Constructor that sets the Node's data & priority to
		// the passed in data & priority.
		protected HeapData(String dat,int pri){
			this.data = dat;
			this.priority = pri;
		}
		
		// Changes the current priority to the passed in one.
		protected void changePriority(int newPri){
			this.priority = newPri;
		}
		
		// Returns this node's data.
		protected String getData(){
			return this.data;
		}
		
		// Returns this node's priority.
		protected int getPriority(){
			return this.priority;
		}
		// Add additional functions here as necessary
		
	}
	
	private HeapData[] heap; // This should be the array where you store your data, priority pairs.
	// We recommend that you use an array that starts at index 1 to make the math easier
	
	private int size;
	// Number of elements in the Priority Queue / Heap.
		
	public BinaryHeap(){
		//TODO BinaryHeap constructor
		// Initializes an empty heap.
		
		size = 0;
		heap = new HeapData[1];
		heap[0] = null;
	}
	
	public BinaryHeap(int startArray){
		//TODO this constructor should set the start size of your heap array to startArray
		// Initializes an empty heap with a set amount of empty spaces.
		
		size = 0;
		heap = new HeapData[startArray+1];
		heap[0] = null;
	}
	
	/*
	 * This method returns if the Priority Queue is devoid of elements.
	 * @return a boolean that is true when it is empty and false when it isn't.
	 */
	public boolean isEmpty(){
		//TODO implement isEmpty
		return(size == 0);
	}
	
	/*
	 * This method returns the size of the Priority Queue.
	 * @return an integer that is the number of elements in the Priority Queue.
	 */
	public int size(){
		//TODO implement size
		return size;
	}
	
	/*
	 * This method returns the highest priority data point in the Priority Queue.
	 * @return a String that is the data of the highest priority node in the
	 *			Priority Queue. Returns null when there are no nodes.
	 *	
	 */
	public String findMin(){
		//TODO implement findMin
		if(!isEmpty()){
			return heap[1].getData();
		}
		return null;
	}
	
	/*
	 * This method inserts a Node into the PriorityQueue.
	 * @param data is a String that contains the data point for the Node to be inserted.
	 * @param priority is an Integer that contains the priority of the Node to 
	 * 		  be inserted.
	 */
	public void insert(String data, int priority){
		//TODO implement insert
		if(size == heap.length-1){
			// When the heap is full, double the size of the heap.
			HeapData[] tempHeap = new HeapData[heap.length * 2];
			for(int j = 1; j < heap.length; j++){
				tempHeap[j] = heap[j];
			}
			heap = tempHeap;
		}
		// Adds the new element to the heap and percolates up.
		heap[++size] = new HeapData(data,priority);
		percolateUp(size);
	}
	
	/*
	 * This method percolates up which compares the passed in index to it's
	 * parents in order to enforce heap property.
	 * @param indexToPercolate an integer that is the index for the Node that will
	 * 		  be percolated up.
	 */
	private void percolateUp(int indexToPercolate){
		boolean heapProperty = false;
		int recentIndex = indexToPercolate;
		while(!heapProperty){
			// While heap property is not enforced, check the parent to see if it
			// is greater than the current node. If it is switch places and repeat, if
			// not, heap property is enforced.
			int parentNode = recentIndex/2;
			if(parentNode > 0){
				if(heap[parentNode].getPriority() > heap[recentIndex].getPriority()){
					HeapData temp = heap[parentNode];
					heap[parentNode] = heap[recentIndex];
					heap[recentIndex] = temp;
					recentIndex = parentNode;
				}else{
					heapProperty = true;
				}
			}else{
				heapProperty = true;
			}
		}
	}
	
	/*
	 * This method returns and deletes the highest priority data value in the Queue.
	 * @return a String that contains the highest priority data value. Returns null
	 * 		   if there is nothing to delete.
	 */
	public String deleteMin(){
		//TODO implement deleteMin
		if(!isEmpty()){
			// Switches the root and the last node in preparation for percolateDown().
			HeapData root = heap[1];
			heap[1] = heap[size];
			if(size != 1){
				// Updates the size since a node was deleted.
				heap[size--] = null;
			}else if(size == 1){
				size = 0;
			}
			percolateDown();
			return root.getData();
		}
		return null;
	}
	
	/*
	 * This method percolatesDown by checking that the root node is following
	 * heap property.
	 */
	private void percolateDown(){
		boolean heapProperty = false;
		int parentIndex = 1;
		int parentPriority = heap[1].getPriority();
		while(!heapProperty){
			// While heap property is not enforced.
			
			if(parentIndex*2 <= size && parentIndex*2 + 1 <= size){
				// makes sure both left and right child exist.
				
				int leftPriority = heap[parentIndex*2].getPriority();
				// the priority of the left child.
				int rightPriority = heap[parentIndex*2 + 1].getPriority();
				// the priority of the right child.
				
				boolean leftSwitch = false; // whether the left can be switched.
				boolean rightSwitch = false; // whether the right can be switched.
				if(parentPriority >= leftPriority){
					// if the parent is greater than the left, the left can be switched.
					leftSwitch = true;
				}else if(parentPriority >= rightPriority){
					// if the parent is greater than the right, the right can be switched.
					rightSwitch = true;
				}else{
					// if the parent is not greater than any children, heap property is enforced.
					heapProperty = true;
				}
				if(leftSwitch || rightSwitch){
					// if either child can be switched, switch the lowest priority one.
					if(leftPriority <= rightPriority){
						parentIndex = swapChild(parentIndex, parentIndex*2);
					}else{
						parentIndex = swapChild(parentIndex, parentIndex*2 + 1);
					}
				}
			}else if(parentIndex*2 <= size){
				// if only left child exists, determine if it can be switched and
				// switch if need be.
				int leftPriority = heap[parentIndex*2].getPriority();
				if(parentPriority >= leftPriority){
					parentIndex = swapChild(parentIndex, parentIndex*2);
				}else{
					// does not need to be switched, heap property is enforced.
					heapProperty = true;
				}
			}else{
				// no children exist therefore no heap property to enforce.
				heapProperty = true;
			}
		}
		if(size == 0){
			// When the size is 0, set the 1st element to null.
			// This is used when percolatingDown() on a 1 element queue.
			heap[1] = null;
		}
	}
	
	/*
	 * 	This method swaps the nodes in a Priority Queue.
	 * @param parentIndex is an integer that is the index of the parent to be swapped.
	 * @param childIndex is an integer that is the index of the child to be swapped.
	 * @return the index to which the parent was swapped to (child's index).
	 */
	private int swapChild(int parentIndex, int childIndex){
		HeapData temp = heap[parentIndex];
		heap[parentIndex] = heap[childIndex];
		heap[childIndex] = temp;
		return childIndex;
	}
	
	/*
	 * This method makes the Priority Queue empty.
	 */
	public void makeEmpty(){
		//TODO implement makeEmpty
		for(int i = 1; i < size+1; i++){
			heap[i] = null;
		}
		size = 0;
	}
	
	/*
	 * This method changes the priority of a node with the given data point.
	 * @param data is the String of the node to have it's priority changed.
	 * @param newPri is the priority that the node's priority 
	 * 		  will be changed to.
	 * @return a boolean that is true when the priority has been changed and
	 * 		   false when the priority hasn't been changed.
	 */
	public boolean changePriority(String data, int newPri){
		//TODO implement changePriority
		for(int i = 1; i < size+1; i++){
			if(heap[i].getData() == data){
				// Found the node.
				heap[i].changePriority(newPri);
				// Change the priority and then swap the root and the changed
				// node.
				HeapData temp = heap[1];
				heap[1] = heap[i];
				heap[i] = temp;
				// Percolate down in order to sort the changed node.
				percolateDown();
				// Percolate up in order to sort the swapped root.
				percolateUp(i);
				return true;
			}
		}
		return false;
	}

}
