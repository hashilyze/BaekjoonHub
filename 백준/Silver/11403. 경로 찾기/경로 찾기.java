import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int MAX_N = 100;
	static final int INF = MAX_N;
	
	static int N, M;
	static int[][] adj = new int[MAX_N][MAX_N];

	
	static void solution() {
		for(int k = 0; k < N; ++k) {
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < N; ++j) {
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		// Input
		N = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < N; ++i) { // 인접 행렬 초기화
			Arrays.fill(adj[i], 0, N, INF);
		}
		for(int u = 0; u < N; ++u) { 
			st = new StringTokenizer(br.readLine());
			for(int v = 0; v < N; ++v) {
				adj[u][v] = Integer.parseInt(st.nextToken()) != 0 ? 1 : INF;
			}
		}
		
		solution();
		
		// Output
		for(int i = 0; i < N; ++i) {
			for(int j = 0; j < N; ++j) {
				sb.append(adj[i][j] != INF ? "1 " : "0 ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}