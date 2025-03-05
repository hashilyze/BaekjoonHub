import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// constants
	static int MAX_N = 256;
	static int INF = MAX_N + 1;
	// variables
	static int N, M;
	static int[][] adj = new int[MAX_N][MAX_N];
	static int[][] iAdj = new int[MAX_N][MAX_N];
	
	static void floyd(int[][] adj) {
		for(int k = 0; k < N; ++k) {
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < N; ++j) {
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		/* 
		 * 1. 자신보다 우선순위가 높은/낮은 학생의 수를 구한다. 
		 * 2. (1 + (우선순위가 높은 학생의 수)) ~ (N - (우선순위가 낮은 학생의 수)) 가 범위가 된다.
		 */
		// Input
		N = readInt();
		M = readInt();
		for(int i = 0; i < N; ++i) { // 인접 행렬 초기화
			Arrays.fill(adj[i], INF);
			Arrays.fill(iAdj[i], INF);
		}
		for(int i = 0; i < M; ++i) {
			int u = readInt() - 1;
			int v = readInt() - 1;
			adj[u][v] = 1;
			iAdj[v][u] = 1;
		}
		// Solution
		floyd(adj); // 키 오름차순 방향으로 그래프 연결
		floyd(iAdj); // 키 내림차순 방향으로 그래프 연결
		boolean disable = false;
		for(int i = 0; i < N; ++i) {
			if(adj[i][i] != INF || iAdj[i][i] != INF) {
				disable = true;
				break;
			}
		}
		
		if(disable) {
			System.out.println(-1);
		} else {
			for(int i = 0; i < N; ++i) {
				int lower = 1, upper = N;
				for(int j = 0; j < N; ++j) {
					if(adj[i][j] != INF) --upper;
					if(iAdj[i][j] != INF) ++lower;
				}
				sb.append(lower).append(" ").append(upper).append("\n");
			}
			System.out.println(sb);
		}
	}
	
	// 부호없는 양의 정수 읽기
	static int readInt() throws IOException { 
		int c, n;
		while((c = System.in.read()) < 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) {
			n = (n << 3) + (n << 1) + (c & 0x0F);
		}
		return n;
	}
}