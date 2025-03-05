import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static final int MAX_N = 500;
	static final int INF = MAX_N * MAX_N;
	
	static int N, M;
	static int[][] adj = new int[MAX_N][MAX_N]; // adj[i][j]: 키의 오름차순으로 생성한 그래프
	static int[][] iAdj = new int[MAX_N][MAX_N]; // iAdj[i][j]: 키의 내림차순으로 생성한 그래프
	
	
	static void floyd(int N, int[][] adj) {
		for(int k = 0; k < N; ++k) {
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < N; ++j) {
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
				}
			}
		}
	}
	
	static int solution() {
		floyd(N, adj);
		floyd(N, iAdj);
		
		int cnt = 0;
		for(int i = 0; i < N; ++i) { 
			// 나머지 N-1명의 학생이 자신보다 앞인지 뒤인지 알아야 순위를 알 수 있음
			int sum = -2; // 자기자신과는 항상 연결되어 있으므로 제외
			for(int j = 0; j < N; ++j) {
				if(adj[i][j] != INF) ++sum;
				if(iAdj[i][j] != INF) ++sum;
			}
			if(sum == N - 1) ++cnt;
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; ++i) { // 인접 행렬 초기화
			Arrays.fill(adj[i], INF);
			Arrays.fill(iAdj[i], INF);
			adj[i][i] = iAdj[i][i] = 0;
		}
		
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			adj[a][b] = iAdj[b][a]= 1;
		}
		System.out.print(solution());
	}
}
