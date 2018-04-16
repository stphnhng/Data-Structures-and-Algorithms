import java.util.Iterator;

public abstract class AVLTester {

	public static boolean verifyAVL(StringTree toTest){
		// TODO Return true if toTest is an AVL implementation of a String tree and false otherwise. 
		// All StringTree interface methods must behave correctly
		// You may assume that size() and isEmpty() return the correct values
		// Other than this, do not assume anything about the tree toTest, including its start size.
		
		// Tests both makeEmpty and size methods.
		toTest.makeEmpty();
		if(toTest.size() != 0){
			return false;
		}
		// AVL Tree that tests all rotations.
		toTest.insert("Jan", "a");
		toTest.insert("Feb", "l");
		toTest.insert("Mar", "k");
		toTest.insert("Apr", "b");
		toTest.insert("May", "j");
		toTest.insert("Jun", "c");
		toTest.insert("Jul", "g");
		toTest.insert("Aug", "i");
		toTest.insert("Sep", "d");
		toTest.insert("Oct", "h");
		toTest.insert("Nov", "e");
		toTest.insert("Dec", "f");
		
		String[] expectedResult = {"Mar", "Jan", "Oct", "Aug", "Jun", "May",
								   "Sep", "Apr", "Feb", "Jul", "Nov", "Dec"};
		int expectedCounter = 0;
		
		Iterator<String> iter = toTest.getBFSIterator();
		while(iter.hasNext()){
			String item = iter.next();
			if(!item.equals(expectedResult[expectedCounter])){
				return false;
			}
			expectedCounter++;
		}
		
		// Tests find() method.
		if(!toTest.find("Jan").equals("a")){
			return false;
		}else if(!toTest.find("Dec").equals("f")){
			return false;
		}else if(!toTest.find("Jul").equals("g")){
			return false;
		}
		
		// Tests that no duplicates are allowed.
		boolean illegalArgumentFound = false;
		try{
			toTest.insert("Jan", "b");
			toTest.insert("Jan", "a");
		}catch(IllegalArgumentException e){
			illegalArgumentFound = true;
		}
		if(!illegalArgumentFound){
			return false;
		}
		
		return true;
	}
	
	// You may use as many static helper functions as you think are necessary
}

