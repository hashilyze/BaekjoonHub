import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int MAX_N = 11;
	static final int NUM_OP = 4;
	
	static int N;
	static int[] numbers = new int[MAX_N];
	static int[] operators = new int[NUM_OP];
	
	static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
	
	
	static int calc(int lhs, int rhs, int opCode) {
		switch (opCode) {
		case 0: return lhs + rhs;
		case 1: return lhs - rhs;
		case 2: return lhs * rhs;
		case 3: return lhs / rhs;
		default: throw new IllegalArgumentException("Unexpected value");
		}
	}
	
	static void generatePermutation(int idx, int subCalc) {
		if(idx == N) {
			min = Math.min(min, subCalc);
			max = Math.max(max, subCalc);
			return;
		}
		for(int i = 0; i < NUM_OP; ++i) {
			if(operators[i] == 0) continue;
			--operators[i];
			generatePermutation(idx + 1, calc(subCalc, numbers[idx], i));
			++operators[i];
		}
	}
	
	static void solution() {
		generatePermutation(1, numbers[0]);
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < NUM_OP; ++i) {
			operators[i] = Integer.parseInt(st.nextToken());
		}
		
		solution();
		System.out.println(max);
		System.out.println(min);
	}
}