import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N;
	static int[][] mat = new int[20][20];
	static int[] groupA = new int[10], groupB = new int[10];
	
	static boolean isBipartied(int bitMask) { return Integer.bitCount(bitMask) * 2 == N; }
	
	static int getDiff(int bitMask) {
		int curA = 0, curB = 0;
		for(int i = 0; i < N; ++i) {
			if(((bitMask >> i) & 1) == 1){
				groupA[curA++] = i;
			} else {
				groupB[curB++] = i;
			}
		}
		
		int diff = 0;
		int half = N >> 1;
		for(int i = 0; i < half; ++i) {
			for(int j = i + 1; j < half; ++j) {
				diff += mat[groupA[i]][groupA[j]] + mat[groupA[j]][groupA[i]];
				diff -= mat[groupB[i]][groupB[j]] + mat[groupB[j]][groupB[i]];
			}
		}
		return Math.abs(diff);
	}
	
	static int solution() {
		int min = Integer.MAX_VALUE;
		for(int bitMask = 0x00, limit = 0x01 << N - 1; bitMask < limit; ++bitMask) {
			if(isBipartied(bitMask)) {
				min = Math.min(min, getDiff(bitMask));
			}
		}
		return min;
	}
	
	public static void main(String[] args) throws IOException{		
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; ++j) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.print(solution());
	}
}
