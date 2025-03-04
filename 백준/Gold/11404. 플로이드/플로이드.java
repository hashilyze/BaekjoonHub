import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int MAX_N = 100;
	static final int INF = 100_000 * MAX_N;
	
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
		M = Integer.parseInt(br.readLine());
		
		for(int i = 0 ; i < N; ++i) {
			Arrays.fill(adj[i], 0, N, INF);
			adj[i][i] = 0;
		}
		
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			
			adj[a][b] = Math.min(adj[a][b], c);
		}
		
		solution();
		// Output
		for(int i = 0; i < N; ++i) {
			for(int j = 0; j < N; ++j) {
				sb.append(adj[i][j] != INF ? adj[i][j] : 0).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}