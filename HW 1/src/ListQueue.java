// Stephen Hung

public class ListQueue {
	private class Node{
		//TODO Implement Linked List Node
		private Node nextNode;
		// the next node in this linked list.
		private String data;
		// the data for the current node.
		
		// Constructor a Node with the passed in data and null as it's next node.
		public Node(String inputData){
			this.data = inputData;
			this.nextNode = null;
		}
		
		// Gets Data of current node.
		public String getData(){
			return this.data;
		}
		
		// Gets the next node from this one.
		public Node getNextNode(){
			return this.nextNode;
		}
		
		// Sets the next node of this one.
		public void setNextNode(Node next){
			this.nextNode = next;
		}
	}
	
	//Class variables here, if necessary
	
	// Front node in queue.
	private Node frontElement;
	// Rear node in queue.
	private Node rearElement;
	
	public ListQueue(){
		//TODO Implement constructor
		frontElement = rearElement = null;
	}
	
	// Adds a node to the queue.
	public void enqueue(String toInput) {
		// TODO Implement enqueue
		if(frontElement == null){
			frontElement = new Node(toInput);
			rearElement = frontElement;
		}else{
			rearElement.setNextNode(new Node(toInput));
			rearElement = rearElement.getNextNode();
		}
	}
	
	// Removes a node from the queue.
	public String dequeue(){
		// TODO Implement dequeue
		Node returnElement = frontElement;
		if(returnElement != null){
			frontElement = frontElement.getNextNode();
			return returnElement.getData();
		}
		return null;
	}
	
	// Returns the data from the front node.
	public String front(){
		// TODO Implement front
		if(frontElement != null){
			return frontElement.getData();
		}
		return null;
	}

}
