
public class ULLDict implements Dictionary {
	private class Node{
		//TODO implement Node class
		Node next;
		int key;
		String value;
		
		public Node(int k, String v, Node n){
			this.key = k;
			this.value = v;
			this.next = n;
		}
		
	}
	
	Node first;
	Node last;
	
	public ULLDict(){
		first = last = null;
	}
	
	public void insert(int key, String value) {
		// TODO implement insert
		if(first == null){
			first = new Node(key,value, null);
			last = first;
		}else{
			last.next = new Node(key, value, null);
			last = last.next;
		}
	}

	public String find(int key) {
		// TODO implement find
		Node iterator = first;
		while(iterator != null){
			if(iterator.key == key){
				return iterator.value;
			}
			iterator = iterator.next;
		}
		return null;
	}

	public boolean delete(int key) {
		// TODO implement delete
		Node deleter = first;
		Node prevNode = first;
		while(deleter != null){
			if(deleter.key == key){
				prevNode.next = deleter.next;
				if(deleter == first){
					first = deleter.next;
				}else if(deleter == last){
					last = prevNode;
				}
				return true;
			}
			prevNode = deleter;
			deleter = deleter.next;
		}
		return false;
	}

}
