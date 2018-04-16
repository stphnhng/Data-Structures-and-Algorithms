import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {
	/*Your functions (on the left) correspond to the following java Queue functions (on the right)
	 * toTest.enqueue(String a) = toCompare.add(String a)
	 * toTest.dequeue() = toCompare.poll()
	 * toTest.front() = toCompare.peek()
	 * 
	 * This private class performs this interface for you.
	 */
    private static class JavaQueue{
    	//DO NOT EDIT THIS CLASS
    	Queue<String> queue;
    	protected JavaQueue(){
    		queue = new LinkedList<String>();
    	}
    	protected void enqueue(String a){
    		queue.add(a);
    	}
    	protected String dequeue(){
    		return queue.poll();
    	}
    	protected String front(){
    		return queue.peek();
    	}
    }

	public static void main(String[] args){

		testEmpty( new ListQueue(),new JavaQueue());
		testOne(new ListQueue(),new JavaQueue());
		testMany(new ListQueue(),new JavaQueue());
			
	}
	
	public static boolean testEmpty(ListQueue yourQueue, JavaQueue correctQueue){
		//TODO implement a test of the three functions when the queues are empty
		//Compare the result of your queue  against the java implementation
		boolean frontCorrect = (yourQueue.front() == correctQueue.front() );
		// tests the front method.
		
		boolean dequeueCorrect = (yourQueue.dequeue() == correctQueue.dequeue() );
		// tests the dequeue method.
		
		// enqueues the string "Test" to both queues.
		yourQueue.enqueue("Test");
		correctQueue.enqueue("Test");
		
		// dequeues the element to test if enqueue worked.
		boolean enqueueCorrect = (yourQueue.dequeue().equals(correctQueue.dequeue()) );
		boolean dequeueCorrect2 = (yourQueue.dequeue() == correctQueue.dequeue() );
		// dequeues the empty queue to check if the implementation is correct.
		
		return (frontCorrect && dequeueCorrect && enqueueCorrect && dequeueCorrect2);
	}
	
	public static boolean testOne(ListQueue a, JavaQueue b){
		//TODO implement a test of the three functions when the queues have one element
		//Compare the result of your queue  against the java implementation
		
		// enqueues "Test" to both queues.
		a.enqueue("Test");
		b.enqueue("Test");
		
		// Tests if the front() method returns the same String in both queues.
		boolean frontCorrect = (a.front().equals(b.front()) );
		
		// Tests if the dequeue() method returns the same String in both queues.
		boolean dequeueCorrect = (a.dequeue().equals(b.dequeue()) );
		return(frontCorrect && dequeueCorrect);
	}
	
	public static boolean testMany(ListQueue a, JavaQueue b){
		//TODO implement a test of the three functions when the queues have many elements
		//Compare the result of your queue  against the java implementation
		//More than one test may be necessary
		
		// enqueues 10 different items to both queues.
		for(int i = 0; i < 10; i++){
			a.enqueue(Integer.toString(i));
			b.enqueue(Integer.toString(i));
		}
		boolean firstTest = testManyHelper(a,b);
		// calls testing method.
		
		// enqueues 100 different items to both queues.
		for(int j = 0; j < 100; j++){
			a.enqueue(Integer.toString(j) + " " + j);
			b.enqueue(Integer.toString(j) + " " + j);
		}
		boolean secondTest = testManyHelper(a,b);
		// calls testing method.
		
		return(firstTest && secondTest);
	}
	
	// ListQueue a is my implementation of a linked-list queue.
	// JavaQueue b is java's implementation of a queue.
	// Both of these queues contain enqueued data.
	// This function checks if the front element of the two queues are the same
	// and if the dequeued elements are the same.
	// If there are no errors this returns true otherwise it returns false.
	private static boolean testManyHelper(ListQueue a, JavaQueue b){
		
		// Checks if front() returns the same String in both queues.
		boolean frontCorrect = (a.front().equals(b.front()) );
		
		// Checks if the dequeued element is the same for both my implementation
		// and Java's implementation.
		boolean dequeueCorrect = true;
		for(int i = 0; i < 100; i++){
			// If size of queue is not equal to 100 (1st test of testMany)
			// dequeueCorrect will not be equal to false or raise an error.
			if(a.front() != null && b.front() != null){
				if(!a.dequeue().equals(b.dequeue()) ){
					dequeueCorrect = false;
				}
			}
		}
		return (frontCorrect && dequeueCorrect);
	}
}
