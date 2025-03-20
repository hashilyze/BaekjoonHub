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
	// variables
	static int N, M;
	static List<Integer>[] adj = new List[MAX_N + 1];
	static boolean[] isVisited = new boolean[MAX_N + 1];
	static int[] dp = new int[MAX_N + 1];
	
	static int getCount(int s) {
		Arrays.fill(isVisited, 1, N + 1, false);
		
		int cnt = 1;
		Deque<Integer> q = new ArrayDeque<>();
		q.offerLast(s);
		isVisited[s] = true;
		
		while(!q.isEmpty()) {
			int u = q.pollFirst();
			for(int v : adj[u]) {
				if(isVisited[v]) continue;
				q.offer(v);
				isVisited[v] = true;
				
				++cnt;
			}
		}
		return cnt;
	}
	
	static List<Integer> solution() {
		int max = 0;
		for(int u = 1; u <= N; ++u) {
			max = Math.max(max, dp[u] = getCount(u));
		}
		
		List<Integer> li = new ArrayList<>();
		for(int u = 1; u <= N; ++u) {
			if(max == dp[u]) li.add(u);
		}
		return li;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt(); M = readInt();
		for(int i = 1; i <= N; ++i) adj[i] = new ArrayList<Integer>();
		for(int i = 0; i < M; ++i) {
			int a = readInt(), b = readInt();
			adj[b].add(a);
		}
		
		List<Integer> ans = solution();
		for(int e : ans) sb.append(e).append(" ");
		System.out.println(sb);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}