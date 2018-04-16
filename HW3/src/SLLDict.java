public class SLLDict implements Dictionary {
	private class Node{
		//TODO implement Node class
		Node next;
		int key;
		String value;
		
		public Node(int k, String v, Node n){
			this.next = n;
			this.key = k;
			this.value = v;
		}
		
	}
	
	Node first;
	Node last;
	
	public SLLDict(){
		first = last = null;
	}
	
	public void insert(int key, String value) {
		// TODO implement insert
		if(first == null){
			first = new Node(key,value, null);
		}else if(first.key > key){
			Node temp = first;
			first = new Node(key,value,temp);
		}else{
			last = first;
			while(last.next != null && last.next.key < key){
				last = last.next;
			}
			if(last.next == null){
				last.next = new Node(key,value,null);
			}else{
				Node temp = last.next;
				last.next = new Node(key,value,temp);
			}
		}
	}

	public String find(int key) {
		// TODO implement find
		Node start = first;
		while(start != null){
			if(start.key == key){
				return start.value;
			}
			start = start.next;
		}
		return null;
	}

	public boolean delete(int key) {
		// TODO implement delete
		Node start = first;
		Node prev = first;
		while(start != null){
			if(start.key == key){
				if(start == first){
					first = start.next;
				}else{
					prev.next = start.next;
				}
				return true;
			}
			prev = start;
			start = start.next;
		}
		return false;
	}

}
