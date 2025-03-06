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
			return -(lhs + rhs).compareTo(rhs + lhs);
		});
		
		if(numbers[0].equals("0") && numbers[N - 1].equals("0")) {
			System.out.print(0);
		} else {
			for(String number : numbers) sb.append(number);
			System.out.print(sb);
		}
	}
}