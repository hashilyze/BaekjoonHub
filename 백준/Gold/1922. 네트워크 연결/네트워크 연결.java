import java.io.*;
import java.util.*;


public class Main {
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	static class Edge implements Comparable<Edge>{
		int u, v, w;

		public Edge() { }
		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) { return this.w-o.w; }
		
	}
	// constants
	static final int MAX_N = 1_000;
	// variables
	static int N, M;
	static int[] parents = new int[MAX_N + 1];
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	
	
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
	
	static int solution() {
		int cnt = N, sum = 0;
		while(cnt > 1) {
			Edge edge = pq.poll();
			if(merge(edge.u, edge.v)) {
				--cnt;
				sum += edge.w;
			}
		}
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt(); M = readInt();
		for(int i = 1; i <= N; ++i) parents[i] = i;
		for(int i = 0; i < M; ++i) {
			int u = readInt(), v = readInt(), w = readInt();
			pq.offer(new Edge(u, v, w));
		}
		System.out.println(solution());
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}