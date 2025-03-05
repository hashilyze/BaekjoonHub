import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// constants
	static final int MAX_N = 100;
	static final int INF = 15 * MAX_N + 1;
	// variables
	static int N, M, R;
	static int[] numItems = new int[MAX_N];
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
		
		int max = Integer.MIN_VALUE; 
		for(int u = 0; u < N; ++u) {
			int sum = 0; // 얻을 수 있는 아이템의 수
			for(int v = 0; v < N; ++v) {
				if(adj[u][v] <= M) { // 최단 거리가 M이하인 지역만 수색 가능
					sum += numItems[v];
				}
			}
			max = Math.max(max, sum);
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();
		R = readInt();
		for(int i = 0; i < N; ++i) numItems[i] = readInt();
		
		for(int i = 0; i < N; ++i) { // 인접 행렬 초기화
			Arrays.fill(adj[i], 0, N, INF);
			adj[i][i] = 0;
		}
		for(int i = 0; i < R; ++i) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int c = readInt();
			adj[a][b] = adj[b][a] = c;
		}
		System.out.println(solution());
	}
	
	// 부호없는 정수 읽기
	static int readInt() throws IOException { 
		int c, n;
		while((c = System.in.read()) < 0x30);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) {
			n = (n << 3) + (n << 1) + (c & 0x0F);
		}
		return n;
	}
}