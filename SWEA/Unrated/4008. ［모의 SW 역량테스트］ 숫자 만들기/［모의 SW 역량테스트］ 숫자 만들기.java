import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int NUM_OP = 4;
	
	static int N;
	static int[] ops = new int[NUM_OP];
	static int[] digits;
	
	static int min, max;
	
	
	static int calc(int lhs, int rhs, int opCode) {
		switch(opCode) {
		case 0: return lhs + rhs;
		case 1: return lhs - rhs;
		case 2: return lhs * rhs;
		case 3: return lhs / rhs;
		}
		return -1;
	}
	
	static void nextPermutation(int idx, int subCalc) {
		if(idx == N) {
			min = Math.min(min, subCalc);
			max = Math.max(max, subCalc);
			return;
		}
		
		for(int i = 0; i < NUM_OP; ++i) {
			if(ops[i] > 0) {
				--ops[i];
				nextPermutation(idx + 1, calc(subCalc, digits[idx], i));
				++ops[i];
			}
		}
	}
	
	static int solution() {
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		nextPermutation(1, digits[0]);
		return max - min;
	}
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; ++t) {
			N = Integer.parseInt(br.readLine());
			digits = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < NUM_OP; ++i) ops[i] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; ++i) digits[i] = Integer.parseInt(st.nextToken());
			
			sb.append("#").append(t).append(" ").append(solution()).append("\n");
		}
		System.out.print(sb);
	}
}
