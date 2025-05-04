import java.io.*;
import java.util.*;


public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	static final int MAX_N = 50_000;
	// variables
	static int N, M;
	static List<Integer>[] adj = new List[MAX_N + 1];
	static boolean[] isVisited = new boolean[MAX_N + 1];
	
	static int[] parents = new int[MAX_N + 1];
	static int[] depth = new int[MAX_N + 1];
	
	
	static void initTree(int u) {
		isVisited[u] = true;
		for(int v : adj[u]) {
			if(isVisited[v]) continue;
			
			parents[v] = u;
			depth[v] = depth[u] + 1;
			initTree(v);
		}
	}
	
	static int lca(int u, int v) {
		while(depth[u] < depth[v]) v = parents[v];
		while(depth[u] > depth[v]) u = parents[u];
		while(u != v) {
			u = parents[u];
			v = parents[v];
		}
		return u;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 1; i <= N; ++i) adj[i] = new ArrayList<>();
		for(int i = 0; i < N-1; ++i) {
			int u = readInt(), v = readInt();
			adj[u].add(v);
			adj[v].add(u);
		}
		
		initTree(1);
		
		M = readInt();
		for(int i = 0; i < M; ++i) {
			int u = readInt(), v = readInt();
			sb.append(lca(u, v)).append("\n");
		}
		System.out.print(sb);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}