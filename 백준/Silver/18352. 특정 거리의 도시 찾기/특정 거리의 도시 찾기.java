import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N, M, K, X;
	static List<Integer>[] adj = new List[300_000];
	static boolean[] isVisited = new boolean[300_000];
	static List<Integer> ans = new ArrayList<Integer>();
	
	static void solution() {
		Deque<int[]> q = new ArrayDeque<int[]>();
		q.add(new int[] {X, 0});
		isVisited[X] = true;
		
		while(!q.isEmpty()) {
			int[] node = q.pollFirst();
			int u = node[0], w = node[1];
			
			for(int v : adj[u]) {
				if(isVisited[v]) continue;
				
				isVisited[v] = true;
				q.add(new int[] {v, w + 1});
				if(w + 1 == K) ans.add(v);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken()) - 1;
		
		for(int i = 0; i < N; ++i) adj[i] = new ArrayList<Integer>();
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			adj[u].add(v);
		}
		
		solution();
		
		if(ans.isEmpty()) System.out.print("-1");
		else {
			Collections.sort(ans);
			for(int e : ans) sb.append(e + 1).append("\n");
			System.out.print(sb);
		}
	}
}