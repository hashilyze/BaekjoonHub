import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int MAX_N = 16;
	static int START = 0;
	static double INF = 2_000 * 16;
	
	static int N;
	static int[][] locations = new int[MAX_N][2];
	static double[][] adj = new double[MAX_N][MAX_N];
	static double[][] dp = new double[MAX_N][0x01 << MAX_N];
	
	
	static double euclideanDistance(int[] u, int[] v) {
		return Math.sqrt(Math.pow(u[0] - v[0], 2) + Math.pow(u[1] - v[1], 2));
	}
	
	static double minPath(int at, int isVisited) {
		double cached = dp[at][isVisited];
		if(dp[at][isVisited] > 0) return cached;
		
		if(isVisited == (0x01 << N) - 1) { // 기저
			return dp[at][isVisited] = adj[at][START];
		}
		
		double min = INF;
		for(int i = 0; i < N; ++i) {
			if(((isVisited >> i) & 1) == 1) continue;
			min = Math.min(min, minPath(i, isVisited | (0x01 << i)) + adj[at][i]);
		}
		return dp[at][isVisited] = min;
	}
	
	static double solution() {
		return minPath(START, 0x01 << START);
	}
	
	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			locations[i][0] = Integer.parseInt(st.nextToken());
			locations[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int u = 0; u < N; ++u) {
			for(int v = u; v < N; ++v) {
				adj[u][v] = adj[v][u] = euclideanDistance(locations[u], locations[v]);
			}
		}
		
		System.out.println(solution());;
	}

}
