import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int SIZE = 26;
	static final int INF = SIZE * SIZE;
	
	static int N, M;
	static int[][] adj = new int[SIZE][SIZE];
	
	
	static void floyd() {
		for(int k = 0; k < SIZE; ++k) {
			for(int i = 0; i < SIZE; ++i) {
				for(int j = 0; j < SIZE; ++j) {
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		for(int i = 0; i < SIZE; ++i) { // 인접 행렬 초기화
			Arrays.fill(adj[i], INF);
			adj[i][i] = 0;
		}
		
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			int u = st.nextToken().charAt(0) - 'a';
			st.nextToken();
			int v = st.nextToken().charAt(0) - 'a';
			
			adj[u][v] = 1;
		}
		
		floyd();
		
		M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int u = st.nextToken().charAt(0) - 'a';
			st.nextToken();
			int v = st.nextToken().charAt(0) - 'a';
			
			sb.append(adj[u][v] != INF ? 'T' : 'F').append("\n");
		}
		System.out.print(sb);
	}
}