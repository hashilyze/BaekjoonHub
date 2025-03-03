import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	
	static final int MAX_N = 20 + 1; // 포켓몬의 최대 개수
	static final int MAX_M = 15 + 1; // 고유한 포켓몬의 최대 개수
	static final int START = 0; // 원점의 인덱스
	static final int INF = 200 * MAX_M;
	
	static int N, M;
	static int[][] locations = new int[MAX_N][2]; // 위치
	static int[] indecies = new int[MAX_N];
	
	static int[][] adj = new int[MAX_N][MAX_N]; // 간선
	static int[][] dp = new int[MAX_N][0x01 << MAX_M];
	
	
	
	static int manhattanDistance(int[] u, int[] v) {
		return Math.abs(u[0] - v[0]) + Math.abs(u[1] - v[1]); 
	}
	
	static int minPath(int at, int isVisited) {
		int cached = dp[at][isVisited];
		if(dp[at][isVisited] > 0) return cached;
		
		if(isVisited == (0x01 << M + 1) - 1) { // 기저
			return dp[at][isVisited] = adj[at][START];
		}
		
		int min = INF;
		for(int i = 1; i <= N; ++i) {
			if(((isVisited >> indecies[i]) & 1) == 1) continue;
			min = Math.min(min, minPath(i, isVisited | (0x01 << indecies[i])) + adj[at][i]);
		}
		return dp[at][isVisited] = min;
	}
	
	static int solution() {
		return minPath(START, 0x01 << START);
	}
	
	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(br.readLine());
		
		Map<String, Integer> name2Index = new HashMap<>();
		for(int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine());
			locations[i][0] = Integer.parseInt(st.nextToken());
			locations[i][1] = Integer.parseInt(st.nextToken());
			
			String name = st.nextToken();
			if(!name2Index.containsKey(name)) {
				name2Index.put(name, name2Index.size() + 1);
			}
			indecies[i] = name2Index.get(name);
		}
		M = name2Index.size();
		
		// 간선 가중치 생성
		for(int u = 0; u <= N; ++u) {
			for(int v = u + 1; v <= N; ++v) {
				adj[u][v] = adj[v][u] = manhattanDistance(locations[u], locations[v]);
			}
		}
		
		System.out.println(solution());
	}

}
