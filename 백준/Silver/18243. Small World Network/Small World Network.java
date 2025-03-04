import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int SIZE = 100;
	static final int INF = SIZE * SIZE;
	
	static int N, K;
	static int[][] adj = new int[SIZE][SIZE];
	
	
	static void floyd() {
		for(int k = 0; k < N; ++k) {
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < N; ++j) {
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
				}
			}
		}
	}
	
	static boolean solution() {
		floyd();
		for(int i = 0; i < N; ++i) {
			for(int j = 0; j < N; ++j) {
				if(adj[i][j] > 6) return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; ++i) { // 인접 행렬 초기화
			Arrays.fill(adj[i], 0, N, INF);
			adj[i][i] = 0;
		}
		
		for(int i = 0; i < K; ++i) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			adj[u][v] = adj[v][u] = 1;
		}
		System.out.print(solution() ? "Small World!" : "Big World!");
	}
}