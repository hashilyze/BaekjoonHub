import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// constants
	static final int MAX_N = 200;
	static final int INF = 1_000 * MAX_N + 1;
	// variables
	static int N, M;
	static int[][] adj = new int[MAX_N][MAX_N];
	static int[][] next = new int[MAX_N][MAX_N];
	
	
	static void solution() {
		for(int k = 0; k < N; ++k) {
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < N; ++j) {
					if(adj[i][k] + adj[k][j] < adj[i][j]) {
						adj[i][j] = adj[i][k] + adj[k][j];
						next[i][j] = next[i][k];
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		// Input
		N = readInt();	
		M = readInt();
		for(int i = 0; i < N; ++i) { // 인접 행렬 초기화
			Arrays.fill(adj[i], INF);
			adj[i][i] = 0;
		}
		for(int i = 0; i < M; ++i) {
			int u = readInt() - 1;
			int v = readInt() - 1;
			int w = readInt();
			
			adj[u][v] = adj[v][u] = w;
			next[u][v] = v;
			next[v][u] = u;
		}
		
		solution();
		
		// Output
		for(int i = 0; i < N; ++i) {
			for(int j = 0; j < N; ++j) {
				if(i == j) {
					sb.append("- ");
				} else {
					sb.append(next[i][j] + 1).append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
	
	// 부호없는 0 또는 양의 정수 읽기
	static int readInt() throws IOException{
		int c, n;
		while((c = System.in.read()) < 0x30);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) {
			n = (n << 3) + (n << 1) + (c & 0x0F);
		}
		return n;
	}
}
