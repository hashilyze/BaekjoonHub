import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int MAX_N = 100;
	static final int INF = MAX_N * MAX_N;
	
	static int N, M;
	static int[][] adj = new int[MAX_N][MAX_N];

	
	static void floyd() {
		for(int k = 0; k < N; ++k) {
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < N; ++j) {
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
				}
			}
		}
	}
	
	static int solution() {
		floyd();
		
		int min = INF, who = -1;
		for(int i = 0; i < N; ++i) {
			int sum = 0;
			for(int j = 0; j < N; ++j) {
				sum += adj[i][j];
			}
			if(sum < min) {
				min = sum;
				who = i + 1;
			}
		}
		return who;
	}
	
	public static void main(String[] args) throws IOException {
		// Input
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for(int i = 0 ; i < N; ++i) { // 인접 행렬 초기화
			Arrays.fill(adj[i], 0, N, INF);
			adj[i][i] = 0;
		}
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;			
			adj[u][v] = adj[v][u] = 1;
		}
		
		System.out.print(solution());
	}
}