import java.io.*;
import java.util.*;


public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	
	static final int MAX_N = 16;
	static final int INF = 1_000_000 * MAX_N + 1;
	
	static int N;
	static int[][] adj = new int[MAX_N][MAX_N];
	static int[][][] dp = new int[MAX_N][MAX_N][0x01 << MAX_N];

	
	static int minWeight(int from, int to, int passed) {
		if(dp[from][to][passed] > 0) 
			return dp[from][to][passed];
		
		int min = INF;
		if(passed == 0) {
			min = from == to ? 0 : adj[from][to];
		} else if(from != to) {
			for(int u = 0; u < N; ++u) {
				if((passed & (0x01 << u)) == 0) continue;
				
				for(int v = 0; v < N; ++v) {
					if((passed & (0x01 << v)) == 0) continue;
					
					min = Math.min(min, minWeight(u, v, passed ^ (0x01 << u | 0x01 << v)) + adj[from][u] + adj[v][to]);
				}
			}
		}
		return dp[from][to][passed] = min;
	}
	
	
	static int solution() {
		for(int u = 0; u < N; ++u) {
			for(int v = 0; v < N; ++v) {
				Arrays.fill(dp[u][v], -1);
			}
		}
		
		int min = INF;
		int u = 0;
		for(int v = 1; v < N; ++v) {
			int passed = (0x01 << N) - 1;
			passed ^= (0x01 << u | 0x01 << v);
			min = Math.min(min, minWeight(u, v, passed) + adj[v][u]);
		}
		return min;
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		for(int u = 0; u < N; ++u) {
			st = new StringTokenizer(br.readLine());
			for(int v = 0; v < N; ++v) {
				int w = Integer.parseInt(st.nextToken());
				if((adj[u][v] = w) == 0) adj[u][v] = INF; 
			}
		}
		bw.append("" + solution()).flush();
	}
}