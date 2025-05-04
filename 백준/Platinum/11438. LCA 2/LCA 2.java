import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	static final int MAX_N = 100_000;
	// variables
	static int N, M;
	static List<Integer>[] adj = new List[MAX_N + 1];
	static boolean[] isVisited = new boolean[MAX_N + 1];

	static int idx = 0;
	static int[] inv = new int[MAX_N + 1];
	static int[] depth = new int[MAX_N * 2 - 1];
	static int[] nodes = new int[MAX_N * 2 - 1];
	
	static int[] segment = new int[MAX_N << 3];
	
	static int initSegment(int rangeL, int rangeR, int index) {
		if(rangeL == rangeR) 
			return segment[index] = rangeL;
		int mid = (rangeL + rangeR) >> 1;
		int lhs = initSegment(rangeL, mid, index * 2 + 1);
		int rhs = initSegment(mid+1, rangeR, index * 2 + 2);
		
		if(depth[lhs] < depth[rhs]) 
			return segment[index] = lhs;
		return segment[index] = rhs;
	}
	static int querySegment(int queryL, int queryR, int rangeL, int rangeR, int index) {
		if(rangeR < queryL || queryR < rangeL) return Integer.MAX_VALUE;
		if(queryL <= rangeL && rangeR <= queryR) return segment[index];
		
		int mid = (rangeL + rangeR) >> 1;
		int lhs = querySegment(queryL, queryR, rangeL, mid, index * 2 + 1);
		int rhs = querySegment(queryL, queryR, mid+1, rangeR, index * 2 + 2);
		
		if(lhs == Integer.MAX_VALUE) return rhs;
		else if(rhs == Integer.MAX_VALUE) return lhs;
		
		if(depth[lhs] < depth[rhs]) 
			return lhs;
		return rhs;
	}
	

	static void init(int u, int d) {
		isVisited[u] = true;
		nodes[idx] = u;
		depth[idx] = d;
		inv[u] = idx;
		++idx;

		for (int v : adj[u]) {
			if (isVisited[v]) continue;
			init(v, d + 1);
			
			nodes[idx] = u;
			depth[idx] = d;
			inv[u] = idx;
			++idx;
		}
	}

	static int lca(int u, int v) {
		int left = Math.min(inv[u], inv[v]);
		int right = Math.max(inv[u], inv[v]);
		
		return nodes[querySegment(left, right, 0, 2*N-2, 0)];
	}

	public static void main(String[] args) throws IOException {
		N = readInt();
		for (int i = 0; i <= N; ++i)
			adj[i] = new ArrayList<>();
		for (int i = 0; i < N - 1; ++i) {
			int u = readInt(), v = readInt();
			adj[u].add(v);
			adj[v].add(u);
		}
		init(1, 0);
		initSegment(0, N*2-2, 0);

		M = readInt();
		for (int i = 0; i < M; ++i) {
			int u = readInt(), v = readInt();
			int lca = lca(u, v);
			sb.append(lca).append("\n");
		}
		System.out.print(sb);
	}

	static int readInt() throws IOException {
		int c, n = 0;
		while ((c = System.in.read()) >= 0x30)
			n = (n << 3) + (n << 1) + (c & 0x0F);
		if (c == '\r')
			System.in.read();
		return n;
	}
}