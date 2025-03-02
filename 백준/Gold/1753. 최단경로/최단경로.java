import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static class Node {
		int next, w;
		
		Node() { }
		Node(int next, int w) { this.next = next; this.w = w; }
	}
	
	static final int MAX_V = 20_000;
	static final int MAX_E = 300_000;
	static final int INF = Integer.MAX_VALUE;
	
	static int V, E, K;
	static List<Node>[] adj = new ArrayList[MAX_V];
	static int[] minDist = new int[MAX_V];
	
	
	static void solution() {
		Arrays.fill(minDist, 0, V, INF);
		
		PriorityQueue<Node> q = new PriorityQueue<>((lhs, rhs) -> lhs.w - rhs.w);
		q.offer(new Node(K, 0));
		minDist[K] = 0;
		
		while(!q.isEmpty()) {
			Node u = q.poll();
			
			for(Node v : adj[u.next]) {
				int nextW = u.w + v.w;
				if(nextW < minDist[v.next]) {
					minDist[v.next] = nextW;
					q.offer(new Node (v.next, nextW));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		for(int i = 0; i < V; ++i) adj[i] = new ArrayList<Node>();
		
		
		K = Integer.parseInt(br.readLine()) - 1;
		for(int i = 0; i < E; ++i) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			adj[u].add(new Node (v, w));
		}
		
		solution();
		
		for(int i = 0; i < V; ++i) {
			if(minDist[i] == INF) sb.append("INF\n");
			else sb.append(minDist[i]).append("\n");
		}
		System.out.println(sb);
	}
}