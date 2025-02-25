import java.io.*;
import java.util.*;


public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int INF = Integer.MAX_VALUE >> 2;
	static final int MAX_N = 16;
	static final int SRC = 0;
	
	static int N;
	static int[][] weights = new int[MAX_N][MAX_N];
	static int[][] dp = new int[MAX_N][0x01 << MAX_N]; 
	
	
	static int minPath(int from, int isVisited) {
		if(isVisited == (0x01 << N) - 1) {
			return weights[from][SRC];
		}
		if(dp[from][isVisited] >= 0) return dp[from][isVisited]; 
		
		int min = INF;
		for(int to = 0; to < N; ++to) {
			if(((isVisited >> to) & 1) == 1 || weights[from][to] == INF) continue;
			min = Math.min(min, weights[from][to] + minPath(to, isVisited | (0x01 << to)));
		}
		return dp[from][isVisited] = min;
	}
	
	static void initDp() {
		for(int from = 0; from < N; ++from) {
			Arrays.fill(dp[from], -1);
		}
	}
	
	static int solution() {
		initDp();
		return minPath(SRC, 0x01);
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		for(int u = 0; u < N; ++u) {
			st = new StringTokenizer(br.readLine());
			for(int v = 0; v < N; ++v) {
				weights[u][v] = Integer.parseInt(st.nextToken());
				if(weights[u][v] == 0) weights[u][v] = INF;
			}
		}
		System.out.print(solution());
	}
}
