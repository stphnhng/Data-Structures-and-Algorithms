
package interview;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class interview {
	
	public static void main(String[]args){
		interview i = new interview();
		//System.out.println(i.run());
		i.printFile();
	}
	
	public boolean run(){
		System.out.print("Input String: " );
		Scanner reader = new Scanner(System.in);
		String input = reader.nextLine();
		String result = "";
		for(int i = input.length()-1; i >= 0; i--){
			result = result + input.charAt(i);
		}
		System.out.println(result);
		if(input.length() > 0){
			for(int i = 0; i < input.length() /2; i++){
				if(!(input.charAt(i) == (input.charAt(input.length()-i-1)))){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public void printFile(){
		String dir = "/usr/share/dict/words";
		File fInput = new File(dir);
		FileInputStream fStream = null;
		try {
			fStream = new FileInputStream(fInput);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner fScanner = new Scanner(fStream);
		Map<Character, Integer> charFrequency = new HashMap<Character, Integer>();
		while(fScanner.hasNextLine()){
			String word = fScanner.nextLine();
			for(int i = 0; i < word.length(); i++){
				char letter = word.charAt(i);
				if(!charFrequency.containsKey(letter)){
					charFrequency.put(letter, 1);
				}else{
					charFrequency.put(letter, charFrequency.get(letter) + 1);
				}
			}
		}
		char highFreqLetter = ' ';
		int max = 0;
		for(char key : charFrequency.keySet()){
			if(charFrequency.get(key) > max){
				max = charFrequency.get(key);
				highFreqLetter = key;
			}
		}
		System.out.println("Highest freq letter: " + highFreqLetter + " at " + max);
		
		// Printing out the sets with the highest number of anagrams
		try {
			fStream = new FileInputStream(fInput);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fScanner = new Scanner(fStream);
		Map<String, ArrayList<String>> anaFreq = new HashMap<String, ArrayList<String>>();
		
		
		
		while(fScanner.hasNextLine()){
			String input = fScanner.nextLine();
			char[] charArray = input.toCharArray();
			Arrays.sort(charArray);
			String key = new String(charArray);
			if(anaFreq.containsKey(key)){
				ArrayList<String> anaWords = anaFreq.get(key);
				anaWords.add(input);
				anaFreq.put(key, anaWords);
			}else{
				ArrayList<String> result = new ArrayList<String>();
				result.add(key);
				anaFreq.put(key, result);
			}
			
		}
		
		max = 0;
		ArrayList<String> resultList = new ArrayList<String>();
		for(String key : anaFreq.keySet()){
			if(max < anaFreq.get(key).size()){
				resultList = anaFreq.get(key);
				max = anaFreq.get(key).size();
			}
		}
		System.out.println(max);
		System.out.println(resultList);
		
		
	}
	
	private boolean isAnagram(String a, String b){
		char[] aLetters = a.toCharArray();
		char[] bLetters = b.toCharArray();
		
		Arrays.sort(aLetters);
		Arrays.sort(bLetters);
		if(aLetters.length != bLetters.length){
			return false;
		}
		for(int i = 0; i < aLetters.length; i++){
			if(aLetters[i] != bLetters[i]){
				return false;
			}
		}
		return true;
	}
	
	
}
