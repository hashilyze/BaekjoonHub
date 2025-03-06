import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// constants
	static final int MAX_N = 100;
	static final int MAX_M = 100_000;
	static final int INF = 100_000 * MAX_N + 1;
	// variables
	static int N, M;
	static int[][] adj = new int[MAX_N][MAX_N];
	static int[][] next = new int[MAX_N][MAX_N]; // next[i][j]로 가는 최단 경로에서 i가 통과하는 다음 정점
	
	
	static List<Integer> getPath(int u, int v) {
		List<Integer> li = new ArrayList<>();
		while(u != v) {
			li.add(u);
			u = next[u][v];
		}
		li.add(u);
		return li;
	}
	
	static void solution() {
		for(int k = 0; k < N; ++k) { // floyd
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < N; ++j) {
//					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
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
		for(int i = 0; i < N; ++i) { // 인접행렬 초기화
			Arrays.fill(adj[i], 0, N, INF);
			adj[i][i] = 0;
		}
		for(int i = 0; i < M; ++i) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int c = readInt();
			adj[a][b] = Math.min(adj[a][b], c);
			next[a][b] = b;
		}
		
		solution();
		
		// Output
		// 최단 거리 행렬 출력
		for(int i = 0; i < N; ++i) {
			for(int j = 0; j < N; ++j) {
				sb.append(adj[i][j] != INF ? adj[i][j] : 0).append(" ");
			}
			sb.append("\n");
		}
		// 경로 출력
		for(int i = 0; i < N; ++i) {
			for(int j = 0; j < N; ++j) {
				if(i != j && adj[i][j] != INF) {
					List<Integer> li = getPath(i, j);
					sb.append(li.size());
					for(int e : li) {
						sb.append(" ").append(e + 1);
					}
					
				} else {
					sb.append(0);
				}
				sb.append("\n");
			}
		}
		System.out.print(sb);
	}
	
	// 부호없는 정수 입력
	static int readInt() throws IOException{
		int c, n = 0;
		while((c = System.in.read()) < 0x30);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) {
			n = (n << 3) + (n << 1) + (c & 0x0F);
		}
		return n;
	}
	
}
