import java.io.*;
import java.util.*;


public class Main {
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	static class Edge implements Comparable<Edge>{
		int u, v;
		double w;

		public Edge(int u, int v, double w) { this.u = u;this.v = v;this.w = w; }

		@Override
		public int compareTo(Edge o) { return this.w < o.w ? -1 : 1; }
	}
	// constants
	// variables
	static int N, M;
	static int[][] positions = new int[1_000][2];
	static int[] parents = new int[1_000];
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	
	
	static double distance(int p1, int p2) {
		long dx = positions[p1][0] - positions[p2][0];
		long dy = positions[p1][1] - positions[p2][1];
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	static int getId(int u) {
		if(u == parents[u]) return u;
		return parents[u] = getId(parents[u]);
	}
	static boolean merge(int u, int v) {
		u = getId(u); v = getId(v);
		if(u == v) return false;
		parents[v] = u;
		return true;
	}
	
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();
		
		for(int i = 0; i < N; ++i) parents[i] = i;
		for(int i = 0; i < N; ++i) {
			positions[i][0] = readInt();
			positions[i][1] = readInt();
		}
		
		int cnt = N;
		for(int i = 0; i < M; ++i) {
			int u = readInt(), v = readInt();
			if(merge(u - 1, v - 1)) --cnt;
		}
		// 간선 생성
		for(int u = 0; u < N; ++u) {
			for(int v = 0; v < u; ++v) {
				pq.offer(new Edge(u, v, distance(u, v)));
			}
		}
		// Kruskal
		double sum = 0.0;
		while(cnt > 1) {
			Edge edge = pq.poll();
			if(merge(edge.u, edge.v)) {
				--cnt;
				sum += edge.w;
			}
		}
		System.out.printf("%.2f", sum);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}