import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	// variables
	
	static boolean allTheSame(String s) {
		char first = s.charAt(0);
		for(char ch : s.toCharArray()) {
			if(first != ch) return false;
		}
		return true;
	}
	
	static  boolean isPalindrome(String s) {
		return s.equals(new StringBuilder().append(s).reverse().toString());
	}
	
	public static void main(String[] args) throws IOException {
		String S = br.readLine();
		if(allTheSame(S)) System.out.print(-1);
		else if(isPalindrome(S)) System.out.print(S.length() - 1);
		else System.out.print(S.length());
	}
}