import java.io.*;
import java.util.*;


public class Main {
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	static class Node implements Comparable<Node>{
		int v;
		double w;
		Node(int v, double w){this.v=v;this.w=w;}
		
		@Override
		public int compareTo(Node o) { return this.w - o.w < 0 ? -1 : 1; }
	}
	// constants
	static final int MAX_N = 100;
	// variables
	static int N;
	static double[][] points = new double[MAX_N][2];
	static double[][] adj = new double[MAX_N][MAX_N];  
	static boolean[] isVisited = new boolean[MAX_N];
	
	static double distance(int u, int v) {
		double[] p1 = points[u], p2 = points[v];
		double dx = p1[0] - p2[0], dy = p1[1] - p2[1];
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	static double solution() {
		int cnt = 1;
		double sum = 0.0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		isVisited[0] = true;
		for(int v = 0; v < N; ++v) pq.offer(new Node(v, adj[0][v]));
		
		while(cnt < N) {
			Node node = pq.poll();
			
			if(isVisited[node.v]) continue;
			isVisited[node.v] = true;
			sum += node.w;
			++cnt;
			
			for(int v = 0; v < N; ++v) {
				if(isVisited[v]) continue;
				pq.offer(new Node(v, adj[node.v][v]));
			}
		}
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			points[i][0] = Double.parseDouble(st.nextToken());
			points[i][1] = Double.parseDouble(st.nextToken());
		}
		for(int u = 0; u < N; ++u) {
			for(int v = 0; v < u; ++v) {
				adj[u][v] = adj[v][u] = distance(u, v);   
			}
		}
		System.out.println(String.format("%.2f", solution()));
	}
}