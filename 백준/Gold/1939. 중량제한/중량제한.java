import java.io.*;
import java.util.*;

import javax.print.attribute.HashAttributeSet;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	static class Node implements Comparable<Node>{
		int v, w;
		
		Node(int v, int w) {this.v=v;this.w=w;}

		@Override
		public int compareTo(Node o) { return o.w - this.w; } // 중량제한이 큰 간선 순으로 정렬
	}
	// constants
	static final int MAX_N = 10_000;
	// variables
	static int N, M;
	static int S, T;
	static List<Node>[] adj = new List[MAX_N + 1];
	static int[] maxFlow = new int[MAX_N + 1]; 
	
	static int solution() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		maxFlow[S] = Integer.MAX_VALUE;
		pq.offer(new Node(S, maxFlow[S]));
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			if(node.v == T) return node.w;
			
			for(Node next : adj[node.v]) {
				int flow = Math.min(node.w, next.w);
				if(maxFlow[next.v] < flow) {
					maxFlow[next.v] = flow;
					pq.offer(new Node(next.v, flow));
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();
		for(int i = 1; i <= N; ++i) adj[i] = new ArrayList<>();
		for(int i = 0; i < M; ++i) {
			int a = readInt(), b = readInt(), c = readInt();
			adj[a].add(new Node(b, c));
			adj[b].add(new Node(a, c));
		}
		S = readInt(); T = readInt();
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
