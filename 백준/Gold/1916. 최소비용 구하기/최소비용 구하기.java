import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int INF = 1_000 * 100_000;
	static int N, M, S, T;
	static List<int[]>[] adj = new List[1_000];
	static int[] minDist = new int[1_000];
	
	static int solution() {
		Arrays.fill(minDist, 0, N, INF);
		
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((lhs, rhs) -> lhs[1] - rhs[1]);
		pq.add(new int[] {S, 0});
		minDist[S] = 0;
		
		while(!pq.isEmpty()) {
			int[] node = pq.poll();
			int u = node[0], w = node[1];
			if(minDist[u] < w) continue;
			
			for(int[] next : adj[u]) {
				int v = next[0], edge = next[1];
				if(w + edge < minDist[v]) {
					minDist[v] = w + edge;
					pq.add(new int[] {v, minDist[v]});
				}
			}
		}
		return minDist[T];
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; ++i) adj[i] = new ArrayList<int[]>();
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			adj[u].add(new int[] {v, w});
		}
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken()) - 1;
		T = Integer.parseInt(st.nextToken()) - 1;
		
		System.out.println(solution());
	}
}