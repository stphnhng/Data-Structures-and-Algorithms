import java.util.Arrays;


/**
 * @author stephen
 *	Implementation of a Radix sort for Strings 
 */
public class LSD {
	
	public static void main(String[]args){
		String[] input = {"abc","da","ffff", "defcd", "abebd", "ca", "b",
				"fef","dfe"};
		sort(input, 1);
		System.out.println(Arrays.toString(input));
	}
	
	
	public static void sort(String[] a, int W){
	        int R = 256; // extended ASCII Alphabets
	        int N = a.length;
	        String[] aux = new String[N];
	        for (int d = W-1; d >= 0; d--)
	        {
	            int[] count = new int[R+1];
	            for (int i = 0; i < N; i++)
	                count[a[i].charAt(d) + 1]++;
	            for (int r = 0; r < R; r++)
	                count[r+1] += count[r];
	            for (int i = 0; i < N; i++)
	                aux[count[a[i].charAt(d)]++] = a[i];
	            for (int i = 0; i < N; i++)
	                a[i] = aux[i];
	        }
	 }
}
