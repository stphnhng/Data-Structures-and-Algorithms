public class BSTDict implements Dictionary {
	private class BSTNode{
		//TODO implement Node class
		public int key;
		public String value;
		public BSTNode left;
		public BSTNode right;
		
		public BSTNode(int k, String v, BSTNode l, BSTNode r){
			this.key = k;
			this.value = v;
			this.left = l;
			this.right = r;
		}
		
	}
	
	BSTNode root;
	
	public BSTDict(){
		root = null;
	}
	
	public void insert(int key, String value) {
		// TODO implement insert
		root = recursiveInsert(key,value, root);
	}
	
	public BSTNode recursiveInsert(int key, String value, BSTNode parent){
		if(parent == null){
			 return( new BSTNode(key,value,null,null) );
		}
		if(key <= parent.key){
			parent.left = recursiveInsert(key,value,parent.left);
		}else{
			parent.right = recursiveInsert(key,value,parent.right);
		}
		return parent;
	}

	public String find(int key) {
		// TODO implement find
		BSTNode start = root;
		while(start != null && key != start.key){
			if(key < start.key){
				start = start.left;
			}else{
				start = start.right;
			}
		}
		if(start != null){
			return start.value;
		}
		return null;
	}

	public boolean delete(int key) {
		// TODO implement delete
		root = recursiveDelete(key, root);
		return false;
	}
	
	public BSTNode recursiveDelete(int key, BSTNode parent){
		if(parent == null){
			return parent;
		}
		if(key < parent.key){
			parent.left = recursiveDelete(key, parent.left);
		}else if(key > parent.key){
			parent.right = recursiveDelete(key, parent.right);
		}else{
			if(parent.left != null && parent.right != null){
				// 2 children.
				
				// Do in order tranversal to get next node.
				BSTNode successorNode = findSuccessorNode(parent.right);
				// delete old node.
				parent.right = recursiveDelete(successorNode.key, parent.right);
				// Copy to parent's location
				parent.key = successorNode.key;
				parent.value = successorNode.value;
			}else if(parent.left != null){
				// 1 child (left)
				parent = parent.left;
			}else if(parent.right != null){
				// 1 child (right)
				parent = parent.right;
			}else{
				// no children.
				parent = null;
			}
		}
		// key == parent.key. Must have been removed by now, exit.
		return parent;
	}
	
	private BSTNode findSuccessorNode(BSTNode parent){
		while(parent.left != null){
			parent = parent.left;
		}
		return parent;
	}

}
