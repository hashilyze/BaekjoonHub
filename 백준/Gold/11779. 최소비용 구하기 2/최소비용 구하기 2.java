import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static class Node implements Comparable<Node>{
		int vertex, weight;
		
		Node() { }
		Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node other) {
			return this.weight - other.weight;
		}
	}
	
	static final int MAX_N = 1_000;
	static final int MAX_W = 100_000;
	static final int INF = MAX_N * MAX_W;
	
	static int N, M, S, T;
	static List<Node>[] adj = new List[MAX_N];
	static boolean[] isVisited = new boolean[MAX_N];
	static int[] minDist = new int[MAX_N];
	static int[] previous = new int[MAX_N];
	static List<Integer> path = new ArrayList<Integer>();
	
	
	static void solution() {
		Arrays.fill(minDist, 0, N, INF);
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.offer(new Node(S, 0));
		minDist[S] = 0;
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int u = node.vertex, w = node.weight;
			if(isVisited[u]) continue;
			isVisited[u] = true;
			
			for(Node next : adj[u]) {
				int v = next.vertex, edge = next.weight;
				if(w + edge < minDist[v]) {
					previous[v] = u;
					minDist[v] = w + edge;
					pq.offer(new Node(v, minDist[v]));
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
	}
	
	public static void main(String[] args) throws IOException {
		// Input
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; ++i) adj[i] = new ArrayList<Node>();
		
		M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			adj[u].add(new Node(v, w));
		}
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken()) - 1;
		T = Integer.parseInt(st.nextToken()) - 1;
		
		solution();
		
		// Output
		sb.append(minDist[T]).append("\n")
			.append(path.size()).append("\n");
		for(int v : path) sb.append(v + 1).append(" ");
		System.out.print(sb);
	}
}