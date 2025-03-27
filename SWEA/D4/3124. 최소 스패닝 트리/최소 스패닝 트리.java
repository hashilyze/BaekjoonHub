import java.io.*;
import java.util.*;

public class Solution {
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	static class Edge implements Comparable<Edge> {
		int u, v, w;
		public Edge(int u, int v, int w) {this.u = u;this.v = v;this.w = w;}
		
		@Override
		public int compareTo(Edge o) {return this.w-o.w;}
	}
	// constants
	static final int MAX_V = 100_000;
	// variables
	static int V, E;
	static int[] parents = new int[MAX_V + 1];
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
	
	static long solution() {
		long sum = 0;
		int cnt = V;
		while(cnt > 1) {
			Edge edge = pq.poll();
			if(merge(edge.u, edge.v)) {
				sum += edge.w;
				--cnt;
			}
		}
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		int T = readInt();
		for(int t = 1; t <= T; ++t) {
			V = readInt(); E = readInt();
			// 초기화
			for(int i = 0; i <= V; ++i) parents[i] = i;
			pq.clear();
			
			for(int i = 0; i < E; ++i) {
				pq.offer(new Edge(readInt(), readInt(), readInt()));
			}
			sb.append('#').append(t).append(' ').append(solution()).append('\n');
		}
		System.out.println(sb);
	}
	
	static int readInt() throws IOException {
		int c, n = 0 , s = 1;
		while((c = System.in.read()) <= 0x20);
		if(c == '-') {
			s = -1;
			c = System.in.read();
		}
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n * s;
	}
}
