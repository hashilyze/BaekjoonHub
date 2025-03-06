import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// constants
	// variables
	static int N;
	static String[] numbers;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		numbers = br.readLine().split(" ");
		
		Arrays.sort(numbers, 0, N, (lhs, rhs)->{
			int lenL = lhs.length();
			int lenR = rhs.length();
			
			int cur = 0;
			while(cur < lenL + lenR) {
				char lch = 0;
				if(cur < lenL) lch = lhs.charAt(cur);
				else lch = rhs.charAt(cur - lenL);
				
				char rch = 0;
				if(cur < lenR) rch = rhs.charAt(cur);
				else rch = lhs.charAt(cur - lenR);
				
				if(lch != rch) return rch - lch;
				++cur;
			}
			return 0;
		});
		
		if(numbers[0].equals("0") && numbers[N - 1].equals("0")) {
			System.out.print(0);
		} else {
			for(String number : numbers) sb.append(number);
			System.out.print(sb);
		}
	}
}