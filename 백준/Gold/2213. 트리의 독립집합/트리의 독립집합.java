import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int MAX_N = 10_000;
	static final int SRC = 1;
	// variables
	static int N;
	static List<Integer>[] adj = new List[MAX_N + 1];
	static boolean[] isVisited = new boolean[MAX_N + 1];
	static int[][] dp = new int[MAX_N + 1][2];
	static int max;
	static List<Integer> li = new ArrayList<>();
	
	
	static void makeMaxValue(int p, int u) {
		if(isVisited[u]) return;
		isVisited[u] = true;
		
		for(int v : adj[u]) makeMaxValue(u, v);
		
		if(p != 0) {
			// 부모 정점을 선택/미선택 하였을 때 최댓값을 갱신
			dp[p][0] += Math.max(dp[u][0], dp[u][1]); 
			dp[p][1] += dp[u][0];
		}
	}
	
	static void makeIndependentSet(boolean pSelected, int u) {
		if(isVisited[u]) return;
		isVisited[u] = true;
		
		// 최댓값에 대해 정점 u가 선택되었는지 판별
		boolean isSelected = !pSelected && dp[u][0] < dp[u][1]; 
		if(isSelected) li.add(u);			
		for(int v : adj[u]) makeIndependentSet(isSelected, v);
	}
	
	static void solution() {
		makeMaxValue(0, SRC);
		max = Math.max(dp[1][0], dp[1][1]);
		Arrays.fill(isVisited, false);
		makeIndependentSet(false, SRC);
		Collections.sort(li);
	}
	
	public static void main(String[] args) throws IOException {
		// Input
		N = readInt();
		for(int i = 1; i <= N; ++i) adj[i] = new ArrayList<>(); // 인접 리스트 초기화
		
		for(int i = 1; i <= N; ++i) dp[i][1] = readInt(); // 정점 i를 선택하였을 때 값 저장
		for(int i = 0; i < N - 1; ++i) {
			int u = readInt(), v = readInt();
			adj[u].add(v);
			adj[v].add(u);
		}
		solution();
		// Output
		sb.append(max).append("\n");
		for(int e : li) sb.append(e).append(" ");
		System.out.print(sb);
	}
	
	// 부호없는 정수 읽기
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}