import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// constants
	static int MAX_N = 50;
	static int INF = 50 + 1;
	// variables
	static int N;
	static int[][] adj = new int[MAX_N][MAX_N];
	static int[] scores = new int[MAX_N];
	
	static void floyd() {
		for(int k = 0; k < N; ++k) {
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < N; ++j) {
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
				}
			}
		}
	}
	
	static List<Integer> solution() {
		// 거리 계산
		floyd();
		// 점수 계산
		for(int i = 0; i < N; ++i) {
			for(int j = 0; j < N; ++j) {
				scores[i] =  Math.max(scores[i], adj[i][j]);
			}
		}
		// 최소 점수와 최소 점수를 가진 회원 수 계산
		List<Integer> li = new ArrayList<Integer>();
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < N; ++i) {
			if(scores[i] < min) {
				min = scores[i];
				li.clear();
				li.add(i);
			} else if(scores[i] == min) {
				li.add(i);
			}
		}
		return li;
	}
	
	public static void main(String[] args) throws IOException {
		/* 1. 임의의 두 회원 사이의 최단 거리를 계산 -> 플로이드
		 * 2. 각 회원의 점수는 자신과 가장 먼 회원까지의 거리
		 * 3. 점수가 가장 낮은 회원들을 찾기
		 */
		N = readInt();
		for(int i = 0; i < N; ++i) { // 인접 행렬 초기화
			Arrays.fill(adj[i], INF);
			adj[i][i] = 0;
		}
		while(true) {
			int u = readInt() - 1;
			int v = readInt() - 1;
			if(u < 0) break;
			
			adj[u][v] = adj[v][u] = 1;
		}
		List<Integer> ans = solution();
		sb.append(scores[ans.get(0)]).append(" ").append(ans.size()).append("\n");
		for(int e : ans) {
			sb.append(e + 1).append(" ");
		}
		System.out.print(sb);
	}
	
	// 부호없는 양의 정수와 음수 읽기
	static int readInt() throws IOException { 
		int c, n, s = 1;
		while((c = System.in.read()) < 0x20);
		if(c == '-') { 
			s = -1;
			c = System.in.read();
		}
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) {
			n = (n << 3) + (n << 1) + (c & 0x0F);
		}
		return n * s;
	}
}