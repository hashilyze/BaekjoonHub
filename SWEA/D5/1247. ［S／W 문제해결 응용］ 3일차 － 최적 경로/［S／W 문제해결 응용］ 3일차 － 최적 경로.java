import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int INF = Integer.MAX_VALUE >> 2;
	static final int MAX_N = 10 + 2;
	static final int SRC = 0, DEST = 1;
	
	static int N;
	static int[][] locations = new int[MAX_N][2];
	static int[][] weights = new int[MAX_N][MAX_N];
	static int[][] dp = new int[MAX_N][0x01 << MAX_N]; // dp[from][to][passed]; 
	
	
	static int minPath(int from, int isVisited) {
		if(isVisited == (0x01 << N) - 1) {
			return weights[from][DEST];
		}
		if(dp[from][isVisited] >= 0) return dp[from][isVisited]; 
		
		int min = Integer.MAX_VALUE;
		for(int to = 0; to < N; ++to) {
			if(((isVisited >> to) & 1) == 1) continue;
			min = Math.min(min, weights[from][to] + minPath(to, isVisited | (0x01 << to)));
		}
		return dp[from][isVisited] = min;
	}
	
	static void initDp() {
		for(int from = 0; from < N; ++from) {
			Arrays.fill(dp[from], -1);
		}
	}
	static void initWeight() {
		for(int i = 0; i < N; ++i) {
			for(int j = i; j < N; ++j) {
				weights[i][j] = weights[j][i] = Math.abs(locations[i][0] - locations[j][0]) + Math.abs(locations[i][1] - locations[j][1]);
			} 
			weights[i][i] = INF;
		}
	}
	
	static int solution() {
		initDp();
		initWeight();
		return minPath(SRC, 0x03);
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