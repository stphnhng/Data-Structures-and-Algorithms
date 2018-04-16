import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.Arrays;
import java.util.Collections;


public class TestSuite {
	public static void main(String[]args){
		TestSuite tester = new TestSuite();
		//tester.testULL();
		//tester.testUA();
		//tester.testSLL();
		//tester.testSA();
		//tester.testBST();
		
		for(int i = 500; i <= 5500; i+=1000){
			System.out.println("(" + i + ") Delete Average Time: " + 
								tester.bstTester(i) );
		}
	}
	
	public Set<Integer> generateRandomSet(int size){
		Set<Integer> randomSet = new LinkedHashSet<Integer>();
		Random r = new Random();
		while(randomSet.size() != size){
			int newAddition = r.nextInt(size);
			randomSet.add(newAddition);
		}
		return randomSet;
	}
	
	public void testULL(){
		System.out.println("ULL DICT TESTING");
		for(int i = 500; i <= 5500; i+=1000){
			System.out.println("(" + i + ") Insert Average Time: " + 
								insertWrapper(i,new ULLDict()) );
		}

		for(int i = 500; i <= 5500; i+=1000){
			System.out.println("(" + i + ") Find Average Time: " + 
								findWrapper(i, new ULLDict()) );
		}
		
		for(int i = 500; i <= 5500; i+=1000){
			System.out.println("(" + i + ") Delete Average Time: " + 
								deleteWrapper(i, new ULLDict()) );
		}
	}
	
	public void testUA(){
		System.out.println("UA DICT TESTING");
		for(int i = 500; i <= 5500; i+=1000){
			System.out.println("(" + i + ") Insert Average Time: " + 
								insertWrapper(i,new UADict()) );
		}

		for(int i = 500; i <= 5500; i+=1000){
			System.out.println("(" + i + ") Find Average Time: " + 
								findWrapper(i, new UADict()) );
		}
		
		for(int i = 500; i <= 5500; i+=1000){
			System.out.println("(" + i + ") Delete Average Time: " + 
								deleteWrapper(i, new UADict()) );
		}
	}
	
	public void testSLL(){
		System.out.println("SLL DICT TESTING");
		for(int i = 500; i <= 5500; i+=1000){
			System.out.println("(" + i + ") Insert Average Time: " + 
								insertWrapper(i,new SLLDict()) );
		}

		for(int i = 500; i <= 5500; i+=1000){
			System.out.println("(" + i + ") Find Average Time: " + 
								findWrapper(i, new SLLDict()) );
		}
		
		for(int i = 500; i <= 5500; i+=1000){
			System.out.println("(" + i + ") Delete Average Time: " + 
								deleteWrapper(i, new SLLDict()) );
		}
	}
	
	public void testSA(){
		System.out.println("SA DICT TESTING");
		for(int i = 500; i <= 5500; i+=1000){
			System.out.println("(" + i + ") Insert Average Time: " + 
								insertWrapper(i,new SADict()) );
		}

		for(int i = 500; i <= 5500; i+=1000){
			System.out.println("(" + i + ") Find Average Time: " + 
								findWrapper(i, new SADict()) );
		}
		
		for(int i = 500; i <= 5500; i+=1000){
			System.out.println("(" + i + ") Delete Average Time: " + 
								deleteWrapper(i, new SADict()) );
		}
	}
	
	public void testBST(){
		System.out.println("BST DICT TESTING");
		for(int i = 500; i <= 5500; i+=1000){
			System.out.println("(" + i + ") Insert Average Time: " + 
								insertWrapper(i,new BSTDict()) );
		}

		for(int i = 500; i <= 5500; i+=1000){
			System.out.println("(" + i + ") Find Average Time: " + 
								findWrapper(i, new BSTDict()) );
		}
		
		for(int i = 500; i <= 5500; i+=1000){
			System.out.println("(" + i + ") Delete Average Time: " + 
								deleteWrapper(i, new BSTDict()) );
		}
	}
	
	public double bstTester(int size){
		long[] ullInsert = new long[6];
		long sumInsert = 0;
		for(int i = 0; i < 6; i++){
			ullInsert[i] = bstFindTest(size);
			if(i != 0){
				sumInsert += ullInsert[i];
			}
		}
		double averageTime = (double)(sumInsert)/10;
		// divides by 10 for the 10 test cases.
		return averageTime;
	}
	
	public long bstFindTest(int size){
		BSTDict dict = new BSTDict();
		
		// Worst Case
		for(int i = 0; i < size; i++){
			dict.insert(i, i + " " + i);
		}
		long start = System.currentTimeMillis();
		for(int i = size-1; i >= 0; i--){
			dict.find(i);
		}
		long end = System.currentTimeMillis();
		return end-start;
	}
	
	public double insertWrapper(int size, Dictionary dict){
		long[] ullInsert = new long[6];
		long sumInsert = 0;
		for(int i = 0; i < 6; i++){
			ullInsert[i] = testInsert(size, dict);
			if(i != 0){
				sumInsert += ullInsert[i];
			}
		}
		double averageTime = (double)(sumInsert)/10;
		// divides by 10 for the 10 test cases.
		return averageTime;
	}
	
	public long testInsert(int size, Dictionary dict){
		Set<Integer> randomSet = generateRandomSet(size);
		Integer[] randomArray = randomSet.toArray(new Integer[randomSet.size()]);
		long start = System.currentTimeMillis();
		for(int i = 0; i < size; i++){
			dict.insert(randomArray[i], randomArray[i] + " " + randomArray[i]);
		}
		long end = System.currentTimeMillis();
		return end-start;
	}
	
	public double findWrapper(int size, Dictionary dict){
		long[] ullFind = new long[6];
		long sumFind = 0;
		for(int i = 0; i < 6; i++){
			ullFind[i] = testFind(size, dict);
			if(i != 0){
				sumFind += ullFind[i];
			}
		}
		double averageTime = (double)(sumFind)/10;
		return averageTime;
	}
	
	public long testFind(int size, Dictionary dict){
		Set<Integer> randomSet = generateRandomSet(size);
		Integer[] randomArray = randomSet.toArray(new Integer[randomSet.size()]);
		for(int i = 0; i < size; i++){
			dict.insert(randomArray[i], randomArray[i] + " " + randomArray[i]);
		}
		Random r = new Random();
		long start = System.currentTimeMillis();
		for(int i = 0; i < 500; i++){
			dict.find(randomArray[r.nextInt(randomArray.length)]);
		}
		long end = System.currentTimeMillis();
		return end-start;
	}
	
	public double deleteWrapper(int size, Dictionary dict){
		long[] ullDelete = new long[6];
		long sumDelete = 0;
		for(int i = 0; i < 6; i++){
			ullDelete[i] = testDelete(size, dict);
			if(i != 0){
				sumDelete += ullDelete[i];
			}
		}
		double averageTime = (double)(sumDelete)/10;
		// divides by 10 for the 10 test cases.
		return averageTime;
	}
	
	public long testDelete(int size, Dictionary dict){
		Set<Integer> randomSet = generateRandomSet(size);
		Integer[] randomArray = randomSet.toArray(new Integer[randomSet.size()]);
		for(int i = 0; i < size; i++){
			dict.insert(randomArray[i], randomArray[i] + " " + randomArray[i]);
		}
		Collections.shuffle(Arrays.asList(randomArray));
		long start = System.currentTimeMillis();
		for(int i = 0; i < size; i++){
			dict.delete(randomArray[i]);
		}
		long end = System.currentTimeMillis();
		return end-start;
	}
	
}
