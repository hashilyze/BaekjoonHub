import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int MAX_N = 1_000;
	static final int MAX_W = 100_000;
	static final int INF = MAX_N * MAX_W;
	static int N, M, S, T;
	static List<int[]>[] adj = new List[MAX_N];
	static List<int[]>[] invAdj = new List[MAX_N];
	static boolean[] isVisited = new boolean[MAX_N];
	static int[] minDist = new int[MAX_N];
	static int[] previous = new int[MAX_N];
	static List<Integer> path = new ArrayList<Integer>();
	
	
	static int solution() {
		Arrays.fill(minDist, 0, N, INF);
		
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((lhs, rhs) -> lhs[1] - rhs[1]);
		pq.add(new int[] {S, 0});
		minDist[S] = 0;
		
		while(!pq.isEmpty()) {
			int[] node = pq.poll();
			int u = node[0], w = node[1];
			if(isVisited[u]) continue;
			isVisited[u] = true;
			
			for(int[] next : adj[u]) {
				int v = next[0], edge = next[1];
				if(w + edge < minDist[v]) {
					previous[v] = u;
					minDist[v] = w + edge;
					pq.add(new int[] {v, minDist[v]});
				}
			}
		}
		
		int x = T;
		path.add(T);
		while(x != S) {
			x = previous[x];
			path.add(x);
		}
		Collections.reverse(path);
		return minDist[T];
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; ++i) adj[i] = new ArrayList<int[]>();
		for(int i = 0; i < N; ++i) invAdj[i] = new ArrayList<int[]>();
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			adj[u].add(new int[] {v, w});
			invAdj[v].add(new int[] {u, w});
		}
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken()) - 1;
		T = Integer.parseInt(st.nextToken()) - 1;
		
		solution();
		sb.append(minDist[T]).append("\n")
			.append(path.size()).append("\n");
		for(int v : path) sb.append(v + 1).append(" ");
		System.out.println(sb);
	}
}