import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int MAX_N = 10 + 2;
	static final int SRC = 0, DEST = 1;
	
	static int N;
	static int[][] locations = new int[MAX_N][2];
	static int[][] weights = new int[MAX_N][MAX_N];
	static int min;
	
	
	static void minPath(int at, int sum, int isVisited) {
		if(sum >= min) return;
		
		if(isVisited == (0x01 << N) - 1) {
			min = Math.min(min, sum + weights[at][DEST]);
			return;
		}
		
		for(int i = 2; i < N; ++i) {
			if(((isVisited >> i) & 1) == 1) continue;
			
			int w = sum + weights[at][i];
			if(w < min) minPath(i, w, isVisited | (0x01 << i));
		}
	}
	
	static int solution() {
		min = Integer.MAX_VALUE;
		for(int i = 0; i < N; ++i) {
			for(int j = i; j < N; ++j) {
				weights[i][j] = weights[j][i] = Math.abs(locations[i][0] - locations[j][0]) + Math.abs(locations[i][1] - locations[j][1]);
			} 
		}
		minPath(SRC, 0, 0x03);
		return min;
	}
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; ++t) {
			N = Integer.parseInt(br.readLine()) + 2;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; ++i) {
				locations[i][0] = Integer.parseInt(st.nextToken());
				locations[i][1] = Integer.parseInt(st.nextToken());
			}
			
			sb.append("#").append(t).append(" ").append(solution()).append("\n");
		}
		System.out.print(sb);
	}
}