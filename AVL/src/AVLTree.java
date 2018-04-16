import java.io.ObjectStreamException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author stephen
 *
 */
public class AVLTree implements StringTree{
	
	private class AVLNode{
		//Do not change these variable names
		String key;
		String value;
		AVLNode left;
		AVLNode right;
		
		//Place any additional fields you need here
		int height;
		
		//TODO implement the node class here
		
		public AVLNode(String k, String v, AVLNode l, AVLNode r){
			this.key = k;
			this.value = v;
			this.left = l;
			this.right = r;
			height = 1;
		}
	}
	
	public static void main(String[]args){

		AVLTree tree1 = new AVLTree();
		
		tree1.insert("Jan", "Hello");
		tree1.insert("Feb", "World");
		tree1.insert("Mar", "World");
		tree1.insert("Apr", "World");
		tree1.insert("May", "World");
		tree1.insert("Jun", "World");
		tree1.insert("Jul", "World");
		tree1.insert("Aug", "World");
		tree1.insert("Sep", "World");
		tree1.insert("Oct", "World");
		tree1.insert("Nov", "World");
		tree1.insert("Dec", "World");
		tree1.preorder(tree1.root);
	}
	
	//Use this variable as your root
	AVLNode root;
	private int size;
	
	//You may use any additional fields here as you see fit
	
	public int getMaxHeight(AVLNode left, AVLNode right){
		if(left.height < right.height){
			return right.height;
		}
		return left.height;
	}
	
	public void makeEmpty() {
		// TODO Remove all elements from the AVL tree.
		root = null;
		size = 0;
	}
	
	public int size() {
		// TODO Return the number of elements currently in the tree.
		return size;
	}

	private int height(AVLNode node){
		if(node == null){
			return 0;
		}
		return node.height;
	}
	
	private AVLNode rotateRight(AVLNode node){
		// Get the new root (left child of passed in node)
		AVLNode newRoot = node.left;
		// Get second node to be moved (right child of new root)
		AVLNode newRootRight = newRoot.right;
		
		// Rotate
		// new root's right child is old root
		newRoot.right = node;
		// old root's left child is the new root's old right child.
		node.left = newRootRight;
		
		// Update heights of new root and old root.
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;
		
		return newRoot;
	}
	
	private AVLNode rotateLeft(AVLNode node){
		// Get the new root (right child of passed in node)
		AVLNode newRoot = node.right;
		// Get second node to be moved (left child of new root)
		AVLNode newRootLeft = newRoot.left;
		
		// Rotate
		// new root's left child is old root
		newRoot.left = node;
		// old root's right child is new root's old left child.
		node.right = newRootLeft;
		
		// Update heights of new root and old root.
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;
		
		return newRoot;
	}
	
	private int getBalanceValue(AVLNode node){
		if(node == null){
			return 0;
		}
		return height(node.left) - height(node.right);
	}
	
	
	public void insert(String key, String value) {
		// TODO Insert the <key,value> pair into the AVLTree
		// Throw an IllegalArgumentException if the client attempts to insert a duplicate key
		try{
			if(find(key) != null){
				// Throws an IllegalArgumentException if the key could be found before insertion
				// because it would mean that the key is already in the AVLTree.
				throw new IllegalArgumentException("Cannot insert duplicate key: " + key);
			}
		}catch(NoSuchElementException e){};
		
		root = recursiveInsert(root,key,value);
		size++;
		
	}
	
	private AVLNode recursiveInsert(AVLNode node, String key, String value){
		if(node == null){
			return new AVLNode(key,value,null,null);
		}
		
		// Doesn't need else statement for duplicates since it is already taken
		// care of from insert().
		if(key.compareTo(node.key) < 0){
			node.left = recursiveInsert(node.left, key, value);
		}else if(key.compareTo(node.key) > 0){
			node.right = recursiveInsert(node.right, key, value);
		}
		
		// Updates current node's height
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		
		// gets balance to determine if need to rotate anything.
		int nodeBalance = getBalanceValue(node);
		
		if(nodeBalance > 1 && key.compareTo(node.left.key) < 0){
			return rotateRight(node);
		}
		
		if(nodeBalance < -1 && key.compareTo(node.right.key) > 0){
			return rotateLeft(node);
		}
		
		if(nodeBalance > 1 && key.compareTo(node.left.key) > 0){
			node.left = rotateLeft(node.left);
			return rotateRight(node);
		}
		
		if(nodeBalance < -1 && key.compareTo(node.right.key) < 0){
			node.right = rotateRight(node.right);
			return rotateLeft(node);
		}
		
		return node;
	}

	public String find(String key) {
		// TODO Return the value affiliated with the String key.
		// Throw an NoSuchElementException if the key is not in the AVLTree
		
		// Binary Search - may not be 100% correct and uses
		// String.compareTo in place of < and >.
		AVLNode start = root;
		while(start != null && !key.equals(start.key)){
			if(key.compareTo(start.key) < 0){
				start = start.left;
			}else{
				start = start.right;
			}
		}
		if(start != null){
			return start.value;
		}
		
		// if element could not be found.
		throw new NoSuchElementException(key + " was not found in the AVLTree");
	}
	
	public void preorder(AVLNode r)
    {
        if (r != null)
        {
            System.out.print(r.key + "h" + r.height + " ");
            preorder(r.left);             
            preorder(r.right);
        }
    }

	public Iterator<String> getBFSIterator() {
		// TODO Only complete this section if you wish to attempt the 10 points EC
		// This function should return a BFSIterator: Starter code provided below
		return null;
	}
	

	private class BFSIterator implements Iterator<String>{
		
		public boolean hasNext() {
			// TODO Return true if the iterator has another value to return
			return false;
		}

		public String next() {
			// TODO Return the next value in a BFS traversal of the tree
			// It should start at the root and iterate through left children before right
			return null;
		}
	
	}



}
