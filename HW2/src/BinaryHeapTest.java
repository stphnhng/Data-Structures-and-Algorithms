
public abstract class BinaryHeapTest {

	public static void main(String[] args) {
		// Use your tests here to make sure your implementation is working, this is for your help
		// Call your tests here to make sure that your implementation works.
		// This section will not be graded
		BinaryHeap bhEmpty = new BinaryHeap();
		BinaryHeap bhOne = new BinaryHeap(1);
		bhOne.insert("test", 3);
		BinaryHeap bhMany = new BinaryHeap(11);
		bhMany.insert("test1",4);
		bhMany.insert("test2",10);
		bhMany.insert("test3",3);
		bhMany.insert("test4",7);
		bhMany.insert("test5",8);
		bhMany.insert("test6",2);
		if(testEmpty(bhEmpty) && testOne(bhOne) && testMany(bhMany)){
			System.out.println("Priority Queue Implementation passed the tests.");
		}else{
			System.out.println("Priority Queue Implementation failed the tests.");
		}
		
	}
	
	//Make the below tests as thorough as possible.
	//Assume toTest is empty when the function is called
	//These will be tested against other implementations, both correct and incorrect, so be as thorough as you can
	//Only applicable functions need to be tested at each level.
	//Helper tests are strongly recommended
	
	/*
	 *  This method tests an empty PriorityQueue to make sure all functions work.
	 *  @param toTest is the empty BinaryHeap to be tested.
	 *  @return a boolean that is true when all tests are passed and false
	 *			when the Queue fails any of the tests.
	 */
	public static boolean testEmpty(BinaryHeap toTest){
		//TODO write a test case that tests functionality for all of the functions applicable to a BinaryHeap when it is empty
		//toTest should be empty when it is called
		//You may find writing helper tests to be useful
		//This should return true if it passes your tests, false otherwise
		boolean testCorrect = true; // boolean to make sure that all tests are passed.
		
		testCorrect = testEmptyTests(toTest);
		
		toTest.insert("test", 1);
		// inserting to test that makeEmpty() works.
		
		toTest.makeEmpty();
		// Should empty the queue and prepare it for another round of testing
		// since a empty queue due to initialization is different from having a
		// empty queue due to being made empty.
		
		testCorrect = testEmptyTests(toTest);
		// Repeats the same tests for an empty queue after inserting and removing
		// from a queue.
		
		return testCorrect;
	}
	
	/*
	 *  This method contains various tests for an empty PriorityQueue.
	 *  These tests are repeated and are therefore put into a separate helper method
	 *  to reduce redundancy.
 	 *  @param toTest is the BinaryHeap to be tested.
 	 *  @return a boolean value that is true when the heap passes the tests
 	 *		    and false when the heap fails any of the tests.
	 */
	private static boolean testEmptyTests(BinaryHeap toTest){
		
		if(toTest.size() != 0){
			// tests that the size is equal to 0.
			return false;
		}
		if(!toTest.isEmpty()){
			// The queue has to be empty otherwise the test fails.
			return false;
		}
		if(toTest.findMin() != null){
			// findMin() should return null since the queue is empty.
			return false;
		}
		if(toTest.deleteMin() != null){
			// deleteMin() should return null since the queue is empty.
			return false;
		}
		if(toTest.changePriority("missing", 1) == true){
			// changePriority() should return false since there is nothing to change.
			return false;
		}
		toTest.insert("test1", 3);
		// Tests inserting a data and priority pair into the Priority Queue.
		if(toTest.deleteMin() != "test1"){
			// Makes sure the Node was inserted into the Queue correctly.
			return false;
		}
		return true;
	}
	
	/*
	 *  This method tests a PriorityQueue with only one element inside of it.
	 *  @param toTest is the BinaryHeap to be tested. It contains one element
	 *  	   with the data, "test" and priority 3.
	 *  @return a boolean that is true when all tests are passed and false
	 *			when the Queue fails any of the tests.
	 */
	public static boolean testOne(BinaryHeap toTest){
		//TODO write a test case that tests functionality for all of the functions applicable to a BinaryHeap when it has one element
		//Add the element in this function, toTest should be empty when it is called.
		//You may find writing helper tests to be useful
		//This should return true if it passes your tests, false otherwise
		
		if(toTest.size() != 1){
			// If the size() is not one, then the Queue fails the test.
			return false;
		}
		if(toTest.isEmpty()){
			// If the Queue is empty, then it fails the test.
			return false;
		}
		if(toTest.findMin() != "test"){
			// If the Queue returns the wrong element, then it fails the test.
			return false;
		}
		toTest.insert("test2", 5);
		// Inserts a Node with a lower priority (higher number) than the initial
		// Node.
		if(toTest.size() != 2){
			// Makes sure that the new Node was actually element and the size
			// was updated to reflect that.
			return false;
		}
		if(toTest.deleteMin() != "test"){
			// Makes sure that the lower priority is returned and was
			// inserted correctly.
			return false;
		}
		toTest.insert("test3", 10);
		toTest.changePriority("test3",1);
		// Adds a new Node to test changing priorities.
		if(toTest.deleteMin() != "test3"){
			// Tests that the new Node's priority was updated correctly
			// from 10 to 1 thus making it a higher priority than the "test2" Node.
			return false;
		}
		
		// Don't need to test madeEmpty() due to having being tested
		// in testEmpty();
		
		return true;
	}
	
	/*
	 * 	This method tests a PriorityQueue when multiple elements are inside of it.
	 * 	@param toTest is the BinaryHeap to be tested.
	 * 	@return a boolean that is true when all tests are passed and false
	 * 			when the Queue fails any of the tests.
	 */
	public static boolean testMany(BinaryHeap toTest){
		//TODO write a test case that tests functionality for all of the functions applicable to a BinaryHeap when it has many elements
		//Add the elements to the BinaryHeap in this function, toTest should be empty when it is called.
		//You may find writing helper tests to be useful
		//This should return true if it passes your tests, false otherwise
		boolean testCorrect = true;
		
		testCorrect = testManyTests(toTest,6, "test6", "test2");
		// Tests a queue with 6 out of 11 elements that are manually entered.
		
		toTest.makeEmpty();
		// Empties the queue to test again due to differences when the queue is 
		// empty due to initialization or made empty.
		
		String randomData = ""; 
		// A random data string of a node that will have it's priority changed.
		
		for(int i = 1; i < 100; i++){
			// Creates 99 elements that have random data & priorities from
			// 1 to 100.
			String data = Integer.toString((int)(Math.random()*100+1));
			int priority = (int)(Math.random()*100+1);
			if(i == 51){
				// Chooses the 50th random Node generated to become 
				// the node that will have it's priority changed.
				randomData = data;
			}
			toTest.insert(data, priority);
			// inserts randomly generated Nodes.
		}
		toTest.insert("frontElement", 0);
		// Inserts an element that will become the root node in order 
		// to test that the root was inserted correctly.
		
		testCorrect = testManyTests(toTest,100,"frontElement",randomData);
		// Tests the randomly generated queue containing 100 elements.
		
		return testCorrect;
	}
	
	/*
	 * 	This method contains various tests for a Priority Queue containing 
	 * 	multiple elements.
	 * 	The tests are repeated and therefore put into a separate helper method 
	 * 	in order to reduce redundancy.
	 * 	@param toTest is the BinaryHeap to be tested.
	 * 	@return a boolean that is true when all tests are passed and false
	 * 			when the Queue fails any of the tests.
	 */
	private static boolean testManyTests(BinaryHeap toTest, int expectedSize, 
										String frontElement, String changeElement){
		if(toTest.size() != expectedSize){
			// Tests to make sure that the amount of nodes is the same as expected.
			return false;
		}
		if(toTest.isEmpty() != false){
			// Tests to make sure a filled Queue is not deemed empty.
			return false;
		}
		if(toTest.deleteMin() != frontElement){
			// Tests to make sure the Priority Queue's root is the correct one.
			return false;
		}
		toTest.insert("testNew", 0);
		if(toTest.findMin() != "testNew"){
			// Root should be the recently inserted "testNew" because it's priority
			// is 1, making it the highest priority Node.
			return false;
		}
		toTest.insert("testNewDuplicate", 0);
		if(!toTest.deleteMin().equals("testNew")){
			// Root should not be recently inserted "testnewDuplicate", even
			// though it's priorities are the same, duplicates should be removed
			// in a FIFO manner.
			return false;
		}
		if(!toTest.deleteMin().equals("testNewDuplicate")){
			// Tests that the second duplicate priority to be added is removed second.
			return false;
		}
		if(!toTest.changePriority(changeElement, 0)){
			// Tests that changePriority finds the node and changes it's priority.
			return false;
		}
		if(!toTest.deleteMin().equals(changeElement)){
			// Tests that changePriority actually changed the priority of passed in Node.
			return false;
		}
		toTest.makeEmpty();
		// Empties the queue.
		if(!toTest.isEmpty()){
			// Tests that the makeEmpty correctly empties a multi-element queue.
			return false;
		}
		
		return true;
	}

}
