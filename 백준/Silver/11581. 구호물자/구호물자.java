import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int SIZE = 100;
	static final int INF = SIZE * SIZE;
	
	static int N, M;
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
			// 1번 교차로에서 갈 수 있는 교차로 중에 사이클이 존재하는 교차로를 찾음
			if(adj[0][i] != INF && adj[i][i] != INF) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; ++i) { // 인접 행렬 초기화
			Arrays.fill(adj[i], 0, N, INF);
		}
		
		for(int u = 0; u < N - 1; ++u) {
			int num = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < num; ++i) {
				int v = Integer.parseInt(st.nextToken()) - 1;
				adj[u][v] = 1;
			}
		}
		System.out.print(solution() ? "NO CYCLE" : "CYCLE");
	}
}