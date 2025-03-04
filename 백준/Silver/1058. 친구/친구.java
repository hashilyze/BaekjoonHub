import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int MAX_N = 50;
	static final int INF = MAX_N * MAX_N;
	
	static int N;
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
		
		int max = Integer.MIN_VALUE; // 가장 유명한 사람의 친구 수
		for(int i = 0; i < N; ++i) {
			adj[i][i] = INF;
			int cnt = 0;
			for(int j = 0; j < N; ++j) {
				if(adj[i][j] <= 2) ++cnt;
			}
			max = Math.max(max, cnt);
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; ++i) {
			String line = br.readLine();
			for(int j = 0; j < N; ++j) {
				adj[i][j] = line.charAt(j) == 'Y' ? 1 : INF;
			}
		}
		System.out.print(solution());
	}
}