import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N, B;
	static int[][] mat = new int[16][16];
	static int[] slotA = new int[8], slotB = new int[8];
	
	static int min;
	
	static int diff(int bitMask) {
		int curA = 0, curB = 0;
		for(int i = 0; i < N; ++i) {
			if(((bitMask >> i) & 1) == 1){
				slotA[curA++] = i;
			} else {
				slotB[curB++] = i;
			}
		}
		
		int halfN = N >> 1;
		int a = 0, b = 0;
		for(int i = 0; i < halfN; ++i) {
			for(int j = i + 1; j < halfN; ++j) {
				a += mat[slotA[i]][slotA[j]] + mat[slotA[j]][slotA[i]];
				b += mat[slotB[i]][slotB[j]] + mat[slotB[j]][slotB[i]];
			}
		}
		return Math.abs(a - b);
	}
	
	static void nextCombination(int idx, int left, int bitMask) {
		if(left == 0) {
			min = Math.min(min, diff(bitMask));
			return;
		}
		for(int i = idx; i < N - left + 1; ++i) {
			nextCombination(i + 1, left - 1, bitMask | (0x01 << i));
		}
	}
	
	static int solution() {
		min = Integer.MAX_VALUE;
		nextCombination(1, (N >> 1) - 1, 0x01);
		return min;
	}
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; ++t) {
			N = Integer.parseInt(br.readLine());
			
			for(int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; ++j) {
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			sb.append("#").append(t).append(" ").append(solution()).append("\n");			
		}
		System.out.print(sb);
	}
}
