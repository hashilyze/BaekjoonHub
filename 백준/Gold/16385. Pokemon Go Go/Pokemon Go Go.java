import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int MAX_N = 20 + 1;
	static final int START = 0;
	static final int INF = 200 * MAX_N;
	
	static int N;
	static int[][] locations = new int[MAX_N][2]; // 위치
	static String[] names = new String[MAX_N]; // 이름
	static Map<String, Integer> bitMasks = new HashMap<>(); // 이름에 대응하는 isVisited 비트마스크
	
	static int[][] adj = new int[MAX_N][MAX_N]; // 간선
	static int[][] dp = new int[MAX_N][0x01 << MAX_N];
	
	
	
	static int manhattanDistance(int[] u, int[] v) {
		return Math.abs(u[0] - v[0]) + Math.abs(u[1] - v[1]); 
	}
	
	static int minPath(int at, int isVisited) {
		int cached = dp[at][isVisited];
		if(dp[at][isVisited] > 0) return cached;
		
		if(isVisited == (0x01 << N + 1) - 1) { // 기저
			return dp[at][isVisited] = adj[at][START];
		}
		
		int min = INF;
		for(int i = 1; i <= N; ++i) {
			if(((isVisited >> i) & 1) == 1) continue;
			min = Math.min(min, minPath(i, isVisited | bitMasks.get(names[i])) + adj[at][i]);
		}
		return dp[at][isVisited] = min;
	}
	
	static int solution() {
		return minPath(START, 0x01 << START);
	}
	
	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(br.readLine());
		for(int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine());
			locations[i][0] = Integer.parseInt(st.nextToken());
			locations[i][1] = Integer.parseInt(st.nextToken());
			names[i] = st.nextToken();
		}
		
		// 간선 가중치 생성
		for(int u = 0; u <= N; ++u) {
			for(int v = u + 1; v <= N; ++v) {
				adj[u][v] = adj[v][u] = manhattanDistance(locations[u], locations[v]);
			}
		}
		// 비트마스크 생성
		for(int i = 1; i <= N; ++i) {
			String name = names[i];
			int base = 0x00;
			if(bitMasks.containsKey(name)) {
				base = bitMasks.get(name);
			}
			bitMasks.put(name, base | (0x01 << i));
		}
		
		System.out.println(solution());;
	}

}
