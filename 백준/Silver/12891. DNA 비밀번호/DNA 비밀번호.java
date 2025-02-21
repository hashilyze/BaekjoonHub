import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static final int SIZE = 4;
	
	static int S, P;
	static String str;
	static int[] lower_bounds = new int[SIZE];
	static int[] freqs = new int[SIZE];
	
	
	static int toIndex(char ch) {
		switch(ch) {
		case 'A': return 0;
		case 'C': return 1;
		case 'G': return 2;
		case 'T': return 3;
		}
		return -1;
	}
	
	static boolean isValid(int[] freqs) {
		for(int i = 0; i < SIZE; ++i) {
			if(freqs[i] < lower_bounds[i]) return false;
		}
		return true;
	}
	
	static int solution() {
		int cnt = 0;
		for(int i = 0; i < P; ++i) ++freqs[toIndex(str.charAt(i))];
		if(isValid(freqs)) ++cnt;
		
		for(int i = 1; i < S - P + 1; ++i) {
			--freqs[toIndex(str.charAt(i - 1))];
			++freqs[toIndex(str.charAt(i + P - 1))];
			if(isValid(freqs)) ++cnt;
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		str = br.readLine();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < SIZE; ++i) lower_bounds[i] = Integer.parseInt(st.nextToken());
		
		System.out.print(solution());
	}
}